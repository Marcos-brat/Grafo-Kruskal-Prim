
package trabalhografo;

import java.util.ArrayList;

public class Prim 
{
    private int[][] matrizAdj;
    private ArrayList<Vertice> vertices;
    private ArrayList<String> pai;
    private ArrayList<ArestaSelecionada> arestasSelecionadas;
    private ArestaSelecionada aresta;
    
    public Prim(int[][] matrizAdj, ArrayList<Vertice> vertices)
    {
        this.vertices = vertices;
        this.pai = new ArrayList<String>();
        this.arestasSelecionadas = new ArrayList<ArestaSelecionada>();
        this.aresta = new ArestaSelecionada();
        this.matrizAdj = matrizAdj;
    }
    
    public boolean visitouTodos(ArrayList<String> pai)
    {
        for (int i = 0; i < vertices.size(); i++) 
        {
            if(pai.get(i).compareToIgnoreCase("*") == 0)
                return false;
        }
        return true;
    }
    
    public boolean JaVisitado(int pos)
    {
        if(pai.get(pos).compareToIgnoreCase("*") != 0)
            return true;
        return false;
    }
    
    public int buscaMenorLinha()
    {
        int posMenor = -1, menor = 999;
        for (int i = 0; i < vertices.size(); i++) 
        {
            if(pai.get(i).compareToIgnoreCase("*") != 0)
            {
                for (int j = 0; j < vertices.size(); j++) 
                {
                    if(matrizAdj[i][j] > 0 && !JaVisitado(j) && matrizAdj[i][j] < menor)
                    {
                        menor = matrizAdj[i][j];
                        posMenor = i;
                    }
                }
            }
        }
        return posMenor;
    }
    
    public int buscaMenorColuna()
    {
        int posMenor = -1, menor = 999;
        for (int i = 0; i < vertices.size(); i++) 
        {
            if(pai.get(i).compareToIgnoreCase("*") != 0)
            {
                for (int j = 0; j < vertices.size(); j++) 
                {
                    if(matrizAdj[i][j] > 0 && !JaVisitado(j) && matrizAdj[i][j] < menor)
                    {
                        menor = matrizAdj[i][j];
                        posMenor = j;
                    }
                }
            }
        }
        return posMenor;
    }
    
    public int buscaMenorPeso()
    {
        int posMenor = -1, menor = 999;
        for (int i = 0; i < vertices.size(); i++) 
        {
            if(pai.get(i).compareToIgnoreCase("*") != 0)
            {
                for (int j = 0; j < vertices.size(); j++) 
                {
                    if(matrizAdj[i][j] > 0 && !JaVisitado(j) && matrizAdj[i][j] < menor)
                    {
                        menor = matrizAdj[i][j];
                        posMenor = j;
                    }
                }
            }
        }
        return menor;
    }
    
    public void startPrim()
    {
        ArestaSelecionada aresta = new ArestaSelecionada();
        int menor = 999, posMenor = -1, posMenorLinha, posMenorColuna;
        for(int i = 0; i < vertices.size(); i++)
        {
            pai.add("*");
        }
        pai.set(0, vertices.get(0).getDado());
        
        for (int j = 0; j < vertices.size(); j++) 
        {
            if(matrizAdj[0][j] > 0 && matrizAdj[0][j] < menor)
            {
                menor = matrizAdj[0][j];
                posMenor = j;
            }
        }
        pai.set(posMenor, pai.get(0));
        aresta.setOrigem(pai.get(0));
        aresta.setDestino(vertices.get(posMenor).getDado());
        aresta.setPeso(menor);
        arestasSelecionadas.add(aresta);
        while(!visitouTodos(pai))
        {
            //busca pos do menor, dos que ja foi visitados
            posMenorLinha = buscaMenorLinha();
            posMenorColuna = buscaMenorColuna();
            menor = buscaMenorPeso();
            pai.set(posMenorColuna, vertices.get(posMenorLinha).getDado());
            if(pai.get(posMenorColuna).compareToIgnoreCase(vertices.get(posMenorColuna).getDado()) != 0)
            {
                aresta = new ArestaSelecionada();
                aresta.setOrigem(pai.get(posMenorColuna));
                aresta.setDestino(vertices.get(posMenorColuna).getDado());
                aresta.setPeso(menor);
                arestasSelecionadas.add(aresta);
            }
        }
    }
    
