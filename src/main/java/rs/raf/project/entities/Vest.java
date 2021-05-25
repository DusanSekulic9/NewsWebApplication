package rs.raf.project.entities;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Vest {

    @NotNull(message = "Title field is required")
    @NotEmpty(message = "Title field is required")
    private String naslov;

    @NotNull(message = "Title field is required")
    @NotEmpty(message = "Title field is required")
    private String tekst;

    @NotNull(message = "Title field is required")
    @NotEmpty(message = "Title field is required")
    private String tagovi;

    private Date datum;
    private Integer posete;
    private Integer kreatorId;
    private Integer kategorijaId;

    private List<Komentar> komentari;

    public Vest() {
    }

    public Vest(@NotNull(message = "Title field is required") @NotEmpty(message = "Title field is required") String naslov, @NotNull(message = "Title field is required") @NotEmpty(message = "Title field is required") String tekst, @NotNull(message = "Title field is required") @NotEmpty(message = "Title field is required") String tagovi, Integer kreatorId, Integer kategorijaId) {
        this.naslov = naslov;
        this.tekst = tekst;
        this.tagovi = tagovi;
        this.kreatorId = kreatorId;
        this.kategorijaId = kategorijaId;
        this.datum = new Date();
        this.posete = 0;
        this.komentari = new ArrayList<>();
    }

    public String getNaslov() {
        return naslov;
    }

    public void setNaslov(String naslov) {
        this.naslov = naslov;
    }

    public String getTekst() {
        return tekst;
    }

    public void setTekst(String tekst) {
        this.tekst = tekst;
    }

    public String getTagovi() {
        return tagovi;
    }

    public void setTagovi(String tagovi) {
        this.tagovi = tagovi;
    }

    public Date getDatum() {
        return datum;
    }

    public void setDatum(Date datum) {
        this.datum = datum;
    }

    public Integer getPosete() {
        return posete;
    }

    public void setPosete(Integer posete) {
        this.posete = posete;
    }

    public Integer getKreatorId() {
        return kreatorId;
    }

    public void setKreatorId(Integer kreatorId) {
        this.kreatorId = kreatorId;
    }

    public Integer getKategorijaId() {
        return kategorijaId;
    }

    public void setKategorijaId(Integer kategorijaId) {
        this.kategorijaId = kategorijaId;
    }

    public List<Komentar> getKomentari() {
        return komentari;
    }

    public void setKomentari(List<Komentar> komentari) {
        this.komentari = komentari;
    }
}
