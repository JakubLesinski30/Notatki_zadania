package Notatki_zadania.Rejestracja_logowanie;

import java.security.Principal;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class UzytkownikKontroler {

    private final UzytkownikSerwis uzytkownikService;

    public UzytkownikKontroler(UzytkownikSerwis uzytkownikService) {
        this.uzytkownikService = uzytkownikService;
    }

    @GetMapping("/")
    public String index(Model model, Principal principal) {
        if (principal instanceof Authentication auth) {
            SzczegolyUzytkownika userDetails = (SzczegolyUzytkownika) auth.getPrincipal();
            model.addAttribute("username", userDetails.getNazwaUzytkownika());
        }
        return "index";
    }

    @GetMapping("/rejestracja")
    public String rejestracja(Model model, Principal principal) {
        if (principal != null) {

            return "redirect:/";

        } else {
            model.addAttribute("uzytkownik", new Uzytkownik());
            return "rejestracja";
        }
    }

    @PostMapping("/rejestracja")
    public String zarejestrujUzytkownika(@ModelAttribute Uzytkownik uzytkownik, RedirectAttributes redirectAttributes,
            Model model) {

        if (uzytkownik.getEmail() == null || uzytkownik.getEmail().isEmpty() || !uzytkownik.getEmail().contains("@")
                || uzytkownik.getEmail().length() < 4) {

            model.addAttribute("komunikat_email",
                    "Wprowadzony email musi zawierac minimum 4 znaków i musi zawierać @!");

            return "rejestracja";
        }

        if (uzytkownik.getNazwaUzytkownika() == null || uzytkownik.getNazwaUzytkownika().isEmpty()
                || !uzytkownik.getNazwaUzytkownika().matches("^[a-zA-Z0-9]{4,}$")
                || "admin".equalsIgnoreCase(uzytkownik.getNazwaUzytkownika())) {

            model.addAttribute("komunikat_username",
                    "Nazwa uzytkownika musi zawierać mimimum 4 znaków i nie moze zawierać znaków specjalnych!");

            return "rejestracja";

        }

        if (uzytkownik.getHaslo() == null || uzytkownik.getHaslo().isEmpty() || uzytkownik.getHaslo().length() < 6
                || !uzytkownik.getHaslo().matches("^(?=.*[a-z])(?=.*[A-Z])(?=.*[0-9!@#$%^&*]).{6,}$")) {

            model.addAttribute("komunikat_haslo",
                    "Hasło musi zawierać mimimum 6 znaków, co najmniej jedną małą literę, jedną dużą literę, oraz znak specjalny lub cyfrę.");

            return "rejestracja";

        }

        uzytkownikService.zarejestrujNowegoUzytkownika(uzytkownik);
        redirectAttributes.addFlashAttribute("rejestracjaKompletna", true);
        return "redirect:/pomyslna_rejestracja";
    }

    @GetMapping("/pomyslna_rejestracja")
    public String pomyslnaRejestracja(Model model) {
        Boolean rejestracjaKompletna = (Boolean) model.getAttribute("rejestracjaKompletna");
        if (Boolean.TRUE.equals(rejestracjaKompletna)) {
            return "pomyslna_rejestracja";
        } else {
            return "redirect:/";
        }
    }

    @GetMapping("/login")
    public String login(Principal principal) {
        if (principal != null) {

            return "redirect:/";

        } else {
            return "login";
        }
    }
}