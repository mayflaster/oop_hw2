package homework2;

import static org.junit.Assert.*;

import java.util.Set;

import org.junit.Test;

/**
 * This class contains a set of test cases that can be used to test the graph
 * and shortest path finding algorithm implementations of homework assignment
 * #2.
 */
public class GraphTests extends ScriptFileTests {

	// black-box test are inherited from super
	public GraphTests(java.nio.file.Path testFile) {
		super(testFile);
	}




	@Test
	public void emptyGraph(){
		Graph<WeightedNode> g = new Graph<>();
		assertNotNull(g);
		assertTrue(g.getNodes().size()==0);
	}




	@Test
	public void addNullNode(){
		Graph<WeightedNode> g = new Graph<>();
		assertTrue(g.addNode(null)==Graph.result.NULL_ARG);
	}




	@Test
	public void addNodeCheck(){
		Graph<WeightedNode> g = new Graph<>();
		WeightedNode n0 = new WeightedNode("n0",-1);
		g.addNode(n0);
		assertTrue(g.getNodes().size()==1);
		assertTrue(g.getNodes().contains(n0));
		assertFalse(g.getNodes().contains(new WeightedNode("n1",-1)));
		assertFalse(g.getNodes().contains(new WeightedNode("n0",1)));
		n0 = new WeightedNode("n1",1);
		assertTrue(g.addNode(n0)==Graph.result.SUCSSESS);
		assertTrue(g.getNodes().size()==2);
	}




	@Test
	public void addTheSameNode(){
		Graph<WeightedNode> g = new Graph<>();
		assertTrue(g.addNode(null)==Graph.result.NULL_ARG);
		g.addNode(new WeightedNode("n0", 0));
		assertTrue(g.addNode(new WeightedNode("n0", 0))==Graph.result.NODE_ALREADY_EXIST);
	}




	@Test
	public void addEdgeNoSrc(){
		Graph<WeightedNode> g = new Graph<>();
		WeightedNode n0 = new WeightedNode("dst",100);
		WeightedNode n1 = new WeightedNode("src",101);
		g.addNode(n0);
		assertTrue(g.addEdge(n1,n0)==Graph.result.NODE_FAIL);
	}




	@Test
	public void addEdgeNoDst(){
		Graph<WeightedNode> g = new Graph<>();
		WeightedNode n0 = new WeightedNode("dst",100);
		WeightedNode n1 = new WeightedNode("src",101);
		g.addNode(n1);
		assertTrue(g.addEdge(n1,n0)==Graph.result.NODE_FAIL);
	}




	@Test
	public void addEdgeCheck(){
		Graph<WeightedNode> g = new Graph<>();
		WeightedNode n0 = new WeightedNode("dst",100);
		WeightedNode n1 = new WeightedNode("src",101);
		g.addNode(n0);
		g.addNode(n1);
		assertTrue(g.getChildren(n1).size()==0);
		assertTrue(g.addEdge(n1,n0)==Graph.result.SUCSSESS);
		assertTrue(g.getChildren(n1).size()==1);
		assertTrue(g.getChildren(n1).contains(n0));
	}





	@Test
	public void addTheSameEdge(){
		Graph<WeightedNode> g = new Graph<>();
		WeightedNode n0 = new WeightedNode("dst",100);
		WeightedNode n1 = new WeightedNode("src",101);
		g.addNode(n0);
		g.addNode(n1);
		g.addEdge(n1,n0);
		assertTrue(g.addEdge(n1,n0)==Graph.result.EDGE_ALREADY_EXIST);
		assertTrue(g.getChildren(n1).size()==1);

	}







	@Test
	public void fullGraph(){
		Graph<WeightedNode> g = new Graph<>();
		WeightedNode a = new WeightedNode("a",-10);
		WeightedNode b = new WeightedNode("b",0);
		WeightedNode c = new WeightedNode("c",10);
		g.addNode(a);
		g.addNode(b);
		g.addNode(c);
		int counter=0;
		for(WeightedNode node1 : g.getNodes()){
			for(WeightedNode node2 : g.getNodes()){
				if( g.addEdge(node1, node2)==Graph.result.SUCSSESS ) ++counter;
			}
		}
		assertTrue(counter==9);

		counter=0;
		for(WeightedNode node1 : g.getNodes()){
			for(WeightedNode node2 : g.getNodes()){
				if( g.addEdge(node1, node2)==Graph.result.SUCSSESS ) ++counter;
			}
		}
		assertTrue(counter==0);
	}



	@Test
	public void getNodesEmpty(){
		Graph<WeightedNode> g = new Graph<>();
		assertNotNull(g.getNodes());
		assertTrue(g.getNodes().size()==0);
	}



	@Test
	public void getNodesCheck(){
		Graph<WeightedNode> g = new Graph<>();
		WeightedNode a = new WeightedNode("a",-10);
		WeightedNode b = new WeightedNode("b",0);
		WeightedNode c = new WeightedNode("c",10);
		g.addNode(a);
		g.addNode(b);
		g.addNode(c);
		Set<WeightedNode> s = g.getNodes();
		assertTrue(s.size()==3);

		assertTrue(s.contains(a));
		assertTrue(s.contains(b));
		assertTrue(s.contains(c));
	}



	@Test
	public void getChildrenEmpty(){
		Graph<WeightedNode> g = new Graph<>();
		WeightedNode a = new WeightedNode("a",-10);
		g.addNode(a);
		assertNotNull(g.getChildren(a));
		assertTrue(g.getChildren(a).size()==0);
	}



	@Test
	public void getChildrenCheck(){
		Graph<WeightedNode> g = new Graph<>();
		WeightedNode[] arr = new WeightedNode[4];
		for(int i=0 ; i<4 ; ++i){
			arr[i] = new WeightedNode(Integer.toString(i),i);
			g.addNode(arr[i]);
			g.addEdge(arr[0], arr[i]);
		}
		assertTrue(g.getChildren(arr[0]).size()==4);
		Set<WeightedNode> s = g.getChildren(arr[0]);
		for(int i=0 ; i<4 ; ++i){
			assertTrue(s.contains(arr[i]));
		}
	}

//=======================================================================


}
