package fr.epita.assistants.item_producer.domain.service;
import fr.epita.assistants.common.aggregate.ItemAggregate;
import fr.epita.assistants.common.aggregate.ResetInventoryAggregate;
import fr.epita.assistants.item_producer.domain.entity.GameEntity;
import fr.epita.assistants.item_producer.domain.entity.ItemEntity;
import fr.epita.assistants.item_producer.domain.entity.PlayerEntity;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.*;
import kotlin.Pair;
import kotlin.Triple;
import org.eclipse.microprofile.config.inject.ConfigProperty;

import java.time.temporal.ChronoUnit;
import java.util.List;
import java.time.*;

@ApplicationScoped
public class StartService {

    @Inject
    GameService game;
    @Inject
    PlayerService player;
    @Inject
    ItemService item;

    @ConfigProperty(name = "JWS_TICK_DURATION")
    Long TD;
    @ConfigProperty(name = "JWS_DELAY_MOVEMENT")
    Long DM;

    @ConfigProperty(name = "JWS_DELAY_COLLECT")
    Long DC;

    @ConfigProperty(name = "JWS_UPGRADE_COLLECT_COST")
    Float upcollectcost;

    @ConfigProperty(name = "JWS_UPGRADE_MOVE_COST")
    Float upmovetcost;
     @ConfigProperty(name = "JWS_UPGRADE_STAMINA_COST")
    Float upsatminacost;

     @ConfigProperty(name = "JWS_UPGRADE_MULTIPLIER")
     Float upprate;


    public float ucc()
    {
        return upcollectcost;
    }
    public float umc()
    {
        return upmovetcost;
    }
    public float usc()
    {
        return upsatminacost;
    }

    public  boolean islaunch()
    {
        return game.isgame();
    }

    public boolean ismovedelay()
    {
        System.out.println(player.lastmove());
        return (player.lastmove() == null || player.lastmove().plus((TD * DM ), ChronoUnit.MILLIS ).isBefore(LocalDateTime.now()) );
    }

    public boolean iscollectdelay()
    {
         System.out.println(player.lastcollect());
        return (player.lastcollect() == null || player.lastcollect().plus((TD * DC),ChronoUnit.MILLIS ).isBefore(LocalDateTime.now()));
    }


    public ItemAggregate.ResourceType[][] start(String file)
    {

        game.resetrepo();
        player.resetrepo();
        player.nplayer();
        return game.add(file);
    }

    public void erase(ResetInventoryAggregate input)
    {
        item.delaggr(input);
    }



    public PlayerEntity getplayer()
    {
        return player.gplayer();
    }

    public List<ItemEntity> getresources()
    {
        return item.getallres();
    }

    public Pair<Integer,Integer> newcoord(String move,LocalDateTime temp)
    {
        ItemAggregate.ResourceType[][] map = game.getmap();
        PlayerEntity p = player.gplayer();
        int nx = p.getPos_x();
        int ny = p.getPos_y();
        switch (move)
        {
            case "UP":
                ny-=1;
                break;
            case "DOWN":
                ny+=1;
                break;
            case "LEFT":
                nx-=1;
                break;
            case "RIGHT":
                nx+=1;
                break;

            default:
                return null;
        }
        if (nx<0 || ny >=map.length || ny < 0 || nx>=map[ny].length || !map[ny][nx].getItemInfo().isWalkable())
        {
            return null;
        }
        player.change(nx,ny,temp,p.getLast_collect());
        return new Pair<>(nx,ny);

    }

    public Triple<ItemAggregate.ResourceType[][], ItemAggregate.ResourceType,Float> collect(LocalDateTime temp)
    {

        ItemAggregate.ResourceType[][] map = game.getmap();
        GameEntity g = new GameEntity(map);
        PlayerEntity p = player.gplayer();
        if (!map[p.getPos_y()][p.getPos_x()].getItemInfo().isCollectable())
        {
            return null;
        }
        ItemAggregate.ResourceType recolted = map[p.getPos_y()][p.getPos_x()];
        map[p.getPos_y()][p.getPos_x()] = ItemAggregate.ResourceType.GROUND;
        game.change(g);
        player.change(p.getPos_x(),p.getPos_y(),p.getLast_move(),temp);
        return new Triple<>(map,recolted,p.getCollect_rate_multiplier());
    }


    public void updateinv(ItemAggregate input)
    {
        item.upd(input.type,input.quantity);
    }

    public void erase()
    {
        game.resetrepo();
        player.resetrepo();
    }

    public boolean anymoney()
    {
        return item.getmoney()!=-1F;
    }

    public boolean enoughtmoneym()
    {
        return item.getmoney()>=upcollectcost;
    }

    public boolean enoughtmoneys()
    {
        return item.getmoney()>=upsatminacost;
    }

    public boolean enoughtmoneyc()
    {
        return item.getmoney()>=upcollectcost;
    }

    public void setq(ItemAggregate.ResourceType t ,Float q)
    {
        item.setq(t,q);
    }


    public void uppc()
    {
        player.uppc(upprate);
    }

        public void uppm()
    {
        player.uppm(upprate);
    }

        public void upps()
    {
        player.upps(upprate);
    }

}
