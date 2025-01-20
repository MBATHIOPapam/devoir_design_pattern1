package com.example.demo.services;

import com.example.demo.entities.Client;
import com.example.demo.entities.Commande;
import com.example.demo.repositories.ClientRepository;
import com.example.demo.repositories.CommandeRepository;
import jakarta.validation.Valid;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClientService {

    private final ClientRepository clientRepository;

    public ClientService(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    public Client createClientWithCommandes(@Valid Client client, List<Commande> commandes) {
        if (commandes.isEmpty()) {
            throw new IllegalArgumentException("Un client doit avoir au moins une commande.");
        }

        commandes.forEach(commande -> commande.setClient(client));
        client.setCommandes(commandes);

        return clientRepository.save(client);
    }

    public Client getClientWithCommandes(Long clientId) {
        return clientRepository.findById(clientId).orElseThrow(
                () -> new IllegalArgumentException("Client introuvable.")
        );
    }
}
