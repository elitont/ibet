/**
 * Alunos: Igor Beilner e Eliton Traverssini
 * E-mails: igor.beilner@hotmail.com e eliton.traverssini@gmail.com
 *
 * Classe que representa uma pilha, é usada para aninhar laços
 *
 *
**/  
class Pilha{
	private int topo;
	private int[] pile;

	public Pilha(){
		this.pile = new int[50];
		this.topo = -1;
	}

	public int getTopo(){
		return this.topo;
	}

	public void pop(){
		this.topo--;
	}

	public void push(int a){  
		this.topo++;;
		this.pile[this.topo] = a;
	}

	public int getPile(int t){
		return this.pile[t];
	}
}
