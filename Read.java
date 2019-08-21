package br.unifil.dc.lab2;
import java.io.FileReader;
import java.util.ArrayList;
import java.io.IOException;
import java.io.BufferedReader;
import java.util.regex.Pattern;


public class Read {
    ArrayList<Agenda> ag = new ArrayList<Agenda>();

    public Read(){
    }

    public ArrayList<Agenda> reader() throws IOException {
        try(BufferedReader br = new BufferedReader(new FileReader("out.txt"))){
            String line;
            while((line = br.readLine()) != null) {
                ag.add(new Agenda(line.substring(0,line.indexOf(";")),
                        line.substring(line.indexOf(";")+1)));
            }
        } catch(IOException e) {
            System.out.println(e.getMessage());
        }
        return ag;
    }

}
