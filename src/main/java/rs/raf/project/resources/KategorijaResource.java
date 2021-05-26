package rs.raf.project.resources;

import rs.raf.project.entities.Kategorija;
import rs.raf.project.services.KategorijaService;

import javax.inject.Inject;
import javax.validation.Valid;
import javax.ws.rs.*;

import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("/kategorije")
public class KategorijaResource {

    @Inject
    private KategorijaService kategorijaService;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Kategorija> getAllCategories(){
        return this.kategorijaService.all();
    }

    @POST
    @Path("/nova-kategorija")
    @Produces(MediaType.APPLICATION_JSON)
    public Kategorija addKategorija(@Valid Kategorija kategorija){
        return this.kategorijaService.addKategorija(kategorija);
    }

    @DELETE
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public void deleteKategorija(@PathParam("id") Integer id){
        this.kategorijaService.deleteKategorija(id);
    }

    @PUT
    @Path("/izmeni-kategoriju")
    @Produces(MediaType.APPLICATION_JSON)
    public void updateKategorija(@Valid Kategorija kategorija){
        this.kategorijaService.updateKategorija(kategorija);
    }
}
