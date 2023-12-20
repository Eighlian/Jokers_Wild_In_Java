package ch14;

import java.util.ArrayList;

public class Cards extends Poker1 {
	public static boolean[] deck = new boolean[54] ;	//global variable arrays ‘deck’
								
	public static void dealHand() {							//Draw method generates array of integers between 0 and 51 until 
		int cards;																	//it finds an available card to return
		boolean available = false;										//Loop switch
		
		for(int i=0;i<5;i++){ 											//Loop 5 times 
			if (card[i] == false) {
				
			
			while(!available) {											//Loop until card found
				cards = (int) (Math.floor(Math.random() * 54)); 		//Generate random number between 0-51 (a 52 slot array)
				
				if (deck[cards] == false){ 								//Check array if card available 
					deck[cards] = true;
					
						pokerHand[i] = cards;						 	//the array slots filled by cards
						
						if (cards == 53 || cards == 54) {
							cardRank.add(20);
						}
						else if (cards % 13 == 0) {
							cardRank.add(0); //Ace
						}else if (cards % 13 == 1) {
							cardRank.add(2);
						}else if (cards % 13 == 2) {
							cardRank.add(3);
						}else if (cards % 13 == 3) {
							cardRank.add(4);
						}else if (cards % 13 == 4) {
							cardRank.add(5);
						}else if (cards % 13 == 5) {
							cardRank.add(6);
						}else if (cards % 13 == 6) {
							cardRank.add(7);
						}else if (cards % 13 == 7) {
							cardRank.add(8);
						}else if (cards % 13 == 8) {
							cardRank.add(9);
						}else if (cards % 13 == 9) {
							cardRank.add(10);
						}else if (cards % 13 == 10) {
							cardRank.add(11); //Jack
						}else if (cards % 13 == 11) {
							cardRank.add(12); //Queen
						}else {
							cardRank.add(13); //King
						}
						
						if (cards == 53 || cards == 54) {
							cardSuit.add(20);
						}
						else if (cards / 13 == 0) {
							cardSuit.add(0); //Spade
						}else if (cards / 13 == 1) {
							cardSuit.add(1); //Heart
						}else if (cards / 13 == 2) {
							cardSuit.add(2); //Diamond
						}else {
							cardSuit.add(3); //Club
						}
						
						available = true;							//jumps out of loops
					
				}
			}
			available = false;										//re-enter loop
		}
			
		}
	}
	
}
	
