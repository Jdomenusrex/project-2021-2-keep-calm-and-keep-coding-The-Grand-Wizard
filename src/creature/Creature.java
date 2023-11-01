package creature;

import javafx.beans.binding.Bindings;
import javafx.beans.binding.DoubleBinding;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.geometry.Pos;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.scene.media.AudioClip;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;

public class Creature {
	private int hp;
	private int maxHp;
	

	private int shield;
	private ImageView imageV;
	private Text textHp;
	private DoubleProperty hpPrecentProperty=new SimpleDoubleProperty();
	private StackPane hpPane;
	private AudioClip healAudio;
	private AudioClip hitAudio;
	private AudioClip shieldAudio;
	public Creature(int MaxHp,String furl) {
		this.textHp=new Text();
		this.setMaxHp(MaxHp);
		this.setHp(MaxHp);
		this.setShield(0);
		imageV=new ImageView(new Image(ClassLoader.getSystemResource(furl).toString()));
		healAudio=setUpAudio("Heal.wav");
		hitAudio=setUpAudio("HitSound.wav");
		shieldAudio=setUpAudio("Shield.wav");
		Font font = new Font("Verdana", 15);
		Font.font("Verdana", FontWeight.BLACK, 15);
	    this.getTextHp().setFont(font);
	    this.getTextHp().setFill(Color.WHEAT);
	    
	   
	}
		public int getShield() {
		return shield;
	}
			
	public void setShield(int shield) {
		this.shield=shield;
		this.setHp(this.getHp());
	}
	public void addShield(int shield) {
		if(shield>0){
			this.shieldAudio.play();
			}
		this.setShield(this.getShield()+shield);
	}
	public void setTextHp() {
		this.textHp.setText(this.hp+" / "+this.maxHp);
	}
	public int getHp() {
		return hp;
	}

	public void setHp(int newhp) {
		hp = Math.min(this.maxHp,Math.max(newhp, 0));
	
		if(this.getShield()>0) {
			this.textHp.setText(this.getHp()+" (+"+this.getShield()+") / "+this.getMaxHp());
		}
		else
			this.textHp.setText(hp+" / "+this.getMaxHp());
		this.hpPrecentProperty.set(1.0*hp/this.maxHp);	
	}

	public int getMaxHp() {
		return maxHp;
	}

	public void setMaxHp(int newMaxHp) {
		this.maxHp = newMaxHp;
	}

	public int takeDamge(int AttackPower) {
		int Damage=Math.max(0, AttackPower-this.getShield());
		this.setShield(Math.max(0, this.getShield()-AttackPower));
		this.setHp(Math.max(0,this.getHp()-Damage));
		if(Damage>0) {
			this.hitAudio.play();
		}
		return Damage;
	}
	public ImageView getImageV() {
		return imageV;
	}
	public Text getTextHp() {
		return textHp;
	}
	
	
	public void heal(int Hp) {
		if((this.getMaxHp()!=this.getHp())&&Hp>0)
		{this.setHp(this.getHp()+Hp);
		this.healAudio.play();
		}
	}
	
	public DoubleProperty getHpPrecentProperty() {
		return hpPrecentProperty;
	}
	
	public void createHpBar(Double width) {
		this.hpPane=new StackPane();
		Rectangle hpBar=this.createHpRetangle(Color.RED,width);
		Rectangle hpBarBackground=this.createHpRetangle(Color.BLACK,width);
		StackPane.setAlignment(hpBarBackground,Pos.CENTER_LEFT);
		StackPane.setAlignment(hpBar, Pos.CENTER_LEFT);
		StackPane.setAlignment(this.getTextHp(), Pos.CENTER);
		this.getTextHp().setTextAlignment(TextAlignment.CENTER);
		DoubleBinding b1=new SimpleDoubleProperty(width).multiply(this.getHpPrecentProperty());
		hpBar.widthProperty().bind(b1);
		hpPane.setPrefWidth(width);
		hpPane.setAlignment(Pos.CENTER);
		hpPane.getChildren().addAll(hpBarBackground,hpBar,this.getTextHp());
		
	
	}
	public StackPane getHpPane() {
		return hpPane;
	}
	public Rectangle createHpRetangle(Color color,Double width) {
		Rectangle hpBar=new Rectangle(width,20);
		hpBar.setFill(color);
		hpBar.setStroke(Color.BLACK);
		hpBar.setArcHeight(20);
		hpBar.setArcWidth(20);
		return hpBar;
		
	}
	public AudioClip setUpAudio(String url) {
		AudioClip audio= new AudioClip(ClassLoader.getSystemResource(url).toString());
		audio.setVolume(0.2);
		return audio;
		}
	public void muteAll() {
		this.healAudio.setVolume(0);
		this.hitAudio.setVolume(0);
		this.shieldAudio.setVolume(0);
	}
}
