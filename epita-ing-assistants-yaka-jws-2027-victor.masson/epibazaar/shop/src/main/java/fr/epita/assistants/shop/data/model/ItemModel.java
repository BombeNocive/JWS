package fr.epita.assistants.shop.data.model;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "item")
public class ItemModel {
    String type;
    Float quantity;
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) Integer id;
}
