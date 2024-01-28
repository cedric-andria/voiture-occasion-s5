
package com.ced.app.controller;

import org.springframework.web.bind.annotation.*;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Base64;
import java.util.Map;

@RestController
@RequestMapping("/images")
@CrossOrigin(origins = "*", methods = {RequestMethod.OPTIONS, RequestMethod.HEAD, RequestMethod.TRACE, RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE})
public class ImageController {


    @PostMapping
    public void uploadImage(@RequestBody Map<String, String> data){
        //System.out.println("Image en base64 : "+data.get("base64image"));
        try {
            // Décoder la chaîne base64 en tableau de bytes
            byte[] decodedBytes = Base64.getDecoder().decode(data.get("base64image"));

            // Sauvegarder le fichier dans le système de fichiers
            // Vous pouvez ajuster le chemin de stockage en fonction de votre structure de répertoire
            Path path = Paths.get("./images/avatar/" + data.get("fileName"));
            Files.write(path, decodedBytes);
            String dir = System.getProperty("user.dir");
            System.out.println("Dir = "+dir);
            //return ResponseEntity.ok("Image uploaded successfully.");
        } catch (Exception e) {
            e.printStackTrace();
            //return ResponseEntity.status(500).body("Internal Server Error");
        }
    }
}
