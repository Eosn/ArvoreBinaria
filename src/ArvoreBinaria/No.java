package ArvoreBinaria;

/*
 * @author Éllen Oliveira Silva Neves e Carlos Breno Norato Rosa
 */

public class No<T extends Comparable> {

    protected No<T> esq;
    protected No<T> dir;
    protected T dado;
    
    public No(T dado) {
        this.dado = dado;
    }

    public int obterAltura() {
        return obterAltura(this);
    }

    public int obterAltura(No<T> no) {
        if (no == null) 
            return -1;
        else {
            int hd = obterAltura(no.getDir());
            int he = obterAltura(no.getEsq());
            if (hd>he) 
                return hd+1;    
            else
                return he+1;
        }
    }

    public int fatorBalanceamento(){
        return obterAltura(this.getDir()) - obterAltura(this.getEsq());
    }

    public T getDado() {
        return dado;
    }

    public void setDado(T dado) {
        this.dado = dado;
    }

    public No<T> getDir() {
        return dir;
    }

    public void setDir(No<T> dir) {
        this.dir = dir;
    }

    public No<T> getEsq() {
        return esq;
    }

    public void setEsq(No<T> esq) {
        this.esq = esq;
    }
    
}
