package exam_java.services.implemente;

import exam_java.entities.Niveau;
import exam_java.Repositories.NiveauRepository;
import exam_java.services.NiveauService;

import java.util.List;
import java.util.Optional;

public class NiveauServiceImpl implements NiveauService {
    private final NiveauRepository niveauRepository;

    public NiveauServiceImpl(NiveauRepository niveauRepository) {
        this.niveauRepository = niveauRepository;
    }

    @Override
    public void ajouterNiveau(Niveau niveau) {
        niveauRepository.save(niveau);
    }

    @Override
    public void supprimerNiveau(Niveau niveau) {
        niveauRepository.delete(niveau);
    }

    @Override
    public List<Niveau> listerNiveaux() {
        return niveauRepository.findAll();
    }

    @Override
    public Optional<Niveau> rechercherNiveauParId(int id) {
        return niveauRepository.findById(id);
    }
}
