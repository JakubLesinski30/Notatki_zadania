package Notatki_zadania.Notatki_i_zadania;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.security.core.Authentication;

import Notatki_zadania.Rejestracja_logowanie.Uzytkownik;
import Notatki_zadania.Rejestracja_logowanie.UzytkownikRepozytorium;

@RestController
public class NotatkiZadaniaKontroler {

    @Autowired
    private NotatkiRepozytorium notatkiRepozytorium;

    @Autowired
    private ZadaniaRepozytorium zadaniaRepozytorium;

    @Autowired
    private UzytkownikRepozytorium uzytkownikRepozytorium;

    @GetMapping("/notatki")
    public Iterable<Notatki> getMojeNotatki(Authentication authentication) {
        String userEmail = authentication.getName();
        return notatkiRepozytorium.findByUzytkownikEmail(userEmail);
    }

    @PostMapping("/notatki")
    public Notatki dodajNotatke(@RequestBody Notatki notatka, Authentication authentication) {
        String userEmail = authentication.getName();
        Uzytkownik uzytkownik = uzytkownikRepozytorium.findByEmail(userEmail);
        notatka.setUzytkownik(uzytkownik);
        return notatkiRepozytorium.save(notatka);
    }

    @DeleteMapping("/notatki/{id}")
    public void usunNotatke(@PathVariable Long id, Authentication authentication) {
        String userEmail = authentication.getName();
        Uzytkownik uzytkownik = uzytkownikRepozytorium.findByEmail(userEmail);
        notatkiRepozytorium.deleteByIdAndUzytkownik(id, uzytkownik);
    }

    @GetMapping("/zadania")
    public Iterable<Zadania> getMojeZadania(Authentication authentication) {
        String userEmail = authentication.getName();
        return zadaniaRepozytorium.findByUzytkownikEmail(userEmail);
    }

    @PostMapping("/zadania")
    public Zadania dodajZadanie(@RequestBody Zadania zadanie, Authentication authentication) {
        String userEmail = authentication.getName();
        Uzytkownik uzytkownik = uzytkownikRepozytorium.findByEmail(userEmail);
        zadanie.setUzytkownik(uzytkownik);
        return zadaniaRepozytorium.save(zadanie);
    }

    @DeleteMapping("/zadania/{id}")
    public void usunZadanie(@PathVariable Long id, Authentication authentication) {
        String userEmail = authentication.getName();
        Uzytkownik uzytkownik = uzytkownikRepozytorium.findByEmail(userEmail);
        zadaniaRepozytorium.deleteByIdAndUzytkownik(id, uzytkownik);
    }
}
