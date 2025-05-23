
package fr.epita.assistants.item_producer.data.repository;

import fr.epita.assistants.common.aggregate.ItemAggregate;
import fr.epita.assistants.item_producer.data.model.*;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.*;
import jakarta.transaction.*;


@ApplicationScoped
public class GameRepository implements PanacheRepository<GameModel> {

    @Transactional
    public void resetDatabase() {
    this.deleteAll();
    }
     @Transactional
    public  void add(GameModel map) {
        this.persist(map);
    }

    public boolean started()
    {
        return !this.listAll().isEmpty();
    }
    @Transactional
  public GameModel getgame()
    {
        return this.listAll().getFirst();
    }
    @Transactional
    public void change(String map)
    {
        GameModel game = getgame();
        game.setMap(map);
        this.persist(game);
    }
}
