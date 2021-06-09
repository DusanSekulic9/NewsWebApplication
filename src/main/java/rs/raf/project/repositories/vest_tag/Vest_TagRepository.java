package rs.raf.project.repositories.vest_tag;

import rs.raf.project.entities.Tag;
import rs.raf.project.entities.Vest;
import rs.raf.project.entities.Vest_Tag;

import java.util.List;

public interface Vest_TagRepository {

    public List<Vest> getVestiByTagId(Integer tagId);

    public List<Tag> getTagsByVestId(Integer vestId);

    public void addVest_Tag(Integer vestId, Integer tagId);

    public List<Vest_Tag> all();
}
