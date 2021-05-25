package rs.raf.project.repositories.Korisnik;

import rs.raf.project.entities.Korisnik;

import java.util.List;

public interface KorisnikRepository {

    public Korisnik addKorisnik(Korisnik korisnik);
    //public List<Korisnik> allCommentsForParentId(int id);
    //public Korisnik findComment(Integer id);
    public void updateKorisnik(Integer id);

    //public void setStatusKorisnik(Integer id);

}