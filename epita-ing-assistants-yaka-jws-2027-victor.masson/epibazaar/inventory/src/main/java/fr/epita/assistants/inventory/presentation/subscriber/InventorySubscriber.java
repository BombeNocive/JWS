package fr.epita.assistants.inventory.presentation.subscriber;

import fr.epita.assistants.common.aggregate.ItemAggregate;
import fr.epita.assistants.common.aggregate.ResetInventoryAggregate;
import fr.epita.assistants.common.aggregate.UpgradeItemProducerAggregate;
import fr.epita.assistants.common.command.CollectItemCommand;
import fr.epita.assistants.common.command.UpgradeCollectRateCommand;
import fr.epita.assistants.common.command.UpgradeMovementSpeedCommand;
import fr.epita.assistants.inventory.domain.service.ItemService;
import io.smallrye.reactive.messaging.annotations.Broadcast;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.PATCH;
import org.eclipse.microprofile.reactive.messaging.Incoming;
import org.eclipse.microprofile.reactive.messaging.Outgoing;
import fr.epita.assistants.common.command.ResetInventoryCommand;
import jakarta.inject.Inject;


public class InventorySubscriber {
    @Inject
    ItemService service;


    @Broadcast
    @Incoming("reset-inventory-command")
    @Outgoing("reset-inventory-aggregate")
     public ResetInventoryAggregate process(ResetInventoryCommand input) {
        return service.toaggregate();
    }

    @Broadcast
    @Incoming("collect-item-command")
    @Outgoing("collect-item-aggregate")
     public ItemAggregate process(CollectItemCommand input) {
        service.upd(input.getType(),input.getCollectRateMultiplier());
        return new ItemAggregate(input.getType(),input.getCollectRateMultiplier());
    }

    @Broadcast
    @Incoming("upgrade-collect-rate-command")
    @Outgoing("upgrade-collect-rate-aggregate")
    public UpgradeItemProducerAggregate ucp(UpgradeCollectRateCommand in)
    {
        service.upd(ItemAggregate.ResourceType.MONEY,in.getPrice()*-1);
         return new UpgradeItemProducerAggregate(service.getmoney());
    }
    @Broadcast
    @Incoming("upgrade-movement-speed-command")
    @Outgoing("upgrade-movement-speed-aggregate")
    public UpgradeItemProducerAggregate ump(UpgradeMovementSpeedCommand in)
    {
        service.upd(ItemAggregate.ResourceType.MONEY,in.getPrice()*-1);
        return new UpgradeItemProducerAggregate(service.getmoney());
    }
    @Broadcast
    @Incoming("upgrade-stamina-command")
    @Outgoing("upgrade-stamina-aggregate")
    public UpgradeItemProducerAggregate ups(UpgradeMovementSpeedCommand in)
    {
        service.upd(ItemAggregate.ResourceType.MONEY,in.getPrice()*-1);
        return new UpgradeItemProducerAggregate(service.getmoney());
    }



}
