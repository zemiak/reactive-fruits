package org.acme;

import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.transaction.Transactional;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import io.quarkus.hibernate.reactive.panache.Panache;
import io.smallrye.mutiny.Uni;

@Path("/fruits")
@ApplicationScoped
public class Fruits {

    @GET
    public Uni<List<Fruit>> all() {
        return Fruit.listAll();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Uni<Response> create(Fruit fruit) {
        return Panache.withTransaction(
                () -> fruit.persist()
            ).replaceWith(
                () -> Response.status(Status.CREATED).entity(fruit).build()
        );
    }

    @GET
    @Path("count")
    public Uni<Long> count() {
        return Fruit.count();
    }
}
