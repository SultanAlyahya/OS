
public class PQueue {

	private int size;

	private PQueueNode head;


	
	public PQueue() {

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

			PQueueNode tmp = new PQueueNode(e, pty);

			if((size == 0) || (pty < head.prioritySize)) {

			tmp.next = head;

			head = tmp;
			}
			
			else {

				PQueueNode p = head;

				
				PQueueNode q = null;

			while((p != null) && (pty >= p.prioritySize)) {

			q = p;

			p = p.next;

			}

			tmp.next = p;

			q.next = tmp;
			}

			size++;

			}
		public PQueueNode serve() {
			PQueueNode node = head;
				head = head.next;
				size--;
				return node;
			}
		public void print() {
			PQueueNode tmp = head;
			while(tmp.next != null) {
				System.out.println(tmp.data.getId()+" "+tmp.data.getProgramSize()+" "+tmp.data.getExpectedExecutionTime()+" "+tmp.data.getState());
				tmp = tmp.next;
			}
		}
}
