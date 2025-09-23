import java.util.ArrayList;

public class GerenciadorEventos {
    private ArrayList<Evento> eventos;
    private ArrayList<Participante> participantes;

    public GerenciadorEventos() {
        this.eventos = new ArrayList<>();
        this.participantes = new ArrayList<>();
        inicializarDados();
    }


    private void inicializarDados() {

        cadastrarParticipante("Carlos Vinícius",
                "carlosvini@gmail.com",
                "12345678900");

        cadastrarParticipante("André Henrique",
                "andrehrqe@mail.com",
                "98765432100");

        cadastrarParticipante("Alysson Edward",
                "alyssinedwr@mail.com",
                "55566677700");


        cadastrarEvento("Show do Baitaca",
                "Parque Eduardo Gomes",
                "15/11/2025",
                "Produtora USADISCOS",
                50,
                40.0);

        cadastrarEvento("Palestra sobre Java",
                "Auditório 516",
                "20/11/2025",
                "Universidade PUCRS",
                40,
                20.0);

        cadastrarEvento("Palestra sobre teologia",
                "Igreja evangelica do caminho",
                "25/11/2025",
                "Pastor Christian",
                90,
                15.0);
    }


    public Participante cadastrarParticipante(String nome, String email, String cpf) {
        Participante p = new Participante(nome, email, cpf);
        participantes.add(p);
        return p;
    }

    public void listarParticipantes() {
        if (participantes.isEmpty()) {
            System.out.println("Nenhum participante cadastrado.");
            return;
        }
        for (Participante p : participantes) {
            System.out.println(p.exibirInformacoes());
        }
    }

    public Participante procurarParticipantePorCpf(String cpf) {
        for (Participante p : participantes) {
            if (p.getCpf().equals(cpf)) {
                return p;
            }
        }
        return null;
    }

    public Evento cadastrarEvento(String titulo, String local, String data, String responsavel,
                                  int capacidade, double valor) {
        Evento e = new Evento(titulo, local, data, responsavel, capacidade, valor);
        eventos.add(e);
        return e;
    }

    public void listarEventos() {
        if (eventos.isEmpty()) {
            System.out.println("Nenhum evento cadastrado.");
            return;
        }
        for (Evento e : eventos) {
            System.out.println(e);
        }
    }

    public Evento procurarEventoPorCodigo(int codigoEvento) {
        for (Evento e : eventos) {
            if (e.getCodigo() == codigoEvento) return e;
        }
        return null;
    }


    public void procurarEventosPorNome(String parteNome) {
        boolean achou = false;
        String consulta = parteNome.toLowerCase();
        for (Evento e : eventos) {
            if (e.getTitulo().toLowerCase().contains(consulta)) {
                System.out.println(e);
                achou = true;
            }
        }
        if (!achou) System.out.println("Nenhum evento encontrado para: " + parteNome);
    }


    public Ingresso emitirIngresso(int codigoEvento, boolean especial, String cpf) {
        Evento evento = procurarEventoPorCodigo(codigoEvento);
        if (evento == null) return null;

        Participante participante = procurarParticipantePorCpf(cpf);
        if (participante == null) return null;


        for (Ingresso ing : evento.getIngressosVendidos()) {
            if (ing.getParticipante() == null && ing.isEspecial() == especial) {
                ing.setParticipante(participante);
                participante.adicionarIngresso(ing);
                return ing;
            }
        }


        if (evento.getIngressosVendidos().size() < evento.getCapacidade()) {
            Ingresso novo = new Ingresso(codigoEvento, especial);
            novo.setParticipante(participante);
            evento.adicionarIngresso(novo);
            participante.adicionarIngresso(novo);
            return novo;
        }


        return null;
    }


    public boolean registrarEntrada(int codigoEvento, String codigoIngresso) {
        Evento evento = procurarEventoPorCodigo(codigoEvento);
        if (evento == null) return false;

        Ingresso ing = evento.procurarIngressoPorCodigo(codigoIngresso);
        if (ing == null) return false;
        if (ing.getParticipante() == null) return false;

        if (!ing.isEntradaRegistrada()) {
            ing.registrarEntrada();
            return true;
        }
        return false;
    }

    public boolean cancelarIngresso(int codigoEvento, String codigoIngresso) {
        Evento evento = procurarEventoPorCodigo(codigoEvento);
        if (evento == null) return false;

        Ingresso ing = evento.procurarIngressoPorCodigo(codigoIngresso);
        if (ing == null) return false;

        Participante part = ing.getParticipante();
        if (part == null) return false;


        part.getIngressos();
        ing.setParticipante(null);

        return true;
    }


    public void listarParticipantesDeEvento(int codigoEvento) {
        Evento evento = procurarEventoPorCodigo(codigoEvento);
        if (evento == null) {
            System.out.println("Evento não encontrado (codigo " + codigoEvento + ").");
            return;
        }

        boolean encontrou = false;
        for (Ingresso ing : evento.getIngressosVendidos()) {
            Participante p = ing.getParticipante();
            if (p != null) {
                System.out.println(p.exibirInformacoes() + " | Ingresso: " + ing.getCodigo() +
                        " | Tipo: " + (ing.isEspecial() ? "Especial" : "Normal"));
                encontrou = true;
            }
        }
        if (!encontrou) {
            System.out.println("Nenhum participante com ingresso para o evento " + evento.getTitulo() + ".");
        }
    }


    public ArrayList<Evento> getEventos() {
        return eventos;
    }

    public ArrayList<Participante> getParticipantes() {
        return participantes;
    }
}
