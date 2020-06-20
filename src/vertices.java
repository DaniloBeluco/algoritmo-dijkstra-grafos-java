
public class vertices implements Comparable<vertices> {

	private double distancia_atual = 99999;
	private double distancia_aresta = 99999; //ESSA VARIAVEL SERVE PARA AJUDAR A COLOCAR OS ELEMENTOS EM ORDEM CRESCENTE NA FILA
	private vertices anterior;
	private String nome;
    private String color = "white";
    
    
	public double getDistancia_aresta() {
		return distancia_aresta;
	}

	public void setDistancia_aresta(double distancia_aresta) {
		this.distancia_aresta = distancia_aresta;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public double getDistancia_atual() {
		return distancia_atual;
	}

	public void setDistancia_atual(double distancia_atual) {
		this.distancia_atual = distancia_atual;
	}

	public vertices getAnterior() {
		return anterior;
	}

	public void setAnterior(vertices anterior) {
		this.anterior = anterior;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	@Override
	public int compareTo(vertices o) {
		if (this.getDistancia_aresta() > o.getDistancia_aresta()) {
			return -1;
		} else if (this.getDistancia_aresta() < o.getDistancia_aresta()) {
			return 1;
		}
		return 0;
	}
}
