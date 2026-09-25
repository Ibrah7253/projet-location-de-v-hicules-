package strategie;

import modele.Vehicule;

public class TarifVoiture implements StrategieTarif {

    @Override
    public double tarifJournalier(Vehicule vehicule) {
        return 15000;
    }

    @Override
    public double penaliteParJour(Vehicule vehicule) {
        return 5000;
    }
}