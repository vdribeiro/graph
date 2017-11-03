package Grafos;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedList;

import junit.framework.*;


public class MiniSpanningTreeTest extends TestCase{
	
	//metodo de teste aos constructores
	//constructores dos vertices e das arestas
	public void testCreate_Vertex_Edge()
	{
		// Vertices unidos por arestas
		Vertex v0 = new Vertex("A");
		Vertex v1 = new Vertex("B");
		Edge e0 = new Edge(v0,v1,2);
		
		// Arestas unidas por vertices
		Edge e1 = new Edge(3);
		Edge e2 = new Edge(6);
		LinkedList<Edge> aux = new LinkedList<Edge>();
		aux.add(e1);
		aux.add(e2);
		Vertex v2 = new Vertex("C", aux);
		
		// Teste
		assertEquals("A", v0.getName());
		assertEquals(new Vertex[] {v0, v1}, e0.getVertex());
		assertEquals(aux, v2.getEdges());
	}
	
	//constructor do grafo
	public void testCreatGraph()
	{
		// Arestas
		Edge e0 = new Edge(1);
		Edge e1 = new Edge(5);
		Edge e2 = new Edge(3);
		Edge e3 = new Edge(6);
		
		// Vertices
		Vertex v0 = new Vertex("A");
		Vertex v1 = new Vertex("B");
		Vertex v2 = new Vertex("C");
		Vertex v3 = new Vertex("D");
		Vertex v4 = new Vertex("E");
	
		//Ligacoes
		v0.addEdge(e0);
		v0.addEdge(e1);
		v1.addEdge(e0);
		v2.addEdge(e1);
		v2.addEdge(e2);
		v2.addEdge(e3);
		v3.addEdge(e2);
		v4.addEdge(e3);
		e0.setOD(v0, v1);
		e1.setOD(v0, v2);
		e2.setOD(v2, v3);
		e3.setOD(v2, v4);
		
		// Grafo
		ArrayList<Vertex> vxSet = new ArrayList<Vertex>();
		vxSet.add(v0);
		vxSet.add(v1);
		vxSet.add(v2);
		vxSet.add(v3);
		vxSet.add(v4);
		Graph g1 = new Graph("Grafo_Teste", vxSet);
		
		// Teste
		assertEquals(vxSet, g1.getVertex());
		assertEquals(v0, g1.getVertex().get(0));
		assertEquals(e0, g1.getVertex().get(0).getEdges().get(0));
	}
	
	//para o algoritmo de Prim temos um grafo cujos vertices guardam as arestas
	//para o algoritmo de Kruskal as arestas apontam para os vertices
	//o facto de ter um grafo com vertices a apontar para arestas e arestas a
	//apontar para vertices facilita o processamento dos algorimtos
	
	//teste ao algoritmo de Prim
	public void testAlgPrim()
	{
		// Vertices
		Vertex v0 = new Vertex("A");
		Vertex v1 = new Vertex("B");
		Vertex v2 = new Vertex("C");
		Vertex v3 = new Vertex("D");
		Vertex v4 = new Vertex("E");
		
		// Arestas
		Edge e0 = new Edge(2);
		Edge e1 = new Edge(4);
		Edge e2 = new Edge(1);
		Edge e3 = new Edge(3);
		Edge e4 = new Edge(10);
		Edge e5 = new Edge(2);
		Edge e6 = new Edge(7);
		
		// Grafo final
		v0.addEdge(e0);
		v0.addEdge(e2);	
		v1.addEdge(e0);	
		v2.addEdge(e5);	
		v3.addEdge(e5);
		v3.addEdge(e2);
		v3.addEdge(e6);	
		v4.addEdge(e6);
		
		e0.setOD(v0, v1);	
		e2.setOD(v0, v3);	
		e5.setOD(v2, v3);
		e6.setOD(v3, v4);
		
		Graph g2 = new Graph("Grafo_Final");
		g2.addVertex(v0);
		g2.addVertex(v1);
		g2.addVertex(v2);
		g2.addVertex(v3);
		g2.addVertex(v4);
		
		// Grafo inicial
		v0.addEdge(e1);
		v1.addEdge(e3);
		v1.addEdge(e4);
		v2.addEdge(e1);
		v3.addEdge(e3);
		v4.addEdge(e4);
		
		e1.setOD(v0, v2);
		e3.setOD(v1, v3);
		e4.setOD(v1, v4);	
		
		Graph g1 = new Graph("Grafo_Inicial");
		g1.addVertex(v0);
		g1.addVertex(v1);
		g1.addVertex(v2);
		g1.addVertex(v3);
		g1.addVertex(v4);	
		
		//Teste
		MiniSpanningTree mst = new MiniSpanningTree(g1);
		assertEquals(mst.Prim(),g2);
	}
	
