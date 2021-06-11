package rs.raf.project.resources;

import rs.raf.project.entities.Vest;
import rs.raf.project.services.Vest_TagService;

import javax.annotation.PreDestroy;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.awt.*;
import java.util.List;

@Path("/tag")
public class Vest_TagResource {

    @Inject
    private Vest_TagService vest_tagService;

    @GET
    @Path("/{page}")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Vest> getVestiByTagId(Integer tagId, @PathParam("page") Integer page){
        return vest_tagService.getVestiByTagId(tagId, page);
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public int getPagginationForTag(@PathParam("id") Integer id){
        return vest_tagService.getPagginationForTag(id);
    }
}
