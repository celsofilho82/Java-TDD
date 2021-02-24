package br.ce.wcaquino.servicos;

import br.ce.wcaquino.entidades.exception.NaoPodeDividirPorZeroException;

public class Calculadora {

	public int somar(int valor1, int valor2) {
		return valor1 + valor2;
	}

	public int subtrair(int valor1, int valor2) {
		return valor1 - valor2;
	}

	public int dividir(int valor1, int valor2) throws NaoPodeDividirPorZeroException {
		if (valor2 == 0) {
			throw new NaoPodeDividirPorZeroException();
		}
		
		return valor1 / valor2;
	}

}
