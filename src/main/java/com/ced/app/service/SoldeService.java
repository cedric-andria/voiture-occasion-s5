package com.ced.app.service;

import com.ced.app.model.SoldeUtilisateur;
import com.ced.app.model.Utilisateur;
import com.ced.app.repository.SoldeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SoldeService {
    @Autowired
    private SoldeRepository soldeRepository;

    @Autowired
    private UtilisateurService utilisateurService;

    public void createSolde(String email, double solde){
        Utilisateur user = utilisateurService.getByEmail(email);
        SoldeUtilisateur soldeUser = new SoldeUtilisateur(user, solde);
        soldeRepository.save(soldeUser);
    }

    public void createSolde(Utilisateur user, double solde){
        SoldeUtilisateur soldeUser = new SoldeUtilisateur(user, solde);
        soldeRepository.save(soldeUser);
    }

    public void setSolde(String email, double solde) throws Exception{
        Utilisateur user = utilisateurService.getByEmail(email);
        Optional<SoldeUtilisateur> test = soldeRepository.findByUtilisateur(user);
        if(test.isPresent()){
            SoldeUtilisateur soldeUtilisateur = test.get();
            soldeUtilisateur.setSolde(soldeUtilisateur.getSolde() + solde);
            soldeRepository.save(soldeUtilisateur);
        } else {
            throw new Exception("Solde de l'utilisateur introuvable");
        }
    }

    public List<SoldeUtilisateur> getAllSolde(){
        return soldeRepository.findAll();
    }

    public SoldeUtilisateur getSoldeUser(String email) throws Exception{
        Utilisateur user = utilisateurService.getByEmail(email);
        Optional<SoldeUtilisateur> test = soldeRepository.findByUtilisateur(user);
        if(test.isPresent()){
            SoldeUtilisateur soldeUtilisateur = test.get();
            return soldeUtilisateur;
        } else {
            throw new Exception("Solde de l'utilisateur introuvable");
        }
    }
}
