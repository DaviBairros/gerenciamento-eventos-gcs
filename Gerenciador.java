import java.util.ArrayList;

public class GerenciadorEventos {
    private ArrayList<Evento> eventos = new ArrayList<>();
    private ArrayList<Participante> participantes = new ArrayList<>();
    private int proximoCodigo = 1;

    public Evento cadastrarEvento(String nome, String data, double valor, String responsavel, int quantidade) {
        Evento ev = new Evento(proximoCodigo++, nome, data, valor, responsavel, quantidade);
        eventos.add(ev);
        return ev;
    }

    public Participante cadastrarParticipante(String nome, String email, String cpf) {
        Participante p = new Participante(nome, email, cpf);
        participantes.add(p);
        return p;
    }

    public Participante procurarParticipantePorCpf(String cpf) {
        for (Participante p : participantes) {
            if (p.getCpf().equals(cpf)) {
                return p;
            }
        }
        return null;
    }

    public Ingresso emitirIngresso(int codigoEvento, int tipo, String cpf) {
        for (Evento ev : eventos) {
            if (ev.getCodigo() == codigoEvento) {
                for (Ingresso ing : ev.getIngressos()) {
                    if (ing.isEspecial() == (tipo == 2) && ing.getParticipante() == null) {
                        Participante p = procurarParticipantePorCpf(cpf);
                        if (p != null) {
                            ing.setParticipante(p);
                            p.adicionarIngresso(ing);
                            return ing;
                        }
                    }
                }
            }
        }
        return null;
    }

    public boolean registrarEntrada(int codigoEvento, String codigoIngresso) {
        for (Evento ev : eventos) {
            if (ev.getCodigo() == codigoEvento) {
                Ingresso ing = ev.procurarIngressoPorCodigo(codigoIngresso);
                if (ing != null && ing.getParticipante() != null) {
                    ing.registrarEntrada();
                    return true;
                }
            }
        }
        return false;
    }

    // Funcionalidade extra 1
    public boolean cancelarIngresso(int codigoEvento, String codigoIngresso) {
        for (Evento ev : eventos) {
            if (ev.getCodigo() == codigoEvento) {
                Ingresso ingresso = ev.procurarIngressoPorCodigo(codigoIngresso);
                if (ingresso != null && ingresso.getParticipante() != null) {
                    ingresso.getParticipante().getIngressos().remove(ingresso);
                    ingresso.setParticipante(null);
                    return true;
                }
            }
        }
        return false;
    }

    // Funcionalidade extra 2
    public void listarParticipantesDeEvento(int codigoEvento) {
        for (Evento ev : eventos) {
            if (ev.getCodigo() == codigoEvento) {
                System.out.println("Participantes do evento: " + ev.getNome());
                boolean encontrou = false;
                for (Ingresso ing : ev.getIngressos()) {
                    if (ing.getParticipante() != null) {
                        System.out.println(ing.getParticipante().exibirInformacoes());
                        encontrou = true;
                    }
                }
                if (!encontrou) {
                    System.out.println("Nenhum participante registrado ainda.");
                }
                return;
            }
        }
        System.out.println("Evento não encontrado.");
    }

    public ArrayList<Evento> getEventos() {
        return eventos;
    }
}
