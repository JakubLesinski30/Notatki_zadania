package Notatki_zadania.Rejestracja_logowanie;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import Notatki_zadania.Notatki_i_zadania.Notatki;
import Notatki_zadania.Notatki_i_zadania.Zadania;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "Uzytkownik")
@Getter
@Setter
public class Uzytkownik {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false, unique = true)
    private String nazwaUzytkownika;

    @Column(nullable = false)
    private String haslo;

    @OneToMany(mappedBy = "uzytkownik", cascade = CascadeType.ALL)
     @JsonIgnore
    private List<Notatki> notatki = new ArrayList<>();

    @OneToMany(mappedBy = "uzytkownik", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<Zadania> zadania = new ArrayList<>();
}