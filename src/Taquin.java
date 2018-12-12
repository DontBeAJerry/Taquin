import java.util.ArrayList;
import java.util.List;

public class Taquin {


	private Case[][] grille;
	private ArrayList<Taquin> successeurs ;
	private int profondeur; 
	private Case saveCaseVide;


	Taquin(){
		this.profondeur = 0; 
		this.grille = new Case[3][3];
		this.successeurs = new ArrayList<Taquin>();
	}
	
	/**
	 * Initialisation du premier taquin
	 */
	public void init() {
		this.createFirstTaquin();
		System.out.println("Voici le taquin originel");
		this.affiche();
		
		this.createSucc();
	}
	
	/**
	 * Copie d'un taquin
	 */
	private static Taquin taquinSucc(Taquin t, Case c) {
		Taquin newT = new Taquin();
		for(int i = 0; i<3; i++) {
			for(int j = 0; j<3; j++) {
				newT.grille[i][j] = new Case(t.getCase(i, j));
			}
		}
		newT.setProfondeur(t.getProfondeur()+1);
		newT.permut(c);
		
		return newT;
	}
	
	
	public void affiche() {
		System.out.println(" -----------");
		for(int i = 0; i<3; i++) {
			for(int j = 0; j<3; j++) {
				if(this.grille[i][j].getVal() == 0) {
					System.out.print("|   ");
				}else {
				System.out.print("| " + this.grille[i][j].getVal() + " ");
				}
				
				if(j==2) {
					System.out.println("|");
					System.out.println(" -----------");
				}

			}
		}
	}
	
	/** 
	 * Crée le premier taquin non aléatoire
	 * Permet de tester avec un taquin connu à l'avance
	 */
	private void createFirstTaquin() {
		int k = 1; 
		for(int i = 0; i<3; i++) {
			for(int j = 0; j<3; j++) {
				if(i==2 && j==0) {
					grille[i][j] = new Case(i,j,0);
					saveCaseVide = grille[i][j];
				}else {
					grille[i][j]= new Case(i,j,k);
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
			
			if(this.getCase(x+1, y) != null) {
				listDir.add(Directions.BAS);
			}
		}catch(ArrayIndexOutOfBoundsException e) {
			e.printStackTrace();
		}
		
		try {
			if (this.getCase(x-1, y) != null) {
				listDir.add(Directions.HAUT);
			}
		}catch(ArrayIndexOutOfBoundsException e) {
			e.printStackTrace();
		}
		
		try {			
			if(this.getCase(x, y-1) != null) {
				listDir.add(Directions.GAUCHE);
			}
		}catch(ArrayIndexOutOfBoundsException e) {
			e.printStackTrace();
		}
		
		try {
			if(this.getCase(x, y+1) != null) {
				listDir.add(Directions.DROITE);
			}
		}catch(ArrayIndexOutOfBoundsException e) {
			e.printStackTrace();
		}
			
		System.out.println("Liste coups jouables : "+listDir);
		return listDir;
		
		
	}
	
	/**
	 * Retourne la case associée à la direction donnée
	 */
	private Case getCaseFromDirection(Directions dir) {
		int x = this.getCaseVide().getX();
		int y = this.getCaseVide().getY();
		
		switch(dir) {
		case HAUT : 
			return this.getCase(x-1, y);
		case BAS : 
			return this.getCase(x+1, y);
		case DROITE : 
			return this.getCase(x, y+1);
		case GAUCHE : 
			return this.getCase(x, y-1);
		default :
			return null;
			
		}
	}
	
	/**
	 * Permutte la case pleine et la case vide
	 * Simule un coup joué
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
	 * Copie le taquin parent, et crée les taquins enfants suite aux différents coups jouables
	 * Crée la liste des successeur
	 */
	private void createSucc() {
		//Pour chaque coup jouable, on crée le taquin correspondant
		for (Directions dir : this.listCoupJouable()) {
			System.out.println("Direction : "+dir);
			//On récupère la case correspondante au coup jouable
			Case c = this.getCaseFromDirection(dir);
			if (c != null) {
				System.out.println("Oui" + c.getX() + c.getY() + c.getVal());
				this.affiche();
				//Création du taquin successeur, avec permutation des cases
				Taquin tSucc = Taquin.taquinSucc(this, c);
				
				//Ajout du taquin à la liste des successeurs
				//this.successeurs.add(tSucc);
				tSucc.affiche();
				
				System.out.println("****************");
			}
		}
		
	}
	
	public Case getCase(int x , int y) {
		return grille[x][y];
	}
	
	public void setCase(Case c, int x, int y) {
		this.grille[x][y].setVal(c.getVal()); 
		this.grille[x][y].setX(c.getX());
		this.grille[x][y].setY(c.getY());
		}
	
	public Case getCaseVide() {
		return saveCaseVide;
	}
	public int getProfondeur() {
		return profondeur;
	}
	
	public void setProfondeur(int p) {
		this.profondeur = p;
	}

	public Case getSaveCaseVide() {
		return saveCaseVide;
	}

	public void setSaveCaseVide(Case saveCaseVide) {
		this.saveCaseVide = saveCaseVide;
	}
	
	
}
