package fr.epita.assistants.item_producer.converter;

import  fr.epita.assistants.item_producer.data.model.*;
import  fr.epita.assistants.item_producer.domain.entity.*;

import jakarta.enterprise.context.ApplicationScoped;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@ApplicationScoped
public class PlayerConverter {
    public PlayerEntity ptoentity(PlayerModel base)
    {
        return new PlayerEntity(base.getCollectRateMultiplier(), base.getMoveSpeedMultiplier(), base.getPosX(), base.getPosY(), base.getStaminaMultiplier(),base.getLastMove(),base.getLastCollect(), base.getId());
    }

        public PlayerModel ptomodel(PlayerEntity base)
    {
        PlayerModel res =  new PlayerModel();
        res.setCollectRateMultiplier(base.getCollect_rate_multiplier());
        res.setMoveSpeedMultiplier(base.getMove_speed_multiplier());
       res.setPosX(base.getPos_x());
         res.setPosY(base.getPos_y());
         res.setStaminaMultiplier(base.getStamina_multiplier());
         res.setLastMove(base.getLast_move());
         res.setLastCollect(base.getLast_collect());
         res.setId(base.getId());
         return res;
    }
}
