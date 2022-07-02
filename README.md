# FAprojetIHM2020

## Introduction

Dans le cadre de nos cours d’ACDA à l’IUT de Fontainebleau, j’ai eu l’opportunité de travailler sur un logiciel intitulé Dungeon Maker. Développé en JAVA, ce logiciel est connecté à une base MySQL via l’API JDBC.

Le logiciel est construit sur la base d’une API développée par nos professeurs et un modèle non persistant est à notre disposition.

L’objectif du travail est double:

Créer une interface Homme machine douée de sens et permettant une utilisation simple

Créer le modèle persistant qui permettra au logiciel de fonctionner à l’aide d’une base MySQL

Dans le cadre de ce rapport, je m’apprête à vous faire découvrir l’ensemble des rouages qui m’ont permis de créer ce logiciel.

Bonne lecture. 

## L’Interface Homme-Machine

L’inspiration principale pour la réalisation de ce projet est l’UI MacOSX. EN effet, je souhaitais mettre en place une interface simple d’accès et qui avait déjà fait ses preuves. L’idée est donc de diviser le logiciel en trois parties:

un menu sous forme de JBarMenu, permettant de manipuler la partie gestion du logiciel: la gestion de fichiers, l’ouverture d’un fichier vierge… Cette partie sera dédiée au Menu.
une seconde partie est la partie centrale, elle est le coeur du logiciel ou s’afficheront les pièces et les passages sur une grille.
Enfin, la troisième partie est décomposée en deux entités:
la première est le menu contextuel qui permet de manipuler le fichier en cours d’utilisation
la seconde est un « log » sur lequel vont s’afficher différentes informations telles que des validations, la position courante de la grille ou la case ajouté/cliquée.

L’interface utilisateur est donc pensée pour être simple, et robuste.

Par exemple, le fait d’avoir dissocié la partie gestion de document de la manipulation du document courant est une volonté de s’accorder aux standards actuels.

Autrement dit la dissociation de la partie gestion et création permet à l’utilisateur de mieux comprendre le fonctionnement du logiciel.

Initialement j’étais parti pour centrer le menu du bas, et ôter la partie log afin de ressembler au plus à l’interface MacOS. Mais j’ai finalement opté pour quelque chose ressemblant plus à un utilitaire qu’à un système d’exploitation.

## La barre de menu

Cette entité de l’IHM est primordiale, car elle sert à l’exploitation des fichiers. L’objectif de cette barre est d’exécuter les méthodes de gestion de fichier directement depuis le contrôleur. En effet, le logiciel est architecturé selon le modèle MVC.
L’idée est la suivante: les boutons de la JBarMenu sont instanciés dans la vue, puis une fois cliqués, ils lancent un Action Listener dans une classe correspondante dans lequel on a transmis un lien vers le contrôleur. De cette manière, on peut manipuler les données sauvegardées en local et faire des appels depuis le contrôleur au modèle (plus précisément à la classe Connexion du modèle Persistant).

## L’affichage du contenu du fichier

Comme dit précédemment, l’affichage est le coeur du logiciel.
C’est là ou s’affichent les différentes salles du donjon et les passages qui les connectent.

Il y a plusieurs fonctionnalités qui dans cette affichage simplifient l’expérience utilisateur, à commencer par le zoom.

Le zoom est l’une des parties les plus compliquée que j’ai dû faire, car quand le redimensionnement de la grille fonctionnait bien, la mise à l’échelle des autres éléments ne marchait pas toujours. Le principal défaut de cette fonctionnalité est ce que j’appelle « le centre du zoom ».

En effet, lorsque l’utilisateur va zoomer sur la grille, celle-ci va se redimensionner par rapport à l’origine du graphique. Autrement dit, la grille se met à l’échelle mais depuis le haut-gauche.

J’ai laissé tel quel le zoom, mais je pense qu’avec du temps supplémentaire, j’aurais corrigé cette fonctionnalité pour que le zoom s’accorde avec le centre de l’écran, voire avec la position de la souris.
J’ai eu l’occasion de faire plusieurs demandes à mon professeur Mr Hernandez sur la mise en place du zoom.

Les autres fonctionnalités sont liées au deuxième menu du logiciel: le menu contextuel.

## Le menu contextuel

