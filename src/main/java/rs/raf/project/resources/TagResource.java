package rs.raf.project.resources;

import rs.raf.project.entities.Tag;
import rs.raf.project.services.TagService;

import javax.inject.Inject;
import javax.validation.Valid;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

@Path("/tagovi")
public class TagResource {

    @Inject
    private TagService tagService;


    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public Tag addTag(@Valid  Tag tag){
        return this.tagService.addTag(tag);
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Tag getTagById(@PathParam("id") Integer id){
        return this.tagService.getTagById(id);
    }
}
