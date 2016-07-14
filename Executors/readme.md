# TD 2, Indexation efficace

## A search Engine
`Which collection(s) from java.util.concurrent shall you use to model an index?`<br/>
D'après le package, la classe ConcurrentSkipListMap est mieux qu'un arbre donc correspond pas trop mal à une arborescence de fichier.

`What kind of executors does java.util.concurrent offers? What does the factory java.util.concurrent.Executors class offers?`<br/>
Executors, ScheduledThreadPoolExecutor, ThreadPoolExecutor, AbstractExecutorService... La classe Executors offre des methodes pour fabriquer des instances d'Executor du package. Ils executent des taches. dès qu'il a finie une tache, il passe à la suivante.

Il existe des executeurs monothread, multithread, et des executeurs avec un pool de thread. Celui la crée et supprime des thread en fonction du nombre de taches à faire.
Les executeurs s'occupent de réaliser des taches à un moment du programme..

`What is a Future for?`<br/>
Un futur est le résultat issue d'un calcul asynchrone. (Donc un résultat qu'on obtient à un moment comme javascript).

## Indexing files

`Describe the file indexing algorithm. How do you deal with folders?`<br/>
L'algorithme regarde si le chemin est un fichier ou un repertoire. Si c'est un fichier, alors il va lire le fichier à la recherche du mot recherché. Si c'est un dossier, il va parcourir le dossier pour relancer des job sur les fichier/dossier contenue.
