import java.util.ArrayList;
import java.util.PriorityQueue;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

        PriorityQueue<Taquin> listeEtatOuvert = new PriorityQueue<Taquin>();
        PriorityQueue<Taquin> listeEtatFerme = new PriorityQueue<>();
		//Taquin à resoudre, etat initiale
		Taquin t = new Taquin();
		t.init(listeEtatOuvert, listeEtatFerme);
		listeEtatOuvert.add(t);

        int i=0;
        long debut = System.currentTimeMillis();
        long tmp = System.currentTimeMillis();


        System.out.println("Veuillez Patienter");
        try{
            while(!listeEtatOuvert.isEmpty()) {
                tmp = Main.printWaiting(tmp, System.currentTimeMillis());
                Taquin x = listeEtatOuvert.poll();
                //x.affiche();
                listeEtatFerme.add(x);
                x.createSucc(listeEtatOuvert, listeEtatFerme);
                for(Taquin y : x.getSuccesseurs()){
                    y.heuristique(listeEtatOuvert, listeEtatFerme);
                }
                x.getSuccesseurs().clear();
            }
            long fin = System.currentTimeMillis()-debut;
            System.out.println("\nEnd of calcul in "+fin+"ms");

        }catch(OutOfMemoryError e){
            System.out.println("\n\nErreur, il n'y a plus de memoire disponible :");
            e.printStackTrace();
        }


	}

    static long printWaiting(long debut, long tmp){
        if(tmp - debut > 5000){
            System.out.println("Veuillez Patienter ...");
            return tmp;
        }

        return debut;
    }

}
