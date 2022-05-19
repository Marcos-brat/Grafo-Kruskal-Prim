
package trabalhografo;


public class NoCorpo 
{
    private String dado;
    private int peso;
    private NoCorpo prox;
    
    public NoCorpo(String dado, int peso)
    {
        this.dado = dado;
        this.peso = peso;
        this.prox = null;
    }

    public String getDado() {
        return dado;
    }

    public void setDado(String dado) {
        this.dado = dado;
    }

    public int getPeso() {
        return peso;
    }

    public void setPeso(int peso) {
        this.peso = peso;
    }

    public NoCorpo getProx() {
        return prox;
    }

    public void setProx(NoCorpo prox) {
        this.prox = prox;
    }
    
    
}
