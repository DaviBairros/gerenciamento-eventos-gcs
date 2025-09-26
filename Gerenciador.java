import java.util.ArrayList;
import java.util.Scanner;
import java.util.Comparator;

public class GerenciadorEventos {
    private ArrayList<Evento> eventos;
    private ArrayList<Participante> participantes;

    public GerenciadorEventos(ArrayList<Evento> eventos) {
        this.eventos = eventos;
        this.participantes = new ArrayList<>();
    }

    public Evento buscarEventoPorNome(String nome) {
        for (Evento e : eventos) {
            if (e.getNome().equalsIgnoreCase(nome)) {
                return e;
            }
        }
        return null;
    }

    public Participante buscarParticipantePorCpf(String cpf) {
        for (Participante p : participantes) {
            if (p.getCpf().equals(cpf)) {
                return p;
            }
        }
        return null;
    }

    public Ingresso buscarIngressoPorCodigo(String codigo) {
        for (Evento e : eventos) {
            for (Ingresso i : e.getIngressos()) {
                if (i.getCodigo().equalsIgnoreCase(codigo)) {
                    return i;
                }
            }
        }
        return null;
    }

    public void cadastrarParticipante(Scanner teclado) {
        System.out.println("===== CADASTRO DE PARTICIPANTE =====");
        System.out.print("Digite o nome: ");
        String nome = teclado.nextLine();
        System.out.print("Digite o CPF: ");
        String cpf = teclado.nextLine();

        if (buscarParticipantePorCpf(cpf) != null) {
            System.out.println("Erro: Participante com este CPF já está cadastrado.");
            return;
        }

        participantes.add(new Participante(nome, cpf));
        System.out.println("Participante cadastrado com sucesso!");
    }

    public void cadastrarEvento(Scanner teclado) {
        System.out.println("===== CADASTRO DE NOVO EVENTO =====");

        System.out.print("Digite o código do evento: ");
        int codigo;
        if (teclado.hasNextInt()) {
            codigo = teclado.nextInt();
            teclado.nextLine();
        } else {
            System.out.println("Erro: O código do evento deve ser um número.");
            teclado.nextLine();
            return;
        }

        System.out.print("Digite o nome do evento: ");
        String nome = teclado.nextLine();

        System.out.print("Digite a data (ex: DD/MM/AAAA): ");
        String dataTexto = teclado.nextLine();

        System.out.print("Digite o mês (MM): ");
        int mes = teclado.nextInt();
        System.out.print("Digite o ano (AAAA): ");
        int ano = teclado.nextInt();

        System.out.print("Digite a lotação máxima: ");
        int lotacaoMaxima = teclado.nextInt();

        System.out.print("Digite o valor do ingresso: R$ ");
        double valorIngresso = teclado.nextDouble();
        teclado.nextLine();

        int qtdEspecial = (int) Math.round(lotacaoMaxima * 0.15);
        int qtdNormal = lotacaoMaxima - qtdEspecial;

        if (lotacaoMaxima == 0) {
            System.out.println("Erro: Lotação máxima deve ser maior que zero.");
            return;
        }

        Ingresso[] ingressos = new Ingresso[lotacaoMaxima];
        int indiceIngresso = 0;

        for (int i = 0; i < qtdNormal; i++) {
            String cod = String.format("%03d-%d", codigo, (i + 1));
            ingressos[indiceIngresso++] = new Ingresso(cod, "NORMAL");
        }

        for (int i = 0; i < qtdEspecial; i++) {
            String cod = String.format("%03d-%dE", codigo, (qtdNormal + i + 1));
            ingressos[indiceIngresso++] = new Ingresso(cod, "ESPECIAL");
        }

        Evento novoEvento = new Evento(codigo, nome, dataTexto, mes, ano, lotacaoMaxima, valorIngresso, ingressos);

        this.eventos.add(novoEvento);

        System.out.println("Evento '" + nome + "' cadastrado com sucesso! Lotação: " + lotacaoMaxima);
        System.out.println("Ingressos gerados (Normais: " + qtdNormal + ", Especiais: " + qtdEspecial + ").");
    }

    public void listarEventos() {
        System.out.println("===== Eventos Disponíveis =====");
        if (eventos.isEmpty()) {
            System.out.println("Nenhum evento cadastrado.");
            return;
        }
        for (Evento e : eventos) {
            System.out.println(e.getCodigo() + " - " + e.getNome() + " em " + e.getDataTexto() +
                    " (Lotação: " + e.getLotacaoMaxima() + ")");
        }
        System.out.println("===============================");
    }

