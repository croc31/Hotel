package src;
import java.math.BigDecimal;

public class Cliente extends Pessoa {
    int quartosAlugados;
    int diasHospedados;
    BigDecimal fidelidade = new BigDecimal("0");

    public Cliente(String nome, Long CPF) {
        super(nome, CPF);
    }

    // Método para listar todas as informações sobre o hotel e ajudar no teste dos
    // métodos
    public void Listar() {
        System.out.println("Cliente: " + this.nome + " CPF: " + this.CPF);
        System.out.println(this.quartosAlugados + " quartos que já foram alugados");
        System.out.println("Está hospedado há " + this.diasHospedados + " dias");
        System.out.println("Desconto na próxima estadia(sistema de fidelidade): " +
                fidelidade.multiply(new BigDecimal("100")) + "%");
    }

    public int getQuartosAlugados() {
        return quartosAlugados;
    }

    public int getDiasHospedados() {
        return diasHospedados;
    }

    public BigDecimal getFidelidade() {
        return fidelidade;
    }

    public void setQuartosAlugados(int quartosAlugados) {
        this.quartosAlugados = quartosAlugados;
    }

    public void setDiasHospedados(int diasHospedados) {
        this.diasHospedados = diasHospedados;
    }

    public void setFidelidade(BigDecimal fidelidade) {
        if (fidelidade.doubleValue() > 0.20) {
            this.fidelidade = new BigDecimal("0.20");
        } else {
            this.fidelidade = fidelidade;
        }
    }
}
