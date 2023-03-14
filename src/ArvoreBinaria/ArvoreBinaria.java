package ArvoreBinaria;

/*
 * @author Éllen Oliveira Silva Neves (20202BSI0071) e Carlos Breno Norato Rosa (20202BSI0233)
 */

import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.Writer;
import java.nio.Buffer;
import java.util.LinkedList;

public class ArvoreBinaria<T extends Comparable> {

    protected int altura;
    protected int quant;
    protected No<T> raiz;
    protected T piorNo;

    public ArvoreBinaria() {
        this.raiz = null;
    }

    public void insereNo(T dado) {
        No<T> no = new No<T>(dado);
        if (this.raiz == null) { //se a árvore estiver vazia, adiciona na raiz
            this.raiz = no;

        } else { //percorre a avr
            No<T> noAtual = this.raiz; //começa na raiz

            while (noAtual.getDado().compareTo(no.getDado()) != 0) { //enquanto o dado não tiver sido adicionado
                
                if (no.getDado().compareTo(noAtual.getDado()) < 0) { //caminha pra esquerda
                    if (noAtual.getEsq() != null) { //se a esquerda do atual não for vazia, caminha pra esquerda
                        noAtual = noAtual.getEsq();
                    } else { //se esquerda do atual for vazia, adiciona dado à esquerda
                        noAtual.setEsq(no);
                        noAtual = noAtual.getEsq();
                    }

                } else { //caminha pra direita
                    if (noAtual.getDir() != null) { //se direita do atual não for vazia, caminha pra direita
                        noAtual = noAtual.getDir();
                    } else { //se direita do atual for vazia, adiciona dado à direita
                        noAtual.setDir(no);
                        noAtual = noAtual.getDir();
                    }
                }

            } //fim da adição

        } //fim do percorrer avr

        this.quant += 1; //atualiza qtd
    }

    protected No<T> insereNoRecursivo(No<T> atual, No<T> novo) {
        if (novo.getDado().compareTo(atual.getDado()) < 0) {
            if (atual.getEsq() == null)
                atual.setEsq(novo);
            else
                atual.setEsq(insereNoRecursivo(atual.getEsq(), novo));
        } else {
            if (atual.getDir() == null)
                atual.setDir(novo);
            else
                atual.setDir(insereNoRecursivo(atual.getDir(), novo));
        }
        return atual;
    }

    public No<T> buscaNo(T dado) {
        No<T> noAtual = this.raiz; //começa da raiz

        while(noAtual != null && noAtual.getDado().compareTo(dado) != 0) { //enquanto noAtual n for nem vazio nem for o dado a ser buscado
            
            if (dado.compareTo(noAtual.getDado()) < 0) { //caminha a esquerda
                noAtual = noAtual.getEsq();            
            } else { //caminha a direita
                noAtual = noAtual.getDir();
            }

        }

        if (noAtual == null) //se nao achou, retorna nulo
            return null;
        return noAtual; //se achou, retorna o no
    }

    public void removeNo(T dado){
        this.raiz = removeNoRecursivo(dado, this.raiz);
    }

    public No<T> removeNoRecursivo(T dado, No<T> noAtual){
        No<T> noSubstituto;

        if (noAtual == null) {
            return null;
        }
        if (dado.compareTo(noAtual.getDado()) < 0 ) 
            noAtual.setEsq(removeNoRecursivo(dado, noAtual.getEsq()));
        else if (dado.compareTo(noAtual.getDado()) > 0 ) 
            noAtual.setDir(removeNoRecursivo(dado, noAtual.getDir()));
        else{ 
                
            if (noAtual.getEsq() == null) 
                return noAtual.getDir();
    
            else if (noAtual.getDir() == null) 
                return noAtual.getEsq();

            else {
                noSubstituto = menorNo(noAtual.getDir());
                noAtual.setDado(noSubstituto.getDado());
                noAtual.setDir(removeNoRecursivo(noSubstituto.getDado(), noAtual.getDir()));
            }
        }
        return noAtual;
    }

