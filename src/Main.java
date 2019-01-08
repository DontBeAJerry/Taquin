import java.util.ArrayList;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		ArrayList<Taquin> listeEtatOuvert = new ArrayList<>();
		ArrayList<Taquin> listeEtatFerme = new ArrayList<>();

		//Taquin à resoudre, etat initiale
		Taquin t = new Taquin();

		//Todo Vérifier Taquin Init n'est pas solution

		t.init(listeEtatOuvert, listeEtatFerme);
		listeEtatFerme.add(t);
		int i = 0;

		System.out.println(t.isEtatFerme(listeEtatFerme));
		while(!listeEtatOuvert.isEmpty()) {
			Taquin x = listeEtatOuvert.get(0);
			if (x.routine(listeEtatOuvert, listeEtatFerme)){
				x.affiche();
				System.out.println("Ca marche");
				listeEtatOuvert.clear();
			}
			i++;
		}
		System.out.println(i);


	}




}
