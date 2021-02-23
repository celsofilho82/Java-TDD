package br.ce.wcaquino.servicos;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import br.ce.wcaquino.entidades.Usuario;

public class AssertTest {

	/*
	 * Trabalhando com as Assertivas do JUnit
	 */

	@Test
	public void test() {

		// Recebe um valor boleano TRUE para verificação
		assertTrue(true);

		// Recebe um valor boleano FALSE para verificação
		assertFalse(false);

		// Verifica se o valor recebido é igual a um outro valor tratando cada tipo de
		// uma forma diferente
		assertEquals(0, 0);

		// Para objetos do tipo Double e Float usamos o assertEquals com um parâmetro
		// delta que serve como margem de erro para compração
		assertEquals(0.51, 0.51, 0.01);

		// No Java temos o autoboxing and unboxing que é a conversão de tipos primitivos
		// para objetos e vice-versa. Com o JUnit temos que definir corretamente se o
		// que estamos testando é um tipo primitivo ou objeto e não existe autoboxing
		// and unboxing automatico
		int i = 5;
		Integer i2 = 5;
		assertEquals(Integer.valueOf(i), i2);
		assertEquals(i, i2.intValue());

		// Podemos utilizar métodos da classe string nas Assertivas
		assertEquals("bola", "bola");
		assertTrue("bola".equalsIgnoreCase("Bola"));
		assertTrue("bola".startsWith("bo"));

		// A igualdade dos objetos deve ser verificada através do próprio método equal
		// do objeto. Para isso a classe deve ter implementados os métodos equals e hash
		// code
		Usuario u1 = new Usuario("Usuario 1");
		Usuario u2 = new Usuario("Usuario 1");

		assertEquals(u1, u2);

		// Verificando se os dois objetos são a mesma instância com o assertSame
		assertSame(u1, u1);

		// Podemos verificar se um objeto é nulo de duas formas:
		Usuario u3 = null;

		assertTrue(u3 == null);
		assertNull(u3);

	}
}
