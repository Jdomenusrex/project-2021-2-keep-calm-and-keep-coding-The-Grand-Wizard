package pane;





import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.media.AudioClip;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import logic.GameLogic;


public class EndTurnPane extends BorderPane {
		
	private Rectangle endTurnRectangle; 
	
	public EndTurnPane() {
		this.setPadding(new Insets(25,50,25,50));
		this.setEndRectangle();
		this.setRight(endTurnRectangle);
		
		// TODO Auto-generated constructor stub
	    EventHandler<MouseEvent> EH= new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent arg0) {
				if(GameLogic.isPlayerTurn) {
				GameLogic.endTurn();
				drawOut();
				AudioClip Hit = new AudioClip(ClassLoader.getSystemResource("HitSound.wav").toString());
				Hit.setVolume(0.1);
				Hit.play();}
			}
		};
		this.setOnMouseClicked(EH);
		this.setOnMouseEntered(new EventHandler<MouseEvent>()  {
			@Override
			public void handle(MouseEvent arg0) {
				// TODO Auto-generated method stub
				if(GameLogic.isPlayerTurn)
				drawEnter();
			}
		});
		this.setOnMouseExited(new EventHandler<MouseEvent>()  {
			@Override
			public void handle(MouseEvent arg0) {
				// TODO Auto-generated method stub
				if(GameLogic.isPlayerTurn)
				drawOut();
			}
		});
}
	public void drawOut() {
		ImagePattern image=new ImagePattern(new Image(ClassLoader.getSystemResource("Endturn1.jpg").toString()));
		this.endTurnRectangle.setFill(image);
	}
	public void drawEnter() {
		ImagePattern image=new ImagePattern(new Image(ClassLoader.getSystemResource("Endturn2.jpg").toString()));
		this.endTurnRectangle.setFill(image);
	}
	public void setEndRectangle() {
		this.endTurnRectangle=new Rectangle(200,100);
		this.endTurnRectangle.setArcHeight(100);
		this.endTurnRectangle.setArcWidth(100);
		this.endTurnRectangle.setStroke(Color.GRAY);
		this.endTurnRectangle.setStrokeWidth(3);
		this.drawOut();
	}
}
	
