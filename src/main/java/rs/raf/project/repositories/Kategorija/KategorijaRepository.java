package rs.raf.project.repositories.Kategorija;

import rs.raf.project.entities.Kategorija;

import java.util.List;

public interface KategorijaRepository {

    public List<Kategorija> all();
    public Kategorija addKategorija(Kategorija kategorija);
    public void deleteKategorija(Integer id);
    public Kategorija updateKategorija(Kategorija kategorija);

}
