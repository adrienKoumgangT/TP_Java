# Ordonnancement

Le programme se déroule en plusieurs étapes :

1. Lecture d'un tableau de contraintes contenu dans un fichier .txt,
mise en mémoire et affichage de ce tableau sur l'écran ;
2. Construction d'un graphe correspondant à ce tableau de contraintes ;
3. Vérification du fait que ce graphe possède toutes les propriétés
nécessaires pour qu'il soit un graphe d'ordonnancement :
    - Un seul point d'entrée,
    - Un seul point de sortie,
    - Pas de circuit,
    - Valeurs identiques pour tous les arcs incidents vers l'extérieur à un sommet,
    - Arcs incidents vers l'extérieur au point d'entrée ont une valeur nulle,
    - Pas d'arcs à valeur négative.
4. Si toutes ces propriétés sont vérifiées, calculer le calendrier au plus tot,
la durée totale de projet, le calendrier au plus tard et les marges.

Pour le calcul du calendrier au plus tard, on utilisera la convention que la date
au plus tard de fin de projet soit égale à sa date au plus tot.
Comme on le sait, pour le calcul des calendriers, il faut d'abord effectuer
le tri topologique du graphe : ordonner les sommets dans l'ordre des rangs croissants.
Il faut donc affecter un rang à chaque sommet, en utilisant un algorithme au choix.


## 1. Tableau de contraintes de test

## 1.1 Référence

Des tableaux de test sont disponibles sous forme de fichiers .txt
pour permettre de tester notre programme. 
Ces fichiers sont disponibles dans le dossier [input files](test/files/input/)

De meme, des résultats attendus de ces tests sont disponibles dans le dossier
[expected output files](test/files/output/)

## 1.2 Exemple de tableau de contraintes

Un tableau de contraintes a la forme suivante.
Sur chaque ligne :
- Le premier chiffre est le numéro de tache ;
- Le deuxième sa durée ;
- Et les autres chiffres, si présents, sont des contraintes (prédécesseurs).

Les N taches sont numérotés de 1 à N.
La tache fictive $\alpha$ sera notée 0.
La tache fictive $\omega$ aura le numéro N+1.

**Exemple** :
```
1 9
2 2
3 3 2
4 5 1
5 2 1 4
6 2 5
7 2 4
8 4 4 5
9 5 4
10 1 2 3
11 2 1 5 6 7 8
```

## 2. Déroulement du programme

### 2.1 Consigne

Le programme de lancement de l'ordonnancement
exécute les actions suivantes au préalable :

1. Lecture d'un tableau de contraintes donné dans un fichier texte (.txt)
et stockage en mémoire
2. Affichage du graphe correspondant sous forme matricielle (matrice des valeurs).
Attention : cet affichage se fait à partir du contenu mémoire, et non pas directement en lisant le fichier.
Ce graphe incorpore les deux sommets fictifs $\alpha$ et $\omega$ (notés 0 et N+1 où N est le nombre de taches).
3. Vérification de toutes les propriétés nécessaires du graphe pour qu'il puisse servir
d'un graphe d'ordonnancement :
   - pas de circuit,
   - pas d'arcs à valeur négative.
4. Calcul les rangs de tous les sommets du graphe.
5. Calcul du calendrier au plus tot, le calendrier au plus tard et les marges.
   - NB : Pour le calcul du calendrier au plus tard, nous considérons que la date
   au plus tard de fin de projet est égale à sa date au plus tot.

### 2.2 Structure globale du programme

La structure globale de notre programme est illustrée
par le pseudo-code suivant :

```
Début
   Tant que l'utilisateur décide de tester un tableau de contraintes faire
      Choisir le tableau de contraintes à traiter
      Lire le tableau de contraintes sur fichier le stocker en mémoire
      Créer la matrice correspondant au graphe représentant ce tableau de contraintes et l'afficher
      Vérifier que les propriétés nécessiares pour que ce graphe soit un graphe d'ordonnancement sont vérifiées
         Si oui alors
            Calculer les rangs des sommets et les affichers
            Calculer les calendriers au plus tot et au plus tard et les afficher
            Calculer le(s) chemin(s) critique(s) et les afficher
         Sinon
            Proposer à l'utilisateur de changer de tableau de contraintes
   Fin tant que
Fin
```

**Remarque** : nous mettrons en œuvre la détection de circuit dans l'algorithme de calcul de rang.
Nous n'afficherons les rangs que dans l'absence de circuit.

## 2.3 Exemple de trace

En prenant le tableau de contrainte suivant,
nous donnons un exemple d'affichage pour les trois premières étapes
sachant que les autres étapes suivant le meme principe de lisibilité.

```
1 1
2 2
3 3 1
4 4 1 2
5 5 2 4
```

- **Etape 1**
```
Création du graphe d'ordonnancement:
7 sommets
9 arcs
0 -> 1 = 0
0 -> 2 = 0
1 -> 3 = 1
1 -> 4 = 1
2 -> 4 = 2
2 -> 5 = 2
3 -> 6 = 3
4 -> 5 = 4
5 -> 6 = 5
```

- **Etape 2**

```
Matrice des valeurs
   0  1  2  3  4  5  6
0  *  0  0  *  *  *  *
1  *  *  *  1  1  *  *
2  *  *  *  *  2  2  *
3  *  *  *  *  *  *  3
4  *  *  *  *  *  4  *
5  *  *  *  *  *  *  5
6  *  *  *  *  *  *  *
```

- **Etape 3**

Il y a un seul point d'entrée: 0
Il y a un seul point de sortie: 6
Détection de circuit (Avec la méthode de suppression des points d'entrée):

```
* Détection de circuit
* Méthode d'élimination des points d'entrée
Points d'entrée : 0
Suppression des points d'entrée
Sommets restant : 1 2 3 4 5 6
Points d'entrée : 1 2
Suppression des points d'entrée
Sommets restant : 3 4 5 6
Points d'entrée : 3 4
Suppression des points d'entrée
Sommets restant : 5 6
Points d'entrée : 5
Suppression des points d'entrée
Somments restant : 6
Points d'entrée : 6
Suppression des points d'entrée
Sommets restant : Aucun
-> Il n'y a pas de circuit
```

Les valeurs pour tous les arcs incidents vers l'extérieur à un sommet sont identiques.

Les arcs 0→1 et 0→2 sont nuls.

Il n'a pas d'arcs négatifs.

→ C'est un graph d'ordonnancement.
