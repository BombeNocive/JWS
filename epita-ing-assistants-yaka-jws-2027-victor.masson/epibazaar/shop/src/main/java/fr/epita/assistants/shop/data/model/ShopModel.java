package fr.epita.assistants.shop.data.model;



import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
@Entity
@Getter
@Setter
@Table(name = "shop")
public class ShopModel {

    Float priceMultiplier;
    Float upgradePrice;
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) Integer id;
}
