package test;

import static org.junit.Assert.*;

import java.math.BigDecimal;

import org.junit.Test;

import  src.*;
import org.junit.Test;

public class ClienteTest {
	
	@Test
	 public void Fidelidade1() {
		Cliente cliente = new Cliente("fulano", (long) 123);
		cliente.setFidelidade(new BigDecimal("0.02"));
		assertEquals(cliente.getFidelidade().doubleValue(), new BigDecimal("0.02").doubleValue(), 0.002);
	}
	
	@Test
	 public void FidelidadeMax() {
		Cliente cliente = new Cliente("fulano", (long) 123);
		cliente.setFidelidade(new BigDecimal("1"));
		assertEquals(cliente.getFidelidade().doubleValue(), new BigDecimal("0.2").doubleValue(), 0.002);
	}
	
}
