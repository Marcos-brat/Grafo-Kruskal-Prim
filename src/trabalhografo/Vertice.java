
package trabalhografo;

import java.util.ArrayList;

public class Vertice
{
    private String dado;
    private ArrayList<Aresta> origem;
    private ArrayList<Aresta> destino;
    
    public Vertice(String dado)
    {
        this.dado = dado;
        this.origem = new ArrayList<Aresta>();
        this.destino = new ArrayList<Aresta>();
    }
    
    public void adicionarArestaOrigem(Aresta aresta)
    {
        this.origem.add(aresta);
    }
    
    public void adicionarArestaDestino(Aresta aresta)
    {
        this.destino.add(aresta);
    }

    public String getDado()
    {
        return dado;
    }
    
    
}
