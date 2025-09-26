import java.util.Scanner;
import java.util.ArrayList;

public class App {
    public static void main(String[] args) {

        Ingresso[] ingressosEvento1 = new Ingresso[50];
        for (int i = 0; i < 43; i++) {
            ingressosEvento1[i] = new Ingresso("001-" + (i + 1), "NORMAL");
        }
        for (int i = 43; i < 50; i++) {
            ingressosEvento1[i] = new Ingresso("001-" + (i + 1) + "E", "ESPECIAL");
        }
        Evento evento1 = new Evento(1,
                "Show do Baitaca",
                "15/10/2025",
                10,
                2025,
                50,
                50.0, ingressosEvento1);
        //evento1.getIngressos()[0].vender();
        //evento1.getIngressos()[1].vender();

        Ingresso[] ingressosEvento2 = new Ingresso[45];
        for (int i = 0; i < 38; i++) {
            ingressosEvento2[i] = new Ingresso("002-" + (i + 1), "NORMAL");
        }
        for (int i = 38; i < 45; i++) {
            ingressosEvento2[i] = new Ingresso("002-" + (i + 1) + "E", "ESPECIAL");
        }
        Evento evento2 = new Evento(2,
                "Palestra sobre Java",
                "20/10/2025",
                10, 2025,
                45,
                20.0, ingressosEvento2);
        //evento2.getIngressos()[0].vender();
        //evento2.getIngressos()[1].vender();

        Ingresso[] ingressosEvento3 = new Ingresso[70];
        for (int i = 0; i < 60; i++) {
            ingressosEvento3[i] = new Ingresso("003-" + (i + 1), "NORMAL");
        }
        for (int i = 60; i < 70; i++) {
            ingressosEvento3[i] = new Ingresso("003-" + (i + 1) + "E", "ESPECIAL");
        }
        Evento evento3 = new Evento(3,
                "Congresso de teologia",
                "05/11/2025",
                11,
                2025,
                70,
                30.0, ingressosEvento3);
        //evento3.getIngressos()[0].vender();
        //evento3.getIngressos()[1].vender();

        ArrayList<Evento> eventos = new ArrayList<>();
        eventos.add(evento1);
        eventos.add(evento2);
        eventos.add(evento3);

        GerenciadorEventos ger = new GerenciadorEventos(eventos);

        Scanner teclado = new Scanner(System.in);
        int opcao = -1;

        while (opcao != 0) {
            System.out.println("\f===== MENU =====");
            System.out.println("1. Cadastrar Participante");
            System.out.println("2. Cadastrar Evento");
            System.out.println("3. Listar Eventos");
            System.out.println("4. Listar Participantes");
            System.out.println("5. Emitir Ingresso");
            System.out.println("6. Registrar Entrada");
            System.out.println("7. Cancelar Ingresso");
            System.out.println("8. Listar Participantes de um Evento");
            System.out.println("9. Procurar eventos por nome");
            System.out.println("10. Relatório Financeiro");
            System.out.println("11. Consultar Detalhes de um Evento");
            System.out.println("0. Sair");
            System.out.print("Escolha: ");

            opcao = teclado.nextInt();
            teclado.nextLine();

            switch (opcao) {
                case 1 -> ger.cadastrarParticipante(teclado);
                case 2 -> ger.cadastrarEvento(teclado);
                case 3 -> ger.listarEventos();
                case 4 -> ger.listarParticipantes();
                case 5 -> ger.emitirIngresso(teclado);
                case 6 -> ger.registrarEntrada(teclado);
                case 7 -> {
                    System.out.print("Digite o código do ingresso: ");
                    String codigo = teclado.nextLine();
                    ger.cancelarIngresso(codigo);
                }
                case 8 -> ger.listarParticipantesDeEvento(teclado);
                case 9 -> ger.procurarEventosPorNome(teclado);
                case 10 -> ger.gerarRelatorioFinanceiro();
                case 11 -> ger.consultarDetalhesEvento(teclado);
                case 0 -> System.out.println("Saindo...");
                default -> System.out.println("Opção inválida.");
            }
        }
    }
}
