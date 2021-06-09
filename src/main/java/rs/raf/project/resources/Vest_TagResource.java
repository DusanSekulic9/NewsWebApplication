package rs.raf.project.resources;

import rs.raf.project.entities.Vest;
import rs.raf.project.services.Vest_TagService;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.awt.*;
import java.util.List;

@Path("/tag")
public class Vest_TagResource {

    @Inject
    private Vest_TagService vest_tagService;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Vest> getVestiByTagId(Integer tagId){
        return vest_tagService.getVestiByTagId(tagId);
    }
}
