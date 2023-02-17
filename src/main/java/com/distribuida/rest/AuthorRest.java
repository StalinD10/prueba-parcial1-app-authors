package com.distribuida.rest;
import com.distribuida.db.Authors;
import com.distribuida.servicios.AuthorRepository;
import org.eclipse.microprofile.openapi.annotations.OpenAPIDefinition;
import org.eclipse.microprofile.openapi.annotations.info.Contact;
import org.eclipse.microprofile.openapi.annotations.info.Info;
import org.eclipse.microprofile.openapi.annotations.info.License;
import org.eclipse.microprofile.openapi.annotations.tags.Tag;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;


@OpenAPIDefinition(
        tags = {
                @Tag(name = "widget", description = "Widget operations."),
                @Tag(name = "gasket", description = "Operations related to gaskets")
        },
        info = @Info(
                title = "APP Authors",
                version = "1.0.0",
                contact = @Contact(
                        name = "Stalin David Sandoval Sacoto",
                        url = "https://github.com/StalinD10",
                        email = "sdsandovals1@uce.edu.ec"),
                license = @License(
                        name = "Apache 2.0",
                        url = "https://www.apache.org/licenses/LICENSE-2.0.html"))
)
@ApplicationScoped
@Path("/authors")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class AuthorRest {

    @Inject
    AuthorRepository repository;
    @GET
    @Path("/{id}")
    public Authors findById(@PathParam("id") Long id) {
        return repository.findById(id);
    }


    @GET
    public List<Authors> findAll() {
        return repository
                .findAll()
                .list();
    }

    @POST
    public void insert(Authors obj) {
        repository.persist(obj);
    }

    @PUT
    @Path("/{id}")
    public void update(Authors obj, @PathParam("id") Long id) {

        var author = repository.findById(id);

        author.setFirstName(obj.getFirstName());
        author.setLastName(obj.getLastName());
    }

    @DELETE
    @Path("/{id}")
    public void delete( @PathParam("id") Long id ) {
        repository.deleteById(id);
    }
}
