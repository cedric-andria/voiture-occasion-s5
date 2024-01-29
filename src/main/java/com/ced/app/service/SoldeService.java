package com.ced.app.service;

import com.ced.app.model.Solde;
import com.ced.app.repository.SoldeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SoldeService {
    @Autowired
    private SoldeRepository soldeCommissionRepository;

    public void createSolde(double solde){
        soldeCommissionRepository.save(new Solde(solde));
    }

    public Solde getSolde(){
        List<Solde> list = soldeCommissionRepository.findAll();
        int index = list.size() - 1;
        return list.get(index);
    }

    public void addSolde(double solde) throws Exception {
        Solde soldeUser = this.getSolde();
        double difference_solde = soldeUser.getSolde() + solde;
        if(solde < 0){
            if(difference_solde < 0)
            {
                throw new Exception("Solde insuffisant");
            }

        }
        soldeUser.setSolde(soldeUser.getSolde() + solde);
    }
}
