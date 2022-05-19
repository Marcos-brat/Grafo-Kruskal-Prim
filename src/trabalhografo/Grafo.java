
package trabalhografo;

import java.util.ArrayList;

public class Grafo 
{
    private ArrayList<Aresta> arestas;
    private ArrayList<Vertice> vertices;
    private ArrayList<String> origem;
    private ArrayList<String> destino;
    private int[][] matrizAdj;
    private Lista listaAdj;
    private int tamanho;
    private int[][] matrizInc;
    private ArrayList<Aresta> auxInc;
    private boolean emissao;
    private boolean recepcao;
    private Kruskal agm;
    private Prim prim;
    
    public Grafo()
    {
        this.arestas = new ArrayList<Aresta>();
        this.vertices = new ArrayList<Vertice>();
        this.origem = new ArrayList();
        this.destino = new ArrayList();
        this.listaAdj = new Lista();
        this.auxInc = new ArrayList<Aresta>();
        this.emissao = true;
        this.recepcao = true;
        
    }
    
    public void inicializaMatriz(int tam)
    {
        this.tamanho = tam;
        this.matrizAdj = new int[tam][tam];
    }
    
    public void inicializaMatrizIncidencia(int tamA)
    {
        this.matrizInc = new int[tamanho][tamA];
    }
    
    public void inicializaAGM()
    {
        this.agm = new Kruskal(arestas, matrizAdj, tamanho, origem, destino, vertices);
    }
    
    public void inicializaPrim()
    {
        this.prim = new Prim(matrizAdj, vertices);
    }
    
    public void adicionarVertice(String dado)
    {
        Vertice novoVertice = new Vertice(dado);
        this.vertices.add(novoVertice);
    }
    
    public Vertice getVertice(String dado)
    {
        Vertice vertice = null;
        for(int i = 0; i < this.vertices.size(); i++)
        {
            if(this.vertices.get(i).getDado().equalsIgnoreCase(dado))
            {
                vertice = this.vertices.get(i);
                return vertice;
            }
        }
        return null;
    }
    
    public void adicionarAresta(String dadoOrigem, String dadoDestino, int peso, boolean digrafo)
    {
        if(digrafo)
        {
            Vertice origem = getVertice(dadoOrigem);
            Vertice destino = getVertice(dadoDestino);
            Aresta aresta = new Aresta(origem, destino, peso);
            origem.adicionarArestaOrigem(aresta);
            destino.adicionarArestaDestino(aresta);
            this.arestas.add(aresta);
        }
        else
        {
            Vertice origem = getVertice(dadoOrigem);
            Vertice destino = getVertice(dadoDestino);
            Aresta aresta = new Aresta(origem, destino, peso);
            origem.adicionarArestaOrigem(aresta);
            destino.adicionarArestaDestino(aresta);
            this.arestas.add(aresta);
            
            origem = getVertice(dadoDestino);
            destino = getVertice(dadoOrigem);
            aresta = new Aresta(origem, destino, peso);
            origem.adicionarArestaOrigem(aresta);
            destino.adicionarArestaDestino(aresta);
            this.arestas.add(aresta);
        }
    }
    
    public ArrayList<Vertice> getVertices() {
        return vertices;
    }

    public ArrayList<Aresta> getArestas() {
        return arestas;
    }
    
    public void exibirVertices()
    {
        for(int i = 0; i < vertices.size(); i++)
            System.out.println("\t["+vertices.get(i).getDado()+"]");
    }
    
    public void exibirArestas()
    {
        for(int i = 0; i < arestas.size(); i++)
            System.out.println("\t["+arestas.get(i).getOrigem().getDado()+" ---> "+ arestas.get(i).getDestino().getDado()+"]");
    }
    
    public void criaOrigemDestino()
    {
        for(int i = 0; i < vertices.size(); i++)
        {
            origem.add(vertices.get(i).getDado());
            destino.add(vertices.get(i).getDado());
        }
    }
    
