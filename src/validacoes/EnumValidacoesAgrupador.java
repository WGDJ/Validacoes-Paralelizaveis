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

            HashMap dadosCompartilhados = new HashMap<String, Object>();
            dadosCompartilhados.put("IDENTIFICADOR1", "Valor1");
            dadosCompartilhados.put("IDENTIFICADOR2", new Object());
            dadosCompartilhados.putAll(EnumValidacoesAgrupador.AGRUPAMENTO1.getDadosCompartilhados());

            return dadosCompartilhados;
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



}

