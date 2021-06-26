package rs.raf.project.services;

import rs.raf.project.entities.Kategorija;
import rs.raf.project.repositories.Kategorija.KategorijaRepository;

import javax.inject.Inject;
import javax.validation.Valid;
import java.util.List;

public class KategorijaService {

    @Inject
    private KategorijaRepository kategorijaRepository;

    public List<Kategorija> all(Integer page) {
        return this.kategorijaRepository.all(page);
    }

    public Kategorija addKategorija(Kategorija kategorija) {
        return kategorijaRepository.addKategorija(kategorija);
    }

    public void deleteKategorija(Integer id) {
        this.kategorijaRepository.deleteKategorija(id);
    }

    public Kategorija updateKategorija(@Valid Kategorija kategorija, Integer id) {
        return this.kategorijaRepository.updateKategorija(kategorija, id);
    }

    public int getPagginationForCategory(){
        return this.kategorijaRepository.getPagginationLimitForAllCategories();
    }
}
