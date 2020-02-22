import java.io.FileReader;
import java.io.*;
import java.io.FileWriter;
import java.io.IOException;
import java.io.EOFException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;


public class Process {
	
		int id;
		int programSize;
		private	double expectedExecutionTime;
		double fixedExecutionTime;
		private String state;
		private int interrupts;
		private int request;
		int swapIn=0;
		int swapOut=0;
		
		public Process(int Id,int ps, double eet, double fet, String st, int interrupts, int request) {
			this.id=Id;
			this.programSize = ps;
			this.expectedExecutionTime = eet;
			this.fixedExecutionTime = fet;
			this.state = st;
			this.interrupts=interrupts;
			this.request=request;
		}
		
		public void print() {
			System.out.println(this.id + " " + this.expectedExecutionTime +  " " + this.state);
		}
		public static void write(String fileName) throws IOException {
			FileWriter fw = new FileWriter(fileName);

			PrintWriter pw = new PrintWriter(fw);
			
			int i=0;
			int allSize=0;
			for(;allSize<=2097152;i++) {
				int size=(int)(Math.random()*((16384-16)+1))+16;
				System.out.println("size: "+size);
				double expectedExecutionTime=(Math.random()*((512-16)+1))+16;
				System.out.println("expectedExecutionTime: "+expectedExecutionTime);
				String state="new";
				System.out.println("state: "+state);
				allSize += size;
				if(allSize <= 2097152)
				pw.println(i+" "+size+" "+expectedExecutionTime+" "+expectedExecutionTime+" "+state);
			
				
			}
			System.out.println(allSize+" "+i);
			pw.close();
		}
		
		static void read(String fileName) throws IOException, ClassNotFoundException {
			File nf  = new File(fileName);
			Scanner read = new Scanner(nf);
			int i;
			while(read.hasNextLine()) {
				String line=read.nextLine();
				
				String[] lines=line.split(" ");
			int id =	Integer.parseInt(lines[0]);
			double eet = Double.parseDouble(lines[1]);
			double eet1 = Double.parseDouble(lines[2]);
			String state = lines[3];
				
			}
			
	
//			File f1 = new File(fileName);
//			FileInputStream fis = new FileInputStream(f1);
//			
//			ObjectInputStream obj = new ObjectInputStream(fis);
//			System.out.println(1);
//			
//			ArrayList<Process> yazeed = new ArrayList<Process>();
//			try {
//			while (obj.available() >= 0) {
//				
//				Process p=(Process) obj.readObject();
//				
//					yazeed.add(p);
//			}}
//				catch(EOFException e) {
//					
//					
//					
//				}
//			obj.close();
//			
				
			
			//for(int i = 0 ; i  < yazeed.size(); i++) {
				//yazeed.get(i).print();
		//	}
		
			
		}
		
		
	public int getId() {
			return id;
		}

		public void setId(int id) {
			this.id = id;
		}

		public int getProgramSize() {
			return programSize;
		}

		public void setProgramSize(int programSize) {
			this.programSize = programSize;
		}

		public double getExpectedExecutionTime() {
			return expectedExecutionTime;
		}

		public void setExpectedExecutionTime(double expectedExecutionTime) {
			this.expectedExecutionTime = expectedExecutionTime;
		}

		public double getFixedExecutionTime() {
			return fixedExecutionTime;
		}

		public void setFixedExecutionTime(double fixedExecutionTime) {
			this.fixedExecutionTime = fixedExecutionTime;
		}

		public String getState() {
			return state;
		}

		public void setState(String state) {
			this.state = state;
		}
		public int getInterrupts() {
			return interrupts;
		}

		public void setInterrupts(int interrupts) {
			this.interrupts = interrupts;
		}
		public int getRequest() {
			return request;
		}

		public void setRequest(int request) {
			this.request = request;
		}

	public static void main(String[] args) throws IOException, ClassNotFoundException {
		
		try {
		write("/Users/sa3d-10alajlan/Desktop/CSC227/yazeed");
		
		//read("/Users/sa3d-10alajlan/Desktop/CSC227/yazeed");
		} catch (Exception e) {
			
		}
	}
	}
	

