package fr.epita.assistants.item_producer.data.repository;

import fr.epita.assistants.item_producer.data.model.PlayerModel;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.transaction.*;
import kotlin.Pair;

import java.time.LocalDateTime;

@ApplicationScoped
public class PlayerRepository implements PanacheRepository<PlayerModel> {


    @Transactional
    public void resetDatabase() {
        this.deleteAll();
    }
    @Transactional
    public void newplayer(Pair<Integer,Integer> pair) {
        PlayerModel res = new PlayerModel();
        res.setCollectRateMultiplier(1F);
        res.setMoveSpeedMultiplier(1F);
       res.setPosX(pair.component1());
         res.setPosY(pair.component2());
         res.setStaminaMultiplier(1F);
         res.setLastMove(null);
         res.setLastCollect(null);

         this.persist(res);
    }

    public PlayerModel getplayer()
    {
        return this.listAll().getFirst();
    }

    @Transactional
    public  void maj(PlayerModel input)
    {
        this.persist(input);
    }

    @Transactional
    public void change(Integer x , Integer y, LocalDateTime move , LocalDateTime collect)
     {
         PlayerModel m = getplayer();
         m.setPosX(x);
          m.setPosY(y);
          m.setLastMove(move);
          m.setLastCollect(collect);
          maj(m);

     }

     @Transactional
    public void uppgradec( Float nc)
     {
          PlayerModel m = getplayer();
          m.setCollectRateMultiplier(nc);
           maj(m);
     }
          @Transactional
    public void uppgrades(Float ns)
     {
PlayerModel m = getplayer();
          m.setStaminaMultiplier(ns);
           maj(m);
     }
          @Transactional
    public void uppgradem(Float nm)
     {
          PlayerModel m = getplayer();

          m.setMoveSpeedMultiplier(nm);

           maj(m);
     }

}