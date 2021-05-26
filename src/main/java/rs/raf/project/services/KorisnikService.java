package rs.raf.project.services;

import rs.raf.project.entities.Korisnik;
import rs.raf.project.repositories.Korisnik.KorisnikRepository;

import javax.inject.Inject;
import javax.validation.Valid;
import java.util.List;

public class KorisnikService {

    @Inject
    private KorisnikRepository korisnikRepository;

    public Korisnik addKorisnik(Korisnik korisnik) {
        return this.korisnikRepository.addKorisnik(korisnik);
    }

    public Korisnik updateKorisnik(@Valid Korisnik korisnik) {
       return this.korisnikRepository.updateKorisnik(korisnik);
    }

    public Korisnik getKorisnik(Integer id){
        return this.korisnikRepository.getKorisnik(id);
    }

    public List<Korisnik> all() {
        return this.korisnikRepository.all();
    }
}
