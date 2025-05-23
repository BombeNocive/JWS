package fr.epita.assistants.item_producer.presentation.rest;


import fr.epita.assistants.common.api.request.MoveRequest;
import fr.epita.assistants.common.api.request.StartRequest;
import fr.epita.assistants.common.api.response.*;
import fr.epita.assistants.common.command.CollectItemCommand;
import fr.epita.assistants.common.command.ResetInventoryCommand;
import fr.epita.assistants.common.utils.ErrorInfo;
import fr.epita.assistants.item_producer.domain.entity.ItemEntity;
import fr.epita.assistants.item_producer.domain.entity.PlayerEntity;
import kotlin.Pair;
import kotlin.Triple;
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

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Path("/")
@Consumes(MediaType.APPLICATION_JSON)
public class GetterResource {

    @Inject @Broadcast
    @Channel("collect-item-command")
    Emitter<CollectItemCommand> emitter;

    @Inject
    StartService startser;

    @GET
    public Response getres()
    {
        if (!startser.islaunch())
        {
            return Response.status(Response.Status.BAD_REQUEST).entity(new ErrorInfo("The game is not running.")).build();
        }
        List<ItemEntity> a = startser.getresources();
        List<ItemResponse> c = new ArrayList<>();
        for (ItemEntity b : a)
        {
            c.add(new ItemResponse(b.getId(),b.getQuantity(),b.getType()));
        }
        return Response.ok(new ItemsResponse(c)).build();
    }

    @Path("player")
    @GET
    public Response getp()
    {
         if (!startser.islaunch())
        {
            return Response.status(Response.Status.BAD_REQUEST).entity(new ErrorInfo("The game is not running.")).build();
        }

        PlayerEntity a = startser.getplayer();

        return Response.ok(new PlayerResponse(a.getPos_x(),a.getPos_y(),a.getLast_move(),a.getLast_collect(),a.getMove_speed_multiplier(),a.getStamina_multiplier(),a.getCollect_rate_multiplier())).build();
    }
    @Path("upgrades")
    @GET
    public Response getupcost()
    {
        if (!startser.islaunch())
        {
            return Response.status(Response.Status.BAD_REQUEST).entity(new ErrorInfo("The game is not running.")).build();
        }
        return Response.ok(new UpgradeCostResponse(startser.ucc(), startser.umc(),startser.usc())).build();
    }

    @Path("move")
    @POST
    public Response movement(MoveRequest req)
    {
        LocalDateTime i = LocalDateTime.now();
        if (!startser.islaunch())
        {
            return Response.status(Response.Status.BAD_REQUEST).entity(new ErrorInfo("Invalid direction or the game is not running.")).build();
        }
        if (!startser.ismovedelay())
        {
            return Response.status(429).entity(new ErrorInfo("Player has recently moved and must wait before moving again.")).build();
        }
        Pair<Integer,Integer> res = startser.newcoord(req.getDirection(),i);
        if (res == null)
        {
            return Response.status(Response.Status.BAD_REQUEST).entity(new ErrorInfo("Invalid direction or the game is not running.")).build();
        }
        return Response.ok(new MoveResponse(res.component1(), res.component2())).build();

    }

    @Path("collect")
    @POST
    public Response collect()
    {
         LocalDateTime i = LocalDateTime.now();
        if (!startser.islaunch())
        {
            return Response.status(Response.Status.BAD_REQUEST).entity(new ErrorInfo("Invalid tile or the game is not running.")).build();
        }
        if (!startser.iscollectdelay())
        {
            return Response.status(429).entity(new ErrorInfo("Player has recently collected and must wait before collecting again.")).build();
        }
        Triple<ResourceType[][],ResourceType,Float> res =  startser.collect(i);
        if (res == null)
        {
            return Response.status(Response.Status.BAD_REQUEST).entity(new ErrorInfo("Invalid tile or the game is not running.")).build();
        }
        ResourceType[][] matt = res.component1();
        CollectItemCommand mess = new CollectItemCommand(res.component2(), res.component3());
        emitter.send(mess);

        return Response.ok(new StartResponse(matt)).build();

    }



}