    public void listarParticipantes() {
        System.out.println("===== Participantes Cadastrados =====");
        for (Participante p : participantes) {
            // Assume que o toString() de Participante e Pessoa está correto.
            System.out.println(p.toString());
        }
        System.out.println("=====================================");
    }

    public void emitirIngresso(Scanner teclado) {
        System.out.println("===== EMISSÃO DE INGRESSO =====");
        System.out.print("Digite o nome do Evento: ");
        String nomeEvento = teclado.nextLine();
        Evento evento = buscarEventoPorNome(nomeEvento);

        if (evento == null) {
            System.out.println("Erro: Evento não encontrado.");
            return;
        }

        System.out.print("Digite o tipo do ingresso (NORMAL/ESPECIAL): ");
        String tipo = teclado.nextLine().toUpperCase();

        System.out.print("Digite o CPF do Participante (para vincular o ingresso): ");
        String cpfParticipante = teclado.nextLine();
        Participante p = buscarParticipantePorCpf(cpfParticipante);

        if (p == null) {
            System.out.println("Erro: Participante com CPF " + cpfParticipante + " não encontrado. Cadastre-o primeiro.");
            return;
        }

        emitirIngressoServico(evento, tipo, p);
    }

    private void emitirIngressoServico(Evento evento, String tipo, Participante p) {
        Ingresso ingressoDisponivel = null;
        for (Ingresso i : evento.getIngressos()) {
            if (!i.isVendido() && !i.isCancelado() && i.getTipo().equalsIgnoreCase(tipo)) {
                ingressoDisponivel = i;
                break;
            }
        }

        if (ingressoDisponivel != null) {
            ingressoDisponivel.vender();
            p.adicionarIngresso(ingressoDisponivel);

            System.out.println("Ingresso emitido com sucesso!");
            System.out.println("==============================================");
            System.out.println("EVENTO: " + evento.getNome());
            System.out.println("TIPO: " + ingressoDisponivel.getTipo());
            System.out.println("CÓDIGO: " + ingressoDisponivel.getCodigo());
            System.out.println("PARTICIPANTE: " + p.getNome());
            System.out.println("==============================================");

        } else {
            System.out.println("Não há ingressos " + tipo + " disponíveis para o evento " + evento.getNome() + ".");
        }
    }

    public void registrarEntrada(Scanner teclado) {
        System.out.println("===== REGISTRO DE ENTRADA =====");
        System.out.print("Digite o código do ingresso: ");
        String codigo = teclado.nextLine();

        Ingresso i = buscarIngressoPorCodigo(codigo);
        if (i == null) {
            System.out.println("Erro: Ingresso não encontrado.");
        } else if (!i.isVendido()) {
            System.out.println("Erro: Ingresso não foi vendido.");
        } else if (i.isCancelado()) {
            System.out.println("Erro: Ingresso foi cancelado.");
        } else if (i.isUsado()) {
            System.out.println("Aviso: Ingresso já foi registrado como usado.");
        } else {
            i.usar(); // Marca como presente
            System.out.println("Entrada registrada com sucesso! Participante liberado.");
        }
    }

    public void cancelarIngresso(String codigo) {
        Ingresso i = buscarIngressoPorCodigo(codigo);

        if (i == null) {
            System.out.println("Erro: Ingresso não encontrado.");
        } else if (i.isUsado()) {
            System.out.println("Erro: Não é possível cancelar. O ingresso já foi usado na entrada.");
        } else if (i.isCancelado()) {
            System.out.println("Aviso: Ingresso já está cancelado.");
        } else {
            i.cancelar();
            System.out.println("Ingresso " + codigo + " cancelado com sucesso!");
        }
    }

    public void listarParticipantesDeEvento(Scanner teclado) {
        System.out.println("===== PARTICIPANTES POR EVENTO =====");
        System.out.print("Digite o nome do Evento: ");
        String nomeEvento = teclado.nextLine();
        Evento evento = buscarEventoPorNome(nomeEvento);

        if (evento == null) {
            System.out.println("Erro: Evento não encontrado.");
            return;
        }

        System.out.println("Participantes que compraram ingresso para " + evento.getNome() + ":");
        for (Participante p : participantes) {
            for (Ingresso i : p.getIngressos()) {
                if (i.getCodigo().startsWith(String.format("%03d-", evento.getCodigo())) && !i.isCancelado())
                {
                    String status = i.isUsado() ? "PRESENTE" : "AUSENTE";
                    System.out.println("- " + p.getNome() + " (CPF: " + p.getCpf() + ") - Status: " + status);
                    break;
                }
            }
        }
        System.out.println("======================================");
    }

