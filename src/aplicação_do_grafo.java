import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import javax.swing.JOptionPane;

public class aplicação_do_grafo {

	boolean orientado = false;
	boolean valorado = false;
	Fila f = new Fila();
	ArrayList<vertices> vertices = new ArrayList<vertices>();
	ArrayList<arestas> arestas = new ArrayList<arestas>();
	ArrayList<arestas> adjacentes_de_u = new ArrayList<arestas>(); // GUARDA TEMPORARIAMENTO OS ADJACENTES DO VERTICE U

	public boolean Valorado() {
		String val = JOptionPane.showInputDialog("Valorado? S/N");
		if (val.equalsIgnoreCase("S")) {
			return true;
		} else {
			return false;
		}
	}

	public boolean Orientado() {
		String ori = JOptionPane.showInputDialog("Orientado? S/N");
		if (ori.equalsIgnoreCase("S")) {
			return true;
		} else {
			return false;
		}
	}

	public void addvertices() {
		String mostrar = "";
		String continua = "S";
		do {

			String vertice = JOptionPane.showInputDialog("Vértices:" + "\n" + mostrar + "\n" + "Informe o vertice");
			vertices v = new vertices();
			v.setNome(vertice);
			vertices.add(v);

			mostrar = "";
			for (vertices m : vertices) {
				mostrar += m.getNome() + " - ";
			}

			continua = JOptionPane.showInputDialog("Continuar? S/N");
		} while (continua.equalsIgnoreCase("S"));
	}

	public void addarestas() {
		String mostrar = "";
		String continua = "S";

		valorado = Valorado();
		orientado = Orientado();

		do {
			arestas a = new arestas();
			String seta = " - ";
			String aresta1 = JOptionPane
					.showInputDialog("Adicionando Arestas:" + "\n" + mostrar + "\n" + "Vertice de SAIDA da aresta");
			for (vertices v : vertices) {
				if (aresta1.equals(v.getNome())) {
					a.setSaida(v);
				}
			}

			String aresta2 = JOptionPane
					.showInputDialog("Adicionando Arestas:" + "\n" + mostrar + "\n" + "Vertice de CHEGADA da aresta");
			for (vertices v : vertices) {
				if (aresta2.equals(v.getNome())) {
					a.setChegada(v);
				}
			}
			if (orientado == true) {
				a.setOrientado(true);
				seta = " --> ";
			}
			if (valorado == true) {
				a.setValorado(true);
				a.setValor(Double.parseDouble(
						JOptionPane.showInputDialog("VALOR da Aresta " + aresta1 + seta + aresta2 + ": ")));
			} else {
				a.setValor(1.0);
			}

			arestas.add(a);

			mostrar = "";
			for (arestas v : arestas) {
				mostrar += v.getSaida().getNome() + seta + v.getChegada().getNome() + "  //  ";
			}

			continua = JOptionPane.showInputDialog("Continuar? S/N");
		} while (continua.equalsIgnoreCase("S"));

	}

