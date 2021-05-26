package rs.raf.project.entities;

import rs.raf.project.enums.Status;
import rs.raf.project.enums.TipKorisnika;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class Korisnik {

    private Integer id;

    @NotNull(message = "Title field is required")
    @NotEmpty(message = "Title field is required")
    private String email;

    @NotNull(message = "Title field is required")
    @NotEmpty(message = "Title field is required")
    private String imeIPrezime;


    private TipKorisnika tipKorisnika;

    private Status status;

    @NotNull(message = "Title field is required")
    @NotEmpty(message = "Title field is required")
    private String lozinka;

    public Korisnik(){}

    public Korisnik(@NotNull(message = "Title field is required") @NotEmpty(message = "Title field is required") String email, @NotNull(message = "Title field is required") @NotEmpty(message = "Title field is required") String imeIPrezime, TipKorisnika tipKorisnika, @NotNull(message = "Title field is required") @NotEmpty(message = "Title field is required") String lozinka) {
        this.email = email;
        this.imeIPrezime = imeIPrezime;
        this.tipKorisnika = tipKorisnika;
        this.status = Status.AKTIVAN;
        this.lozinka = lozinka;
    }


    public Korisnik(@NotNull(message = "Title field is required") @NotEmpty(message = "Title field is required") String email, @NotNull(message = "Title field is required") @NotEmpty(message = "Title field is required") String imeIPrezime, @NotNull(message = "Title field is required") @NotEmpty(message = "Title field is required") String lozinka) {
        this.email = email;
        this.imeIPrezime = imeIPrezime;
        this.lozinka = lozinka;
        this.status = Status.AKTIVAN;
        this.tipKorisnika = TipKorisnika.CONTNENT_CREATOR;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getImeIPrezime() {
        return imeIPrezime;
    }

    public void setImeIPrezime(String imeIPrezime) {
        this.imeIPrezime = imeIPrezime;
    }

    public TipKorisnika getTipKorisnika() {
        return tipKorisnika;
    }

    public void setTipKorisnika(TipKorisnika tipKorisnika) {
        this.tipKorisnika = tipKorisnika;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public String getLozinka() {
        return lozinka;
    }

    public void setLozinka(String lozinka) {
        this.lozinka = lozinka;
    }

    public Integer getId() {
        return id;
    }
}
