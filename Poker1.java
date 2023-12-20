package ch14;

import java.util.ArrayList;
import java.util.Collections;

import javafx.animation.Interpolator;
import javafx.animation.ParallelTransition;
import javafx.animation.PathTransition;
import javafx.animation.RotateTransition;
import javafx.animation.SequentialTransition;
import javafx.application.Application;
import javafx.beans.binding.Bindings;
import javafx.beans.value.ObservableBooleanValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.PerspectiveCamera;
import javafx.scene.Scene;
import javafx.scene.SceneAntialiasing;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.text.Text;
import javafx.scene.transform.Rotate;
import javafx.stage.Stage;
import javafx.util.Duration;

public class Poker1 extends Application {
	protected Text text = new Text();										//Text for radio button pane
	public static int[] pokerHand = new int[5];								//global array of integers for pokerHands
	public static boolean[] card = new boolean[5];							//global array of booleans for cards displaying
	public static ArrayList<Integer> cardRank = new ArrayList<Integer>();  	//ArrayList to track the ranks on board
	public static ArrayList<Integer> cardSuit = new ArrayList<Integer>();  	//ArrayList to track the suits on board
	public static int rank1, rank2, rank3, rank4, rank5;					//Integers to track ranks
	public static int suit1, suit2, suit3, suit4, suit5;					//Integers to track suits
	public static boolean dealt = false;									//Boolean switch to determine if betting can still take place
	public static boolean win = false;										//Boolean switch to declare winner	
	
	int intX, intY;															//Bet & Pocket Integers
	String x, y;															//Text of bet & pocket to convert to integers
	TextField pocketX,betY;													//Textfield labels
	
	ImageView imageView = new ImageView();									//imageView to display poker cards
	ImageView imageView2 = new ImageView();									//imageView to display poker cards
	ImageView imageView3 = new ImageView();									//imageView to display poker cards
	ImageView imageView4 = new ImageView();									//imageView to display poker cards
	ImageView imageView5 = new ImageView();									//imageView to display poker cards

	Image back = new Image("card\\b1fv.png");								//back of the card image
	Image c1, c2, c3, c4, c5;												//images to update the imageviews
	
	PathTransition pt = new PathTransition();								//path transition for imageviews
	PathTransition pt2 = new PathTransition();								//path transition for imageviews
	PathTransition pt3 = new PathTransition();								//path transition for imageviews
	PathTransition pt4 = new PathTransition();								//path transition for imageviews
	PathTransition pt5 = new PathTransition();								//path transition for imageviews
	
	int multiplier;
	
	//Method to create stage,pane, and scene
	public void start(Stage primaryStage) {							
		primaryStage.setTitle("Group Project -- Joker's Wild!!");
		primaryStage.setScene(createScene());
		primaryStage.show();
	}
	
	//method which creates the scene
	private Scene createScene() {
		Scene scene = new Scene(getPane(),750, 425, true, SceneAntialiasing.BALANCED);	//Scene				
		scene.setCamera(new PerspectiveCamera());
		
		return scene;
	}
	
