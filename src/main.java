import javax.swing.JOptionPane;

public class main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
    
	aplicação_do_grafo a = new aplicação_do_grafo();
	
    a.preencheGrafo();
    System.out.println("Enfileirando adjacentes ao vertice inicial");
   //a.enfileiraAdjacentes("2", a.mostrarGrafos());
    String pontoinicial=JOptionPane.showInputDialog("DIGITE O VERTICE INICIAL  (PONTO DE PARTIDA)");
    a.dijkstra(pontoinicial, a.mostrarGrafos());
	}

}