	public String[][] mostrarGrafos() {

		String vetorVertices[] = new String[vertices.size()];
		String matrizArestas[][] = new String[vertices.size()][vertices.size()];

		for (int i = 0; i < vertices.size(); i++) {
			vetorVertices[i] = vertices.get(i).getNome();

		}
		if (orientado == false) { // coloquei false pq bugou
			System.out.println("");
			//////// matriz de adjcacência normal////////
			// System.out.println("---- Matriz de Adjacência: ----");

			int c1 = 0;
			for (int i = 0; i < vetorVertices.length; i++) {
				if (c1 == 0) {
					// System.out.print(" " + "|" + vetorVertices[i] + "|");
					c1++;
				} else {
					// System.out.print("|" + vetorVertices[i] + "|");
				}

			}
			for (int i = 0; i < vetorVertices.length; i++) {
				// System.out.print("\n" + "|" + vetorVertices[i] + "|");
				for (int j = 0; j < vetorVertices.length; j++) {
					matrizArestas[i][j] = " 0 ";
					for (arestas ar : arestas) {

						if (ar.getSaida().getNome().equals(vertices.get(i).getNome())
								&& ar.getChegada().getNome().equals(vertices.get(j).getNome())
								|| ar.getSaida().getNome().equals(vertices.get(j).getNome())
										&& ar.getChegada().getNome().equals(vertices.get(i).getNome())) {
							if (ar.isValorado()) {
								matrizArestas[i][j] = ar.getValor().toString();
							} else {
								matrizArestas[i][j] = " 1 ";
							}
						} else {

						}

					}
					// System.out.print(matrizArestas[i][j]);

				}

			}

		} else {
			//////// matriz de adjcacência orientada////////
			System.out.println("");
			// System.out.println("---- Matriz de Adjacência: ----");
			System.out.println("");
			int c1 = 0;
			for (int i = 0; i < vetorVertices.length; i++) {
				if (c1 == 0) {
					// System.out.print(" " + "|" + vetorVertices[i] + "|");
					c1++;
				} else {
					// System.out.print("|" + vetorVertices[i] + "|");
				}

			}
			for (int i = 0; i < vetorVertices.length; i++) {
				// System.out.print("\n" + "|" + vetorVertices[i] + "|");
				for (int j = 0; j < vetorVertices.length; j++) {
					matrizArestas[i][j] = " 0 ";
					for (arestas ar : arestas) {

						if (ar.getSaida().getNome().equals(vertices.get(i).getNome())
								&& ar.getChegada().getNome().equals(vertices.get(j).getNome())) {

							if (ar.isValorado()) {
								matrizArestas[i][j] = ar.getValor().toString();
							} else {
								matrizArestas[i][j] = " 1 ";
							}
						} else {

						}

					}
					// System.out.print(matrizArestas[i][j]);

				}
			}

		}
		return matrizArestas;
	}

	public void preencheGrafo() {
		addvertices();
		addarestas();
	}

