package ArvoreBinaria;

/*
 * @author Éllen Oliveira Silva Neves (20202BSI0071) e Carlos Breno Norato Rosa (20202BSI0233)
 */

public class ArvoreAVL<T extends Comparable> extends ArvoreBinaria<T>{
    
    public No<T> rotacaoEsquerda(No<T> no){
        No<T> n = no.getDir();
        no.setDir(n.getEsq());
        n.setEsq(no);
        
        return n;
    }

    public No<T> rotacaoDireita(No<T> no){
        No<T> n = no.getEsq();
        no.setEsq(n.getDir());
        n.setDir(no);
        
        return n;
    }

    public No<T> rotacaoEsquerdaDireita(No<T> no){
        no.setEsq(rotacaoEsquerda(no.getEsq()));
        return rotacaoDireita(no);
    }

    public No<T> rotacaoDireitaEsquerda(No<T> no){
        no.setDir(rotacaoDireita(no.getDir()));
        return rotacaoEsquerda(no);
    }

    public void insereNoAVL(T dado) {
        No<T> novoNo = new No<T>(dado);
        if (this.raiz == null)
            this.raiz = novoNo;
        else
            this.raiz = insereNoRecursivo(this.raiz, novoNo);
    }

    @Override
    protected No<T> insereNoRecursivo(No<T> raiz, No<T> novo) {
        this.raiz = super.insereNoRecursivo(raiz, novo);

        if (raiz.fatorBalanceamento() > 1) {
            if (raiz.getDir().fatorBalanceamento() > 0)
                raiz = this.rotacaoEsquerda(raiz);
            else
                raiz = this.rotacaoDireita(raiz);
        } else if (raiz.fatorBalanceamento() < -1) {
            if (raiz.getEsq().fatorBalanceamento() < 0)
                raiz = this.rotacaoDireita(raiz);
            else
                raiz = this.rotacaoEsquerdaDireita(raiz);
        }
        return raiz;
    }
}
