import java.util.ArrayList;

public class Evento {
    private static int contadorEventos = 1;
    private int codigo;
    private String titulo;
    private String local;
    private String data;
    private String responsavel;
    private int capacidade;
    private double valor;
    private ArrayList<Ingresso> ingressosVendidos;

    public Evento(String titulo, String local, String data, String responsavel, int capacidade, double valor) {
        this.codigo = contadorEventos++;
        this.titulo = titulo;
        this.local = local;
        this.data = data;
        this.responsavel = responsavel;
        this.capacidade = capacidade;
        this.valor = valor;
        this.ingressosVendidos = new ArrayList<>();
    }

    public int getCodigo() {
        return codigo;
    }

    public String getTitulo() {
        return titulo;
    }

    public String getLocal() {
        return local;
    }

    public String getData() {
        return data;
    }

    public String getResponsavel() {
        return responsavel;
    }

    public int getCapacidade() {
        return capacidade;
    }

    public double getValor() {
        return valor;
    }

    public ArrayList<Ingresso> getIngressosVendidos() {
        return ingressosVendidos;
    }


    public Ingresso procurarIngressoPorCodigo(String codigoIngresso) {
        for (Ingresso ing : ingressosVendidos) {
            if (ing.getCodigo().equals(codigoIngresso)) {
                return ing;
            }
        }
        return null;
    }


    public void adicionarIngresso(Ingresso ingresso) {
        if (ingressosVendidos.size() < capacidade) {
            ingressosVendidos.add(ingresso);
        }
    }

    @Override
    public String toString() {
        return "Evento: codigo=" + codigo +
                ", titulo='" + titulo + '\'' +
                ", local='" + local + '\'' +
                ", data='" + data + '\'' +
                ", responsavel='" + responsavel + '\'' +
                ", capacidade=" + capacidade +
                ", valor=" + valor +
                ", ingressosEmitidos=" + ingressosVendidos.size() +
                '.';
    }
}
