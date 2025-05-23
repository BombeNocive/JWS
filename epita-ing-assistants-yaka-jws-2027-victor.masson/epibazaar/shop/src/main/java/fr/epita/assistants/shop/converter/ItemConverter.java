package fr.epita.assistants.shop.converter;

import fr.epita.assistants.common.aggregate.ItemAggregate;
import fr.epita.assistants.shop.data.model.ItemModel;
import fr.epita.assistants.shop.domain.entity.ItemEntity;
import jakarta.enterprise.context.ApplicationScoped;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@ApplicationScoped

public class ItemConverter {

    public ItemEntity itoentity(ItemModel base)
    {
        return new ItemEntity(base.getType(), base.getQuantity());
    }

    public ItemModel itomodel(ItemEntity base)
    {
       ItemModel res =  new ItemModel();
      res.setType(base.getType());
        res.setQuantity(base.getQuantity());
        return res;
    }

    public ItemModel itomodel2(ItemAggregate base)
    {
       ItemModel res =  new ItemModel();
      res.setType(base.type.getItemInfo().toString());
        res.setQuantity(base.getQuantity());
        return res;
    }

}
