
package trabalhografo;

import java.util.Scanner;

public class TrabalhoGrafo 
{

    public static void main(String[] args)
    {
        Grafo grafo = new Grafo();
        Scanner scan = new Scanner(System.in);
        int qtdeVertices = 0, qtdeAresta, menu = 1, opDigrafo, temPeso, peso;
        String dado, dado2;
        boolean digrafo = false, comPeso = false;
        boolean simples;
        do{
            System.out.println("----------------------------------------");
            System.out.println("\t\tMENU");
            System.out.println("----------------------------------------");
            System.out.println("1 - Adicionar Vertices");
            System.out.println("2 - Adicionar Arestas");
            System.out.println("3 - Exibir Matriz de Adjacência");
            System.out.println("4 - Exibir Matriz de Incidência");
            System.out.println("5 - Exibir Lista de Adjacência");
            System.out.println("6 - Exibir Árvore Geradora Mínima (Kruskal)");
            System.out.println("7 - Exibir Árvore Geradora Mínima (Prim)");
            System.out.print("\nOpção: ");
            
            
            int opcao = scan.nextInt();
            switch(opcao)
            {
                case 1:
                {
                    System.out.println("Escolha:");
                    System.out.println("1 - Digrafo");
                    System.out.println("2 - Grafo");
                    System.out.print("\nOpção: ");
                    opDigrafo = scan.nextInt();
                    if(opDigrafo == 1)
                        digrafo = true;
                    System.out.println("----------------------------------------");
                    System.out.println("Quantos Vertices deseja adicionar no grafo?");
                    qtdeVertices = scan.nextInt();   //Tamanho da matriz
                    
                    System.out.println("----------------------------------------");
                    for(int i = 0; i < qtdeVertices; i++)
                    {
                        System.out.println("Digite o dado do "+(i+1)+"º Vertice");
                        dado = scan.next();
                        System.out.println("----------------------------------------");
                        grafo.adicionarVertice(dado);
                    }
                    grafo.criaOrigemDestino();
                    grafo.inicializaMatriz(qtdeVertices);
                    break;
                }
                case 2:
                {
                    
                    System.out.println("----------------------------------------");
                    System.out.println("Quantas Arestas deseja adicionar no grafo?");
                    qtdeAresta = scan.nextInt();
                    System.out.println("----------------------------------------");
                    System.out.println("Deseja por peso nas arestas?");
                    System.out.println("1 - Sim");
                    System.out.println("2 - Não");
                    System.out.print("\nOpção: ");
                    temPeso = scan.nextInt();
                    if(grafo.getVertices().size() > 0)
                    {    
                        for(int i = 0; i < qtdeAresta; i++)
                        {
                            System.out.println("----------------------------------------");
                            System.out.println("\tDados do Grafo");
                            System.out.println("----------------------------------------");
                            grafo.exibirVertices();
                            System.out.println("----------------------------------------");
                            System.out.println("----------------------------------------");
                            System.out.println("Digite o dado do "+(i+1)+"º vertice de origem:");
                            dado = scan.next();
                            System.out.println("----------------------------------------");
                            System.out.println("Agora digite o dado do "+(i+1)+"º vertice destino:");
                            dado2 = scan.next();
                            System.out.println("----------------------------------------");
                            if(temPeso == 1)
                            {
                                comPeso = true;
                                System.out.println("Digite o peso da "+(i+1)+"ª aresta:");
                                peso = scan.nextInt();
                                grafo.adicionarAresta(dado, dado2, peso, digrafo);
                                
                            }
                            else
                            {
                                grafo.adicionarAresta(dado, dado2, 0, digrafo);
                            }
                        }
                    }
                    else
                    {
                        System.out.println("----------------------------------------");
                        System.out.println("\tGRAFO VAZIO");
                        System.out.println("Obrigatório Adicionar Vertices Primeiro!!!");
                        System.out.println("----------------------------------------");
                    }
                    System.out.println("----------------------------------------");
                    System.out.println("\t\tVertices");
                    System.out.println("----------------------------------------");
                    grafo.exibirVertices();
                    System.out.println("----------------------------------------");
                    System.out.println("\t\tArestas");
                    System.out.println("----------------------------------------");
                    grafo.exibirArestas();
                    System.out.println("----------------------------------------");
                    grafo.criarMatrizAdjacente(comPeso);//comPeso boolean para saber se tem peso ou nao
                    grafo.criarListaAdj();
                    grafo.inicializaMatrizIncidencia(qtdeAresta);
                    grafo.criaMatrizIncidencia(digrafo, comPeso);
                    
                    break;
                } 
                case 3:
                {
                    
                    System.out.println("----------------------------------------");
                    System.out.println("\tMatriz de Adjacência");
                    System.out.println("----------------------------------------");
                    if(!grafo.buscaArestaDupla())
                        System.out.println("Não é possível representar com arestas duplas!");
                    else
                    {    
                        grafo.exibeMatrizAdjacente();
                        System.out.println("");
                        System.out.print("É SIMPLES? ");
                        simples = grafo.AnalisaSeMatrizAdjSimples(comPeso, digrafo);
                        if(simples == false)
                            System.out.println("Não!");
                        else
                            System.out.println("Sim!");
                        System.out.print("É REGULAR? ");
                        if(digrafo && grafo.VerificaSeRegular(digrafo))
                        {
                            
                            if(grafo.isEmissao() == true && grafo.isRecepcao() == true)
                                System.out.println("De Emissão e Recepção!");
                            else{
                                if(grafo.isEmissao() == true && grafo.isRecepcao() == false)
                                    System.out.println("Apenas de Emissão!");
                                else
                                    if(grafo.isEmissao() == false && grafo.isRecepcao() == true)
                                        System.out.println("Apenas de Recepção!");
                                    else
                                        System.out.println("Nem de Emissão e nem de Recepção!");
                            }
                        }
                        else
                        {
                            if(grafo.VerificaSeRegular(digrafo))
                                System.out.println("Sim!");
                            else
                                System.out.println("Não!");
                        }
                        System.out.print("É COMPLETO? ");
                        if(grafo.verificaSeCompleto())
                            System.out.println("Sim!");
                        else
                            System.out.println("Não!");
                            
                    }
                    break;  
                }
                case 4:
                {
                    System.out.println("----------------------------------------");
                    System.out.println("\tMatriz de Incidência");
                    System.out.println("----------------------------------------");
                    grafo.exibeMatrizInc();
                    System.out.println("");
                    System.out.print("É SIMPLES? ");
                    simples = grafo.AnalisaSeMatrizAdjSimples(comPeso, digrafo);
                    if(simples == false && !grafo.buscaArestaDupla())
                        System.out.println("Não!");
                    else
                        System.out.println("Sim!");
                    System.out.print("É REGULAR? ");
                    if(digrafo && grafo.VerificaSeRegular(digrafo))
                    {
                        if(grafo.isEmissao() == true && grafo.isRecepcao() == true)
                            System.out.println("De Emissão e Recepção!");
                        else{
                            if(grafo.isEmissao() == true && grafo.isRecepcao() == false)
                                System.out.println("Apenas de Emissão!");
                            else
                                if(grafo.isEmissao() == false && grafo.isRecepcao() == true)
                                    System.out.println("Apenas de Recepção!");
                                else
                                    System.out.println("Nem de Emissão e nem de Recepção!");
                        }
                    }
                    else
                    {
                        if(grafo.VerificaSeRegular(digrafo))
                            System.out.println("Sim!");
                        else
                            System.out.println("Não!");
                    }
                    System.out.print("É COMPLETO? ");
                    if(grafo.verificaSeCompleto())
                        System.out.println("Sim!");
                    else
                        System.out.println("Não!");
                    break;
                }
                case 5:
                {
                    System.out.println("----------------------------------------");
                    System.out.println("\tLista de Adjacência");
                    System.out.println("----------------------------------------");
                    grafo.exibirListaAdj(comPeso);
                    System.out.println("");
                    System.out.print("É SIMPLES? ");
                    simples = grafo.AnalisaSeMatrizAdjSimples(comPeso, digrafo);
                    if(simples == false && !grafo.buscaArestaDupla())
                        System.out.println("Não!");
                    else
                        System.out.println("Sim!");
                    System.out.print("É REGULAR? ");
                    if(digrafo && grafo.VerificaSeRegular(digrafo))
                    {
                        if(grafo.isEmissao() == true && grafo.isRecepcao() == true)
                            System.out.println("De Emissão e Recepção!");
                        else{
                            if(grafo.isEmissao() == true && grafo.isRecepcao() == false)
                                System.out.println("Apenas de Emissão!");
                            else
                                if(grafo.isEmissao() == false && grafo.isRecepcao() == true)
                                    System.out.println("Apenas de Recepção!");
                                else
                                    System.out.println("Nem de Emissão e nem de Recepção!");
                        }
                    }
                    else
                    {
                        if(grafo.VerificaSeRegular(digrafo))
                            System.out.println("Sim!");
                        else
                            System.out.println("Não!");
                    }
                    System.out.print("É COMPLETO? ");
                    if(grafo.verificaSeCompleto())
                        System.out.println("Sim!");
                    else
                        System.out.println("Não!");
                    break;
                }
                case 6:
                {
                    int op = 1;
                    System.out.println("[1] - Mínimo");
                    System.out.println("[2] - Maximo");
                    op = scan.nextInt();
                    System.out.println("----------------------------------------");
                    System.out.println("\tÁrvore Geradora Mínima (Kruskal)");
                    System.out.println("----------------------------------------");
                    System.out.println("\tArestas Classificadas");
                    System.out.println("----------------------------------------");
                    grafo.exibeAgmArestasClassificadas(op);
                    System.out.println("----------------------------------------");
                    System.out.println("\tMatriz de Componentes");
                    System.out.println("----------------------------------------");
                    grafo.exibeMatrizAgmCompleta();
                    System.out.println("----------------------------------------");
                    System.out.println("\tArestas Selecionadas");
                    System.out.println("----------------------------------------");
                    grafo.exibeAgmArestasSelecionadas();
                    System.out.println("----------------------------------------");
                    System.out.println("Custo = "+grafo.somaCustoAgm());
                    break;
                }
                case 7:
                {
                    int op = 1;
                    System.out.println("[1] - Mínimo");
                    System.out.println("[2] - Maximo");
                    op = scan.nextInt();
                    System.out.println("----------------------------------------");
                    System.out.println("\tÁrvore Geradora Mínima (Prim)");
                    System.out.println("----------------------------------------");
                    System.out.println("\tAresta(s) Antecessora(s), (Pai)");
                    System.out.println("----------------------------------------");
                    grafo.exibeVerticesPai(op);
                    System.out.println("");
                    System.out.println("----------------------------------------");
                    System.out.println("\tArestas Selecionadas");
                    System.out.println("----------------------------------------");
                    grafo.exibeArestasSelecionadasPrim();
                    System.out.println("----------------------------------------");
                    System.out.println("Custo = "+grafo.somaCustoPrim());
                    break;
                }
            }
            
        }while(menu != 0);
    }
    
}
