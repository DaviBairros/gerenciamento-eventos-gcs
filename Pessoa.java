public class Pessoa {
    private String nome;
    private String email;
    private int cpf;

    public Pessoa(String nome, String email, int cpf) {
        this.nome = nome;
        this.email = email;
        if (cpf == 11){
            this.cpf = cpf;
        }
    }

    public String getNome() {
        return nome;
    }

    public String getEmail() {
        return email;
    }

    public int getcpf() {
        return cpf;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setCpf(int cpf) {
        this.cpf = cpf;
    }

    public String exibirInformacoes() {
        return ("Pessoa [Nome: " + nome + ", Email: " + email + ", Cpf: " + cpf + "]");
    }

    @Override
    public String toString() {
        return exibirInformacoes();
    }
}
