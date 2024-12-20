package exam_java.Repositories.Bd;


import exam_java.entities.Sessions;
import exam_java.Repositories.SessionRepository;
import exam_java.core.bd.DataBase;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class SessionsRepositoryBD implements SessionRepository {
    private final DataBase dataBase;

    public SessionsRepositoryBD(DataBase dataBase) {
        this.dataBase = dataBase;
    }

    @Override
    public void save(Sessions session) {
        String sql = "INSERT INTO sessions (date, heure_debut, heure_fin, salle, en_ligne, cours_id) VALUES (?, ?, ?, ?, ?, ?)";
        try (PreparedStatement stmt = dataBase.getConnection().prepareStatement(sql)) {
            stmt.setDate(1, Date.valueOf(session.getDate()));
            stmt.setTime(2, Time.valueOf(session.getHeureDebut()));
            stmt.setTime(3, Time.valueOf(session.getHeureFin()));
            stmt.setString(4, session.getSalle());
            stmt.setBoolean(5, session.isEnLigne());
            stmt.setInt(6, session.getId());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(Sessions session) {
        String sql = "DELETE FROM sessions WHERE id = ?";
        try (PreparedStatement stmt = dataBase.getConnection().prepareStatement(sql)) {
            stmt.setInt(1, session.getId());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Sessions> findAll() {
        List<Sessions> sessionsList = new ArrayList<>();
        String sql = "SELECT * FROM sessions";
        try (Statement stmt = dataBase.getConnection().createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                Sessions session = new Sessions();
                session.setId(rs.getInt("id"));
                session.setDate(rs.getDate("date").toLocalDate());
                session.setHeureDebut(rs.getTime("heure_debut").toLocalTime());
                session.setHeureFin(rs.getTime("heure_fin").toLocalTime());
                session.setSalle(rs.getString("salle"));
                session.setEnLigne(rs.getBoolean("en_ligne"));
                session.setCoursId(rs.getInt("cours_id"));
                sessionsList.add(session);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return sessionsList;
    }

    @Override
    public Optional<Sessions> findById(Object id) {
        String sql = "SELECT * FROM sessions WHERE id = ?";
        try (PreparedStatement stmt = dataBase.getConnection().prepareStatement(sql)) {
            stmt.setInt(1, (int) id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                Sessions session = new Sessions();
                session.setId(rs.getInt("id"));
                session.setDate(rs.getDate("date").toLocalDate());
                session.setHeureDebut(rs.getTime("heure_debut").toLocalTime());
                session.setHeureFin(rs.getTime("heure_fin").toLocalTime());
                session.setSalle(rs.getString("salle"));
                session.setEnLigne(rs.getBoolean("en_ligne"));
                session.setCoursId(rs.getInt("cours_id"));
                return Optional.of(session);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }
}

