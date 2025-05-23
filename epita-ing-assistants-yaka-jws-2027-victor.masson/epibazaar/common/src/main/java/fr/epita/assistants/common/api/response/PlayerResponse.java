package fr.epita.assistants.common.api.response;


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

public class  PlayerResponse{

    int posX;
    int posY;
    LocalDateTime lastMove;
    LocalDateTime lastCollect;
    float moveSpeedMultiplier;
    float staminaMultiplier;
    float collectRateMultiplier;


}
