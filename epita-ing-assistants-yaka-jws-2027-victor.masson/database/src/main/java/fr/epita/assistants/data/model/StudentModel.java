package fr.epita.assistants.data.model;

import io.quarkus.hibernate.orm.panache.PanacheEntity;
import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import jakarta.persistence.*;

import java.math.BigInteger;
@Entity @Table (name = "student_model")
public class StudentModel extends PanacheEntityBase {
        @Id @GeneratedValue(strategy = GenerationType.IDENTITY) public long id;
       public String name;
       @ManyToOne
       private CourseModel course;


}
