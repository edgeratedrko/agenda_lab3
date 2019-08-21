package br.unifil.dc.lab2;
import java.io.IOException;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.regex.Pattern;
import java.util.Collections;
import java.util.Comparator;

public class Main {

    public Main() {
    }

    public static void main(String[] args) throws IOException{
        Menu();
    }

    public static void Menu() throws IOException{
        boolean isActive = true;
        Read rd = new Read();
        ArrayList<Agenda> agList = new ArrayList<Agenda>();
        agList = rd.reader();
        Scanner sc = new Scanner(System.in);
        int ind = 0;
        while(isActive) {
            System.out.println("Escolha uma das opções:");
            System.out.println("1-Adicionar à agenda");
            System.out.println("2-Buscar contato");
            System.out.println("3-Lista de contatos");
            System.out.println("4-Deletar contato");
            System.out.println("5-Editar contato");
            System.out.println("9-Encerrar");
            int opt = sc.nextInt();
            switch(opt) {
                case 1:
                    sc.nextLine();
                    System.out.println("Insira o nome do contato: ");
                    String n = sc.nextLine();
                    n = capitalizeName(n);
                    System.out.println("Insira o número do contato: ");
                    String nm = "";
                    while(true) {
                        nm = sc.nextLine();
                        if(nm.matches("[0-9]+") && nm.length() >= 8) {
                            break;
                        } else {
                            System.out.println("Número inválido, favor inserir novamente: ");
                        }
                    }
                    agList.add(new Agenda(n,nm));
                    sorter(agList);
                    ind++;
                    break;
                case 2:
                    sc.nextLine();
                    System.out.println("Digite o nome do contato que deseja procurar: ");
                    String search = sc.nextLine();
                    search = capitalizeName(search);
                    for(int i2 = 0; i2 < agList.size(); i2++) {
                        if(agList.get(i2).getNome().toUpperCase().contains(search.toUpperCase())) {
                            System.out.println(agList.get(i2).getNome() + ";" +
                            agList.get(i2).getNumber());
                        }
                    }
                    break;
                case 3:
                    sorter(agList);
                    for(int i3 = 0; i3 < agList.size(); i3++) {
                        System.out.println(agList.get(i3).getNome() + "; " +
                        agList.get(i3).getNumber());
                    }
                    break;
                case 4:
                    sc.nextLine();
                    int contador = 0;
                    int c = 0;
                    int[] pos = new int[9];

                    for(int z = 0; z < pos.length; z++) {
                        pos[z] = -1;
                    }

                    System.out.println("Digite o nome do contato que deseja excluir: ");
                    for(int i3 = 0; i3 < agList.size(); i3++) {
                        System.out.println(agList.get(i3).getNome() + "; " +
                                agList.get(i3).getNumber());
                    }
                    String s2 = sc.nextLine();
                    s2 = capitalizeName(s2);

                    for(int i4 = 0; i4 < agList.size(); i4++) {
                        if (agList.get(i4).getNome().equals(s2)) {
                            pos[contador] = i4;
                            contador++;
                        }
                    }

                    if(contador > 0) {
                        System.out.println("Digite a opção que deseja excluir: ");
                        for(int a = 0; a < pos.length; a++) {
                            if(pos[a] >= 0) {
                                System.out.println(a + " " +
                                    agList.get(pos[a]).getNome() + ", " + agList.get(pos[a]).
                                    getNumber());
                            }
                        }
                        while(true) {
                            c = sc.nextInt();
                            if(c <= pos.length) {
                                agList.remove(agList.get(pos[c]));
                                break;
                            } else {
                                System.out.println("Posição inválida, digite novamente: ");
                            }
                        }
                    } else {
                        System.out.println("Opção inválida.");
                        break;
                    }

                    sorter(agList);
                    break;
                case 5:
                    sc.nextLine();
                    int c2 = 0;
                    String subs = "";
                    int cont2 = 0;
                    int[] pos2 = new int[agList.size()];

                    for(int w = 0; w < pos2.length; w++) {
                        pos2[w] = -1;
                    }

                    String newNumb = "";
                    System.out.println("Digite o nome do contato que deseja editar: ");
                    String s3 = sc.nextLine();
                    s3 = capitalizeName(s3);

                    for(int i7 = 0; i7 < agList.size(); i7++) {
                        if (agList.get(i7).getNome().toUpperCase().contains(s3.toUpperCase())) {
                            pos2[cont2] = i7;
                            cont2++;
                        }
                    }

                    if(cont2 > 0) {
                        System.out.println("Digite a opção que deseja editar: ");
                        for(int inf = 0; inf < pos2.length; inf++) {
                            if(pos2[inf] >= 0) {
                                System.out.println(inf + " " +
                                        agList.get(pos2[inf]).getNome() + ", " + agList.get(pos2[inf]).
                                        getNumber());
                            }
                        }
                    }

                    while(true) {
                        c2 = sc.nextInt();
                        if(pos2[c2] <= pos2.length) {
                            sc.nextLine();
                            System.out.println("Digite o novo nome do contato: ");
                            subs = capitalizeName(sc.nextLine());
                            System.out.println("Digite o novo número: ");
                            while (true) {
                                newNumb = sc.nextLine();
                                if (newNumb.matches("[0-9]+") && newNumb.length() >= 8) {
                                    break;
                                } else {
                                    System.out.println("Número inválido, favor inserir novamente: ");
                                }
                            }
                        }
                            agList.set(pos2[c2], new Agenda(subs,newNumb));
                            sorter(agList);
                            break;
                        }
                    break;
                case 9:
                    isActive = false;
                    break;

            }
        }
        try {
            Save sv = new Save(agList);
        } catch(IOException e) {
            System.out.println(e.getMessage());
        }
    }

    public static String capitalizeName(String name) {

        StringBuilder converted = new StringBuilder();

        boolean convertNext = true;
        for (char ch : name.toCharArray()) {
            if (Character.isSpaceChar(ch)) {
                convertNext = true;
            } else if (convertNext) {
                ch = Character.toTitleCase(ch);
                convertNext = false;
            } else {
                ch = Character.toLowerCase(ch);
            }
            converted.append(ch);
        }

        return converted.toString();
    }

    public static ArrayList<Agenda> sorter(ArrayList<Agenda> ag) {
        Collections.sort(ag, new Comparator<Agenda>() {
            @Override
            public int compare(Agenda a1, Agenda a2) {
                return a1.getNome().compareToIgnoreCase(a2.getNome());
            }
        });
        return ag;
    }
}
