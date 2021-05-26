package rs.raf.project.entities;

public class Vest_Tag {

    private Integer id;

    private Integer vestId;
    private Integer tagId;

    public Vest_Tag(){

    }

    public Vest_Tag(Integer vestId, Integer tagId) {
        this.vestId = vestId;
        this.tagId = tagId;
    }

    public Integer getVestId() {
        return vestId;
    }

    public void setVestId(Integer vestId) {
        this.vestId = vestId;
    }

    public Integer getTagId() {
        return tagId;
    }

    public void setTagId(Integer tagId) {
        this.tagId = tagId;
    }

    public Integer getId() {
        return id;
    }
}
