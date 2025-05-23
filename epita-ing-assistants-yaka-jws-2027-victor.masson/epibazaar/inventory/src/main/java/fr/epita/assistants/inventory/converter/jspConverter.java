package fr.epita.assistants.inventory.converter;

import fr.epita.assistants.common.aggregate.ItemAggregate;
import fr.epita.assistants.inventory.data.model.ItemModel;
import jakarta.enterprise.context.ApplicationScoped;

import java.util.ArrayList;
import java.util.List;

import static fr.epita.assistants.common.aggregate.ItemAggregate.ResourceType.getResource;
@ApplicationScoped
public class jspConverter {


    public List<ItemAggregate> comv(List<ItemModel> itemlist) {

        List<ItemAggregate> list = new ArrayList<>();
        for (
                ItemModel item : itemlist) {
            list.add(new ItemAggregate(getResource(item.getType().getItemInfo().getValue()), item.getQuantity()));
        }
        return list;
    }

}
