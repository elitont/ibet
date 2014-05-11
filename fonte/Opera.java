
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
                System.out.println("Programa deve conter <Inicio>");
                System.exit(1);
                break;

        }
    }

    public void setVetor(){

    }

    public void getVetor(){

    }

    public void criaVariavel(String nome){
        
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