	//method creating the pane which includes the HBox & VBox that make up the program
	protected BorderPane getPane() {
		BorderPane pane = new BorderPane();						//Pane
		
		HBox centerhBox = new HBox(10);							//hBox for cards
		centerhBox.setPadding(new Insets(150,100,100,100));
		centerhBox.setBackground(new Background(new BackgroundImage(new Image("card\\back.jpg"), null, null, null, null)));
		pane.setCenter(centerhBox);
		
		HBox bottomhBox = new HBox(38);							//hBox for deal,hold,draw buttons
		pane.setBottom(bottomhBox);
		
		VBox paneForRadioButtons = new VBox(30);						//VBox for Radio buttons
		paneForRadioButtons.setPadding(new Insets(30, 30, 30, 30));
		RadioButton one = new RadioButton("$1");						//$1 radio button
		RadioButton ten = new RadioButton("$10");						//$10 radio button
		RadioButton hundred = new RadioButton("$100");					//$100 radio button
		
		Text pocket = new Text(50,50, "Pocket: $");						//textfield lable for pocket
		x = "200";
		pocketX = new TextField(x);
		pocketX.setPrefWidth(80);
		pocketX.setEditable(false);
		pocketX.setAlignment(Pos.CENTER_RIGHT);
							
		Text bet = new Text(50,50, "Bet: $");							//textfield label for bet
		y = "0";
		betY = new TextField(y);
		betY.setPrefWidth(80);
		betY.setEditable(false);
		betY.setAlignment(Pos.CENTER_RIGHT);
		
		betY.setTextFormatter(new TextFormatter<>(c -> {
		    if (!c.getControlNewText().matches("\\d*")) 
		        return null;
		    else
		        return c;
		    }
		));
	
		intX = Integer.parseInt(x);
		intY = Integer.parseInt(y);
		
		paneForRadioButtons.getChildren().addAll(one,ten,hundred,pocket,pocketX,bet,betY);
		pane.setRight(paneForRadioButtons);
							
		//ToggleGroup group = new ToggleGroup();	
		//one.setToggleGroup(group);
		//ten.setToggleGroup(group);
		//hundred.setToggleGroup(group);
							
		one.setOnAction(e -> {
			if (one.isSelected() && dealt == false) {
				System.out.println("Bet: $"+1);
				intX = intX - 1;
				intY = intY + 1;
				String x = String.valueOf(intX);				//convert string to integer
				String y = String.valueOf(intY);				//convert string to integer
				pocketX.setText(x);
				System.out.println(x);
				betY.setText(y);
				System.out.println(y);
			}
		});
						
		ten.setOnAction(e -> {
			if (ten.isSelected() && dealt == false) {
				System.out.println("Bet: $"+10);
				intX = intX - 10;
				intY = intY + 10;
				String x = String.valueOf(intX);			//convert string to integer
				String y = String.valueOf(intY);			//convert string to integer
				pocketX.setText(x);
				betY.setText(y);
			}
		});
							
		hundred.setOnAction(e -> {			
			if (hundred.isSelected() && dealt == false) {
				System.out.println("Bet: $"+100);
				intX = intX - 100;
				intY = intY + 100;
				String x = String.valueOf(intX);			//convert string to integer
				String y = String.valueOf(intY);			//convert string to integer
				pocketX.setText(x);
				betY.setText(y);
			}
		});
		
			Button btDeal = new Button("Deal");						//Deal Button
			btDeal.setOnAction(new EventHandler<ActionEvent>() {
				public void handle(ActionEvent e) {
					System.out.println("Process Deal");
					
					imageView.setImage(null);
					imageView2.setImage(null);
					imageView3.setImage(null);
					imageView4.setImage(null);
					imageView5.setImage(null);
						
					cardRank.clear();
					cardSuit.clear();
					
					Cards.dealHand();
					for(int i=0;i<5;i++){ 
						card[i] = true;
					}
					
					rank1 = cardRank.get(0);
					suit1 = cardSuit.get(0);
					rank2 = cardRank.get(1);
					suit2 = cardSuit.get(1);
					rank3 = cardRank.get(2);
					suit3 = cardSuit.get(2);
					rank4 = cardRank.get(3);
					suit4 = cardSuit.get(3);
					rank5 = cardRank.get(4);
					suit5 = cardSuit.get(4);
					
					imageView = new ImageView(new Image("card\\"+ pokerHand[0] +".png"));							//imageView to display poker cards
					imageView2 = new ImageView(new Image("card\\"+ pokerHand[1] +".png"));							//imageView to display poker cards
					imageView3 = new ImageView(new Image("card\\"+ pokerHand[2] +".png"));							//imageView to display poker cards
					imageView4 = new ImageView(new Image("card\\"+ pokerHand[3] +".png"));							//imageView to display poker cards
					imageView5 = new ImageView(new Image("card\\"+ pokerHand[4] +".png"));							//imageView to display poker cards
					centerhBox.getChildren().addAll(imageView, imageView2, imageView3, imageView4, imageView5);
					
					pt = new PathTransition(Duration.millis(1500), new Line(25,-150,25,50), imageView);				//Path Transitions to display imageview animations
					pt2 = new PathTransition(Duration.millis(1500), new Line(25,-150,25,50), imageView2);			//Path Transitions to display imageview animations
					pt3 = new PathTransition(Duration.millis(1500), new Line(25,-150,25,50), imageView3);			//Path Transitions to display imageview animations
					pt4 = new PathTransition(Duration.millis(1500), new Line(25,-150,25,50), imageView4);			//Path Transitions to display imageview animations
					pt5 = new PathTransition(Duration.millis(1500), new Line(25,-150,25,50), imageView5);			//Path Transitions to display imageview animations

					pt.play();
					pt2.play();
					pt3.play();
					pt4.play();
					pt5.play();
					
					System.out.println(cardRank);
					System.out.println(cardSuit);
					dealt = true;									//Boolean trigger to prevent future betting
				}
			});
			 
			Button btHold1 = new Button("Discard");							//Discard Button1
			btHold1.setOnAction(new EventHandler<ActionEvent>() {
				public void handle(ActionEvent e) {
					System.out.println("Process Discard-1");
					card[0] = false;									//Allows need card to be drawn
					imageView.setImage(back);							//"flips" imageview of card to display back
					cardRank.remove((Integer)(rank1));					//removes card from rank array
					cardSuit.remove((Integer)(suit1));					//removes card from suit array
					
					System.out.println(cardRank);
					System.out.println(cardSuit);
				}
			});
			
			Button btHold2 = new Button("Discard");							//Discard Button2
			btHold2.setOnAction(new EventHandler<ActionEvent>() {
				public void handle(ActionEvent e) {
					System.out.println("Process Discard-2");
					card[1] = false;									//Allows need card to be drawn
					imageView2.setImage(back);							//"flips" imageview of card to display back
					cardRank.remove((Integer)(rank2));					//removes card from rank array
					cardSuit.remove((Integer)(suit2));					//removes card from suit array
					
					System.out.println(cardRank);
					System.out.println(cardSuit);
				}
			});
			
			Button btHold3 = new Button("Discard");							//Discard Button3
			btHold3.setOnAction(new EventHandler<ActionEvent>() {
				public void handle(ActionEvent e) {
					System.out.println("Process Discard-3");
					card[2] = false;									//Allows need card to be drawn
					imageView3.setImage(back);							//"flips" imageview of card to display back
					cardRank.remove((Integer)(rank3));					//removes card from rank array
					cardSuit.remove((Integer)(suit3));					//removes card from suit array
					
					System.out.println(cardRank);
					System.out.println(cardSuit);
				}
			});
			
			Button btHold4 = new Button("Discard");						//Discard Button4
			btHold4.setOnAction(new EventHandler<ActionEvent>() {
				public void handle(ActionEvent e) {
					System.out.println("Process Discard-4");
					card[3] = false;									//Allows need card to be drawn
					imageView4.setImage(back);							//"flips" imageview of card to display back
					cardRank.remove((Integer)(rank4));					//removes card from rank array
					cardSuit.remove((Integer)(suit4));					//removes card from suit array
					
					System.out.println(cardRank);
					System.out.println(cardSuit);
				}
			});
			
			Button btHold5 = new Button("Discard");						//Discard Button5
			btHold5.setOnAction(new EventHandler<ActionEvent>() {
				public void handle(ActionEvent e) {
					System.out.println("Process Discard-5");
					card[4] = false;									//Allows need card to be drawn
					imageView5.setImage(back);							//"flips" imageview of card to display back
					cardRank.remove((Integer)(rank5));					//removes card from rank array
					cardSuit.remove((Integer)(suit5));					//removes card from suit array
					
					System.out.println(cardRank);
					System.out.println(cardSuit);
				}
			});
			
			Button btDraw = new Button("Draw");							//Draw Button
			btDraw.setOnAction(new EventHandler<ActionEvent>() {
				public void handle(ActionEvent e) {
					System.out.println("Draw");
				
					Cards.dealHand();
					
					if (card[0] == false) {
						card[0] = true;
						pt = new PathTransition(Duration.millis(1500), new Line(25,50,25,350), imageView);
					    pt.setCycleCount(0);
					    pt.play();
					    pt.setOnFinished(event -> {
					    	c1 = new Image("card\\"+ pokerHand[0] +".png");
					    	imageView.setImage(c1);
					    	pt = new PathTransition(Duration.millis(1500), new Line(25,-150,25,50), imageView);
					    	pt.play();
					    	pt.setOnFinished(null);
					    });
					}
				    
					if (card[1] == false) {
						card[1] = true;
						pt2 = new PathTransition(Duration.millis(1500), new Line(25,50,25,350), imageView2);
					    pt2.setCycleCount(0);
					    pt2.play();
					    pt2.setOnFinished(event -> {
					    	c2= new Image("card\\"+ pokerHand[1] +".png");
					    	imageView2.setImage(c2);
					    	pt2 = new PathTransition(Duration.millis(1500), new Line(25,-150,25,50), imageView2);
					    	pt2.play();
					    	pt2.setOnFinished(null);
					    });
					}
					
				    if (card[2] == false) {
				    	card[2] = true;
						pt3 = new PathTransition(Duration.millis(1500), new Line(25,50,25,350), imageView3);
					    pt3.setCycleCount(0);
					    pt3.play();
					    pt3.setOnFinished(event -> {
					    	c3 = new Image("card\\"+ pokerHand[2] +".png");
					    	imageView3.setImage(c3);
					    	pt3 = new PathTransition(Duration.millis(1500), new Line(25,-150,25,50), imageView3);
					    	pt3.play();
					    	pt3.setOnFinished(null);
					    });
				    }
				    
				    if (card[3] == false) {
				    	card[3] = true;
						pt4 = new PathTransition(Duration.millis(1500), new Line(25,50,25,350), imageView4);
					    pt4.setCycleCount(0);
					    pt4.play();
					    pt4.setOnFinished(event -> {
					    	c4 = new Image("card\\"+ pokerHand[3] +".png");
					    	imageView4.setImage(c4);
					    	pt4 = new PathTransition(Duration.millis(1500), new Line(25,-150,25,50), imageView4);
					    	pt4.play();
					    	pt4.setOnFinished(null);
					    });
				    }
				    
				    if (card[4] == false) {
				    	card[4] = true;
						pt5 = new PathTransition(Duration.millis(1500), new Line(25,50,25,350), imageView5);
					    pt5.setCycleCount(0);
					    pt5.play();
					    pt5.setOnFinished(event -> {
					    	c5 = new Image("card\\"+ pokerHand[4] +".png");
					    	imageView5.setImage(c5);
					    	pt5 = new PathTransition(Duration.millis(1500), new Line(25,-150,25,50), imageView5);
					    	pt5.play();
					    	pt5.setOnFinished(null);
					    });
				    }   
				    
				    Collections.sort(cardRank);
				    Collections.sort(cardSuit);
				    
				    //System.out.println(cardRank);
					//System.out.println(cardSuit);
					
					if (hasTwoPair() && !hasThreeOfAKind() && !hasFullHouse()) {
						System.out.println("TWO PAIR -- YOU WIN!!");
						System.out.println(intY + " x 2 !!");
						
						intX = intX + (intY * 2);
						intY = 0;
						String x = String.valueOf(intX);			//convert string to integer
						String y = String.valueOf(intY);			//convert string to integer
						pocketX.setText(x);							//update Pocket						
						betY.setText(y);							//update Bet
						dealt = false;
						win = true;
						
						
						one.setSelected(false);
						ten.setSelected(false);
						hundred.setSelected(false);
						
						for(int i=0;i<5;i++){ 
							card[i] = false;
						}
					}
					else if (hasThreeOfAKind() && !hasFullHouse()){
						System.out.println("THREE OF A KIND -- YOU WIN!!");
						System.out.println(intY + " x 3 !!");
						
						intX = intX + (intY * 3);
						intY = 0;
						String x = String.valueOf(intX);			//convert string to integer
						String y = String.valueOf(intY);			//convert string to integer
						pocketX.setText(x);							//update Pocket						
						betY.setText(y);							//update Bet
						dealt = false;
						win = true;
						
						
						one.setSelected(false);
						ten.setSelected(false);
						hundred.setSelected(false);
						
						for(int i=0;i<5;i++){ 
							card[i] = false;
						}
					}
					else if (hasStraight()) {
						System.out.println("STRAIGHT -- YOU WIN!!");
						System.out.println(intY + " x 4 !!");
						
						intX = intX + (intY * 4);
						intY = 0;
						String x = String.valueOf(intX);			//convert string to integer
						String y = String.valueOf(intY);			//convert string to integer
						pocketX.setText(x);							//update Pocket						
						betY.setText(y);							//update Bet
						dealt = false;
						win = true;
						
						
						one.setSelected(false);
						ten.setSelected(false);
						hundred.setSelected(false);
						
						for(int i=0;i<5;i++){ 
							card[i] = false;
						}
					}
					else if (hasFlush()) {
						System.out.println("FLUSH -- YOU WIN!!");
						System.out.println(intY + " x 5 !!");
						
						intX = intX + (intY * 5);
						intY = 0;
						String x = String.valueOf(intX);			//convert string to integer
						String y = String.valueOf(intY);			//convert string to integer
						pocketX.setText(x);							//update Pocket						
						betY.setText(y);							//update Bet
						dealt = false;
						win = true;
						
						
						one.setSelected(false);
						ten.setSelected(false);
						hundred.setSelected(false);
						
						for(int i=0;i<5;i++){ 
							card[i] = false;
						}
					}
					else if ((hasFullHouse())) {
						System.out.println("FULL HOUSE -- YOU WIN!!");
						System.out.println(intY + " x 6 !!");
						
						intX = intX + (intY * 6);
						intY = 0;
						String x = String.valueOf(intX);			//convert string to integer
						String y = String.valueOf(intY);			//convert string to integer
						pocketX.setText(x);							//update Pocket						
						betY.setText(y);							//update Bet
						dealt = false;
						win = true;
						
						
						one.setSelected(false);
						ten.setSelected(false);
						hundred.setSelected(false);
						
						for(int i=0;i<5;i++){ 
							card[i] = false;
						}
					}
					else if (hasFourOfAKind()) {
						System.out.println("FOUR OF A KIND -- YOU WIN!!");
						System.out.println(intY + " x 7 !!");
						
						intX = intX + (intY * 7);
						intY = 0;
						String x = String.valueOf(intX);			//convert string to integer
						String y = String.valueOf(intY);			//convert string to integer
						pocketX.setText(x);							//update Pocket						
						betY.setText(y);							//update Bet
						dealt = false;
						win = true;
						
						
						one.setSelected(false);
						ten.setSelected(false);
						hundred.setSelected(false);
						
						for(int i=0;i<5;i++){ 
							card[i] = false;
						}
					}
					else if (hasStraightFlush()) {
						System.out.println("STRAIGHT FLUSH -- YOU WIN!!");
						System.out.println(intY + " x 9 !!");
						
						intX = intX + (intY * 8);
						intY = 0;
						String x = String.valueOf(intX);			//convert string to integer
						String y = String.valueOf(intY);			//convert string to integer
						pocketX.setText(x);							//update Pocket						
						betY.setText(y);							//update Bet
						dealt = false;
						win = true;
						
						
						one.setSelected(false);
						ten.setSelected(false);
						hundred.setSelected(false);
						
						for(int i=0;i<5;i++){ 
							card[i] = false;
						}
					}
					else if (hasRoyalFlush()) {
						System.out.println("ROYAL FLUSH -- YOU WIN!!");
						System.out.println(intY + " x 10 !!");
						
						intX = intX + (intY * 9);
						intY = 0;
						String x = String.valueOf(intX);			//convert string to integer
						String y = String.valueOf(intY);			//convert string to integer
						pocketX.setText(x);							//update Pocket						
						betY.setText(y);							//update Bet
						dealt = false;
						win = true;
						
						
						one.setSelected(false);
						ten.setSelected(false);
						hundred.setSelected(false);
						
						for(int i=0;i<5;i++){ 
							card[i] = false;
						}
					}
					
				}
			});
			bottomhBox.getChildren().addAll(btDeal, btHold1, btHold2, btHold3, btHold4, btHold5, btDraw);
			
			
		return pane;
	}
	
