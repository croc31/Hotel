package test;

import static org.junit.Assert.*;

import java.math.BigDecimal;

import org.junit.Test;

import  src.*;
import org.junit.Test;

public class FuncionarioTest {
	
	@Test
	 public void Aumento() {
		long CPF = 70488899956L;
		Funcionario chefe = new Funcionario("Fulano de Sicrano souza", CPF, "chefe", new BigDecimal("2300.00"), SalaEnum.COZINHA);
		//aumento de 10%
		chefe.Aumento(new BigDecimal("1.1"));
		assertEquals(chefe.getSalario().doubleValue(), 2530, 0.1);
	}

	@Test
	 public void AumentoZero() {
		long CPF = 70488899956L;
		Funcionario chefe = new Funcionario("Fulano de Sicrano souza", CPF, "chefe", new BigDecimal("2300.00"), SalaEnum.COZINHA);
		//aumento de 0% - deveria ser tratado/não acontecer
		chefe.Aumento(new BigDecimal("0"));
		assertEquals(chefe.getSalario().doubleValue(), 2300, 0.1);
	}

	@Test
	 public void AumentoNegativo() {
		long CPF = 70488899956L;
		Funcionario chefe = new Funcionario("Fulano de Sicrano souza", CPF, "chefe", new BigDecimal("2300.00"), SalaEnum.COZINHA);
		//aumento de -10% - não deveria acontecer
		chefe.Aumento(new BigDecimal("-1.1"));
		assertEquals(chefe.getSalario().doubleValue(), 2300, 0.1);
	}
	
	
}
