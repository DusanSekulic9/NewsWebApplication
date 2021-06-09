package rs.raf.project.entities;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Date;

public class Komentar {

    private Integer id;

    private Integer vestId;

    @NotNull(message = "Title field is required")
    @NotEmpty(message = "Title field is required")
    private String autor;

    @NotNull(message = "Title field is required")
    @NotEmpty(message = "Title field is required")
    private String teskt;

    private Date datum;

    public Komentar(){

    }

    public Komentar(@NotNull(message = "Title field is required") @NotEmpty(message = "Title field is required") String autor, @NotNull(message = "Title field is required") @NotEmpty(message = "Title field is required") String teskt) {
        this.autor = autor;
        this.teskt = teskt;
        this.datum = new Date();
    }

    public Komentar(Integer id, Integer vestId, @NotNull(message = "Title field is required") @NotEmpty(message = "Title field is required") String autor, @NotNull(message = "Title field is required") @NotEmpty(message = "Title field is required") String teskt, Date datum) {
        this.id = id;
        this.vestId = vestId;
        this.autor = autor;
        this.teskt = teskt;
        this.datum = datum;
    }

    public Date getDatum() {
        return datum;
    }

    public String getAutor() {
        return autor;
    }

    public String getTeskt() {
        return teskt;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public void setTeskt(String teskt) {
        this.teskt = teskt;
    }

    public void setDatum(Date datum) {
        this.datum = datum;
    }

    public Integer getVestId() {
        return vestId;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
