package strategie;

import modele.Vehicule;

public interface StrategieTarif {

    double tarifJournalier(Vehicule vehicule);

    double penaliteParJour(Vehicule vehicule);
}