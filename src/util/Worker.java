package util;

import excecoes.Menssagem;
import validacoes.EnumValidacoes;

import java.util.Map;
import java.util.Set;
import java.util.concurrent.Callable;

public class Worker implements Callable<Set<Menssagem> > {

	Object objeto;

	private EnumValidacoes validacao;

	private Map<String, Object> dadosCompartilhados;


	public Worker(Object objeto, final Map<String, Object> dadosCompartilhados, EnumValidacoes validacao) {
		this.objeto = objeto;
		this.dadosCompartilhados = dadosCompartilhados;
		this.validacao = validacao;
	}

	@Override
	public Set<Menssagem> call() {

		try {
			return validacao.executar(objeto, dadosCompartilhados);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}
}