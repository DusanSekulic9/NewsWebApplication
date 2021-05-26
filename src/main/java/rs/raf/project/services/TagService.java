package rs.raf.project.services;

import rs.raf.project.entities.Tag;
import rs.raf.project.repositories.Tag.TagRepository;

import javax.inject.Inject;

public class TagService {

    @Inject
    private TagRepository tagRepository;

    public Tag addTag(Tag tag) {
        return this.tagRepository.addTag(tag);
    }

    public Integer getTagId(String tag) {
        return this.tagRepository.getTagId(tag);
    }

    public Tag getTagById(Integer id) {
        return this.tagRepository.getTagById(id);
    }
}
