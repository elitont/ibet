/**
 * Exemplo de interpretador.
 *
 * Esse código é um exemplo de interpretador para a linguagem 'Blah'. Esse programa
 * não faz qualquer interpretação, ele apenas lê o conteúdo de um arquivo que foi
 * passado pela linha de comando.
 *
 * Originalmente por Fernando Bevilacqua <fernando.bevilacqua@uffs.edu.br>
 * Modificado por: Eliton Traverssini e Igor Beilner
 * <eliton.traverssini@gmail.com> <igor.beilner@hotmail.com>
 */

class Interpretador {
    private String[] linhas;
    private Opera op;

    public Interpretador(){
        op = new Opera();
    }

    public void interpreta(String[] l) {
        this.linhas = l;
        
        for(int i = 0; i < this.linhas.length; i++) {
            if(this.linhas[i] != null) {    //testar 
                if(i == 0 && this.linhas[0].equals("Inicio") == false){     //se nao contem Inicio da pau
                    op.erro(0);
                }
                if(this.linhas[i].contains("imprima") == false){            //deixa os espaços de impressao
                    this.linhas[i] = this.linhas[i].replace("\t","");
                    this.linhas[i] = this.linhas[i].replace(" ", "");
                }
                tokens(this.linhas[i]);
            }
        }
    }
   public void tokens(String a){
        String[] b;

        b = a.split("[|]");                 
        int i;
        for(i = 0; i < b.length; i++){      //imprime cada linha
            //System.out.println(b[i]); 
        }
        
        //verifica comandos especiais

        if(b[0].equals("double")){
            op.criaVariavel(b[1]);    
        }
        else if(b[0].contains("imprima")){
            b[0] = b[0].replace("\t", "");
            b[0] = b[0].replace(" ", "");
            op.impressao(b[0], b[1]);
        }
        else if(b[0].equals("se")){

        }
        else if(b[0].equals("enquanto")){

        }
        else if(b[0].equals("Inicio") == false && b[0].equals("Fim") == false && b[0].equals("fim_se") == false && b[0].equals("fim_enquanto") == false){     //atribuição (sem cmd especial)
            if(b.length <= 6){
                op.atribuicao(b);
            }
            else{
                op.erro(3);
            }
        }

    }



}
