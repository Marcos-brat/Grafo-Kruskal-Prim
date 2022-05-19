
package trabalhografo;

public class ArestaSelecionada 
{
    private String origem;
    private String destino;
    private int peso;

    public ArestaSelecionada() 
    {
        this.origem = null;
        this.destino = null;
        this.peso = -1;
    }

    public String getOrigem() {
        return origem;
    }

    public void setOrigem(String origem) {
        this.origem = origem;
    }

    public String getDestino() {
        return destino;
    }

    public void setDestino(String destino) {
        this.destino = destino;
    }

    public int getPeso() {
        return peso;
    }

    public void setPeso(int peso) {
        this.peso = peso;
    }
    
    
}
