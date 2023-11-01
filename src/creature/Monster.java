package creature;



import javafx.beans.binding.Bindings;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import logic.GameLogic;

public class Monster extends Creature{
	
	public Monster(int MaxHp, String furl) {
		super(MaxHp, furl);
		this.createHpBar(400.0);
		createIntendBoxForMove1();
		createIntendBoxForMove2();
		createIntendBoxForMove3();
		// TODO Auto-generated constructor stub
	}
	private StackPane intendPane=new StackPane();
	private IntegerProperty attackValueForMove1=new SimpleIntegerProperty(15);
	private IntegerProperty attackValueForMove2=new SimpleIntegerProperty(10);
	private IntegerProperty shieldValueForMove1=new SimpleIntegerProperty(8);
	private BooleanProperty bPForIntend1=new SimpleBooleanProperty(true);
	private HBox intendBoxForMove1;
	private HBox intendBoxForMove2;
	private HBox intendBoxForMove3;
	private int percentageMultiplierOfEachMoveValue=100;
	public StackPane getIntendPane() {
		return intendPane;
	}
	public void setIntendPane(StackPane intendPane) {
		this.intendPane = intendPane;
	}
	
	public int getAttackValueForMove1() {
		return attackValueForMove1.get();
	}
	public void setAttackValueForMove1(int attackValueForMove1) {
		this.attackValueForMove1.set(attackValueForMove1);;
	}
	public int getAttackValueForMove2() {
		return attackValueForMove2.get();
	}
	public void setAttackValueForMove2(int attackValueForMove2) {
		this.attackValueForMove2.set(attackValueForMove2);;
	}
	public int getShieldValueForMove1() {
		return shieldValueForMove1.get();
	}
	public void setShieldValueForMove1(int shieldValueForMove1) {
		this.shieldValueForMove1.set(shieldValueForMove1);
	}
	
	public int getPercentageMultiplierOfEachMoveValue() {
		return percentageMultiplierOfEachMoveValue;
	}
	public void setPercentageMultiplierOfEachMoveValue(int percentageMultiplierOfEachMoveValue) {
		this.percentageMultiplierOfEachMoveValue = percentageMultiplierOfEachMoveValue;
	}
	public Thread getActionThread(Player player,int turnNuber) {
		Thread thread = new Thread(() -> {
			try {
				Thread.sleep(1000);
				switch (turnNuber%3) {
				case 1: {
					player.takeDamge(this.getAttackValueForMove1());
					this.bPForIntend1.set(false);
					Thread.sleep(1000);
					this.addShield(this.getShieldValueForMove1());
					this.intendBoxForMove1.setVisible(false);
					this.bPForIntend1.set(true);
					break;
				}
				case 2:{
					this.heal(player.takeDamge(this.getAttackValueForMove2()));
					this.intendBoxForMove2.setVisible(false);
					break;}
				case 0:{
					player.takeDamge(player.getMaxHp()-player.getHp());
					this.setNewValue();
					this.intendBoxForMove3.setVisible(false);
					break;}
				}
				
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	
	
	 });
	return thread;	
	}
	public void showIntend(int turnNuber) {
		
		switch (turnNuber%3) {
		case 1: {
			this.intendBoxForMove1.setVisible(true);
			break;
		}
		case 2:{
			this.intendBoxForMove2.setVisible(true);
			break;}
		case 0:{
			this.intendBoxForMove3.setVisible(true);
			break;}
		
		}
		
		}
	public void setNewValue() {
		this.setPercentageMultiplierOfEachMoveValue(percentageMultiplierOfEachMoveValue+15);
		this.setAttackValueForMove1((int) Math.round(this.getAttackValueForMove1()*(this.getPercentageMultiplierOfEachMoveValue()/100.0)));
		this.setShieldValueForMove1((int) Math.round(this.getShieldValueForMove1()*(this.getPercentageMultiplierOfEachMoveValue()/100.0)));
		this.setAttackValueForMove2((int) Math.round(this.getAttackValueForMove2()*(this.getPercentageMultiplierOfEachMoveValue()/100.0)));
	}
	
	public void createIntendBoxForMove1() {
		this.intendBoxForMove1=new HBox();
		ImageView attackPic=new ImageView(new Image(ClassLoader.getSystemResource("AttackToken1.png").toString()));
		ImageView shieldPic=new ImageView(new Image(ClassLoader.getSystemResource("shield.png").toString()));
		attackPic.setFitHeight(50);
		attackPic.setFitWidth(50);
		shieldPic.setFitWidth(50);
		shieldPic.setFitHeight(50);
		Text attackText=getTextForIntend(this.attackValueForMove1);
		Text shieldText=getTextForIntend(shieldValueForMove1);
		intendBoxForMove1.getChildren().addAll(attackPic,attackText,shieldPic,shieldText);
		this.setUpIntendForBoxForMove1(attackPic,attackText);
		this.getIntendPane().getChildren().add(intendBoxForMove1);
		intendBoxForMove1.setAlignment(Pos.CENTER);
		
		this.intendBoxForMove1.setVisible(false);
	}
	public Text getTextForIntend(IntegerProperty iP) {
		Text text=new Text();
		text.textProperty().bind(Bindings.convert(iP));
		Font font = new Font("Verdana", 30);
	    text.setFont(font);
	    text.setFill(Color.SADDLEBROWN);
		return text;
	}
	public void createIntendBoxForMove2() {
		this.intendBoxForMove2=new HBox();
		ImageView attackPic=new ImageView(new Image(ClassLoader.getSystemResource("AttackTokenWithHeal.png").toString()));
		attackPic.setFitHeight(50);
		attackPic.setFitWidth(50);
		Text attackText=getTextForIntend(this.attackValueForMove2);
		intendBoxForMove2.setAlignment(Pos.CENTER);
		this.intendBoxForMove2.getChildren().addAll(attackPic,attackText);
		this.getIntendPane().getChildren().add(this.intendBoxForMove2);
		this.intendBoxForMove2.setVisible(false);
	}
	public void createIntendBoxForMove3() {
		this.intendBoxForMove3=new HBox();
		ImageView attackPic=new ImageView(new Image(ClassLoader.getSystemResource("buff.png").toString()));
		attackPic.setFitHeight(50);
		attackPic.setFitWidth(50);
		intendBoxForMove3.setAlignment(Pos.CENTER);
		IntegerProperty iP=new SimpleIntegerProperty();
		iP.bind( (GameLogic.player.getHpPrecentProperty().multiply(-75)).add(75));
		Text attackText=getTextForIntend(iP);
		this.intendBoxForMove3.getChildren().addAll(attackPic,attackText);
		this.getIntendPane().getChildren().add(intendBoxForMove3);
		this.intendBoxForMove3.setVisible(false);
	}
	public void setUpIntendForBoxForMove1(Node node1,Node node2) {
		this.bPForIntend1.addListener((ChangeListener<? super Boolean>) new ChangeListener<Object>(){
	        
			@Override
			public void changed(ObservableValue<? extends Object> arg0, Object arg1, Object arg2) {
				// TODO Auto-generated method stub
				
				if((Boolean)arg2==false) {
					node1.setVisible(false);
					node2.setVisible(false);
				}
				else {
					node1.setVisible(true);
					node2.setVisible(true);
				}	
			
			}});
	}
}
