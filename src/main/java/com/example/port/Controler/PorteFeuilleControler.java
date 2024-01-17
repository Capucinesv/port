package com.example.port.Controler;

import com.example.port.Controler.repo.PorteFeuilleRepo;
import com.example.port.Model.PorteFeuille;

import java.sql.ResultSet;
import java.sql.SQLException;

public class PorteFeuilleControler {

    private PorteFeuilleRepo porteFeuilleRepo;

    public PorteFeuilleControler(PorteFeuilleRepo porteFeuilleRepo) {
        this.porteFeuilleRepo = porteFeuilleRepo;
    }

    public void achat(){

    }
    public void vente(){

    }
    public void voirSpecificAction(){

    }
    public PorteFeuille voirAllActions(Long id) throws SQLException {
        PorteFeuille porteFeuille = porteFeuilleRepo.chargerListePortefeuilles(id);
        return porteFeuille;
    }
}
