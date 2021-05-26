package rs.raf.project.resources;

import rs.raf.project.entities.Komentar;
import rs.raf.project.services.KomentarService;

import javax.inject.Inject;
import javax.validation.Valid;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("/komentari")
public class KomentarResource {

    @Inject
    private KomentarService komentarService;

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Komentar> getAllCommentsById(@PathParam("id") Integer id){
        return this.komentarService.allCommentsForParentId(id);
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public Komentar addKomentar(@Valid Komentar komentar){
        return this.komentarService.addKomentar(komentar);
    }

}
