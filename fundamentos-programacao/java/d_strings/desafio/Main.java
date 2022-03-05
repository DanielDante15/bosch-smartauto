package d_strings.desafio;

import java.util.Calendar;
import java.util.Date;
import java.util.Scanner;

public class Main {
    public static Date calculateDate(long dataSegundos, int minutos) {
        Date dataAdicionado = new Date(dataSegundos + (minutos*60*1000));
        return dataAdicionado; 
    }

    public static Trem entrarTrem(Scanner input, Trem trem) {
        System.out.println("Digite o nome do trem: ");
        if(input.hasNextLine())
            trem.nome = input.nextLine();
        while (true) {
            System.out.println("Digite a posicao (do Km 0 ate Km 10000) do trem "+trem.nome+": (Km)");
            if(input.hasNextLine())            
                trem.posicao = Double.parseDouble(input.nextLine());
                if (trem.posicao < 0 || trem.posicao > 10000)
                    System.out.println("A posicao deve ser entre o Km 0 e Km 10000!!, Digite novamente\n");
                else
                    break;
        }
        while (true) {
            System.out.println("Digite a velocidade do trem "+trem.nome+": (Km/h)");
            if(input.hasNextLine())
                trem.velocidade = Double.parseDouble(input.nextLine());
                if (trem.velocidade < 0 || trem.velocidade > 300)
                    System.out.println("A velocidade não pode ser negativa ou acima de 300 Km/h!! , Digite novamente\n");
                else
                    break;
        }
        return trem;
    }
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        while (true) {
            Trem tremA = new Trem();
            tremA = entrarTrem(input, tremA); // Chama o método entrarTrem passando o input e o objeto tremA como parâmetros
            System.out.println("\n\nAgora digite as informações do segundo trem: \n");
            Trem tremB = new Trem();
            tremB = entrarTrem(input, tremB);
            tremB.velocidade = tremB.velocidade*-1; // INVERTER A DIREÇÃO DO TREM PARA A COLISÃO
    
            tremA.exibirInfo(); // Acessa o método do objeto de exibir as informações
            tremB.exibirInfo();
            
            if (tremA.posicao > tremB.posicao) // Se o tremB estiver antes do tremA, eles nunca irão se encontrar pois ambos estão em direções opostas
                System.out.println("Os trens nunca irão se colidir");
            else {
                double tempo = (tremA.posicao-tremB.posicao)/(tremB.velocidade-tremA.velocidade);
                int tempo_min = ((int) tempo )* 60;
                double posicao_colisao = tremA.posicao+(tremA.velocidade*tempo);

                // Aqui é onde acontece a criação de um objeto a partir da classe Calendar para fazer contas envolvendo tempo e horário
                Calendar dataSaida = Calendar.getInstance();
                dataSaida.set(Calendar.MONTH, 2);
                dataSaida.set(Calendar.YEAR, 2022);
                dataSaida.set(Calendar.DAY_OF_MONTH, 1);
                dataSaida.set(Calendar.HOUR_OF_DAY, 17);
                dataSaida.set(Calendar.MINUTE, 0);
                dataSaida.set(Calendar.SECOND, 0);
                long dataSaidaSegundos = dataSaida.getTimeInMillis(); // Conversão do objeto Calendar em um inteiro em segundos, que conta quantos segundos se passaram desde 1970 até a data em questão

                Date dataDaSaida = new Date(dataSaidaSegundos); // Conversão do inteiro em um objeto da classe Date
                Date dataColisao = calculateDate(dataSaidaSegundos, tempo_min); // Chama o método que adiciona nas horas o tempo até a colisão dos trems para obter a a data da colisão dos trens
        
                System.out.println(String.format("A colisão dos trens acontecerá no KM %.2f e ocorrerá após %.1f horas, ", posicao_colisao, tempo));
                
                System.out.println("Data da saída: "+dataDaSaida);
                System.out.println("Data da colisão "+dataColisao);                
            }
            System.out.println("Deseja rodar o programa novamente? : S/N"); // Pergunta se o usuário quer rodar o programa de novo
            String opcao = input.nextLine().toUpperCase(); // Converte a entrada do usuário em letra maiúscula
            if (opcao.equals("S"))
                continue;
            else {
                System.out.println("FIM DO PROGRAMA");
                break;
            }
        }
        input.close();
    }
}
