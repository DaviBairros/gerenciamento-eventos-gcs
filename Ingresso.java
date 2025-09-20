public class Ingresso {
    public static final int NORMAL = 1;
    public static final int ESPECIAL = 2;

    private String codigo;
    private int tipo;
    private Participante participante;
    private boolean entradaRegistrada = false;

    public Ingresso(String codigo, int tipo, Participante participante) {
        this.codigo = codigo;
        this.tipo = tipo;
        this.participante = participante;
    }

    public String getCodigo() {
        return codigo;
    }
    public int getTipo() {
        return tipo;
    }
    public Participante getParticipante() {
        return participante;
    }
    public boolean isEntradaRegistrada() {
        return entradaRegistrada;
    }
    public void registrarEntrada() {
        this.entradaRegistrada = true;
    }

    @Override
    public String toString() {
        String tipoStr = (tipo == NORMAL ? "NORMAL" : "ESPECIAL");
        String presenca = entradaRegistrada ? " (Presente)" : " (Ausente)";
        return "Ingresso [codigo=" + codigo + ", tipo=" + tipoStr +
                ", participante=" + participante.getNome() +
                ", CPF=" + participante.getcpf() + presenca + "]";
    }
}