	//method to return true if player has a pair
	public boolean hasPair() {
		Collections.sort(cardRank);
		
		if ((cardRank.get(0) == cardRank.get(1)) 
			|| (cardRank.get(1) == cardRank.get(2))
			|| (cardRank.get(2) == cardRank.get(3))
			|| (cardRank.get(3) == cardRank.get(4))) {  
			return true; 									//has a pair
		}
		else {
			return false;									//doesn't have a pair
		}
	}
	
	//method to return true if player has a two pair
	public boolean hasTwoPair() {
		Collections.sort(cardRank);
		
		if (((cardRank.get(0) == cardRank.get(1)) 
				&& (cardRank.get(2) == cardRank.get(3)))
				|| ((cardRank.get(0) == cardRank.get(1))
				&& (cardRank.get(3) == cardRank.get(4)))
				|| (cardRank.get(1) == cardRank.get(2) 
				&& (cardRank.get(3) == cardRank.get(4)))
					|| (cardRank.contains(53)) && (cardRank.get(0) == cardRank.get(1))
					|| (cardRank.contains(53)) && (cardRank.get(1) == cardRank.get(2))
					|| (cardRank.contains(53)) && (cardRank.get(2) == cardRank.get(3))
					|| (cardRank.contains(53)) && (cardRank.get(3) == cardRank.get(4))
					|| (cardRank.contains(54)) && (cardRank.get(0) == cardRank.get(1))
					|| (cardRank.contains(54)) && (cardRank.get(1) == cardRank.get(2))
					|| (cardRank.contains(54)) && (cardRank.get(2) == cardRank.get(3))
					|| (cardRank.contains(54)) && (cardRank.get(3) == cardRank.get(4))) {  
			return true;									//has two pair
		}
		else {
			return false;									//doesn't have a pair
		}
	}
	
