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
    private int open, close;	//escopo se falso
    private int on, off;		//escopo laço falso
    private static int flag; //condiçao falsa se
    private static int flag2; //condiçao falsa laço


    public Interpretador(){
        op = new Opera();
        this.abre = 0;
        this.fecha = 0;
        this.open = 0;
        this.close = 0;
        this.on = 0;
        this.off = 0;
        flag = 0;
        flag2 = 0;
    }

    public void interpreta(String[] l) {
        this.linhas = l;
        int swap = 0, swap2 = 0;
        for(int i = 0; i < this.linhas.length; i++) {
            if(this.linhas[i] != null) {    //testar 
            	if(flag2 == 0 && this.linhas[i].contains("enquanto|")){
            		swap = i;
            	}
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
                if(flag2 == 1 && this.linhas[i].contains("enquanto|")){
                	on++;
                }
                if((flag2 == 1) && this.linhas[i].contains("fim_enquanto")){
                	flag2 = 0;
                	on = off = 0;
                	off++;
                	i = swap2+1;
                }
                else if(this.linhas[i].contains("fim_enquanto") && (on == off)){
                	flag2 = 0;
                	on = off = 0;
                	swap2 = i;
                	i = swap;
                }
                if(flag == 0 && flag2 == 0) tokens(this.linhas[i]);
            }
        }
        if(this.abre != this.fecha && swap == 0) {
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
        	if(op.condicao(b) == false) flag2 = 1;
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
