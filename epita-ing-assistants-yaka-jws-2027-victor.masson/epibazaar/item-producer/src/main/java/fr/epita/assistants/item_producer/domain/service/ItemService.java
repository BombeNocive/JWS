package fr.epita.assistants.item_producer.domain.service;



import fr.epita.assistants.common.aggregate.ItemAggregate;
import fr.epita.assistants.common.aggregate.ResetInventoryAggregate;
import fr.epita.assistants.item_producer.converter.ItemConverter;
import fr.epita.assistants.item_producer.data.model.ItemModel;
import fr.epita.assistants.item_producer.domain.entity.ItemEntity;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.*;
import fr.epita.assistants.item_producer.data.repository.*;

import java.util.ArrayList;
import java.util.List;

import static fr.epita.assistants.common.aggregate.ItemAggregate.ResourceType.getResource;

@ApplicationScoped
public class ItemService {

    @Inject
    public ItemRepository repo;

    @Inject
    ItemConverter conver;
    public void resetrepo()
    {
        repo.resetDatabase();
    }

    public void delaggr(ResetInventoryAggregate input) {

        List<ItemModel>  res = new ArrayList<>();
        for (ItemAggregate agg : input.getItems())
        {
            res.add(conver.itomodel2(agg));
        }

       repo.deletematch(res);
    }

    public List<ItemEntity> getallres()
    {
        List<ItemEntity> res = new ArrayList<>();
        for (ItemModel mod : repo.allData())
        {
            res.add(conver.itoentity(mod));
        }
        return res;
    }

    public void upd(ItemAggregate.ResourceType type, Float q)
    {
        repo.update(type,q);
    }

    public Float getmoney()
    {
        return repo.money();
    }

    public void setq(ItemAggregate.ResourceType t ,Float q)
    {
        repo.setq(t,q);
    }


}


