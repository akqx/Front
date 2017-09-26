const wynik = document.getElementById("wynik");
var start = document.getElementById("start");
var koniec = document.getElementById("koniec");
var liczba = document.getElementById("wpiszLiczbe");
var startLiczba;
var koniecLiczba;


var jednostki = [
    { nazwa: 'metr', wartosc: 1000 },
    { nazwa: 'kilometr', wartosc: 1000000 },
    { nazwa: 'decymetr', wartosc: 100 },
    { nazwa: 'centymetr', wartosc: 10 },
    { nazwa: 'milimetr', wartosc: 1 },
    { nazwa: 'cal', wartosc: 25.4 },
    { nazwa: 'stopa', wartosc: 304.8 },
    { nazwa: 'jard', wartosc: 914.4 },
    { nazwa: 'mila', wartosc: 1609344 }
]

var zmianaStart = function() {
    startLiczba = jednostki.filter(x => x.nazwa == start.value);
    return startLiczba[0].wartosc;
}

var zmianaKoniec = function() {
    koniecLiczba = jednostki.filter(x => x.nazwa == koniec.value);
    return (koniecLiczba[0].wartosc);
}

function zmiana(a, b, c) {
    return (a * b) / c;

}

function sprawdz(x, a, b, c) {
    if (isFinite(x)) {
        wynik.innerHTML = zmiana(a, b, c);
    } else {
        wynik.innerHTML = "ej ej to program do przeliczania jdenostek ;) wpisz liczbę!"
    }

}

function oblicz() {
    let liczbaText = liczba.value;
    let startLiczba = zmianaStart();
    let koniecLiczba = zmianaKoniec();
    sprawdz(liczbaText, liczbaText, startLiczba, koniecLiczba);
}


start.addEventListener('change', oblicz);
koniec.addEventListener('change', oblicz);
liczba.addEventListener('change', oblicz);