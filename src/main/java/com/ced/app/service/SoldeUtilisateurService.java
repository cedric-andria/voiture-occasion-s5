package com.ced.app.service;

import com.ced.app.model.SoldeUtilisateur;
import com.ced.app.model.Utilisateur;
import com.ced.app.repository.SoldeUtilisateurRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SoldeUtilisateurService {
    @Autowired
    private SoldeUtilisateurRepository soldeUtilisateurRepository;

    @Autowired
    private UtilisateurService utilisateurService;

    public void createSolde(String email, double solde){
        Utilisateur user = utilisateurService.getByEmail(email);
        SoldeUtilisateur soldeUser = new SoldeUtilisateur(user, solde);
        soldeUtilisateurRepository.save(soldeUser);
    }

    public void createSolde(Utilisateur user, double solde){
        SoldeUtilisateur soldeUser = new SoldeUtilisateur(user, solde);
        soldeUtilisateurRepository.save(soldeUser);
    }

    public void setSolde(String email, double solde) throws Exception{
        Utilisateur user = utilisateurService.getByEmail(email);
        Optional<SoldeUtilisateur> test = soldeUtilisateurRepository.findByUtilisateur(user);
        if(test.isPresent()){
            SoldeUtilisateur soldeUtilisateur = test.get();
            soldeUtilisateur.setSolde(soldeUtilisateur.getSolde() + solde);
            soldeUtilisateurRepository.save(soldeUtilisateur);
        } else {
            throw new Exception("Solde de l'utilisateur introuvable");
        }
    }

    public void addSolde(Utilisateur user, double solde) throws Exception{
        Optional<SoldeUtilisateur> test = soldeUtilisateurRepository.findByUtilisateur(user);
        if(test.isPresent()){
            SoldeUtilisateur soldeUtilisateur = test.get();
            double difference_solde = soldeUtilisateur.getSolde() + solde;
            if(solde < 0){
                if(difference_solde < 0)
                {
                    throw new Exception("Solde insuffisant");
                }

            }
            soldeUtilisateur.setSolde(soldeUtilisateur.getSolde() + solde);
            soldeUtilisateurRepository.save(soldeUtilisateur);
        } else {
            throw new Exception("Solde de l'utilisateur introuvable");
        }
    }

    public List<SoldeUtilisateur> getAllSolde(){
        return soldeUtilisateurRepository.findAll();
    }

    public SoldeUtilisateur getSoldeUser(String email) throws Exception{
        Utilisateur user = utilisateurService.getByEmail(email);
        Optional<SoldeUtilisateur> test = soldeUtilisateurRepository.findByUtilisateur(user);
        if(test.isPresent()){
            SoldeUtilisateur soldeUtilisateur = test.get();
            return soldeUtilisateur;
        } else {
            throw new Exception("Solde de l'utilisateur introuvable");
        }
    }
}
