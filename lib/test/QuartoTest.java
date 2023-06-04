package test;

import static org.junit.Assert.*;

import org.junit.Test;

import  src.*;
import org.junit.Test;

public class QuartoTest {
	
	@Test
	 public void OcuparQuarto() {
		Cliente cliente = new Cliente("fulano", (long) 123);
		Quarto quarto = new Quarto(121, 2, QuartoEnum.SIMPLES);
		quarto.ocuparQuarto(cliente);
		assertTrue(quarto.getOcupado());
	}
	
	@Test
	 public void LiberarQuarto() {
		Cliente cliente = new Cliente("fulano", (long) 123);
		Quarto quarto = new Quarto(121, 2, QuartoEnum.SIMPLES);
		quarto.ocuparQuarto(cliente);
		quarto.liberarQuarto();
		assertFalse(quarto.getOcupado());
	}
}
