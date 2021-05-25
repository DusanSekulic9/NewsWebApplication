package rs.raf.project.entities;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class Tag {

    @NotNull(message = "Title field is required")
    @NotEmpty(message = "Title field is required")
    private String rec;

    public Tag(@NotNull(message = "Title field is required") @NotEmpty(message = "Title field is required") String rec) {
        this.rec = rec;
    }

    public Tag(){}

    public String getRec() {
        return rec;
    }

    public void setRec(String rec) {
        this.rec = rec;
    }
}
