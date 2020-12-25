package homework2;

import org.w3c.dom.Node;

import java.io.*;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.Collections;

/**
 * This class implements a testing driver which reads test scripts
 * from files for testing Graph and PathFinder.
 */
public class TestDriver {

	// String -> Graph: maps the names of graphs to the actual graph
	// TODO: Parameterize the next line correctly.
  	private final Map<String,Graph<WeightedNode>> graphs = new HashMap<>();
  	// String -> WeightedNode: maps the names of nodes to the actual node
  	private final Map<String,WeightedNode> nodes = new HashMap<>();
	private final BufferedReader input;
  	private final PrintWriter output;


  	/**
  	 * Creates a new TestDriver.
     * @requires r != null && w != null
     * @effects Creates a new TestDriver which reads command from
     * <tt>r</tt> and writes results to <tt>w</tt>.
     */
  	public TestDriver(Reader r, Writer w) {
    	input = new BufferedReader(r);
    	output = new PrintWriter(w);
  	}


  	/**
  	 * Executes the commands read from the input and writes results to the
  	 * output.
     * @effects Executes the commands read from the input and writes
     * 		    results to the output.
     * @throws IOException - if the input or output sources encounter an
     * 		   IOException.
     */
  	public void runTests() throws IOException {

    	String inputLine;
		while ((inputLine = input.readLine()) != null) {
			// echo blank and comment lines
      		if (inputLine.trim().length() == 0 ||
      		    inputLine.charAt(0) == '#') {
        		output.println(inputLine);
        		continue;
      		}

      		// separate the input line on white space
      		StringTokenizer st = new StringTokenizer(inputLine);
      		if (st.hasMoreTokens()) {
        		String command = st.nextToken();

        		List<String> arguments = new ArrayList<>();
        		while (st.hasMoreTokens())
          			arguments.add(st.nextToken());

        		executeCommand(command, arguments);
      		}
    	}

    	output.flush();
  	}


  	private void executeCommand(String command, List<String> arguments) {

    	try {
      		if (command.equals("CreateGraph")) {
        		createGraph(arguments);
      		} else if (command.equals("CreateNode")) {
        		createNode(arguments);
      		} else if (command.equals("AddNode")) {
        		addNode(arguments);
      		} else if (command.equals("AddEdge")) {
        		addEdge(arguments);
      		} else if (command.equals("ListNodes")) {
        		listNodes(arguments);
      		} else if (command.equals("ListChildren")) {
        		listChildren(arguments);
      		} else if (command.equals("FindPath")) {
        		findPath(arguments);
      		} else {
        		output.println("Unrecognized command: " + command);
      		}
    	} catch (Exception e) {
      		output.println("Exception: " + e.toString());
    	}
  	}


	private void createGraph(List<String> arguments) {

    	if (arguments.size() != 1)
      		throw new CommandException(
				"Bad arguments to CreateGraph: " + arguments);

    	String graphName = arguments.get(0);
    	createGraph(graphName);
  	}


  	private void createGraph(String graphName) {
  		

		Graph<WeightedNode> graph = new Graph<WeightedNode>();
  		if(graphs.putIfAbsent(graphName, graph) != null){
  			output.println("graph " + graphName + " already exist");
		}
  		else {
			output.println("created graph " + graphName);
		}


		//TODO:  graph already exist
		// graphName empty/null

  	}
 
  	
  	private void createNode(List<String> arguments) {

    	if (arguments.size() != 2)
      		throw new CommandException(
				"Bad arguments to createNode: " + arguments);

    	String nodeName = arguments.get(0);
    	String cost = arguments.get(1);
    	createNode(nodeName, cost);
  	}


 	private void createNode(String nodeName, String cost) {

  		WeightedNode node = new WeightedNode(nodeName,Integer.parseInt(cost));
		if (nodes.putIfAbsent(nodeName,node) != null){
			output.println("node " + nodeName + " already exist");
		}
		else {
			output.println("created node " + nodeName + " with cost " + cost );
		}

 		// TODO:
		// cost is not int or negative?
		// node exist in the map?

  	}
	

