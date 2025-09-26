import java.util.ArrayList;

public class Participante extends Pessoa {
    private final ArrayList<Ingresso> ingressos;

    public Participante(String nome, String cpf) {
        super(nome, cpf);
        this.ingressos = new ArrayList<>();
    }


    public ArrayList<Ingresso> getIngressos() {
        return ingressos;
    }

    public void adicionarIngresso(Ingresso ingresso) {
        ingressos.add(ingresso);
    }
    @Override
    public String toString() {
        return super.toString() + " | Ingressos: " + ingressos.size();
    }
}
