import java.util.ArrayList;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		ArrayList<Taquin> listeEtatOuvert = new ArrayList<>();
		ArrayList<Taquin> listeEtatFerme = new ArrayList<>();

		//Taquin à resoudre, etat initiale
		Taquin t = new Taquin();
		listeEtatOuvert.add(t);
		t.init(listeEtatOuvert, listeEtatFerme);

		System.out.println(t.isEtatFerme(listeEtatOuvert,listeEtatFerme));


	}

}
