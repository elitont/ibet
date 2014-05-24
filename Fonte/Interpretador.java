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
//teste smart git
class Interpretador {
    private String[] linhas;
    private Opera op;
    private int abre, fecha;	//escopos gerais
    private int open, close;	//escopo condição falsa
    private static int flag = 0; //condiçao falsa

    public Interpretador(){
        op = new Opera();
        this.abre = 0;
        this.fecha = 0;
        this.open = 0;
        this.close = 0;
    }

    public void interpreta(String[] l) {
        this.linhas = l;
        
        for(int i = 0; i < this.linhas.length; i++) {
            if(this.linhas[i] != null) {    //testar 
            	if(this.linhas[i].contains("se|") || this.linhas[i].contains("enquanto|") || this.linhas[i].contains("Inicio")) {
            		this.abre++;
        		}
        		if(this.linhas[i].contains("fim_se") || this.linhas[i].contains("fim_enquanto") || this.linhas[i].contains("Fim")) {
            		this.fecha++;
        		}
                if(i == 0 && this.linhas[0].equals("Inicio") == false){     //se nao contem Inicio da pau
                    op.erro(0);
                }
                if(this.linhas[i].contains("fim_se") && (open == close)){
                	flag = 0;
                	open = close = 0;
                }
                if(this.linhas[i].contains("imprima") == false){            //deixa os espaços de impressao
                    this.linhas[i] = this.linhas[i].replace("\t","");
                    this.linhas[i] = this.linhas[i].replace(" ", "");
                }
                if(flag == 1 && this.linhas[i].contains("se|")){
                	open++;
                }
                if((flag == 1) && this.linhas[i].contains("fim_se")){
                	close++;
                }
                if(flag == 0) tokens(this.linhas[i]);
            }
        }
        if(this.abre != this.fecha) {
            op.erro(9);
        }
    }
   public void tokens(String a){
        String[] b;

        b = a.split("[|]");  

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
            if(op.condicao(b) == false) flag = 1;
        }
        else if(b[0].equals("enquanto")){

        }
        else if(b[0].equals("Inicio") == false && b[0].equals("Fim") == false && b[0].equals("fim_se") == false && b[0].equals("fim_enquanto") == false){     //atribuição (sem cmd especial)
            if(b.length == 4 || b.length == 6){
                op.atribuicao(b);
            }
            else{
                op.erro(3);
            }
        }
    }
}
