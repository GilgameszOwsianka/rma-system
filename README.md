# RMA System

Aplikacja konsolowa do obsługi reklamacji RMA napisana w czystej Javie (ETAP 1).

---

## 🚀 Uruchomienie

1. Sklonuj repozytorium:
   ```bash
   git clone https://github.com/GilgameszOwsianka/rma-system.git
   cd rma-system

Otwórz projekt w IntelliJ IDEA (lub innym IDE).

Uruchom metodę main() w klasie:

pl.rma.App


Możesz to zrobić klikając prawym na App → Run 'App.main()'.

✅ Funkcjonalności w ETAPIE 1

    Dodanie reklamacji (konsola prosi o nazwę produktu i opis)

    Wyświetlanie wszystkich reklamacji

    Aktualizacja statusu reklamacji (NOWA, W_TRAKCIE, ZAKONCZONA)

    Usuwanie reklamacji

    Obsługa komunikatów w dwóch językach (PL / EN) przez ResourceBundle

    Logowanie do konsoli (SLF4J + SimpleLogger)

    Testy jednostkowe w JUnit 4 dla logiki domenowej

🧩 Struktura pakietów

pl.rma
├── App                  # klasa główna z menu konsolowym
├── model
│   ├── Produkt.java
│   ├── Reklamacja.java
│   └── Status.java      # ENUM
├── repo
│   └── MemoryRepozytorium.java
└── service
    └── ReklamacjaService.java

🧪 Testy

Testy jednostkowe JUnit 4 znajdziesz w:

src/test/java/pl/rma/service/ReklamacjaServiceTest.java

Uruchom przez:

    w IntelliJ prawym na klasie ReklamacjaServiceTest → Run.

    lub przez gradle (jeśli skonfigurujesz build.gradle w ETAPIE 2).

🚦 Przykładowe komunikaty

Komunikaty w polskich i angielskich plikach .properties:

    src/main/resources/messages_pl.properties

    src/main/resources/messages_en.properties

🏗️ Roadmap (kolejne etapy projektu)

ETAP 1 – aplikacja konsolowa w czystej Javie

ETAP 2 – Maven/Gradle + Lombok + wzorce projektowe

ETAP 3 – Spring + JDBC Template + baza H2

ETAP 4 – Spring Data JPA + Hibernate

ETAP 5 – Spring MVC + Thymeleaf

ETAP 6 – Spring Boot + REST API + JWT

ETAP 7 – Docker + CI/CD

📝 Autor: GilgameszOwsianka