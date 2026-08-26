# Bibliothèque – Gestion de bibliothèque en Java

Projet personnel réalisé pour appliquer et consolider les fondamentaux Java (POO, héritage, exceptions personnalisées, collections, Streams) avant d'évoluer progressivement vers JDBC puis Spring Boot.

## Contexte

Ce projet simule la gestion d'une bibliothèque : catalogue de documents, adhérents, emprunts et retours. Il a été construit de manière incrémentale, phase par phase, pour préparer une transition vers un poste de développeur.

## Stack technique

- **Java 21** (LTS)
- **Maven** pour la gestion des dépendances et du build
- **JUnit 5** pour les tests unitaires

## Fonctionnalités

- Hiérarchie de documents : `Document` (classe abstraite) et `Livre` (sous-classe), extensible à d'autres types (DVD, magazine...)
- Gestion du catalogue : ajout de documents, association d'un livre à un auteur et une catégorie
- Gestion des adhérents et de leurs emprunts
- Emprunt et retour de documents, avec vérification de disponibilité (`LivreIndisponibleException`)
- Recherche de documents par ISBN ou catégorie, et de livres par auteur (via Streams)
- Statistiques : document le plus emprunté, top adhérents les plus actifs (`Collectors.groupingBy`)
- Suivi des emprunts en retard

## Structure du projet

```
src/main/java/com/steeve/bibliotheque/
├── modele/       # Document (abstraite), Livre, Auteur, Adherent, Emprunt
├── exception/    # Exceptions personnalisées (LivreIndisponibleException, LivreNonTrouveException...)
├── service/      # Bibliotheque (logique métier et orchestration)
└── Main.java     # Point d'entrée / démonstration manuelle
```

## Lancer le projet

```bash
mvn compile
mvn exec:java -Dexec.mainClass="com.steeve.bibliotheque.Main"
```

## Lancer les tests

```bash
mvn test
```

## Roadmap

- [x] **Phase 1** – Modèle Java pur : POO, héritage, exceptions personnalisées, collections, Streams, tests JUnit
- [ ] **Phase 2** – Persistance via JDBC (pattern DAO, base MySQL/PostgreSQL)
- [ ] **Phase 3** – API REST avec Spring Boot (Spring Data JPA, documentation Swagger)

## Choix de conception

- `Document` est une classe abstraite regroupant les attributs communs à tout document empruntable (`titre`, `isbn`, `dispo`, `categorie`). `Livre` en hérite et ajoute la notion d'auteur, propre à ce type de document.
- Les méthodes de recherche par ISBN/catégorie travaillent au niveau `Document` (polymorphisme), tandis que la recherche par auteur filtre spécifiquement les `Livre` via `instanceof` + cast, car un auteur n'a de sens que pour ce type de document.
- Les méthodes de recherche (`rechercherParIsbn`, etc.) retournent un `Optional<Document>` plutôt que de lever une exception, car l'absence de résultat est un cas normal de consultation, pas une erreur.
- `LivreIndisponibleException` est une exception *checked*, car elle représente un cas métier attendu que l'appelant doit explicitement gérer.
- La relation `Auteur` ↔ `Livre` est synchronisée automatiquement dans le constructeur de `Livre`, pour éviter toute incohérence entre les deux objets.
