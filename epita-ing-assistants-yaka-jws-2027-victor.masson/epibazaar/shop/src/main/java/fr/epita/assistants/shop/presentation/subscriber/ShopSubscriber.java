package fr.epita.assistants.shop.presentation.subscriber;


import fr.epita.assistants.common.aggregate.ResetInventoryAggregate;
import fr.epita.assistants.shop.domain.service.ItemService;
import io.smallrye.reactive.messaging.annotations.Broadcast;
import jakarta.ws.rs.Consumes;
import org.eclipse.microprofile.reactive.messaging.Incoming;
import org.eclipse.microprofile.reactive.messaging.Outgoing;
import fr.epita.assistants.common.command.ResetInventoryCommand;
import jakarta.inject.Inject;


public class ShopSubscriber {
    @Inject
    ItemService service;


    @Broadcast
    @Incoming("reset-inventory-aggregate")
     public void process(ResetInventoryAggregate input) {
        service.delaggr(input);
    }

}