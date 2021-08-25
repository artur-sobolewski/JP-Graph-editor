import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.Line2D;
import java.io.Serializable;

/*
 * Program: Kolorowy edytor grafu
 * Plik: Edge.java
 * 
 * Klasa Edge reprezentuje krawêdzie grafu na p³aszczyŸnie. 
 * 
 * Autor: Artur Sobolewski
 * Data: styczeñ 2020 r.
 */
public class Edge implements Serializable {

	private static final long serialVersionUID = 1L;
	private Color color;
	private Node nodeFrom;
	private Node nodeTo;

	public Edge(final Node nodeFrom, final Node nodeTo) {
		this.nodeFrom = nodeFrom;
		this.nodeTo = nodeTo;
		this.color = Color.BLACK;
	}

	public Node getNodeFrom() {
		return nodeFrom;
	}

	public void setNodeFrom(Node nodeFrom) {
		this.nodeFrom = nodeFrom;
	}

	public Node getNodeTo() {
		return nodeTo;
	}

	public void setNodeTo(Node nodeTo) {
		this.nodeTo = nodeTo;
	}

	public Color getColor() {
		return color;
	}

	public void setColor(Color color) {
		this.color = color;
	}

	public boolean isMouseOver(int mx, int my) {
		final double dmin = 4.0;
		if ((mx > this.nodeFrom.getX() && mx > this.nodeTo.getX())
				|| (mx < this.nodeFrom.getX() && mx < this.nodeTo.getX())) {
			return false;
		}
		if ((my > this.nodeFrom.getY() && my > this.nodeTo.getY())
				|| (my < this.nodeFrom.getY() && my < this.nodeTo.getY())) {
			return false;
		}
		final int A = this.nodeTo.getY() - this.nodeFrom.getY();
		final int B = this.nodeFrom.getX() - this.nodeTo.getX();
		final int C = this.nodeTo.getX() * this.nodeFrom.getY() - this.nodeFrom.getX() * this.nodeTo.getY();
		final double d = Math.abs(A * mx + B * my + C) / Math.sqrt(A * A + B * B);
		if (d <= dmin) {
			return true;
		}
		return false;
	}

	void draw(final Graphics2D g) {
		g.setColor(this.color);
		Line2D linia = new Line2D.Float(this.nodeFrom.getX(), this.nodeFrom.getY(), this.nodeTo.getX(),
				this.nodeTo.getY());
		BasicStroke grubaLinia = new BasicStroke(3);
		g.setStroke(grubaLinia);
		g.setColor(color);
		g.draw(linia);
	}

	@Override
	public String toString() {
		return nodeFrom + "==>" + nodeTo + ", color=" + this.color.getRGB();
	}

}
