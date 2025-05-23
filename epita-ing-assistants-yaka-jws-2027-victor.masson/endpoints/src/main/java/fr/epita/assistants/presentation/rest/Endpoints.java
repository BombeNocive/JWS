package fr.epita.assistants.presentation.rest;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import fr.epita.assistants.presentation.rest.request.ReverseRequest;
import fr.epita.assistants.presentation.rest.response.HelloResponse;
import fr.epita.assistants.presentation.rest.response.ReverseResponse;
import jakarta.json.Json;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import static io.vertx.core.json.jackson.DatabindCodec.mapper;


@Path("/")
@Produces(MediaType.APPLICATION_JSON)
public class Endpoints
{
    @GET
    @Path("hello/{name}")
    public HelloResponse Hello(@PathParam("name") final String name) throws JsonProcessingException {
            return new HelloResponse("hello " + name);
    }

    @POST
    @Path("reverse")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response Reverse(ReverseRequest body)
    {
        if (body == null || body.content == null || body.content.isEmpty())
        {
            return Response.status(Response.Status.BAD_REQUEST).entity("missing content").build();
        }
        String rr = new StringBuilder(body.content).reverse().toString();
        ReverseResponse res = new ReverseResponse(body.content,rr);
        return Response.ok(res).build();
    }

}
