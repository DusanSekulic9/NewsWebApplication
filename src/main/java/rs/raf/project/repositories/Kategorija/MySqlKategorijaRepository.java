package rs.raf.project.repositories.Kategorija;

import rs.raf.project.entities.Kategorija;
import rs.raf.project.repositories.MySqlAbstractRepository;

import java.util.List;

public class MySqlKategorijaRepository extends MySqlAbstractRepository implements KategorijaRepository {
    @Override
    public List<Kategorija> all() {
        return null;
    }

    @Override
    public Kategorija addKategorija(Kategorija kategorija) {
        return null;
    }

    @Override
    public void deleteKategorija(Integer id) {

    }

    @Override
    public Kategorija updateKategorija(Kategorija kategorija) {
        return kategorija;
    }
}
