package com.ced.app.controller;

import java.util.List;
import java.util.ArrayList;
import java.util.Base64;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import com.ced.app.model.Annonce;
import com.ced.app.model.Categorie;
import com.ced.app.model.Favori;
import com.ced.app.model.PhotoVoiture;
import com.ced.app.model.RequeteImage;
import com.ced.app.model.Utilisateur;
import com.ced.app.service.AnnonceService;
import com.ced.app.service.FavoriService;
import org.springframework.web.client.RestTemplate;

import org.springframework.web.bind.annotation.RequestMethod;

@RestController
@CrossOrigin(origins = "*", methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE})
public class AnnonceController {
    @Autowired
    private AnnonceService annonceService;

    @Autowired
    private FavoriService favoriService;

    @GetMapping("/annonces")
	public List<Annonce> getAllAnnonces(){
        // for (Annonce annonce : annonceService.getAllAnnonces()) {
        //     System.out.println("annonce id : " + annonce.getId());
        // }
		return annonceService.getAllAnnonces();
	}

    @GetMapping("/annonces/except_user/")
	public List<Annonce> getAllAnnoncesExceptSelf(@RequestHeader("Authorization") String bearerToken) {
        // for (Annonce annonce : annonceService.getAllAnnonces()) {
        //     System.out.println("annonce id : " + annonce.getId());
        // }
        int id_user_actuel = 0;
        
        try {
            id_user_actuel = Integer.parseInt(Utilisateur.extractId(bearerToken.substring(7)));
            System.out.println("id user actuel " + id_user_actuel);
        } catch (Exception e) {
            e.printStackTrace();
        }
        List<Annonce> annonces_native = annonceService.getAllAnnonces();
        List<Annonce> filtered_annonces = new ArrayList<>();

        for (Annonce annonce : annonces_native) {
            if (annonce.getVoiture().getVendeur().getId() != id_user_actuel) {
                filtered_annonces.add(annonce);
            }
        }

        for (Annonce annonce : filtered_annonces) {
            List<PhotoVoiture> photoVoitures = annonceService.get_photos_of_annonce(annonce.getId(), 3);
            annonce.setPhotos_voiture(photoVoitures);
        }

        if (id_user_actuel == 0) {
            throw new IllegalStateException("id manquant");
        }

        return filtered_annonces;
	}

    @GetMapping("/annonces/favoris/{id_user_actuel}")
	public List<Annonce> getAllAnnoncesFavorites(@PathVariable int id_user_actuel){
        // for (Annonce annonce : annonceService.getAllAnnonces()) {
        //     System.out.println("annonce id : " + annonce.getId());
        // }
        List<Annonce> annonces_favorites = new ArrayList<>();
        List<Favori> favoris = favoriService.getAnnoncesFavorites(id_user_actuel);
        for (Favori favori : favoris) {
            annonces_favorites.add(favori.getAnnonce());
        }
        return annonces_favorites;
	}

    @PostMapping("/annonces/favoris/")
	public Favori setAnnonceToFavori(@RequestHeader("Authorization") String bearerToken)
    {
        String token = bearerToken.substring(7);
        int id_user_actuel = Integer.parseInt(Utilisateur.extractId(token));
        

        return new Favori();
	}

    @GetMapping("/annonces/etat/lessthan/10")
	public List<Annonce> getAnnoncesNonValidees(){
        // for (Annonce annonce : annonceService.getAllAnnonces()) {
        //     System.out.println("annonce id : " + annonce.getId());
        // }
		return annonceService.getAnnoncesNonValidees();
	}

    @GetMapping("/annonces/{id}")
	public ResponseEntity<Annonce> ficheannonce(@PathVariable int id){
        // Annonce annonce = annonceService.findById(id).orElseThrow(() -> new IllegalStateException("Categorie ID: "+id+" n'existe pas"));
        Optional<Annonce> optionalAnnonce = annonceService.findById(id);
        Annonce temp_annonce = null;
        if (optionalAnnonce.isPresent()) {
            temp_annonce = optionalAnnonce.get();
            List<PhotoVoiture> photosAnnonces = annonceService.get_photos_of_annonce(temp_annonce.getId(), 3);
            temp_annonce.setPhotos_voiture(photosAnnonces);
        }
		return Optional.ofNullable(temp_annonce).map(annonce -> new ResponseEntity<>(annonce, HttpStatus.OK)).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
	}

