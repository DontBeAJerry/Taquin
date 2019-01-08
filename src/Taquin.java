import java.time.Clock;
import java.util.ArrayList;

public class Taquin {


	private Case[][] grille;
	private ArrayList<Taquin> successeurs;
	private int profondeur;
	private Case saveCaseVide;
	Taquin pere;


	Taquin() {
		this.profondeur = 0;
		this.grille = new Case[3][3];
		this.successeurs = new ArrayList<Taquin>();
	}

	/**
	 * Initialisation du premier taquin
	 * Et lancement de la création des successeurs
	 */
	public void init(ArrayList<Taquin> ouvert, ArrayList<Taquin> ferme) {
		this.createFirstTaquin();
		System.out.println("Voici le taquin originel");
		this.affiche();
		this.createSucc(ouvert, ferme);
	}

	/**
	 * Copie d'un taquin
	 */
	private Taquin taquinSucc(Taquin t, Case c) {
		Taquin newT = new Taquin();
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				newT.grille[i][j] = new Case(t.getCase(i, j));
			}
		}
		newT.setSaveCaseVide(t.getSaveCaseVide());
		newT.setProfondeur(t.getProfondeur() + 1);
		newT.permut(c);

		return newT;
	}


	public void affiche() {
		System.out.println(" -----------");
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				if (this.grille[i][j].getVal() == 0) {
					System.out.print("|   ");
				} else {
					System.out.print("| " + this.grille[i][j].getVal() + " ");
				}

				if (j == 2) {
					System.out.println("|");
					System.out.println(" -----------");
				}

			}
		}
	}

	/**
	 * Cr�e le premier taquin non al�atoire
	 * Permet de tester avec un taquin connu � l'avance
	 */
	private void createFirstTaquin() {
		int k = 1;

		for (int i = 0; i < 3; i++) {

			for (int j = 0; j < 3; j++) {
				if (i == 2 && j == 0) {
					grille[i][j] = new Case(i, j, 0);
					saveCaseVide = grille[i][j];
				} else {
					grille[i][j] = new Case(i, j, k);
					k++;
				}

			}
		}
	this.pere = null;

	/*
		grille[0][0].setVal(3);
		grille[0][1].setVal(4);
		grille[0][2].setVal(8);
		grille[1][0].setVal(5);
		grille[1][1].setVal(7);
		grille[1][2].setVal(6);
		grille[2][0].setVal(1);
		grille[2][1].setVal(2);
		grille[2][2].setVal(0);
		saveCaseVide = grille[2][2];

		grille[0][0].setVal(1);
		grille[0][1].setVal(2);
		grille[0][2].setVal(3);
		grille[1][0].setVal(4);
		grille[1][1].setVal(0);
		grille[1][2].setVal(6);
		grille[2][0].setVal(7);
		grille[2][1].setVal(5);
		grille[2][2].setVal(8);
		saveCaseVide = grille[1][1];
	*/

		grille[0][0].setVal(4);
		grille[0][1].setVal(8);
		grille[0][2].setVal(5);
		grille[1][0].setVal(3);
		grille[1][1].setVal(6);
		grille[1][2].setVal(2);
		grille[2][0].setVal(7);
		grille[2][1].setVal(1);
		grille[2][2].setVal(0);
		saveCaseVide = grille[2][2];

	}

	/**
	 * Retourne la liste des coups jouables
	 */
	private ArrayList<Directions> listCoupJouable() {
		int x = this.getCaseVide().getX();
		int y = this.getCaseVide().getY();
		ArrayList<Directions> listDir = new ArrayList<Directions>();

		try {

			if (this.getCase(x + 1, y) != null) {
				listDir.add(Directions.BAS);
			}
		} catch (ArrayIndexOutOfBoundsException e) {
			//e.printStackTrace();
		}

		try {
			if (this.getCase(x - 1, y) != null) {
				listDir.add(Directions.HAUT);
			}
		} catch (ArrayIndexOutOfBoundsException e) {
			//e.printStackTrace();
		}

		try {
			if (this.getCase(x, y - 1) != null) {
				listDir.add(Directions.GAUCHE);
			}
		} catch (ArrayIndexOutOfBoundsException e) {
			//e.printStackTrace();
		}

		try {
			if (this.getCase(x, y + 1) != null) {
				listDir.add(Directions.DROITE);
			}
		} catch (ArrayIndexOutOfBoundsException e) {
			//e.printStackTrace();
		}

		//System.out.println("Liste coups jouables : " + listDir);
		return listDir;


	}

	/**
	 * Retourne la case associ�e � la direction donn�e
	 */
	private Case getCaseFromDirection(Directions dir) {
		int x = this.getCaseVide().getX();
		int y = this.getCaseVide().getY();

		switch (dir) {
			case HAUT:
				return this.getCase(x - 1, y);
			case BAS:
				return this.getCase(x + 1, y);
			case DROITE:
				return this.getCase(x, y + 1);
			case GAUCHE:
				return this.getCase(x, y - 1);
			default:
				return null;

		}
	}

	/**
	 * Permutte la case pleine et la case vide
	 * Simule un coup jou�
	 *
	 * @param c case pleine
	 */
	private void permut(Case c) {
		Case tmp = new Case(c);

		this.setCase(saveCaseVide, c.getX(), c.getY());
		this.setCase(tmp, saveCaseVide.getX(), saveCaseVide.getY());
		this.setSaveCaseVide(this.grille[c.getX()][c.getY()]);

		/*
		this.grille[c.getX()][c.getY()].setVal(0);
		this.grille[saveCaseVide.getX()][saveCaseVide.getY()].setVal(tmp.getVal());
		saveCaseVide = this.grille[c.getX()][c.getY()] ;
		*/
	}


	/**
	 * Recherche les coups jouables
	 * Copie le taquin parent, et cr�e les taquins enfants suite aux diff�rents coups jouables
	 * Cr�e la liste des successeur
	 */
	private void createSucc(ArrayList<Taquin> ouvert, ArrayList<Taquin> ferme) {
		//Pour chaque coup jouable, on cr�e le taquin correspondant
		for (Directions dir : this.listCoupJouable()) {
			//System.out.println("Direction : " + dir);
			//On r�cup�re la case correspondante au coup jouable
			Case c = this.getCaseFromDirection(dir);
			if (c != null) {
				//Cr�ation du taquin successeur, avec permutation des cases
				Taquin tSucc = new Taquin();
				tSucc = tSucc.taquinSucc(this, c);

				//Ajout du taquin � la liste des successeurs
				this.successeurs.add(tSucc);
				tSucc.pere = this;
				//Ajout du taquin � la liste des etats ouverts
				if(!tSucc.isEtatFerme(ferme))
					ouvert.add(tSucc);

				//tSucc.affiche();
				//System.out.println("****************");
			}
		}

	}

	public void afficheChemin(){
		if(this.pere != null){
			this.pere.afficheChemin();
		}
		this.affiche();
	}
	void comparaison(ArrayList<Taquin> ouvert, ArrayList<Taquin> ferme) {
		//TODO une fonction qui appel isEtatFerme(O,F) puis traite les cas différents
		//
	}

	/**
	 * Test si le taquin est deja existant dans la liste de taquin fermé
	 *
	 * @param ferme
	 * @return
	 */
	boolean isEtatFerme(ArrayList<Taquin> ferme) {
		for (Taquin t : ferme) {
			if (gridIsEquals(this.grille, t.grille))
				return true;
		}

		return false;

	}

	private boolean isSolution() {

		int tmp = 1;
		for (int i = 0; i < this.grille.length; i++) {
			for (int j = 0; j < this.grille.length; j++) {
				if (this.grille[i][j].getVal() != tmp && tmp < (grille.length*grille.length)) {
					return false;
				}
				tmp++;
			}
		}

		return true;
	}

	/**
	 * Permet de comparer les grilles de deux taquins pour savoir si elles sont
	 * égales
	 *
	 * @param grille
	 * @param grille2
	 * @return
	 */
	private boolean gridIsEquals(Case[][] grille, Case[][] grille2) {

		for (int i = 0; i < grille.length; i++) {
			for (int j = 0; j < grille.length; j++) {
				if (grille[i][j].getVal() != grille2[i][j].getVal()) {
					return false;
				}
			}
		}
		return true;
	}

	private Case getCase(int x, int y) {
		return grille[x][y];
	}

	private void setCase(Case c, int x, int y) {
		this.grille[x][y].setVal(c.getVal());
	}

	private Case getCaseVide() {
		return saveCaseVide;
	}

	private int getProfondeur() {
		return profondeur;
	}

	private void setProfondeur(int p) {
		this.profondeur = p;
	}

	private Case getSaveCaseVide() {
		return saveCaseVide;
	}

	private void setSaveCaseVide(Case saveCaseVide) {
		this.saveCaseVide = saveCaseVide;
	}

	public ArrayList<Taquin> getSuccesseurs() {
		return this.successeurs;
	}

	/**
	 * @param listeEtatOuvert
	 * @param listeEtatFerme
	 * @return
	 */
	public boolean routine(ArrayList<Taquin> listeEtatOuvert, ArrayList<Taquin> listeEtatFerme) {
		//System.out.println("Profondeur : "+this.getProfondeur()+" : ");
		if (this.isSolution()) {
			System.out.println("\nVoici la solution, de profondeur "+this.getProfondeur()+" : ");
			return true;
		} else if (this.isEtatFerme(listeEtatFerme)) {
			listeEtatOuvert.remove(this);
		} else {
			listeEtatOuvert.remove(this);
			this.createSucc(listeEtatOuvert, listeEtatFerme);
			listeEtatFerme.add(this);

		}

		return false;
	}
}
