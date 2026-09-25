package modele;

import exception.DureeInvalideException;
import exception.VehiculeIndisponibleException;
import strategie.StrategieTarif;

public class Location {

    private final Client client;
    private final Vehicule vehicule;
    private final int dureePrevue;
    private final StrategieTarif strategieTarif;

    private int joursRetard;
    private boolean cloturee;

    public Location(
            Client client,
            Vehicule vehicule,
            int dureePrevue,
            StrategieTarif strategieTarif
    ) throws VehiculeIndisponibleException, DureeInvalideException {

        if (dureePrevue <= 0) {
            throw new DureeInvalideException(
                    "La durée de location doit être supérieure à 0."
            );
        }

        if (!vehicule.estDisponible()) {
            throw new VehiculeIndisponibleException(
                    "Le véhicule " + vehicule.getImmatriculation()
                            + " est déjà loué."
            );
        }

        this.client = client;
        this.vehicule = vehicule;
        this.dureePrevue = dureePrevue;
        this.strategieTarif = strategieTarif;
        this.joursRetard = 0;
        this.cloturee = false;

        vehicule.louer();
    }

    public Client getClient() {
        return client;
    }

    public Vehicule getVehicule() {
        return vehicule;
    }

    public int getDureePrevue() {
        return dureePrevue;
    }

    public int getJoursRetard() {
        return joursRetard;
    }

    public boolean estCloturee() {
        return cloturee;
    }

    public double calculerMontant() {
        return strategieTarif.tarifJournalier(vehicule) * dureePrevue;
    }

    public double calculerPenalite() {
        return strategieTarif.penaliteParJour(vehicule) * joursRetard;
    }

    public void cloturer(int joursRetard) {

        if (joursRetard < 0) {
            throw new IllegalArgumentException(
                    "Le nombre de jours de retard ne peut pas être négatif."
            );
        }

        this.joursRetard = joursRetard;
        this.cloturee = true;

        vehicule.rendreDisponible();
    }
    public String resume() {
        return "Résumé de la location\n"
                + "Client : " + client.getNom() + "\n"
                + "Véhicule : " + vehicule.getImmatriculation() + "\n"
                + "Durée prévue : " + dureePrevue + " jour(s)\n"
                + "Montant : " + calculerMontant() + " FCFA\n"
                + "Jours de retard : " + joursRetard + "\n"
                + "Pénalité : " + calculerPenalite() + " FCFA";
    }
}