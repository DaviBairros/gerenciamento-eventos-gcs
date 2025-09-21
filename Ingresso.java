public class Ingresso {
    private String codigo;
    private boolean especial;
    private Participante participante;
    private boolean entradaRegistrada;

    public Ingresso(String codigo, boolean especial) {
        this.codigo = codigo;
        this.especial = especial;
        this.participante = null;
        this.entradaRegistrada = false;
    }

    public String getCodigo() {
        return codigo;
    }

    public boolean isEspecial() {
        return especial;
    }

    public Participante getParticipante() {
        return participante;
    }

    public void setParticipante(Participante participante) {
        this.participante = participante;
    }

    public boolean isEntradaRegistrada() {
        return entradaRegistrada;
    }

    public void registrarEntrada() {
        this.entradaRegistrada = true;
    }

    @Override
    public String toString() {
        return "Ingresso [Codigo: " + codigo +
                ", Especial: " + (especial ? "Sim" : "Não") +
                ", Participante: " + (participante != null ? participante.getNome() : "Nenhum") + "]";
    }
}
