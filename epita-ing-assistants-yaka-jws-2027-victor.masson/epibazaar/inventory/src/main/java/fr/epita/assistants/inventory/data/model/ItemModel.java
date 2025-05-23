package fr.epita.assistants.inventory.data.model;
import fr.epita.assistants.common.aggregate.ItemAggregate;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
 @Table(name = "item")
@AllArgsConstructor
@NoArgsConstructor
public class ItemModel {
    @Enumerated(EnumType.STRING)
    ItemAggregate.ResourceType type;
    Float quantity;
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) Integer id;
}