    @PostMapping("/annonces")
    public ResponseEntity<Annonce> saveAnnonce(@RequestBody Annonce annonce) {
        return new ResponseEntity<>(annonceService.save(annonce), HttpStatus.CREATED);
    }

    @PutMapping("/annonces/{id}")
    public ResponseEntity<Annonce> updateAnnonce(@PathVariable int id, @RequestBody Annonce updatedAnnonce) {
        // Check if the Annonce with the given id exists
        Optional<Annonce> existingAnnonceOptional = annonceService.findById(id);

        if (existingAnnonceOptional.isPresent()) {
            Annonce existingAnnonce = existingAnnonceOptional.get();

            updatedAnnonce.setId(existingAnnonce.getId());
            updatedAnnonce.setEtat(10);

            System.out.println("updated annonce get id : " + updatedAnnonce.getId());

            // Save the updated Annonce
            Annonce savedAnnonce = annonceService.update(updatedAnnonce);

            return new ResponseEntity<>(savedAnnonce, HttpStatus.OK);
        } else {
            // Annonce with the given id not found
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // @PostMapping("/testuploadimage")
    // public void uploadimage() {
    //     // Check if the Annonce with the given id exists
    //     RestTemplate restTemplate = new RestTemplate();
    //     HttpHeaders headers = new HttpHeaders();
    //     headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

    //     MultiValueMap<String, String> formData = new LinkedMultiValueMap<>();
    //     formData.add("image", "/9j/4AAQSkZJRgABAQAAAQABAAD/2wCEAAkGBw4LCggICw4ICAgJBwoHBwcHBw8ICQcKIBEWFiARHx8YHSggGBolGxMTITEhMSkrOi4uFx8zODMsNygtLisBCgoKDQ0NDg0NDisZFRkrKzc3LS03LTcrKzcrKystKysrKys3KysrKystLSsrKysrKysrKysrKysrKysrKysrK//CABEIANkA6wMBIgACEQEDEQH/xAAyAAABBQEBAQAAAAAAAAAAAAAAAQMEBQYCBwgBAAMBAQAAAAAAAAAAAAAAAAACAwEE/9oADAMBAAIQAxAAAADxTlUMBUBVToOQQ1UUAQUxZnWmnfG8b2Gpj16btHlAzBUAAAURQQAFBQUDdRQMAAAARQARUCRq8dsY9V5Gny+TpzGK9FpOjnySOtdPMiKmKdJ3ugiByAYqnQIAaAGAAAgCgAIqB1e0FulfQZaTuPtr6rT14uVx+/jXjgV0cO0anu3cCkbuJJmcXTcm52RqH8bDgPFUAAAAABUAADS7o7hd9fdiyuLubhyI4tHUyMq6Cv3do5aROiMMIWOEW0euoWrYlqZXzgRe3gADAAAAAABFDUv6H01H0TMim5O52tdlk6J7QaWk6K8uHWn535v7p53uZ+PpGcpEn17066mNVSEPMwO/iADAABFQFANAAT0rz/0CNtBXk7mvBtHLKiu2Ed11kvMuGP43XwxfPKvcZceledRaddN8Jnn4J28SgAAgKBooiYL23rcfi44u+e2XYgQ7R2egx8lW9NsKC/Sr7nCmrwQRY9C/XZj0CqtFrGa6Vd8zBO3hURQBDDq09H9TG+Ty3qdLPURV57yb+ouDMhS+u0tEy19MXKXl1AmI096LIM6zWgxJmMhz2rxgvVdurSYuqr5P54inRzoHQInomyNtri3Yx/B8PucML6jQaKj4ezQ6Gov6ys7KFLEaq7dhnpkiyMrKe440k4fdKT81b9MzjZ57oZbIIxeW2J8+gt4r7pl/d91yLBYk5Er6ub43zn3PwqsvZq17QcnXFtJrNZdyqx/UnNstrTO3VNYnVKThvE0smK40nqW0Z1cXB08Qxy4hzdT5n74l3l9OPPRcpCrrir5607TUmdnfCfefGbc+09P8z9WTY8afFGp+3ojZJSPHzeID3J02U2mshLTqH1sp3LLAgjbgjnbXbHzeqJaX0XO8e9enSPDk18LxkfFd6wiz2lR6edDdejtR49faMhQt2XM2rEtm22oem8sHXCmHPUgR11Omn31y658xKDzW6owz6ZjQdTG+Z7kdRrx2qYSJVbJorvTTeklvno2LFsIcdi8o1jTOo6sOcoyyyXIHDpYpRMVnrEz8jTwdVGmidIHoXuXzp9GYU8eVH5ujhXGx2+kVleb55CYcIarXTSbGjz2EaN2NGNV9hU6rLbMO2TIcCFSejezje5//xAAjEAACAgEEAwEBAQEAAAAAAAACAwEEAAUQERIGEyAwFBVA/9oACAEBAAECAf0Af5ZD/rpYtvZ2msX98cfPO/P0mVRAEq4ohyfy5+OefocpmBY9FuoY5x9cR++lsEoKZaFmoxe/q6SO/p/XTgrqgCBwLm5BxJBYK37F2Og0laZ7P10iFJmZxosizYIhFmFjs7TlStZZB/pOaTKmFndp3WWJqoXTtonIwVpVMfzdPrn60Zc4bjNoSj+FOnMrWqJL/hdgmsoMo++fnxVT8InFiEhW463lWqQY2vNeAVPt25/HnxpjC6Qk0VF8xkZGa0BKNrGzI5x+iE1xgwNMcRkZGRkTbQ5LYbIYUT+iwrJGWWv9Gpcp3F7Rtz2szKprNzjj86NcMkLeQDK9SKrByNixputMsf6q7zM4+oidNnagh5IyJv1RyrBxWgSiYwsvstvW+LNfGM/t+vD9GfGqV80tTIrJUM1WaH/kooLWORsc322K9O1OVF+qaH1ogxnlyow1lNIAWtEAcMWWRPMGybVeUxVGipEx6t4jTPEleEpryHmLFZYyFVsVgM740WmGQOTgV3VXUyHtWX6doHxfxzCbYfUs+aVV5xwkByCg4OwcEMTIkuIE4eh6IVWDjOfDtILDKWWzUzX05opjThBCMxME+eqnmxYgXs7nNmBWsdgHQq7TJbTYxeWZPPEJ46sicgoYbG5wnByC57EU7RvUxJFPZ+MwNtSqeHbljB2Ip2A1l2guxMz2QfO3juoGwmmwtlY2rW0USzqYsWQ9fX6+oT27yYwOcev17eM6ljB68cKEMWwxjYsKDXAznUl+r1yEBAjtBdtudP1FYmqRFfWMgxKZiZycLJnt3zsR8xnb2eyD7fHidtq2DGFuMnkbcTBwWDt14mfd7GMh4t9vx4baPGjxMYWRPYiDaZLDCRzuwjZLBc1/vXZm5vOeM4zGbTtO85G07Fk4W047J2fsGFn/xAA6EAABAwEFBgQEAwcFAAAAAAABAAIRIQMSMUFRBBAgImFxEzCBkVKhsfAyQGIUIzNCcsHxJENTstH/2gAIAQEAAz8B8wuMCqtMbpRGII/ORaDJHDHBA0c0GfSibaNv2Rg/8eaLSWOkEH81zNRnXBSEW1EoW7ZoLQYZSoJaaET+Zggq98lhoppkoN4TReJzN/H8OqLTddQj8h9x5MOhCAe2+CXDJC2bfFHj3KLTDhH03907R3sjoUevA7R3t5160u9kAIJlaDdQ5qH/AKSrp5mNezJybk0Ae8IYQFdnlblinTAp/SjIPfuvia1wVi6ZF0nTAKzcYDiEGHxXEFrfmUcsMvOm3aMJQZ1wUUUrH1UdFLLhgxmpoMEZ1Va4q7dOq/D81lGiikcx90Za91Ap5RgPmj9jzo2hiJy0UoYIdFWmCJp/lFwmDKIIcQVzHK7PqqCTUKXnQrM0AHumkh5VMeyOPnl1uyJhvyVdPostznPGhxRLlee0Vk/JCzaB1QFn7d5VC6swVNq5tUbrTNUQbmVEBdzMR2QnpqhAAVTu7rvu7ruu678TblpanGbqxVVUQqB3ZEv6UKh146rshgjd5arw33syjCvHqPZQb2qiER6Ka+R34osrRuFffcTWFee3HEKDd/l/uro6/RTvnqor6I3REoh+mqvFrYMCuiwjALHLzr7tBReG26316rDcJnRBzvuqy4RathXW3cwud1PVaCEcIp9UaZf3R8wuMCqu0wNE4dkQU4K0e17gAWt/Er0deKFe9VkcVFYlGRAhV4O/k3GeIcXD2Un1XIey5vuim6NSG9JT9leLK8C22swXNaaK64ZCUY4YQBxVJESrRhMi80Jlq39X0Unl8jIVJ91bhvieHaXBW9FBv8V8ZNgqBdyFN15sIzeUH7or1ox1qSQIBdmAmeIPDMtlU4YHujenunc2Jgei1zXNLU9lRVHjY6drthLY/dDJNLSyBccC27lC8LabVmAvkt6DddsnWvxUCMnupCIKFoDKMyMPqiAUWmSoGnBAKBcWlEhzwOUIWTLVpa1xc38RyUu7oz0V5sEBDpxubsVhFOQI5q7tIOrQqjqQrlgxo+AEr/sqeyBj0X3qhnVA4Ciruw38qcXE4pw/FNw5DAhbMW1Lw7Rc9KsHvCDQEclacEmBJOmZVvtAa9xFiw1rjCs2kFz3uI9ASmWdm2zGFmwBaKdqu/C1cze4R8NsZMCfOANRisoAw4YXNvqpEZIHRDPr2VmK0Qbungk3RU/NBjG7XbgOc4BzGuyQaIwDYpuug9VVzTUK7bMtsfEBXMP6gpY0jC4PdS71XTh5Spd6qgRHZcypukfdN0bsODx7X9qtBNkzAalQNA0Q0ZALqhug/dF4+yT/ADMz0C+X1XjWAbiWxKPT/wAQHem6N/Ko5lRSKIyCqDgk9d0b7zmt1KGzbHYtirmBx6omYRxlFu6ZV6wtWfoKq4fqK/jaU8k8cqvB+9s9LwU2GznLwBvlVChTeb8QIT7G0debAc83TlC/ja0jgx8vJFHTgioxXj7FZt/3LIXTqQq7pCrurOis9oZctQDo7RHY7U2lmZs3CvRU4613++6FRVx4xs9rcfSzeMdCg4XmEOBE0rRU4qRiFFRnxRw+nBTjtLC0a4OdczbMiF4lmy2FW2jA/wBeOijt9F6+RHkdt3beLbZBZZ2VPJuqeKaI4KFXdKjf34SzajZfy2jCIyB3DyKcMf43VUle+6u4bhw/66y7r+/m4eTXdUr/xAAmEAEAAgICAgICAwEBAQAAAAABABEhMUFxUWGBkRDwocHRsSAw/9oACAEBAAE/EF+5cv8Aaly9RIvQRYp6rqX1UX6i/UEGp0ESFUhxL8o92TWeGDuP/wAx+Jfs+ov7UXx/yWfpF/al/tfhfj4l6/yLr/Ip+kv66ixWL03LTZBhcAonayDQm4cFxTUSV3E4if8AioQ/kfUv2fUXjkjp/kXX+Rf2ouv8/C4uov7UuX9RZU3lq4dFUdpUCAauMDgPE9svD7Y2cENy4NP5/wCQIHMdP5GfrEv9qL+1L/al9/U/WJ+sS/2ov7Uvs+JZ7rqX39Re66j9A3MxG6LSiiZEKBTTOI1OoE5gloIZg4qJF17h9ojA1L2wifUSEB1xKfMXjnqXLi85JcXqX8S5cX4Jf1F1Llw9MbQbAT9KjolYYlyEYqMnB2xfoZqOfc+/qU+F8QTIx5lGjb6hNhPZK5gy8gZXxBwd0r2Rf2ovZnxL7+ovf1F7+pet4i9/Uvv6l9/UXmL3DiY0g5WzPBOCFQIjyyjGAlXA3iNoRKZiWDnIZVJkIWKM+ahVWOlI1EH0iOAlLJGQQPkhZvkEl4I6GNhtgWHh1x1xL7Jff1L7+ovf1F7+pfdRe5fcXuX3XUXv6l85+ovZHNVYXLxc3+kKgEvzNRirzKqYpIYJquWahFZLK4aQgC1XEHks8zgFRxOYuwswQARiBQybYbceNjB2NVUUjEr3F7+outxdbi9xdb+ovcXTnEvnMvnNRecy+/wZJjMPchHBjpbVhuFnl5l1tCEVQ7RimPGdPEMAgaySrhDxGaBaWYGEvDFYb4ZiKsGxjRXNMNwAGpG4yRzmUMZx6i8Zi9y+4uty+8S+/qfKLreIvOYvOZfOYvOYsdgktYKNLgp6uGp4rusEeM00YFEaGlga27WoIIq54l+MtYQFTRNU1jLolFxlxKrKtLZsGzhgLoMtYHiBwbi9kvULC9kX4RYXnScPCL39S+c1L7qLbyRII63UMaNKBbmLfmVNa61cF4tZQnQrphgFJzgVHQgham+I5AQlRgzpvBGLIpeLl/WMjFhRBUSgMo6qML3nsF5qL3L7nyg9xe5fcXuXzwl6cy+4vOYvOahFiVTFYfORWKNlZ1Lw0akiUrUaIuVAthYyW8sWtVHi8bmwQCZlwAgY4l7CtuN1KVjUcqyuxc1NFrHZghLlfUXuX2Re4vcvvEvuW+4uty+5fOYutxdefEcjQ2ZU4TnF2SXAxk8Rl1ArzENEo8QtDAbh44uHBDslFhp3Nwrcxy7KhVNzRIWFiq1LbwUjTVXFQLMXsl63F1sl9xfSVL9MXW5etxe4OtkvJxmUgbPEpNFBQdsqu5cmsOItHFEyDBRDxVarcbh9wNcw6hMI1Xi2ZgVB55m4fK8QAFNUxArVfcRbki9kvslxfcXW4uty+8RfTF1hi9krRuCwtRBiqkEcnGPM96i7y2jyfjM7mQxbYkUDNXVseBh6hFzBLcWFDwTOVUvLGCgYwC2EC83vNynNXzF7l+mX3F9MXvEW03UVS9mAIYswupPkPJL1KlYsYwDC1Jm3p3Gc5UgULve41Lu1xANPLMBxbVWe5jM0G2a9xa8xUS6byaRqtkhlvOhFV1swYB0LykN2FyzzmecRe4t8JXuX6cS+4vcvUoTK6v2lwXBPBMVqBeRic8y+BETezSNYiPuV6scypAOi5jAvmyDDMdwGDV+ZSGRzFpxWozEHQb8zOM1OQCXShtYnRwD/AAjseIq2rECiLXMSrWT5i+pfcXWMS/iPzUDNF3AEWWiRyTCwNDcYbHwv5gBCoe41cFnePSXgzmDM4Lg4VacTGBG8AA6Iwxp8Tc2eIaszh/2WLykBgpg7WrTMPij4ZGU2RhYBlU15mSG7j4v5i+ovr+YvFP3KwlNAgZY6s2aGCVAsWqMJtpV8fg4MjrF9wsVgf8RjUQ06NgZJMalFHEyxxE1zcqL8zc3bUGhwQqxqLRqq8QUU0jGkvmHABQWLgBBYMBLCjUbNpbLo4uXi6xEvFRABfAEf1S9kxdUYBm3xxMKU0Zni4xcosRaTxHSuiz7lc+d9+kEhVmMqXInDyTVE+Y1zL8hHBeJuTmUnhLgXlYdL3Cu4aDnFEDxzGTjHLGUlJoMMSLr/AGfCBRWZ/tjDDU2CxzhuVE5ISB53LpMW1cEmKFig4U5W4a8wl4RurXuLhawGIRYYSZXM8OJRXMtRluHnYbqBQ0kw207iglkefEGkuDzcxHhNJqHS9weovkx3Kw8cCYEGKsqFjbS1q4Fus0S+7uyDJjVs3i5P4h8BLCNi26CXq6r8Ma4uHLwXKKeYGtpBwaFj2FZZWlKFx5LySqqXhsmACwh3hgBl9x6HNEKrw+5XhWIKzhxBxzHqYs6QYHeg+JRHhs9wgXCkLRbncOmh3A4ml7JaExHyRmOLxDBq+SPMO9MWlqFmGLt8zcbTUaouyLkZUhm2adTjNRuHKPnnL3H4mgQvUu2T5xv19wWiIMlQWFaKsD22k3+SWHlgu2dRUrdTViquFvEsg5WNkXC96SIw5cw6Ie+KjDXEvmsr5izkuJ+fEariVtfCF6tt9SnaOoKViobY4Z7BAPGIWMUXDCN+iVzHjtTIpqA6yFScVUUatuXLUzxGxVZlArHo0XdQFLRzAeRFbDZfErUOjcsK48wMwka3KLTs5iFs1M0LW8xriLCsY5lSc9TmKF5gqjl8wMrolRW4GjKerj8Rv1UNPPpjoYc0gjQBIeGZVnVS45u5zQpUxvUxVgdVMC6fBLCt20EaC+0ORtrwxcTn5n3EVdwWIthmmFDyy6vMJ2HESUbieVIWd4YWDcYI+vwrqeTmMe6riw6dalVmiHvUGmEX7mtyEyOW5ieRguXEeX4trDVvMe3mD2Qyct3G6mrJV/QjwwpHNeEj2b6mbaR9jiCin1EfUz6lc4uXlqBA4eaKWWFj4RceIFE4RKCGATVFdtMXjco8QDmXIaLl5qoGkUxLUeDhjImI5e3ScxYs0F1ANovGY2PnxcO6/uAUtx7/APHD8Vq9Rz6h+DNyE8Tj3OcZpGbxvOUdTec+5x6htm0/p+P8yf/EACIRAQACAgIDAQEAAwAAAAAAAAEAEQIQIDEDIUESEyIwUf/aAAgBAgEBPwDllnXcPKLUu/8Ab5j1F+E8WfxhT1o1UOfkBJVawzTuGVy5ddz+hP6Hznl1HvWJcPUFixZ+uefo9zKoFwKly2LE+yufmSqIizErgx641rJoj7mGAkyA64dxvjbCZ5W1rHyV6mWQ7ZhiVMwJfBr5PkyX9QVN3BlQzSOSwN551P3BsmR/lAo0sslwb0zF0szF142ZnuDqiUSiUG8TWVnUV+6xaY0ytX7g3wCG88f+SnR1p6jBqGawZj7eL1Mu4bYk/JDWEdf/xAAiEQACAgIDAAIDAQAAAAAAAAAAAQIRECEDIDESMBMiMnH/2gAIAQMBAT8A7Q4/kiXC0r+7gY6o5eNXaHjf1cbpjd4nBPw+FMasoUGz8T7x9EnSPCbG7wlZCLK7xW9FqqGxux4ihOiT334lvY5DeKQ1RDD7RVsekORCTb3iiQmkRaZrolZVEIuhlJsSSKGNDuyLd0W8pHg3sX8kkJ0LYkNUWUUkS9xRFWOLKaE/1JXiHhBaJodItDeEJWiCo0ctXog9DVlIS2Raok09DGkukaI1iUbEmmWISKP9GS6WQmhOxrKE8MaGr6o4x9Flljx//9k=");
    //     try {
    //         String ws_response = restTemplate.postForObject("https://api.imgbb.com/1/upload?key=ca01a4a5f52ac1e27bb4d636d622ba24", formData, String.class);
    //         System.out.println("ws_response :" + ws_response);

    //     } catch (Exception e) {
    //         e.printStackTrace();
    //     }
    // }
}
