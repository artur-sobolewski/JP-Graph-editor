import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

/* 
 *  Program: Kolorowy edytor grafu
 *     Plik: GraphEditor.java
 *           
 *  Klasa GraphEditor implementuje okno g³ówne
 *  dla graficznego edytora grafu.  
 *            
 *    Autor: Artur Sobolewski
 *     Data:  styczeñ 2020 r.
 */

public class GraphEditor extends JFrame implements ActionListener {

	private static final long serialVersionUID = 1L;

	private static final String APP_AUTHOR = "Autor: Artur Sobolewski\n  Data: styczeñ 2020";
	private static final String APP_TITLE = "Kolorowy edytor grafów";
	Graph currentGraph = new Graph();

	private static final String APP_INSTRUCTION = "                  O P I S   P R O G R A M U \n\n"
			+ "Aktywna klawisze:\n" + "   strza³ki ==> przesuwanie wszystkich kó³\n"
			+ "   SHIFT + strza³ki ==> szybkie przesuwanie wszystkich kó³\n\n" + "ponadto gdy kursor wskazuje ko³o:\n"
			+ "   DEL   ==> kasowanie ko³a\n" + "   +, -   ==> powiêkszanie, pomniejszanie ko³a\n"
			+ "   r,g,b ==> zmiana koloru ko³a\n\n" + "Operacje myszka:\n"
			+ "   przeci¹ganie ==> przesuwanie wszystkich kó³\n"
			+ "   PPM ==> tworzenie nowego ko³a w niejscu kursora\n" + "ponadto gdy kursor wskazuje ko³o:\n"
			+ "   przeci¹ganie ==> przesuwanie ko³a\n" + "   PPM ==> zmiana koloru ko³a\n"
			+ "                   lub usuwanie ko³a\n";

	public static void main(String[] args) {
		new GraphEditor();
	}

	// private GraphBase graph;
	private JMenuBar menuBar = new JMenuBar();
	private JMenu menuFile = new JMenu("File");
	private JMenu menuGraph = new JMenu("Graph");
	private JMenu menuHelp = new JMenu("Help");
	private JMenuItem menuNew = new JMenuItem("New", KeyEvent.VK_N);
	private JMenuItem menuShowExample = new JMenuItem("Example", KeyEvent.VK_X);
	private JMenuItem menuExit = new JMenuItem("Exit", KeyEvent.VK_E);
	private JMenuItem menuListOfNodes = new JMenuItem("List of Nodes", KeyEvent.VK_N);
	private JMenuItem menuListOfEdges = new JMenuItem("List of Edges", KeyEvent.VK_E);
	private JMenuItem menuAuthor = new JMenuItem("Author", KeyEvent.VK_A);
	private JMenuItem menuInstruction = new JMenuItem("Instruction", KeyEvent.VK_I);
	private JMenuItem menuSaveToFile = new JMenuItem("Save to file", KeyEvent.VK_S);
	private JMenuItem menuReadFromFile = new JMenuItem("Read from file", KeyEvent.VK_R);

	private GraphPanel panel = new GraphPanel();

	public GraphEditor() {
		super(APP_TITLE);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setSize(400, 400);
		setLocationRelativeTo(null);
		setContentPane(panel);
		createMenu();
		showBuildinExample();
		setVisible(true);
	}

	private void showListOfNodes(Graph graph) {
		Node array[] = graph.getNodes();
		int i = 0;
		StringBuilder message = new StringBuilder("Liczba wêz³ów: " + array.length + "\n");
		for (Node node : array) {
			message.append(node + "\n");
			if (++i % 5 == 0)
				message.append("\n");
		}
		JOptionPane.showMessageDialog(this, message, APP_TITLE + " - Lista wêz³ów", JOptionPane.PLAIN_MESSAGE);
	}

	private void showListOfEdges(Graph graph) {
		Edge array[] = graph.getEdges();
		int i = 0;
		StringBuilder message = new StringBuilder("Liczba krawêdzi: " + array.length + "\n");
		for (Edge node : array) {
			message.append(node + " \n");
			if (++i % 5 == 0)
				message.append("\n");
		}
		JOptionPane.showMessageDialog(this, message, APP_TITLE + " - Lista krawêdzi", JOptionPane.PLAIN_MESSAGE);
	}

