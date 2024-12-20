package exam_java.services.implemente;

import exam_java.entities.Sessions;
import exam_java.Repositories.SessionRepository;
import exam_java.services.SessionsService;

import java.util.List;
import java.util.Optional;

public class SessionsServiceImpl implements SessionsService {
    private final SessionRepository sessionRepository;

    public SessionsServiceImpl(SessionRepository sessionRepository) {
        this.sessionRepository = sessionRepository;
    }

    @Override
    public void ajouterSession(Sessions session) {
        sessionRepository.save(session);
    }

    @Override
    public void supprimerSession(Sessions session) {
        sessionRepository.delete(session);
    }

    @Override
    public List<Sessions> listerSessions() {
        return sessionRepository.findAll();
    }

    @Override
    public Optional<Sessions> rechercherSessionParId(int id) {
        return sessionRepository.findById(id);
    }
}