  	private void addNode(List<String> arguments) {

    	if (arguments.size() != 2)
      		throw new CommandException(
				"Bad arguments to addNode: " + arguments);

    	String graphName = arguments.get(0);
    	String nodeName = arguments.get(1);
    	addNode(graphName, nodeName);
  	}


  	private void addNode(String graphName, String nodeName) {


  		Graph<WeightedNode> g = graphs.get(graphName);
		WeightedNode node = nodes.get(nodeName);
		if ( g == null ) {
			output.println("graph "+ graphName +" does not exist" );
			return;
		}
		if ( node == null ) {
			output.println("node " + nodeName + " does not exist" );
			return;
		}
		if (!g.addNode(node)){
			output.println("failed to add node " + nodeName + " to " + graphName);
			return;
		}
		output.println("added node " + nodeName + " to " + graphName);

  		// TODO: errors: graph/node not exist
		// cant add node to the graph

  		
  	}


  	private void addEdge(List<String> arguments) {

    	if (arguments.size() != 3)
      		throw new CommandException(
				"Bad arguments to addEdge: " + arguments);

    	String graphName = arguments.get(0);
    	String parentName = arguments.get(1);
    	String childName = arguments.get(2);
    	addEdge(graphName, parentName, childName);
  	}


	private void addEdge(String graphName, String parentName, String childName) {



		Graph<WeightedNode> g = graphs.get(graphName);
		WeightedNode parentNode = nodes.get(parentName);
		WeightedNode childNode  = nodes.get(childName);
		if(!g.addEdge(parentNode,childNode)) {
			output.println("failed to add edge from "+ parentName + " to " + childName + " in " + graphName);
			return;
		}
		output.println("added edge from "+ parentName + " to " + childName + " in " + graphName);

		// TODO: errors: graph/node not exist in maps,
		// one of the nodes does not exist

  	}


  	private void listNodes(List<String> arguments) {

    	if (arguments.size() != 1)
      		throw new CommandException(
				"Bad arguments to listNodes: " + arguments);

    	String graphName = arguments.get(0);
    	listNodes(graphName);
  	}

	private List<String> toStringList (Set <WeightedNode> nodes){
  		List<String> sortedList = new ArrayList<>();
  		for(WeightedNode n : nodes){
				sortedList.add(n.getName());
  		}
  		Collections.sort(sortedList);
  		return sortedList;
	}

  	private void listNodes(String graphName) {

		Graph<WeightedNode> g = graphs.get(graphName);
		if (g==null){
			output.println("graph"+ graphName +"does not exist" );
			return;
		}
		Set<WeightedNode> gNodes = g.getNodes();

		List<String> sortedList =toStringList(gNodes);

		StringBuilder s = new StringBuilder(graphName + " contains:");
		for (String name: sortedList) {
			s.append(" ");
			s.append(name);
		}
		output.println(s);

  		
  		// TODO: g not exist ?


  	}


  	private void listChildren(List<String> arguments) {

    	if (arguments.size() != 2)
      		throw new CommandException(
				"Bad arguments to listChildren: " + arguments);

    	String graphName = arguments.get(0);
    	String parentName = arguments.get(1);
    	listChildren(graphName, parentName);
  	}


  	private void listChildren(String graphName, String parentName) {
		Graph<WeightedNode> g = graphs.get(graphName);
		if (g==null){
			output.println("graph "+ graphName +" does not exist" );
			return;
		}
		WeightedNode parentNode = nodes.get(parentName);
		if (parentNode==null){
			output.println("node "+ parentName +" does not exist" );
			return;
		}
		Set<WeightedNode> parentChildren= g.getChildren(parentNode);
		if (parentChildren==null){
			output.println("failed to get " +parentName+ "list of children in " + graphName +". node does not exist. " );
		}
		List<String> sortedList =toStringList(parentChildren);

		StringBuilder s = new StringBuilder( "the children of " + parentName + " in " + graphName + " are:" );
		for (String name: sortedList) {
			s.append(" ");
			s.append(name);
		}
		output.println(s);
		// TODO: graph/node not exist
		// node not exist on graph
  		
  	}


