class Counter{
	volatile int c;
	private static Integer lock = new Integer(0);
	Counter(int c){
		this.c = c;
	}

	void even(){
		synchronized(lock){
			System.out.println(c);
			c++;
			try{
				lock.notify();
				lock.wait();
			}
			catch(InterruptedException ie){
			}
		}
	}

	void odd(){
		synchronized(lock){
			System.out.println(c);
			c++;
			try{
				lock.notify();
				lock.wait();
			}
			catch(InterruptedException ie){
			}
		}
	}
}

class Even implements Runnable{
	Counter cnt;

	Even(Counter c){
		cnt = c;
	}

	public void run(){
		while(true){
			cnt.even();
		}
	}
}

class Odd implements Runnable{
	Counter cnt;
	Odd(Counter c){
		cnt = c;
	}

	public void run(){
		while(true){
			cnt.odd();
		}
	}
}

public class EO{
	public static void main(String[] args){
		Counter c = new Counter(1);
		new Thread(new Odd(c)).start();
		new Thread(new Even(c)).start();
	}
}
