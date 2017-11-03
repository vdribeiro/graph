package Grafos;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.LinkedList;
import java.util.Scanner;

public class MiniSpanningTree {
	
	//o grafo inicial
	Graph myGraph = null;
	//a arvore final
	Graph myTree = null;
	
	
	//construtor
	public MiniSpanningTree(Graph initGraph)
	{
		this.myGraph = initGraph;
		this.myTree = null;
	}
	
	//Prim
	public Graph Prim()
	{
		//a completar...
		return myTree;
	}
	
	//Kruskal
	public Graph Kruskal()
	{
		LinkedList<Edge> edlis = new LinkedList<Edge>();
		boolean existeorig=false;
		boolean existedest=false;
		Vertex vo = null;
		Vertex vd = null;
		Edge e = null;
		//lista de arestas ordenada pelo custo
		edlis=this.myGraph.getEdges();
		for (int k = 0; k!=edlis.size()-1; k++)
		{
			//testa se o vertice origem e destino ja existem em myTree
			e = (Edge) edlis.get(k);
			vo = (Vertex) e.getOrig();
			vd = (Vertex) e.getDest();
			existeorig=this.myTree.searchVertex(vo);
			existedest=this.myTree.searchVertex(vd);
			//se nenhum existir os vertices sao limpos, e adicionado
			//a aresta aos vertices e os vertices a arvore
			//se um deles ja existir mas o outro nao, e adicionado o
			//que nao existe pela mesma logica. Se nenhuma das
			//condicoes for verificada avanca para a proxima aresta
			if (!existeorig && !existedest)
			{
				vo.adj.clear();
				vd.adj.clear();
				vo.adj.add(e);
				vd.adj.add(e);
				this.myTree.vertexSet.add(vo);
				this.myTree.vertexSet.add(vd);
			}else
			{
				if (existeorig && !existedest)
				{
					vd.adj.clear();
					vo.adj.add(e);
					vd.adj.add(e);
					this.myTree.vertexSet.add(vd);
				}else
				{
					if (!existeorig && existedest)
					{
						vo.adj.clear();
						vo.adj.add(e);
						vd.adj.add(e);
						this.myTree.vertexSet.add(vo);
					}
				}
			}
		}
		return myTree;
	}
	
	//Performance
	public long Performance(Graph g1, Graph g2)
	{
		return 0;
	}
	
	public String toString()
	{
		return myGraph.toString();
	}
	
	public void SaveMyGraph()
	{
		myGraph.WriteGraph("Grafo1");
	}
	
