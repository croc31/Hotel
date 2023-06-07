package src;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

public class Hotel {
    // salário padrão de cada profissão
    final BigDecimal salarioRecepcionista = new BigDecimal("1200").setScale(2, RoundingMode.UP);
    final BigDecimal salarioAdministrador = new BigDecimal("2000").setScale(2, RoundingMode.UP);
    final BigDecimal salarioCozinheiro = new BigDecimal("1500").setScale(2, RoundingMode.UP);
    final BigDecimal salarioContador = new BigDecimal("1700").setScale(2, RoundingMode.UP);
    // custo padrão de cada quarto por dia
    final BigDecimal custoSimples = new BigDecimal("100").setScale(2, RoundingMode.UP);
    final BigDecimal custoCasal = new BigDecimal("150").setScale(2, RoundingMode.UP);
    final BigDecimal custoLuxuoso = new BigDecimal("300").setScale(2, RoundingMode.UP);
    final BigDecimal custoPresidencial = new BigDecimal("500").setScale(2, RoundingMode.UP);
    // Atributos definidores
    String nome;
    BigDecimal saldo = new BigDecimal("0").setScale(2, RoundingMode.UP);
    // Listas
    List<Cliente> clientes = new ArrayList<Cliente>();
    List<Funcionario> funcionarios = new ArrayList<Funcionario>();
    // Para facilitar a implementação dos métodos e deixar a busca mais rápida
    // cada tipo de quarto terá uma lista separada
    List<Quarto> simples = new ArrayList<Quarto>();
    List<Quarto> casal = new ArrayList<Quarto>();
    List<Quarto> luxuoso = new ArrayList<Quarto>();
    List<Quarto> presidencial = new ArrayList<Quarto>();

    // Contrutor do hotel
    public Hotel(String nome) {
        this.nome = nome;
    }

    public BigDecimal getSaldo(){
        return this.saldo;
    }

    // Método para cadastrar novos quartos
    public void CadastrarQuarto(int numero, int andar, QuartoEnum tipo) {
        Quarto quartoNovo = new Quarto(numero, andar, tipo);
        switch (tipo) {
            case SIMPLES:
                simples.add(quartoNovo);
                break;
            case CASAL:
                casal.add(quartoNovo);
                break;
            case LUXUOSO:
                luxuoso.add(quartoNovo);
                break;
            case PRESIDENCIAL:
                presidencial.add(quartoNovo);
                break;
        }
    }

    // Método para cadastrar novos clientes
    public void CadastrarCliente(String nome, long CPF) {
        Cliente cli = new Cliente(nome, CPF);
        clientes.add(cli);
    }

    // Método para alugar um quarto livre informando a quantidade de dias que estará
    // alugado
    public BigDecimal Alugar(long CPF, QuartoEnum tipo, int dias) {
        int index = BuscaClien(CPF);
        if (index != -1) {
            int quartoLivreIndex = BuscaQuarto(tipo, false);
            if (quartoLivreIndex != -1) {

                // aumentando o número de quartos que um cliente já alugou e a quantidade de
                // dias
                // que um cliente ficou hospedado
                clientes.get(index).setQuartosAlugados(clientes.get(index).getQuartosAlugados() + 1);
                clientes.get(index).setDiasHospedados(clientes.get(index).getDiasHospedados() + dias);

                // mudando o estado do quarto para ocupado
                switch (tipo) {
                    case SIMPLES:
                        simples.get(quartoLivreIndex).setOcupado(true);
                        simples.get(quartoLivreIndex).setOcupante(clientes.get(index));
                        // dando baixa no valor pago pelo cliente descontando o valor da fidelidade

                        return this.DarBaixa(index, dias);
                    case CASAL:
                        casal.get(quartoLivreIndex).setOcupado(true);
                        casal.get(quartoLivreIndex).setOcupante(clientes.get(index));
                        // dando baixa no valor pago pelo cliente descontando o valor da fidelidade

                        return this.DarBaixa(index, dias);
                    case LUXUOSO:
                        luxuoso.get(quartoLivreIndex).setOcupado(true);
                        luxuoso.get(quartoLivreIndex).setOcupante(clientes.get(index));
                        // dando baixa no valor pago pelo cliente descontando o valor da fidelidade

                        return this.DarBaixa(index, dias);
                    case PRESIDENCIAL:
                        presidencial.get(quartoLivreIndex).setOcupado(true);
                        presidencial.get(quartoLivreIndex).setOcupante(clientes.get(index));
                        // dando baixa no valor pago pelo cliente descontando o valor da fidelidade

                        return this.DarBaixa(index, dias);
                }
            } else {
                System.out.println("Quarto livre não disponivel");
                return new BigDecimal(-2);
            }
        }
        System.out.println("Erro: cliente não encontrado");

        return new BigDecimal(-1);
    }

