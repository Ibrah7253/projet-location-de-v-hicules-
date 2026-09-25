import exception.DureeInvalideException;
import exception.VehiculeIndisponibleException;
import modele.Client;
import modele.Location;
import modele.Moto;
import modele.Voiture;
import service.AgenceLocation;
import strategie.TarifVoiture;

public class Main {

    public static void main(String[] args)
            throws DureeInvalideException, VehiculeIndisponibleException {

        // Création de l'agence
        AgenceLocation agence = new AgenceLocation();

        // Création d'un client
        Client client = new Client("Ibrahim", "PERMIS001");

        // Création des véhicules
        Voiture voiture = new Voiture("AB-123-CD", 5);
        Moto moto = new Moto("MO-456-CD", 125);

        // Enregistrement
        agence.enregistrerClient(client);
        agence.enregistrerVehicule(voiture);
        agence.enregistrerVehicule(moto);

        // Location de la voiture pour 5 jours
        Location location = agence.louerVehicule(
                client,
                voiture,
                5,
                new TarifVoiture()
        );

        System.out.println("===== LOCATION =====");
        System.out.println(location.resume());

        // Fermeture avec 2 jours de retard
        location.cloturer(2);

        System.out.println();
        System.out.println("===== APRÈS CLÔTURE =====");
        System.out.println(location.resume());

        // Liste des véhicules disponibles
        System.out.println();
        System.out.println("===== VÉHICULES DISPONIBLES =====");

        for (var vehicule : agence.listerVehiculesDisponibles()) {
            System.out.println(vehicule);
        }
    }
}