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
		assertEquals(new BigDecimal("0.02").doubleValue(), cliente.getFidelidade().doubleValue(), 0.002);
	}

	@Test
	public void FidelidadeLimiteInferior() {
	Cliente cliente = new Cliente("fulano", (long) 123);
	cliente.setFidelidade(new BigDecimal("0.0199"));
	assertEquals(new BigDecimal("0.019").doubleValue(), cliente.getFidelidade().doubleValue(), 0.002);
	}

	@Test
	public void FidelidadeLimiteSuperior() {
	Cliente cliente = new Cliente("fulano", (long) 123);
	cliente.setFidelidade(new BigDecimal("0.2111"));
	assertEquals(new BigDecimal("0.2").doubleValue(), cliente.getFidelidade().doubleValue(), 0.002);
	}
	
	@Test
	 public void FidelidadeMax() {
		Cliente cliente = new Cliente("fulano", (long) 123);
		cliente.setFidelidade(new BigDecimal("1"));
		assertEquals(new BigDecimal("0.2").doubleValue(), cliente.getFidelidade().doubleValue(), 0.002);
	}

	@Test
	 public void FidelidadeZero() {
		Cliente cliente = new Cliente("fulano", (long) 123);
		cliente.setFidelidade(new BigDecimal("0"));
		assertEquals(new BigDecimal("0").doubleValue(), cliente.getFidelidade().doubleValue(), 0.002);
	}

	@Test
	 public void FidelidadeNegativa() {
		Cliente cliente = new Cliente("fulano", (long) 123);
		//não deveria acontecer
		cliente.setFidelidade(new BigDecimal("-1"));
		assertEquals(new BigDecimal("0").doubleValue(), cliente.getFidelidade().doubleValue(), 0.002);
	}
	
}