	public void SaveMytree ()
	{
		myTree.WriteGraph("Tree");
	}
	
	
	public static void main(String[] args) throws IOException
	{
		
		Scanner fileIn = null;
		String FileName = "Grafo.txt";
		
		//guarda o grafo inicail
		Graph MyGraph = null;
		
		//guarda o grafo inicial e a arvore respectiva a construir
		 MiniSpanningTree MyTree =null;
		 
		
		//guarda o grafo a ler
		ArrayList<Vertex> Vertices = new ArrayList<Vertex>();
		
		//Menu de iteração com o ulizador
		
		
		Scanner entry = new Scanner(System.in);
		String Str_option = "none";
		int option = -1;
		
		while(option == -1)
		{
			System.out.println("Escolha o acção a efectuar:\n");
			System.out.println("[1]-Gerar Minimum Spanning Tree a partir de um Novo Grafo aleatório");
			System.out.println("[2]-Gerar Minimum Spanning Tree a partir de um Grafo  definido  num ficheiro");
			System.out.println("[0]-Sair");
			System.out.print("\n\nOption: ");
			
			Str_option = entry.next();
			if(Str_option.equals("1"))
				option = 1;
			else if(Str_option.equals("2"))
				option = 2;
			else if(Str_option.equals("0"))
				option = 0;
				
			if(option == -1)
				System.out.println("Invalid Option.\n");
		}
		//fecha o scanner
		
		if(option == 1)
		{
				String GraphName = "";
				int NumVertices = 10; //por omissão gráfico terá 10 vértices
				
				System.out.print("\n\nNome do Grafo:");
				GraphName = entry.next();
				System.out.print("Numero de Vertices do Grafo:");
				NumVertices = entry.nextInt();
			
				MyGraph = new Graph(GraphName,NumVertices);
				
				MyTree = new MiniSpanningTree(MyGraph);
				
				
				
				
				MyTree.SaveMyGraph();
				
				entry.close();
				
			
		}
		else if(option == 2)
		{
		
			//ler Grafo do ficheiro Grafo.txt
			try
			{
				fileIn = new Scanner(new BufferedReader( new FileReader(FileName)));
			
			
				Vertex V = null;
				Edge E = null;
			
				//estamos a falar do mesmo vértice do grafo
				String MyActualVertex = null;
				boolean isNewVertex = false;
			
				while(fileIn.hasNext())
				{
				   //Linha do ficheiro Grafo.txt
					String LineFile = fileIn.next();
					
					//guarda informação sobre um nó
					String OrigVertexName = null;
					String DestVertexName = null;
					int    CustoEdge = -1;
					//controles da informação a guardar
					int beginIndex = 0;
					int flag = 0;
				
				
					//ciclo sobre a linha, determinar o vértice do grafo
					for(int i = 0; i < LineFile.length();i++)
					{
						
						if(LineFile.charAt(i) == ',' && flag == 0)
						{
							OrigVertexName = LineFile.substring(beginIndex,i);
							beginIndex = i+1;
							flag++;
							
							if(!OrigVertexName.equals(MyActualVertex))
								isNewVertex = true;
							else if(OrigVertexName.equals(MyActualVertex))
								isNewVertex = false;
						}
						else if(LineFile.charAt(i) == ',' && flag == 1)
						{
							DestVertexName = LineFile.substring(beginIndex,i);
							CustoEdge = Integer.parseInt(LineFile.substring(i+1));
							beginIndex = 0;
							flag ++;
						}
					}
					
					//Temos um novo Vértice
					if(flag ==2 && isNewVertex)
					{
					
						//actualiza o vértice Actual com o novo vertice
						MyActualVertex = OrigVertexName;
						
						
						if(Vertices.size() >= 0)
						{
							//guarda o vértice anterior
							if(V != null)
							{
								 Vertices.add(V);
							}
							
							
							//cria um novo Vértice
							Vertex V1 = new Vertex(OrigVertexName);
							
							//cria o vértice de destino
							
							Vertex Vdest = new Vertex(DestVertexName);
							
							//cria e adcciona a primeira Aresta do vértice
							E = new Edge(V1,Vdest,CustoEdge);
							V1.addEdge(E);
							
						
							//actualiza o vértice Actual
							V = V1;
						}
						
					}
					//caso seja o mesmo Vértice
					else if(flag ==2 && !isNewVertex)
					{
						//cria o vértice de destino
						
						Vertex Vdest = new Vertex(DestVertexName);
						
						//cria e adcciona a primeira Aresta do vértice
						E = new Edge(V,Vdest,CustoEdge);
						
						V.addEdge(E);
						
					}
				}
				//adccionar o ultimo vértice e cria o grafo
			
				if(!Vertices.contains(V))
				 Vertices.add(V);
			
		
			
			}finally{
			
				if(fileIn != null)fileIn.close();
			
				MyGraph = new Graph(FileName,Vertices);
			
				//cria a arvore com o grapho inicial
				
				

				/* Algoritmos
				*
				*
				*
				*
				*
				*
				*
				*
				*
				*
				*
				*
				*
				*	System.out.println(MyTree.toString());
				*
				*
				*
				*
				*
				*
				*
				*
				*
				*
				*
				*
				*
				*
				*
				*
				*
				*
				
				
				*/
			
				MyTree = new MiniSpanningTree(MyGraph);
			
			}
			
			//guarda o grafo;
			MyTree.SaveMyGraph();
		}
		else if(option == 0)
		{
			System.out.println("No action to do");
		}
		
	}
	
	
}

