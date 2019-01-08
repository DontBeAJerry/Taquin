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

		System.out.println(t.isEtatFerme(listeEtatFerme));


		if(t.routine(listeEtatOuvert, listeEtatFerme,0)){
			System.out.println("Ca marche");
		}else{
			System.out.println("Ca marche pas :(");
		}


	}




}
