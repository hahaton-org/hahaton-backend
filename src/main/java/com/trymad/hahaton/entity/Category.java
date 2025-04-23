package com.trymad.hahaton.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "categories")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Category {

    @Id
    private Integer id;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, unique = true)
    private CategoryType name;
}
