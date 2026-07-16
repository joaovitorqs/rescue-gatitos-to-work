package com.github.joaovitorqs.rescue_gatitos_to_work.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "users")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nickName;
    private String email;
    private String password;

    private Integer qtdGatitos;
    private Double dinheiro;
    private Integer levelClick;
    private Integer levelAutoClick;
}
