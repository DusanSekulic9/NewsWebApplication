package rs.raf.project.services;

import rs.raf.project.entities.Tag;
import rs.raf.project.entities.Vest;
import rs.raf.project.entities.Vest_Tag;
import rs.raf.project.repositories.vest_tag.Vest_TagRepository;

import javax.inject.Inject;
import java.util.List;

public class Vest_TagService {

    @Inject
    private Vest_TagRepository vest_tagRepository;

    public List<Vest> getVestiByTagId(Integer tagId, Integer page){
        return vest_tagRepository.getVestiByTagId(tagId, page);
    }

    public List<Tag> getTagsByVestId(Integer vestId){
        return vest_tagRepository.getTagsByVestId(vestId);
    }

    public void addVest_Tag(Integer vestId, Integer tagId){
        vest_tagRepository.addVest_Tag(vestId,tagId);
    }

    public List<Vest_Tag> all(){
        return vest_tagRepository.all();
    }

    public int getPagginationForTag(Integer tagId){
        return this.vest_tagRepository.paginationForTags(tagId);
    }
}
