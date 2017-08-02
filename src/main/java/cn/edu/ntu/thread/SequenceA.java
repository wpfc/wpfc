package cn.edu.ntu.thread;

public class SequenceA implements Sequence {

	private static int count = 0;
	
	@Override
	public int getNumber() {
		count ++;
		return count;
	}

	public static void main(String[] args){
		SequenceA a = new SequenceA();
		
		ClientThread c1 = new ClientThread(a);
		ClientThread c2 = new ClientThread(a);
		ClientThread c3 = new ClientThread(a);
		
		c1.start();
		c2.start();
		c3.start();
	}
}
