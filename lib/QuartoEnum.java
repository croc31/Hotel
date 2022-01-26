public enum QuartoEnum {
    SIMPLES(1), CASAL(2), LUXUOSO(3), PRESIDENCIAL(4);

    private final int valor;

    QuartoEnum(int valorOpcao) {
        valor = valorOpcao;
    }

    public int getValor() {
        return valor;
    }
}
