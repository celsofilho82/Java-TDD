package br.ce.wcaquino.servicos;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import org.junit.Before;
import org.junit.Test;

import br.ce.wcaquino.entidades.exception.NaoPodeDividirPorZeroException;

public class CalculadoraTest {

	/*
	 * Desenvolvendo uma calculadora usando TDD
	 */

	private Calculadora calculadora;

	@Before
	public void setup() {
		calculadora = new Calculadora();
	}

	@Test
	public void deveSomarDoisValores() {
		// cenário
		int valor1 = 5;
		int valor2 = 3;

		// ação
		int resultado = calculadora.somar(valor1, valor2);

		// verificação
		assertThat(resultado, is(8));
	}

	@Test
	public void deveSubtrairDoisValores() {
		// cenário
		int valor1 = 8;
		int valor2 = 5;

		// ação
		int resultado = calculadora.subtrair(valor1, valor2);

		// verificação
		assertThat(resultado, is(3));

	}

	@Test
	public void deveDividirDoisValores() throws NaoPodeDividirPorZeroException {
		// cenário
		int valor1 = 10;
		int valor2 = 5;

		// ação
		int resultado = calculadora.dividir(valor1, valor2);

		// verificação
		assertThat(resultado, is(2));
	}

	@Test(expected = NaoPodeDividirPorZeroException.class)
	public void deveLancarExcecaoAoDividirPorZero() throws NaoPodeDividirPorZeroException {
		// cenário
		int valor1 = 10;
		int valor2 = 0;

		// ação
		calculadora.dividir(valor1, valor2);
	}

}
