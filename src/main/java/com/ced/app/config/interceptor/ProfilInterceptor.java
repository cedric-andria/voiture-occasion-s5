package com.ced.app.config.interceptor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerInterceptor;

import com.ced.app.model.Utilisateur;
import com.ced.app.service.UtilisateurService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class ProfilInterceptor implements HandlerInterceptor {

    @Autowired
    private UtilisateurService utilisateurService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String bearerToken = request.getHeader("Authorization");
        System.out.println("===============================middleware profil");
        if (Utilisateur.isBearerTokenValid(bearerToken)) {
            String token = bearerToken.substring(7);
            if (Utilisateur.isTokenExpired(token)){
                Utilisateur user = utilisateurService.getByEmail(Utilisateur.extractEmail(token));
                if (user != null){
                    System.out.println("Id profil de l'user : " + user.getProfil().getId());
                    if (user.getProfil().getId() != 2) {
                        throw new Exception("Methode non autorise pour l'utilisateur");
                    }
                    System.out.println("===============================middleware");
                    return true;
                } else {
                    throw new Exception("Utilisateur non reconnu");
                }
            } else {
                response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                response.getWriter().write("Token expired");
                return false;
            }
        } else {
            // Gérez les erreurs d'authentification ici (par exemple, renvoyez une réponse d'erreur)
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            response.getWriter().write("Token JWT invalide.");
            return false;
        }
    }

}

