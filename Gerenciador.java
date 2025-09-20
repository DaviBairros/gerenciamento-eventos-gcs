import java.util.ArrayList;

public class GerenciadorEventos {
        private ArrayList<Evento> eventos;
        private ArrayList<Participante> participantes;

        public GerenciadorEventos() {
            eventos = new ArrayList<>();
            participantes = new ArrayList<>();
            inicializarDados();
        }

        public Evento cadastrarEvento(String nome, String data, double valor, String responsavel, int lotacao) {
            Evento e = new Evento(nome, data, valor, responsavel, lotacao);
            eventos.add(e);
            return e;
        }

        public ArrayList<Evento> listarEventos() {
            return eventos;
        }

        public ArrayList<Evento> procurarEventosPorNome(String parteNome) {
            ArrayList<Evento> encontrados = new ArrayList<>();
            for (Evento e : eventos) {
                if (e.getNome().toLowerCase().contains(parteNome.toLowerCase())) {
                    encontrados.add(e);
                }
            }
            return encontrados;
        }

        public Participante cadastrarParticipante(String nome, String email, int cpf) {
            Participante p = new Participante(nome, email, cpf);
            participantes.add(p);
            return p;
        }

        public Participante procurarParticipantePorCpf(int cpf) {
            for (Participante p : participantes) {
                if (p.getcpf()) return p;
            }
            return null;
        }

        public Ingresso emitirIngresso(int codigoEvento, int tipo, int cpf) {
            Evento evento = null;
            for (Evento e : eventos) {
                if (e.getCodigo() == codigoEvento) {
                    evento = e;
                    break;
                }
            }
            if (evento == null) return null;
            Participante p = procurarParticipantePorCpf(cpf);
            if (p == null) return null;
            return evento.emitirIngresso(tipo, p);
        }

        public boolean registrarEntrada(int codigoEvento, String codigoIngresso) {
            for (Evento e : eventos) {
                if (e.getCodigo() == codigoEvento) {
                    return e.registrarEntrada(codigoIngresso);
                }
            }
            return false;
        }

        private void cadastrarParticipante(String nom, String mail, String cpf) {
    }


        private void inicializarDados() {
            // Exemplos de participantes iniciais (quem se apresentaria)
            cadastrarParticipante("Baitaca",
                    "baitaca@gmail.com",
                    "150.432.765-02");

            cadastrarParticipante("Larry Ellison",
                    "larry@gmail.com",
                    "501.324.657-01");

            cadastrarParticipante("Christian Santiago Lo Iacono",
                    "christian@gmail.com",
                    "051.234.567-00");

            // Exemplos de eventos iniciais (os nomes aqui são os resposáveis pela organização)
            cadastrarEvento("Show da semana farroupilha",
                    "2025-09-30",
                    70.0,
                    "Guri de Uruguaiana",
                    50);

            cadastrarEvento("Palestra de Tecnologia",
                    "2025-10-15",
                    0.0,
                    "Prof. Daniel",
                    70);

            cadastrarEvento("Palestra de teologia ",
                    "2025-11-20",
                    200.0,
                    "Guilerme",
                    100);
        }
}

