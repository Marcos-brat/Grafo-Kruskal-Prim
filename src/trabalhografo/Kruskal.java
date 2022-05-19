
package trabalhografo;

import java.util.ArrayList;
import javafx.scene.input.ScrollEvent;

public class Kruskal
{
    private ArrayList<Aresta> arestasOrdenadas;
    private int[][] matrizAdj;
    private String[][] matrizComp;
    private ArrayList<Aresta> arestasSelecionadas;
    private int tamanho;
    private ArrayList<String> origem;
    private ArrayList<String> destino;
    private ArrayList<Vertice> vertices;
    
    public Kruskal(ArrayList<Aresta> arestas, int[][] matrizAdj, int tam, ArrayList<String> origem, ArrayList<String> destino, ArrayList<Vertice> vertices)
    {
        this.tamanho = tam;
        this.arestasOrdenadas = arestas;
        this.vertices = vertices;
        this.arestasSelecionadas = new ArrayList<Aresta>();
        this.matrizAdj = matrizAdj;
        this.origem = origem;
        this.destino = destino;
    }

    public void inicializaMatrizComp()
    {
        this.matrizComp = new String[arestasOrdenadas.size()][vertices.size()];
    }
    
    public void OrdenaArestas()
    {
        Aresta aux;
        ArrayList<Aresta> aux2 = new ArrayList<Aresta>();
        for (int i = 0; i < arestasOrdenadas.size()-2; i+=2)
        {
            for (int j = i+2; j < arestasOrdenadas.size(); j+=2) 
            {
                if(arestasOrdenadas.get(j).getPeso() < arestasOrdenadas.get(i).getPeso())
                {
                    aux = arestasOrdenadas.get(i);
                    arestasOrdenadas.set(i, arestasOrdenadas.get(j));
                    arestasOrdenadas.set(j, aux);
                }
            }
        }
        for (int i = 0; i < arestasOrdenadas.size(); i+=2) 
        {
            aux2.add(arestasOrdenadas.get(i));
        }
        arestasOrdenadas = aux2;
    }
    
    public void OrdenaArestasMax()
    {
        Aresta aux;
        ArrayList<Aresta> aux2 = new ArrayList<Aresta>();
        for (int i = 0; i < arestasOrdenadas.size()-2; i+=2)
        {
            for (int j = i+2; j < arestasOrdenadas.size(); j+=2) 
            {
                if(arestasOrdenadas.get(j).getPeso() > arestasOrdenadas.get(i).getPeso())
                {
                    aux = arestasOrdenadas.get(i);
                    arestasOrdenadas.set(i, arestasOrdenadas.get(j));
                    arestasOrdenadas.set(j, aux);
                }
            }
        }
        for (int i = 0; i < arestasOrdenadas.size(); i+=2) 
        {
            aux2.add(arestasOrdenadas.get(i));
        }
        arestasOrdenadas = aux2;
    }
    
    public void exibeArestasClassificadas()
    {
        
        for(int i = 0; i < arestasOrdenadas.size(); i++)
        {
            System.out.print(" | "+arestasOrdenadas.get(i).getOrigem().getDado()+","+arestasOrdenadas.get(i).getDestino().getDado());
        }
        System.out.print(" |");
        System.out.println("");
    }
    
    public int buscaPosicaoVertice(String dado)
    {
        for (int i = 0; i < vertices.size(); i++) 
        {
            if(dado.equalsIgnoreCase(vertices.get(i).getDado()))
                return i;
        }
        return -1;
    }
    
    public boolean matrizVazia()
    {
        for (int i = 0; i < vertices.size(); i++)
        {
            if(matrizComp[0][i] == null)
                return true;
        }
        return false;
    }
    
    public boolean linhaIgual(String[] aux)
    {
        for (int i = 1; i < aux.length; i++) 
        {
            if(aux[0] != aux[i])
                return false;
        }
        return true;
    }
    
    public void insereNaMatriz(int i, String[] aux)
    {
        for (int j = 0; j < vertices.size(); j++) 
        {
            matrizComp[i][j] = aux[j];
        }
    }
    
    public boolean TodosFoiVisitado(boolean[] visitado)
    {
        for (int i = 0; i < visitado.length; i++) 
        {
            if(visitado[i] != true)
                return false;
        }
        return true;
    }
    
    
    
    public String[] buscaParidade(ArrayList<String> compara, String[] aux, int i, String menor)
    {
        for(int j = 0; j < i; j++)
        {
            if(compara.contains(arestasOrdenadas.get(j).getOrigem().getDado()) 
                || compara.contains(arestasOrdenadas.get(j).getDestino().getDado()))
            {
                aux[buscaPosicaoVertice(arestasOrdenadas.get(j).getOrigem().getDado())] = menor;
                aux[buscaPosicaoVertice(arestasOrdenadas.get(j).getDestino().getDado())] = menor;
                compara.add(arestasOrdenadas.get(j).getOrigem().getDado());
                compara.add(arestasOrdenadas.get(j).getDestino().getDado());
            }
        }
        arestasSelecionadas.add(arestasOrdenadas.get(i));
        return aux;
    }
    
