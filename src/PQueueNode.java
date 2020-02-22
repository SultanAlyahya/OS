
public class PQueueNode {

	
	

	public Process data;

	public double prioritySize;

	public PQueueNode next;

	

	public PQueueNode() {

	next = null;

	}

	
	
	public PQueueNode(Process e, double p) {

	data = e;

	prioritySize = p;

	}
}
