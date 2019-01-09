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
		//System.out.println(t.isEtatFerme(listeEtatFerme));
		while(!listeEtatOuvert.isEmpty()) {
			Taquin x = listeEtatOuvert.poll();
			listeEtatFerme.add(x);
			for(Taquin f : x.getSuccesseurs()){
				f.createSucc(listeEtatOuvert, listeEtatFerme);
			}

		}
		System.out.println("Fin");

	}




}
