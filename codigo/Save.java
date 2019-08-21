package br.unifil.dc.lab2;
import java.io.IOException;
import java.io.*;
import java.util.ArrayList;

/**
 * Classe responsável por salvar os contatos em um .txt
 */
public class Save {
    FileWriter output = null;

    /**
     * Construtor da classe, no qual já ocorre o processo de salvar o nome e o número do ArrayList de
     * agenda em um arquivo .txt; Utiliza o FileWriter para escrever
     * @param ag ArrayList de Agendas (objeto) que contém o nome e o número do contato.
     * @throws IOException
     */
    public Save(ArrayList<Agenda> ag) throws IOException {

        try {
            output = new FileWriter("out.txt");
            for(Agenda newAg : ag) {
                output.write(newAg.getNome() + ";" + newAg.getNumber() + '\n');
            }
        } finally {
            if(output != null) {
                output.close();
            }
        }
    }
}
