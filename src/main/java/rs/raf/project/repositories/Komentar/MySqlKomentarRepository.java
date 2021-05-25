package rs.raf.project.repositories.Komentar;

import rs.raf.project.entities.Komentar;
import rs.raf.project.repositories.MySqlAbstractRepository;

import java.util.List;

public class MySqlKomentarRepository extends MySqlAbstractRepository implements KomentarRepository {
    @Override
    public Komentar addKomentar(Komentar comment) {
        return null;
    }

    @Override
    public List<Komentar> allCommentsForParentId(int id) {
        return null;
    }

    @Override
    public void deleteKomentar(Integer id) {

    }
}
