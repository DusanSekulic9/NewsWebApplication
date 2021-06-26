package rs.raf.project.filters;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import rs.raf.project.entities.Korisnik;
import rs.raf.project.enums.Status;
import rs.raf.project.enums.TipKorisnika;
import rs.raf.project.repositories.Korisnik.KorisnikRepository;

import javax.inject.Inject;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.Provider;
import java.io.IOException;
import java.util.List;

@Provider
public class AuthFilter implements ContainerRequestFilter {

    @Inject
    KorisnikRepository korisnikRepository;

    private boolean canContentCreatorAccess(ContainerRequestContext request) {
        if (request.getUriInfo().getPath().contains("paggination")) return false;
        if (request.getUriInfo().getPath().contains("kategorije")) return false;
        if (request.getUriInfo().getPath().contains("nova-vest")) return true;
        if (request.getUriInfo().getPath().contains("edit-vest")) return true;
        if (request.getUriInfo().getPath().contains("delete-vest")) return true;
        return false;
    }

    private boolean canAdminAccess(ContainerRequestContext request) {
        if (request.getUriInfo().getPath().contains("korisnik")) return true;
        return false;
    }

    @Override
    public void filter(ContainerRequestContext containerRequestContext) throws IOException {

        if (!(canContentCreatorAccess(containerRequestContext) || canAdminAccess(containerRequestContext))) {
            return;
        }


        try {
            String token = containerRequestContext.getHeaderString("Authorization");
            if (this.canContentCreatorAccess(containerRequestContext)) {
                if (!isLoggedIn(token)) {
                    containerRequestContext.abortWith(Response.status(Response.Status.UNAUTHORIZED).build());
                }
            } else if (canAdminAccess(containerRequestContext)) {
                if (!isAdmin(token)) {
                    containerRequestContext.abortWith(Response.status(Response.Status.UNAUTHORIZED).build());
                }
            }
        } catch (Exception exception) {
            exception.printStackTrace();
            containerRequestContext.abortWith(Response.status(Response.Status.UNAUTHORIZED).build());
        }

    }

    public boolean isLoggedIn(String token) {
        Algorithm algorithm = Algorithm.HMAC256("sifra123");
        JWTVerifier verifier = JWT.require(algorithm).build();
        DecodedJWT jwt = verifier.verify(token);
        String email = jwt.getSubject();
        Status status = Status.valueOf(jwt.getClaim("status").asString());
        if (status.equals(Status.NEAKTIVAN)) return false;
        Korisnik korisnik = this.korisnikRepository.getKorisnikByEmail(email);
        if (korisnik == null) return false;
        return true;
    }

    public boolean isAdmin(String token) {
        Algorithm algorithm = Algorithm.HMAC256("sifra123");
        JWTVerifier verifier = JWT.require(algorithm).build();
        DecodedJWT jwt = verifier.verify(token);
        String email = jwt.getSubject();
        String strStatus = jwt.getClaim("status").asString();
        TipKorisnika tip = TipKorisnika.valueOf(jwt.getClaim("tip").asString());
        Status status = Status.valueOf(strStatus);
        if (status.equals(Status.NEAKTIVAN)) return false;
        Korisnik korisnik = this.korisnikRepository.getKorisnikByEmail(email);
        if (korisnik == null) return false;
        if (tip.equals(TipKorisnika.CONTNENT_CREATOR)) return false;
        return true;
    }

}
