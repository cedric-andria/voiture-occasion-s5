package com.ced.app.model;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Entity;
import java.sql.Date;
import com.ced.app.model.Profil;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import jakarta.persistence.Column;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.JoinColumn;
import com.ced.app.model.Profil;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.JoinTable;

import java.sql.Date;

@Entity
public class Utilisateur {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String nom;
    @Column(name="identifiant", unique = true)
    private String identifiant;
    private String mdp;
    @ManyToOne
    @JoinColumn(name="id_profil")
    private Profil profil;

    public Utilisateur(String nom, String identifiant, String mdp, Profil profil) {
        this.nom = nom;
        this.identifiant = identifiant;
        this.mdp = mdp;
        this.profil = profil;
    }

    public Utilisateur(int id, String nom, String identifiant, String mdp, Profil profil) {
        this.id = id;
        this.nom = nom;
        this.identifiant = identifiant;
        this.mdp = mdp;
        this.profil = profil;
    }

    public Utilisateur(String nom, String identifiant, String mdp) {
        this.setNom(nom);
        this.setIdentifiant(identifiant);
        this.setMdp(mdp);
    }

    public Utilisateur() {
    }

    public String generateToken(Utilisateur user) {
        // Create JWT token using the user information
        // Set expiration, claims, etc. as needed
        // Sign the token with the secret key
        //System.out.println("Secret key : "+this.secret);
        return Jwts.builder()
                .setSubject("data user")
                .claim("id", user.getId())
                .claim("identifiant",user.getIdentifiant())
                .claim("nom",user.getNom())
                .claim("idprofil", user.getProfil().getId())
                .setExpiration(new Date(System.currentTimeMillis() + 864000000)) // 10 days validity\
                .signWith(SignatureAlgorithm.HS512, "mytoken00000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000")
                .compact();
    }

    public static boolean isTokenValid(String token) throws Exception{
        boolean toReturn = false;
        try {
            Claims cl = Utilisateur.extractClaims(token);
            if(cl != null){
                if (cl.get("identifiant")!= null && cl.get("nom") != null && cl.get("exp") != null){
                    toReturn = true;
                }
            }
        } catch (Exception e) {
            throw e;
        }

        return toReturn;
    }

    public static boolean isBearerTokenValid(String token) throws Exception{
        boolean toReturn = false;
        System.out.println("Bearer token = "+token);
        try {
            if (token.startsWith("Bearer ")){
                String temp_token = token.substring(7);
                System.out.println(temp_token);
                toReturn = Utilisateur.isTokenValid(temp_token);
            } else {
                System.out.println("Bearer token not found");
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        return toReturn;
    }

    public static boolean isTokenExpired(String token) throws Exception{
        boolean toReturn = false;
        System.out.println("Bearer token = "+token);
        try {
            Claims cl = Utilisateur.extractClaims(token);
            if(cl != null){
                System.out.println("Exp : "+cl.getExpiration()+" Now :" +new java.util.Date(System.currentTimeMillis()));
                if (!cl.getExpiration().before(new java.util.Date(System.currentTimeMillis()))) {
                    toReturn = true;
                }
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        return toReturn;
    }

    public static String extractUsername(String token){
        return (String) Utilisateur.extractClaims(token).get("nom");
    }

    public static String extractEmail(String token){
        return (String) Utilisateur.extractClaims(token).get("identifiant");
    }

    public static String extractId(String token){
        return Utilisateur.extractClaims(token).get("id").toString();
    }

    public static String extractIdprofil(String token){
            return Utilisateur.extractClaims(token).get("idprofil").toString();
        }
    

    public static String extractToken(String bearerToken) throws Exception{
        if (bearerToken.startsWith("Bearer ")){
            return bearerToken.substring(7);
        } else {
            throw new Exception("Token invalide");
        }
    }

    public static Claims extractClaims(String token) {
        // Extraire les revendications (claims) du token
        return Jwts.parser().setSigningKey("mytoken00000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000").parseClaimsJws(token).getBody();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getIdentifiant() {
        return identifiant;
    }

    public void setIdentifiant(String identifiant) {
        this.identifiant = identifiant;
    }

    public String getMdp() {
        return mdp;
    }

    public void setMdp(String mdp) {
        this.mdp = mdp;
    }

    public Profil getProfil() {
        return profil;
    }

    public void setProfil(Profil profil) {
        this.profil = profil;
    }

}