Le menu contextuel est doué de quatre boutons différents. Chacun des ces boutons permettent de changer de mode (voir la variable Mode dans Controller).
Le mode est un entier qui peut prendre quatre valeurs:
si Mode == 0, alors on est dans le mode Manipulation, qui va permettre de naviguer dans la grille au clic. Il suffira de glisser déposer la grille pour parcourir la grille.
Si Mode == 1, on est dans le mode d’ajout de pièce que je vais détailler plus loin.
Si Mode == 2, on est dans le mode d’ajout de couloir que je vais détailler plus loin.
Si Mode == 3, alors on est dans le mode de suppression que je vais détailler plus loin.
Ces différents modes permettent d’agir différemment sur la grille. En effet, la classe ManipGrid qui permet d’utiliser la grille va être influencée par la variable Mode et ainsi limiter l’interaction de l’utilisateur avec la grille en fonction ce cette dernière.
Le mode Manipulation a pour icône une croix directionnelle pour comprendre aisément son utilité.

Le mode d’ajout de Pièce est un plus avec un carré rouge, les pièces étant affichées sur la grille, il était normal de montrer de manière visuelle et logique sa signification.

Le mode d’ajout de passages est un plus vert avec un ‘p’ en haut à droite. Je voulais qu’on puisse différencier rapidement ce bouton du bouton d’ajout de pièces. Il est donc de couleur verte, la même couleur qui arbore les passages sur la grille.

Le mode de suppression est représenté par une poubelle car elle est immédiatement reconnaissable et compréhensible par tout le monde.

## Le Mode Ajout

Lorsque l’utilisateur passe sur le mode ajout, il a la possibilité de placer de nouvelles pièces aux positions voulues.
Un simple clic sur la case choisie créera une nouvelle case rouge qui représente une pièce.
J’ai choisie la couleur rouge car je souhaitais que la présence de pièce soit visible au premier coup d’oeil.

## Le Mode Ajout de passage
Ce mode a été l’une sinon la plus longue réflexion que je me suis posé au niveau de l’IHM. En effet, l’idée de base était de développer un système de drag and drop: l’utilisateur aurait d’un seul clic lié des pièces, et les directions occupées par le passage aurait été choisie dans une pop-up. J’aurais aimé rajouter cette méthode si j’avais disposé de plus de temps car lors de la première tentative cette méthode c’est révélée infructueuse. La seconde solution que j’ai finalement retenue est le clic successif sur les deux pièces. A chaque clic, une pop-up va s’ouvrir avec une croix directionnelle. Sur cette croix directionnelle s’afficheront les directions possibles. Si une direction est déjà empruntée par un passage, le bouton pour choisir cette direction n’est pas cliquable.

Enfin, l’affichage du chemin a été compliqué à mettre en oeuvre: si au début je rêvais d’implémenter un algorithme de « pathfinding » afin d’afficher le chemin, je me suis résigné à créer un algorithme permettant de dessiner un passage plus simple.
En effet, si les deux pièces sont placées sur une même ligne ou une même colonne, le passage se constituera d’une ligne. Sinon, le passage sera dessiné avec deux lignes successives: de manière à faire un angle droit entre les deux parties du passage, pour plus de cohérence.

## Le mode Suppression

Dans l’objectif de simplifier l’interface utilisateur, j’ai choisi de fusionner deux boutons auxquels j’avais pensé: le bouton de suppression de passage, et le bouton de suppression de pièces.
En effet, j’ai décidé de rassembler deux interfaces en une, plus simple et intuitive.
Comme vous pouvez le constater, cette pièce est isolée et non connectée à d’autres pièces. C’est pourquoi les boutons de direction sont désactivés.

On peut choisir de juste supprimer un passage, ou bien la pièce elle-même, ce qui aura pour conséquence de supprimer l’ensemble des passages affiliés à cette pièce.

## Les fonctionnalités manquantes

J’aurais souhaité travailler sur des fonctionnalités supplémentaire: par exemple, la gestion des objets où avoir eu l’opportunité de travailler sur du drag and drop. J’aurais également implémenté la gestion des objets dans la base de donnée.
J’aurais également aimé ajouter le système de clé qui est important dans la conception du gameplay du jeu.
Enfin, j’aurais adoré pouvoir faire un système de chemin plus élaboré. Non pas avec un pathfinding mais avec le dessin du chemin au clic: l’utilisateur aurait cliqué plusieurs fois pour ajouter des segments au dessin.
