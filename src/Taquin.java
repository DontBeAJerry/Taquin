import java.util.PriorityQueue;

public class Taquin implements Comparable<Taquin>{


	private Case[][] grille;
	private PriorityQueue<Taquin> successeurs;
	private int profondeur;
	private Case saveCaseVide;
	private int priority;
	Taquin pere;


	Taquin() {
		this.profondeur = 0;
		this.priority = 0;
		this.grille = new Case[4][4];
		this.successeurs = new PriorityQueue<Taquin>();
	}

	/**
	 * Initialisation du premier taquin
	 * Et lancement de la création des successeurs
	 */
	public void init(PriorityQueue<Taquin> ouvert, PriorityQueue<Taquin> ferme) {
		this.createFirstTaquin();
		System.out.println("Voici le taquin originel");
		this.affiche();
		//this.createSucc(ouvert, ferme);
	}

	/**
	 * Copie d'un taquin
	 */
	private Taquin taquinSucc(Taquin t, Case c) {
		Taquin newT = new Taquin();
		for (int i = 0; i < this.grille.length; i++) {
			for (int j = 0; j < this.grille.length; j++) {
				newT.grille[i][j] = new Case(t.getCase(i, j));
			}
		}
		newT.setSaveCaseVide(t.getSaveCaseVide());
		newT.setProfondeur(t.getProfondeur() + 1);
		newT.setPriority(newT.heuristiqueChoix(2));
		newT.permut(c);

		return newT;
	}


	public void affiche() {
		System.out.println(" ----------------");
		for (int i = 0; i < this.grille.length; i++) {
			for (int j = 0; j < this.grille.length; j++) {
				if (this.grille[i][j].getVal() == 0) {
					System.out.print("|   ");
				} else {
					System.out.print("| " + this.grille[i][j].getVal() + " ");
				}

				if (j == 3) {
					System.out.println("|");
					System.out.println(" ----------------");
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

		for (int i = 0; i < this.grille.length; i++) {

			for (int j = 0; j < this.grille.length; j++) {
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
		this.setPriority(this.heuristiqueChoix(2));

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
*/
		
		grille[0][0].setVal(1);
		grille[0][1].setVal(2);
		grille[0][2].setVal(3);
		grille[0][3].setVal(4);
		grille[1][0].setVal(5);
		grille[1][1].setVal(6);
		grille[1][2].setVal(7);
		grille[1][3].setVal(8);
		grille[2][0].setVal(9);
		grille[2][1].setVal(10);
		grille[2][2].setVal(11);
		grille[2][3].setVal(12);
		grille[3][0].setVal(0);
		grille[3][1].setVal(13);
		grille[3][2].setVal(14);
		grille[3][3].setVal(15);
		saveCaseVide = grille[3][0];
/*

		grille[0][0].setVal(2);
		grille[0][1].setVal(7);
		grille[0][2].setVal(4);
		grille[1][0].setVal(3);
		grille[1][1].setVal(6);
		grille[1][2].setVal(8);
		grille[2][0].setVal(5);
		grille[2][1].setVal(1);
		grille[2][2].setVal(0);
		saveCaseVide = grille[2][2];
	*/	
}

	/**
	 * Retourne la liste des coups jouables
	 */
	private PriorityQueue<Directions> listCoupJouable() {
		int x = this.getCaseVide().getX();
		int y = this.getCaseVide().getY();
		PriorityQueue<Directions> listDir = new PriorityQueue<Directions>();

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
	public void createSucc(PriorityQueue<Taquin> ouvert, PriorityQueue<Taquin> ferme) {
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
				this.setPriority(this.heuristiqueChoix(2));



			}

		}
	}

	public void heuristique(PriorityQueue<Taquin> ouvert, PriorityQueue<Taquin> ferme){
		if (!this.isSolution()) {
			//Ajout du taquin � la liste des etats
			Taquin isFerme = this.isEtatFerme(ferme);
			Taquin isOuvert = this.isEtatFerme(ouvert);

			if (isFerme == null && isOuvert == null) {
				ouvert.add(this);
			} else if (isOuvert != null) {
				if (isOuvert.getPriority() > this.getPriority()) {
					ouvert.remove(isOuvert);
					ouvert.add(this);
				}
			} else if (isFerme != null) {
				if (isFerme.getPriority() > this.getPriority()) {
					ferme.remove(isFerme);
					ouvert.add(this);
				}

			}
		}else{
			System.out.println("Voici la solution de profondeur "+this.getProfondeur()+" :");
			this.afficheChemin();
			ouvert.clear();
		}
	}

	public void afficheChemin(){
		if(this.pere != null){
			this.pere.afficheChemin();
		}
		this.affiche();
	}

	/**
	 * Test si le taquin est deja existant dans la liste de taquin fermé
	 *
	 * @param ferme
	 * @return
	 */
	Taquin isEtatFerme(PriorityQueue<Taquin> ferme) {
		for (Taquin t : ferme) {
			if (gridIsEquals(this.grille, t.grille))
				return t;
		}

		return null;

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

	private int nbMalPlace() {

		int tmp = 1;
		int compteur = 0;
		for (int i = 0; i < this.grille.length; i++) {
			for (int j = 0; j < this.grille.length; j++) {
				if (this.grille[i][j].getVal() != tmp && tmp < (grille.length*grille.length) && this.grille[i][j].getVal()!=0) {
					compteur++;
				}
				tmp++;
			}
		}

		return compteur;
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

	public PriorityQueue<Taquin> getSuccesseurs() {
		return this.successeurs;
	}

	public void setSuccesseurs(PriorityQueue<Taquin> successeurs) {
		this.successeurs = successeurs;
	}

	public void addSuccesseur(Taquin succ){
		this.successeurs.add(succ);
	}

	public int getPriority(){
		return  this.priority;
	}

	public void setPriority(int x){
		this.priority=x;
	}


	@Override
	public int compareTo(Taquin o) {
		if(this.priority > o.priority) {
			return 1;
		} else if(this.priority == o.priority){
			return 0;
		}
		return -1;
	}

	private int heuristiqueMalPlace(){
		int x = this.profondeur+this.nbMalPlace();
		return x;
	}

	private int heuristiqueChoix(int choix){
		switch(choix){
			case 1:
				return this.heuristiqueMalPlace();
			case 2 :
				return this.heuristiqueManhantan();
			default :
				return 0;
		}
	}

	public int heuristiqueManhantan(){
		int value[] = {1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,0};
		int index = 0;
		int manhatan = 0;
		//Parcourt de la solution
		for(int i = 0; i<this.grille.length;i++){
			for(int j=0; j<this.grille.length; j++){
				int x = 0;
				int y = 0;
				boolean trouve = false;

				while(x < this.grille.length && !trouve){
					while(y < this.grille.length && !trouve){
						if (this.grille[x][y].getVal() == value[index]) {
							trouve = true;
							manhatan= manhatan+distanceManhatan(x,y,i,j);
						}
						y++;
					}
					x++;
				}
			index++;
			}

		}

		return this.getProfondeur()+manhatan;
	}

	private int distanceManhatan(int x, int y, int i, int j){
		return Math.abs(x-i) + Math.abs(y-j);
	}

	private void test(){
		//case solution[] = { (0,0),()	};

		for(int i=1; i<this.grille.length;i++){

		}
	}

}


