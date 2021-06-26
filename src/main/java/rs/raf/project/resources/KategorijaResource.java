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
    @Path("/{page}")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Kategorija> getAllCategories(@PathParam("page") Integer page){
        return this.kategorijaService.all(page);
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
    @Path("/izmeni-kategoriju/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public void updateKategorija(@Valid Kategorija kategorija, @PathParam("id") Integer id){
        this.kategorijaService.updateKategorija(kategorija, id);
    }

    @GET
    @Path("/paggination")
    @Produces(MediaType.APPLICATION_JSON)
    public int getPagginationForCategory(){
        return this.kategorijaService.getPagginationForCategory();
    }

}
