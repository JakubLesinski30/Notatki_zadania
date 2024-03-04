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

    @Autowired
    private Szyfrowanie szyfrowanie;

    @GetMapping("/notatki")
    public Iterable<Notatki> getMojeNotatki(Authentication authentication) throws Exception {
        String userEmail = authentication.getName();
        Iterable<Notatki> notatki = notatkiRepozytorium.findByUzytkownikEmail(userEmail);
        for (Notatki notatka : notatki) {
            notatka.setTresc(szyfrowanie.deszyfruj(notatka.getTresc()));
        }
        return notatki;
    }

    @PostMapping("/notatki")
    public Notatki dodajNotatke(@RequestBody Notatki notatka, Authentication authentication) throws Exception {
        String userEmail = authentication.getName();
        Uzytkownik uzytkownik = uzytkownikRepozytorium.findByEmail(userEmail);
        notatka.setUzytkownik(uzytkownik);
        notatka.setTresc(szyfrowanie.szyfruj(notatka.getTresc()));
        return notatkiRepozytorium.save(notatka);
    }

    @DeleteMapping("/notatki/{id}")
    public void usunNotatke(@PathVariable Long id, Authentication authentication) {
        String userEmail = authentication.getName();
        Uzytkownik uzytkownik = uzytkownikRepozytorium.findByEmail(userEmail);
        notatkiRepozytorium.deleteByIdAndUzytkownik(id, uzytkownik);
    }

    @GetMapping("/zadania")
    public Iterable<Zadania> getMojeZadania(Authentication authentication) throws Exception {
        String userEmail = authentication.getName();
        Iterable<Zadania> zadania = zadaniaRepozytorium.findByUzytkownikEmail(userEmail);
        for (Zadania zadanie : zadania) {
            zadanie.setTresc(szyfrowanie.deszyfruj(zadanie.getTresc()));
        }
        return zadania;
    }

    @PostMapping("/zadania")
    public Zadania dodajZadanie(@RequestBody Zadania zadanie, Authentication authentication) throws Exception {
        String userEmail = authentication.getName();
        Uzytkownik uzytkownik = uzytkownikRepozytorium.findByEmail(userEmail);
        zadanie.setUzytkownik(uzytkownik);
        zadanie.setTresc(szyfrowanie.szyfruj(zadanie.getTresc()));
        return zadaniaRepozytorium.save(zadanie);
    }

    @DeleteMapping("/zadania/{id}")
    public void usunZadanie(@PathVariable Long id, Authentication authentication) {
        String userEmail = authentication.getName();
        Uzytkownik uzytkownik = uzytkownikRepozytorium.findByEmail(userEmail);
        zadaniaRepozytorium.deleteByIdAndUzytkownik(id, uzytkownik);
    }
}
