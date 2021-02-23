package br.ce.wcaquino.servicos;

import static org.junit.Assert.*;

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

		// Fazendo a verificação usando as assertivas do JUnit
		assertTrue(locacao.getValor() == 2.5);
		assertTrue(DataUtils.isMesmaData(locacao.getDataLocacao(), new Date()));
		assertTrue(DataUtils.isMesmaData(locacao.getDataRetorno(), DataUtils.obterDataComDiferencaDias(1)));

	}
}