    // Opção 9: Procurar Eventos por Nome
    public void procurarEventosPorNome(Scanner teclado) {
        System.out.println("===== PROCURAR EVENTOS =====");
        System.out.print("Digite parte do nome do evento: ");
        String termo = teclado.nextLine().toLowerCase();

        System.out.println("Resultados:");
        int count = 0;
        for (Evento e : eventos) {
            if (e.getNome().toLowerCase().contains(termo)) {
                System.out.println("- " + e.getNome() + " em " + e.getDataTexto());
                count++;
            }
        }
        if (count == 0) {
            System.out.println("Nenhum evento encontrado com o termo '" + termo + "'.");
        }
        System.out.println("============================");
    }

    public void gerarRelatorioFinanceiro() {
        System.out.println("===== GERAR RELATÓRIO FINANCEIRO =====");
        Scanner teclado = new Scanner(System.in);
        System.out.print("Digite o Mês (1-12): ");
        int mes = teclado.nextInt();
        System.out.print("Digite o Ano: ");
        int ano = teclado.nextInt();
        teclado.nextLine();

        imprimirRelatorioFinanceiro(mes, ano);
    }

    public void imprimirRelatorioFinanceiro(int mes, int ano) {
        String relatorio = "";
        double totalMes = 0.0;
        int eventosNoMes = 0;

        relatorio += "\n===== RELATÓRIO FINANCEIRO MENSAL =====\n";
        relatorio += "Mês: " + mes + " / Ano: " + ano + "\n";
        relatorio += "====================================================\n";

        for (Evento e : eventos) {
            if (e.getMes() == mes && e.getAno() == ano) {
                eventosNoMes++;

                int vendidosTotal = e.getIngressosVendidos();
                double arrecadacao = e.getArrecadacao();
                totalMes += arrecadacao;

                relatorio += "Evento: " + e.getNome() + " (" + e.getDataTexto() + ")\n";
                relatorio += "  Ingressos vendidos: " + vendidosTotal + " de " + e.getLotacaoMaxima() + "\n";
                relatorio += "  > Ocupação: " + String.format("%.2f", e.getPercentualOcupacao()) + "%\n";
                relatorio += "  > Normais Vendidos: " + e.getIngressosVendidosNormais() + "\n";
                relatorio += "  > Especiais Vendidos: " + e.getIngressosVendidosEspeciais() + "\n";
                relatorio += "  Arrecadação: R$ " + String.format("%.2f", arrecadacao) + "\n";
                relatorio += "----------------------------------------------------\n";
            }
        }

        relatorio += "Eventos no período: " + eventosNoMes + "\n";
        relatorio += "ARRECADAÇÃO TOTAL DO MÊS: R$ " + String.format("%.2f", totalMes) + "\n";
        relatorio += "====================================================\n";

        System.out.println(relatorio);
    }

    public void consultarDetalhesEvento(Scanner teclado) {
        System.out.println("===== CONSULTAR DETALHES DO EVENTO =====");
        System.out.print("Digite o nome do Evento: ");
        String nomeEvento = teclado.nextLine();
        Evento evento = buscarEventoPorNome(nomeEvento);

        if (evento != null) {
            evento.mostrarDetalhes();
        } else {
            System.out.println("Erro: Evento não encontrado.");
        }
    }

    public void rankingEventosMaisLucrativos() {
        System.out.println("\n===== Ranking dos Eventos Mais Lucrativos =====");

        ArrayList<Evento> copia = new ArrayList<>(eventos);

        copia.sort(Comparator.comparing(Evento::getArrecadacao).reversed());

        for (int i = 0; i < copia.size(); i++) {
            System.out.println((i + 1) + "º " + copia.get(i).getNome() +
                    " - R$ " + String.format("%.2f", copia.get(i).getArrecadacao()));
        }
        System.out.println("==================================================\n");
    }

    public ArrayList<Evento> getEventos() {
        return eventos;
    }
}
