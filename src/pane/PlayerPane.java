package pane;

import java.awt.TextField;

import buff.BaseBuff;
import creature.Player;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import logic.GameLogic;
import orb.AttackOrb;
import orb.DefenseOrb;
import orb.HealOrb;


public class PlayerPane extends HBox {
	private Player player;
	private VBox   playerAndStuff;
	private VBox  playerBuffListBox;
	public Player getPlayer() {
		return player;
	}
	
	public PlayerPane(Player player) {
		this.player=player;
		this.setPadding(new Insets(250,0, 0,10));
		player.getImageV().setFitHeight(300);
		player.getImageV().setFitWidth(200);
		GameLogic.holdingOrb.add(new AttackOrb());
		GameLogic.holdingOrb.add(new DefenseOrb() );
		GameLogic.holdingOrb.add(new HealOrb() );
		setUpPlayerBuffListBox();
		playerAndStuff =new VBox();
		playerAndStuff.setPrefWidth(200);
		playerAndStuff.getChildren().addAll(player.getIntendPane(),player.getImageV(),this.getPlayer().getHpPane());
		playerBuffListBox.setPrefWidth(200);
		this.setPrefWidth(400);
		this.getChildren().addAll(playerAndStuff,playerBuffListBox);
		
		this.playPlayerAnimation();
	}
	public void playPlayerAnimation() {
		Thread thread = new Thread(() -> {
			int i = 0;
			while(true) {
				this.getPlayer().getImageV().setImage(new Image(ClassLoader.getSystemResource("alonso-gomez-wizard-idle-anim-"+i+".png").toString()));
				try {
					Thread.sleep(50);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				i++;
				if(i==25) {
					i=0;
				}
				
			}
			});
			thread.start();
		}
	public void setUpPlayerBuffListBox() {
		playerBuffListBox=new VBox();
		this.getPlayer().getImageV().setOnMouseEntered(new EventHandler<Event>() {

			@Override
			public void handle(Event arg0) {
				// TODO Auto-generated method stub
				if(PlayerPane.this.getPlayer().getBuffList().size()>0) {
					playerBuffListBox.setVisible(true);
				}
			}});
		this.getPlayer().getImageV().setOnMouseExited(new EventHandler<Event>() {

			@Override
			public void handle(Event arg0) {
				// TODO Auto-generated method stub
				playerBuffListBox.setVisible(false);
			}
		});
		playerBuffListBox.setVisible(false);
	}
	public void upDatePlayerBuffListBox() {
		BaseBuff buff=this.getPlayer().getBuffList().get(this.getPlayer().getBuffList().size() - 1);
		Text text=new Text();
		Font font = new Font(12);
		Font.font("Verdana", FontWeight.BLACK, 12);
	    text.setFont(font);
	    text.setFill(Color.WHITE);
	    text.setText(buff.getClass().getSimpleName()+System.lineSeparator()+buff.getBuffDescription());
		text.setWrappingWidth(180);
		text.setLineSpacing(-4);
		StackPane pane=new StackPane();
		Rectangle rectangle=new Rectangle(180,50);
		rectangle.setFill(Color.DARKGRAY);
		rectangle.setStroke(Color.GRAY);
		rectangle.setStrokeWidth(2.5);
		rectangle.setArcWidth(30);
		pane.getChildren().addAll(rectangle,text);
		
		StackPane.setAlignment(text,Pos.CENTER);
		pane.setPrefSize(200, 50);
		this.playerBuffListBox.getChildren().add(pane);
	}
}
