package pane;

import creature.Monster;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.image.Image;
import javafx.scene.layout.VBox;
import javafx.scene.text.TextAlignment;
import logic.GameLogic;




public class MonsterPane extends VBox {
	private Monster monster;
	public MonsterPane(Monster monster) {
		this.monster=monster;
		this.setPadding(new Insets(250,50, 0, 0));
		monster.getImageV().setFitHeight(300);
		monster.getImageV().setFitWidth(400);
		this.setAlignment(Pos.CENTER);
		
		this.getChildren().addAll(monster.getIntendPane(),monster.getImageV(),monster.getHpPane());
		this.runMonsterAnimation();
		
	}
	public Monster getMonster() {
		return monster;
	}
	public void setMonster(Monster monster) {
		this.monster = monster;
	}
	public void runMonsterAnimation() {
		Thread thread = new Thread(() -> {
			int i = 0;
			while(true) {
				this.getMonster().getImageV().setImage(new Image(ClassLoader.getSystemResource("dc9a3wp-3ea69672-7df1-4477-b47c-f284266eb2c3-"+i+".png").toString()));
				try {
					Thread.sleep(55);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				i++;
				if(i==74) {
					i=0;
				}
				
			}
			});
			thread.start();
	}
}