	//method to return true if player has three of a kind
	public boolean hasThreeOfAKind() {
		Collections.sort(cardRank);
		
		if (((cardRank.get(0) == cardRank.get(1)) 
			&& (cardRank.get(1) == cardRank.get(2)))
			|| ((cardRank.get(1) == cardRank.get(2))
			&& (cardRank.get(2) == cardRank.get(3)))
			|| (cardRank.get(2) == cardRank.get(3) 
			&& (cardRank.get(3) == cardRank.get(4)))
			|| (cardRank.contains(53)) && (cardRank.get(0) == cardRank.get(1))
			|| (cardRank.contains(53)) && (cardRank.get(1) == cardRank.get(2))
			|| (cardRank.contains(53)) && (cardRank.get(2) == cardRank.get(3))
			|| (cardRank.contains(53)) && (cardRank.get(3) == cardRank.get(4))
			|| (cardRank.contains(54)) && (cardRank.get(0) == cardRank.get(1))
			|| (cardRank.contains(54)) && (cardRank.get(1) == cardRank.get(2))
			|| (cardRank.contains(54)) && (cardRank.get(2) == cardRank.get(3))
			|| (cardRank.contains(54)) && (cardRank.get(3) == cardRank.get(4))) {
			return true;									//has three of a kind
		}
		else {
			return false;									//doesn't have three of a kind	
		}
	}
	
