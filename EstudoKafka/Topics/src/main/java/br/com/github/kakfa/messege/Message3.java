package br.com.github.kakfa.messege;

public class Message3 {

    private String nome;

    private String endereco;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    @Override
    public String toString() {
        return "Message3{" +
                "nome='" + nome + '\'' +
                ", endereco='" + endereco + '\'' +
                '}';
    }
}
