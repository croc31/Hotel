public class Quarto {
    private long numero;
    private long andar;
    private boolean ocupado = false;
    private QuartoEnum tipo;
    private Cliente ocupante;

    public Quarto(long numero, long andar, QuartoEnum tipo) {
        this.numero = numero;
        this.andar = andar;
        this.tipo = tipo;
    }

    public void liberarQuarto() {
        if (ocupado) {
            ocupado = false;
            ocupante = null;
        } else {
            System.out.println("Erro: quarto não está ocupado");
        }
    }

    public void ocuparQuarto(Cliente o) {
        if (!ocupado) {
            ocupado = true;
            ocupante = o;
        } else {
            System.out.println("Erro: quarto está ocupado");
        }
    }

    // Método para listar todas as informações sobre o hotel e ajudar no teste dos
    // métodos
    public void Listar() {
        System.out.println("Quarto: " + this.numero + " Andar: " + this.andar);
        System.out.println("Tipo " + this.tipo);
        if (this.ocupado) {
            System.out.println("Ocupado por: " + ocupante.nome);
        } else {
            System.out.println("Livre");
        }
    }

    public long getAndar() {
        return andar;
    }

    public long getNumero() {
        return numero;
    }

    public QuartoEnum getTipo() {
        return tipo;
    }

    public Cliente getOcupante() {
        return ocupante;
    }

    public boolean getOcupado() {
        return ocupado;
    }

    public void setAndar(long andar) {
        this.andar = andar;
    }

    public void setNumero(long numero) {
        this.numero = numero;
    }

    public void setOcupante(Cliente ocupante) {
        this.ocupante = ocupante;
    }

    public void setOcupado(boolean ocupado) {
        this.ocupado = ocupado;
    }

    public void setTipo(QuartoEnum tipo) {
        this.tipo = tipo;
    }
}
