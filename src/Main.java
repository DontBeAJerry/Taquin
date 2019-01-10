import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        // TODO Vérifier si impossible

        PriorityQueue<Taquin> listeEtatOuvert = new PriorityQueue<Taquin>();
        PriorityQueue<Taquin> listeEtatFerme = new PriorityQueue<>();

        choixTailleTaquin(listeEtatOuvert, listeEtatFerme);


    }



    static void choixTailleTaquin(PriorityQueue<Taquin> listeEtatOuvert, PriorityQueue<Taquin> listeEtatFerme){
        boolean sortir = false;
        while(!sortir) {
            boolean saisieDurcie = false;

            switch (choixTaquin()) {
                case 1:
                    Taquin t = new Taquin(3);
                    int choix = choixHeuristique();
                    t.init(listeEtatOuvert, listeEtatFerme, choix);
                    listeEtatOuvert.add(t);
                    routine(listeEtatOuvert, listeEtatFerme, t, choix);
                    break;
                case 2:
                    Taquin t1 = new Taquin(4);
                    int choix1 = choixHeuristique();
                    t1.init(listeEtatOuvert, listeEtatFerme, choix1);
                    routine(listeEtatOuvert, listeEtatFerme, t1, choix1);
                    listeEtatOuvert.add(t1);
                    break;
                case 0 :
                    sortir = true;
                    break;
                default:
                    System.out.println("Saisie incorrect, veuillez recommencer.");
                    saisieDurcie = true;
                    break;
            }

            if(!saisieDurcie) {
                System.out.println("Voulez vous recommencer sur un autre taquin?");
                System.out.println("\t1 : Oui");
                System.out.println("\t2 : Non (Fin du programme)");
                Scanner sc = new Scanner(System.in);
                int quit = sc.nextInt();
                if (quit == 2) {
                    sortir = true;
                }
            }
            listeEtatFerme.clear();
            listeEtatOuvert.clear();
        }
    }
    static long printWaiting(long debut, long tmp) {
        if (tmp - debut > 5000) {
            System.out.println("Veuillez Patienter ...");
            return tmp;
        }

        return debut;
    }

    static void routine(PriorityQueue<Taquin> listeEtatOuvert, PriorityQueue<Taquin> listeEtatFerme, Taquin t, int choix){
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
                    x.createSucc(listeEtatOuvert, listeEtatFerme, choix);
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

        System.out.println("*******************************\n\tSolution trouvee en "+Long.toString((System.currentTimeMillis()-debut)/1000)+"s.\n*******************************\n\n");
    }

    static int choixTaquin(){
        System.out.println("Veuillez choisir une taille de taquin :");
        System.out.println("\t1 : 3x3");
        System.out.println("\t2 : 4x4");
        System.out.println("\t0 : Sortir");
        Scanner sc = new Scanner(System.in);
        int choix = sc.nextInt();

        return choix;
    }

    static int choixHeuristique(){
        System.out.println("Veuillez choisir une heuristique :");
        System.out.println("\t1 : Mal Place");
        System.out.println("\t2 : Distance de Manhanattan");
        System.out.println("\t3 : Heuristique 0");
        System.out.println("\t0 : Sortir");
        Scanner sc = new Scanner(System.in);
        int choix = sc.nextInt();

        return choix;
    }
}