package orb;


import java.util.Objects;

import creature.Creature;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

public class BaseOrb implements Comparable<BaseOrb> {
	private int maxOrb;
	private int currentOrb;
	private int cooldownTime;
	private int currentCooldown;
	private String url;
	private Text orbNumberText;
	private int effectNumer;
	
	public BaseOrb(int MaxOrb,int CurrentOrb,int CooldownTime,int CurrentCooldown,int EffectNumer,String url) {
		this.setMaxOrb(MaxOrb);
		this.setCurrentOrb(CurrentOrb);
		this.setCooldownTime(CooldownTime);
		this.setCurrentCooldown(CurrentCooldown);
		this.setEffectNumer(EffectNumer);
		this.setUrl(url);
		this.orbNumberText=new Text();
		this.setOrbNumberText();
		this.orbNumberText.setFont(new Font("Verdana", 12));
		this.orbNumberText.setFill(Color.WHITE);
	}


	public Text getOrbNumberText() {
		return orbNumberText;
	}


	public void setOrbNumberText() {
		this.orbNumberText.setText(this.getClass().getSimpleName()+System.lineSeparator()+"Number of Orb "+this.getCurrentOrb()+" / "+this.getMaxOrb());
	}





	public String getUrl() {
		return url;
	}


	public void setUrl(String url) {
		this.url = url;
	}


	public int getbasicComboValue(int numberOfOrb) {
		switch(numberOfOrb){
		case 1:
			return this.getEffectNumer();
		case 2:
			return  (int) Math.round(this.getEffectNumer()*2.5);
		case 3:
			return (int) Math.round(this.getEffectNumer()*4.5);
		default:
			return 0;
		}
		
	}

	
	public int getEffectNumer() {
		return effectNumer;
	}





	public void setEffectNumer(int effectNumer) {
		this.effectNumer = effectNumer;
	}
	
	
	public void setMaxOrb(int maxOrb) {
		this.maxOrb = maxOrb;
	}





	public void setCurrentOrb(int currentOrb) {
		this.currentOrb = Math.min(currentOrb,this.getMaxOrb());
	}





	public void setCooldownTime(int cooldownTime) {
		this.cooldownTime = cooldownTime;
	}





	public void setCurrentCooldown(int currentCooldown) {
		this.currentCooldown = currentCooldown;
	}





	public int getMaxOrb() {
		return maxOrb;
	}
	public int getCurrentOrb() {
		return currentOrb;
	}
	public int getCooldownTime() {
		return cooldownTime;
	}
	public int getCurrentCooldown() {
		return currentCooldown;
	}
	
	public boolean equals(Object orb) {
		 if (orb == this) {
	            return true;
	        }
		 return this.getClass().getSimpleName().equals(orb.getClass().getSimpleName());
	    }


	@Override
	public int compareTo(BaseOrb orb) {
		// TODO Auto-generated method stub
		int resualt=this.getClass().getSimpleName().compareTo(orb.getClass().getSimpleName());
		
		return resualt;
            
	}
	public void gainOrb(){
		if(this.currentCooldown==0) {
			this.addOrb(1);
			this.setCurrentCooldown(this.cooldownTime);
			}
		else {
			this.setCurrentCooldown(currentCooldown-1);
		}
	}
	 @Override
	    public int hashCode() {
	        return Objects.hash( );
	    }

	public void addOrb(int NumberofOrb)
	{
		this.setCurrentOrb(this.getCurrentOrb()+NumberofOrb);
		this.setOrbNumberText();
	}
	
}