	//teste ao algoritmo de Kruskal
	public void testAlgKruskal()
	{
		// Vertices
		Vertex v0 = new Vertex("A");
		Vertex v1 = new Vertex("B");
		Vertex v2 = new Vertex("C");
		Vertex v3 = new Vertex("D");
		Vertex v4 = new Vertex("E");
		
		// Arestas
		Edge e0 = new Edge(2);
		Edge e1 = new Edge(4);
		Edge e2 = new Edge(1);
		Edge e3 = new Edge(3);
		Edge e4 = new Edge(10);
		Edge e5 = new Edge(2);
		Edge e6 = new Edge(7);
		
		// Grafo final
		v0.addEdge(e0);
		v0.addEdge(e2);	
		v1.addEdge(e0);	
		v2.addEdge(e5);	
		v3.addEdge(e5);
		v3.addEdge(e2);
		v3.addEdge(e6);	
		v4.addEdge(e6);
		
		e0.setOD(v0, v1);	
		e2.setOD(v0, v3);	
		e5.setOD(v2, v3);
		e6.setOD(v3, v4);
		
		Graph g2 = new Graph("Grafo_Final");
		g2.addVertex(v0);
		g2.addVertex(v1);
		g2.addVertex(v2);
		g2.addVertex(v3);
		g2.addVertex(v4);
		
		// Grafo inicial
		v0.addEdge(e1);
		v1.addEdge(e3);
		v1.addEdge(e4);
		v2.addEdge(e1);
		v3.addEdge(e3);
		v4.addEdge(e4);
		
		e1.setOD(v0, v2);
		e3.setOD(v1, v3);
		e4.setOD(v1, v4);	
		
		Graph g1 = new Graph("Grafo_Inicial");
		g1.addVertex(v0);
		g1.addVertex(v1);
		g1.addVertex(v2);
		g1.addVertex(v3);
		g1.addVertex(v4);	
		
		//Teste
		MiniSpanningTree mst = new MiniSpanningTree(g1);
		assertEquals(mst.Kruskal(),g2);
	}
	
	//teste ao Desempenho
	//retorna um vector com 2 tempos: o tempo de Prim e o de Kruskal respectivamente 	
	public long[] testPerformance()
	{
		// Vertices
		Vertex v0 = new Vertex("A");
		Vertex v1 = new Vertex("B");
		Vertex v2 = new Vertex("C");
		Vertex v3 = new Vertex("D");
		Vertex v4 = new Vertex("E");
		
		// Arestas
		Edge e0 = new Edge(2);
		Edge e1 = new Edge(4);
		Edge e2 = new Edge(1);
		Edge e3 = new Edge(3);
		Edge e4 = new Edge(10);
		Edge e5 = new Edge(2);
		Edge e6 = new Edge(7);
		
		// Grafo final
		v0.addEdge(e0);
		v0.addEdge(e2);	
		v1.addEdge(e0);	
		v2.addEdge(e5);	
		v3.addEdge(e5);
		v3.addEdge(e2);
		v3.addEdge(e6);	
		v4.addEdge(e6);
		
		e0.setOD(v0, v1);	
		e2.setOD(v0, v3);	
		e5.setOD(v2, v3);
		e6.setOD(v3, v4);
		
		Graph g2 = new Graph("Grafo_Final");
		g2.addVertex(v0);
		g2.addVertex(v1);
		g2.addVertex(v2);
		g2.addVertex(v3);
		g2.addVertex(v4);
		
		// Grafo inicial
		v0.addEdge(e1);
		v1.addEdge(e3);
		v1.addEdge(e4);
		v2.addEdge(e1);
		v3.addEdge(e3);
		v4.addEdge(e4);
		
		e1.setOD(v0, v2);
		e3.setOD(v1, v3);
		e4.setOD(v1, v4);	
		
		Graph g1 = new Graph("Grafo_Inicial");
		g1.addVertex(v0);
		g1.addVertex(v1);
		g1.addVertex(v2);
		g1.addVertex(v3);
		g1.addVertex(v4);	
		
		//Teste
		MiniSpanningTree mst = new MiniSpanningTree(g1);
		Date d1 = new Date();
		
		long tempo1=d1.getTime();
		assertEquals(mst.Prim(),g2);
		long tempo2=d1.getTime();
		
		long tempo3=d1.getTime();
		assertEquals(mst.Kruskal(),g2);
		long tempo4=d1.getTime();
		
		long [] time = new long [] {tempo2-tempo1, tempo4-tempo3};
		
		return time;
		
	}
	

}
