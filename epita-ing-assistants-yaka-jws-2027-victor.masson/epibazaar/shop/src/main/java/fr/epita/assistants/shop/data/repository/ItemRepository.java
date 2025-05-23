

package fr.epita.assistants.shop.data.repository;

import fr.epita.assistants.shop.data.model.ItemModel;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;


import jakarta.transaction.Transactional;

import java.util.List;

@ApplicationScoped
public class ItemRepository implements PanacheRepository<ItemModel> {

    @Transactional
    public void resetDatabase() {
    this.deleteAll();
    }

    @Transactional
    public void deletematch(List<ItemModel> input)
    {
        for (ItemModel mod : input)
        {
            this.delete(mod);
        }

    }

}
