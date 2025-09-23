import java.util.Scanner;

public class App {
    public static void main(String[] args) {
        Scanner teclado = new Scanner(System.in);
        GerenciadorEventos ger = new GerenciadorEventos();

        int op;
        do {
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
            System.out.println("0. Sair");
            System.out.print("Escolha: ");
            op = teclado.nextInt();
            teclado.nextLine();

            switch (op) {
                case 1:
                    System.out.print("Nome: ");
                    String pn = teclado.nextLine();
                    System.out.print("Email: ");
                    String pe = teclado.nextLine();
                    System.out.print("CPF: ");
                    String pc = teclado.nextLine();
                    Participante p = ger.cadastrarParticipante(pn, pe, pc);
                    System.out.println("Participante cadastrado: " + p.exibirInformacoes());
                    break;

                case 2:
                    System.out.print("Título: ");
                    String titulo = teclado.nextLine();
                    System.out.print("Local: ");
                    String local = teclado.nextLine();
                    System.out.print("Data (dd/mm/aaaa): ");
                    String data = teclado.nextLine();
                    System.out.print("Responsável: ");
                    String responsavel = teclado.nextLine();
                    System.out.print("Capacidade: ");
                    int capacidade = teclado.nextInt();
                    System.out.print("Valor ingresso: ");
                    double valor = teclado.nextDouble();
                    teclado.nextLine();
                    Evento ev = ger.cadastrarEvento(titulo, local, data, responsavel, capacidade, valor);
                    System.out.println("Evento cadastrado: " + ev);
                    break;

                case 3:
                    System.out.println("=== Lista de Eventos ===");
                    ger.listarEventos();
                    break;

                case 4:
                    System.out.println("=== Lista de Participantes ===");
                    ger.listarParticipantes();
                    break;

                case 5:
                    System.out.print("Código evento: ");
                    int ce = teclado.nextInt();
                    teclado.nextLine();
                    System.out.print("Tipo (1=Normal, 2=Especial): ");
                    int t = teclado.nextInt();
                    teclado.nextLine();
                    System.out.print("CPF participante: ");
                    String cpf = teclado.nextLine();
                    Ingresso ing = ger.emitirIngresso(ce, t == 2, cpf);
                    if (ing != null) System.out.println("Emitido: " + ing);
                    else System.out.println("Erro na emissão (evento lotado, participante não encontrado, ou sem ingresso disponível).");
                    break;

                case 6:
                    System.out.print("Código evento: ");
                    int ce2 = teclado.nextInt();
                    teclado.nextLine();
                    System.out.print("Código ingresso: ");
                    String ci = teclado.nextLine();
                    if (ger.registrarEntrada(ce2, ci)) System.out.println("Entrada registrada com sucesso.");
                    else System.out.println("Falha ao registrar entrada (ingresso inválido, sem participante ou já registrado).");
                    break;

                case 7:
                    System.out.print("Código evento: ");
                    int ce3 = teclado.nextInt();
                    teclado.nextLine();
                    System.out.print("Código ingresso: ");
                    String ci2 = teclado.nextLine();
                    if (ger.cancelarIngresso(ce3, ci2)) System.out.println("Ingresso cancelado com sucesso (agora disponível).");
                    else System.out.println("Falha ao cancelar ingresso.");
                    break;

                case 8:
                    System.out.print("Código evento: ");
                    int ce4 = teclado.nextInt();
                    teclado.nextLine();
                    ger.listarParticipantesDeEvento(ce4);
                    break;

                case 9:
                    System.out.print("Texto a buscar no nome do evento: ");
                    String q = teclado.nextLine();
                    ger.procurarEventosPorNome(q);
                    break;

                case 0:
                    System.out.println("Saindo...");
                    break;

                default:
                    System.out.println("Opção inválida!");
            }

        } while (op != 0);

    }
}
