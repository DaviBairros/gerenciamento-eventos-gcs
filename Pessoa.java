public class Pessoa {
    private String nome;
    private String email;
    private String cpf;

    public Pessoa(String nome, String email, String cpf) {
        this.nome = nome;
        this.email = email;
        this.cpf = cpf;
    }

    public String getNome() {
        return nome;
    }

    public String getEmail() {
        return email;
    }

    public String getCpf() {
        return cpf;
    }

    public String exibirInformacoes() {
        return "Nome: " + nome + ", Email: " + email + ", CPF: " + cpf;
    }
}
