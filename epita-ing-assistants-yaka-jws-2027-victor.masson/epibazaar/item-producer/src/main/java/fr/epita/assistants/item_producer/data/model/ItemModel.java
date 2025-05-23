package fr.epita.assistants.item_producer.data.model;
import fr.epita.assistants.common.aggregate.ItemAggregate;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.testcontainers.shaded.org.checkerframework.framework.qual.IgnoreInWholeProgramInference;


@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "item")
public class ItemModel {
    @Enumerated(EnumType.STRING)
    ItemAggregate.ResourceType type;
    Float quantity;
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) Integer id;
}
