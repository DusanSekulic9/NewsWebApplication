package rs.raf.project.repositories.Vest;

import rs.raf.project.entities.Vest;

public interface VestRepository {

    public Vest addVest(Vest vest);
    public void updateVest(Integer id);
    public void deleteVest(Integer id);
    public void counterUpdate(Integer id);
}