  	private void findPath(List<String> arguments) {

    	String graphName;
    	List<String> sourceArgs = new ArrayList<>();
    	List<String> destArgs = new ArrayList<>();

    	if (arguments.size() < 1)
      		throw new CommandException(
				"Bad arguments to FindPath: " + arguments);

    	Iterator<String> iter = arguments.iterator();
    	graphName = iter.next();

		// extract source arguments
    	while (iter.hasNext()) {
      		String s = iter.next();
      		if (s.equals("->"))
        		break;
      		sourceArgs.add(s);
    	}

		// extract destination arguments
    	while (iter.hasNext())
      		destArgs.add(iter.next());

    	if (sourceArgs.size() < 1)
      		throw new CommandException(
				"Too few source args for FindPath");

    	if (destArgs.size() < 1)
      		throw new CommandException(
				"Too few dest args for FindPath");

    	findPath(graphName, sourceArgs, destArgs);
  	}


  	private void findPath(String graphName, List<String> sourceArgs,
  						  List<String> destArgs) {
  		

		Graph<WeightedNode> g = graphs.get(graphName);
		if (g==null){
			output.println("graph "+ graphName +" does not exist" );
			return;
		}
		Set<WeightedNode> sourceNodes = new HashSet<>();
		for (String s: sourceArgs) {
			if(nodes.get(s) == null){
				output.println("node "+ s +" does not exist" );
				return;
			}
			sourceNodes.add(nodes.get(s));
		}
		Set<WeightedNode> destNodes = new HashSet<>();
		for (String t: destArgs) {
			if(nodes.get(t) == null){
				output.println("node "+ t +" does not exist" );
				return;
			}
			destNodes.add(nodes.get(t));
		}
		Set<WeightedNode> path = g.findShortestPath(sourceNodes,destNodes);; // probably will be list and not set!
		StringBuilder s = new StringBuilder( "shortest path in " + graphName + ":");
		for (WeightedNode n : path){
			s.append(" ");
			s.append(n.getName());
		}
		output.println(s);


		// TODO: graph/source/dest not exist here
		// sources/dests not exist in the graph
		// some problem in path..
		
  	}


	private static void printUsage() {
		System.err.println("Usage:");
		System.err.println("to read from a file: java TestDriver <name of input script>");
		System.err.println("to read from standard input: java TestDriver");
	}


	public static void main(String args[]) {

		try {
			// check for correct number of arguments
			if (args.length > 1) {
				printUsage();
				return;
			}

			TestDriver td;
			if (args.length == 0)
				// no arguments - read from standard input
				td = new TestDriver(new InputStreamReader(System.in),
								    new OutputStreamWriter(System.out));
			else {
				// one argument - read from file
				java.nio.file.Path testsFile = Paths.get(args[0]);
				if (Files.exists(testsFile) && Files.isReadable(testsFile)) {
					td = new TestDriver(
							Files.newBufferedReader(testsFile, Charset.forName("US-ASCII")),
							new OutputStreamWriter(System.out));
				} else {
					System.err.println("Cannot read from " + testsFile.toString());
					printUsage();
					return;
				}
			}

			td.runTests();

		} catch (IOException e) {
			System.err.println(e.toString());
			e.printStackTrace(System.err);
		}
	}
}


/**
 * This exception results when the input file cannot be parsed properly.
 */
class CommandException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public CommandException() {
		super();
  	}

  	public CommandException(String s) {
		super(s);
  	}
}