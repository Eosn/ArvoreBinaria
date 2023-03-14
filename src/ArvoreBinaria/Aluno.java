package ArvoreBinaria;

/*
 * @author Éllen Oliveira Silva Neves e Carlos Breno Norato Rosa
 */

public class Aluno implements Comparable<Aluno> {

    private int matricula;
    private String nome;
    private int nota;

    public Aluno () {
        
    }

    public Aluno(int m, String nm, int nt) {
        this.matricula = m;
        this.nome = nm;
        this.nota = nt;
    }

    @Override
    public int compareTo(Aluno a) {
        if (this.matricula > a.matricula) {
            return 1;
        } else if (this.matricula < a.matricula) {
            return -1;
        }
        return 0;
    }

    @Override
    public String toString() {
        return (this.matricula + ";" + this.nome + ";" + this.nota);
    }

    public int getMatricula() {
        return matricula;
    }

    public String getNome() {
        return nome;
    }

    public int getNota() {
        return nota;
    }

    public void setMatricula(int matricula) {
        this.matricula = matricula;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setNota(int nota) {
        this.nota = nota;
    }
    
}
