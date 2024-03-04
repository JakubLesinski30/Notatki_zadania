document.addEventListener("DOMContentLoaded", function() {
    zaladujNotatki();
    zaladujZadania();

    document.getElementById("dodajNotatkeForm").addEventListener("submit", function(event) {
        event.preventDefault();
        var trescNotatki = document.getElementById("trescNotatki").value;
        dodajNotatkeNaSerwer(trescNotatki);
    });

    document.getElementById("dodajZadanieForm").addEventListener("submit", function(event) {
        event.preventDefault();
        var opisZadania = document.getElementById("opisZadania").value;
        dodajZadanieNaSerwer(opisZadania);
    });

    function zaladujNotatki() {
        var xhr = new XMLHttpRequest();
        xhr.onreadystatechange = function() {
            if (xhr.readyState === 4 && xhr.status === 200) {
                var notatki = JSON.parse(xhr.responseText);
                var listaNotatek = document.getElementById("listaNotatek");
                listaNotatek.innerHTML = "";
                notatki.reverse().forEach(function(notatka) {
                    var pozycjaListy = document.createElement("li");
                    pozycjaListy.className = "list-group-item";
                    pozycjaListy.textContent = notatka.tresc;
    
                    var przyciskUsun = document.createElement("button");
                    przyciskUsun.textContent = "Usuń";
                    przyciskUsun.className = "btn btn-danger btn-sm float-right";
                    przyciskUsun.addEventListener("click", function() {
                        usunNotatkeZSerwera(notatka.id);
                    });
    
                    pozycjaListy.appendChild(przyciskUsun);
                    listaNotatek.appendChild(pozycjaListy);
                });
            }
        };
        xhr.open("GET", "/notatki", true);
        xhr.send();
    }
    
    function zaladujZadania() {
        var xhr = new XMLHttpRequest();
        xhr.onreadystatechange = function() {
            if (xhr.readyState === 4 && xhr.status === 200) {
                var zadania = JSON.parse(xhr.responseText);
                var listaZadan = document.getElementById("listaZadan");
                listaZadan.innerHTML = "";
                zadania.reverse().forEach(function(zadanie) {
                    var pozycjaListy = document.createElement("li");
                    pozycjaListy.className = "list-group-item";
                    pozycjaListy.textContent = zadanie.tresc;
    
                    var przyciskUsun = document.createElement("button");
                    przyciskUsun.textContent = "Usuń";
                    przyciskUsun.className = "btn btn-danger btn-sm float-right";
                    przyciskUsun.addEventListener("click", function() {
                        usunZadanieZSerwera(zadanie.id);
                    });
    
                    pozycjaListy.appendChild(przyciskUsun);
                    listaZadan.appendChild(pozycjaListy);
                });
            }
        };
        xhr.open("GET", "/zadania", true);
        xhr.send();
    }

    function dodajNotatkeNaSerwer(trescNotatki) {
        var xhr = new XMLHttpRequest();
        xhr.onreadystatechange = function() {
            if (xhr.readyState === 4 && xhr.status === 200) {
                zaladujNotatki();
                document.getElementById("trescNotatki").value = "";
            }
        };
        xhr.open("POST", "/notatki", true);
        xhr.setRequestHeader("Content-Type", "application/json");
        xhr.send(JSON.stringify({ tresc: trescNotatki }));
    }

    function dodajZadanieNaSerwer(opisZadania) {
        var xhr = new XMLHttpRequest();
        xhr.onreadystatechange = function() {
            if (xhr.readyState === 4 && xhr.status === 200) {
                zaladujZadania();
                document.getElementById("opisZadania").value = "";
            }
        };
        xhr.open("POST", "/zadania", true);
        xhr.setRequestHeader("Content-Type", "application/json");
        xhr.send(JSON.stringify({ tresc: opisZadania }));
    }

    function usunNotatkeZSerwera(idNotatki) {
        var xhr = new XMLHttpRequest();
        xhr.onreadystatechange = function() {
            if (xhr.readyState === 4 && xhr.status === 200) {
                zaladujNotatki();
            }
        };
        xhr.open("DELETE", "/notatki/" + idNotatki, true);
        xhr.send();
    }

    function usunZadanieZSerwera(idZadania) {
        var xhr = new XMLHttpRequest();
        xhr.onreadystatechange = function() {
            if (xhr.readyState === 4 && xhr.status === 200) {
                zaladujZadania();
            }
        };
        xhr.open("DELETE", "/zadania/" + idZadania, true);
        xhr.send();
    }
});
