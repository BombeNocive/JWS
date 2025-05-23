package fr.epita.assistants;

import io.smallrye.reactive.messaging.annotations.Broadcast;
import io.smallrye.reactive.messaging.annotations.Incomings;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.core.MediaType;
import org.eclipse.microprofile.reactive.messaging.Channel;
import org.eclipse.microprofile.reactive.messaging.Emitter;

import jakarta.inject.Inject;
import org.eclipse.microprofile.reactive.messaging.Outgoing;

import java.awt.*;

@Path("/send")
public class StringInfoResource {

    @Inject @Broadcast
    @Channel("string-info-command")
    Emitter<String> emitter;

    @POST @Broadcast
    public String send(String s) {
           // emitter.send(s);
        return s;
    }
}
