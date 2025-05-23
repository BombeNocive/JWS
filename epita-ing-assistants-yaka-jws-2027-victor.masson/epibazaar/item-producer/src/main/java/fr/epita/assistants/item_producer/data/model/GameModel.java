package fr.epita.assistants.item_producer.data.model;



import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "game")
public class GameModel {
    @Column(columnDefinition = "TEXT")
    String map;
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) Integer id;
}
