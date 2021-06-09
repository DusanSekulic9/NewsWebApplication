package rs.raf.project.services;

import rs.raf.project.entities.Vest;
import rs.raf.project.repositories.Vest.VestRepository;

import javax.inject.Inject;
import javax.validation.Valid;
import java.util.List;

public class VestService {

    @Inject
    private VestRepository vestRepository;

    public Vest addVest(Vest vest) {
        return this.vestRepository.addVest(vest);
    }

    public Vest updateVest(@Valid Vest vest) {
       return this.vestRepository.updateVest(vest);
    }

    public void deleteVest(Integer id) {
        this.vestRepository.deleteVest(id);
    }

    public List<Vest> recentVesti(Integer brStrane){
        return this.vestRepository.RecentVesti(brStrane);
    }

    public List<Vest> popularVesti(Integer brStrane){
        return this.vestRepository.PopularVesti(brStrane);
    }

    public List<Vest> vestIzKategorije(Integer kategorijaId, Integer brStrane){
        return this.vestRepository.VestIzKategorije(kategorijaId,brStrane);
    }

    public List<Vest> all() {
        return this.vestRepository.all();
    }

    public Vest getVest(Integer id) {
        return this.vestRepository.getVest(id);
    }
}
