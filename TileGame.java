//This program purpose is  implement the  game by using JavaFX framework. This game aims moving a ball from a start point to an end point if there is an appropriate path. The user is supposed to form a path using pipes given as sliding tiles.
//Gizem Çelik 150118002
//Þükriye Soyer 150116010
//We imported important javafx docs and others.

import java.io.File;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class TileGame extends Application{
Scene sce; //We declared scene
int countt = 0; //This is for drag event. It's on the drag part.
Pane pa  = new Pane();; //we declared our pane.
Scene scen; //We declared scene   
Tile[][] tileuse = new Tile[4][4]; // We created 2d array for drag tile's. It's on the drag part.
//This booleans is for win conditions         // This is for read a level file.   //This is for creating new file's name.  //This is for number of moves.
 boolean win = false;  boolean win1 = false;      int level = 2;                     int count =0;                           int moves = 1;
	
public static void main(String[] args) {
		// Here is main method  for launch our game.
   launch(args);
	}

	@Override  //Here is our start method.
	public void start(Stage primaryStage) throws Exception {
		
		 //We declared our width and height properties for our scene.
		final double WIDTH = 400; final double HEIGHT = 450;
		//We created 'next' button. And we set it's coordinates.
		Button next = new Button(" Next ");   next.setLayoutY(405); next.setLayoutX(330);
		
		ArrayList<Tile> level1 = new ArrayList<Tile>(); //This is for hold level1's tile objects.
		ArrayList<Tile> levels = new ArrayList<Tile>(); //This is for hold other levels tile objects.
		//We created a label and we set it's coordinates.
		Label rule = new Label("You should set pipes correctly to move next level.");   rule.setLayoutX(3);   rule.setLayoutY(400);
		//We created 2d array for place our tile  objects.
		Tile[][] pipe = new Tile[4][4];  
		
		try {
		
			File first = new File("level1.txt"); //We entered a file for read it.
			String newFile = fileMaker(first,count); //We created method which returns us no ',' text.
			File l0 = new File(newFile); //We read file which is returned by method. Actually return value of this method is string.
			Scanner i1 = new Scanner(l0);
			
			//This x and y is for setting image's coordinates by our algorithm.
			int x = 0;	int y=  0;
			while (i1.hasNext()) {
				for( int k = 0; k< 16 ; k++) {
					int index = i1.nextInt(); String type = i1.next(); String loc = i1.next();
					Tile p = new Tile(type,loc,index);
					//We set object's properties.
					p.setLoc(loc); p.setIndex(index);	p.setType(type); p.isMoving(type);
					//Adding object to our ArrayList
					level1.add(p); 
					if(k%4==0 && k !=0) {
						y++; x=0;  //We set the x and y for 4 dimensional array. 
					p.images(type, loc,x,y); //This method sets images and its locations. It's in the Tile class.
					x++;
					  }
					else { p.images(type, loc,x,y); x++; }}
				
					 int b = 0;
				for(x=0;x<4;x++) { //This code transfers level1's objects to 2d array.
					for(y=0;y<4;y++) {pipe[x][y]= level1.get(b);  b++;}}
                
					for(x=0;x<4;x++) {
					for(y=0;y<4;y++) {//This code adds images to our pane.
					pa.getChildren().add(pipe[x][y].getI());  }}
				count++; // This increments newFiles name in fileMaker method.
                //This code adds rule label to our pane.   //This creates scene containing pane pa.with width and height //This code sets stage to Scene sce.
				pa.getChildren().addAll(rule);	            sce= new Scene( pa, WIDTH, HEIGHT);	                       primaryStage.setScene(sce); }}
		//If program can not find a file, then it will raise exception
	catch (Exception e) {
		System.out.println(e.getMessage()); }
	//This code creates pane and scene for start screen of the game. Also, it
		Pane pane = new Pane();
		Scene s = new Scene(pane);  pane.setMaxSize(400, 400);
		   //This code creates label and txt, and it sets it's coordinates as we give. Then we set it's font.
			Label l1 = new Label("Tile Game!");  Text t1 = new Text("Press start to play game.");  t1.setX(70); t1.setY(230); t1.setFill(Color.MEDIUMPURPLE); t1.setFont(Font.font(null,FontWeight.BOLD,20));
			//We set the color of label to purple for our main screen
			l1.setTextFill(Color.PURPLE); l1.setFont(Font.font("Arial", FontWeight.BOLD, FontPosture.ITALIC, 50));    l1.setLayoutX(60);   l1.setLayoutY(150);
			//We added image to our start screen. Then we set It's coordinates.
			ImageView i = new ImageView("holographic-wallpaper-vector.jpg");   i.setFitWidth(400);   i.setFitHeight(400);
			
			//We created start and exit button.
			Button start = new Button("  Start  ");  Button exit = new Button("   Exit   ");
			start.setLayoutX(130); start.setLayoutY(250);  //sets the 
			exit.setLayoutX(190); exit.setLayoutY(250);
			
		
			
		    //Creates start page, by adding our objects.
		    pane.getChildren().add(i); pane.getChildren().addAll(l1,start,exit,t1);
		    //sets scene and title, then it displays page to us.
			primaryStage.setScene(s);  primaryStage.setTitle("TileGame");  primaryStage.show();
			
			//When we press start, It gaves level 1 to us.
			start.setOnAction(e-> {primaryStage.setScene(sce);
			primaryStage.show();});
			
			//If user presses exit button, it will exit from the program.
           exit.setOnAction(e->{
        	   System.exit(1);
           });
         
           //This arraylist contains, our 2 tiles to swap.
			ArrayList<Tile> presd = new ArrayList<>();
			countt = 0;
		//this is lambda expression. It will be activated when user drags a tile.
			pa.setOnMouseDragged(e1->{
				//coordinates must be in this interval. 
				if(e1.getX()<= 400 && e1.getY()<=400) {
				if(countt  == 0) {
					//This gets pressed location, and set x and y coordinates.
				double mousex1 = e1.getX(); 	double mousey1 = e1.getY(); countt++; //Count is incremented because we choose one coordinate.
				//This method finds the object that we first touched.
				Tile p2= Tile(mousex1,mousey1,pipe);  presd.add(p2);  //then it adds this object to pressed arraylist.
				
				}
			
				//this is lambda expression. It will be activated when user releases mouse.
                pa.setOnMouseReleased(e->{
                	//This gets the coordinates when mouse is released.
                	double mousex2 = e.getX(); 	double mousey2 = e.getY();
    				//coordinates must be in this interval. 
                	if(mousex2 <=400 && mousey2 <=400) {
                	//This method finds the object that we lastly touched.
    				Tile p1= Tile(mousex2,mousey2,pipe);  presd.add(p1); //then it adds this object to pressed arraylist.
				
    				if(presd.size() % 2 ==  0) {
					boolean move = move(presd.get(0),presd.get(1));// This looks at, is tile  can move to right,left,up or down?
					//This controls, is object is movable? it is movable then it will return true.
					boolean p1move = presd.get(0).isMoving(presd.get(0).getType());    boolean p2move = presd.get(1).isMoving(presd.get(1).getType());
				//If tiles is movable and second tile property is empty free and if they are close to each other, it will invoke the swap method.
					if(p1move && p2move && move && presd.get(1).getType().equals("Empty")&& presd.get(1).getLoc().equals("Free")) {
					tileuse = swap(presd.get(0),presd.get(1),pipe); //This method swaps 
					pa.getChildren().clear();//Then it clears pane.
					//We created text for print number of moves to screen.
					Text movescount1 = new Text("Number of moves is: " + moves);   movescount1.setLayoutX(10);  movescount1.setLayoutY(445);
					pa.getChildren().add(movescount1); // this adds our text to pane.
					for(int x=0;x<4;x++) {
						for(int y=0;y<4;y++) {//This add swapped 2d array to our pane.
						pa.getChildren().add(tileuse[x][y].getI());  }}
					 pa.getChildren().add(rule);// this adds our rule to pane.
					win1 = won2(tileuse);//This method controls win condition. if path is true, then user will able to play next level. It's not dynamic. It's for level 4 and 5. 
					win = won1(tileuse); //This method controls win condition. if path is true, then user will able to play next level. It's not dynamic. It's for level 1,2 and 3.
					moves++;//This counts, number of movements.
					
					//If user wins 12345 levels. it wil continue executing. It's not dynamic.
					if(win || win1) {
						//If user wins the level, it will print last status of tiles.
						pa.getChildren().clear();
						pa.getChildren().addAll(movescount1,next); 
						
						for(int x=0;x<4;x++) {
							for(int y=0;y<4;y++) {//This add swapped 2d array to our pane.
							pa.getChildren().add(tileuse[x][y].getI());  }}
						 pa.getChildren().add(rule);
						//If user wins and clicks next, then next level will be printed to same pane and it will be shown to us.
						next.setOnAction(e2->{ //Here there is lambda expression too. 
			            File isHere = new File("level" + level + ".txt");
			            
			            if(isHere.exists()) {//If next level exists, it will continue executing.
						try {
							//This makes moves as 1 for next level.
							moves = 1;
							pa.getChildren().removeAll(movescount1);  //This removes previous count from pane, because it makes it duplicate.
							//Here this is where we read a file.
							File first = new File("level" + level + ".txt");
							String newFile = fileMaker(first,count); //We created method which returns us no ',' text.
							File l0 = new File(newFile); //We read file which is returned by method. Actually return value of this method is string.
							Scanner i1 = new Scanner(l0); //Then we created scanner for read a file.
							//This x and y is for setting image's coordinates by our algorithm.
							int x = 0;	int y=  0; 
							while (i1.hasNext()) {
								for( int k = 0; k< 16 ; k++) {//this code reads file and gets properties.
									int index = i1.nextInt(); String type = i1.next(); String loc = i1.next();
									Tile p = new Tile(type,loc,index); //This creates pipe object with given parameters.
									//Setting variables
									p.setLoc(loc); p.setIndex(index);	p.setType(type); p.isMoving(type);
									//Adding object to array
									levels.add(p); 
									if(k%4==0 && k !=0) {
										y++; x=0;
									p.images(type, loc,x,y);  //This is algorithm for set images coordinates.
									x++;
									  }
									else {
										p.images(type, loc,x,y); x++; //This code set's Tile's image.
									   }
									}
									 int b = 0;
								for(x=0;x<4;x++) {  //This places elements in arraylist to Tile array
									for(y=0;y<4;y++) {pipe[x][y]= levels.get(b);  b++;}}
				                
									for(x=0;x<4;x++) {
									for(y=0;y<4;y++) { //This adds swapped 2d array to our pane.
									pa.getChildren().add(pipe[x][y].getI());
									  }}
									//This clears Tile arraylist for next levels.
									levels.clear();    pa.getChildren().removeAll(next); //This removes next button from pane.
									level++;   count++;               win = false;   win1 = false;          primaryStage.show(); }}
						       //increments level,count property.    // Sets win condition to false again.
					catch (Exception ex) { //If there is a exception, it will raise a exception.
					System.out.println(ex.getMessage()); }}
			            else {
			            	//If there is no next level txt, it will print "You completed all levels!\n Congrats!" to screen.
			            	pa.getChildren().clear();
			            	Label wiin = new Label("You completed all levels!\n Congrats!");
			    			wiin.setFont(Font.font("Arial", FontWeight.BOLD, FontPosture.ITALIC, 30));  wiin.setLayoutX(25); wiin.setLayoutY(250);
			    			wiin.setTextFill(Color.PINK);
			    			pa.getChildren().add(wiin); //This adds label to pane.
			    			primaryStage.show();
			            }
						});   }}
					if(presd.size() % 2 == 0) // If there are 2 tiles in arrayList, it will remove all elements in arraylist.
					presd.removeAll(presd);  primaryStage.show();
					countt=0; //Sets count as 0
				 }
                
                
                else //There is no else condition, it will do nothing.
                	;
			}});
				}});
		
			
	}
	
	public  static String fileMaker(File f, int count) {  //This method most important method to read a file
		try {
			Scanner i1 = new Scanner(f);
			PrintWriter new1 = new PrintWriter("newFilee1" + count + ".txt"); 
		while (i1.hasNext()) {
			String s = i1.next();  s = s.replace(',', ' ');  new1.println(s);  } //Firstly it takes out ','
		new1.close();
		return "newFilee1" + count + ".txt"; //This returns new created files name.
		}
		catch (Exception e) { //If exception happens, it will catch and print this message.
			System.out.println("No file created.");
		}
		return "newFilee1" + count + ".txt"; //This returns new created files name.
		
	}
		
		
		public static Tile Tile(double x, double y, Tile[][] p) { // This method aim is find the tile around mouse.
			Tile pip = null; //Here is return object.
			//If mouse coordinates is between this numbers then it will return first element of 2d array.
			if(x <= 100 && y <= 100) {
				pip =  p[0][0];
			}//Logic is same for other else if cases.
			else if(x>=100 && x<= 200 && y<= 100) {
				pip = p[0][1];
		}
			else if(x>=200 && x<= 300 && y<= 100) {
				pip = p[0][2];
		}
			else if(x>=300 && x<= 400 && y<= 100) {
				pip = p[0][3];
		}
			else if(x <= 100  && y <= 200 && y>=100) {
				pip =  p[1][0];
			}
			else if(x >= 100 && x<= 200 && y <= 200 && y>=100) {
				pip =  p[1][1];
			}
			else if(x >= 200 && x<= 300 && y <= 200 && y>=100) {
				pip =  p[1][2];
			}
			else if(x >= 300 && x<= 400 && y <= 200 && y>=100) {
				pip =  p[1][3];
			}
			else if(x <= 100  && y <= 300 && y>=200) {
				pip =  p[2][0];
			}
			
			else if(x >= 100 && x<= 200 && y <= 300 && y>=200) {
				pip =  p[2][1];
			}
			else if(x >= 200 && x<= 300 && y <= 300 && y>=200) {
				pip =  p[2][2];
			}
			else if(x >= 300 && x<= 400 && y <= 300 && y>=200) {
				pip =  p[2][3];
			}
			else if(x <= 100  && y <= 400 && y>=300) {
				pip =  p[3][0];
			}
			else if(x >= 100 && x<= 200 && y <= 400 && y>=300) {
				pip =  p[3][1];
			}
			else if(x >= 200 && x<= 300 && y <= 400 && y>=300) {
				pip =  p[3][2];
			}
			else if(x >= 300 && x<= 400 && y <= 400 && y>=300) {
				pip =  p[3][3];
			}
		return pip;
}
		

public static Tile[][] swap(Tile p1, Tile p2,Tile[][] arr) { //This method to swaps the  tiles
	//This variable represents tiles coordinates.
	int p1x=0; int p1y=0; int p2x=0; int p2y = 0;
	Tile temp=p1;
	int tempin = p1.getIndex(); //These variables represents tiles first location and first index. This is for first tile.
	double tempx = p1.getI().getX();  double tempy = p1.getI().getY();
	
	Tile temp2 = p2;
	int tempin2 = p2.getIndex(); //These variables represents tiles first location and first index. This is for second tile.
	double temp2x = p2.getI().getX();  double temp2y = p2.getI().getY();
	
	for(int x=0;x<4;x++) {  //Then swap the tiles
		for(int y=0;y<4;y++) {
			if(arr[x][y].equals(p1)) {
				p1x= x; p1y = y;}
			
			else if(arr[x][y].equals(p2)) {
					p2x= x; p2y = y;}
		}
	}
	
	//This if condition is for find priority of tiles, if p1 is before p2. Then it will find p1 first then find p2.
	if( (p1x < p2x && p1y == p2y) || p2y > p1y) {
	for(int x=0;x<4;x++) {
		for(int y=0;y<4;y++) {
	if(arr[x][y].equals(p1)) {
		arr[x][y] = p2;//If tile equals to p1 tile. it will place p2 to p1's place.
		p1.getI().setX(p2.getI().getX()); p1.getI().setY(p2.getI().getY()); //here it sets p1's coordinates to p2.
		p2.getI().setX(tempx); p2.getI().setY(tempy);  //here it sets p2's properties to p1.
		p1.setIndex(p2.getIndex()); 	p2.setIndex(tempin); } //Then it swaps indexes. 
	else if(arr[x][y].equals(p2)) { //If tile equals to p2 tile. it will place p1 to p2's place.
		arr[x][y] = temp;
		
		return arr;
	}
		}
  }
}
	//This if condition is for find priority of tiles, if p2 is before p1. Then it will find p2 firstly then find p1.
	else if( (p1x > p2x && p1y == p2y) || p2y < p1y) {
		for(int x=0;x<4;x++) {
			for(int y=0;y<4;y++) {
		if(arr[x][y].equals(p2)) { //Logic is same as first if.
			arr[x][y] = p1;
			p2.getI().setX(p1.getI().getX()); p2.getI().setY(p1.getI().getY());
			p1.getI().setX(temp2x); p1.getI().setY(temp2y); 
			p2.setIndex(p1.getIndex());
			p1.setIndex(tempin2);
			 
		}
		else if(arr[x][y].equals(p1)) {
			arr[x][y] = temp2;
			return arr;
		}
			}
	  }
	}
	return arr;
    }

public static boolean move(Tile p1, Tile p2) { //The most important method to move tiles. This controls second tile is near to first pressed tile. 
	//. If it is second tile is in right side upper side down side or left side of first tile, it checks this conditions for if it can move. 
	if(p1.getIndex() == 1 &&( p2.getIndex() == 2 || ((Tile) p2).getIndex() == 5)) {
		return true;
	}
	else if(p1.getIndex() == 2 &&( p2.getIndex() == 3 || p2.getIndex() == 6 || p2.getIndex() == 1)) {
		return true;
	}
	else if(p1.getIndex() == 3 &&( p2.getIndex() == 2 || p2.getIndex() == 7 || p2.getIndex() == 4)) {
		return true;
	}
	else if(p1.getIndex() == 4 &&( p2.getIndex() == 3 || p2.getIndex() == 8 )) {
		return true;
	}
	else if(p1.getIndex() == 5 &&( p2.getIndex() == 6 || p2.getIndex() == 9|| p2.getIndex() == 1)) {
		return true;
	}
	else if(p1.getIndex() == 6 &&( p2.getIndex() == 5 || p2.getIndex() == 2 || p2.getIndex() == 7|| p2.getIndex() == 10)) {
		return true;
	}
	else if(p1.getIndex() == 7 &&( p2.getIndex() == 3 || p2.getIndex() == 6 || p2.getIndex() == 11|| p2.getIndex() == 8)) {
		return true;
	}
	else if(p1.getIndex() == 8 &&( p2.getIndex() == 4 || p2.getIndex() == 12 || p2.getIndex() == 7)) {
		return true;
	}
	else if(p1.getIndex() == 9 &&( p2.getIndex() == 5 || p2.getIndex() == 13 || p2.getIndex() == 10)) {
		return true;
	}
	else if(p1.getIndex() == 10 &&( p2.getIndex() == 9 || p2.getIndex() == 6 || p2.getIndex() == 14 || p2.getIndex() == 11)) {
		return true;
	}
	else if(p1.getIndex() ==11 &&( p2.getIndex() == 7 || p2.getIndex() == 12 || p2.getIndex() == 15|| p2.getIndex() == 10)) {
		return true;
	}
	else if(p1.getIndex() == 12 &&( p2.getIndex() == 8 || p2.getIndex() == 16 || p2.getIndex() == 11)) {
		return true;
	}
	else if(p1.getIndex() == 13 &&( p2.getIndex() == 9 || p2.getIndex() == 14 )) {
		return true;
	}
	else if(p1.getIndex() == 14 &&( p2.getIndex() == 10 || p2.getIndex() == 15 || p2.getIndex() == 13)) {
		return true;
	}
	else if(p1.getIndex() == 15 &&( p2.getIndex() == 11 || p2.getIndex() == 16 || p2.getIndex() == 14)) {
		return true;
	}else if(p1.getIndex() == 16 &&( p2.getIndex() == 15 || p2.getIndex() == 12 )) {
		return true;
	}
	else {
		return false;
	}
  }
public static boolean won1 (Tile[][] p) {  // This method compute status of  win of 1 ,2 ,and 3 levels, it basically looks for placements of tiles.

	if(p[0][0].getType().equals("Starter") && p[1][0].getType().equals("Pipe") && p[1][0].getLoc().equals("Vertical") 
			&& p[2][0].getType().equals("Pipe") && p[2][0].getLoc().equals("Vertical") && p[3][0].getType().equals("Pipe") && p[3][0].getLoc().equals("01") &
 p[3][1].getType().equals("Pipe") && p[3][1].getLoc().equals("Horizontal") && p[3][2].getType().equals("PipeStatic") && p[3][2].getLoc().equals("Horizontal") &&  p[3][3].getType().equals("End")    ) {
		return true;
	}
	else
		return false;
	
   }
public static boolean won2 (Tile[][] p) {  // This method compute status of  win of 4 and 5 levels,  it basically looks for placements of tiles.
if(p[0][0].getType().equals("Starter")  && p[1][0].getLoc().equals("Vertical") && p[2][0].getLoc().equals("01")&& p[2][1].getLoc().equals("Horizontal") &&  p[2][2].getLoc().equals("Horizontal") && p[2][3].getLoc().equals("00") && p[1][3].getType().equals("End"))
	return true;
else
	return false;
}
}

