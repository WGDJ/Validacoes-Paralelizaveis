package main;

import excecoes.Alerta;
import excecoes.Erro;
import excecoes.Menssagem;
import modelo.ClasseDeModelo1;
import modelo.ClasseDeModelo2;
import util.EstrategiaDeValidacao;

import java.util.Set;

public class Main {

    public static void main(String[] args) throws Exception{

        ClasseDeModelo1 classeDeModelo1 = new ClasseDeModelo1();
        classeDeModelo1.setNome("Nome");


        ClasseDeModelo2 classeDeModelo2 = new ClasseDeModelo2();
        classeDeModelo2.setNome("Nome");
        classeDeModelo2.setNumero("Numero");

        Set<Menssagem> menssagens1 = EstrategiaDeValidacao.validar(classeDeModelo1);
        imprimeMenssagens(menssagens1);

        Set<Menssagem> menssagens2 = EstrategiaDeValidacao.validar(classeDeModelo2);
        imprimeMenssagens(menssagens2);

    }

    private static void imprimeMenssagens(Set<Menssagem> menssagens) {
        for (Menssagem msg : menssagens){

            if(Erro.class.isInstance(Menssagem.class)){
                System.err.println(msg.getMenssagem());
            } else if(Alerta.class.isInstance(Menssagem.class)){
                System.out.println(msg.getMenssagem());
            }

        }
    }
}
