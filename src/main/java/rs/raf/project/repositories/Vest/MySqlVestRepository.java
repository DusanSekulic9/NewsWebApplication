package rs.raf.project.repositories.Vest;

import rs.raf.project.entities.Vest;
import rs.raf.project.repositories.MySqlAbstractRepository;

import java.util.List;

public class MySqlVestRepository extends MySqlAbstractRepository implements VestRepository {
    @Override
    public Vest addVest(Vest vest) {
        return null;
    }

    @Override
    public Vest updateVest(Vest id) {
        return null;
    }


    @Override
    public void deleteVest(Integer id) {

    }

    @Override
    public void counterUpdate(Integer id) {

    }

    @Override
    public List<Vest> all() {
        return null;
    }

    @Override
    public Vest getVest(Integer id) {
        return null;
    }
}