    public void geraMatrizComponentes()
    {
        boolean[] visitado = new boolean[vertices.size()];
        ArrayList<String> compara;
        String paridade;
        inicializaMatrizComp();
        String[] aux = new String[vertices.size()];
        int posOrigem, posDestino;
        for (int i = 0; i < arestasOrdenadas.size(); i++) 
        {
            posOrigem = buscaPosicaoVertice(arestasOrdenadas.get(i).getOrigem().getDado());
            posDestino = buscaPosicaoVertice(arestasOrdenadas.get(i).getDestino().getDado());
            if(matrizVazia())   //Caso seja o primeiro (Matriz estiver vazia)
            {
                for (int j = 0; j < vertices.size(); j++) 
                {
                    if(j != posOrigem && j != posDestino)
                    {
                        aux[j] = vertices.get(j).getDado();
                        visitado[j] = false;
                    }
                    else
                    {
                        aux[j] = arestasOrdenadas.get(i).getOrigem().getDado();
                        visitado[j] = true;
                    }
                }
                insereNaMatriz(i, aux);
                arestasSelecionadas.add(arestasOrdenadas.get(i));
            }
            else
            {
                if(!linhaIgual(aux))
                {
                    if(visitado[posOrigem] == true && visitado[posDestino] == true)
                    {
                        if(aux[posOrigem].compareToIgnoreCase(aux[posDestino]) != 0)
                        {
                            if(aux[posOrigem].compareToIgnoreCase(aux[posDestino]) < 0)
                            {
                                compara = new ArrayList<String>();
                                compara.add(aux[posOrigem]);
                                compara.add(aux[posDestino]);
                                aux[posDestino] = aux[posOrigem];
                                aux = buscaParidade(compara, aux, i, aux[posOrigem]);
                                insereNaMatriz(i, aux);
                            }
                            else
                            {
                                compara = new ArrayList<String>();
                                compara.add(aux[posDestino]);
                                compara.add(aux[posOrigem]);
                                aux[posOrigem] = aux[posDestino];
                                aux = buscaParidade(compara, aux, i, aux[posDestino]);
                                insereNaMatriz(i, aux);
                            }
                        }
                        else
                        {
                            for (int j = 0; j < vertices.size(); j++) 
                            {
                                matrizComp[i][j] = "-";
                            }
                        }
                    }
                    else
                    {
                        if(visitado[posOrigem] == true && visitado[posDestino] == false)
                        {
                            aux[posDestino] = aux[posOrigem];
                            visitado[posDestino] = true;
                            insereNaMatriz(i, aux);
                            arestasSelecionadas.add(arestasOrdenadas.get(i));
                        }
                        else
                        {
                            if(visitado[posOrigem] == false && visitado[posDestino] == true)
                            {   
                                aux[posOrigem] = aux[posDestino];
                                visitado[posOrigem] = true;
                                insereNaMatriz(i, aux);
                                arestasSelecionadas.add(arestasOrdenadas.get(i));
                            }
                            else//origem e destino false
                            {
                                aux[posOrigem] = arestasOrdenadas.get(i).getOrigem().getDado();
                                aux[posDestino] = arestasOrdenadas.get(i).getOrigem().getDado();
                                visitado[posOrigem] = true;
                                visitado[posDestino] = true;
                                insereNaMatriz(i, aux);
                                arestasSelecionadas.add(arestasOrdenadas.get(i));
                            }
                        }
                    }
                }
                else
                {
                    for (int j = 0; j < vertices.size(); j++) 
                    {
                        matrizComp[i][j] = "-";
                    }
                }
            }
        }
    }
    
    public void exibeMatrizComponentes()
    {
        for (int i = 0; i < arestasOrdenadas.size(); i++) 
        {
            System.out.println("");
            for (int j = 0; j < vertices.size(); j++) 
            {
                System.out.print(" "+matrizComp[i][j]+" |");
            }
        }
        System.out.println("");
    }
    
    public void exibeArestasSelecionadas()
    {
        for (int i = 0; i < arestasSelecionadas.size(); i++) 
        {
            System.out.print(" | "+arestasSelecionadas.get(i).getOrigem().getDado());
            System.out.print(","+arestasSelecionadas.get(i).getDestino().getDado());
        }
        System.out.print(" |");
        System.out.println("");
    }
    
    public void exibeMatrizCompleta()
    {
        System.out.print("     ");
        for (int i = 0; i < vertices.size(); i++)
        {
            System.out.print(vertices.get(i).getDado()+" | ");
        }
        System.out.println("");
        for (int i = 0; i < arestasOrdenadas.size(); i++)
        {
            System.out.print(""+arestasOrdenadas.get(i).getOrigem().getDado());
            System.out.print(","+arestasOrdenadas.get(i).getDestino().getDado()+"|");
            for (int j = 0; j < vertices.size(); j++) 
            {
                System.out.print(" "+matrizComp[i][j]+" |");
            }
            System.out.println("");
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
}
