package com.ced.app.service;

import com.ced.app.model.Messages;
import com.ced.app.repository.MessagesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class MessagesService {
    @Autowired
    private MessagesRepository messageRepository;


    public void saveMessages(Messages t){
        messageRepository.save(t);
    }

    public List<Messages> getAllMessages(){
        return messageRepository.findAll();
    }

    public List<Messages> getMessagesBetweenUsers(String user1Id, String user2Id) {
        System.out.println("Getting message");
        List<Messages> messages1to2 = messageRepository.findBySenderIdAndReceiverId(user1Id, user2Id);
        System.out.println("Messages 1 size : "+messages1to2.size());
        List<Messages> messages2to1 = messageRepository.findBySenderIdAndReceiverId(user2Id, user1Id);
        System.out.println("Messages 2 size : "+messages2to1.size());

        // Combine les deux listes de messages
        messages1to2.addAll(messages2to1);

        // Triage des messages par rapport au temp
        List<Messages> toReturn = messages1to2.stream()
                .sorted(Comparator.comparing(Messages::getDate))
                .collect(Collectors.toList());

        return toReturn;
    }

    public Set<String> getDiscussion(String iduser){
        List<Messages> messagesList = this.getAllMessages();

        // Utilisateur pour lequel vous souhaitez obtenir les utilisateurs avec lesquels il a eu un échange
        String utilisateur = iduser;

        // Obtenir la liste des utilisateurs avec lesquels l'utilisateur a eu un échange
        Set<String> utilisateursAvecEchange = messagesList.stream()
                .filter(message -> message.getSenderId().equals(utilisateur) || message.getReceiverId().equals(utilisateur))
                .flatMap(message -> List.of(message.getSenderId(), message.getReceiverId()).stream())
                .filter(user -> !user.equals(utilisateur))
                .collect(Collectors.toSet());

        // Affichage des utilisateurs avec lesquels l'utilisateur a eu un échange
        //utilisateursAvecEchange.forEach(System.out::println);
        return utilisateursAvecEchange;
    }

    public Map<String, Messages> getDisc(String iduser){
        List<Messages> messagesList = this.getAllMessages();

        // Utilisateur pour lequel vous souhaitez obtenir les utilisateurs avec lesquels il a eu un échange
        String utilisateur = iduser;

        // Map pour stocker les informations sur les utilisateurs avec lesquels l'utilisateur a eu un échange
        Map<String, Messages> utilisateursAvecEchange = new HashMap<>();

        // Filtrer les messages pour ceux liés à l'utilisateur spécifié
        List<Messages> messagesUtilisateur = messagesList.stream()
                .filter(message -> message.getSenderId().equals(utilisateur) || message.getReceiverId().equals(utilisateur))
                .collect(Collectors.toList());

        // Parcourir la liste des messages pour extraire les informations nécessaires
        for (Messages message : messagesUtilisateur) {
            String autreUtilisateur = message.getSenderId().equals(utilisateur) ? message.getReceiverId() : message.getSenderId();
            Messages lastMessageInfo = utilisateursAvecEchange.get(autreUtilisateur);

            // Si l'utilisateur n'est pas déjà présent dans la map, l'ajouter
            if (lastMessageInfo == null) {
                utilisateursAvecEchange.put(autreUtilisateur, new Messages(message.getSenderId(),message.getReceiverId(),message.getContent(), message.getDate()));
            } else {
                // Comparer les dates pour mettre à jour le dernier message s'il est plus récent
                if (message.getDate().compareTo(lastMessageInfo.getDate()) > 0) {
                    lastMessageInfo.setContent(message.getContent());
                    lastMessageInfo.setDate(message.getDate());
                }
            }
        }

        return utilisateursAvecEchange;
    }


}
