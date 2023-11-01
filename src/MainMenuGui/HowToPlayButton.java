package MainMenuGui;

import javafx.event.EventHandler;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.Pane;
import javafx.scene.media.AudioClip;

public class HowToPlayButton extends Pane{

	public HowToPlayButton() {
		this.setPrefWidth(240);
		this.setPrefHeight(135);
		this.setBackground(new Background(new BackgroundImage(new Image(ClassLoader.getSystemResource("Howtoplay1.jpg").toString()), BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, null, new BackgroundSize(240,135,false,false,false,false))));
		this.setOnMouseEntered(new EventHandler<MouseEvent>()  {
			@Override
			public void handle(MouseEvent arg0) {
				// TODO Auto-generated method stub
				drawenter();
			}
		});
		this.setOnMouseExited(new EventHandler<MouseEvent>()  {
			@Override
			public void handle(MouseEvent arg0) {
				// TODO Auto-generated method stub
				drawout();
			}
		});
		}
		
	public void drawenter() {
		this.setBackground(new Background(new BackgroundImage(new Image(ClassLoader.getSystemResource("Howtoplay2.jpg").toString()), BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, null, new BackgroundSize(240,135,false,false,false,false))));
		}

	public void drawout() {
		this.setBackground(new Background(new BackgroundImage(new Image(ClassLoader.getSystemResource("Howtoplay1.jpg").toString()), BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, null, new BackgroundSize(240,135,false,false,false,false))));
		}
	
}
