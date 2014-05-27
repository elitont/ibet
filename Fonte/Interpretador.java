/**
 * Alunos: Igor Beilner e Eliton Traverssini
 * E-mails: igor.beilner@hotmail.com e eliton.traverssini@gmail.com
 *
 * Classe usada para interpretar o código do arquivo, reconhece os comandos ou
 * acusa um arro caso algum tenha ocorido
 *
**/  
class Interpretador {
    private String[] linhas;
    private Opera op;
    private int abre, fecha;	        //escopos gerais
    private int open, close;	        //escopos "se", quando falso
    private static int flag_se;         //condiçao falsa do "se"
    private static int flag_laco;         //condiçao falsa do "enquanto"
    private int line;                   //posição da linha 
    private Pilha laco;

    public Interpretador(){
        op = new Opera();
        this.line = 0;
        this.abre = 0;
        this.fecha = 0;
        this.open = 0;
        this.close = 0;
        flag_se = 0;
        flag_laco = 0;
        laco = new Pilha();
    }

    public void interpreta(String[] l) {
        this.linhas = l;
        
        for(line = 0; line < this.linhas.length; line++) {
            if(this.linhas[line] != null) {    
                if(this.linhas[line].contains("enquanto|") && flag_laco != 0){
                    flag_laco++;
                }
                if(this.linhas[line].contains("fim_enquanto") && flag_laco != 0){
                    if(flag_laco == 1){
                        flag_laco = 0;
                    }else{
                        flag_laco--;
                    }
                }else if(this.linhas[line].contains("fim_enquanto") && flag_laco == 0){
                    line = laco.getPile(laco.getTopo());
                    laco.pop();
                    this.fecha++;
                }
                //------------------------------------------------------------------------------------------------------------------
            	//conta todos os escopos que foram abertos e fechados e verifica se todos que foram abertos, foram fechados
                if(this.linhas[line].contains("se|") || this.linhas[line].contains("enquanto|") || this.linhas[line].contains("Inicio")) {
            		this.abre++;
        		}
        		if(this.linhas[line].contains("fim_se") || this.linhas[line].contains("fim_enquanto") || this.linhas[line].contains("Fim")) {
            		this.fecha++;
        		}
                //------------------------------------------------------------------------------------------------------------------
                //verifica se a primeira linha de codigo contém Inicio, que equivale ao escopo da main
                if(line == 0 && this.linhas[0].equals("Inicio") == false){
                    op.erro(0);
                }
                //apaga o flag de falso e zera o numero de escopos que foram abertos e fechados e desconsidera o que tem
                //dentro deles
                if(this.linhas[line].contains("fim_se") && (open == close)){
                	flag_se = 0;
                	open = close = 0;
                }
                //desconsidera a linha que contém imprima, para não retirar os espaços contidos na frase que será escrita
                if(this.linhas[line].contains("imprima") == false){
                    this.linhas[line] = this.linhas[line].replace("\t","");
                    this.linhas[line] = this.linhas[line].replace(" ", "");
                }
                //---------------------------------------------------------------------------------------------------------
                //quando a condição de um "se" for falsa é necessário contar os escopos de "se" anihados para desconsiderar
                //apenas o código que está dentro do "se" que deu condição falsa
                if(flag_se == 1 && this.linhas[line].contains("se|")){
                	open++;
                }
                if((flag_se == 1) && this.linhas[line].contains("fim_se")){
                	close++;
                }
                //---------------------------------------------------------------------------------------------------------
                //invoca o método tokens quando quando os flags das condições dos "se" e "enquanto" forem verdadeiras
                if(flag_se == 0 && flag_laco == 0) tokens(this.linhas[line]);
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
            if(op.condicao(b) == false) flag_se = 1;
        }
        else if(b[0].equals("enquanto")){
        	if(op.condicao(b) == false) {
                flag_laco++;
            }else{
                laco.push(line);        //guardo a linha que começa o laço
            }
        }
        else if(b[0].equals("leia")) {
            op.scan(b[1]);
        }
        else if(b[0].equals("#") == false && b[0].equals("Inicio") == false && b[0].equals("Fim") == false && b[0].equals("fim_se") == false && b[0].equals("fim_enquanto") == false){     //atribuição (sem cmd especial)
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
