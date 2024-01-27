package com.ced.app.service;

import com.ced.app.model.Solde;
import com.ced.app.repository.SoldeCommissionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SoldeCommissionService {
    @Autowired
    private SoldeCommissionRepository soldeCommissionRepository;

    public void createSolde(double solde){
        soldeCommissionRepository.save(new Solde(solde));
    }

    public Solde getSolde(){
        List<Solde> list = soldeCommissionRepository.findAll();
        int index = list.size() - 1;
        return list.get(index);
    }

    public void addSolde(double solde){
        Solde soldeUser = this.getSolde();
        soldeUser.setSolde(soldeUser.getSolde() + solde);
    }
}
