package Grafos;
import java.util.LinkedList;


public class Vertex {
	
	//designacao do vertice
	String name = "";
	//lista de arestas
	LinkedList<Edge> adj = new LinkedList<Edge>();
	
	
	//construtores
	//vertice com arestas
	public Vertex (String nome, LinkedList<Edge> aresta)
	{
		this.name = nome;
		this.adj = aresta;	
	}
	
	//vertice vazio
	public Vertex(String name)
	{
		this.name = name;
		this.adj = null;
	}
	
	
	//Getters
	public String getName()
	{
		return this.name;
	}
	
	public LinkedList<Edge> getEdges()
	{
		return this.adj;
	}
	
	public boolean searchEdge(Edge e)
	{
		for (int i=0; i!=this.adj.size()-1;i++)
		{
			if (this.adj.get(i).equals(e))
			{
				return true;
			}
		}
		return false;
	}
	
	
	//Setters
	public void setName(String nome)
	{
		this.name=nome;
	}
	
	public void setEdges(LinkedList<Edge> aresta)
	{
		this.adj=aresta;
	}
	
	
	//Edit
	public void addEdge(Edge e1)
	{
		this.adj.add(e1);
	}
	
	public void rmvEdge(Edge e2)
	{
		this.adj.remove(e2);
	}
	

	//Override
	public String toString()
	{
		return adj.toString();
	}
	
	public boolean equals(Object obj)
	{
		Vertex V = (Vertex) obj;
		if (this.name == V.name)
		{
			return true;
		}
		return false;
	}
}
