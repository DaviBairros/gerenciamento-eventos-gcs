public class Ingresso {
    private String codigo;
    private boolean especial;
    private Participante participante;
    private boolean entradaRegistrada;

    private static int contador = 1;


    public Ingresso(int codigoEvento, boolean especial) {
        this.codigo = codigoEvento + "-" + contador++;
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
        String p = (participante != null) ? participante.getNome() : "Nenhum";
        String tipo = especial ? "Especial" : "Normal";
        String pres = entradaRegistrada ? "Presente" : "Ausente";
        return "Ingresso: Codigo: " + codigo + ", Tipo: " + tipo + ", Participante: " + p + ", Status: " + pres + ".";
    }
}
