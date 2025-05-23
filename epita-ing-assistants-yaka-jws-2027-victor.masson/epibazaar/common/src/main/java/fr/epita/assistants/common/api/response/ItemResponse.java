package fr.epita.assistants.common.api.response;


import fr.epita.assistants.common.aggregate.ItemAggregate;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.testcontainers.shaded.org.checkerframework.framework.qual.IgnoreInWholeProgramInference;

import java.util.List;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class ItemResponse {
    int id;
    float quantity;
    ItemAggregate.ResourceType type;
}