	private void createMenu() {
		menuNew.addActionListener(this);
		menuShowExample.addActionListener(this);
		menuExit.addActionListener(this);
		menuListOfNodes.addActionListener(this);
		menuListOfEdges.addActionListener(this);
		menuAuthor.addActionListener(this);
		menuInstruction.addActionListener(this);
		menuSaveToFile.addActionListener(this);
		menuReadFromFile.addActionListener(this);

		menuGraph.setMnemonic(KeyEvent.VK_G);
		menuGraph.add(menuListOfNodes);
		menuGraph.add(menuListOfEdges);

		menuFile.setMnemonic(KeyEvent.VK_F);
		menuFile.add(menuNew);
		menuFile.add(menuShowExample);
		menuFile.addSeparator();
		menuFile.add(menuSaveToFile);
		menuFile.add(menuReadFromFile);
		menuFile.addSeparator();
		menuFile.add(menuExit);

		menuHelp.setMnemonic(KeyEvent.VK_H);
		menuHelp.add(menuInstruction);
		menuHelp.add(menuAuthor);

		menuBar.add(menuFile);
		menuBar.add(menuGraph);
		menuBar.add(menuHelp);
		setJMenuBar(menuBar);
	}

	@Override
	public void actionPerformed(ActionEvent event) {
		Object source = event.getSource();
		if (source == menuNew) {
			panel.setGraph(new Graph());
			repaint();
		}
		if (source == menuShowExample) {
			showBuildinExample();
			repaint();
		}
		if (source == menuListOfNodes) {
			showListOfNodes(panel.getGraph());
		}
		if (source == menuListOfEdges) {
			showListOfEdges(panel.getGraph());
		}
		if (source == menuAuthor) {
			JOptionPane.showMessageDialog(this, APP_AUTHOR, APP_TITLE, JOptionPane.INFORMATION_MESSAGE);
		}
		if (source == menuInstruction) {
			JOptionPane.showMessageDialog(this, APP_INSTRUCTION, APP_TITLE, JOptionPane.PLAIN_MESSAGE);
		}
		if (source == menuSaveToFile) {

			JFileChooser chooser = new JFileChooser(".");
			int returnVal = chooser.showSaveDialog(this);
			if (returnVal == JFileChooser.APPROVE_OPTION) {
				try {
					Graph.printToFileBin(chooser.getSelectedFile().getName(), panel.graph);
				} catch (GraphException e) {

					e.printStackTrace();
				}
			}

		}
		if (source == menuReadFromFile) {
			JFileChooser chooser = new JFileChooser(".");
			int returnVal = chooser.showOpenDialog(this);
			if (returnVal == JFileChooser.APPROVE_OPTION) {
				try {
					panel.graph = Graph.readFromFileBin(chooser.getSelectedFile().getName());
				} catch (GraphException e) {
					e.printStackTrace();
				}
			}
			repaint();
		}
		if (source == menuExit) {
			System.exit(0);
		}
	}

	private void showBuildinExample() {
		Graph graph = new Graph();

		Node n1 = new Node(100, 100);
		n1.setName("A");
		Node n2 = new Node(75, 200);
		n2.setR(20);
		n2.setColor(Color.CYAN);
		n2.setName("B");
		Node n3 = new Node(200, 60);
		n3.setR(20);
		n3.setName("C");
		Node n4 = new Node(300, 250);
		n4.setColor(Color.GREEN);
		n4.setR(30);
		n4.setName("D");
		Edge e1 = new Edge(n4, n2);
		Edge e2 = new Edge(n3, n4);
		e2.setColor(Color.RED);
		Edge e3 = new Edge(n2, n3);
		e3.setColor(Color.MAGENTA);
		Edge e4 = new Edge(n4, n1);
		e4.setColor(Color.YELLOW);

		graph.addNode(n1);
		graph.addNode(n2);
		graph.addNode(n3);
		graph.addNode(n4);
		graph.addEdge(e1);
		graph.addEdge(e2);
		graph.addEdge(e3);
		graph.addEdge(e4);
		panel.setGraph(graph);
	}
}
