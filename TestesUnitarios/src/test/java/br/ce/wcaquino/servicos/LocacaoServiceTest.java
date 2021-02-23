package br.ce.wcaquino.servicos;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;
import static org.junit.Assert.assertThat;

import java.util.Date;

import org.junit.Test;

import br.ce.wcaquino.entidades.Filme;
import br.ce.wcaquino.entidades.Locacao;
import br.ce.wcaquino.entidades.Usuario;
import br.ce.wcaquino.utils.DataUtils;

public class LocacaoServiceTest {

	@Test
	public void teste() {
		// Criando o cenário

		LocacaoService service = new LocacaoService();
		Usuario usuario = new Usuario("Celso Ribeiro");
		Filme filme = new Filme("COMA", 10, 2.50);

		// Realizadno a ação

		Locacao locacao = service.alugarFilme(usuario, filme);

		// Usando o assertThat(verifique que!) que deixa a leitura do método mais
		// fluido(fluent interface)
		assertThat(locacao.getValor(),is(equalTo(2.5)));
		assertThat(locacao.getValor(),is(not(5.0)));
		assertThat(DataUtils.isMesmaData(locacao.getDataLocacao(), new Date()), is(true));
		assertThat(DataUtils.isMesmaData(locacao.getDataRetorno(), DataUtils.obterDataComDiferencaDias(1)), is(true));

	}
}
