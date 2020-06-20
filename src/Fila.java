
public class Fila {
	public No inicio, fim;
	int t = 0;

	public Fila() {
		inicio = fim = null;
	}

	public void insere(vertices e) {

		No novo = new No(e);
		if (inicio == null) {
			inicio = novo;
			fim = novo;
			t++;
			System.out.println("inseriu na Fila: " + novo.Elemento.getNome());
		} else {
			fim.proximo = novo;
			fim = novo;
			t++;
			System.out.println("inseriu na Fila: " + novo.Elemento.getNome());
		}
	}

	public vertices remover() {
		vertices e;
		e = inicio.Elemento;
		inicio = inicio.proximo;

		return e;
	}

	public int tamanho() {
		int c = 0;
		No aux = inicio;
		if (aux == null) {
			
			return 0;
		} else {
			while (aux.proximo != null) {
				
				aux = inicio.proximo;
				c++;
			}
			
			
			return c;
		}
	}

	public void mostra() {
		System.out.println("\n >> ELEMENTOS NA FILA << \n");
		No aux = inicio;
		while (aux != null) {
			System.out.println(aux.Elemento.getNome());
			aux = aux.proximo;
			
		}
		System.out.println("\n >> ------------------- << \n");
	}

	public boolean Vazio() {
		return (inicio == null);
	}

}
