package Grafos;
import java.util.ArrayList;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.util.LinkedList;
import java.util.Random;
import java.util.Scanner;
import java.io.File;

public class Graph {
	
	//um grafo é constituido por um conjunto de vértices
	ArrayList<Vertex> vertexSet = new ArrayList<Vertex>();
	String name= ""; 
	
	
	//construtores
	//gera um grafo aleatório com n vertices
	public Graph(String nome, int numVertex)
	{
		//o numero maximo de arestas num grafo pode ser dado pela seguinte formula
		int max = (int) (numVertex*(numVertex-1)/2);
		//para um grafo com n vertices ser conexo necessita de pelo menos n-1 arestas
		int rnd = (int) (numVertex-1 + (Math.random() * max));
		this.name=nome;
		int r1=0;
		int r2=0;
		for (int i=1; i!=numVertex; i++)
		{
			Vertex vx = new Vertex ("V" + i);
			this.vertexSet.add(vx);	
		}
		
		for (int j=1; j!=rnd;j++)
		{
			Edge e = new Edge((int)(1 + (Math.random() * numVertex)));
			
			//testar se o vertice ja tem esta aresta
			do
			{
				r1 = (int) (0 + (Math.random() * numVertex-1));
				do
					r2 = (int) (0 + (Math.random() * numVertex-1));
				while (r1==r2);
				e.setOD(this.vertexSet.get(r1), this.vertexSet.get(r2));
			}while ((this.vertexSet.get(r1).getEdges().contains(e)) ||
					(this.vertexSet.get(r2).getEdges().contains(e)));
			
			this.vertexSet.get(r1).addEdge(e);
			this.vertexSet.get(r2).addEdge(e);
		}
	}
	
	public Graph(String nome, int numVertex)
	{
		ArrayList<Vertex> vxSet = new ArrayList<Vertex>();
		vxSet = GenarateGraph(numVertex);
		this.name=nome;
		this.vertexSet = vxSet;
		
	}
	
	//gera um grafo aleatório com n vertices
	private ArrayList<Vertex> GenarateGraph(int numVertex) {
		
		
		//numero máximo de arestas q o grafo pode ter
		int max = (numVertex*(numVertex-1)/2);
		//grafo a ser Gerado e preenchido com N vértices
		
		ArrayList<Vertex> verticesGerados = new ArrayList<Vertex>(numVertex);
		ArrayList<Edge> EdgesGerados = new ArrayList<Edge>();
		
		Scanner entry = new Scanner(System.in);
		String Str_option = "none";
		int option = -1;
		int complexityGraph = 2;

		while(option == -1)
		{
			System.out.println("Escolha a complexidade do grafo:\n");
			System.out.println("[1]-Simples");
			System.out.println("[2]-Médio");
			System.out.println("[3]-Complexo");
			System.out.print("\n\nComplexidade:");
			
			Str_option =entry.next();
			
			if(Str_option.equals("1"))
				option = 1;
			else if(Str_option.equals("2"))
				option = 2;
			else if(Str_option.equals("3"))
				option = 3;
			if(option == -1)
				System.out.println("Invalid Option.\n");
		}
		
		
		
	
	
		
		//complexidade de um grafo, numero máximo de arestas q um vértice pode ter
		if(option == 1)
		{	
			complexityGraph = numVertex;
		}
		else if(option == 2)
		{
			int rnd = (int) (numVertex + (Math.random() * max-1));
			complexityGraph = rnd;
		}
		else if(option == 3)
		{
			complexityGraph = max;
		}
		
		//processo de geração do grafo;
		
		//1º gerar os N vértices V0,V1,V2,....;
		
		for(int i = 0; i < numVertex;i++)
		{
		
			Vertex V = new Vertex("V"+i);
			verticesGerados.add(V);
		}
		
		for(Vertex V: verticesGerados)
		{
			//gerar um numero aleatório de 1 a 	complexityGraph para o numero de arestas de um vértice
			Random r = new Random();
			int NumArestas = Math.abs((r.nextInt()%numVertex-1)+1);
			
			
			//guarda o vértice de origem
			Vertex VOri = V;
			
			//ir buscar N(NumArestas) Vértices de Destino
			int i = 0;
			while(i <=NumArestas)
			{
				Random r1 = new Random();
				int indiceVDest = Math.abs((r1.nextInt()% verticesGerados.size()));
				int custoAresta = Math.abs((r1.nextInt()% 20))+1;
				if(indiceVDest < verticesGerados.size())
				{
					Vertex Vdest = verticesGerados.get(indiceVDest);
					Edge E = null;
					
					if(!V.getName().equals(Vdest.getName()))
						 E = new Edge(V,Vdest,custoAresta);
				
					if(E != null)
					{
						if(V.getEdges().isEmpty())
						{	
							V.addEdge(E);
							EdgesGerados.add(E);
							i++;
						}
					
						else if(!V.getEdges().isEmpty())
						{
							if(!SameEdge(E,EdgesGerados))
							{
								V.addEdge(E);
								EdgesGerados.add(E);
								i++;
							}
						}
					}
				}
			}
		
			
		}
		
		
		
		//2º para cada vértice,1...N, gerar arestas de modo
		//a garantir que no fim tenhamos um grafo conexo que
		//contenha pelo menos um ciclo
		
		return verticesGerados;
	}
	
