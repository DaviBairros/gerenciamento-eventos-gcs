import java.util.ArrayList;

public class Participante extends Pessoa {
    private ArrayList<Ingresso> ingressos;

    public Participante(String nome, String email, String cpf) {
        super(nome, email, cpf);
        this.ingressos = new ArrayList<>();
    }

    public ArrayList<Ingresso> getIngressos() {
        return ingressos;
    }

    public void adicionarIngresso(Ingresso ingresso) {
        ingressos.add(ingresso);
    }

    @Override
    public String exibirInformacoes() {
        return super.exibirInformacoes() + ", Ingressos: " + ingressos.size();
    }
}
