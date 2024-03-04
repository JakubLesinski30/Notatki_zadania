package Notatki_zadania.Rejestracja_logowanie;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

public class SzczegolyUzytkownika extends User {
    private String nazwaUzytkownika;

    public SzczegolyUzytkownika(Uzytkownik uzytkownik, Collection<? extends GrantedAuthority> authorities) {
        super(uzytkownik.getEmail(), uzytkownik.getHaslo(), authorities);
        this.nazwaUzytkownika = uzytkownik.getNazwaUzytkownika();
    }

    public String getNazwaUzytkownika() {
        return nazwaUzytkownika;
    }

}