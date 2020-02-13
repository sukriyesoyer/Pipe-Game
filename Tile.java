//Gizem Çelik 150118002
//Þükriye Soyer 150116010
//We imported javafx Imageview.
import javafx.scene.image.ImageView;

/*We created a class named tile. This class sets tile's properties. We create objects of tile's in our test class.
                           */
public class Tile  {
//We declared our variables as private. 
//Type means, type of tiles. Loc means properties of the tiles.(vertical, horizontal)    Index means id of tile in board. Lastly, i represents picture of tiles.
private String type;
private String loc;
private int index;
private ImageView i; 

//We created default constructor.
public Tile ( ) {
	
}
//This constructor sets our parameters 
public Tile (String type , String loc, int index ) {
	type = this.getType();
	loc = this.loc;
	index = this.getIndex();
}
//This method controls move property. if tile tile is not movable , it will return false to its caller.
public boolean isMoving (String type) {
	if( type.equals("PipeStatic") || type.equals("Starter") || type.equals("End")) {  //Here is not movable types of tiles.
		return false;
	}
	else //If type is other than in line 28, it will return move property as true.
		return true;
}

//This method is most important method to chose picture
public void images (String type , String loc,int x, int y) {
	if( type.equals("Starter") && loc.equals("Vertical")) { //This if case finds the true picture for tile object with its properties.
		i = new ImageView("starterVertical.PNG");
		i.setFitHeight(100); // Design the picture
		i.setFitWidth(100);
		i.setX(100*(x)); //This sets coordinates of image with given x and y.
		i.setY(100*(y));
	}
	if( type.equals("Starter") && loc.equals("Horizontal")) { //Compute the name of picture
		i = new ImageView("starterHorizontal.PNG");
		i.setFitHeight(100);  // Design the picture
		i.setFitWidth(100);
		i.setX(100*(x));
		i.setY(100*(y));
		
	}
	else if( type.equals("Empty") && loc.equals("none")) { //Compute the name of picture
		i = new ImageView("emptyNone.PNG");
		i.setFitHeight(100);  // Design the picture
		i.setFitWidth(100);
		i.setX(100*(x));
		i.setY(100*(y));
	}
	else if( type.equals("Pipe") && loc.equals("Vertical")) { //Compute the name of picture
		i = new ImageView("pipeVertical.PNG");
		i.setFitHeight(100);  // Design the picture
		i.setFitWidth(100);
		i.setX(100*(x));
		i.setY(100*(y));
	}
	else if( type.equals("Pipe") && loc.equals("Horizontal")) { //Compute the name of picture
		i = new ImageView("pipeHorizontal.PNG");
		i.setFitHeight(100);  // Design the picture
		i.setFitWidth(100);
		i.setX(100*(x));
		i.setY(100*(y));
	}
	else if( type.equals("Pipe") && loc.equals("01")) { //Compute the name of picture
		i = new ImageView("pipe01.PNG");
		i.setFitHeight(100);  // Design the picture
		i.setFitWidth(100);
		i.setX(100*(x));
		i.setY(100*(y));
	}
	else if( type.equals("Empty") && loc.equals("Free")) { //Compute the name of picture
		i = new ImageView("emptyFree.PNG");
		i.setFitHeight(100);  // Design the picture
		i.setFitWidth(100);
		i.setX(100*(x));
		i.setY(100*(y));
	}
	else if( type.equals("PipeStatic") && loc.equals("Horizontal")) { //Compute the name of picture
		i = new ImageView("pipestaticHorizontal.PNG");
		i.setFitHeight(100);  // Design the picture
		i.setFitWidth(100);
		i.setX(100*(x));
		i.setY(100*(y));
	}
	else if( type.equals("End") && loc.equals("Horizontal")) { //Compute the name of picture
		i = new ImageView("endHorizontal.PNG");
		i.setFitHeight(100);  // Design the picture
		i.setFitWidth(100);
		i.setX(100*(x));
		i.setY(100*(y));
	}
	else if( type.equals("End") && loc.equals("Vertical")) { //Compute the name of picture
		i = new ImageView("endVertical.PNG");
		i.setFitHeight(100);  // Design the picture
		i.setFitWidth(100);
		i.setX(100*(x));
		i.setY(100*(y));
	}
	else if( type.equals("PipeStatic") && loc.equals("Vertical")) { //Compute the name of picture
		i = new ImageView("pipestaticVertical.PNG");
		i.setFitHeight(100);  // Design the picture
		i.setFitWidth(100);
		i.setX(100*(x));
		i.setY(100*(y));
	}
	else if( type.equals("Pipe") && loc.equals("00")) { //Compute the name of picture
		i = new ImageView("pipe00.PNG");
		i.setFitHeight(100);  // Design the picture
		i.setFitWidth(100);
		i.setX(100*(x));
		i.setY(100*(y));
	}
	else if( type.equals("Pipe") && loc.equals("10")) { //Compute the name of picture
		i = new ImageView("pipe10.PNG");
		i.setFitHeight(100);  // Design the picture
		i.setFitWidth(100);
		i.setX(100*(x));
		i.setY(100*(y));
	}
	else if( type.equals("Pipe") && loc.equals("11")) { //Compute the name of picture
		i = new ImageView("pipe11.PNG");
		i.setFitHeight(100);  // Design the picture
		i.setFitWidth(100);
		i.setX(100*(x));
		i.setY(100*(y));
	}
	
	else if( type.equals("PipeStatic") && loc.equals("01")) { //Compute the name of picture
		i = new ImageView("pipestatic0l.PNG");
		i.setFitHeight(100);  // Design the picture
		i.setFitWidth(100);
		i.setX(100*(x)); 
		i.setY(100*(y));
	}
			
}

//This methods is getter setter methods for our variables. 
public String getType() {
	return type;
}

public void setType(String type) {
	this.type = type;
}
public String getLoc() {
	return loc;
}

public void setLoc(String loc) {
	this.loc = loc;
}

public int getIndex() {
	return index;
}

public void setIndex(int index) {
	this.index = index;
}

public ImageView getI() {
	return i;
}

public void setI(ImageView i) {
	this.i = i;
}




}
