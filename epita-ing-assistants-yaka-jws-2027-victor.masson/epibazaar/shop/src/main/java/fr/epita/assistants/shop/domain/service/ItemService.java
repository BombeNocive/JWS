package fr.epita.assistants.shop.domain.service;

import fr.epita.assistants.common.aggregate.ResetInventoryAggregate;
import fr.epita.assistants.common.aggregate.ItemAggregate;
import fr.epita.assistants.common.aggregate.ResetInventoryAggregate;
import fr.epita.assistants.shop.converter.ItemConverter;
import fr.epita.assistants.shop.data.model.ItemModel;
import fr.epita.assistants.shop.data.repository.ItemRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

import java.util.ArrayList;
import java.util.List;

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

}


