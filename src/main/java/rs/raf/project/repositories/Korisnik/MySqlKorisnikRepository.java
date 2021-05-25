package rs.raf.project.repositories.Korisnik;

import rs.raf.project.entities.Korisnik;
import rs.raf.project.repositories.MySqlAbstractRepository;

public class MySqlKorisnikRepository extends MySqlAbstractRepository implements KorisnikRepository {
    @Override
    public Korisnik addKorisnik(Korisnik korisnik) {
        return null;
    }

    @Override
    public void updateKorisnik(Integer id) {

    }
}
