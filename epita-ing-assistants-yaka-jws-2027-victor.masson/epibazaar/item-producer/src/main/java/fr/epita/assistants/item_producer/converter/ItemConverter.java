package fr.epita.assistants.item_producer.converter;

import fr.epita.assistants.common.aggregate.ItemAggregate;
import  fr.epita.assistants.item_producer.data.model.*;
import  fr.epita.assistants.item_producer.domain.entity.*;

import jakarta.enterprise.context.ApplicationScoped;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@ApplicationScoped

public class ItemConverter {

    public ItemEntity itoentity(ItemModel base)
    {
        return new ItemEntity(base.getType(), base.getQuantity(), base.getId());
    }

    public ItemModel itomodel(ItemEntity base)
    {
    return new ItemModel(base.getType(),base.getQuantity(), base.getId());

    }

    public ItemModel itomodel2(ItemAggregate base)
    {
       ItemModel res =  new ItemModel();
      res.setType(base.type);
        res.setQuantity(base.getQuantity());
        return res;
    }

}
