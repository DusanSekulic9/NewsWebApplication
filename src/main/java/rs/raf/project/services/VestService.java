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

    public void counterUpdate(Integer id) {
        this.vestRepository.counterUpdate(id);
    }

    public List<Vest> all() {
        return this.vestRepository.all();
    }

    public Vest getVest(Integer id) {
        return this.vestRepository.getVest(id);
    }
}
