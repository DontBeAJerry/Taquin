
public class Case {

	//Coordonn�es de la case
	private int x; 
	private int y;
	//Valeur de la case
	private int val;
	
	public Case (Case c) {
		this.x = c.getX();
		this.y = c.getY();
		this.val = c.getVal();
	}
	public Case(int x, int y, int val) {
		this.x = x;
		this.y = y;
		this.val = val;
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public int getVal() {
		return val;
	}

	public void setVal(int val) {
		this.val = val;
	}
	
	
	
	
	
}
