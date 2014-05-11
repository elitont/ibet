
class Variavel{
	private String nome;
	private Double valor;

	public Variavel(){
		valor = 0.0;
	}

	public void setNome(String a){
		this.nome = a;
	}

	public String getNome(){
		return this.nome;
	}

	public void setValor(Double a){
		this.valor = a;
	}

	public Double getValor(){
		return this.valor;
	}
}