package rs.raf.project.repositories.Komentar;

import rs.raf.project.entities.Komentar;

import java.util.List;

public interface KomentarRepository {

    public Komentar addKomentar(Komentar comment);
    public List<Komentar> allCommentsForParentId(int id);
    //public Komentar findComment(Integer id);
    public void deleteKomentar(Integer id);
}
