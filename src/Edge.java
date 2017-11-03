package Grafos;

public class Edge {

	//no destino e origem
	Vertex dest = null;
	Vertex orig = null;
	//custo da arresta
	int weight = 0;
	
	
	//construtores
	//aresta com vertices
	public Edge(Vertex orig, Vertex dest, int weight) {
		this.orig = orig;
		this.dest = dest;
		this.weight = weight;
	}
	
	//aresta vazia
	public Edge (int weight)
	{
		this.weight = weight;
		this.dest = null;
		this.orig = null;	
	}
	
	
	//Getters
	public Vertex[] getVertex()
	{
		Vertex [] vertices = new Vertex [] {this.orig,this.dest};
		return vertices ;
	}
	
	public Vertex getOrig()
	{
		return this.orig;
	}
	
	public Vertex getDest()
	{
		return this.dest;
	}
	
	public double getWeigth()
	{
		return this.weight;
	}
	
	
	//Setters
	public void setOrig(Vertex orig)
	{
		this.orig = orig;
	}
	
	public void setDest(Vertex dest)
	{
		this.dest = dest;
	}
	
	public void setOD(Vertex orig, Vertex dest)
	{
		this.orig = orig;
		this.dest = dest;
	}
	
	
	//Override
	public String toString()
	{
		return orig.getName() + ","+ dest.getName() + "," + weight ;
	}

	public boolean equals(Object obj)
	{
		Edge E = (Edge) obj;
		if (((this.orig.getName().equals(E.orig.getName())) ||
			(this.orig.getName().equals(E.dest.getName()))) &&
			((this.dest.getName().equals(E.orig.getName())) || 
			(this.dest.getName().equals(E.dest.getName()))))
		{
			return true;
		}
		return false;
	}
}

