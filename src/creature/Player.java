package creature;





import java.util.ArrayList;

import buff.BaseBuff;
import javafx.animation.FadeTransition;
import javafx.application.Platform;
import javafx.beans.binding.Bindings;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.util.Duration;
import logic.GameLogic;
import orb.BaseOrb;
import pane.ShowOrbPane;

public class Player extends Creature{
	

	private ShowOrbPane showOfPane;
	private ArrayList<BaseBuff> buffList=new ArrayList<>();
	private StackPane intendPane=new StackPane();
	private IntegerProperty attackValue=new SimpleIntegerProperty(0);
	private IntegerProperty healValue=new SimpleIntegerProperty(0);
	private IntegerProperty shieldValue=new SimpleIntegerProperty(0);
	private HBox intendToAttackBox;
	private HBox intendToShieldBox;
	private HBox intendToHealBox;
	public Player(int MaxHp, String furl,ShowOrbPane showOfPane) {
		super(MaxHp, furl);
		this.showOfPane=showOfPane;
		this.createHpBar(200.0);
		this.createIntendToAttackBox();
		this.createIntendToShieldBox();
		this.createintendToHealBox();
		this.setBoxVisible(0);
		
	
		

		// TODO Auto-generated constructor stub
	}
	

	public ShowOrbPane getShowOrbPane() {
		return this.showOfPane;
	}
	public void addOrbToShowOrbPane(BaseOrb orb) {
		Platform.runLater(new Runnable() {
			@Override
			public void run() {
				// TODO Auto-generated method stub
		Circle circle=new Circle(50);
		circle.setFill(new ImagePattern(new Image(ClassLoader.getSystemResource(orb.getUrl().toString()).toString())));
		Player.this.getShowOrbPane().getChildren().add(circle);
		
		circle.setOnMouseClicked(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent arg0) {
				// TODO Auto-generated method stub
				Player.this.getShowOrbPane().getChildren().remove(circle);
				orb.addOrb(1);
				GameLogic.selectOrb.remove(orb);
				
				
			}
		});
			}});
	}
	public ArrayList<BaseBuff> getBuffList() {
		return buffList;
	}

	public void setBuffList(ArrayList<BaseBuff> buffList) {
		buffList = buffList;
	}	
	public void createIntendToAttackBox() {
		this.intendToAttackBox=new HBox();
		ImageView attackPic=new ImageView(ClassLoader.getSystemResource("AttackToken1.png").toString());
		attackPic.setFitHeight(50);
		attackPic.setFitWidth(50);
		intendToAttackBox.setAlignment(Pos.CENTER);
		Text text=this.setTextForIntend(attackValue);
		this.intendToAttackBox.getChildren().addAll(attackPic,text);
		this.getIntendPane().getChildren().add(intendToAttackBox);
		this.intendToAttackBox.setVisible(false);
	}
	public void createIntendToShieldBox() {
		this.intendToShieldBox=new HBox();
		ImageView shieldPic=new ImageView(new Image(ClassLoader.getSystemResource("shield.png").toString()));
		shieldPic.setFitHeight(50);
		shieldPic.setFitWidth(50);
		intendToShieldBox.setAlignment(Pos.CENTER);
		Text text=this.setTextForIntend(shieldValue);
		this.intendToShieldBox.getChildren().addAll(shieldPic,text);
		this.getIntendPane().getChildren().add(intendToShieldBox);
		this.intendToShieldBox.setVisible(false);
	}
	public void createintendToHealBox() {
		this.intendToHealBox=new HBox();
		ImageView healPic=new ImageView(new Image(ClassLoader.getSystemResource("heal.png").toString()) );
		healPic.setFitHeight(50);
		healPic.setFitWidth(50);
		intendToHealBox.setAlignment(Pos.CENTER);
		Text text=this.setTextForIntend(healValue);
		this.intendToHealBox.getChildren().addAll(healPic,text);
		this.getIntendPane().getChildren().add(intendToHealBox);
		this.intendToHealBox.setVisible(false);
	}

	
	


	public HBox getIntendToAttackBox() {
		return intendToAttackBox;
	}


	public void setIntendToAttackBox(HBox intendToAttackBox) {
		this.intendToAttackBox = intendToAttackBox;
	}


	public HBox getIntendToShieldBox() {
		return intendToShieldBox;
	}


	public void setIntendToShieldBox(HBox intendToShieldBox) {
		this.intendToShieldBox = intendToShieldBox;
	}


	public HBox getIntendToHealBox() {
		return intendToHealBox;
	}


	public void setIntendToHealBox(HBox intendToHealBox) {
		this.intendToHealBox = intendToHealBox;
	}


	public int getAttackValue() {
		return attackValue.get();
	}



	public void setAttackValue(int attackValue) {
		this.attackValue.set(attackValue);
		this.setBoxVisible(1);
		this.setFadeAnmationForIntend(1);
		
	}



	public int getHealValue() {
		return healValue.get();
	}



	public void setHealValue(int healValue) {
		this.healValue.set(healValue);
		this.setBoxVisible(3);
		this.setFadeAnmationForIntend(3);
	}



	public int getShieldValue() {
		return shieldValue.get();
	}



	public void setShieldValue(int shieldValue) {
		this.shieldValue.set(shieldValue);
		this.setBoxVisible(2);
		this.setFadeAnmationForIntend(2);
	}
	public Text setTextForIntend(IntegerProperty iP) {
		Text text=new Text();
		text.textProperty().bind(Bindings.convert(iP));
		Font font = new Font(30);
	    Font.font("Verdana",FontWeight.EXTRA_BOLD ,30);
		text.setFont(font);
	    text.setFill(Color.SADDLEBROWN);
		return text;
	}



	




	
	public void setBoxVisible(int boxNum) {
		this.getIntendToAttackBox().setVisible(false);
		this.getIntendToShieldBox().setVisible(false);
		this.getIntendToHealBox().setVisible(false);
		switch(boxNum){
		case 1:
			this.getIntendToAttackBox().setVisible(true);
			break;
		case 2:
			this.getIntendToShieldBox().setVisible(true);
			break;
		case 3:
			this.getIntendToHealBox().setVisible(true);
			break;
	
			
		}
	}



	public void setIntendPane(StackPane intendPane) {
		this.intendPane = intendPane;
		
	}



	public StackPane getIntendPane() {
		return intendPane;
	}

	public void setFadeAnmationForIntend(int intendnumber) {
		Platform.runLater(new Runnable() {
			@Override
			public void run() {
				// TODO Auto-generated method stub
				FadeTransition fadeOut=new FadeTransition(Duration.seconds(1.2), Player.this.getSelectIntendBox(intendnumber));
				fadeOut.setFromValue(1.0);
				fadeOut.setToValue(0.0);
				fadeOut.setDelay(Duration.seconds(1));
				fadeOut.setOnFinished(new EventHandler<ActionEvent>() {

					@Override
					public void handle(ActionEvent arg0) {
						// TODO Auto-generated method stub
						Player.this.getSelectIntendBox(intendnumber).setOpacity(1.0);
						Player.this.getSelectIntendBox(intendnumber).setVisible(false);
					}
				});
				fadeOut.play();
				}
			}
		);
	}
	public Node getSelectIntendBox(int intendnumber) {
		HBox hBox;
		switch(intendnumber){
		case 1:
			hBox=Player.this.getIntendToAttackBox();
			return hBox;
			
		case 2:
			hBox=Player.this.getIntendToShieldBox();
			return hBox;
			
		case 3:
			 hBox=Player.this.getIntendToHealBox();
			 return hBox;
		default:
			
		}
		
		
		return null;
		
	}
}
	

