
/**
 * An object of type Hand represents a hand of cards.  The
 * cards belong to the class Card.  A hand is empty when it
 * is created, and any number of cards can be added to it.
 */

import java.util.ArrayList;

public class Hand {

   private Card[] hand;   // The cards in the hand.
   private int count; 
   
   /**
    * Create a hand that is initially empty.
    */
   public Hand() {
      hand = new Card[5];
	  count = 0;
   }
   
   /**
    * Remove all cards from the hand, leaving it empty.
    */
   public void clear() {
      for(int i=0 ; i<hand.length; i++){ hand[i] = null; }
	  count = 0;
   }
   
   /**
    * Add a card to the hand.  It is added at the end of the current hand.
    * @param c the non-null card to be added.
    * @throws NullPointerException if the parameter c is null.
    */
   public void addCard(Card c) {
      
	  for(int i=0 ; i<hand.length; i++){ 
		if (hand[i] == null){
			hand[i] = c;
			count = count + 1;
			break;
		}
	 }

	  
   }
   
   /**
    * Remove a card from the hand, if present.
    * @param c the card to be removed.  If c is null or if the card is not in 
    * the hand, then nothing is done.
    */
   public void removeCard(Card c) {

	   for(int i=0 ; i<hand.length; i++){ 
	     if (hand[i]!=null && hand[i].equals(c)){
	       hand[i] = null;
	       count = count-1;
	     }
	   }

	    }
   /**
    * Remove the card in a specified position from the hand.
    * @param position the position of the card that is to be removed, where
    * positions are starting from zero.
    * @throws IllegalArgumentException if the position does not exist in
    * the hand, that is if the position is less than 0 or greater than
    * or equal to the number of cards in the hand.
    */
   public void removeCard(int position) {
	      if (position < 0 || position >= hand.length)
	         throw new IllegalArgumentException("Position does not exist in hand: "
	               + position);
	      hand[position] = null;
	      count --;
	   }	
   
   /**
    * Returns the number of cards in the hand.
    */
   public int getCardCount() {
      return count;
   }
   
   /**
    * Gets the card in a specified position in the hand.  (Note that this card
    * is not removed from the hand!)
    * @param position the position of the card that is to be returned
    * @throws IllegalArgumentException if position does not exist in the hand
    */
   public Card getCard(int position) {
      if (position < 0 || position >= hand.length)
         throw new IllegalArgumentException("Position does not exist in hand: "
               + position);
       return hand[position];
   }
   
   /**
    * Sorts the cards in the hand so that cards of the same suit are
    * grouped together, and within a suit the cards are sorted by value.
    * Note that aces are considered to have the lowest value, 1.
    */
   public void sortBySuit() {
	  int size = count;
	  int nonnull = 0;
	  int index = 0;
	  
      Card[] newHand = new Card[5];
      while (size > 0) {
		 if (hand[nonnull] == null) { nonnull = nonnull+1; continue;}
         int pos = nonnull;  // Position of minimal card.
         Card c = hand[nonnull];  // Minimal card.
		 
         for (int i = nonnull+1; i < hand.length; i++) {
            Card c1 = hand[i];
			if (c1 != null){
				if ( c1.getSuit() < c.getSuit() ||
						(c1.getSuit() == c.getSuit() && c1.getValue() < c.getValue()) ) {
					pos = i;
					c = c1;
				}
			}
         }
         hand[pos] = null;
		 size = size - 1;
         newHand[index++] = c;
		 nonnull = 0;
      }
      hand = newHand;
   }
   
   /**
    * Sorts the cards in the hand so that cards of the same value are
    * grouped together.  Cards with the same value are sorted by suit.
    * Note that aces are considered to have the lowest value, 1.
    */
   public void sortByValue() {
	  int size = count;
	  int nonnull = 0;
	  int index = 0;
	  
      Card[] newHand = new Card[5];
      while (size > 0) {
		 if (hand[nonnull] == null) { nonnull = nonnull+1; continue;}
         int pos = nonnull;  // Position of minimal card.
         Card c = hand[nonnull];  // Minimal card.
		 
         for (int i = nonnull+1; i < hand.length; i++) {
            
			Card c1 = hand[i];
            if (c1 != null){
				if ( c1.getValue() < c.getValue() ||
						(c1.getValue() == c.getValue() && c1.getSuit() < c.getSuit()) ) {
					pos = i;
					c = c1;
				}
			}
         }
         hand[pos] = null;
		 size = size - 1;
         newHand[index++] = c;
		 nonnull = 0;
      }
      hand = newHand;
   }
   
