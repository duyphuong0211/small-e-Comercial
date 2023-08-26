package com.example.demo.domain;
import lombok.*;
import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name = "colors")
@NoArgsConstructor
@AllArgsConstructor
public class Color {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;
}
