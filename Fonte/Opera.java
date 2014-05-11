
class Opera{
    private Variavel[] vetor; 
    private int controle;

    public Opera(){
        vetor = new Variavel[1000];
        int i;
        for(i = 0; i < vetor.length; i++){
            vetor[i] = new Variavel();
        }
    }

    public void erro(int e){
        switch(e){
            case 0: 
                System.out.println("Erro 0: Programa deve conter <Inicio>.");
                break;
            case 1:
                System.out.println("Erro 1: Nome de variáveis replicadas.");
                break;
            case 2:
                System.out.println("Erro 2: Número de variáveis excedido.");
                break;
        }
        System.exit(1);
    }

    public void setVetor(){

    }

    public void getVetor(){

    }

    public void criaVariavel(String a){
        int i;
        for(i=0; i < vetor.length; i++){
            if(a.equals(vetor[i].getNome())){
                this.erro(1);
            }
        }
        if(controle < vetor.length){
            vetor[controle].setNome(a);
            this.controle++;
        }
        else this.erro(2);
    }

    public void atribuicao(){

    }

    public Double lerVariavel(){
        return 0.0;   //mudar
    }

    public void condicao(){

    }

    public void laco(){

    }

    public void impressao(){

    }


}