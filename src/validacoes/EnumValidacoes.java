package validacoes;

import excecoes.Menssagem;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;



public enum EnumValidacoes {

	VALIDACAO1 {

		/**
		 * Validação 1
		 */
		@Override
		public Set<Menssagem> executar(Object object, Map<String, Object> dadosCompartilhados) {

			Set<Menssagem> menssagens = new HashSet<Menssagem>();

			//Codigo da validação caso necessário, retiorna uma coleção com erros e ou alertas.

			return menssagens;
		}

	},

	VALIDACAO2 {

		/**
		 * Validação 2
		 */
		@Override
		public Set<Menssagem> executar(Object object, Map<String, Object> dadosCompartilhados) {

			Set<Menssagem> menssagens = new HashSet<Menssagem>();

			//Codigo da validação caso necessário, retiorna uma coleção com erros e ou alertas.

			return menssagens;
		}

	},

	VALIDACAO3 {

		/**
		 * Validação 3
		 */
		@Override
		public Set<Menssagem> executar(Object object, Map<String, Object> dadosCompartilhados) {

			Set<Menssagem> menssagens = new HashSet<Menssagem>();

			//Codigo da validação caso necessário, retiorna uma coleção com erros e ou alertas.

			return menssagens;
		}

	}

	;

	public abstract Set<Menssagem> executar(Object object, Map<String, Object> dadosCompartilhados) throws Exception;


}
