package test;

import static org.junit.Assert.*;
import static org.junit.jupiter.api.Assertions.assertAll;

import java.math.BigDecimal;

import org.junit.Test;
import org.junit.jupiter.api.BeforeAll;

import  src.*;
import org.junit.Test;

public class HotelTest {
	
	@BeforeAll
	public static void init() {
		Cliente cliente = new Cliente("fulano", (long) 123);
		//adicionando no hotel
		
		Hotel hotel = new Hotel("Hotel Gusteau");
		hotel.CadastrarCliente("Ratatoulli", 77755588899L);
		hotel.CadastrarQuarto(101, 1, QuartoEnum.CASAL);
		hotel.CadastrarQuarto(201, 2, QuartoEnum.LUXUOSO);
		hotel.CadastrarQuarto(301, 3, QuartoEnum.PRESIDENCIAL);
		hotel.Contratar("Linguini",  70488855522278L, "su chefe", SalaEnum.COZINHA);
		hotel.Contratar("ineficiente",  7047755442278L, "sopeiro", SalaEnum.COZINHA);
	}
	
	@Test
	 public void CadastroCliente() {
		Hotel hotel = new Hotel("Hotel Gusteau");
		hotel.CadastrarCliente("fulano", 77755588899L);
		int clienteID = hotel.BuscaClien(77755588899L);
		Cliente cliente = hotel.getCliente(clienteID);
		assertAll("cliente",
				() -> assertEquals(cliente.getNome(), "fulano"),
				() -> assertEquals(cliente.getCPF() , 77755588899L));
	}

	@Test
	 public void CadastroQuarto() {
		Hotel hotel = new Hotel("Hotel Gusteau");
		hotel.CadastrarQuarto(100, 1, QuartoEnum.CASAL);
		Quarto quarto = hotel.getQuarto(QuartoEnum.CASAL, 0);

		assertAll("quarto",
                () -> assertEquals(quarto.getTipo(), QuartoEnum.CASAL),
                () -> assertEquals(quarto.getNumero(), 100),
                () -> assertEquals(quarto.getAndar(), 1)
        );

	}

	@Test
    public void ContratarFuncionario() {
		Hotel hotel = new Hotel("Hotel Gusteau");
        hotel.Contratar("Cozinheiro1", 70488855522279L, "cozinheiro", SalaEnum.COZINHA);
        Funcionario funcionario = hotel.BuscaFunc(0);

        assertAll("funcionario",
                () -> assertEquals(funcionario.getNome(), "Cozinheiro1"),
                () -> assertEquals(funcionario.getCPF(), 70488855522279L),
                () -> assertEquals(funcionario.getCargo(), "cozinheiro")
        );
    }

	@Test
    public void DemitirFuncionario() {
		Hotel hotel = new Hotel("Hotel Gusteau");
        hotel.Contratar("Cozinheiro1", 70488855522279L, "cozinheiro", SalaEnum.COZINHA);
		hotel.Contratar("Cozinheiro2", 70488855522278L, "cozinheiro", SalaEnum.COZINHA);
		hotel.Demitir(0);
        Funcionario funcionario = hotel.BuscaFunc(0);

        assertNull(funcionario);
    }

	@Test
    public void AlugarQuarto() {
		Hotel hotel = new Hotel("Hotel Gusteau");
		hotel.CadastrarQuarto(100, 1, QuartoEnum.CASAL);
		hotel.CadastrarCliente("João Câmara", 19208475475L);
        hotel.Alugar(19208475475L, QuartoEnum.CASAL, 5);
		
        Quarto quarto = hotel.getQuarto(QuartoEnum.CASAL, 0);

        assertAll("quarto",
                () -> assertTrue(quarto.getOcupado()),
                () -> assertEquals(quarto.getOcupante().getCPF(), 19208475475L)
        );
    }

	@Test
    public void AlugarQuartoSemDias() {
		Hotel hotel = new Hotel("Hotel Gusteau");
		hotel.CadastrarQuarto(100, 1, QuartoEnum.CASAL);
		hotel.CadastrarCliente("João Câmara", 19208475475L);
        hotel.Alugar(19208475475L, QuartoEnum.CASAL);
		
        Quarto quarto = hotel.getQuarto(QuartoEnum.CASAL, 0);

        assertAll("quarto",
                () -> assertTrue(quarto.getOcupado()),
                () -> assertEquals(quarto.getOcupante().getCPF(), 19208475475L)
        );
    }



	@Test
    public void DarBaixaHospedagem() {
		Hotel hotel = new Hotel("Hotel Gusteau");
		for (QuartoEnum i : QuartoEnum.values()) {
            hotel.CadastrarQuarto(i.getValor(), i.getValor(), i);
        }
		hotel.CadastrarCliente("João Câmara", 19208475475L);
        hotel.Alugar(19208475475L, QuartoEnum.SIMPLES, 2);

        Quarto quarto = hotel.getQuarto(QuartoEnum.SIMPLES, 0);

		//Tem algum problema no código na hora de calcular o saldo, então esse teste já foi útil para identificar esse problema
        hotel.DarBaixa(2, QuartoEnum.SIMPLES, 0, 0);

		assertEquals(200, hotel.getSaldo());

    }


	
	
}
