package rs.raf.project.resources;

import rs.raf.project.entities.Korisnik;
import rs.raf.project.services.KorisnikService;

import javax.inject.Inject;
import javax.print.attribute.standard.Media;
import javax.validation.Valid;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("/korisnik")
public class KorisnikResource {

    @Inject
    private KorisnikService korisnikService;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Korisnik> getAll(){
        return this.korisnikService.all();
    }

    @GET
    @Path("/{id}")
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


}
