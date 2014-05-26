/**
 * Exemplo de interpretador.
 *
 * Esse é o ponto de partida para o interpretador da linguagem 'ibet'.
 * O único objetivo desse programa é instanciar um objeto ibet, que é
 * o interpretador da linguagem, passando para ele o caminho do arquivo
 * a ser interpretador. Para mais informações, veja o arquivo Ibet.java
 * 
 * Originalmente por: Fernando Bevilacqua <fernando.bevilacqua@uffs.edu.br>
 * Modificado por: Eliton Traverssini e Igor Beilner
 * <eliton.traverssini@gmail.com> <igor.beilner@hotmail.com>
 *
 */

import java.util.Scanner;
import java.io.File;

class Ibet {
    public static void main(String args[]) {
        File f;
        Scanner s;
        Interpretador b;
        String linhas[] = new String[2000]; // arquivo pode ter, no máximo, 2000 linhas.
        String branco = new String();       // linhas em branco ignoradas
        try {
            // Referencia o arquivo. args[0] conterá os dados passados pela linha de comando.
            f = new File(args[0]);
            // Mandamos o Scanner ler a partir do arquivo.
            s = new Scanner(f);
            // Instanciamos o interpretador.
            b = new Interpretador();
            
            // Lemos todas as linhas do arquivo para dentro do
            // vetor "linhas".
            int i = 0;
            while(s.hasNext()) {
            branco = s.nextLine();
                if(branco.equals("\t") != true && branco.equals("") != true){  
                    linhas[i] = branco;
                    if(linhas[i].equals("") != true){
                        i++;
                    }
                }
            }
            
            // Inicializamos o interpretador com o vetor de linhas. A partir
            // desse ponto, o objeto "b" irá interpretar o código lido do arquivo.
            b.interpreta(linhas);
            
        } catch (Exception e) {
            System.out.println("Nao consegui ler o arquivo: " + (args.length > 0 ? args[0] : "(desconhecido)"));
            System.out.println("Uso:");
            System.out.println("    java Ibet /caminho/para/arquivo.ibet");
        }
        
    }
}
