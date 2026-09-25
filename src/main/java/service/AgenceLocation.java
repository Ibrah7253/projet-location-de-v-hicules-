package service;

import exception.DureeInvalideException;
import exception.VehiculeIndisponibleException;
import modele.Client;
import modele.Location;
import modele.Vehicule;
import strategie.StrategieTarif;

import java.util.ArrayList;
import java.util.List;

public class AgenceLocation {

    private final List<Client> clients;
    private final List<Vehicule> vehicules;
    private final List<Location> locations;

    public AgenceLocation() {
        clients = new ArrayList<>();
        vehicules = new ArrayList<>();
        locations = new ArrayList<>();
    }

    public void enregistrerClient(Client client) {
        clients.add(client);
    }

    public void enregistrerVehicule(Vehicule vehicule) {
        vehicules.add(vehicule);
    }

    public Location louerVehicule(
            Client client,
            Vehicule vehicule,
            int duree,
            StrategieTarif strategieTarif
    ) throws DureeInvalideException, VehiculeIndisponibleException {

        Location location = new Location(
                client,
                vehicule,
                duree,
                strategieTarif
        );

        locations.add(location);

        return location;
    }

    public List<Vehicule> listerVehiculesDisponibles() {

        List<Vehicule> disponibles = new ArrayList<>();

        for (Vehicule vehicule : vehicules) {
            if (vehicule.estDisponible()) {
                disponibles.add(vehicule);
            }
        }

        return disponibles;
    }

    public List<Client> getClients() {
        return clients;
    }

    public List<Vehicule> getVehicules() {
        return vehicules;
    }

    public List<Location> getLocations() {
        return locations;
    }
}