	public void dijkstra(String v, String matriz[][]) {
		vertices pontoInicial = null;
		vertices u;
		for (vertices v1 : vertices) {
			v1.setDistancia_atual(99999); // TODAS DISTANCIAS RECEBEM INFINITO
			v1.setAnterior(null); // TODOS VERTICES ANTERIORES RECEBEM NULL
			v1.setColor("white");
			if (v1.getNome().equals(v)) {
				pontoInicial = v1; // ENCONTRA O PONTO INICIAL
			}

		}
		pontoInicial.setDistancia_atual(0); // DISTANCIA DO PONTO INICIAL RECEBE '0'
		pontoInicial.setColor("gray"); // COR RECEBE CINZA
		alteraDistanciaDosAdjacentesDoVerticeDeSaida(v, matriz); // SETA A DISTANCIA ATUAL DOS ADJACENTES DO VERTICE DE
																	// SAIDA
		enfileiraAdjacentes(v, mostrarGrafos()); // INSERE OS ADJACENTES NA FILA EM ORDEM CRESCENTE DE DISTANCIA
		while (f.Vazio() == false) { // ENQUANTO A FILA NÃO ESTIVER VAZIA
			u = f.remover(); // REMOVE UM VERTICE DA FILA E USA ELE
			System.out.println("removeu o " + u.getNome() + " da Fila");
			adjacentesDeU(u, mostrarGrafos()); // ADICIONA AS ARESTAS ADJACENTES DE U EM UMA ARRAY LIST

			for (arestas ad : adjacentes_de_u) { // PARA CADA ARESTA INCIDENTE DE U

				if ((ad.getSaida() != u) && (ad.getSaida().getDistancia_atual() == 99999.0)) {

					System.out.println("PRECISA ENFILEIRAR O " + ad.getSaida().getNome());
					vertices vetor[] = new vertices[f.tamanho() + 1]; // ESSA DESGRAÇA SÓ FUNCIONA SE COLOCAR +1 (não
																		// sei pq)
					int i = 0;
					while (f.Vazio() == false) {

						f.mostra();
						vetor[i] = f.remover();

						i++;
					}
					enfileiraAdjacentesNovamente(u.getNome(), matriz, vetor);

				}
				if ((ad.getChegada() != u) && (ad.getChegada().getDistancia_atual() == 99999.0)) {
					System.out.println("PRECISA ENFILEIRAR O " + ad.getChegada().getNome());
					vertices vetor[] = new vertices[f.tamanho() + 1];
					// System.out.println("tamanho do vetor: " + f.tamanho());
					int i = 0;
					while (f.Vazio() == false) {
						vetor[i] = f.remover();

						i++;
					}
					enfileiraAdjacentesNovamente(u.getNome(), matriz, vetor);
				}

				System.out
						.println("CONDIÇÃO: " + ad.getSaida().getNome() + " é diferente de " + u.getNome() + " ?? \n");
				if (ad.getSaida() != u && (orientado == false)) {
					System.out.println("SIM \n");
					System.out.println("CONDIÇÃO: " + ad.getSaida().getDistancia_atual() + " é MAIOR que  "
							+ u.getDistancia_atual() + " + " + ad.getValor() + " ?? \n");
					if (ad.getSaida().getDistancia_atual() > (u.getDistancia_atual() + ad.getValor())) {
						System.out.println("SIM \n");
						ad.getSaida().setDistancia_atual(u.getDistancia_atual() + ad.getValor());
						System.out.println("----AGORA O " + u.getNome() + " É O ANTERIOR AO" + ad.getSaida().getNome());
						ad.getSaida().setAnterior(u);
						System.out.println("Entrou aqui! \n");

					}
				} else {
					System.out.println("NÃO \n");
				}
				System.out
						.println("CONDIÇÃO: " + ad.getSaida().getNome() + " é diferente de " + u.getNome() + " ?? \n");
				if (ad.getChegada() != u) {

					System.out.println("CONDIÇÃO: " + ad.getChegada().getDistancia_atual() + " é MAIOR que  "
							+ u.getDistancia_atual() + " + " + ad.getValor() + " ?? \n");
					if (ad.getChegada().getDistancia_atual() > (u.getDistancia_atual() + ad.getValor())) {
						System.out.println("SIM \n");
						ad.getChegada().setDistancia_atual(u.getDistancia_atual() + ad.getValor());
						System.out
								.println("----AGORA O " + u.getNome() + " É O ANTERIOR AO" + ad.getChegada().getNome());
						ad.getChegada().setAnterior(u);
						System.out.println("Entrou aqui! \n");
					}
				}

			}
			u.setColor("gray"); // APÓS PERCORRER TODOS ADJACENTES DO u
		}

		for (vertices v2 : vertices) {
			String anteriores2 = "";

			vertices aux2 = v2;
			vertices aux = v2;
			
			int cont = 0;
			while (aux2 != null) { // CALCULA A QUANTIDADE DE VERTICES PERCORRIDOS PARA CHEGAR NESSE VERTICES
				cont++;
				aux2 = aux2.getAnterior();

			}
			String vetor[] = new String[cont];
			int i = 0;
			while (aux != null) {	
				vetor[i] = aux.getNome();
				aux = aux.getAnterior();				
				i++;

			}
			
			for (int i2 = vetor.length-1; i2 >-1 ; i2--) {
				anteriores2 = anteriores2+ " -> " + vetor[i2];
			}
			
			if (v2.getAnterior() != null) { // PRINTA NA TELA TODOS OS VERTICES COM O MELHOR CAMINHO (MENOS O DE SAIDA)
				System.out.println(v2.getNome() + "| melhor distância: " + v2.getDistancia_atual()
						+ "  | melhor caminho: " + anteriores2);
			}
		}

	}

