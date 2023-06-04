package src;
public enum SalaEnum {
    RECEPCAO(1), ADMINISTRACAO(2), COZINHA(3), ALMOXARIFADO(4);

    private final int valor;

    SalaEnum(int valorOpcao) {
        valor = valorOpcao;
    }

    public int getValor() {
        return valor;
    }
}