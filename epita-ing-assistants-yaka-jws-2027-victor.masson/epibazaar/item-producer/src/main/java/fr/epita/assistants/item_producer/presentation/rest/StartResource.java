package fr.epita.assistants.item_producer.presentation.rest;

import fr.epita.assistants.common.api.request.StartRequest;
import fr.epita.assistants.common.api.response.StartResponse;
import fr.epita.assistants.common.command.ResetInventoryCommand;
import fr.epita.assistants.common.utils.ErrorInfo;
import org.eclipse.microprofile.reactive.messaging.Channel;
import org.eclipse.microprofile.reactive.messaging.Emitter;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.*;
import jakarta.ws.rs.POST;
import io.smallrye.reactive.messaging.annotations.Broadcast;
import jakarta.ws.rs.Path;
import jakarta.inject.Inject;
import jakarta.ws.rs.core.Response;
import fr.epita.assistants.item_producer.domain.service.*;
import fr.epita.assistants.common.aggregate.ItemAggregate.*;

@Path("/start")
@Consumes(MediaType.APPLICATION_JSON)
public class StartResource {

    @Inject @Broadcast
    @Channel("reset-inventory-command")
    Emitter<ResetInventoryCommand> emitter;

    @Inject
    StartService startser;

    @POST@Broadcast
    public Response debut(StartRequest s)
    {
        if(s==null || s.getMapPath()==null)
        {
            startser.erase();
            return Response.status(Response.Status.BAD_REQUEST).entity(new ErrorInfo("Invalid"+ null +"provided.")).build();
        }

        ResourceType[][] matt = startser.start(s.getMapPath());

        if(matt==null)
        {
            startser.erase();
            return Response.status(Response.Status.BAD_REQUEST).entity(new ErrorInfo("Invalid"+s.getMapPath()+"provided.")).build();
        }
        StartResponse a = new StartResponse(matt);
        ResetInventoryCommand res = new ResetInventoryCommand();
        emitter.send(res);
        return Response.ok(a).build();

    }


}
