
public class PQueueNodeRAM {
	public Process data;

	public double priorityTime;

	public PQueueNodeRAM next;

	

	public PQueueNodeRAM() {

	next = null;

	}

	
	
	public PQueueNodeRAM(Process e, double p) {

	data = e;

	priorityTime = p;

	}
}