	//method to return true if player has a straight
	public boolean hasStraight() {
		Collections.sort(cardRank);
		
		if (((cardRank.get(0)) + 1 == (cardRank.get(1)))
			&& ((cardRank.get(1)) + 1 == (cardRank.get(2)))
			&& ((cardRank.get(2)) + 1 == (cardRank.get(3)))
			&& ((cardRank.get(3)) + 1 == (cardRank.get(4)))
				//|| (cardRank.contains(53)) || (cardRank.contains(54))
				) {
			return true;										//has straight
		}
		else {
			return false;										//doesn't have a straight	
		}
	}
	
	//method to return true if player has a flush
	public boolean hasFlush () {
		if (cardSuit.get(0) == cardSuit.get(1) 
				&& cardSuit.get(1) == cardSuit.get(2)
				&& cardSuit.get(2) == cardSuit.get(3) 
				&& cardSuit.get(3) == cardSuit.get(4)) 
			//|| (cardRank.contains(53)) || (cardRank.contains(54))
		{
			
			return true; 									//has flush
		}
		else {
			return false; 									//doesn't have flush
		}
	}
	
		//method to return true if player has a full house
		public boolean hasFullHouse() {
			Collections.sort(cardRank);
			
			if (((cardRank.get(0) == cardRank.get(1)) 
				&& (cardRank.get(1) == cardRank.get(2)))
				&& ((cardRank.get(3) == cardRank.get(4))
				|| (cardRank.get(2) == cardRank.get(3)))
				&& (cardRank.get(3) == cardRank.get(4) 
				&& (cardRank.get(0) == cardRank.get(1)))
				//|| (cardRank.contains(53)) || (cardRank.contains(54))
					) {  
				return true;									//has a full house
			}
			else {
				return false;									//doesn't have a full house
			}
		}
		
