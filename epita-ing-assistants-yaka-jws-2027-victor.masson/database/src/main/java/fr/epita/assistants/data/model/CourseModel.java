package fr.epita.assistants.data.model;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import jakarta.persistence.*;
import jdk.jfr.Name;

import java.awt.*;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

@Entity @Table (name = "course_model")
public class CourseModel extends PanacheEntityBase {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) long id;
    String name;

    @OneToMany( targetEntity=StudentModel.class, mappedBy="course" )
    private List<StudentModel>  student = new ArrayList<StudentModel>();

    public @ElementCollection @CollectionTable(name = "course_model_tags",joinColumns=@JoinColumn(name="course_id")) List<String> tag;
}
