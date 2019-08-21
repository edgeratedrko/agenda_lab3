package br.unifil.dc.lab2;
import java.util.*;

/** Classe responsável por criar um objeto de agenda, que conterá o nome e o número
 *  do contato.
 *  Parâmetros: String nome, String number.
 */
public class Agenda {
    String nome;
    String number;

    //Construtor
    public Agenda(String nome, String number) {
        this.nome = nome;
        this.number = number;
    }

    //Métodos get a seguir, o primeiro retorna nome e o segundo, número.
    public String getNome() {
        return nome;
    }

    public String getNumber() {
        return number;
    }
}
