import java.util.ArrayList;
import java.util.PriorityQueue;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

        PriorityQueue<Taquin> listeEtatOuvert = new PriorityQueue<Taquin>();
        PriorityQueue<Taquin> listeEtatFerme = new PriorityQueue<>();

		//Taquin à resoudre, etat initiale
		Taquin t = new Taquin();


		//Todo Vérifier Taquin Init n'est pas solution

		t.init(listeEtatOuvert, listeEtatFerme);
		System.out.println(t.getPriority());
		listeEtatFerme.add(t);
		int i = 0;

		//System.out.println(t.isEtatFerme(listeEtatFerme));
		while(!listeEtatOuvert.isEmpty()) {
			Taquin x = listeEtatOuvert.poll();
			if (x.routine(listeEtatOuvert, listeEtatFerme)){
				x.afficheChemin();
				listeEtatOuvert.clear();
			}

			i++;
		}


	}




}
