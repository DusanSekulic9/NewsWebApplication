package rs.raf.project.entities;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

public class Kategorija {

    private Integer id;

    @NotNull(message = "Title field is required")
    @NotEmpty(message = "Title field is required")
    private String ime;


    @NotNull(message = "Title field is required")
    @NotEmpty(message = "Title field is required")
    private String opis;

    List<Vest> vesti;

    public Kategorija(){

    }

    public Kategorija(Integer id,@NotNull(message = "Title field is required") @NotEmpty(message = "Title field is required") String ime, @NotNull(message = "Title field is required") @NotEmpty(message = "Title field is required") String opis) {
        this.id = id;
        this.ime = ime;
        this.opis = opis;
        this.vesti = new ArrayList<>();
    }

    public String getIme() {
        return ime;
    }

    public String getOpis() {
        return opis;
    }

    public void setIme(String ime) {
        this.ime = ime;
    }

    public void setOpis(String opis) {
        this.opis = opis;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