    public void criarMatrizAdjacente(boolean temPeso)
    {
        int peso;
        int existe;
        for(int i = 0; i < tamanho; i++)
        {
            for(int j = 0; j < tamanho; j++)
            {
                if(temPeso == false)
                {
                    existe = buscaLigacao(origem.get(i), destino.get(j));
                    if(existe > 0)
                        matrizAdj[i][j] = 1;  
                    else
                        matrizAdj[i][j] = 0;
                }
                else
                {
                    existe = buscaLigacao(origem.get(i), destino.get(j));
                    if(existe > 0)
                    {
                        peso = retornaPeso(origem.get(i), destino.get(j));
                        matrizAdj[i][j] = peso;
                    }
                    else
                        matrizAdj[i][j] = 0;
                }   
            }
        }
    }
    
    public int buscaLigacao(String origem, String destino)
    {
        int cont = 0;
        for(int i = 0; i < arestas.size(); i++)
        {
            if(arestas.get(i).getOrigem().getDado().compareToIgnoreCase(origem) == 0 && arestas.get(i).getDestino().getDado().compareToIgnoreCase(destino) == 0)
                cont++;
        }
        return cont;
    }
    
    public boolean buscaArestaDupla()
    {
        int cont = 0;
        for(int i = 0; i < arestas.size() - 1; i++)
        {
            for(int j = i + 1; j < arestas.size(); j++)
            {
                if(arestas.get(i).getOrigem().getDado().compareToIgnoreCase(arestas.get(j).getOrigem().getDado()) == 0
                   && arestas.get(i).getDestino().getDado().compareToIgnoreCase(arestas.get(j).getDestino().getDado()) == 0
                   && j > i+1)
                    return false;
            }
        }
        return true;
    }
    
    public int retornaPeso(String origem, String destino)
    {
        for(int i = 0; i < arestas.size(); i++)
        {
            if(arestas.get(i).getOrigem().getDado().compareToIgnoreCase(origem) == 0 && arestas.get(i).getDestino().getDado().compareToIgnoreCase(destino) == 0)
                return arestas.get(i).getPeso();
        }
        return -1;
    }
    
    public void exibeMatrizAdjacente()
    {
        System.out.print("   ");
        for(int k = 0; k < tamanho; k++)
            System.out.print(""+destino.get(k)+"| ");
        System.out.println("");
        for(int i = 0; i < tamanho; i++)
        {
            System.out.print(""+origem.get(i)+"|");
            for(int j = 0; j < tamanho; j++)
            {
                System.out.print(""+matrizAdj[i][j]+" | ");
            }
            System.out.println("");
        }
    }
    
    public boolean AnalisaSeMatrizAdjSimples(boolean tempeso, boolean digrafo)
    {
        int existe;
        if(digrafo == false)
        {
            if(tempeso == false)
            {
                for(int i = 0; i < tamanho; i++)
                {
                    for(int j = 0; j < tamanho; j++)
                    {
                        if((i == j && matrizAdj[i][j] > 0))
                            return false;
                    }   
                }
            }
            else
            {
                for(int i = 0; i < tamanho; i++)
                {
                    for(int j = 0; j < tamanho; j++)
                    {
                        existe = buscaLigacao(origem.get(i), destino.get(j));
                        if(existe > 1 || (i == j && matrizAdj[i][j] > 0))
                            return false;
                    }   
                }
            }
        }
        else{
            
            for (int i = 0; i < tamanho; i++) 
            {
                for (int j = 0; j < tamanho; j++) 
                {
                    if(i == j && matrizAdj[i][j] > 0)
                        return false;
                }
            }
            
        }
        return true;
    }
    
    public void criarListaAdj()
    {
        NoCabeca cab = listaAdj.getInicio();
        for (int i = 0; i < vertices.size(); i++) 
        {
            listaAdj.inserirCabecas(vertices.get(i).getDado());
        }
        for(int i = 0; i < arestas.size(); i++)
        {
           cab = listaAdj.getNoCabecas(arestas.get(i).getOrigem().getDado());
           listaAdj.inserirCorpo(arestas.get(i).getDestino().getDado(), arestas.get(i).getPeso(), cab);
        }
    }
    
