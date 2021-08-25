import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.io.Serializable;

/*
 *  Program: Kolorowy edytor grafu
 *     Plik: Node.java
 *           
 *  Klasa Node reprezentuje wêz³y grafu na p³aszczyŸnie. 
 *            
 *    @autor: Artur Sobolewski
 *    @version:  styczeñ 2020 r.
 */

public class Node implements Serializable {

	private static final long serialVersionUID = 1L;
	/** Wspó³rzêdna x wêz³a. */
	protected int x;
	/** Wspó³rzêdna y wêz³a. */
	protected int y;
	/** Nazwa wêz³a. */
	protected String name;
	/** Promieñ ko³a reprezentuj¹cego wêzê³ grafu. */
	protected int r;
	/** Zmienna przechowuj¹ca kolor reprezentuj¹cego wêzê³ grafu. */
	private Color color;
	/** Pole, okreœlaj¹ce czcionkê wyœwietlanej nazwy wêz³a.*/ 
	Font font = new Font("MonoSpaced", Font.BOLD, 18);

	
	/**
	 * Konstruktor klasy <code>Node</code>, który pozwala wprowaciæ wspó³rzêdne tworzonego wêz³a.
	 * @param Wspó³rzêdna x wêz³a.
	 * @param Wspó³rzêdna y wêz³a.
	 */
	public Node(int x, int y) {
		this.x = x;
		this.y = y;
		this.r = 10;
		this.color = Color.WHITE;
		name = "";
	}

	/**
	 * Metoda umo¿liwiaj¹ca uzyskanie referencji do wzpó³rzêdnej x wêz³a.
	 * @return Wspó³rzêdna x wêz³a.
	 */
	public int getX() {
		return x;
	}
	/**
     * Metoda umo¿liwia ustawienie wspó³rzêdnej x wêz³a grafu.
     *
     * @param  wspó³rzêdna <code>x</code> wêz³a grafu.
     */
	public void setX(int x) {
		this.x = x;
	}

	/**
	 * Metoda umo¿liwiaj¹ca uzyskanie referencji do wzpó³rzêdnej y wêz³a.
	 * @return Wspó³rzêdna y wêz³a.
	 */
	public int getY() {
		return y;
	}

	/**
     * Metoda umo¿liwia ustawienie wspó³rzêdnej y wêz³a grafu.
     *
     * @param  wspó³rzêdna <code>y</code> wêz³a grafu.
     */
	public void setY(int y) {
		this.y = y;
	}

	/**
	 * Metoda umo¿liwiaj¹ca uzyskanie referencji do nazyw wêz³a.
	 * @return Nazwa <code>name</code> wêz³a grafu.
	 */
	public String getName() {
		return name;
	}

	/**
     * Metoda umo¿liwia ustawienie nazwy wêz³a grafu.
     *
     * @param  Nazwa <code>name</code> wêz³a grafu.
     */
	public void setName(String name) {
		this.name = name;
	}

	public int getR() {
		return r;
	}

	/**
     * Metoda umo¿liwia ustawienie promieñ ko³a reprezentuj¹cego wêzê³ grafu.
     *
     * @param  promieñ ko³a reprezentuj¹cego <code>r</code> wêzê³ grafu.
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