		//method to return true if player has four of a kind
		public boolean hasFourOfAKind() {
			Collections.sort(cardRank);
			
			if (((cardRank.get(0) == cardRank.get(1)) 
				&& (cardRank.get(1) == cardRank.get(2)))
				&& ((cardRank.get(2) == cardRank.get(3))
				|| (cardRank.get(1) == cardRank.get(2)))
				&& (cardRank.get(2) == cardRank.get(3) 
				&& (cardRank.get(3) == cardRank.get(4)))
				//|| (cardRank.contains(53)) || (cardRank.contains(54))
					) {  
				return true;									//has four of a kind
			}
			else {
				return false;									//doesn't have four of a kind	
			}
		}	
		
		//method to return true if player has a straight straight
		public boolean hasStraightFlush() {
			Collections.sort(cardRank);
			
			if (((cardRank.get(0)) + 1 == (cardRank.get(1)))
				&& ((cardRank.get(1)) + 1 == (cardRank.get(2)))
				&& ((cardRank.get(2)) + 1 == (cardRank.get(3)))
				&& ((cardRank.get(3)) + 1 == (cardRank.get(4)))
					&& ((cardSuit.get(0) == cardSuit.get(1)) 
					&& (cardSuit.get(1) == cardSuit.get(2))
					&& (cardSuit.get(2) == cardSuit.get(3)) 
					&& (cardSuit.get(3) == cardSuit.get(4)))
					//|| (cardRank.contains(53)) || (cardRank.contains(54))
					) {
				return true;										//has straight flush
			}
			else {
				return false;										//doesn't have a straight flush
			}
		}
		//method to return true if player has a royal flush
		public boolean hasRoyalFlush() {
			Collections.sort(cardRank);
					
			if (((cardRank.get(0)) + 1 == (cardRank.get(1)))
				&& ((cardRank.get(1)) + 1 == (cardRank.get(2)))
				&& ((cardRank.get(2)) + 1 == (cardRank.get(3)))
				&& ((cardRank.get(3)) + 1 == (cardRank.get(4)))
					&& ((cardSuit.get(0) == cardSuit.get(1)) 
					&& (cardSuit.get(1) == cardSuit.get(2))
					&& (cardSuit.get(2) == cardSuit.get(3)) 
					&& (cardSuit.get(3) == cardSuit.get(4)))
					//|| (cardRank.contains(53)) || (cardRank.contains(54))
					) {
						return true;										//has a royal flush
					}
					else {
						return false;										//doesn't have a royal flush
					}
				}
	
	//rotator to animate the card rotating
	/*private RotateTransition createRotator(Node imageView) {
        RotateTransition rotator = new RotateTransition(Duration.millis(1000), imageView);
        rotator.setAxis(Rotate.Y_AXIS);
        rotator.setFromAngle(0);
        rotator.setToAngle(360);
        rotator.setInterpolator(Interpolator.LINEAR);
        rotator.setCycleCount(1);
        
        return rotator;
    }*/
	
	//main method to run the game
	public static void main(String[] args) {
		System.out.println("Launching application...");
		Application.launch(args);
	}
}
