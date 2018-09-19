package validacoes;

import excecoes.Menssagem;
import modelo.ClasseDeModelo1;
import modelo.ClasseDeModelo2;
import util.ThreadPool;
import util.Worker;

import java.util.*;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.Future;

public enum EnumValidacoesAgrupador {

    AGRUPAMENTO1(ClasseDeModelo1.class) {
        public Map<String, Object> getDadosCompartilhados() {

            return new HashMap<String, Object>() {{
                this.put("IDENTIFICADOR1", "Valor1");
                this.put("IDENTIFICADOR2", new Object());
            }};
        }

        public List<EnumValidacoes> getValidacoes() {

            return Arrays.asList(EnumValidacoes.VALIDACAO1, EnumValidacoes.VALIDACAO2);
        }

    },

    AGRUPAMENTO2(ClasseDeModelo2.class) {
        public Map<String, Object> getDadosCompartilhados() {

            return new HashMap<String, Object>() {{
                this.put("IDENTIFICADOR1", "Valor1");
                this.put("IDENTIFICADOR2", new Object());
            }};
        }

        @Override
        public List<EnumValidacoes> getValidacoes() {

            List<EnumValidacoes> enumValidacoes = Arrays.asList(EnumValidacoes.VALIDACAO3);
            enumValidacoes.addAll(EnumValidacoesAgrupador.AGRUPAMENTO1.getValidacoes());

            return enumValidacoes;
        }

    };


    private Class clazz;

    public Class getClazz() {
        return clazz;
    }

    EnumValidacoesAgrupador(Class clazz) {

        this.clazz = clazz;
    }

    public abstract Map<String, Object> getDadosCompartilhados();

    public abstract List<EnumValidacoes> getValidacoes();

    public static Set<Menssagem> validar(Object object) throws Exception {

        for (EnumValidacoesAgrupador agrupador : EnumValidacoesAgrupador.values()) {
            if (agrupador.getClazz().equals(object.getClass())) {
                return agrupador.executarValidacoes(object);
            }
        }

        return null;
    }


    private Set<Menssagem> executarValidacoes(Object object) throws Exception {

        ThreadPool threadPool = new ThreadPool();

        Set<Future<Set<Menssagem>>> menssagensFuture = new HashSet<Future<Set<Menssagem>>>();

        for (EnumValidacoes validacao : this.getValidacoes()) {
            menssagensFuture.add((Future<Set<Menssagem>>) threadPool.executeAsync(new Worker(object, this.getDadosCompartilhados(), validacao)));
        }

        threadPool.aguardarProcessamento();

        Set<Menssagem> menssagens = new HashSet<Menssagem>();

        for (Future<Set<Menssagem>> menssagem : menssagensFuture) {
            menssagens.addAll(menssagem.get());
        }

        return menssagens;
    }


}

