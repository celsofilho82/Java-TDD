package br.ce.wcaquino.servicos;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.fail;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.junit.Before;
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

	private LocacaoService service;

	// Utilizamos o @Before nos casos em que eu tenho o mesmo pre-requisito para
	// todos os cenários

	@Before
	public void setup() {
		service = new LocacaoService();
	}

	@Test
	public void quandoTudoEstaCorreto() throws Exception {
		// Criando o cenário

		Usuario usuario = new Usuario("Celso Ribeiro");
		List<Filme> filmes = Arrays.asList(new Filme("COMA", 1, 4.0));

		// Realizadno a ação

		Locacao locacao = service.alugarFilme(usuario, filmes);

		// Usando o assertThat(verifique que!) que deixa a leitura do método mais
		// fluido(fluent interface)
		assertThat(locacao.getValor(), is(equalTo(4.0)));
		assertThat(locacao.getValor(), is(not(5.0)));
		assertThat(DataUtils.isMesmaData(locacao.getDataLocacao(), new Date()), is(true));
		assertThat(DataUtils.isMesmaData(locacao.getDataRetorno(), DataUtils.obterDataComDiferencaDias(1)), is(true));

	}

	// Informo ao teste que uma Excessão é esperada - Forma elegante
	@Test(expected = Exception.class)
	public void lancaExceptionSeOFilmeNaoTemEstoque() throws Exception {
		// Criando o cenário

		Usuario usuario = new Usuario("Celso Ribeiro");
		List<Filme> filmes = Arrays.asList(new Filme("COMA", 0, 2.50));

		// Realizadno a ação

		service.alugarFilme(usuario, filmes);
	}

	// Não informo ao teste que uma Excessão é esperada - Forma controlada
	@Test
	public void lancaExceptionSeOFilmeNaoTemEstoque2() {
		// Criando o cenário

		LocacaoService service = new LocacaoService();
		Usuario usuario = new Usuario("Celso Ribeiro");
		List<Filme> filmes = Arrays.asList(new Filme("COMA", 0, 2.50));

		// Realizadno a ação

		try {
			service.alugarFilme(usuario, filmes);
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

		Usuario usuario = new Usuario("Celso Ribeiro");
		List<Filme> filmes = Arrays.asList(new Filme("COMA", 0, 2.50));

		// Realizadno a ação

		// Neste exemplo a espectativa deve ser executada antes da ação
		exception.expect(Exception.class);
		exception.expectMessage("Filme sem estoque!");

		service.alugarFilme(usuario, filmes);

	}

	@Test
	public void usuarioNaoPodeSerNulo() {
		// Criando o cenário

		Usuario usuario = null;
		List<Filme> filmes = Arrays.asList(new Filme("COMA", 1, 2.50));

		// Realizadno a ação
		try {
			service.alugarFilme(usuario, filmes);
			fail("Deve ser lançado uma exceção");
		} catch (Exception e) {
			assertThat(e.getMessage(), is("Usuaŕio não pode ser nulo"));
		}
	}

}
