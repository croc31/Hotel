import java.math.BigDecimal;
import java.math.RoundingMode;

public class Funcionario extends Pessoa {
    // variáveis
    String cargo;
    BigDecimal salario = new BigDecimal("0.00").setScale(2, RoundingMode.UP);
    int tempoServico = 0;
    long ID;
    static long IDcount = 0;
    // Como as salas não possuiam métodos preferi transformar ela em um enum
    SalaEnum sala;

    public Funcionario(String nome, long CPF, String cargo, BigDecimal salario, SalaEnum sala) {
        super(nome, CPF);
        this.cargo = cargo;
        this.ID = IDcount;
        this.sala = sala;
        this.salario = this.salario.add(salario);

        ++IDcount;

    }

    // Método para listar todas as informações sobre o funcionario e ajudar no teste
    // dos
    // métodos
    public void Listar() {
        System.out.println("Funcionario(a) " + this.nome + " ID: " + this.ID);
        System.out.println("CPF: " + this.CPF);
        System.out.println("Trabalha como " + this.cargo + " há " + this.tempoServico + " dias");
        System.out.println("Ganha " + this.salario + " reais");
        switch (this.sala) {
            case RECEPCAO:// recepçao recepcionista
                System.out.println("Trabalha na recepçao");
                break;
            case ADMINISTRACAO:// administração administrador
                System.out.println("Trabalha na administração");
                break;
            case COZINHA:// cozinha cozinheiro
                System.out.println("Trabalha na cozinha");
                break;
            case ALMOXARIFADO:// almoxarifado contador
                System.out.println("Trabalha no almoxarifado");
                break;
            default:
                System.out.println("valor de sala invalido: " + this.sala);
                return;
        }
    }

    public void Aumento(BigDecimal valorDecimal) {
        this.salario = this.salario.multiply(valorDecimal);
    }

    public String getCargo() {
        return cargo;
    }

    public long getID() {
        return ID;
    }

    public BigDecimal getSalario() {
        return salario;
    }

    public int getTempoServico() {
        return tempoServico;
    }

    public void setCargo(String cargo) {
        this.cargo = cargo;
    }

    public void setID(long iD) {
        ID = iD;
    }

    public void setSalario(BigDecimal salario) {
        this.salario = salario;
    }

    public void setTempoServico(int tempoServico) {
        this.tempoServico = tempoServico;
    }
}
