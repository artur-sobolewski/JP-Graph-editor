import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.io.Serializable;

/*
 *  Program: Kolorowy edytor grafu
 *     Plik: Node.java
 *           
 *  Klasa Node reprezentuje w�z�y grafu na p�aszczy�nie. 
 *            
 *    @autor: Artur Sobolewski
 *    @version:  stycze� 2020 r.
 */

public class Node implements Serializable {

	private static final long serialVersionUID = 1L;
	/** Wsp�rz�dna x w�z�a. */
	protected int x;
	/** Wsp�rz�dna y w�z�a. */
	protected int y;
	/** Nazwa w�z�a. */
	protected String name;
	/** Promie� ko�a reprezentuj�cego w�z� grafu. */
	protected int r;
	/** Zmienna przechowuj�ca kolor reprezentuj�cego w�z� grafu. */
	private Color color;
	/** Pole, okre�laj�ce czcionk� wy�wietlanej nazwy w�z�a.*/ 
	Font font = new Font("MonoSpaced", Font.BOLD, 18);

	
	/**
	 * Konstruktor klasy <code>Node</code>, kt�ry pozwala wprowaci� wsp�rz�dne tworzonego w�z�a.
	 * @param Wsp�rz�dna x w�z�a.
	 * @param Wsp�rz�dna y w�z�a.
	 */
	public Node(int x, int y) {
		this.x = x;
		this.y = y;
		this.r = 10;
		this.color = Color.WHITE;
		name = "";
	}

	/**
	 * Metoda umo�liwiaj�ca uzyskanie referencji do wzp�rz�dnej x w�z�a.
	 * @return Wsp�rz�dna x w�z�a.
	 */
	public int getX() {
		return x;
	}
	/**
     * Metoda umo�liwia ustawienie wsp�rz�dnej x w�z�a grafu.
     *
     * @param  wsp�rz�dna <code>x</code> w�z�a grafu.
     */
	public void setX(int x) {
		this.x = x;
	}

	/**
	 * Metoda umo�liwiaj�ca uzyskanie referencji do wzp�rz�dnej y w�z�a.
	 * @return Wsp�rz�dna y w�z�a.
	 */
	public int getY() {
		return y;
	}

	/**
     * Metoda umo�liwia ustawienie wsp�rz�dnej y w�z�a grafu.
     *
     * @param  wsp�rz�dna <code>y</code> w�z�a grafu.
     */
	public void setY(int y) {
		this.y = y;
	}

	/**
	 * Metoda umo�liwiaj�ca uzyskanie referencji do nazyw w�z�a.
	 * @return Nazwa <code>name</code> w�z�a grafu.
	 */
	public String getName() {
		return name;
	}

	/**
     * Metoda umo�liwia ustawienie nazwy w�z�a grafu.
     *
     * @param  Nazwa <code>name</code> w�z�a grafu.
     */
	public void setName(String name) {
		this.name = name;
	}

	public int getR() {
		return r;
	}

	/**
     * Metoda umo�liwia ustawienie promie� ko�a reprezentuj�cego w�z� grafu.
     *
     * @param  promie� ko�a reprezentuj�cego <code>r</code> w�z� grafu.
     */
	public void setR(int r) {
		this.r = r;
	}

	public Color getColor() {
		return color;
	}

	public void setColor(Color color) {
		this.color = color;
	}

	public boolean isMouseOver(int mx, int my) {
		return (x - mx) * (x - mx) + (y - my) * (y - my) <= r * r;
	}

	void draw(Graphics2D g) {
		g.setColor(color);
		g.fillOval(x - r, y - r, 2 * r, 2 * r);
		g.setColor(Color.BLACK);
		BasicStroke grubaLinia = new BasicStroke(3);
		g.setStroke(grubaLinia);
		g.drawOval(x - r, y - r, 2 * r, 2 * r);
		g.setFont(font);
		g.drawString(name, x - 5 * name.length(), y + 5);
	}

	@Override
	public String toString() {
		return name + "(X=" + x + ", Y=" + y + ", r=" + r + "), color=" + this.color.getRGB();
	}

}
