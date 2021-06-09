package rs.raf.project.resources;


import rs.raf.project.entities.Vest;
import rs.raf.project.services.VestService;

import javax.inject.Inject;
import javax.print.attribute.standard.Media;
import javax.validation.Valid;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.ext.ParamConverter;
import java.util.List;

@Path("/vesti")
public class VestResource {

    @Inject
    private VestService vestService;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Vest> getAll(){
        return this.vestService.all();
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Vest getVest(@PathParam("id") Integer id){
        return this.vestService.getVest(id);
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public Vest addVest(@Valid Vest vest){
        return this.vestService.addVest(vest);
    }

    @DELETE
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public void deleteVest(@PathParam("id") Integer id){
        this.vestService.deleteVest(id);
    }

    @PUT
    @Path("/edit-vest")
    @Produces(MediaType.APPLICATION_JSON)
    public Vest updateVest(@Valid Vest vest){
        return this.vestService.updateVest(vest);
    }

    @GET
    @Path("/home/{brStrane}")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Vest> recentVesti(@PathParam("brStrane") Integer brStrane){
        return vestService.recentVesti(brStrane);
    }

    @GET
    @Path("/category/{categoryId}/{brStrane}")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Vest> categoryVesti(@PathParam("categoryId") Integer kategorijaId, @PathParam("brStrane") Integer brStrane){
        return vestService.vestIzKategorije(kategorijaId, brStrane);
    }

    @GET
    @Path("/popular/{brStrane}")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Vest> popularVesti(@PathParam("brStrane") Integer brStrane){
        return vestService.popularVesti(brStrane);
    }



}