    public No<T> menorNo() {
        No<T> noAtual = this.raiz;
        while (noAtual.getEsq() != null) {
            noAtual = noAtual.getEsq();
        }
        return noAtual;
    }

    public No<T> menorNo(No<T> noAtual) {
        while (noAtual.getEsq() != null) {
            noAtual = noAtual.getEsq();
        }
        return noAtual;
    }

    public No<T> maiorNo() {
        No<T> noAtual = this.raiz; //começa da raiz e retorna o no mais a direita
        while (noAtual.getDir() != null) {
            noAtual = noAtual.getDir();
        }
        return noAtual;
    }

    public void caminhaPreOrdem(No<T> raiz) {
        System.out.println(raiz.getDado()); //printa o primeiro no, dps todos os valores da esquerda e dps todos os valores da direita
        if (raiz.getEsq() != null) {
            caminhaPreOrdem(raiz.getEsq());
        }
        if (raiz.getDir() != null) {
            caminhaPreOrdem(raiz.getDir());
        }
    }

    public void caminhaEmOrdem(No<T> raiz) throws IOException { //modificado para gerar arquivo ao inves de printar
        if (raiz.getDado().compareTo(this.raiz.getDado()) == 0) { //printa no arq a qtd de alunos, dps tds os da esquerda, o no de inicio/raiz e dps todos os da direita
            OutputStream fw = new FileOutputStream("alunos", true);
            Writer w = new OutputStreamWriter(fw);
            BufferedWriter bw = new BufferedWriter(w);
            bw.write(String.valueOf(this.quant));
            bw.close();
        }

        if (raiz.getEsq() != null) {
            caminhaEmOrdem(raiz.getEsq());
        }

        OutputStream fw = new FileOutputStream("alunos", true);
        Writer w = new OutputStreamWriter(fw);
        BufferedWriter bw = new BufferedWriter(w);
        bw.newLine();
        bw.write(raiz.getDado().toString());
        bw.close();

        if (raiz.getDir() != null) {
            caminhaEmOrdem(raiz.getDir());
        }
    }

    public void caminhaPosOrdem(No<T> r) {
        if (r.getEsq() != null) { //vai até o nó mais à esquerda e printa esquerda-direita-esquerda-direita (em ordem crescente de valores)
            caminhaPosOrdem(r.getEsq());
        }

        if (r.getDir() != null) {
            caminhaPosOrdem(r.getDir());
        }

        System.out.println(r.getDado());
    }

    public void caminhaNivel() {
        LinkedList<No<T>> fila = new LinkedList<No<T>>();
        fila.addFirst(raiz);
        No<T> noAtual;
        while (!fila.isEmpty()) {
            noAtual = fila.getLast();
            System.out.println(noAtual.getDado());
            fila.removeLast();
            if (noAtual.getEsq() != null) {
                fila.addFirst(noAtual.getEsq());
            }
            if (noAtual.getDir() != null) {
                fila.addFirst(noAtual.getDir());
            }
        }
    }
    
    public int getAltura() {
        return setAltura();
    }

    public int setAltura() {
        return raiz.obterAltura();
    }

    public int getQuant() {
        return quant;
    }

    public void setQuant(int quant) {
        this.quant = quant;
    }

    public No<T> getRaiz() {
        return raiz;
    }

    public void setRaiz(No<T> raiz) {
        this.raiz = raiz;
    }

    public T getPiorNo() {
        LinkedList<No<T>> fila = new LinkedList<No<T>>();
        LinkedList<No<T>> fila2 = new LinkedList<No<T>>();
        fila.addFirst(raiz);
        fila2.addFirst(raiz);
        No<T> noAtual;
        while (!fila.isEmpty()) {
            noAtual = fila.getLast();
            fila.removeLast();
            if (noAtual.getEsq() != null) {
                fila.addFirst(noAtual.getEsq());
                fila2.addFirst(noAtual.getEsq());
            }
            if (noAtual.getDir() != null) {
                fila.addFirst(noAtual.getDir());
                fila2.addFirst(noAtual.getDir());
            }
        }
        return fila2.getFirst().getDado();
    }

    public void setPiorNo(T piorNo) {
        this.piorNo = getPiorNo();
    }
}