	public void enfileiraAdjacentes(String v, String matriz[][]) { // ENFILEIRA OS ADJACENTES AO PONTO DE PARTIDA
		vertices pontoInicial;

		for (vertices ve2 : vertices) {
			if (ve2.getNome().equals(v)) {
				pontoInicial = ve2;
			}
		}

		int pos = 0;
		String vetorVertices[] = new String[vertices.size()];

		for (int i = 0; i < vertices.size(); i++) {
			vetorVertices[i] = vertices.get(i).getNome();

			if (v.equalsIgnoreCase(vertices.get(i).getNome())) {
				pos = i;
			}
		}

		ArrayList<vertices> ordem = new ArrayList<vertices>();

		int quantidadeAdjacentes = 0;
		for (int i = 0; i < vertices.size(); i++) { // RECEBE A QUANTIDADE DE ADJACENTES (CASO PRECISE)

			if (matriz[pos][i] == (" 0 ")) {
				quantidadeAdjacentes++;
			}
		}

		for (int i = 0; i < vetorVertices.length; i++) { // ESTE FOR ADICIONA OS ADJACENTES EM UMA ARRAY LIST

			if (matriz[pos][i] != " 0 ") {
				for (arestas a : arestas) {
					if (a.getSaida() == (vertices.get(pos)) && a.getChegada() == (vertices.get(i))
							&& (orientado == false) && (a.getChegada().getColor().equalsIgnoreCase("white"))) {

						a.getChegada().setDistancia_aresta(a.getValor());

						ordem.add(a.getChegada());

					}

					if (a.getChegada() == (vertices.get(pos)) && a.getSaida() == (vertices.get(i))
							&& (orientado == false) && (a.getSaida().getColor().equalsIgnoreCase("white"))) {

						a.getSaida().setDistancia_aresta(a.getValor());
						ordem.add(a.getSaida());

					}
					// LAÇO TA COM BUG
					if (a.getSaida() == (vertices.get(pos)) && a.getChegada() == (vertices.get(pos))
							&& (orientado == false) && (a.getSaida().getColor().equalsIgnoreCase("white"))) {

						a.getSaida().setDistancia_aresta(a.getValor());
						ordem.add(a.getSaida());

					}

					if (a.getSaida() == (vertices.get(pos)) && a.getChegada() == (vertices.get(i))
							&& (orientado == true) && (a.getChegada().getColor().equalsIgnoreCase("white"))) {

						a.getChegada().setDistancia_aresta(a.getValor());
						ordem.add(a.getChegada());

					}
				}

			}
		}

		vertices ordemCrescente[] = new vertices[ordem.size()];
		Collections.sort(ordem); // COLOCA OS ADJACENTES EM ORDEM DE DISTANCIA
		int i = 0;
		for (vertices o : ordem) {
			ordemCrescente[i] = o;
			i++;
		}

		for (int i2 = ordemCrescente.length - 1; i2 != -1; i2--) { // COLOCA OS VERTICES ADJACENTES EM ORDEM CRESCENTE
																	// DE DISTANCIA

			f.insere(ordemCrescente[i2]); // INSERE OS VERTICES ADJACENTES NA FILA
		}
	}

	public void adjacentesDeU(vertices u, String matriz[][]) {
		adjacentes_de_u.clear(); // esvazia a array list antes de adicionar os adjacentes do proximo u
		int pos = 0;
		String vetorVertices[] = new String[vertices.size()];

		for (int i = 0; i < vertices.size(); i++) {
			vetorVertices[i] = vertices.get(i).getNome();

			if (u.getNome().equalsIgnoreCase(vertices.get(i).getNome())) {
				pos = i;
			}
		}

		System.out.println("---- Informações: ----  \n");

		for (int i = 0; i < vetorVertices.length; i++) { // ESTE FOR ADICIONA OS ADJACENTES EM UMA ARRAY LIST

			if (matriz[pos][i] != " 0 ") {
				for (arestas a : arestas) {
					if (a.getSaida() == (vertices.get(pos)) && a.getChegada() == (vertices.get(i))
							&& (orientado == false) && (a.getChegada().getColor().equalsIgnoreCase("white"))) {

						// a.getChegada().setDistancia_atual(a.getValor());
						adjacentes_de_u.add(a);
						System.out.println(a.getSaida().getNome() + " - " + a.getChegada().getNome() + " é incidente a "
								+ vertices.get(pos).getNome());

					}

					if (a.getChegada() == (vertices.get(pos)) && a.getSaida() == (vertices.get(i))
							&& (orientado == false) && (a.getSaida().getColor().equalsIgnoreCase("white"))) {

						// a.getSaida().setDistancia_atual(a.getValor());
						adjacentes_de_u.add(a);
						System.out.println(a.getSaida().getNome() + " - " + a.getChegada().getNome() + " é incidente a "
								+ vertices.get(pos).getNome());

					}
					// LAÇO TA COM BUG
					if (a.getSaida() == (vertices.get(pos)) && a.getChegada() == (vertices.get(pos))
							&& (orientado == false) && (a.getChegada().getColor().equalsIgnoreCase("white"))) {

						// a.getChegada().setDistancia_atual(a.getValor());
						adjacentes_de_u.add(a);
						System.out.println(a.getSaida().getNome() + " - " + a.getChegada().getNome() + " é incidente a "
								+ vertices.get(pos).getNome());

					}

					if (a.getSaida() == (vertices.get(pos)) && a.getChegada() == (vertices.get(i))
							&& (orientado == true) && (a.getChegada().getColor().equalsIgnoreCase("white"))) {

						// a.getChegada().setDistancia_atual(a.getValor());
						adjacentes_de_u.add(a);
						System.out.println(a.getSaida().getNome() + " - " + a.getChegada().getNome() + " é incidente a "
								+ vertices.get(pos).getNome());

					}
				}

			}
		}
	}

