/** 
 * Autores: Eliton Traverssini e Igor Beilner
 * <eliton.traverssini@gmail.com> <igor.beilner@hotmail.com>
 */
class Interpretador {
    private String[] linhas;
    private Opera op;
    private int abre, fecha;	        //escopos gerais
    private int open, close;	        //escopos "se", quando falso
    private static int flag_se;         //condiçao falsa do "se"
    private static int flag_laco;       //condiçao falsa do "enquanto"
    private static int flag_laco_F;     //condiçao falsa do "enquanto"
    private int swap[];
    private int swap2[];
    private int posicao_pilha;

    public Interpretador(){
        op = new Opera();
        this.abre = 0;
        this.fecha = 0;
        this.open = 0;
        this.close = 0;
        flag_se = 0;
        flag_laco = 0;
        flag_laco_F = 0;
        posicao_pilha = 0;
        swap = new int[50];
        swap2 = new int[50];
    }

    public void interpreta(String[] l) {
        this.linhas = l;
        
        for(int i = 0; i < this.linhas.length; i++) {
            if(this.linhas[i] != null) {    
                //coloca a posição do enquanto na pilha quando a condição for verdadeira
            	if(flag_laco == 0 && this.linhas[i].contains("enquanto|")){
            		swap[posicao_pilha] = i;
                    //posicao_pilha++;
            	}
                //------------------------------------------------------------------------------------------------------------------
            	//conta todos os escopos que foram abertos e fechados e verifica se todos que foram abertos, foram fechados
                if(this.linhas[i].contains("se|") || this.linhas[i].contains("enquanto|") || this.linhas[i].contains("Inicio")) {
            		this.abre++;
        		}
        		if(this.linhas[i].contains("fim_se") || this.linhas[i].contains("fim_enquanto") || this.linhas[i].contains("Fim")) {
            		this.fecha++;
        		}
                //------------------------------------------------------------------------------------------------------------------
                //verifica se a primeira linha de codigo contém Inicio, que equivale ao escopo da main
                if(i == 0 && this.linhas[0].equals("Inicio") == false){
                    op.erro(0);
                }
                //apaga o flag de falso e zera o numero de escopos que foram abertos e fechados e desconsidera o que tem
                //dentro deles
                if(this.linhas[i].contains("fim_se") && (open == close)){
                	flag_se = 0;
                	open = close = 0;
                }
                //desconsidera a linha que contém imprima, para não retirar os espaços contidos na frase que será escrita
                if(this.linhas[i].contains("imprima") == false){
                    this.linhas[i] = this.linhas[i].replace("\t","");
                    this.linhas[i] = this.linhas[i].replace(" ", "");
                }
                //---------------------------------------------------------------------------------------------------------
                //quando a condição de um "se" for falsa é necessário contar os escopos de "se" anihados para desconsiderar
                //apenas o código que está dentro do "se" que deu condição falsa
                if(flag_se == 1 && this.linhas[i].contains("se|")){
                	open++;
                }
                if((flag_se == 1) && this.linhas[i].contains("fim_se")){
                	close++;
                }
                //---------------------------------------------------------------------------------------------------------
                //salta o código que está dentro do laço quando a condição for falsa
                if((flag_laco == 1) && this.linhas[i].contains("fim_enquanto")){
                	flag_laco = 0;
                	if(flag_laco_F == 0) i = swap2[posicao_pilha]+1;
                    flag_laco_F = 0;
                }
                //retorna para a linha do "enquanto" para testar a condição novamente
                else if(this.linhas[i].contains("fim_enquanto")){
                	flag_laco = 0;
                	swap2[posicao_pilha] = i;
                	i = swap[posicao_pilha];
                    //posicao_pilha--;
                }
                //invoca o método tokens quando quando os flags das condições dos "se" e "enquanto" forem verdadeiras
                if(flag_se == 0 && flag_laco == 0) tokens(this.linhas[i]);
            }
        }
        if(this.abre != this.fecha && posicao_pilha != 0) {
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
            if(op.condicao(b) == false) flag_se = 1;
        }
        else if(b[0].equals("enquanto")){
        	if(op.condicao(b) == false) {
                flag_laco = 1;
                flag_laco_F = 1;
            }
        }
        else if(b[0].equals("leia")) {
            op.scan(b[1]);
        }
        else if(b[0].equals("Inicio") == false && b[0].equals("Fim") == false && b[0].equals("fim_se") == false && b[0].equals("fim_enquanto") == false){     //atribuição (sem cmd especial)
            //length = 4 quando for atribuição e = 6 quando tiver operação
            if(b.length == 4 || b.length == 6){
                op.atribuicao(b);
            }
            //acusa erro quando a atribuição não estiver completa
            else{
                op.erro(3);
            }
        }
    }
}
