package rs.raf.project.entities;

import com.google.gson.Gson;
import rs.raf.project.enums.Status;
import rs.raf.project.enums.TipKorisnika;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class Korisnik {

    Gson gson = new Gson();

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

    public Korisnik(String email, String imeIPrezime, String status, String tipKorisnika, String lozinka){
        this.email = email;
        this.imeIPrezime = imeIPrezime;
        this.tipKorisnika = TipKorisnika.valueOf(tipKorisnika);
        this.status = Status.valueOf(status);
        this.lozinka = lozinka;
    }

    public Korisnik(){

    }

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
        this.tipKorisnika = TipKorisnika.CONTENT_CREATOR;
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

    public String getTipKorisnika() {
        return tipKorisnika.toString();
    }

    public void setTipKorisnika(TipKorisnika tipKorisnika) {
        this.tipKorisnika = tipKorisnika;
    }

    public String getStatus() {
        return status.toString();
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

    public void setId(Integer id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return this.gson.toJson(this);
    }
}
