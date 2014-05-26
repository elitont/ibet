
import java.util.Scanner;
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
                System.out.println("Erro 3: Sintaxe incorreta.");
                break;
            case 4: 
                System.out.println("Erro 4: Tentando imprimir variável inexistente.");
                break;
            case 5: 
                System.out.println("Erro 5: Sinal de atribuição inválido.");
                break;
            case 6: 
                System.out.println("Erro 6: Atribuição à variável inexistente.");
                break;
            case 7: 
                System.out.println("Erro 7: Atribuição inválida.");
                break;
            case 8: 
                System.out.println("Erro 8: Operação inválida.");
                break;
            case 9: 
                System.out.println("Erro 9: Erro de escopo.");
                break;
            case 10: 
                System.out.println("Erro 10: Comparação inválida.");
                break;
        }
        System.exit(1);
    }

    void scan(String var){
        int end = verificaVariavel(var);
        if(end != 0){
            Scanner s = new Scanner(System.in);
            this.vetor[end].setValor(s.nextDouble());
        }
        else erro(6);
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

    public Double getDouble(String operando){
        double num = 0.0;
        try{
            num = Double.parseDouble(operando);
            return num;
        }catch(Exception e){
            this.erro(7);
        }
       return num;
    }

    public double Ula(Double op1, String operador, Double op2){
        switch(operador){
            case "+":
                return op1+op2;
            case "-":
                return op1-op2;
            case "*":
                return op1*op2;
            case "/":
                return op1/op2;
            case "%":
                return op1%op2;
            default:
                this.erro(8);
        }
        return op1;
    }

    public void atribuicao(String[] expressao){
        if(expressao[2].equals("=") == true){
            int end = this.verificaVariavel(expressao[1]);
            int end2 = this.verificaVariavel(expressao[3]);
            double operando1;
            if(end == 0){                                   //testa se o destino é variavel
                    this.erro(6);
                }
            if(end2 != 0){
                operando1 = this.vetor[end2].getValor(); //pega o valor da variavel
            }
            else{  
                operando1 = this.getDouble(expressao[3]); //pega double
            }
            if(expressao.length == 6){
                double operando2;
                int end3 = this.verificaVariavel(expressao[5]);
                if(end3 != 0){
                    operando2 = this.vetor[end3].getValor();
                }
                else{
                    operando2 = this.getDouble(expressao[5]);
                }
                this.vetor[end].setValor(this.Ula(operando1, expressao[4], operando2));

            }
            else{
                this.vetor[end].setValor(operando1);
            }
        }
        else this.erro(5);
    }

    
    public boolean condicao(String[] expressao){
        int end1 = this.verificaVariavel(expressao[1]);
        int end2 = this.verificaVariavel(expressao[3]);
        double valor1, valor2;
        if(end1 == 0){
            valor1 = this.getDouble(expressao[1]);
        }else{
            valor1 = this.vetor[end1].getValor();
        }
        if(end2 == 0){
            valor2 = this.getDouble(expressao[3]);
        }else{
            valor2 = this.vetor[end2].getValor();
        }
        if(expressao[2].equals("==")){
            return (valor1 == valor2);
        }
        else if(expressao[2].equals("!=")){
            return (valor1 != valor2);
        }
        else if(expressao[2].equals(">=")){
            return (valor1 >= valor2);
        }
        else if(expressao[2].equals("<=")){
            return (valor1 <= valor2);
        }
        else if(expressao[2].equals(">")){
            return (valor1 > valor2);
        }
        else if(expressao[2].equals("<")){
            return (valor1 < valor2);
        }
        this.erro(10);
        return false;
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