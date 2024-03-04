package Notatki_zadania.Rejestracja_logowanie;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UzytkownikSerwis {

    @Autowired
    private UzytkownikRepozytorium uzytkownikRepository;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    public Uzytkownik zarejestrujNowegoUzytkownika(Uzytkownik uzytkownik) {
        uzytkownik.setHaslo(bCryptPasswordEncoder.encode(uzytkownik.getHaslo()));
        return uzytkownikRepository.save(uzytkownik);
    }

}