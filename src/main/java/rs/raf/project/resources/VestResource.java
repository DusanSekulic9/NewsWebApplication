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



}
