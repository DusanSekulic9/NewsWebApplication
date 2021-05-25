package rs.raf.project.repositories.Kategorija;

import rs.raf.project.entities.Kategorija;

public interface KategorijaRepository {

    public Kategorija addKategorija(Kategorija kategorija);
    public void deleteKategorija(Integer id);
    public void updateKategorija(Integer id);

}
