
public class Test {
	public static void main (String [] args) {
		Hand h = new Hand();
		
		Card c1 = new Card(6,0);
		Card c2 = new Card(7,0);
		Card c3 = new Card(8,0);
		Card c4 = new Card(9,0);
		Card c5 = new Card(10,0);
		
		h.addCard(c1);
		h.addCard(c2);
		h.addCard(c3);
		h.addCard(c4);
		h.addCard(c5);
		
		System.out.println(h.hasFlush());
		
		
	}

}

