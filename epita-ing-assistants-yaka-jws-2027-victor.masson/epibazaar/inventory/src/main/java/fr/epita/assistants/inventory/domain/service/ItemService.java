package fr.epita.assistants.inventory.domain.service;



import fr.epita.assistants.common.aggregate.ItemAggregate;

import fr.epita.assistants.common.aggregate.ResetInventoryAggregate;
import fr.epita.assistants.inventory.converter.jspConverter;
import fr.epita.assistants.inventory.data.model.ItemModel;
import io.quarkus.hibernate.orm.panache.PanacheQuery;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.*;
import fr.epita.assistants.inventory.data.repository.*;

import java.util.ArrayList;
import java.util.List;

import static fr.epita.assistants.common.aggregate.ItemAggregate.ResourceType.getResource;

@ApplicationScoped
public class ItemService {

    @Inject
     jspConverter conver;

    @Inject
     ItemRepository repo;
    public void resetrepo()
    {
        repo.resetDatabase();
    }

    public ResetInventoryAggregate toaggregate()
    {
        ResetInventoryAggregate res = new ResetInventoryAggregate(conver.comv(repo.allData()));
        resetrepo();
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

}


