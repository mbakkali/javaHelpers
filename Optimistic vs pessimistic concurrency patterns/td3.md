# Optimistic vs pessimistic concurrency patterns

## A "no worries" strategy

`Which problems can you anticipate when several threads try invoking add(…​) and substract(…​)?`<br/>
Le résultat peut être faux car on modifie entre temps la valeur de valeurs[index]

`How can you check that experimentally?`<br/>
On crée plusieurs threads qui modifie le même HippieStore (avec add et substract) et on observe si le résultat est ok ou non.

un long se modifie en 2 opérations processeur. Donc l'opération n'est pas atomic.

## Benchmarking
`Runner.java ->`bench La classe crée plusieurs appel de fonctions add et substract sur le magasin: jobs. Il soumet ensuite les taches à un executeurs avec 4 threads.
Il mesure le temps pour le process d'une tache.

Résultat : Les performances ne sont pas stables. Les résultats ne sont pas déterministe. Les opérations ne mélangent et se chevauchent.


## Optimistic strategy
`AtomicLongArray` est un array de long dont chaque élément peut être modifié en prenant en charge la concurrence. Permet de faire de la concurrence LOCK-free.

on utilise:<br/>
compareAndSet(index, atomicValues[index] + amount, amount);

On a gagné encore un peu de temps.


CCL : Quel strategie pour quelle charge?
#### Plus de threads que la taille du tableau :
Pessimistic: 2352ms Optimistic: 2275ms
#### Moins de job count
Réduire le JOB_COUNT réduit le temps global. Optimistic mieux que Pessimistic.
#### Très peu de thread
