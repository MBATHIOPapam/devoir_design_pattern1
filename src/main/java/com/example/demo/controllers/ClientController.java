package com.example.demo.controllers;

import com.example.demo.entities.Client;
import com.example.demo.entities.Commande;
import com.example.demo.services.ClientService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/clients")
public class ClientController {

    private final ClientService clientService;

    public ClientController(ClientService clientService) {
        this.clientService = clientService;
    }

    @PostMapping("/{id}/commandes")
    public ResponseEntity<?> createClientWithCommandes(@RequestBody @Valid Map<String, Object> payload) {
        Client client = new Client();
        client.setNomComplet((String) payload.get("nomComplet"));
        client.setTelephone((String) payload.get("telephone"));

        List<Commande> commandes = (List<Commande>) payload.get("commandes");
        if (commandes == null || commandes.isEmpty()) {
            return ResponseEntity.badRequest().body("Un client doit avoir au moins une commande.");
        }

        Client savedClient = clientService.createClientWithCommandes(client, commandes);
        return ResponseEntity.ok(savedClient);
    }

    @GetMapping("/{id}/commandes")
    public ResponseEntity<?> getClientWithCommandes(@PathVariable Long id) {
        Client client = clientService.getClientWithCommandes(id);
        return ResponseEntity.ok(client);
    }
}
