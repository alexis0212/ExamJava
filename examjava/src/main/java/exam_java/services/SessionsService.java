package exam_java.services;

import exam_java.entities.Sessions;

import java.util.List;
import java.util.Optional;

public interface SessionsService {
    void ajouterSession(Sessions session);
    void supprimerSession(Sessions session);
    List<Sessions> listerSessions();
    Optional<Sessions> rechercherSessionParId(int id);
}
