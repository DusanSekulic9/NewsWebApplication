package rs.raf.project.services;

import rs.raf.project.entities.Komentar;
import rs.raf.project.repositories.Komentar.KomentarRepository;

import javax.inject.Inject;
import java.util.List;

public class KomentarService {

    @Inject
    private KomentarRepository komentarRepository;

    public Komentar addKomentar(Komentar komentar) {
        return this.komentarRepository.addKomentar(komentar);
    }

    public List<Komentar> allCommentsForParentId(int id) {
        return this.komentarRepository.allCommentsForParentId(id);
    }

    public void deleteKomentar(Integer id) {
        this.komentarRepository.deleteKomentar(id);
    }
}