	public void alteraDistanciaDosAdjacentesDoVerticeDeSaida(String v, String matriz[][]) {
		vertices pontoInicial;

		for (vertices ve2 : vertices) {
			if (ve2.getNome().equals(v)) {
				pontoInicial = ve2;
			}
		}

		int pos = 0;
		String vetorVertices[] = new String[vertices.size()];

		for (int i = 0; i < vertices.size(); i++) {
			vetorVertices[i] = vertices.get(i).getNome();

			if (v.equalsIgnoreCase(vertices.get(i).getNome())) {
				pos = i;
			}
		}

		ArrayList<vertices> ordem = new ArrayList<vertices>();

		int quantidadeAdjacentes = 0;
		for (int i = 0; i < vertices.size(); i++) { // RECEBE A QUANTIDADE DE ADJACENTES (CASO PRECISE)

			if (matriz[pos][i] == (" 0 ")) {
				quantidadeAdjacentes++;
			}
		}

		for (int i = 0; i < vetorVertices.length; i++) { // ESTE FOR ADICIONA OS ADJACENTES EM UMA ARRAY LIST

			if (matriz[pos][i] != " 0 ") {
				for (arestas a : arestas) {
					if (a.getSaida() == (vertices.get(pos)) && a.getChegada() == (vertices.get(i))
							&& (orientado == false) && (a.getChegada().getColor().equalsIgnoreCase("white"))) {

						a.getChegada().setDistancia_atual(a.getValor());
						a.getChegada().setAnterior(a.getSaida());
						// ordem.add(a.getChegada());

					}

					if (a.getChegada() == (vertices.get(pos)) && a.getSaida() == (vertices.get(i))
							&& (orientado == false) && (a.getSaida().getColor().equalsIgnoreCase("white"))) {

						a.getSaida().setDistancia_atual(a.getValor());
						a.getSaida().setAnterior(a.getChegada());
						// ordem.add(a.getSaida());

					}
					// LAÇO TA COM BUG
					if (a.getSaida() == (vertices.get(pos)) && a.getChegada() == (vertices.get(pos))
							&& (orientado == false) && (a.getSaida().getColor().equalsIgnoreCase("white"))) {

						a.getSaida().setDistancia_atual(a.getValor());
						a.getSaida().setAnterior(a.getChegada());
						// ordem.add(a.getSaida());

					}

					if (a.getSaida() == (vertices.get(pos)) && a.getChegada() == (vertices.get(i))
							&& (orientado == true) && (a.getChegada().getColor().equalsIgnoreCase("white"))) {

						a.getChegada().setDistancia_atual(a.getValor());
						a.getChegada().setAnterior(a.getSaida());
						// ordem.add(a.getChegada());

					}
				}

			}
		}

//		vertices ordemCrescente[] = new vertices[ordem.size()];
//		Collections.sort(ordem); // COLOCA OS ADJACENTES EM ORDEM DE DISTANCIA
//		int i = 0;
//		for (vertices o : ordem) {
//			ordemCrescente[i] = o;
//			i++;
//		}
//
//		for (int i2 = ordemCrescente.length - 1; i2 != -1; i2--) { // COLOCA OS VERTICES ADJACENTES EM ORDEM CRESCENTE
//																	// DE DISTANCIA
//
//			f.insere(ordemCrescente[i2]); // INSERE OS VERTICES ADJACENTES NA FILA
//		}
	}

