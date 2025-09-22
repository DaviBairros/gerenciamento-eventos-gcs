import java.util.Scanner;

public class App {
    public static void main(String[] args) {
        Scanner teclado = new Scanner(System.in);
        GerenciadorEventos ger = new GerenciadorEventos();
        int opcao;

        do {
            System.out.println("\f=== Sistema de Gerenciamento de Eventos ===");
            System.out.println("1 - Cadastrar evento");
            System.out.println("2 - Listar eventos");
            System.out.println("3 - Cadastrar participante");
            System.out.println("4 - Emitir ingresso");
            System.out.println("5 - Registrar entrada");
            System.out.println("6 - Consultar evento");
            System.out.println("7 - Cancelar ingresso");
            System.out.println("8 - Listar participantes de um evento");
            System.out.println("0 - Sair");
            System.out.print("Opção: ");
            opcao = teclado.nextInt();
            teclado.nextLine();

            switch (opcao) {
                case 1:
                    System.out.print("Nome do evento: ");
                    String nome = teclado.nextLine();
                    System.out.print("Data: ");
                    String data = teclado.nextLine();
                    System.out.print("Valor: ");
                    double valor = teclado.nextDouble();
                    teclado.nextLine();
                    System.out.print("Responsável: ");
                    String resp = teclado.nextLine();
                    System.out.print("Quantidade de ingressos: ");
                    int qtd = teclado.nextInt();
                    teclado.nextLine();

                    Evento ev = ger.cadastrarEvento(nome, data, valor, resp, qtd);
                    System.out.println("Evento cadastrado: " + ev);
                    break;

                case 2:
                    for (Evento e : ger.getEventos()) {
                        System.out.println(e);
                    }
                    break;

                case 3:
                    System.out.print("Nome: ");
                    String pn = teclado.nextLine();
                    System.out.print("Email: ");
                    String pe = teclado.nextLine();
                    System.out.print("CPF: ");
                    String pc = teclado.nextLine();
                    Participante p = ger.cadastrarParticipante(pn, pe, pc);
                    System.out.println("Participante cadastrado: " + p.exibirInformacoes());
                    break;

                case 4:
                    System.out.print("Código evento: ");
                    int ce = teclado.nextInt();
                    teclado.nextLine();
                    System.out.print("Tipo (1=Normal, 2=Especial): ");
                    int t = teclado.nextInt();
                    teclado.nextLine();
                    System.out.print("CPF participante: ");
                    String cpf = teclado.nextLine();
                    Ingresso ing = ger.emitirIngresso(ce, t, cpf);
                    if (ing != null) System.out.println("Emitido: " + ing);
                    else System.out.println("Erro na emissão.");
                    break;

                case 5:
                    System.out.print("Código evento: ");
                    int ce2 = teclado.nextInt();
                    teclado.nextLine();
                    System.out.print("Código ingresso: ");
                    String ci = teclado.nextLine();
                    if (ger.registrarEntrada(ce2, ci))
                        System.out.println("Entrada registrada.");
                    else
                        System.out.println("Erro ao registrar entrada.");
                    break;

                case 6:
                    System.out.print("Código evento: ");
                    int ce3 = teclado.nextInt();
                    teclado.nextLine();
                    for (Evento e : ger.getEventos()) {
                        if (e.getCodigo() == ce3) {
                            System.out.println(e);
                        }
                    }
                    break;

                case 7:
                    System.out.print("Código evento: ");
                    int ceCancel = teclado.nextInt();
                    teclado.nextLine();
                    System.out.print("Código do ingresso: ");
                    String ciCancel = teclado.nextLine();
                    if (ger.cancelarIngresso(ceCancel, ciCancel))
                        System.out.println("Ingresso cancelado com sucesso!");
                    else
                        System.out.println("Erro: ingresso não encontrado ou não estava emitido.");
                    break;

                case 8:
                    System.out.print("Código evento: ");
                    int ceList = teclado.nextInt();
                    teclado.nextLine();
                    ger.listarParticipantesDeEvento(ceList);
                    break;
            }
        } while (opcao != 0);

    }
}
