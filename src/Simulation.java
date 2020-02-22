import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class Simulation {
	
	static public boolean terminatesNormally() {
		double terminate = Math.random()*100;
		if(terminate <= 5) {
			return true;
		}
		return false;
	}
	
	static public boolean terminatesAbnormally() {
		double terminate = Math.random()*100;
		if(terminate <= 1) {
			return true;
		}
		return false;
	}
	
	static public boolean interrupts(PQueueNodeRAM process) {
		double interrupts = Math.random()*100;
		if(interrupts <= 10) {
			process.data.setState("ready");
			return true;
			
		}
		return false;
	}
	
	static public boolean request(PQueueNodeRAM process) {
		double request = Math.random()*100;
		if(request <= 20) {
			process.data.setState("waiting");
			return true;
		}
		return false;
	}
	public void changeState(PQueueNodeRAM process) {
		process.data.setState("ready");
	}
	
	static public boolean IOTerminate(PQueueNodeRAM p) {
		double IOTerminate = Math.random()*100;
		if(IOTerminate <= 20) {
			p.data.setExpectedExecutionTime(p.data.fixedExecutionTime);
			p.data.setInterrupts(0);
			p.data.setRequest(0);
			return true;
		}
		return false;
	}
	
	public static void main(String[] args) throws ClassNotFoundException, IOException {
		double normally=0;
		double abnormally=0;
		double averageSize=0;
		int CPUBound=0;
		int IOTerminate =0;
		Operations o = new Operations();
		PQueue queueJobs=o.JobSchedular("yazeed");
		int queueSize = queueJobs.length();
		PQueueMainMemory pq = o.CPUSchedular(queueJobs);
		int size=0;
		
		while(pq.length() > 0) {
			
			PQueueNodeRAM newPro = pq.serve();
			if(newPro.data.swapOut > 0) {
				newPro.data.swapIn++;
			}
			
			if(pq.RAMSize <= 163840  && queueJobs.length() > 0) {
				PQueueNode pro= queueJobs.serve();
				double p= pq.RAMSize + pro.data.getProgramSize();
				if(p <= 163840) {
					if(pro.data.getExpectedExecutionTime() < newPro.data.getExpectedExecutionTime()) {
						pro.data.swapIn++;
						newPro.data.swapOut++;
						System.out.println("the process with ID: "+newPro.data.id+" with expexted time: "+newPro.data.getExpectedExecutionTime()+" swap to the process with the ID: "+pro.data.id+" with expected time: "+pro.data.getExpectedExecutionTime());
						pq.enqueue(newPro.data, newPro.data.getExpectedExecutionTime());
						newPro.data=pro.data;
						newPro.priorityTime=pro.data.getExpectedExecutionTime();
					}else {
						pq.enqueue(pro.data, pro.data.getExpectedExecutionTime());
					}
				}else {	
					queueJobs.enqueue(pro.data, pro.data.getProgramSize());
					
				}
			}
			
			pq.RAMSize -= newPro.data.getProgramSize();
			newPro.data.setState("running");
			averageSize+=newPro.data.getProgramSize();
			while(true) {
				if(interrupts(newPro)) {
					newPro.data.setInterrupts(newPro.data.getInterrupts()+1);
					newPro.data.setState("running");
				}
				if(request(newPro)) {
					newPro.data.setInterrupts(newPro.data.getInterrupts()+1);
					newPro.data.setRequest(newPro.data.getRequest()+1);
					newPro.data.setState("running");
				}
				if(terminatesNormally() || newPro.data.getExpectedExecutionTime() == 0.0) {
					normally++;
					newPro.data.setState("terminate");
					//System.out.println("the Process with ID: "+newPro.data.id+" has been terminates normally");
					break;
				}
				if(terminatesAbnormally()) {
					abnormally++;
					newPro.data.setState("terminate");
					//System.out.println("the Process with ID: "+newPro.data.id+" has been terminates abnormally");
					break;
				}
				if(IOTerminate(newPro)) {
					IOTerminate++;
					//System.out.println("the Process with ID: "+newPro.data.id+" has been had an IOTerminate");
				}
				 newPro.data.setExpectedExecutionTime(newPro.data.getExpectedExecutionTime()-1);
			
			}
			
			if(newPro.data.getRequest() == 0) {
				CPUBound++;
			}
			
			System.out.println("the process with the ID: "+newPro.data.id+" have been swaped in "+newPro.data.swapIn+" and swaped out "+newPro.data.swapOut);
			
			
			
			
			size++;
//			System.out.println("the Process with ID: "+newPro.data.id+" has been interripted "+newPro.data.getInterrupts()+" times");
//			System.out.println("the Process with ID: "+newPro.data.id+" has been requested "+newPro.data.getRequest()+" times");
//			System.out.println("********");
		}
		System.out.println(size);
		System.out.println("=============================The Final Results=============================================");
		
		System.out.println("The number of jobs that have completed their execution normally: "+normally);
		System.out.println("The number of jobs that have completed their execution abnormally: "+abnormally);
		System.out.println("The number of initially generated jobs stored on the H-disk: "+queueSize);
		System.out.println("The average program size of all jobs: "+averageSize/queueSize);
		System.out.println("The average number of jobs that have completed their execution normally: "+normally/queueSize);
		System.out.println("The average number of jobs that have completed their execution abnormally: "+abnormally/queueSize);
		System.out.println("The number of CPU bound jobs: "+CPUBound);
//		PQueueMainMemory pqm = o.CPUSchedular("yazeed");
//		pqm.print();
		
		
			FileWriter fw = new FileWriter("cc");

			PrintWriter pw = new PrintWriter(fw);
			pw.println("The number of initially generated jobs stored on the H-disk: "+queueSize);
			pw.println("The average program size of all jobs: "+averageSize/queueSize);
			pw.println("The average number of jobs that have completed their execution normally: "+normally/queueSize);
			pw.println("The average number of jobs that have completed their execution abnormally: "+abnormally/queueSize);
			pw.println("The number of CPU bound jobs: "+CPUBound);
			pw.close();
		
	}
}
