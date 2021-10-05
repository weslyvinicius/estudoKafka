package br.com.github.kakfa.messege;

public class Message1 {
    private String nomeCliente;

    public String getNomeCliente() {
        return nomeCliente;
    }

    public void setNomeCliente(String nomeCliente) {
        this.nomeCliente = nomeCliente;
    }

    @Override
    public String toString() {
        return "Message1{" +
                "nomeCliente='" + nomeCliente + '\'' +
                '}';
    }
}
