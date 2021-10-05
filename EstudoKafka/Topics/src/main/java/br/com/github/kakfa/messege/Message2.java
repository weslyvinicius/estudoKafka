package br.com.github.kakfa.messege;

public class Message2 {

    private String endereco;

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    @Override
    public String toString() {
        return "Message2{" +
                "endereco='" + endereco + '\'' +
                '}';
    }
}
