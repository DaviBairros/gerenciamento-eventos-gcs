public class Ingresso {
    private String codigo;
    private String tipo;
    private boolean vendido;
    private boolean cancelado;
    private boolean usado;


    public Ingresso(String codigo, String tipo) {
        this.codigo = codigo;
        this.tipo = tipo;
        this.vendido = false;
        this.cancelado = false;
        this.usado = false;
    }

    public String getCodigo() {
        return codigo;
    }

    public String getTipo() {
        return tipo;
    }

    public boolean isVendido() {
        return vendido;
    }

    public boolean isCancelado() {
        return cancelado;
    }

    public boolean isUsado() {
        return usado;
    }

    public void vender() {
        if (!vendido) {
            vendido = true;
        }
    }

    public void cancelar() {
        if (vendido && !usado) {
            cancelado = true;
        }
    }
    public void usar() {
        if (vendido && !cancelado) {
            usado = true;
        }
    }
    @Override
    public String toString() {
        return "Ingresso:" +
                "codigo='" + codigo + '\'' +
                ", tipo='" + tipo + '\'' +
                ", vendido=" + vendido +
                ", cancelado=" + cancelado +
                ", usado=" + usado +
                '.';
    }
}
