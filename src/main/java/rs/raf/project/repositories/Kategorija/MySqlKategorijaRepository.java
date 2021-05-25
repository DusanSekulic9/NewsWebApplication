package rs.raf.project.repositories.Kategorija;

import rs.raf.project.entities.Kategorija;
import rs.raf.project.repositories.MySqlAbstractRepository;

public class MySqlKategorijaRepository extends MySqlAbstractRepository implements KategorijaRepository {
    @Override
    public Kategorija addKategorija(Kategorija kategorija) {
        return null;
    }

    @Override
    public void deleteKategorija(Integer id) {

    }

    @Override
    public void updateKategorija(Integer id) {

    }
}
