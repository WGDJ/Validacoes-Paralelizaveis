package util;

import excecoes.Menssagem;
import validacoes.EnumValidacoes;
import validacoes.EnumValidacoesAgrupador;

import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.Future;

public class EstrategiaDeValidacao {

    public static Set<Menssagem> validar(Object object) throws Exception {

        for (EnumValidacoesAgrupador agrupador : EnumValidacoesAgrupador.values()) {
            if (agrupador.getClazz().equals(object.getClass())) {
                return executarValidacoes(object, agrupador);
            }
        }

        return null;
    }


    private static Set<Menssagem> executarValidacoes(Object object, EnumValidacoesAgrupador agrupador) throws Exception {

        ThreadPool threadPool = new ThreadPool();

        Set<Future<Set<Menssagem>>> menssagensFuture = new HashSet<Future<Set<Menssagem>>>();

        for (EnumValidacoes validacao : agrupador.getValidacoes()) {
            menssagensFuture.add((Future<Set<Menssagem>>) threadPool.executeAsync(new Worker(object, agrupador.getDadosCompartilhados(), validacao)));
        }

        threadPool.aguardarProcessamento();

        Set<Menssagem> menssagens = new HashSet<Menssagem>();

        for (Future<Set<Menssagem>> menssagem : menssagensFuture) {
            menssagens.addAll(menssagem.get());
        }

        return menssagens;
    }
}
