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
		
		Hotel hotel1 = new Hotel("Hotel Gusteau");
		hotel1.CadastrarCliente("Ratatoulli", 77755588899L);
		hotel1.CadastrarQuarto(101, 1, QuartoEnum.CASAL);
		hotel1.CadastrarQuarto(201, 2, QuartoEnum.LUXUOSO);
		hotel1.CadastrarQuarto(301, 3, QuartoEnum.PRESIDENCIAL);
		hotel1.Contratar("Linguini",  70488855522278L, "su chefe", SalaEnum.COZINHA);
		hotel1.Contratar("ineficiente",  7047755442278L, "sopeiro", SalaEnum.COZINHA);
	}
	@Test
	public void CriacaoHotel() {
		Hotel hotelaria = new Hotel("Hotel Teste");
		
		assertEquals(hotelaria.getSaldo().doubleValue(), 0, 0.0);
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
	 public void CadastroQuartoSimples() {
		Hotel hotel = new Hotel("Hotel Gusteau");
		hotel.CadastrarQuarto(100, 1, QuartoEnum.SIMPLES);
		Quarto quarto = hotel.getQuarto(QuartoEnum.SIMPLES, 0);

		assertAll("quarto",
                () -> assertEquals(quarto.getTipo(), QuartoEnum.SIMPLES),
                () -> assertEquals(quarto.getNumero(), 100),
                () -> assertEquals(quarto.getAndar(), 1)
        );

	}
	
	@Test
	 public void CadastroQuartoCasal() {
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
	 public void CadastroQuartoLuxuoso() {
		Hotel hotel = new Hotel("Hotel Gusteau");
		hotel.CadastrarQuarto(100, 1, QuartoEnum.LUXUOSO);
		Quarto quarto = hotel.getQuarto(QuartoEnum.LUXUOSO, 0);

		assertAll("quarto",
               () -> assertEquals(quarto.getTipo(), QuartoEnum.LUXUOSO),
               () -> assertEquals(quarto.getNumero(), 100),
               () -> assertEquals(quarto.getAndar(), 1)
       );

	}
	
	@Test
	 public void CadastroQuartoPresidencial() {
		Hotel hotel = new Hotel("Hotel Gusteau");
		hotel.CadastrarQuarto(100, 1, QuartoEnum.PRESIDENCIAL);
		Quarto quarto = hotel.getQuarto(QuartoEnum.PRESIDENCIAL, 0);

		assertAll("quarto",
               () -> assertEquals(quarto.getTipo(), QuartoEnum.PRESIDENCIAL),
               () -> assertEquals(quarto.getNumero(), 100),
               () -> assertEquals(quarto.getAndar(), 1)
       );

	}
	
	@Test
    public void ContratarFuncionarioRecepcao() {
		Hotel hotel = new Hotel("Hotel Gusteau");
        long id = hotel.Contratar("Claudineia", 70488855522279L, "Recepcionista", SalaEnum.RECEPCAO);
        Funcionario funcionario = hotel.BuscaFunc(id);

        assertAll("funcionario",
                () -> assertEquals(funcionario.getNome(), "Claudineia"),
                () -> assertEquals(funcionario.getCPF(), 70488855522279L),
                () -> assertEquals(funcionario.getCargo(), "Recepcionista")
        );
    }
	
	@Test
    public void ContratarFuncionarioAdiministracaoa() {
		Hotel hotel = new Hotel("Hotel Gusteau");
        long id = hotel.Contratar("Admin", 70488855522279L, "Admim", SalaEnum.ADMINISTRACAO);
        Funcionario funcionario = hotel.BuscaFunc(id);

        assertAll("funcionario",
                () -> assertEquals(funcionario.getNome(), "Admin"),
                () -> assertEquals(funcionario.getCPF(), 70488855522279L),
                () -> assertEquals(funcionario.getCargo(), "Admim")
        );
    }
	
	@Test
    public void ContratarFuncionarioCozinha() {
		Hotel hotel = new Hotel("Hotel Gusteau");
        long id = hotel.Contratar("Cozinheiro1", 70488855522279L, "cozinheiro", SalaEnum.COZINHA);
        Funcionario funcionario = hotel.BuscaFunc(id);

        assertAll("funcionario",
                () -> assertEquals(funcionario.getNome(), "Cozinheiro1"),
                () -> assertEquals(funcionario.getCPF(), 70488855522279L),
                () -> assertEquals(funcionario.getCargo(), "cozinheiro")
        );
    }
	
	@Test
    public void ContratarFuncionarioAmoxarifado() {
		Hotel hotel = new Hotel("Hotel Gusteau");
        long id = hotel.Contratar("Elmo", 70488855522279L, "chefe de setor", SalaEnum.ALMOXARIFADO);
        Funcionario funcionario = hotel.BuscaFunc(id);

        assertAll("funcionario",
                () -> assertEquals(funcionario.getNome(), "Elmo"),
                () -> assertEquals(funcionario.getCPF(), 70488855522279L),
                () -> assertEquals(funcionario.getCargo(), "chefe de setor")
        );
    }
	
	@Test
    public void ContratarFuncionarioErro() {
		Hotel hotel = new Hotel("Hotel Gusteau");
        try {
        	hotel.Contratar("Elmo", 70488855522279L, "chefe de setor", null);
            fail("deveria ter uma mensagem de erro por setor nulo");
        }catch(Exception e){}
        
    }

	@Test
    public void DemitirFuncionarioErro() {
		Hotel hotel = new Hotel("Hotel Gusteau");
        long id = hotel.Contratar("Cozinheiro1", 70488855522279L, "cozinheiro", SalaEnum.COZINHA);
		hotel.Contratar("Cozinheiro2", 70488855522278L, "cozinheiro", SalaEnum.COZINHA);
		hotel.Demitir(id);
        Funcionario funcionario = hotel.BuscaFunc(id);

        assertNull(funcionario);
    }
	
	@Test
    public void DemitirFuncionario() {
		Hotel hotel = new Hotel("Hotel Gusteau");
        hotel.Contratar("Cozinheiro1", 70488855522279L, "cozinheiro", SalaEnum.COZINHA);
		hotel.Contratar("Cozinheiro2", 70488855522278L, "cozinheiro", SalaEnum.COZINHA);
		hotel.Demitir(-1);
        Funcionario funcionario = hotel.BuscaFunc(0);

        assertNull(funcionario);
    }
	
	@Test
    public void DarAumentoFuncionario() {
		Hotel hotel = new Hotel("Hotel Gusteau");
        long id = hotel.Contratar("Cozinheiro1", 70488855522279L, "cozinheiro", SalaEnum.COZINHA);
		hotel.Aumento(id);
        Funcionario funcionario = hotel.BuscaFunc(id);

        assertEquals(1650, funcionario.getSalario().doubleValue(), 0.1);
    }
	
	@Test
    public void DarAumentoFuncionarioErro() {
		Hotel hotel = new Hotel("Hotel Gusteau");
		try {
			hotel.Aumento(-1);
			fail("deveria ter lan�ado erro de funcionario n�o existente");
		}catch(Exception e) {
			
		}
    }
	
	@Test
    public void BuscaQuartoErro() {
		Hotel hotel = new Hotel("Hotel Gusteau");
		try {
			hotel.BuscaQuarto(null, true);
			fail("deveria ter lan�ado erro tipo nulo");
		}catch(Exception e) {
			
		}
    }
	
	@Test
    public void AlugarQuartoSimplesDias() {
		Hotel hotel = new Hotel("Hotel Gusteau");
		hotel.CadastrarQuarto(100, 1, QuartoEnum.SIMPLES);
		hotel.CadastrarCliente("João Câmara", 19208475475L);
        Double preco = hotel.Alugar(19208475475L, QuartoEnum.SIMPLES, 5).doubleValue();
		
        Quarto quarto = hotel.getQuarto(QuartoEnum.SIMPLES, 0);

        assertAll("quarto",
                () -> assertTrue(quarto.getOcupado()),
                () -> assertEquals(quarto.getOcupante().getCPF(), 19208475475L),
                () -> assertEquals(500, preco, 0.1)
        );
    }
	
	@Test
    public void AlugarQuartoCasalDias() {
		Hotel hotel = new Hotel("Hotel Gusteau");
		hotel.CadastrarQuarto(100, 1, QuartoEnum.CASAL);
		hotel.CadastrarCliente("João Câmara", 19208475475L);
        Double preco = hotel.Alugar(19208475475L, QuartoEnum.CASAL, 5).doubleValue();
		
        Quarto quarto = hotel.getQuarto(QuartoEnum.CASAL, 0);

        assertAll("quarto",
                () -> assertTrue(quarto.getOcupado()),
                () -> assertEquals(quarto.getOcupante().getCPF(), 19208475475L),
                () -> assertEquals(750, preco, 0.1)
        );
    }
	
	@Test
    public void AlugarQuartoLuxuosoDias() {
		Hotel hotel = new Hotel("Hotel Gusteau");
		hotel.CadastrarQuarto(100, 1, QuartoEnum.LUXUOSO);
		hotel.CadastrarCliente("João Câmara", 19208475475L);
        Double preco = hotel.Alugar(19208475475L, QuartoEnum.LUXUOSO, 5).doubleValue();
		
        Quarto quarto = hotel.getQuarto(QuartoEnum.LUXUOSO, 0);

        assertAll("quarto",
                () -> assertTrue(quarto.getOcupado()),
                () -> assertEquals(quarto.getOcupante().getCPF(), 19208475475L),
                () -> assertEquals(1500, preco, 0.1)
        );
    }
	
	@Test
    public void AlugarQuartoPresidencialDias() {
		Hotel hotel = new Hotel("Hotel Gusteau");
		hotel.CadastrarQuarto(100, 1, QuartoEnum.PRESIDENCIAL);
		hotel.CadastrarCliente("João Câmara", 19208475475L);
        Double preco = hotel.Alugar(19208475475L, QuartoEnum.PRESIDENCIAL, 5).doubleValue();
		
        Quarto quarto = hotel.getQuarto(QuartoEnum.PRESIDENCIAL, 0);

        assertAll("quarto",
                () -> assertTrue(quarto.getOcupado()),
                () -> assertEquals(quarto.getOcupante().getCPF(), 19208475475L),
                () -> assertEquals(2500, preco, 0.1)
        );
    }
	
	@Test
    public void AlugarQuartoInexistente() {
		Hotel hotel = new Hotel("Hotel Gusteau");
		hotel.CadastrarCliente("João Câmara", 19208475475L);
        

        assertEquals(-2d, hotel.Alugar(19208475475L, QuartoEnum.CASAL, 5).doubleValue(),0.1);
    }
	
	@Test
    public void AlugarQuartoClienteInexistente() {
		Hotel hotel = new Hotel("Hotel Gusteau");
        

        assertEquals(-1d, hotel.Alugar(000005475L, QuartoEnum.CASAL, 5).doubleValue(),0.1);
    }

	@Test
    public void AlugarQuartoSemDiasCasal() {
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
    public void AlugarQuartoSemDiasLuxuoso() {
		Hotel hotel = new Hotel("Hotel Gusteau");
		hotel.CadastrarQuarto(100, 1, QuartoEnum.LUXUOSO);
		hotel.CadastrarCliente("João Câmara", 19208475475L);
        hotel.Alugar(19208475475L, QuartoEnum.LUXUOSO);
		
        Quarto quarto = hotel.getQuarto(QuartoEnum.LUXUOSO, 0);

        assertAll("quarto",
                () -> assertTrue(quarto.getOcupado()),
                () -> assertEquals(quarto.getOcupante().getCPF(), 19208475475L)
        );
    }
	
	@Test
    public void AlugarQuartoSemDiasPresidencial() {
		Hotel hotel = new Hotel("Hotel Gusteau");
		hotel.CadastrarQuarto(100, 1, QuartoEnum.PRESIDENCIAL);
		hotel.CadastrarCliente("João Câmara", 19208475475L);
        hotel.Alugar(19208475475L, QuartoEnum.PRESIDENCIAL);
		
        Quarto quarto = hotel.getQuarto(QuartoEnum.PRESIDENCIAL, 0);

        assertAll("quarto",
                () -> assertTrue(quarto.getOcupado()),
                () -> assertEquals(quarto.getOcupante().getCPF(), 19208475475L)
        );
    }
	
	@Test
    public void AlugarQuartoSemDiasSimples() {
		Hotel hotel = new Hotel("Hotel Gusteau");
		hotel.CadastrarQuarto(100, 1, QuartoEnum.SIMPLES);
		hotel.CadastrarCliente("João Câmara", 19208475475L);
        hotel.Alugar(19208475475L, QuartoEnum.SIMPLES);
		
        Quarto quarto = hotel.getQuarto(QuartoEnum.SIMPLES, 0);

        assertAll("quarto",
                () -> assertTrue(quarto.getOcupado()),
                () -> assertEquals(quarto.getOcupante().getCPF(), 19208475475L)
        );
    }

	@Test
    public void AlugarQuartoSemDiasErroQuarto() {
		Hotel hotel = new Hotel("Hotel Gusteau");
		hotel.CadastrarQuarto(100, 1, QuartoEnum.SIMPLES);
		hotel.CadastrarCliente("João Câmara", 19208475475L);
        hotel.Alugar(19208475475L, QuartoEnum.SIMPLES);
		
        try {
        	hotel.Alugar(19208475475L, QuartoEnum.SIMPLES);
        	fail("deveria ocorrer um erro de quarto n�o disponivel");
        }catch(Exception e) {}
    }
	
	@Test
    public void AlugarQuartoSemDiasErroCliente() {
		Hotel hotel = new Hotel("Hotel Gusteau");
		hotel.CadastrarQuarto(100, 1, QuartoEnum.SIMPLES);
		
        try {
        	hotel.Alugar(19208475475L, QuartoEnum.SIMPLES);
        	fail("deveria ocorrer um erro de cliente n�o existente");
        }catch(Exception e) {}
    }

	
	@Test
    public void DarBaixaHospedagem() {
		Hotel hotel = new Hotel("Hotel  Baixa");
		for (QuartoEnum i : QuartoEnum.values()) {
            hotel.CadastrarQuarto(i.getValor(), i.getValor(), i);
        }
		hotel.CadastrarCliente("João Câmara", 19208475475L);
        hotel.Alugar(19208475475L, QuartoEnum.SIMPLES, 2);

        Quarto quarto = hotel.getQuarto(QuartoEnum.SIMPLES, 0);

		//Tem algum problema no código na hora de calcular o saldo, então esse teste já foi útil para identificar esse problema
        hotel.DarBaixa(2, QuartoEnum.SIMPLES, 0, 0);

		assertEquals(200, hotel.getSaldo().doubleValue(), 0.1);

    }
	
	@Test
    public void DarBaixaSimples() {
		Hotel hotel = new Hotel("Hotel  Baixa");
		QuartoEnum quartoEnum = QuartoEnum.SIMPLES;
		for (QuartoEnum i : QuartoEnum.values()) {
            hotel.CadastrarQuarto(i.getValor(), i.getValor(), i);
        }
		hotel.CadastrarCliente("João Câmara", 19208475475L);
        hotel.Alugar(19208475475L, quartoEnum);

        Quarto quarto = hotel.getQuarto(quartoEnum, 0);

		//Tem algum problema no código na hora de calcular o saldo, então esse teste já foi útil para identificar esse problema
        hotel.DarBaixa(2, quartoEnum, 0, 0);

		assertEquals(200, hotel.getSaldo().doubleValue(), 0.1);

    }
	
	@Test
    public void DarBaixaCasal() {
		Hotel hotel = new Hotel("Hotel  Baixa");
		QuartoEnum quartoEnum = QuartoEnum.CASAL;
		for (QuartoEnum i : QuartoEnum.values()) {
            hotel.CadastrarQuarto(i.getValor(), i.getValor(), i);
        }
		hotel.CadastrarCliente("João Câmara", 19208475475L);
        hotel.Alugar(19208475475L, quartoEnum);

        Quarto quarto = hotel.getQuarto(quartoEnum, 0);

		//Tem algum problema no código na hora de calcular o saldo, então esse teste já foi útil para identificar esse problema
        hotel.DarBaixa(2, quartoEnum, 0, 0);

		assertEquals(200, hotel.getSaldo().doubleValue(), 0.1);

    }
	
	@Test
    public void DarBaixaLuxuoso() {
		Hotel hotel = new Hotel("Hotel  Baixa");
		QuartoEnum quartoEnum = QuartoEnum.LUXUOSO;
		for (QuartoEnum i : QuartoEnum.values()) {
            hotel.CadastrarQuarto(i.getValor(), i.getValor(), i);
        }
		hotel.CadastrarCliente("João Câmara", 19208475475L);
        hotel.Alugar(19208475475L, quartoEnum);

        hotel.DarBaixa(2, quartoEnum, 0, 0);

		assertEquals(200, hotel.getSaldo().doubleValue(), 0.1);

    }
	
	@Test
    public void DarBaixaPresidencial() {
		Hotel hotel = new Hotel("Hotel  Baixa");
		QuartoEnum quartoEnum = QuartoEnum.PRESIDENCIAL;
		for (QuartoEnum i : QuartoEnum.values()) {
            hotel.CadastrarQuarto(i.getValor(), i.getValor(), i);
        }
		hotel.CadastrarCliente("João Câmara", 19208475475L);
        hotel.Alugar(19208475475L, quartoEnum);

        hotel.DarBaixa(2, quartoEnum, 0, 0);

		assertEquals(200, hotel.getSaldo().doubleValue(), 0.1);

    }
	
	@Test
    public void DarBaixaSimplesErroInexistente() {
		Hotel hotel = new Hotel("Hotel  Baixa");
		hotel.CadastrarCliente("João Câmara", 19208475475L);

		try {
	        hotel.DarBaixa(2, QuartoEnum.SIMPLES, 5, 0);
			fail("Deveria ter lan�ado um erro");
		}catch(Exception e) {}


    }
	
	@Test
    public void DarBaixaCasalErroInexistente() {
		Hotel hotel = new Hotel("Hotel  Baixa");
		hotel.CadastrarCliente("João Câmara", 19208475475L);

		try {
	        hotel.DarBaixa(2, QuartoEnum.CASAL, 5, 0);
			fail("Deveria ter lan�ado um erro");
		}catch(Exception e) {}


    }
	
	@Test
    public void DarBaixaLuxuosoErroInexistente() {
		Hotel hotel = new Hotel("Hotel  Baixa");
		hotel.CadastrarCliente("João Câmara", 19208475475L);

		try {
	        hotel.DarBaixa(2, QuartoEnum.LUXUOSO, 5, 0);
			fail("Deveria ter lan�ado um erro");
		}catch(Exception e) {}


    }

	@Test
    public void DarBaixaPresidencialErroInexistente() {
		Hotel hotel = new Hotel("Hotel  Baixa");
		hotel.CadastrarCliente("João Câmara", 19208475475L);

		try {
	        hotel.DarBaixa(2, QuartoEnum.PRESIDENCIAL, 5, 0);
			fail("Deveria ter lan�ado um erro");
		}catch(Exception e) {}


    }
	
	@Test
    public void DarBaixaSimplesErroLivre() {
		Hotel hotel = new Hotel("Hotel  Baixa");
		QuartoEnum quartoEnum = QuartoEnum.SIMPLES;
		for (QuartoEnum i : QuartoEnum.values()) {
            hotel.CadastrarQuarto(i.getValor(), i.getValor(), i);
        }
		hotel.CadastrarCliente("João Câmara", 19208475475L);

       try {
    	   hotel.DarBaixa(2, quartoEnum, 0, 0);
    	   fail("Deveria ter lan�ado um erro");
       }catch(Exception e) {}

    }
	
	@Test
    public void DarBaixaCasalErroLivre() {
		Hotel hotel = new Hotel("Hotel  Baixa");
		QuartoEnum quartoEnum = QuartoEnum.CASAL;
		for (QuartoEnum i : QuartoEnum.values()) {
            hotel.CadastrarQuarto(i.getValor(), i.getValor(), i);
        }
		hotel.CadastrarCliente("João Câmara", 19208475475L);

       try {
    	   hotel.DarBaixa(2, quartoEnum, 0, 0);
    	   fail("Deveria ter lan�ado um erro");
       }catch(Exception e) {}

    }
	
	@Test
    public void DarBaixaLuxuosoErroLivre() {
		Hotel hotel = new Hotel("Hotel  Baixa");
		QuartoEnum quartoEnum = QuartoEnum.LUXUOSO;
		for (QuartoEnum i : QuartoEnum.values()) {
            hotel.CadastrarQuarto(i.getValor(), i.getValor(), i);
        }
		hotel.CadastrarCliente("João Câmara", 19208475475L);

       try {
    	   hotel.DarBaixa(2, quartoEnum, 0, 0);
    	   fail("Deveria ter lan�ado um erro");
       }catch(Exception e) {}

    }
	
	@Test
    public void DarBaixaPresidencialErroLivre() {
		Hotel hotel = new Hotel("Hotel  Baixa");
		QuartoEnum quartoEnum = QuartoEnum.PRESIDENCIAL;
		for (QuartoEnum i : QuartoEnum.values()) {
            hotel.CadastrarQuarto(i.getValor(), i.getValor(), i);
        }
		hotel.CadastrarCliente("João Câmara", 19208475475L);

       try {
    	   hotel.DarBaixa(2, quartoEnum, 0, 0);
    	   fail("Deveria ter lan�ado um erro");
       }catch(Exception e) {}

    }
	
	@Test
	public void GetClienteErro() {
		Hotel hotel = new Hotel("Hotel  Baixa");
		try {
			hotel.getCliente(12);
			fail("deveria ser lan�ado um erro de cliente n�o encontrado");
		}catch(Exception e) {}
	}
	
	
}
