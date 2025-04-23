package com.trymad.hahaton.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.UuidGenerator;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "partners")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Partner {

    @Id
    @GeneratedValue
    @UuidGenerator
    private UUID id;

    @Column(unique = true)
    private String name;

    @Column(nullable = false, unique = true, length = 10)
    private String inn;

    @Column(nullable = false, unique = true)
    private String mail;

    @Column(nullable = false)
    private boolean active;

    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt;

    @Column(name = "updated_at", nullable = false)
    private LocalDateTime updatedAt;
}