# Exercice Microservices 1

- Service Utilisateur 

    1. Définissez une entité User avec les champs suivants : id, name, et email.
    2. Créez un service et un contrôleur pour l'entité User contenant les methodes d'un CRUD.
    3. Le service doit avoir une endpoint pour récupérer un utilisateur par son id.

- Service Commande 

    1. Définissez une entité Order avec les champs : orderId, userId et product.
    2. Créez un service et un contrôleur pour l'entité Order contenant les methodes d'un CRUD.
    3. Le service doit avoir une endpoint pour récupérer toutes les commandes passées par un userId spécifique.


- Quand le OrderService reçoit une requête pour récupérer les commandes d'un utilisateur, il devrait également communiquer avec le UserService pour récupérer les informations détaillées de cet utilisateur

