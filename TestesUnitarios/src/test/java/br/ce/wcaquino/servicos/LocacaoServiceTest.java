package br.ce.wcaquino.servicos;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.fail;

import java.util.Date;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import br.ce.wcaquino.entidades.Filme;
import br.ce.wcaquino.entidades.Locacao;
import br.ce.wcaquino.entidades.Usuario;
import br.ce.wcaquino.utils.DataUtils;

public class LocacaoServiceTest {

	@Rule
	public ExpectedException exception = ExpectedException.none();

	@Test
	public void quandoTudoEstaCorreto() throws Exception {
		// Criando o cenário

		LocacaoService service = new LocacaoService();
		Usuario usuario = new Usuario("Celso Ribeiro");
		Filme filme = new Filme("COMA", 1, 2.50);

		// Realizadno a ação

		Locacao locacao = service.alugarFilme(usuario, filme);

		// Usando o assertThat(verifique que!) que deixa a leitura do método mais
		// fluido(fluent interface)
		assertThat(locacao.getValor(), is(equalTo(2.5)));
		assertThat(locacao.getValor(), is(not(5.0)));
		assertThat(DataUtils.isMesmaData(locacao.getDataLocacao(), new Date()), is(true));
		assertThat(DataUtils.isMesmaData(locacao.getDataRetorno(), DataUtils.obterDataComDiferencaDias(1)), is(true));

	}

	// Informo ao teste que uma Excessão é esperada - Forma elegante
	@Test(expected = Exception.class)
	public void lancaExceptionSeOFilmeNaoTemEstoque() throws Exception {
		// Criando o cenário

		LocacaoService service = new LocacaoService();
		Usuario usuario = new Usuario("Celso Ribeiro");
		Filme filme = new Filme("COMA", 0, 2.50);

		// Realizadno a ação

		service.alugarFilme(usuario, filme);
	}

	// Não informo ao teste que uma Excessão é esperada - Forma controlada
	@Test
	public void lancaExceptionSeOFilmeNaoTemEstoque2() {
		// Criando o cenário

		LocacaoService service = new LocacaoService();
		Usuario usuario = new Usuario("Celso Ribeiro");
		Filme filme = new Filme("COMA", 0, 2.50);

		// Realizadno a ação

		try {
			service.alugarFilme(usuario, filme);
			// Garantindo que a exception deve ser lançada no teste
			fail("Deve ser lançado uma exceção");
		} catch (Exception e) {
			assertThat(e.getMessage(), is("Filme sem estoque!"));
		}
	}

	// Capturando a excesão através de uma role
	@Test
	public void lancaExceptionSeOFilmeNaoTemEstoque3() throws Exception {
		// Criando o cenário

		LocacaoService service = new LocacaoService();
		Usuario usuario = new Usuario("Celso Ribeiro");
		Filme filme = new Filme("COMA", 0, 2.50);

		// Realizadno a ação

		// Neste exemplo a espectativa deve ser executada antes da ação
		exception.expect(Exception.class);
		exception.expectMessage("Filme sem estoque!");

		service.alugarFilme(usuario, filme);

	}

	@Test
	public void usuarioNaoPodeSerNulo() {
		// Criando o cenário

		LocacaoService service = new LocacaoService();
		Usuario usuario = null;
		Filme filme = new Filme("COMA", 1, 2.50);

		// Realizadno a ação
		try {
			service.alugarFilme(usuario, filme);
			fail("Deve ser lançado uma exceção");
		} catch (Exception e) {
			assertThat(e.getMessage(), is("Usuaŕio não pode ser nulo"));
		}
	}

}
