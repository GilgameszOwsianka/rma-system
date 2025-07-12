📦 RMA System - Java Console Application  
RMA System to przykładowa aplikacja konsolowa w czystej Javie do zarządzania reklamacjami (RMA - Return Merchandise Authorization).

Umożliwia:

- dodawanie reklamacji,  
- listowanie wszystkich reklamacji,  
- aktualizowanie statusu reklamacji,  
- usuwanie reklamacji,  
- zapis/odczyt danych do pliku JSON,  
- obsługę wielu języków (PL/EN) przez ResourceBundle.  

🚀 Funkcjonalności  
- CRUD reklamacji w pamięci (Map<Long, Reklamacja>)  
- Obsługa statusów: NOWA, W_TRAKCIE, ZAKOŃCZONA  
- Wyszukiwanie reklamacji po ID z Optional  
- Stream API (np. forEach, filter)  
- Pliki JSON (zapis i odczyt reklamacji)  
- Lokalizacja: PL / EN (w katalogu resources/messages_*.properties)  
- Logowanie zdarzeń przez SLF4J + SimpleLogger  
- Testy jednostkowe w JUnit  

🛠 Technologie  
- Java 17  
- SLF4J + SimpleLogger  
- ResourceBundle (i18n)  
- JSON (Jackson / GSON – w zależności od implementacji)  
- PlantUML (UML w /docs)  

✅ Wymagania  
- Java 17+  

⚙️ Jak uruchomić  
Sklonuj repozytorium:

git clone https://github.com/GilgameszOwsianka/rma-system.git
cd rma-system

Skompiluj ręcznie:
javac -d out src/main/java/pl/rma/**/*.java

Uruchom aplikację:
java -cp out pl.rma.App

💡 Możesz też skorzystać z konfiguracji w swoim IDE (np. IntelliJ IDEA / Eclipse), wskazując klasę startową pl.rma.App.

📈 Diagramy UML
W katalogu /docs znajdują się diagramy UML w formacie PlantUML:

> class-diagram.puml – diagram klas

> use-case-diagram.puml – diagram przypadków użycia

## ✅ Testy jednostkowe (TDD)

| Test                                           | Opis                                          | Wynik oczekiwany    |
|------------------------------------------------|-----------------------------------------------|---------------------|
| testDodajReklamacjePowinienDodacNowaReklamacje | Dodanie nowej reklamacji                      | Reklamacja dodana   |
| testDodajReklamacjeNullPowinnoRzucicWyjatek    | Walidacja: dodanie null powoduje wyjątek      | NullPointerException|
| testAktualizujStatus                           | Aktualizacja statusu reklamacji               | Status zmieniony    |
| testUsunNieistniejacaReklamacjeZwracaFalse     | Próba usunięcia nieistniejącej reklamacji     | Zwraca false        |

💡 Autor

Projekt edukacyjny w czystej Javie stworzony w celach nauki OOP, kolekcji, Stream API, Optional, testów jednostkowych i wzorców projektowych.