    public int somaCustoMinimo()
    {
        int custo = 0;
        for (int i = 0; i < arestasSelecionadas.size(); i++) 
        {
            custo += arestasSelecionadas.get(i).getPeso();
        }
        return custo;
    }
    
    public void exibePaiDeVertices()
    {
        System.out.print("   ");
        for (int i = 0; i < vertices.size(); i++) 
        {
            System.out.print(" | "+vertices.get(i).getDado());
        }
        System.out.println("");
        System.out.print("Pai");
        for (int i = 0; i < vertices.size(); i++) 
        {
            System.out.print(" | "+pai.get(i));
        }
    }
    
    public void exibeArestasSelecionadas()
    {
        for (int i = 0; i < arestasSelecionadas.size(); i++) 
        {
            System.out.print(" | "+arestasSelecionadas.get(i).getOrigem());
            System.out.print(","+arestasSelecionadas.get(i).getDestino());
        }
        System.out.print(" |");
        System.out.println("");
    }
    
    
    
    //Para Gerar Arvore Maxima
    public int buscaMaiorLinha()
    {
        int posMenor = -1, menor = 0;
        for (int i = 0; i < vertices.size(); i++) 
        {
            if(pai.get(i).compareToIgnoreCase("*") != 0)
            {
                for (int j = 0; j < vertices.size(); j++) 
                {
                    if(matrizAdj[i][j] > 0 && !JaVisitado(j) && matrizAdj[i][j] > menor)
                    {
                        menor = matrizAdj[i][j];
                        posMenor = i;
                    }
                }
            }
        }
        return posMenor;
    }
    
    public int buscaMaiorColuna()
    {
        int posMenor = -1, menor = 0;
        for (int i = 0; i < vertices.size(); i++) 
        {
            if(pai.get(i).compareToIgnoreCase("*") != 0)
            {
                for (int j = 0; j < vertices.size(); j++) 
                {
                    if(matrizAdj[i][j] > 0 && !JaVisitado(j) && matrizAdj[i][j] > menor)
                    {
                        menor = matrizAdj[i][j];
                        posMenor = j;
                    }
                }
            }
        }
        return posMenor;
    }
    
    public int buscaMaiorPeso()
    {
        int posMenor = -1, menor = 0;
        for (int i = 0; i < vertices.size(); i++) 
        {
            if(pai.get(i).compareToIgnoreCase("*") != 0)
            {
                for (int j = 0; j < vertices.size(); j++) 
                {
                    if(matrizAdj[i][j] > 0 && !JaVisitado(j) && matrizAdj[i][j] > menor)
                    {
                        menor = matrizAdj[i][j];
                        posMenor = j;
                    }
                }
            }
        }
        return menor;
    }
    
    public void startPrimMax()
    {
        ArestaSelecionada aresta = new ArestaSelecionada();
        int menor = 0, posMenor = -1, posMenorLinha, posMenorColuna;
        for(int i = 0; i < vertices.size(); i++)
        {
            pai.add("*");
        }
        pai.set(0, vertices.get(0).getDado());
        
        for (int j = 0; j < vertices.size(); j++) 
        {
            if(matrizAdj[0][j] > 0 && matrizAdj[0][j] > menor)
            {
                menor = matrizAdj[0][j];
                posMenor = j;
            }
        }
        pai.set(posMenor, pai.get(0));
        aresta.setOrigem(pai.get(0));
        aresta.setDestino(vertices.get(posMenor).getDado());
        aresta.setPeso(menor);
        arestasSelecionadas.add(aresta);
        while(!visitouTodos(pai))
        {
            //busca pos do menor, dos que ja foi visitados
            posMenorLinha = buscaMaiorLinha();
            posMenorColuna = buscaMaiorColuna();
            menor = buscaMaiorPeso();
            pai.set(posMenorColuna, vertices.get(posMenorLinha).getDado());
            if(pai.get(posMenorColuna).compareToIgnoreCase(vertices.get(posMenorColuna).getDado()) != 0)
            {
                aresta = new ArestaSelecionada();
                aresta.setOrigem(pai.get(posMenorColuna));
                aresta.setDestino(vertices.get(posMenorColuna).getDado());
                aresta.setPeso(menor);
                arestasSelecionadas.add(aresta);
            }
        }
    }
    
}
