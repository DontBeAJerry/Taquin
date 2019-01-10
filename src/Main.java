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
		if(t.taquinPossible()) {
		System.out.println("c'est bon");
		}
		else
			System.out.println("c'est pas bon");
	/*	listeEtatOuvert.add(t);


		//System.out.println("1");
		while(!listeEtatOuvert.isEmpty()) {
			//System.out.println(t.isEtatFerme(listeEtatFerme));
			Taquin x = listeEtatOuvert.poll();
			//x.affiche();
			listeEtatFerme.add(x);
			x.createSucc(listeEtatOuvert, listeEtatFerme);
			for(Taquin y : x.getSuccesseurs()){
			    y.heuristique(listeEtatOuvert, listeEtatFerme);
            }
			x.getSuccesseurs().clear();
		}
		*/
		System.out.println("Fin.");

	}






}
