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

    public void interpreta(String[] l) {
        this.linhas = l;
        
        for(int i = 0; i < this.linhas.length; i++) {
            if(this.linhas[i] != null) {	//testar 
                if(i == 0 && this.linhas[0].equals("Inicio") != true){
                    op.erro(0);
                } 
                tokens(this.linhas[i]);
                //System.out.println("Linha " + (i + 1) + ": " + this.linhas[i]);
            }
        }
    }
   public void tokens(String a){
        String[] b;
        b = a.split("[|]");
        int i;
        for(i = 0; i < b.length; i++){
            System.out.println(b[i]);
        }
        //comandos(b);
    }

   /* public void comandos(String[] a){   
            if(a[0].equals("double")){
                op.criaVariavel(a[1]);
            }
    }*/




}
