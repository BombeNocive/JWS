package fr.epita.assistants.item_producer.domain.entity;

import java.sql.Timestamp;
import java.time.LocalDateTime;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PlayerEntity
{
    float collect_rate_multiplier;
    float move_speed_multiplier;
    int pos_x;
    int pos_y;
    float stamina_multiplier;
    LocalDateTime last_move;
    LocalDateTime last_collect;
    int id;


}
