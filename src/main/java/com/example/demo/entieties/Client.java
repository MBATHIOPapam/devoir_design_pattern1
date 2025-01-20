package com.example.demo.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Client {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Le nom complet est obligatoire.")
    private String nomComplet;

    @Pattern(regexp = "^(77|78|76|75)[0-9]{7}$", message = "Le numéro de téléphone doit être unique et valide.")
    @Column(unique = true, nullable = false)
    private String telephone;

    @OneToMany(mappedBy = "client", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Commande> commandes = new ArrayList<>();

    // Getters et Setters
}
