package com.trymad.hahaton.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.UuidGenerator;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "volunteers")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Volunteer {

    @Id
    @GeneratedValue
    @UuidGenerator
    private UUID id;

    @Column(nullable = false, unique = true, length = 12)
    private String inn;

    @Column(nullable = false, unique = true, length = 20)
    private String phone;

    @Column(nullable = false, unique = true)
    private String mail;

    @Column(name = "birthday_date", nullable = false)
    private LocalDate birthdayDate;

    @Column(nullable = false)
    private boolean active;

    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt;

    @Column(name = "updated_at", nullable = false)
    private LocalDateTime updatedAt;
}
