import java.awt.Graphics2D;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/*
 * Program: Kolorowy edytor grafu
 * Plik: Graph.java
 * 
 * Klasa Graph reprezentuje graf na p³aszczyŸnie. 
 * 
 * Autor: Artur Sobolewski
 * Data: styczeñ 2020 r.
 */

class GraphException extends Exception {
	private static final long serialVersionUID = 1L;

	public GraphException(String message) {
		super(message);
	}
}

public class Graph implements Serializable {

	private static final long serialVersionUID = 1L;

	private List<Edge> edges;
	private List<Node> nodes;

	public Graph() {
		this.nodes = new ArrayList<Node>();
		this.edges = new ArrayList<Edge>();
	}

	public void addNode(Node node) {
		nodes.add(node);
	}

	public void removeNode(Node node) {
		nodes.remove(node);
	}

	public void addEdge(Edge edge) {
		edges.add(edge);
	}

	public void removeEdge(Edge edge) {
		edges.remove(edge);
	}

	public Node[] getNodes() {
		Node[] array = new Node[0];
		return nodes.toArray(array);
	}

	public Edge[] getEdges() {
		Edge[] array = new Edge[0];
		return edges.toArray(array);
	}

	public void draw(Graphics2D g) {
		for (Edge edge : edges) {
			edge.draw(g);
		}
		for (Node node : nodes) {
			node.draw(g);
		}
	}

	public static void printToFileBin(String file_name, Graph graph) throws GraphException {

		try (ObjectOutputStream output = new ObjectOutputStream(new FileOutputStream(file_name))) {
			output.writeObject(graph);

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static Graph readFromFileBin(String file_name) throws GraphException {

		try (ObjectInputStream input = new ObjectInputStream(new FileInputStream(file_name))) {
			Graph readLaptop = (Graph) input.readObject();
			return readLaptop;
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
}
