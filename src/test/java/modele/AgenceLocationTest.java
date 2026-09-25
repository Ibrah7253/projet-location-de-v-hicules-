package service;

import exception.DureeInvalideException;
import exception.VehiculeIndisponibleException;
import modele.Client;
import modele.Location;
import modele.Vehicule;
import modele.Voiture;
import org.junit.jupiter.api.Test;
import strategie.TarifVoiture;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class AgenceLocationTest {

    @Test
    void doitListerUniquementLesVehiculesDisponibles()
            throws DureeInvalideException, VehiculeIndisponibleException {

        AgenceLocation agence = new AgenceLocation();

        Client client = new Client("Ibrahim", "PERMIS001");

        Voiture voiture1 = new Voiture("AB-123-CD", 5);
        Voiture voiture2 = new Voiture("AB-456-CD", 7);

        // Enregistrement des véhicules
        agence.enregistrerVehicule(voiture1);
        agence.enregistrerVehicule(voiture2);

        // Les deux véhicules sont disponibles au départ
        assertEquals(2, agence.listerVehiculesDisponibles().size());

        // On loue voiture1
        agence.louerVehicule(
                client,
                voiture1,
                5,
                new TarifVoiture()
        );

        // Seule voiture2 doit maintenant être disponible
        List<Vehicule> disponibles =
                agence.listerVehiculesDisponibles();

        assertEquals(1, disponibles.size());
        assertTrue(disponibles.contains(voiture2));
        assertFalse(disponibles.contains(voiture1));
    }
}