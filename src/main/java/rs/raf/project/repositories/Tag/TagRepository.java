package rs.raf.project.repositories.Tag;

import rs.raf.project.entities.Tag;

public interface TagRepository {


    public Tag addTag(Tag tag);
    public Integer getTagId(String tag);
    public Tag getTagById(Integer id);
}
