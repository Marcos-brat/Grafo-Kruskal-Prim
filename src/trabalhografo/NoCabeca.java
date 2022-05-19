
package trabalhografo;

public class NoCabeca 
{
    private String dado;
    private NoCabeca prox;
    private NoCorpo corpo;
    
    public NoCabeca(String dado)
    {
        this.dado = dado;
        this.prox = null;
        this.corpo = null;
    }

    public String getDado() {
        return dado;
    }

    public void setDado(String dado) {
        this.dado = dado;
    }

    public NoCabeca getProx() {
        return prox;
    }

    public void setProx(NoCabeca prox) {
        this.prox = prox;
    }

    public NoCorpo getCorpo() {
        return corpo;
    }

    public void setCorpo(NoCorpo corpo) {
        this.corpo = corpo;
    }

    

    
    
}
