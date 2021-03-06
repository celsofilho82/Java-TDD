package br.ce.wcaquino.servicos;

import static br.ce.wcaquino.utils.DataUtils.adicionarDias;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import br.ce.wcaquino.entidades.Filme;
import br.ce.wcaquino.entidades.Locacao;
import br.ce.wcaquino.entidades.Usuario;
import br.ce.wcaquino.entidades.exception.LocadoraException;
import br.ce.wcaquino.utils.DataUtils;

public class LocacaoService {

	public Locacao alugarFilme(Usuario usuario, List<Filme> filmes) throws Exception {
		for (Filme filme : filmes) {
			if (filme.getEstoque() == 0) {
				throw new Exception("Filme sem estoque!");
			}
		}

		if (usuario == null) {
			throw new LocadoraException("Usuaŕio não pode ser nulo");
		}

		Locacao locacao = new Locacao();
		locacao.setFilmes(filmes);
		locacao.setUsuario(usuario);
		locacao.setDataLocacao(new Date());
		Double valorTotal = 0d;
		for (int i = 0; i < filmes.size(); i++) {
			Filme filme = filmes.get(i);
			Double valorFilme = filme.getPrecoLocacao();
			switch (i) {
			case 2:
				valorFilme = valorFilme * 0.75;
				break;
			case 3:
				valorFilme = valorFilme * 0.50;
				break;
			case 4:
				valorFilme = valorFilme * 0.25;
				break;
			case 5:
				valorFilme = 0d;
				break;
			}
			valorTotal += valorFilme;
		}

		locacao.setValor(valorTotal);

		// Entrega no dia seguinte
		Date dataEntrega = new Date();
		dataEntrega = adicionarDias(dataEntrega, 1);
		if (DataUtils.verificarDiaSemana(dataEntrega, Calendar.SUNDAY)) {
			dataEntrega = adicionarDias(dataEntrega, 1);
		}
		locacao.setDataRetorno(dataEntrega);

		// Salvando a locacao...
		// TODO adicionar método para salvar

		return locacao;
	}

	public static void main(String[] args) throws Exception {

		/*
		 * Testes devem ser FIRST Fast (Rápido) Independent (Independente) Repeatables
		 * (Pode ser executado quantas vezes for necessário) Self-Verifying (Auto
		 * verificavel) Timely (Oportuno deve ser criado no momento correto)
		 */

		// Criando o cenário

		LocacaoService service = new LocacaoService();
		Usuario usuario = new Usuario("Celso Ribeiro");
		Filme filme = new Filme("COMA", 10, 2.50);
		List<Filme> filmes = new ArrayList<Filme>();
		filmes.add(filme);
		// Realizadno a ação

		Locacao locacao = service.alugarFilme(usuario, filmes);

		// Fazendo a verificação

		System.out.println(locacao.getValor() == 2.5);
		System.out.println(DataUtils.isMesmaData(locacao.getDataLocacao(), new Date()));
		System.out.println(DataUtils.isMesmaData(locacao.getDataRetorno(), DataUtils.obterDataComDiferencaDias(1)));

	}

}