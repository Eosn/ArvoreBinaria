package ArvoreBinaria;

/*
 * @author Éllen Oliveira Silva Neves (20202BSI0071) e Carlos Breno Norato Rosa (20202BSI0233)
 */

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.InputMismatchException;
import java.util.Scanner;

public class App {
    public static void main(String[] args) throws Exception {
        ArvoreBinaria<Aluno> arvre = new ArvoreBinaria<Aluno>();
        ArvoreAVL<Aluno> alvre = new ArvoreAVL<Aluno>();
        Scanner s = new Scanner(System.in);
        int user;
        int qtd;

        FileReader fr = new FileReader("src/ArvoreBinaria/entradaOrdenada.txt");
        BufferedReader br = new BufferedReader(fr);
        String linha = "";
        String[] dados = new String[3];

        linha = br.readLine();
        qtd = Integer.parseInt(linha);
        linha = br.readLine();
        for (int i = 0; i < qtd; i++) { //le o arquivo e cria a arv
            dados = linha.split(";");
            Aluno b = new Aluno(Integer.valueOf(dados[0]), dados[1], Integer.valueOf(dados[2]));
            arvre.insereNo(b);
            alvre.insereNoAVL(b);
            linha = br.readLine();
        }
        br.close();

        menu();
        user = s.nextInt();
        while (user != 5) {
            if (user < 1 || user > 5) { //verifica opcao
                System.out.println("Opção inválida. Tente novamente.");

            } else if (user == 1) { //exibe estatisticas
                System.out.println("Árvore Binária comum ------------");
                System.out.println("Altura: " + arvre.getAltura());
                System.out.println("Maior elemento: " + arvre.maiorNo().getDado());
                System.out.println("Menor elemento: " + arvre.menorNo().getDado());
                System.out.println("Pior caso de busca: " + arvre.getPiorNo().toString());

                System.out.println("Árvore Binária AVL ------------");
                System.out.println("Altura: " + alvre.getAltura());
                System.out.println("Maior elemento: " + alvre.maiorNo().getDado());
                System.out.println("Menor elemento: " + alvre.menorNo().getDado());
                System.out.println("Pior caso de busca: " + alvre.getPiorNo().toString());

            } else if (user == 2) { //busca por matricula
                Aluno a = new Aluno();
                System.out.println("Qual a matrícula? ");

                try {
                    a.setMatricula(s.nextInt());
                    a = arvre.buscaNo(a).getDado();
                    if (a == null) {
                        System.out.println("Aluno não encontrado.");
                    } else {
                        System.out.println("Matricula: " + a.getMatricula());
                        System.out.println("Nome: " + a.getNome());
                        System.out.println("Nota: " + a.getNota());
                    }

                } catch (InputMismatchException e) {
                    System.out.println("A matrícula informada não é um número.");
                }
                

            } else if (user == 3) { //exclui por matricula
                Aluno a = new Aluno();
                Aluno a2 = new Aluno();
                System.out.println("Qual a matrícula? ");

                try {
                    a.setMatricula(s.nextInt());
                    a = arvre.buscaNo(a).getDado();
                    if (a == null) {
                        System.out.println("Aluno não encontrado.");
                    } else {
                        arvre.removeNo(a);
                        System.out.println("Matricula: " + a.getMatricula());
                        System.out.println("Nome: " + a.getNome());
                        System.out.println("Nota: " + a.getNota());
                    }

                } catch (InputMismatchException e) {
                    System.out.println("A matrícula informada não é um número.");
                }
                

            } else if (user == 4) { //insere aluno
                Aluno a = new Aluno();
                System.out.print("Matricula: ");

                try {
                    a.setMatricula(s.nextInt());
                    System.out.print("Nome: ");
                    a.setNome(s.next());

                    System.out.print("Nota: ");
                    try {
                        a.setNota(s.nextInt());
                        arvre.insereNo(a);
                        alvre.insereNoAVL(a);

                    } catch (InputMismatchException e) {
                    System.out.println("A nota informada não é um número.");
                }
                    
                } catch (InputMismatchException e) {
                    System.out.println("A matrícula informada não é um número.");
                }
                

            }

            menu();
            user = s.nextInt();
        } //sai
        arvre.caminhaEmOrdem(arvre.getRaiz());
        s.close();
    }

    public static void menu() {
        System.out.println("----------------------------------------------");
        System.out.println("1) Exibir estatísticas");
        System.out.println("2) Efetuar busca por matrícula");
        System.out.println("3) Excluír por matrícula");
        System.out.println("4) Incluir aluno");
        System.out.println("5) Sair");
        System.out.println("----------------------------------------------");
    }
}
