import java.util.ArrayList;
import java.util.PriorityQueue;

public class Main {

    public static void main(String[] args) {
        // TODO Vérifier si impossible
        // TODO Switch pour choix taille taquin
        // TODO Switch pour choix heuristique

        PriorityQueue<Taquin> listeEtatOuvert = new PriorityQueue<Taquin>();
        PriorityQueue<Taquin> listeEtatFerme = new PriorityQueue<>();
        //Taquin à resoudre, etat initiale
        Taquin t = new Taquin();
        t.init(listeEtatOuvert, listeEtatFerme);
        listeEtatOuvert.add(t);

        long debut = System.currentTimeMillis();
        long tmp = System.currentTimeMillis();

        System.out.println("Veuillez Patienter ...");
        try {
            if(!t.isSolution()) {
                while (!listeEtatOuvert.isEmpty()) {
                    tmp = Main.printWaiting(tmp, System.currentTimeMillis());
                    Taquin x = listeEtatOuvert.poll();
                    //x.affiche();
                    listeEtatFerme.add(x);
                    x.createSucc(listeEtatOuvert, listeEtatFerme);
                    for (Taquin y : x.getSuccesseurs()) {
                        y.heuristique(listeEtatOuvert, listeEtatFerme);
                    }
                    x.getSuccesseurs().clear();
                }
            }else{
                System.out.println("Le taquin initial est déjà solution.");
            }

        } catch (
                OutOfMemoryError e) {
            System.out.println("\n\nErreur, il n'y a plus de memoire disponible :");
            e.printStackTrace();
        }

        System.out.println("\n\nExit in "+Long.toString((System.currentTimeMillis()-debut)/1000)+"s.");

    }

    static long printWaiting(long debut, long tmp) {
        if (tmp - debut > 5000) {
            System.out.println("Veuillez Patienter ...");
            return tmp;
        }

        return debut;
    }

}