import java.util.ArrayList;

public class Evento {

    private static int proximoCodigo = 100;
    private int codigo;
    private String nome;
    private String data;
    private double valor;
    private String responsavel;
    private int lotacaoMaxima;
    private int normaisDisponiveis;
    private int especiaisDisponiveis;

    private ArrayList<Ingresso> ingressosVendidos;

    public Evento(String nome, String data, double valor, String responsavel,
                  int lotacaoMaxima) {
        this.codigo = proximoCodigo++;
        this.nome = nome;
        this.data = data;
        this.valor = valor;
        this.responsavel = responsavel;
        this.lotacaoMaxima = lotacaoMaxima;
        this.ingressosVendidos = new ArrayList<>();

        int especiais = (lotacaoMaxima * 15) / 100;
        if ((lotacaoMaxima * 15) % 100 != 0) especiais++;
        this.especiaisDisponiveis = especiais;
        this.normaisDisponiveis = lotacaoMaxima - especiais;
    }

    public int getCodigo() {
        return codigo;
    }
    public String getNome() {
        return nome;
    }
    public String getData() {
        return data;
    }
    public double getValor() {
        return valor;
    }
    public String getResponsavel() {
        return responsavel;
    }
    public int getLotacaoMaxima() {
        return lotacaoMaxima;
    }

    public ArrayList<Ingresso> getIngressosVendidos() { return ingressosVendidos; }

    private String formatSeq(int num) {
        String texto = "" + num;

        while (texto.length() < 3) {
            texto = "0" + texto;
        }

        return texto;
    }

    public Ingresso emitirIngresso(int tipo, Participante p) {
        if (tipo == Ingresso.NORMAL) {
            if (normaisDisponiveis <= 0) return null;
            int seq = (lotacaoMaxima - normaisDisponiveis - especiaisDisponiveis) + 1;
            String codigoIng = codigo + "-" + formatSeq(seq);
            Ingresso ing = new Ingresso(codigoIng, tipo, p);
            ingressosVendidos.add(ing);
            p.adicionarIngresso(ing);
            normaisDisponiveis--;
            return ing;
        } else {
            if (especiaisDisponiveis <= 0) return null;
            int seq = (lotacaoMaxima - especiaisDisponiveis) + 1;
            String codigoIng = codigo + "-" + formatSeq(seq) + "E";
            Ingresso ing = new Ingresso(codigoIng, tipo, p);
            ingressosVendidos.add(ing);
            p.adicionarIngresso(ing);
            especiaisDisponiveis--;
            return ing;
        }
    }

    public boolean registrarEntrada(String codigoIng) {
        for (Ingresso i : ingressosVendidos) {
            if (i.getCodigo().equals(codigoIng) && !i.isEntradaRegistrada()) {
                i.registrarEntrada();
                return true;
            }
        }
        return false;
    }

    @Override
    public String toString() {
        return "Evento [codigo=" + codigo + ", nome=" + nome +
                ", data=" + data + ", valor=" + valor +
                ", responsavel=" + responsavel +
                ", lotação=" + lotacaoMaxima + "]";
    }
}

