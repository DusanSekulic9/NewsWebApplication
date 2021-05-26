package rs.raf.project.repositories.Vest;

import rs.raf.project.entities.Vest;

import java.util.List;

public interface VestRepository {

    public Vest addVest(Vest vest);
    public Vest updateVest(Vest id);
    public void deleteVest(Integer id);
    public void counterUpdate(Integer id);

    public List<Vest> all();
    public Vest getVest(Integer id);
}
