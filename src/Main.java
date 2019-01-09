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

		int i = 0;
		System.out.println("1");
		while(!listeEtatOuvert.isEmpty()) {
			//System.out.println(t.isEtatFerme(listeEtatFerme));
			Taquin x = listeEtatOuvert.poll();
			listeEtatFerme.add(x);
			x.createSucc(listeEtatOuvert, listeEtatFerme);
			x.affiche();
		}
		System.out.println("Fin");

	}




}
