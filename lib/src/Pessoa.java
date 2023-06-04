package src;

public class Pessoa {
    String nome;
    long CPF;

    public Pessoa(String nome, long CPF) {
        this.nome = nome;
        this.CPF = CPF;
    }

    public String getNome() {
        return nome;
    }

    public long getCPF() {
        return CPF;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setCPF(long cPF) {
        CPF = cPF;
    }
}