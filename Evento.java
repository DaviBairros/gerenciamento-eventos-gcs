public class Evento {
    private int codigo;
    private String nome;
    private String dataTexto;
    private int mes;
    private int ano;
    private int lotacaoMaxima;
    private double valorIngresso;
    private Ingresso[] ingressos;

    public Evento(int codigo, String nome, String dataTexto, int mes, int ano, int lotacaoMaxima, double valorIngresso, Ingresso[] ingressos) {
        this.codigo = codigo;
        this.nome = nome;
        this.dataTexto = dataTexto;
        this.mes = mes;
        this.ano = ano;
        this.lotacaoMaxima = lotacaoMaxima;
        this.valorIngresso = valorIngresso;
        this.ingressos = ingressos;
    }

    public int getCodigo() {
        return codigo;
    }

    public String getNome() {
        return nome;
    }

    public int getLotacaoMaxima() {
        return lotacaoMaxima;
    }

    public Ingresso[] getIngressos() {
        return ingressos;
    }

    public String getDataTexto() {
        return dataTexto;
    }

    public int getMes() {
        return mes;
    }

    public int getAno() {
        return ano;
    }

    public double getValorIngresso() {
        return valorIngresso;
    }


    public int getIngressosVendidos() {
        int count = 0;
        for (Ingresso i : ingressos) {
            if (i.isVendido() && !i.isCancelado()) count++;
        }
        return count;
    }

    public int getIngressosVendidosNormais() {
        int count = 0;
        for (Ingresso i : ingressos) {
            if (i.isVendido() && !i.isCancelado() && "NORMAL".equalsIgnoreCase(i.getTipo())) count++;
        }
        return count;
    }

    public int getIngressosVendidosEspeciais() {
        int count = 0;
        for (Ingresso i : ingressos) {
            if (i.isVendido() && !i.isCancelado() && "ESPECIAL".equalsIgnoreCase(i.getTipo())) count++;
        }
        return count;
    }

    public double getPercentualOcupacao() {
        int vendidos = getIngressosVendidos();
        if (lotacaoMaxima == 0) return 0.0;
        return (vendidos * 100.0) / lotacaoMaxima;
    }

    public double getArrecadacao() {
        int vendidos = getIngressosVendidos();
        return vendidos * valorIngresso;
    }

    public void mostrarDetalhes() {
        System.out.println("===== DETALHES DO EVENTO =====");
        System.out.println("  Código: " + this.codigo);
        System.out.println("  Nome: " + this.nome);
        System.out.println("  Data: " + this.dataTexto);
        System.out.println("  Lotação Máxima: " + this.lotacaoMaxima);
        System.out.println("  Valor Ingresso: R$ " + String.format("%.2f", this.valorIngresso));
        System.out.println("--- Estatísticas ---");
        System.out.println("  Ingressos Vendidos: " + getIngressosVendidos() + " (" + String.format("%.2f", getPercentualOcupacao()) + "% de ocupação)");
        System.out.println("  > Normais Vendidos: " + getIngressosVendidosNormais());
        System.out.println("  > Especiais Vendidos: " + getIngressosVendidosEspeciais());
        System.out.println("  Arrecadação Total: R$ " + String.format("%.2f", getArrecadacao()));
        System.out.println("==============================");
    }


    public int getQuantidadeIngressosNormais() {
        int count = 0;
        for (Ingresso i : ingressos) {
            if ("NORMAL".equalsIgnoreCase(i.getTipo())) count++;
        }
        return count;
    }
    public int getQuantidadeIngressosEspeciais() {
        int count = 0;
        for (Ingresso i : ingressos) {
            if ("ESPECIAL".equalsIgnoreCase(i.getTipo())) count++;
        }
        return count;
    }

    @Override
    public String toString() {
        return "Evento:" +
                "codigo=" + codigo +
                ", nome='" + nome + '\'' +
                ", data='" + dataTexto + '\'' +
                ", lotacaoMaxima=" + lotacaoMaxima +
                ", valorIngresso=" + valorIngresso +
                '.';
    }
}
