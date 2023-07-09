# contact-list


## Opis poszczególnych klas i metod

### Backend:
ApiControllers
Klasa której zadaniem jest pobieranie i wysyłanie odpowiednich danych na wskazane endpointy. Dzieki niej możemy na wskazane endpointy np. wysyłać kontakty istniejące w bazie danych lub ze wskazanego endpointu wysylać tworzony po stronie uzytkownika kontakt na baze danych.
AuthController
Klasa zajmująca sie autentykacja uzytkownika, czyli jest sciśle związana z logowaniem. Wysyła na wskazany endpoint token dzieki któremu użytkownik będzie w stanie pozostać zalogowanym na stronie.
Contact
Klasa opisujaca jak wyglada kontakt dodawany do listy kontaktów, i tym samym do bazy danych. W jej skład głównie wchodzą gettery i settery.
Jwks
Ta klasa jest narzędziem do generowania kluczy RSA i tworzenia obiektow RSA. Celem tej klasy jest umożliwienie generowania kluczy RSA i utworzenie obiektu RSAKey, który może być używany w innych częściach kodu do obsługi uwierzytelniania, autoryzacji, generowania tokenów itp.
KeyGeneratorUtils
Ta klasa jest pomocnicza i używana jest przez klasę Jwks do generowania klucza RSA. Jej głównym celem jest dostarczenie narzędzia do generowania par kluczy RSA, które mogą być wykorzystane w różnych częściach kodu, w tym w przypadku generowania tokenów, obsługi uwierzytelniania i autoryzacji.
ContactService
Ta klasa umożliwia nam pobieranie z bazy danych kontaktów, modyfikowanie i usuwanie ich.
TokenService
Ta klasa odpowiada za generowanie Tokenow na podstawie obiektu Authentication. Celem tej klasy jest obsługa generowania tokenów uwierzytelniających, które są wykorzystywane do uwierzytelniania żądań i autoryzacji dostępu w aplikacji.
WebSecurityConfig
Celem tej klasy jest konfiguracja zabezpieczeń i dostępów w aplikacji opartej na Spring Security, włącznie z uwierzytelnianiem, autoryzacją, obsługą tokenów JWT, obsługą CORS itp. 

### Frontend:
Addform.jsx
Tworzy nam formularz dodający kontakt do listy
ConfirmationPage.jsx
Daje wrażenie działania aplikacji w “czasie rzeczywistym” gdzie tak naprawdę przeładowuję nam strone 
Contact.jsx
Pokazuje nam dane szczegółowe kontaktu i przyciski dające nam możliwość usuwania i edycji kontaktu.

ContactList.jsx
Wyświetla nam listę kontaktów z podstawowymi danymi.
EditForm.jsx
Daje nam możliwość edytowania istniejącego kontaktu.
Home.jsx
Strona główna naszej aplikacji zajmująca się wysyłaniem użytkownika do logowania lub wylogowania w zależności od tego, czy nie jest, czy jest zalogowany.
LoginForm.jsx
Formularz dający użytkownikowi możliwość zalogowania się.
LogoutPage.jsx
Działa na tej samej zasadzie co ConfirmationPage, jedynie działa nie po edycji, usuwaniu czy dodawaniu a przy wylogowywaniu.

## Działanie logowania:
Pobieramy z backendu wygenerowany token JWT (JSON Web Token) na frontend i przechowujemy go w Store za pomoca biblioteki redux. Dzieki temu tokenowi uzyskujemy dostep do korzystania z edycji, dodawania i usuwania kontaktow z listy.


## Wykorzystane biblioteki

Backend został wykonany z użyciem frameworka Spring Boot w jezyku Java. Przy tworzeniu projektu dodałem Spring Security, OAuth2, Web Spring i biblioteke do obslugi bazy danych MySQL oraz sterownikow do jej obsługi.
Frontend został wykonany z użyciem frameworka React. Korzystałem z bibliotek: Redux, Router, axios.


## Sposób kompilacji aplikacji

Aby aplikacja poprawnie dzialala nalezy:
Pobrac na Docker’a image MySQL 
Połaczyć sie z image mysql za pomoca MySQL workbench
Do bazy danych dodac tabele o nazwie “contact” w ktorej beda kolumny: “id”, “name”, “last_name”, “email”, “password”, “category”, “subcategory”, “phone_number” oraz “birth_date”
Z poziomu folderu backend nalezy uruchomic “mvn spring-boot:run”
Z poziomu folderu frontend nalezy uruchomic “npm run dev”


