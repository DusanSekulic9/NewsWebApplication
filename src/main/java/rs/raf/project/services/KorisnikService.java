package rs.raf.project.services;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import rs.raf.project.entities.Korisnik;
import rs.raf.project.enums.Status;
import rs.raf.project.enums.TipKorisnika;
import rs.raf.project.repositories.Korisnik.KorisnikRepository;
import org.apache.commons.codec.digest.DigestUtils;

import javax.inject.Inject;
import javax.validation.Valid;
import java.util.Date;
import java.util.List;

public class KorisnikService {

    @Inject
    private KorisnikRepository korisnikRepository;

    public Korisnik addKorisnik(Korisnik korisnik) {
        return this.korisnikRepository.addKorisnik(korisnik);
    }

    public Korisnik updateKorisnik(@Valid Korisnik korisnik) {
       return this.korisnikRepository.updateKorisnik(korisnik);
    }

    public Korisnik getKorisnik(Integer id){
        return this.korisnikRepository.getKorisnik(id);
    }

    public List<Korisnik> all(Integer page) {
        return this.korisnikRepository.all(page);
    }

    public String login(String email, String password){
        String hashedPassword = DigestUtils.sha256Hex(password);
        Korisnik korisnik = this.korisnikRepository.getKorisnikByEmail(email);
        if (korisnik == null || !korisnik.getLozinka().equals(hashedPassword)) {
            return null;
        }
        if ( korisnik.getStatus().equals(Status.NEAKTIVAN)) return null;
        Date issuedAt = new Date();

        Algorithm algorithm = Algorithm.HMAC256("sifra123");

        return JWT.create()
                .withIssuedAt(issuedAt)
                .withSubject(email)
                .withExpiresAt(new Date("08/06/2021"))
                .withClaim("status", korisnik.getStatus().toString())
                .withClaim("tip", korisnik.getTipKorisnika().toString())
                .sign(algorithm);
    }

    public int getPagginationForAllUsers(){
        return this.korisnikRepository.getPagginationLimitForAllUsers();
    }

}
