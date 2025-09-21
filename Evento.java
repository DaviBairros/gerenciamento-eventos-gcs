import java.util.ArrayList;

public class Evento {
    private int codigo;
    private String nome;
    private String data;
    private double valorIngresso;
    private String responsavel;
    private ArrayList<Ingresso> ingressos;

    public Evento(int codigo, String nome, String data, double valorIngresso, String responsavel, int quantidade) {
        this.codigo = codigo;
        this.nome = nome;
        this.data = data;
        this.valorIngresso = valorIngresso;
        this.responsavel = responsavel;
        this.ingressos = new ArrayList<>();


        int especiais = (quantidade * 15) / 100;
        int normais = quantidade - especiais;


        for (int i = 1; i <= normais; i++) {
            ingressos.add(new Ingresso(codigo + "-" + String.format("%03d", i), false));
        }

        for (int i = 1; i <= especiais; i++) {
            ingressos.add(new Ingresso(codigo + "-" + String.format("%03dE", i), true));
        }
    }

    public int getCodigo() {
        return codigo;    }

    public String getNome() {
        return nome;
    }

    public ArrayList<Ingresso> getIngressos() {
        return ingressos;
    }

    public Ingresso procurarIngressoPorCodigo(String codigoIngresso) {
        for (Ingresso ing : ingressos) {
            if (ing.getCodigo().equals(codigoIngresso)) {
                return ing;
            }
        }
        return null;
    }

    @Override
    public String toString() {
        return "Evento [Codigo: " + codigo + ", Nome: " + nome +
                ", Data: " + data + ", Valor: " + valorIngresso +
                ", Responsável: " + responsavel +
                ", Total ingressos: " + ingressos.size() + "]";
    }
}
