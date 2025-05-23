package fr.epita.assistants.inventory.data.repository;

import fr.epita.assistants.common.aggregate.ItemAggregate;
import fr.epita.assistants.inventory.data.model.*;
import io.quarkus.hibernate.orm.panache.PanacheQuery;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import io.quarkus.panache.common.Page;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.transaction.*;

import java.util.List;
import java.util.Optional;

@ApplicationScoped
public class ItemRepository implements PanacheRepository<ItemModel> {

    @Transactional
    public void resetDatabase() {
    this.deleteAll();
    }

    public List<ItemModel> allData()
    {
        return this.findAll().list();
    }

    @Transactional
    public void update(ItemAggregate.ResourceType type, Float q)
    {

        Optional<ItemModel> i =  this.find("type", type).stream().findFirst();
        if (!i.isEmpty())
        {
            ItemModel m = i.get();
            m.setQuantity(m.getQuantity()+q);
            this.persist(m);
        }
        else
        {
            ItemModel j = new ItemModel();
            j.setType(type);
            j.setQuantity(q);
            this.persist(j);

        }
    }

        public Float money()
    {
        Optional<ItemModel> i =  this.find("type", ItemAggregate.ResourceType.MONEY).stream().findFirst();
        if (!i.isEmpty())
        {
            return i.get().getQuantity();
        }
        return -1F;
    }



}
