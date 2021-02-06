package org.acme;

import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.transaction.Transactional;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;

import io.smallrye.mutiny.Uni;

@Path("/fruits")
@Transactional
@ApplicationScoped
public class Fruits {

    @GET
    public Uni<List<Fruit>> all() {
        return Fruit.listAll();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public void create(Fruit fruit) {
        fruit.persist();
    }

    @GET
    @Path("count")
    public Uni<Long> count() {
        return Fruit.count();
    }
}