    public void exibirListaAdj(boolean temPeso)
    {
        NoCabeca auxCab = listaAdj.getInicio();
        NoCorpo auxCorp;
        if(temPeso == false)
        {
            while(auxCab != null)
            {
                System.out.println("");
                System.out.print("["+auxCab.getDado()+"]");
                auxCorp = auxCab.getCorpo();
                while(auxCorp != null)
                {
                    System.out.print("-->");
                    System.out.print("["+auxCorp.getDado()+"]");
                    auxCorp = auxCorp.getProx();
                }
                System.out.println("");
                auxCab = auxCab.getProx();
            }
        }
        else{
            while(auxCab != null)
            {
                System.out.println("");
                System.out.print("["+auxCab.getDado()+"]");
                auxCorp = auxCab.getCorpo();
                while(auxCorp != null)
                {
                    System.out.print("-->");
                    System.out.print("["+auxCorp.getDado()+"|"+auxCorp.getPeso()+"]");
                    auxCorp = auxCorp.getProx();
                }
                System.out.println("");
                auxCab = auxCab.getProx();
                
            }
        }
        
    }
    
    public void criaMatrizIncidencia(boolean digrafo, boolean temPeso)
    {
        
        String par;
        int pos = 0;
        if(digrafo == false)
        {
            while(pos < arestas.size())
            {
                auxInc.add(arestas.get(pos));
                pos = pos + 2;
            }
            if(temPeso == false)
            {
                for (int i = 0; i < vertices.size(); i++) 
                {
                    for (int j = 0; j < auxInc.size(); j++) 
                    {
                        if(vertices.get(i).getDado().compareToIgnoreCase(auxInc.get(j).getOrigem().getDado()) == 0)
                            matrizInc[i][j] = 1;
                        if(vertices.get(i).getDado().compareToIgnoreCase(auxInc.get(j).getDestino().getDado()) == 0)
                            matrizInc[i][j] = 1;
                    }
                }
            }
            else{
               for (int i = 0; i < vertices.size(); i++) 
                {
                    for (int j = 0; j < auxInc.size(); j++) 
                    {
                        if(vertices.get(i).getDado().equalsIgnoreCase(auxInc.get(j).getOrigem().getDado()))
                            matrizInc[i][j] = auxInc.get(j).getPeso();
                        if(vertices.get(i).getDado().equalsIgnoreCase(auxInc.get(j).getDestino().getDado()))
                            matrizInc[i][j] = auxInc.get(j).getPeso();
                    }
                } 
            }
        }
        else{
            while(pos < arestas.size())
            {
                auxInc.add(arestas.get(pos));
                pos = pos + 1;
            }
            if(temPeso == false)
            {
                for (int i = 0; i < vertices.size(); i++) 
                {
                    for (int j = 0; j < arestas.size(); j++) 
                    {
                        if(vertices.get(i).getDado().equalsIgnoreCase(arestas.get(j).getOrigem().getDado()))
                            matrizInc[i][j] = -1;
                        if(vertices.get(i).getDado().equalsIgnoreCase(arestas.get(j).getDestino().getDado()))
                            matrizInc[i][j] = 1;
                            
                    }
                }
            }
            else{
                for (int i = 0; i < vertices.size(); i++) 
                {
                    for (int j = 0; j < arestas.size(); j++) 
                    {
                        if(vertices.get(i).getDado().equalsIgnoreCase(arestas.get(j).getOrigem().getDado()))
                            matrizInc[i][j] = -1 * arestas.get(j).getPeso();
                        if(vertices.get(i).getDado().equalsIgnoreCase(arestas.get(j).getDestino().getDado()))
                            matrizInc[i][j] = arestas.get(j).getPeso();
                    }
                } 
            }
        }
    }
    