    // Método para alugar um quarto livre sem informar a quantidade de dias que
    // estará alugado
    public void Alugar(long CPF, QuartoEnum tipo) {
        int index = BuscaClien(CPF);
        if (index != -1) {
            int quartoLivreIndex = BuscaQuarto(tipo, false);
            if (quartoLivreIndex != -1) {
                // aumentando o número de quartos que um cliente já alugou
                clientes.get(index).setQuartosAlugados(clientes.get(index).getQuartosAlugados() + 1);

                // mudando o estado do quarto para ocupado
                switch (tipo) {
                    case SIMPLES:
                        simples.get(quartoLivreIndex).setOcupado(true);
                        simples.get(quartoLivreIndex).setOcupante(clientes.get(index));
                        break;
                    case CASAL:
                        casal.get(quartoLivreIndex).setOcupado(true);
                        casal.get(quartoLivreIndex).setOcupante(clientes.get(index));
                        break;
                    case LUXUOSO:
                        luxuoso.get(quartoLivreIndex).setOcupado(true);
                        luxuoso.get(quartoLivreIndex).setOcupante(clientes.get(index));
                        break;
                    case PRESIDENCIAL:
                        presidencial.get(quartoLivreIndex).setOcupado(true);
                        presidencial.get(quartoLivreIndex).setOcupante(clientes.get(index));
                        break;
                }
            } else {
                System.out.println("Quarto livre não disponivel");
            }
        } else {
            System.out.println("Erro: cliente não encontrado");

        }

    }

    // Método para atualizar o valor do caixa apos o pagamento do cliente
    public BigDecimal DarBaixa(int ClienteIndex, int dias) {
        BigDecimal fidelidade, novaFidelidade;
        // pegando a fidelidade anterior do cliente (em decimal) e subtraindo 1
        fidelidade = clientes.get(ClienteIndex).getFidelidade().subtract(new BigDecimal("1"));
        fidelidade = fidelidade.abs();

        // criando o novo valor da fidelidade com base na estadia atual do cliente
        novaFidelidade = new BigDecimal(dias);
        clientes.get(ClienteIndex).setFidelidade(novaFidelidade.divide(new BigDecimal("100")));

        // dando baixa no valor pago pelo cliente descontando o valor da fidelidade

        this.saldo = this.saldo.add(fidelidade.multiply(custoSimples.multiply(new BigDecimal(dias))));
        return this.saldo;
    }

    // Método para atualizar o valor do caixa de acordo com a quantidade de dias que
    // o cliente ficou no hotel
    public void DarBaixa(int dias, QuartoEnum tipo, int QuartoIndex, int ClienteIndex) {
        switch (tipo) {
            case SIMPLES:
                if (QuartoIndex > simples.size() || ClienteIndex > clientes.size()) {
                    System.out.println("Erro: index do quarto ou do cliente invalido");
                    return;
                }
                if (!simples.get(QuartoIndex).getOcupado()) {
                    System.out.println("Erro: quarto informado não está ocupado");
                    return;
                }
                // dando baixa no valor pago pelo cliente
                this.DarBaixa(ClienteIndex, dias);
                // mudando o estado do quarto para livre
                simples.get(QuartoIndex).setOcupado(false);
                simples.get(QuartoIndex).setOcupante(null);
                break;
            case CASAL:
                if (QuartoIndex > simples.size() || ClienteIndex > clientes.size()) {
                    System.out.println("Erro: index do quarto ou do cliente invalido");
                    return;
                }
                if (!simples.get(QuartoIndex).getOcupado()) {
                    System.out.println("Erro: quarto informado não está ocupado");
                    return;
                }

                // dando baixa no valor pago pelo cliente
                this.DarBaixa(ClienteIndex, dias);
                // mudando o estado do quarto para livre
                casal.get(QuartoIndex).setOcupado(false);
                casal.get(QuartoIndex).setOcupante(null);
                break;
            case LUXUOSO:
                if (QuartoIndex > simples.size() || ClienteIndex > clientes.size()) {
                    System.out.println("Erro: index do quarto ou do cliente invalido");
                    return;
                }
                if (!simples.get(QuartoIndex).getOcupado()) {
                    System.out.println("Erro: quarto informado não está ocupado");
                    return;
                }

                // dando baixa no valor pago pelo cliente
                this.DarBaixa(ClienteIndex, dias);
                // mudando o estado do quarto para livre
                luxuoso.get(QuartoIndex).setOcupado(false);
                luxuoso.get(QuartoIndex).setOcupante(null);
                break;
            case PRESIDENCIAL:
                if (QuartoIndex > simples.size() || ClienteIndex > clientes.size()) {
                    System.out.println("Erro: index do quarto ou do cliente invalido");
                    return;
                }
                if (!simples.get(QuartoIndex).getOcupado()) {
                    System.out.println("Erro: quarto informado não está ocupado");
                    return;
                }

                // dando baixa no valor pago pelo cliente
                this.DarBaixa(ClienteIndex, dias);
                // mudando o estado do quarto para livre
                presidencial.get(QuartoIndex).setOcupado(false);
                presidencial.get(QuartoIndex).setOcupante(null);
                break;
        }
    }

