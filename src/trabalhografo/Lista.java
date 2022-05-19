
package trabalhografo;

public class Lista 
{
    private NoCabeca inicio;
    
    public Lista()
    {
        this.inicio = null;
    }
    
    public void inserirCabecas(String dado)
    {
        NoCabeca nova = new NoCabeca(dado);
        if(inicio == null)
            inicio = nova;
        else{
            NoCabeca aux = inicio;
            while(aux.getProx() != null)
                aux = aux.getProx();
            aux.setProx(nova);
        }
    }

    public void inserirCorpo(String dado, int peso, NoCabeca cab)
    {
        NoCorpo nova = new NoCorpo(dado, peso);
        NoCorpo aux = cab.getCorpo();
        if(aux == null)
            cab.setCorpo(nova);
        else{
            
            while(aux.getProx() != null)
                aux = aux.getProx();
            aux.setProx(nova);
        }
    }

    public NoCabeca getNoCabecas(String dado)
    {
        NoCabeca aux = inicio;
        while(aux.getDado().compareToIgnoreCase(dado) != 0)
            aux = aux.getProx();
        if(aux.getDado().compareToIgnoreCase(dado) == 0)
            return aux;
        return null;
    }
    
    public NoCabeca getInicio() {
        return inicio;
    } 
}
