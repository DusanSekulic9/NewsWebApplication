package rs.raf.project.repositories.Vest;

import rs.raf.project.entities.Vest;

import java.util.List;

public interface VestRepository {

    public Vest addVest(Vest vest);
    public Vest updateVest(Vest id);
    public void deleteVest(Integer id);
    public List<Vest> RecentVesti(Integer brStrane);
    public List<Vest> PopularVesti(Integer brStrane);
    public List<Vest> VestIzKategorije(Integer idKategorije, Integer brStrane);
    public List<Vest> all();
    public Vest getVest(Integer id);
    public int getPagginationForPopularNews();
    public int getPagginationForAllNews();
    public int getPagginationForSearchedNews(String parameter);
    public List<Vest> getSearchedVesti(String parameter, Integer brStrane);

    //TO-DO Cookie

}
