package fr.epita.assistants.item_producer.presentation.subscriber;

import fr.epita.assistants.common.aggregate.ItemAggregate;
import fr.epita.assistants.common.aggregate.ResetInventoryAggregate;
import fr.epita.assistants.common.aggregate.UpgradeItemProducerAggregate;
import fr.epita.assistants.item_producer.domain.service.StartService;
import io.smallrye.reactive.messaging.annotations.Broadcast;
import org.eclipse.microprofile.reactive.messaging.Incoming;
import jakarta.inject.Inject;


public class ProducerSubscriber {
    @Inject
    StartService service;



    @Broadcast
    @Incoming("reset-inventory-aggregate")
     public void process(ResetInventoryAggregate input) {
        service.erase(input);
    }

    @Broadcast
    @Incoming("collect-item-aggregate")
     public void processcolect(ItemAggregate input) {
        service.updateinv(input);
    }

    @Incoming("upgrade-collect-rate-aggregate")
    public void processnewmoney(UpgradeItemProducerAggregate in)
    {
        service.setq(ItemAggregate.ResourceType.MONEY, in.getNewMoney());
        service.uppc();

    }

        @Incoming("upgrade-movement-rate-aggregate")
    public void processnewmoney2(UpgradeItemProducerAggregate in)
    {
        service.setq(ItemAggregate.ResourceType.MONEY, in.getNewMoney());
        service.uppm();

    }

        @Incoming("upgrade-stamina-rate-aggregate")
    public void processnewmoney3(UpgradeItemProducerAggregate in)
    {
        service.setq(ItemAggregate.ResourceType.MONEY, in.getNewMoney());
        service.upps();

    }

}