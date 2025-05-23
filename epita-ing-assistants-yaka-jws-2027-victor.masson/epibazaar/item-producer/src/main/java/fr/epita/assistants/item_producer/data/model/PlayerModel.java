package fr.epita.assistants.item_producer.data.model;


import jakarta.persistence.*;

import java.sql.Timestamp;
import java.time.LocalDateTime;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "player")
public class PlayerModel {

    Float collectRateMultiplier;
    Float moveSpeedMultiplier;
    Integer posX;
    Integer posY;
    Float staminaMultiplier;
    LocalDateTime lastMove;
    LocalDateTime lastCollect;
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) Integer id;

}
