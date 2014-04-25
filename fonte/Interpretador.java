/**
 * Exemplo de interpretador.
 *
 * Esse código é um exemplo de interpretador para a linguagem 'Blah'. Esse programa
 * não faz qualquer interpretação, ele apenas lê o conteúdo de um arquivo que foi
 * passado pela linha de comando.
 *
 * Por Fernando Bevilacqua <fernando.bevilacqua@uffs.edu.br>
 */

class Interpretador {
    private String linhas[];
    
    public void interpreta(String l[]) {
        this.linhas = l;
        
        for(int i = 0; i < this.linhas.length; i++) {
            if(this.linhas[i] != null) {
                // TODO: interpretar a linha
                System.out.println("Linha " + (i + 1) + ": " + this.linhas[i]);
            }
        }
    }
}