    // Método para criar um objeto funcionário e contratá-lo
    public void Contratar(String nome, long CPF, String cargo, SalaEnum sala) {
        Funcionario func;
        switch (sala) {
            case RECEPCAO:// recepçao recepcionista
                func = new Funcionario(nome, CPF, cargo, new BigDecimal(salarioRecepcionista.toString()), sala);
                break;
            case ADMINISTRACAO:// administração administrador
                func = new Funcionario(nome, CPF, cargo, new BigDecimal(salarioAdministrador.toString()), sala);
                break;
            case COZINHA:// cozinha cozinheiro
                func = new Funcionario(nome, CPF, cargo, new BigDecimal(salarioCozinheiro.toString()), sala);
                break;
            case ALMOXARIFADO:// almoxarifado contador
                func = new Funcionario(nome, CPF, cargo, new BigDecimal(salarioContador.toString()), sala);
                break;
            default:
                System.out.println("Erro: sala não listada, funcionario inicializado com salario igual a 0");
                return;
        }
        funcionarios.add(func);
    }

    // Método prar demitir funcionário
    public void Demitir(int ID) {
        Funcionario func = BuscaFunc(ID);
        if (func != null) {
            funcionarios.remove(func);
        } else {
            System.out.println("Erro: funcionario não encontrado");
        }
    }

    // Busca linear por funcionario de ID fornecido
    public Funcionario BuscaFunc(int ID) {
        for (Funcionario func : funcionarios) {
            if (func.ID == ID) {
                return func;
            }
        }
        return null;
    }

    // Busca linear por Cliente de CPF fornecido
    public int BuscaClien(long CPF) {
        ListIterator<Cliente> cliente = clientes.listIterator();
        while (cliente.hasNext()) {
            if (cliente.next().getCPF() == CPF) {
                return cliente.previousIndex();
            }
        }
        return -1;
    }

    // Busca linear por Quarto do tipo informado vago (caso ocupado = falso) ou
    // ocupado (caso ocupado = true)
    public int BuscaQuarto(QuartoEnum tipo, boolean ocupado) {
        ListIterator<Quarto> quarto;
        switch (tipo) {
            case SIMPLES:
                quarto = simples.listIterator();
                while (quarto.hasNext()) {
                    if (quarto.next().getOcupado() == ocupado) {
                        return quarto.previousIndex();
                    }
                }
                break;
            case CASAL:
                quarto = casal.listIterator();
                while (quarto.hasNext()) {
                    if (quarto.next().getOcupado() == ocupado) {
                        return quarto.previousIndex();
                    }
                }
                break;
            case LUXUOSO:
                quarto = luxuoso.listIterator();
                while (quarto.hasNext()) {
                    if (quarto.next().getOcupado() == ocupado) {
                        return quarto.previousIndex();
                    }
                }
                break;
            case PRESIDENCIAL:
                quarto = presidencial.listIterator();
                while (quarto.hasNext()) {
                    if (quarto.next().getOcupado() == ocupado) {
                        return quarto.previousIndex();
                    }
                }
                break;

            default:
                System.out.println("Erro ao buscar por");
                break;
        }

        return -1;
    }

    // Método para dar aumento de salário de funcionário
    public void Aumento(int ID) {
        Funcionario func = BuscaFunc(ID);
        if (func != null) {
            func.Aumento(new BigDecimal("1.1"));
        } else {
            System.out.println("Erro: funcionario não encontrado");
        }
    }

    // Método para listar todas as informações sobre o hotel e ajudar no teste dos
    // métodos
    public void Listar() {
        this.ListarHotel();
        this.ListarClientes();
        this.ListarFuncionarios();
        this.ListarQuartos();
        System.out.println("--------------------------------");
    }

