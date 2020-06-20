


	public class arestas {	
		
	private vertices saida;
	private vertices chegada;
	private Double valor;
	private boolean valorado = false;
	private boolean orientado = false;
	
	
	public boolean isOrientado() {
		return orientado;
	}
	public void setOrientado(boolean orientado) {
		this.orientado = orientado;
	}
	public boolean isValorado() {
		return valorado;
	}
	public void setValorado(boolean valorado) {
		this.valorado = valorado;
	}
	
	
	public vertices getSaida() {
		return saida;
	}
	public void setSaida(vertices saida) {
		this.saida = saida;
	}
	
	public vertices getChegada() {
		return chegada;
	}
	public void setChegada(vertices chegada) {
		this.chegada = chegada;
	}
	
	public Double getValor() {
		return valor;
	}
	public void setValor(Double valor) {
		this.valor = valor;
	}
	

		
	
}
	
