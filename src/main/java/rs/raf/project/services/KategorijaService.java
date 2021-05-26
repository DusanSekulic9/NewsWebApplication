package rs.raf.project.services;

import rs.raf.project.entities.Kategorija;
import rs.raf.project.repositories.Kategorija.KategorijaRepository;

import javax.inject.Inject;
import javax.validation.Valid;
import java.util.List;

public class KategorijaService {

    @Inject
    private KategorijaRepository kategorijaRepository;

    public List<Kategorija> all() {
        return this.kategorijaRepository.all();
    }

    public Kategorija addKategorija(Kategorija kategorija) {
        return kategorijaRepository.addKategorija(kategorija);
    }

    public void deleteKategorija(Integer id) {
        this.kategorijaRepository.deleteKategorija(id);
    }

    public Kategorija updateKategorija(@Valid Kategorija kategorija) {
        return this.kategorijaRepository.updateKategorija(kategorija);
    }
}
