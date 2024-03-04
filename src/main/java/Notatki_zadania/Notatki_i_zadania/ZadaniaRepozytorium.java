package Notatki_zadania.Notatki_i_zadania;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import Notatki_zadania.Rejestracja_logowanie.Uzytkownik;

public interface ZadaniaRepozytorium extends CrudRepository<Zadania, Long> {

    List<Zadania> findByUzytkownikEmail(String email);
     void deleteByIdAndUzytkownik(Long id, Uzytkownik uzytkownik);
     
}