    public void exibeMatrizInc()
    {
        ArrayList<String> aux = new ArrayList<String>();
        String par;
        for (int i = 0; i < auxInc.size(); i++) 
        {
            par = auxInc.get(i).getOrigem().getDado().concat(";"+auxInc.get(i).getDestino().getDado());
            aux.add(par);
        }
        System.out.print("   ");
        for (int i = 0; i < aux.size(); i++)
        {
            System.out.print(""+aux.get(i)+"|");
        }
        System.out.println("");
        for (int i = 0; i < tamanho; i++)
        {
            System.out.print(""+vertices.get(i).getDado()+"|");
            for (int j = 0; j < aux.size(); j++) 
            {
                System.out.print(" "+matrizInc[i][j]+"  | ");
            }
            System.out.println("");
        }
    }
    
    public boolean VerificaSeRegular(boolean digrafo)
    {
        int grau = 0, cont;
        int emi = 0, rec = 0;
        if(digrafo == false)
        {
            for (int i = 0; i < tamanho; i++)
            {
                if(matrizAdj[0][i] > 0)
                    grau++;
            }
            for (int i = 0; i < tamanho; i++) 
            {
                cont = 0;
                for (int j = 0; j < tamanho; j++) 
                {
                    if(matrizAdj[i][j] > 0)
                        cont++;
                }
                if(cont != grau)
                    return false;
            }
        }
        else{
            for (int i = 0; i < tamanho; i++)
            {
                if(matrizAdj[0][i] > 0)
                    emi++;
            }
            for (int i = 0; i < tamanho; i++)
            {
                if(matrizAdj[i][0] > 0)
                    rec++;
            }
            
            //Regular de Emissao
            for (int i = 0; i < tamanho ; i++) 
            {
                cont = 0;
                for (int j = 0; j < tamanho; j++) 
                {
                    if(matrizAdj[i][j] > 0)
                        cont++;
                }
                if(cont != emi)
                {
                    this.emissao = false;
                }
            }
            //Regular de Recepção
            for (int i = 0; i < tamanho; i++) 
            {
                cont = 0;
                for (int j = 0; j < tamanho; j++) 
                {
                    if(matrizAdj[j][i] > 0)
                        cont++;
                }
                if(cont != rec)
                {
                    this.recepcao = false;
                }
            }
        }
        return true;
    }

    public boolean isEmissao() {
        return emissao;
    }

    public boolean isRecepcao() {
        return recepcao;
    }

    public boolean verificaSeCompleto()
    {
        int cont = 0;
        for (int i = 0; i < tamanho; i++)
        {
            cont = 0;
            for (int j = 0; j < auxInc.size(); j++)
            {
                if(matrizInc[i][j] > 0)
                    cont++;
            }
            if(cont != auxInc.size()-1)
                return false;
        }
        
        return true;
    }

    public void exibeAgmArestasClassificadas(int op)
    {
        inicializaAGM();
        if(op == 1)
            agm.OrdenaArestas();
        else
            agm.OrdenaArestasMax();
        agm.exibeArestasClassificadas();
    }
    
    public void exibeAgmMatrizComponentes()
    {
        agm.geraMatrizComponentes();
        agm.exibeMatrizComponentes();
    }
    
    public void exibeAgmArestasSelecionadas()
    {
        agm.exibeArestasSelecionadas();
    }
    
    public void exibeMatrizAgmCompleta()
    {
        agm.geraMatrizComponentes();
        agm.exibeMatrizCompleta();
    }
    
    public int somaCustoAgm()
    {
        return agm.somaCustoMinimo();
    }
    
    public void exibeVerticesPai(int op)
    {
        inicializaPrim();
        if(op == 1)
            prim.startPrim();
        else
            prim.startPrimMax();
        prim.exibePaiDeVertices();
    }
    
    public void exibeArestasSelecionadasPrim()
    {
        prim.exibeArestasSelecionadas();
    }
    
    public int somaCustoPrim()
    {
        return prim.somaCustoMinimo();
    }
}
