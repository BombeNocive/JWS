package fr.epita.assistants.item_producer.domain.service;

import fr.epita.assistants.item_producer.converter.PlayerConverter;
import fr.epita.assistants.item_producer.data.model.PlayerModel;
import fr.epita.assistants.item_producer.domain.entity.PlayerEntity;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.*;
import fr.epita.assistants.item_producer.data.repository.*;
import kotlin.Pair;

import java.sql.Timestamp;
import java.time.LocalDateTime;

@ApplicationScoped
public class PlayerService
{
    @Inject
    public PlayerRepository repo;


    @Inject
    PlayerConverter conver;

    public void resetrepo()
    {
        repo.resetDatabase();
    }

    public void nplayer()
    {
        repo.newplayer(new Pair<>(0,0));
    }

     public PlayerEntity gplayer()
    {
        return  conver.ptoentity(repo.getplayer());
    }

    public LocalDateTime lastmove()
    {
        PlayerEntity a = gplayer();
        return a.getLast_move();
    }

    public LocalDateTime lastcollect()
    {
        PlayerEntity a = gplayer();
        return a.getLast_collect();
    }

    public void addplayer(PlayerEntity input)
    {
        repo.newplayer(new Pair<>(input.getPos_x(), input.getPos_y()));
    }

    public void update(PlayerEntity input)
    {
        repo.maj(conver.ptomodel(input));
    }


     public void change(Integer x ,Integer y,LocalDateTime move ,LocalDateTime collect)
     {
        repo.change(x,y,move,collect);
     }

     public void uppc(Float mul)
    {
        repo.uppgradec(mul);
    }

         public void uppm(Float mul)
    {
        repo.uppgradem(mul);
    }

     public void upps(Float mul)
    {
        repo.uppgrades(mul);
    }

}
