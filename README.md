# Soutenance DTST - Backend

Application backend Spring Boot pour la gestion d'utilisateurs avec API REST.

## Description

API REST Java permettant les opérations CRUD sur des utilisateurs. L'application utilise Spring Boot avec JPA pour la persistance des données dans une base MySQL.

## Stack Technique

- **Framework** : Spring Boot
- **Language** : Java
- **Build** : Maven
- **Base de données** : MySQL 8.0 (AWS RDS)
- **Conteneurisation** : Docker

## API Endpoints

| Méthode | Endpoint | Description |
|---------|----------|-------------|
| GET | `/api/users` | Récupérer tous les utilisateurs |
| GET | `/api/users/{id}` | Récupérer un utilisateur par ID |
| POST | `/api/users` | Créer un nouvel utilisateur |
| PUT | `/api/users/{id}` | Modifier un utilisateur |
| DELETE | `/api/users/{id}` | Supprimer un utilisateur |

## Branches

- **develop** : Branche de développement continu (CI/CD actif)
- **release** : Branche pour préparation des releases

## Installation Locale

### Prérequis
- Java 17+
- Maven 3.6+
- MySQL 8.0

### Compilation
```bash
mvn clean compile
```

### Tests
```bash
mvn test
```

### Build
```bash
mvn clean package
```

### Exécution
```bash
java -jar target/*.jar
```

L'application démarre sur le port **8080**.

## Variables d'Environnement

| Variable | Description | Exemple |
|----------|-------------|---------|
| `SPRING_DATASOURCE_URL` | URL de connexion MySQL | `jdbc:mysql://localhost:3306/userdb` |
| `SPRING_DATASOURCE_USERNAME` | Utilisateur BDD | `admin` |
| `SPRING_DATASOURCE_PASSWORD` | Mot de passe BDD | `admin123` |

## Versioning

Le versioning est géré automatiquement par Maven Release Plugin :
```bash
mvn release:prepare release:perform
```

Les versions suivent le Semantic Versioning (ex: 1.0.0, 1.1.0).

## CI/CD

### Pipeline DEV QA/TEST (automatique)
Déclencheur : Push sur `develop`
- Compilation Maven
- Tests unitaires JUnit
- Analyse SonarQube

### Pipeline REC1-Backend (automatique)
Déclencheur : Merge `develop` → `release`
- Build Maven
- Build image Docker (tag latest)
- Push Docker Hub

### Pipeline RELEASE-Backend (manuel)
- Maven Release (versioning automatique)
- Build image Docker versionnée
- Push Docker Hub

## Tags Docker

- **Registry** : Docker Hub
- **Repository** : `awsfox92/soutenance-project-demo`
- **Tags** : `latest`, `1.0.0`, `1.1.0`, etc.

## Tests et Qualité

### Tests unitaires
Framework : JUnit 5

Classes testées :
- `UserServiceTest`

### Analyse de code
Outil : SonarQube

Métriques surveillées :
- Bugs
- Vulnérabilités
- Code smells
- Coverage
- Tech Debt

## Liens

- **Frontend** : https://github.com/webyprod/soutenance_dtst_ui
- **Déploiement** : https://github.com/webyprod/soutenance_dtst_deploy
- **Docker Hub** : https://hub.docker.com/r/awsfox92/soutenance-project-demo
