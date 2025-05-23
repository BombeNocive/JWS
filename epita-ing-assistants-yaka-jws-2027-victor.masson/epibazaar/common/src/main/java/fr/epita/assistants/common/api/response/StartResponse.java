package fr.epita.assistants.common.api.response;


import static fr.epita.assistants.common.aggregate.ItemAggregate.ResourceType.*;
import static fr.epita.assistants.common.aggregate.ItemAggregate.*;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.format.ResolverStyle;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class StartResponse {
    @JsonProperty
    ResourceType[][] map;
}
