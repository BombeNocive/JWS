package fr.epita.assistants.common.api.response;


import java.sql.Timestamp;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor


public class UpgradeCostResponse {

    float upgradeCollectCost;
    float upgradeMoveCost;
    float upgradeStaminaCost;
}
