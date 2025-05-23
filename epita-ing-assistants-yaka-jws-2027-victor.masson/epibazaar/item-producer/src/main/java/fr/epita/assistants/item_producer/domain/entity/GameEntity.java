package fr.epita.assistants.item_producer.domain.entity;



import  fr.epita.assistants.common.aggregate.ItemAggregate.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class GameEntity {
     ResourceType[][] map;

}
