public class Pessoa {
    private String nome;
    private String email;
    private String cpf;


    public Pessoa(String nome, String cpf) {
        this.nome = nome;
        this.cpf = cpf;
        this.email = email;
    }

    public Pessoa() {
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

    @Override
    public String toString() {
        return "Nome: " + nome + ", CPF: " + cpf;
    }
}
