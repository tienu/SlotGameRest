# SlotGameRest

Projekt gotowy do skompilowania w Intellij w plik pom.xml zawiera wszystkie Dependecies potrzebnoe do uruchomienia projektu

W projekcie znajduje się plik config.json za pomocą którego można zmieniać dane konfiguracyjne aplikacji oczywiście zgodnie z założeniami projektu

# Obsługa

localhost:8080/startGame - rozpoczyna grę w odpowiedzi użytkownik uzyskuje
{
   status: "OK",
   gameId: ,
   rno: 
}

localhost:8080/spin - przyjmuje json z parametrami
{
	"gameId": ,
	"rno":
}
w odpowiedzi uzyskuje
{
   status: "OK" ,
   gameId: ,
   rno: 
   symbols: [
  [	[, , ],
	[, , ],
	[, , ],
],
	win: 
}

localhost:8080/endGame/?gameId= - przyjmuje parametr odpowiadający id gry którą chce zakończyć
w odpowiedzi uzyskuje
{
   status: "OK" ,
   gameId: 2,
   rno: 278
}

localhost:8080/sessions - wyświetla wszystkie sesje 
w odpowiedzi uzyskuje szczegóły wszystkich gier zakończonych oraz nie zakończonych 

# Wykorzystane technologie

Spring boot - Wykorzystany do stworzenia szkieletu projektu automatycznie zostały wykorzystane:
  Tomcat - wersja 9.0.17
  Maven - Zarządzanie zależnościami
  lombok - Generowanie Geterów i Seterów
 Jackson - Przetwarzanie plikówj Json



Mateusz Nguyen Tien

