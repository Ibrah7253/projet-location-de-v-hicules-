package modele;

import exception.DureeInvalideException;
import exception.VehiculeIndisponibleException;
import org.junit.jupiter.api.Test;
import strategie.TarifVoiture;
import strategie.TarifMoto;

import static org.junit.jupiter.api.Assertions.*;

public class LocationTest {

    @Test
    void doitRefuserLocationVehiculeDejaLoue()
            throws DureeInvalideException, VehiculeIndisponibleException {

        Client client1 = new Client("Ibrahim", "PERMIS001");
        Client client2 = new Client("Koffi", "PERMIS002");

        Voiture voiture = new Voiture("AB-123-CD", 5);

        // Première location : doit fonctionner
        Location location1 =
                new Location(client1, voiture, 5, new TarifVoiture());

        assertNotNull(location1);

        // La voiture est maintenant indisponible
        assertFalse(voiture.estDisponible());

        // Deuxième location : doit être refusée
        assertThrows(
                VehiculeIndisponibleException.class,
                () -> new Location(
                        client2,
                        voiture,
                        3,
                        new TarifVoiture()
                )
        );
    }

    @Test
    void doitPermettreLocationVehiculeDisponible()
            throws DureeInvalideException, VehiculeIndisponibleException {

        Client client = new Client("Ibrahim", "PERMIS001");
        Voiture voiture = new Voiture("AB-456-CD", 5);

        Location location =
                new Location(client, voiture, 5, new TarifVoiture());

        assertNotNull(location);
        assertFalse(voiture.estDisponible());
    }

    @Test
    void doitRefuserDureeInvalide()
            throws VehiculeIndisponibleException {

        Client client = new Client("Ibrahim", "PERMIS001");

        Voiture voiture1 = new Voiture("AB-456-CD", 5);

        // Durée négative
        assertThrows(
                DureeInvalideException.class,
                () -> new Location(
                        client,
                        voiture1,
                        -2,
                        new TarifVoiture()
                )
        );

        // Durée nulle
        Voiture voiture2 = new Voiture("AB-789-CD", 5);

        assertThrows(
                DureeInvalideException.class,
                () -> new Location(
                        client,
                        voiture2,
                        0,
                        new TarifVoiture()
                )
        );
    }

    @Test
    void doitRendreVehiculeDisponibleApresCloture()
            throws DureeInvalideException, VehiculeIndisponibleException {

        Client client = new Client("Ibrahim", "PERMIS001");
        Voiture voiture = new Voiture("AB-999-CD", 5);

        Location location =
                new Location(client, voiture, 5, new TarifVoiture());

        // Pendant la location, le véhicule est indisponible
        assertFalse(voiture.estDisponible());

        // Clôture sans retard
        location.cloturer(0);

        // La location est clôturée
        assertTrue(location.estCloturee());

        // Le véhicule redevient disponible
        assertTrue(voiture.estDisponible());
    }

    @Test
    void doitRefuserRetardNegatif()
            throws DureeInvalideException, VehiculeIndisponibleException {

        Client client = new Client("Ibrahim", "PERMIS001");
        Voiture voiture = new Voiture("AB-111-CD", 5);

        Location location =
                new Location(client, voiture, 5, new TarifVoiture());

        assertThrows(
                IllegalArgumentException.class,
                () -> location.cloturer(-2)
        );

        // La location ne doit pas être clôturée
        assertFalse(location.estCloturee());

        // Le véhicule doit rester indisponible
        assertFalse(voiture.estDisponible());
    }

    @Test
    void doitCalculerMontantLocationVoiture()
            throws DureeInvalideException, VehiculeIndisponibleException {

        Client client = new Client("Ibrahim", "PERMIS001");
        Voiture voiture = new Voiture("AB-222-CD", 5);

        Location location =
                new Location(client, voiture, 5, new TarifVoiture());

        // 15 000 × 5 = 75 000 FCFA
        assertEquals(75000, location.calculerMontant());
    }
    @Test
    void doitCalculerPenaliteRetardVoiture()
            throws DureeInvalideException, VehiculeIndisponibleException {

        Client client = new Client("Ibrahim", "PERMIS001");
        Voiture voiture = new Voiture("AB-333-CD", 5);

        Location location =
                new Location(client, voiture, 5, new TarifVoiture());

        // 3 jours de retard
        location.cloturer(3);

        // 5 000 × 3 = 15 000 FCFA
        assertEquals(15000, location.calculerPenalite());
    }
    @Test
    void doitCalculerMontantLocationMoto()
            throws DureeInvalideException, VehiculeIndisponibleException {

        Client client = new Client("Ibrahim", "PERMIS001");
        Moto moto = new Moto("MO-123-CD", 125);

        Location location =
                new Location(client, moto, 4, new TarifMoto());

        // 10 000 × 4 = 40 000 FCFA
        assertEquals(40000, location.calculerMontant());
    }
    @Test
    void doitCalculerPenaliteRetardMoto()
            throws DureeInvalideException, VehiculeIndisponibleException {

        Client client = new Client("Ibrahim", "PERMIS001");
        Moto moto = new Moto("MO-456-CD", 125);

        Location location =
                new Location(client, moto, 4, new TarifMoto());

        // 2 jours de retard
        location.cloturer(2);

        // 3 000 × 2 = 6 000 FCFA
        assertEquals(6000, location.calculerPenalite());
    }
    @Test
    void doitProduireResumeLocation()
            throws DureeInvalideException, VehiculeIndisponibleException {

        Client client = new Client("Ibrahim", "PERMIS001");
        Voiture voiture = new Voiture("AB-777-CD", 5);

        Location location =
                new Location(client, voiture, 5, new TarifVoiture());

        location.cloturer(2);

        String resume = location.resume();

        assertTrue(resume.contains("Ibrahim"));
        assertTrue(resume.contains("AB-777-CD"));
        assertTrue(resume.contains("5 jour(s)"));
        assertTrue(resume.contains("75000"));
        assertTrue(resume.contains("2"));
        assertTrue(resume.contains("10000"));
    }
}