package com.trymad.hahaton.config.entity;

import jakarta.persistence.*;
import lombok.*;
import java.util.Set;

@Entity
@Table(name = "roles")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Role {

    @Id
    private Integer id;

    @Column(nullable = false, unique = true)
	@Enumerated(EnumType.STRING)
    private MyRole name;

}

