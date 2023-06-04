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
	public void init() {
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
		int clienteID = hotel.BuscaClien(123L);
		Cliente cliente = hotel.getCliente(clienteID);
		assertAll("cliente",
				() -> assertEquals(cliente.getNome(), "fulano"),
				() -> assertEquals(cliente.getCPF() , 77755588899L));
	}
	
	
}
