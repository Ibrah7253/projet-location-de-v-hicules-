package strategie;

import modele.Vehicule;

public class TarifMoto implements StrategieTarif {

    @Override
    public double tarifJournalier(Vehicule vehicule) {
        return 10000;
    }

    @Override
    public double penaliteParJour(Vehicule vehicule) {
        return 3000;
    }
}