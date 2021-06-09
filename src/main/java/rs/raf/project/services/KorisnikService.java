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

    public List<Korisnik> all() {
        return this.korisnikRepository.all();
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
                .withClaim("status", korisnik.getStatus().toString())
                .withClaim("tip", korisnik.getTipKorisnika().toString())
                .sign(algorithm);
    }

    public boolean isLoggedIn(String token){
        Algorithm algorithm = Algorithm.HMAC256("sifra123");
        JWTVerifier verifier = JWT.require(algorithm).build();
        DecodedJWT jwt = verifier.verify(token);
        String email = jwt.getSubject();
        Status status = Status.valueOf(jwt.getClaim("status").asString());
        if (status.equals(Status.NEAKTIVAN)) return false;
        Korisnik korisnik = this.korisnikRepository.getKorisnikByEmail(email);
        if(korisnik == null) return false;
        return true;
    }

    public boolean isAdmin(String token){
        Algorithm algorithm = Algorithm.HMAC256("sifra123");
        JWTVerifier verifier = JWT.require(algorithm).build();
        DecodedJWT jwt = verifier.verify(token);
        String email = jwt.getSubject();
        String strStatus = jwt.getClaim("status").asString();
        TipKorisnika tip = TipKorisnika.valueOf(jwt.getClaim("tip").asString());
        Status status = Status.valueOf(jwt.getClaim("status").asString());
        if (status.equals(Status.NEAKTIVAN)) return false;
        Korisnik korisnik = this.korisnikRepository.getKorisnikByEmail(email);
        if(korisnik == null) return false;
        if(tip.equals(TipKorisnika.CONTNENT_CREATOR)) return false;
        return true;
    }

}
