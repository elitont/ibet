
class Opera{
    private Variavel[] vetor; 
    private int controle;

    public Opera(){
        controle = 1;
        vetor = new Variavel[1000];
        int i;
        for(i = 1; i < vetor.length; i++){
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
            case 3: 
                System.out.println("Erro 3: Sintaxe da função imprima incorreta.");
                break;
            case 4: 
                System.out.println("Erro 4: Tentando imprimir variável inexistente.");
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

        if(this.verificaVariavel(a) != 0){
                this.erro(1);
        }
        
        if(controle < this.vetor.length){
            this.vetor[controle].setNome(a);
            this.controle++;
        }
        else this.erro(2);
    }

    public void atribuicao(){

    }

    public void condicao(){

    }

    public void laco(){

    }

    public int verificaVariavel(String var){
        int i;
        for(i = 1; i < this.controle; i++){
            if(var.equals(this.vetor[i].getNome())){
                return i;
            }
        }
        return 0;
    }

    public void impressao(String a, String b){
        if(a.equals("imprima")){
            String[] c = b.split("[\"]");
            int i, end;
            for(i = 0; i < c.length; i++){
                if(c[i].contains("+") == true){
                    c[i] = c[i].replace("+", "");
                    c[i] = c[i].replace(" ", "");
                    end = this.verificaVariavel(c[i]);
                    if(end != 0){
                        System.out.print(vetor[end].getValor());
                    }
                    else{
                        this.erro(4);
                    }
                }
                else{
                    System.out.print(c[i]);
                }
            }
            System.out.println();
        }
        else this.erro(3);
    }


}