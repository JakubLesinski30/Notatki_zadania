package Notatki_zadania.Notatki_i_zadania;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class NotatkiZadaniaKontroler {

    @Autowired
    private NotatkiRepozytorium notatkiRepozytorium;

    @Autowired
    private ZadaniaRepozytorium zadaniaRepozytorium;

    @GetMapping("/notatki")
    public Iterable<Notatki> getWszystkieNotatki() {
        return notatkiRepozytorium.findAll();
    }

    @PostMapping("/notatki")
    public Notatki dodajNotatke(@RequestBody Notatki notatka) {
        return notatkiRepozytorium.save(notatka);
    }

    @DeleteMapping("/notatki/{id}")
    public void usunNotatke(@PathVariable Long id) {
        notatkiRepozytorium.deleteById(id);
    }

    @GetMapping("/zadania")
    public Iterable<Zadania> getWszystkieZadania() {
        return zadaniaRepozytorium.findAll();
    }

    @PostMapping("/zadania")
    public Zadania dodajZadanie(@RequestBody Zadania zadanie) {
        return zadaniaRepozytorium.save(zadanie);
    }

    @DeleteMapping("/zadania/{id}")
    public void usunZadanie(@PathVariable Long id) {
        zadaniaRepozytorium.deleteById(id);
    }
}
