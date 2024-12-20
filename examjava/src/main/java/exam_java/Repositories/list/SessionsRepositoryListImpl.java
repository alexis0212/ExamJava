package exam_java.Repositories.list;

import exam_java.Repositories.SessionRepository;
import exam_java.entities.Sessions;

import java.util.Optional;

public class SessionsRepositoryListImpl extends RepositoryListImpl<Sessions> implements SessionRepository {

    @Override
    public Optional<Sessions> findById(Object id) {
        return data.stream()
                .filter(session -> session.getId() == (int) id)
                .findFirst();
    }
}
