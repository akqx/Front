const wynik = document.getElementById("demo");

function liczbaPierwsza(pierwsza) {
    for (let i = 2; i * i <= pierwsza; i++) {
        if (pierwsza % i == 0)
            return false;
    }
    return true;
}

function pokazLiczbyPierwsze(x) {
    var lista = "liczby pierwsze " + x + ' to:';
    for (var i = 1; i <= x; i++) {
        if (liczbaPierwsza(i) == true) {
            lista += i + ',';
        }
    }
    return lista;
}

function sprawdzLiczbe(x) {

    if (isFinite(x)) {
        return x;
    } else {
        wynik.textContent = "Podaj liczbe! ";
    }
}

function myFunction() {
    const x = document.getElementById("liczba").value;
   
    sprawdzLiczbe(x);
    if (x <= 1000 && x >= 0) {
        if (x == 0) {
            wynik.textContent = ("Zero nie ma liczby pierwszej");
        } else {
            wynik.textContent = pokazLiczbyPierwsze(x);
        }
    }
    else wynik.textContent = 'Podaj liczby z przedziału od 1 do 1000';

}