   public void printHand(){
	   
	   for(int i=0; i<hand.length; i++){
		   if (hand[i] != null){
			   System.out.println(hand[i]);
		   }
	   }
	   System.out.println();
   }
   

   /******************************** Implement your methods here ****************************************/


//Returns the number of pairs this hand contains
public int numPairs() {
	this.sortByValue();
	int numOfPairs=0;
	
	for (int i =0; i < hand.length-1; i ++) {
		if (hand[i].getValue() == hand[i+1].getValue()) {
			numOfPairs ++;
		} if ((i-1) != -1 && hand[i].getValue()== hand[i+1].getValue() && hand[i].getValue() == hand[i-1].getValue()) {
			numOfPairs -=1;
		}
			
	}
	return numOfPairs; 
}

//Return true if this hand has 3 card that are the same value
public boolean hasTriplet() {
	this.sortByValue();

	

	for (int i=0; i<hand.length-2; i++) {
		if (hand[i].getValue()==hand[i+1].getValue() && hand[i].getValue()==hand[i+2].getValue()) {
			return true;
		}
	}
	return false;
}

//Return true if this hand has all cards what are of the same suit
public boolean hasFlush() {
	this.sortBySuit();


	for (int i=0; i<hand.length;i++){
		if (hand[0].getSuit()==hand[1].getSuit() && hand[1].getSuit()==hand[2].getSuit() && 
			hand[2].getSuit()==hand[3].getSuit() && hand[3].getSuit()== hand[4].getSuit()){
			return true;
		}
	}
	return false;
}

//Returns true is this hand has 5 consecutive cards of any suit
public boolean hasStraight() {
	this.sortByValue();
	
	if (hand[0].getValue()==1 && hand[1].getValue()==10 && hand[2].getValue()==11 && hand[3].getValue()==12 && hand[4].getValue()==13) {
		return true;
	}
	for (int i=0; i< this.hand.length-1; i++) {
		if (hand[i+1].getValue() == hand[i].getValue()+1)
			return true;
		
	}
	
	
	return false;
}


//Return true if this hand has a triplet and a pair of different values
public boolean hasFullHouse() {
	this.sortByValue();
	
	if (hand[0].getValue()==hand[2].getValue() && hand[3].getValue()==hand[4].getValue()) {
		return true;
	}

	if (hand[0].getValue()==hand[1].getValue() && hand[2].getValue()==hand[4].getValue()){
		return true;
	}
 return false;
}

//Return true is this hand has 4 cards that are of the same value
public boolean hasFourOfAKind(){
	this.sortByValue();

	if ((hand[0].getValue()==hand[1].getValue()) && (hand[0].getValue()==hand[2].getValue()) && 
		(hand[0].getValue()==hand[3].getValue())) {
		return true;
	}

	if ((hand[1].getValue()==hand[2].getValue()) && (hand[1].getValue()==hand[3].getValue()) && 
		(hand[1].getValue()==hand[4].getValue())) {
		return true;
	}

	return false;
}

//Returns the card with the highest value in the hand
public Card highestValue() {
	this.sortByValue();
	if (hand[0].getValue()==1) {
		return hand[0];
	}
	else {
	return hand[4]; 
		}
	}
//Returns the highest valued card of any pair or triplet found
public Card highestDuplicate() {
	this.sortByValue();
	Card max = null;
	if(this.numPairs()==0) {
		return null;
	}
	if (hand[0].getValue()  == 1 && hand[1].getValue()==1) {
		max= hand[0];
	} else if (hand[4].getValue()==hand[3].getValue()) {
			 max=hand[4];
		} else if (hand[3].getValue()==hand[2].getValue()) {
			max= hand[3];
		}else if  (hand[2].getValue()==hand[1].getValue()) {
			max= hand[2];
		} else if (hand[1].getValue()==hand[0].getValue()) {
			max= hand[0];
		}
			
	return max;
	
	
}

// Returns -1 if the instance hand loses to the the parameter hand, 0 if the hands are equal and +1 is the instance hand wins 
public int compareTo(Hand h) {
	if (this.hasFourOfAKind() && h.hasFourOfAKind()) {
		if (this.highestDuplicate().getValue()==1  || this.highestDuplicate().getValue() > h.highestDuplicate().getValue() ) {
			return 1;
		}else if (h.highestDuplicate().getValue()==1 || this.highestDuplicate().getValue() < h.highestDuplicate().getValue() ) {
			return -1;
		} else if (this.highestDuplicate().getValue() == h.highestDuplicate().getValue()) {
			return 0;
		}
	} else if (this.hasFourOfAKind()) {
		return 1;
		
	} else if (h.hasFourOfAKind()) {
		return -1;
	}
	
	else {
		if (this.hasFullHouse() && h.hasFullHouse()) {
			if(this.highestDuplicate().getValue()==1  ||this.highestDuplicate().getValue() > h.highestDuplicate().getValue()) {
				return 1;
			} else if (h.highestDuplicate().getValue()==1  || this.highestDuplicate().getValue() < h.highestDuplicate().getValue())
				return -1;
		} else if (this.hasFullHouse()) {
			return 1; 
		} else if (h.hasFullHouse()) {
			return -1;
		}
		else { 
			if (this.hasFlush() && h.hasFlush()) {
				if (this.highestValue().getValue()==1  || this.highestValue().getValue() > h.highestValue().getValue() ) {
					return 1; 
				} else if (h.highestValue().getValue()==1 || this.highestValue().getValue() < h.highestValue().getValue()) {
					return -1;
				} else if (this.highestValue().getValue()==1 && h.highestValue().getValue()==1) {
					return 0;
				}
			} else if (this.hasFlush()) {
				return 1;
			} else if (h.hasFlush()) {
				return -1;
			}
			else {
				if (this.hasStraight() && h.hasStraight()) {
					if (this.hand[0].getValue()==1 && h.hand[0].getValue() !=1) {
						return 1;
					} if (this.hand[0].getValue() !=1 && h.hand[0].getValue()==1) {
						return -1;
					}
					  else if (this.hand[4].getValue() > h.hand[4].getValue()) {
						return 1;
					} else if (this.hand[4].getValue() < h.hand[4].getValue()) {
						return -1;
					} else if (this.hand[4].getValue() == h.hand[4].getValue()) {
						return 0;
					}
				} else if (this.hasStraight()) {
					return 1;
				} else if (h.hasStraight()) {
					return -1;
				}
				else {
					if (this.hasTriplet() && h.hasTriplet()) {
						if (this.highestValue().getValue()==1 || this.highestDuplicate().getValue() > h.highestDuplicate().getValue()) {
							return 1;
						} else if (h.highestValue().getValue()==1 || this.highestDuplicate().getValue() < h.highestDuplicate().getValue()) {
							return -1;
						} else if (this.highestDuplicate().getValue() == h.highestDuplicate().getValue()) {
							return 0; 
						}
					} else if (this.hasTriplet()) {
						return 1;
					} else if (h.hasTriplet()) {
						return -1;
					}
					else {
						if (this.numPairs()==2 && h.numPairs()==2) {
							if (this.highestDuplicate().getValue() > h.highestDuplicate().getValue()) {
								return 1;
							} if (this.highestDuplicate().getValue() < h.highestDuplicate().getValue()) {
								return -1;
							} if (this.highestDuplicate().getValue() == 1 && h.highestDuplicate().getValue() !=1) {
								return 1;
							} if (h.highestDuplicate().getValue() ==1 && this.highestDuplicate().getValue() !=1) {
								return -1;
							} if (this.highestDuplicate().getValue() == 1 && h.highestDuplicate().getValue() ==1) {
								return 0;
							}
						if (this.numPairs()==1 && h.numPairs()==1) {
							if (this.highestDuplicate().getValue() > h.highestDuplicate().getValue()) {
								return 1;
							} if (this.highestDuplicate().getValue() < h.highestDuplicate().getValue()) {
								return -1;
							} if (this.highestDuplicate().getValue() == 1 && h.highestDuplicate().getValue() !=1) {
								return 1;
							} if (h.highestDuplicate().getValue() ==1 && this.highestDuplicate().getValue() !=1) {
								return -1;
							} if (this.highestDuplicate().getValue() == 1 && h.highestDuplicate().getValue() ==1) {
								if (this.highestValue().getValue() > h.highestValue().getValue()) {
									return 1;
								} else if (this.highestValue().getValue() < h.highestValue().getValue()) {
									return -1;
								}
							}
							
						}
						} else if (this.numPairs() > h.numPairs()) {
							return 1;
						} else if (h.numPairs() > this.numPairs() ) {
							return -1;
						}
							else {
							if (this.highestValue()==h.highestValue()) {
								return 0;
							} else if (this.highestValue().getValue() > h.highestValue().getValue())  {
								return 1;
							} else if (this.highestValue().getValue() < h.highestValue().getValue()) {
								return -1;
							}
						}
					}
				}
			}
		}
	 }	
	return 0;
  }
}


	




	