    // Método para listar as informações basicas sobre o hotel e ajudar no teste dos
    // métodos
    public void ListarHotel() {
        System.out.println("---Estado do Hotel---");
        System.out.println("Nome: " + this.nome);
        System.out.println("Saldo: " + this.saldo);
        System.out.println("Quantidade de cliente: " + clientes.size());
        System.out.println("Quantidade de funcionarios: " + funcionarios.size());
        System.out.println(
                "Quantidade de quartos: " + (simples.size() + casal.size() + luxuoso.size() +
                        presidencial.size()));
    }

    // Método para listar todas as informações sobre os clientes e ajudar no teste
    // dos
    // métodos
    public void ListarClientes() {
        System.out.println("--------------------------------");
        System.out.println("Clientes:");
        for (Cliente cliente : clientes) {
            cliente.Listar();
            System.out.println();
        }
        System.out.println();
    }

    // Método para listar todas as informações sobre os funcionarios e ajudar no
    // teste dos
    // métodos
    public void ListarFuncionarios() {
        System.out.println("--------------------------------");
        System.out.println("Funcionarios: ");
        for (Funcionario func : funcionarios) {
            func.Listar();
            System.out.println();
        }
        System.out.println();
    }

    // Método para listar todas as informações sobre os quartos e ajudar no teste
    // dos
    // métodos
    public void ListarQuartos() {
        System.out.println("--------------------------------");
        System.out.println("Quartos Simples:");
        for (Quarto quarto : simples) {
            quarto.Listar();
            System.out.println();
        }
        System.out.println("--------------------------------");
        System.out.println("Quartos Casal:");
        for (Quarto quarto : casal) {
            quarto.Listar();
            System.out.println();
        }
        System.out.println("--------------------------------");
        System.out.println("Quartos Luxuosos:");
        for (Quarto quarto : luxuoso) {
            quarto.Listar();
            System.out.println();
        }
        System.out.println("--------------------------------");
        System.out.println("Quartos Presidenciais:");
        for (Quarto quarto : presidencial) {
            quarto.Listar();
            System.out.println();
        }
        System.out.println();
    }
    
    public Cliente getCliente(int ID) {
    	if(ID <= clientes.size()) {
    		return clientes.get(ID);
    	}
    	return null;
    }
    
    public Quarto getQuarto(QuartoEnum tipo, int ID) {

        switch (tipo) {
            case SIMPLES:
                if(ID <=simples.size()) {
                	return simples.get(ID);
                }
                break;
            case CASAL:
            	if(ID <=casal.size()) {
                	return casal.get(ID);
                }
                break;
            case LUXUOSO:
            	if(ID <=luxuoso.size()) {
                	return luxuoso.get(ID);
                }
                break;
            case PRESIDENCIAL:
            	if(ID <=presidencial.size()) {
                	return presidencial.get(ID);
                }
                break;

            default:
                System.out.println("Erro ao buscar por");
                break;
        }

        return null;
    }
    
    public static void main(String[] args) {
        Hotel hotel = new Hotel("Grand Hotel");
        // cadastrando um quarto de cada tipo
        for (QuartoEnum i : QuartoEnum.values()) {
            hotel.CadastrarQuarto(i.getValor(), i.getValor(), i);
        }
        hotel.ListarQuartos();
        // cadastrando dois clientes
        hotel.CadastrarCliente("João Câmara", 19208475475L);
        hotel.CadastrarCliente("Maria José", 19208475475L);
        hotel.ListarClientes();
        // contratando um funcionario de cada profissão
        hotel.Contratar("Adaelmo Santos", 12332113201L, "cozinheiro",
                SalaEnum.COZINHA);
        hotel.Contratar("Contadore Reali", 12532663101L, "contador",
                SalaEnum.ALMOXARIFADO);
        hotel.Contratar("Trixie Recepcio", 87442663101L, "recepcionista",
                SalaEnum.RECEPCAO);
        hotel.Contratar("Ana ADM", 84422663156L, "administrador",
                SalaEnum.ADMINISTRACAO);
        hotel.Contratar("Ana ADM com aumento", 84422663156L, "administrador",
                SalaEnum.ADMINISTRACAO);
        hotel.ListarFuncionarios();
        // Dando aumento para um funcionário e demitindo outro informando seus IDs
        hotel.Aumento(4);
        hotel.Demitir(0);
        hotel.ListarFuncionarios();
        // Alugando um quarto informando o tempo de estadia
        hotel.Alugar(19208475475L, QuartoEnum.CASAL, 5);
        hotel.Listar();
        // alugando um quarto sem informar por quantos dias e dando baixa
        hotel.Alugar(19208475475L, QuartoEnum.SIMPLES);
        hotel.Listar();
        hotel.DarBaixa(5, QuartoEnum.SIMPLES, 0, 0);
        hotel.Listar();

    }
}
