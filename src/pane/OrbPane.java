package pane;

import java.util.ArrayList;

import creature.Player;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.BorderWidths;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import logic.GameLogic;


public class OrbPane extends HBox {
		public OrbPane(Player player) {
		this.setPadding(new Insets(0,0,0,450));
		this.setSpacing(2);
		for(int i=0;i<3;i++) {
			createOrbBox(i);
		}
		}
		
		
		public void createOrbBox(int orbNum) {
			VBox orbBox=new VBox();
			Circle orbCircle=new Circle(50);
			orbCircle.setFill(new ImagePattern(new Image(ClassLoader.getSystemResource(GameLogic.holdingOrb.get(orbNum).getUrl().toString()).toString())));
			orbCircle.autosize();
			orbBox.setBackground(new Background(new BackgroundFill(Color.DARKGRAY,new CornerRadii(0),new Insets(0))));
			orbCircle.setOnMouseClicked(new EventHandler<Event>() {
				@Override
				public void handle(Event arg0) {
					// TODO Auto-generated method stub
					if(GameLogic.holdingOrb.get(orbNum).getCurrentOrb()>0&&GameLogic.selectOrb.size()<3&&GameLogic.isPlayerTurn) {
						GameLogic.player.addOrbToShowOrbPane(GameLogic.holdingOrb.get(orbNum));
						GameLogic.selectOrb.add(GameLogic.holdingOrb.get(orbNum));
						GameLogic.holdingOrb.get(orbNum).addOrb(-1);
				}
			};
		});
			VBox.setMargin(orbCircle,new Insets(20,20,0,20));
			orbBox.getChildren().addAll(orbCircle,GameLogic.holdingOrb.get(orbNum).getOrbNumberText());
			orbBox.setBorder(new Border(new BorderStroke(Color.GRAY,BorderStrokeStyle.SOLID,null,new BorderWidths(4.5))));
			this.getChildren().add(orbBox);
		}
		
	}


