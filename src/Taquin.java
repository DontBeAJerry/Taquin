import java.util.ArrayList;

public class Taquin {


	private Case[][] grille;
	private ArrayList<Taquin> successeurs;
	private int profondeur;
	private Case saveCaseVide;


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
		System.out.println("Vesseurs; oici le taquin originel");
		this.affiche();

		this.createSucc();
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


	private void affiche() {
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

		System.out.println("Liste coups jouables : " + listDir);
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
	private void createSucc() {
		//Pour chaque coup jouable, on cr�e le taquin correspondant
		for (Directions dir : this.listCoupJouable()) {
			System.out.println("Direction : " + dir);
			//On r�cup�re la case correspondante au coup jouable
			Case c = this.getCaseFromDirection(dir);
			if (c != null) {
				//Affiche le Taquin Parent
				//this.affiche();
				//Cr�ation du taquin successeur, avec permutation des cases
				Taquin tSucc = new Taquin();
				tSucc = tSucc.taquinSucc(this, c);

				//Ajout du taquin � la liste des successeurs
				this.successeurs.add(tSucc);
				tSucc.affiche();

				System.out.println("****************");
			}
		}

	}
	
	void comparaison (ArrayList<Taquin> ouvert , ArrayList<Taquin> ferme) {
		//TODO une fonction qui appel isEtatFerme(O,F) puis traite les cas différents
		//
	}
	
	/**
	 * Test si le taquin est deja existant dans la liste de taquin fermé
	 * @param ouvert
	 * @param ferme
	 * @return
	 */
	boolean isEtatFerme(ArrayList<Taquin> ouvert, ArrayList<Taquin> ferme) {
		for (Taquin t : ferme) {
			//TODO Vérifier l'egalite
			if (!gridIsEquals(this.grille, t.grille))
				return false;
		}

		return true;

	}
	
	/**
	 * Permet de comparer les grilles de deux taquins pour savoir si elles sont 
	 * égales
	 * @param grille
	 * @param grille2
	 * @return
	 */
	private boolean gridIsEquals(Case[][] grille, Case[][] grille2) {

		for (int i = 0; i < grille.length; i++) {
			for (int j = 0; j < grille.length; j++) {
				if (grille[i][j] != grille2[i][j]) {
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


}
