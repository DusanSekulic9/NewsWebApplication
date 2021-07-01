package rs.raf.project.resources;

import com.google.gson.Gson;
import rs.raf.project.LogIn;
import rs.raf.project.entities.Korisnik;
import rs.raf.project.services.KorisnikService;

import javax.inject.Inject;
import javax.print.attribute.standard.Media;
import javax.validation.Valid;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Path("/korisnik")
public class KorisnikResource {

    Gson gson = new Gson();

    @Inject
    private KorisnikService korisnikService;

    @GET
    @Path("all/{page}")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Korisnik> getAll(@PathParam("page") Integer page){
        return this.korisnikService.all(page);
    }

    @GET
    @Path("singleKorisnik/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Korisnik getKorisnik(@PathParam("id") Integer id){
        return this.korisnikService.getKorisnik(id);
    }

    @POST
    @Path("new-korisnik")
    @Produces(MediaType.APPLICATION_JSON)
    public Korisnik addKorisnik(@Valid Korisnik korisnik){
        return this.korisnikService.addKorisnik(korisnik);
    }

    @PUT
    @Path("edit-korisnik")
    @Produces(MediaType.APPLICATION_JSON)
    public Korisnik updateKorisnik(@Valid Korisnik korisnik){
        return this.korisnikService.updateKorisnik(korisnik);
    }

    @POST
    @Path("logIn")
    @Produces(MediaType.APPLICATION_JSON)
    public Response login(@Valid LogIn login){
        Map<String, Object> response = new HashMap<>();
        String jwt = this.korisnikService.login(login.getEmail(), login.getPassword());
        if (jwt == null) {
            response.put("message", "Unknown user, please try again");
            return Response.status(422, "Unprocessable Entity").entity(response).build();
        }
        response.put("korisnik", this.korisnikService.getK(login.getEmail()));
        response.put("jwt", jwt);
        //this.korisnikService.getK(login.getEmail());
        return Response.ok(response).header("Authorization", jwt).build();
    }

    @GET
    @Path("paggination")
    @Produces(MediaType.APPLICATION_JSON)
    public int getPagginationForCategory(){
        return this.korisnikService.getPagginationForAllUsers();
    }

    @GET
    @Path("korisnikName/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Korisnik getKorisnikName(@PathParam("id") Integer id){
        return this.korisnikService.getKorisnikName(id);
    }


}
