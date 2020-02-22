
public class PQueueMainMemory {
	

	private int size;
	public double RAMSize=0;
	private PQueueNodeRAM head;


	
	public PQueueMainMemory() {

	head = null;

	size = 0;
	}
	public int length (){

		return size;

		}

	boolean full () {

		return false;

		}
		public void enqueue(Process e, double pty) {

			PQueueNodeRAM tmp = new PQueueNodeRAM(e, pty);

			if((size == 0) || (pty < head.priorityTime)) {

			tmp.next = head;

			head = tmp;
			}
			
			else {

				PQueueNodeRAM p = head;

				
				PQueueNodeRAM q = null;

			while((p != null) && (pty >= p.priorityTime)) {

			q = p;

			p = p.next;

			}

			tmp.next = p;

			q.next = tmp;
			}

			size++;

			}
		public PQueueNodeRAM serve() {
			PQueueNodeRAM node = head;
				head = head.next;
				size--;
				return node;
			}
		
}