	public void enfileiraAdjacentesNovamente(String v, String matriz[][], vertices[] vetor) {

		vertices pontoInicial;

		for (vertices ve2 : vertices) {
			if (ve2.getNome().equals(v)) {
				pontoInicial = ve2;
			}
		}

		int pos = 0;
		String vetorVertices[] = new String[vertices.size()];

		for (int i = 0; i < vertices.size(); i++) {
			vetorVertices[i] = vertices.get(i).getNome();

			if (v.equalsIgnoreCase(vertices.get(i).getNome())) {
				pos = i;
			}
		}

		ArrayList<vertices> ordem = new ArrayList<vertices>();

		int quantidadeAdjacentes = 0;
		for (int i = 0; i < vertices.size(); i++) { // RECEBE A QUANTIDADE DE ADJACENTES (CASO PRECISE)

			if (matriz[pos][i] == (" 0 ")) {
				quantidadeAdjacentes++;
			}
		}

		for (int i = 0; i < vetorVertices.length; i++) { // ESTE FOR ADICIONA OS ADJACENTES EM UMA ARRAY LIST

			if (matriz[pos][i] != " 0 ") {
				for (arestas a : arestas) {
					if (a.getSaida() == (vertices.get(pos)) && a.getChegada() == (vertices.get(i))
							&& (orientado == false) && (a.getChegada().getColor().equalsIgnoreCase("white"))
							&& verificaFila(a.getChegada(), vetor)) {
						if (a.getChegada().getDistancia_atual() > a.getSaida().getDistancia_atual() + a.getValor()) {
							a.getChegada().setDistancia_aresta(a.getSaida().getDistancia_atual() + a.getValor());
							a.getChegada().setDistancia_atual(a.getSaida().getDistancia_atual() + a.getValor());
							a.getChegada().setAnterior(a.getSaida());
						}

						ordem.add(a.getChegada());

					}

					if (a.getChegada() == (vertices.get(pos)) && a.getSaida() == (vertices.get(i))
							&& (orientado == false) && (a.getSaida().getColor().equalsIgnoreCase("white"))
							&& verificaFila(a.getSaida(), vetor)) {
						if (a.getSaida().getDistancia_atual() > a.getChegada().getDistancia_atual() + a.getValor()) {
							a.getSaida().setDistancia_aresta(a.getChegada().getDistancia_atual() + a.getValor());
							a.getSaida().setDistancia_atual(a.getChegada().getDistancia_atual() + a.getValor());
							a.getSaida().setAnterior(a.getChegada());
						}

						ordem.add(a.getSaida());

					}
					// LAÇO TA COM BUG
					if (a.getSaida() == (vertices.get(pos)) && a.getChegada() == (vertices.get(pos))
							&& (orientado == false) && (a.getSaida().getColor().equalsIgnoreCase("white"))) {

						a.getSaida().setDistancia_aresta(a.getValor());
						a.getSaida().setDistancia_atual(a.getValor());
						a.getSaida().setAnterior(a.getChegada());
						ordem.add(a.getSaida());

					}

					if (a.getSaida() == (vertices.get(pos)) && a.getChegada() == (vertices.get(i))
							&& (orientado == true) && (a.getChegada().getColor().equalsIgnoreCase("white"))
							&& verificaFila(a.getChegada(), vetor)) {
						if (a.getChegada().getDistancia_atual() > a.getSaida().getDistancia_atual() + a.getValor()) {
							a.getChegada().setDistancia_aresta(a.getSaida().getDistancia_atual() + a.getValor());
							a.getChegada().setDistancia_atual(a.getSaida().getDistancia_atual() + a.getValor());
							a.getChegada().setAnterior(a.getSaida());
						}

						ordem.add(a.getChegada());

					}
				}

			}
		}

		for (int i = 0; i < vetor.length; i++) {
			if (vetor[i] != null) {
				ordem.add(vetor[i]);
			}

		}

		vertices ordemCrescente[] = new vertices[ordem.size()];
		Collections.sort(ordem); // COLOCA OS ADJACENTES EM ORDEM DE DISTANCIA
		int i = 0;
		for (vertices o : ordem) {
			ordemCrescente[i] = o;
			i++;
		}

		for (int i2 = ordemCrescente.length - 1; i2 != -1; i2--) { // COLOCA OS VERTICES ADJACENTES EM ORDEM CRESCENTE
																	// DE DISTANCIA

			f.insere(ordemCrescente[i2]); // INSERE OS VERTICES ADJACENTES NA FILA
		}

	}

	public boolean verificaFila(vertices v, vertices[] vetor) { // VERIFICA SE O ELEMENTO JA ESTA NA FILA ANTES DE
																// ADICIONA-LO
		boolean b = true;
		for (int i = 0; i < vetor.length; i++) {
			if (v == vetor[i]) {
				b = false;
			}
		}

		return b;
	}

}
