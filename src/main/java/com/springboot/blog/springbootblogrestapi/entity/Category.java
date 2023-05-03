package com.springboot.blog.springbootblogrestapi.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "categories")
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String description;
    // if child entities don't have the parent reference then hibernate will remove those entities from parent table.
    @OneToMany(mappedBy = "category",
            cascade = CascadeType.ALL,orphanRemoval = true)
    private List<Post> posts;
}