	public boolean SameEdge(Edge E, ArrayList<Edge> Edges)
	{
		boolean EqualEdge = false;
		
		for(Edge E1: Edges)
		{
			if(E1.equals(E))
			{
				EqualEdge = true;
				break;
			}
		}
		
		return EqualEdge;
	}
	
	//cria um grafo vazio
	public Graph(String nome)
	{
		this.name=nome;
		this.vertexSet = null;
	}
	
	//gera um grafo com vertices
	//o grafo é lido a partir de um ficheiro em formato CVS
	public Graph(String nome, ArrayList<Vertex> vxSet)
	{
		this.name=nome;
		this.vertexSet = vxSet;
	}
	
	
	//Getters
	public String getName()
	{
		return this.name;
	}
	
	public ArrayList<Vertex> getVertex()
	{
		return this.vertexSet;
	}
	
	//Retorna lista ordenada por custo das arestas existentes no grafo
	public LinkedList<Edge> getEdges()
	{
		LinkedList<Edge> edlis = new LinkedList<Edge>();
		Edge e = null;
		Vertex v = null;
		int k=0;
		//procura arestas nos vertices
		for (int i=0; i!=this.vertexSet.size()-1; i++)
		{
			v = (Vertex) this.vertexSet.get(i);
			for (int j=0; j!=v.getEdges().size()-1; j++)
			{
				e = (Edge) v.getEdges().get(j);
				k=0;
				//se nao existir acrescenta
				while (k<edlis.size() && (!edlis.get(k).equals(e)))
				{
					k++;
				}
				if (k==edlis.size())
				{
					edlis.add(e);
				}
			}
		}
		
		//ordenacao
        for (int m=0; m!=edlis.size()-1;m++)
        {
        	for (int n=m+1; n!=edlis.size();n++)
        	{
        		if (edlis.get(m).getWeigth()>edlis.get(n).getWeigth())
        		{
        			e = edlis.get(n);
        			edlis.set(n, edlis.get(m));
        			edlis.set(m, e);
        	    }
        	}
        }
        return edlis;
	}
	
	
	//Setters
	public void setName(String name)
	{
		this.name = name;
	}
	
	public void setVertex(ArrayList<Vertex> vxSet)
	{
		this.vertexSet=vxSet;
	}
	
	
	//Edit
	public void addVertex(Vertex vx)
	{
		this.vertexSet.add(vx);
	}
	
	public void rmvVertex(Vertex vx)
	{
		this.vertexSet.remove(vx);
	}
	
	
	//Override
	public String toString()
	{
		return vertexSet.toString();
	}
	
	
	//Outros
	//procura um vertice no grafo
	public boolean searchVertex(Vertex v)
	{
		for (int i=0; i!=this.vertexSet.size()-1;i++)
		{
			if (this.vertexSet.get(i).equals(v))
			{
				return true;
			}
		}
		return false;
	}
	
	public void WriteGraph(String FileName)
	{
		PrintStream stream = null;
		PrintStream console = System.out;
		File file;
		
		file = new File(FileName+".txt");
		try {
			stream = new PrintStream(new FileOutputStream(file));
		}
		catch (FileNotFoundException e)
		{
			e.printStackTrace();
			return;
		}
		System.setOut(stream);
		
		
		//guarda a informção do grafo;
		for(Vertex V: vertexSet)
			for(Edge E : V.getEdges())
			{
				stream.println(E.toString());
			}
		
		System.setOut(console);
		stream.close();
		
	}
	
}
