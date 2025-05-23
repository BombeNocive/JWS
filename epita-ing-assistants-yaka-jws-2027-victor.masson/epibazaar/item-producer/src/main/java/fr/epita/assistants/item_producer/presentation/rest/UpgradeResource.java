package fr.epita.assistants.item_producer.presentation.rest;


import fr.epita.assistants.common.command.UpgradeCollectRateCommand;
import fr.epita.assistants.common.command.UpgradeMovementSpeedCommand;
import fr.epita.assistants.common.command.UpgradeStaminaCommand;
import fr.epita.assistants.item_producer.domain.service.StartService;
import org.eclipse.microprofile.reactive.messaging.Channel;
import org.eclipse.microprofile.reactive.messaging.Emitter;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.*;
import io.smallrye.reactive.messaging.annotations.Broadcast;
import jakarta.ws.rs.Path;
import jakarta.inject.Inject;
import fr.epita.assistants.common.utils.ErrorInfo;

@Path("/upgrade")
@Consumes(MediaType.APPLICATION_JSON)
public class UpgradeResource {
    @Inject @Broadcast
    @Channel("upgrade-collect-rate-command")
    Emitter<UpgradeCollectRateCommand> emitterc;

    @Inject @Broadcast
    @Channel("upgrade-movement-speed-command")
    Emitter<UpgradeMovementSpeedCommand> emitterm;

    @Inject @Broadcast
    @Channel("upgrade-stamina-command")
    Emitter<UpgradeStaminaCommand> emitters;



    @Inject
    StartService startser;

    @Path("/collec")
    @PATCH
    public Response upcollect()
    {
        if (!startser.islaunch())
        {
            return Response.status(Response.Status.BAD_REQUEST).entity(new ErrorInfo("Invalid money or the game is not running.")).build();
        }

        if(!startser.anymoney())
        {
            return Response.status(404).entity(new ErrorInfo("Nomoney")).build();
        }

        if (!startser.enoughtmoneyc())
        {
            return Response.status(Response.Status.BAD_REQUEST).entity(new ErrorInfo("Invalid money or the game is not running.")).build();
        }

        emitterc.send(new UpgradeCollectRateCommand(startser.ucc()));

        return Response.status(204).entity(new ErrorInfo("BIEN.")).build();
    }

    @Path("/move")
    @PATCH
    public Response upmove()
    {
        if (!startser.islaunch())
        {
            return Response.status(Response.Status.BAD_REQUEST).entity(new ErrorInfo("Invalid direction or the game is not running.")).build();
        }

        if(!startser.anymoney())
        {
            return Response.status(404).entity(new ErrorInfo("Nomoney")).build();
        }

        if (!startser.enoughtmoneym())
        {
            return Response.status(Response.Status.BAD_REQUEST).entity(new ErrorInfo("Invalid money or the game is not running.")).build();
        }

        emitterm.send(new UpgradeMovementSpeedCommand(startser.umc()));

        return Response.status(204).entity(new ErrorInfo("BIEN.")).build();
    }

    @Path("/stamina")
    @PATCH
    public Response upstamina()
    {
        if (!startser.islaunch())
        {
            return Response.status(Response.Status.BAD_REQUEST).entity(new ErrorInfo("Invalid direction or the game is not running.")).build();
        }
                if(!startser.anymoney())
        {
            return Response.status(404).entity(new ErrorInfo("Nomoney")).build();
        }

        if (!startser.enoughtmoneys())
        {
            return Response.status(Response.Status.BAD_REQUEST).entity(new ErrorInfo("Invalid money or the game is not running.")).build();
        }

        emitters.send(new UpgradeStaminaCommand(startser.usc()));

        return Response.status(204).entity(new ErrorInfo("BIEN.")).build();
    }

}
