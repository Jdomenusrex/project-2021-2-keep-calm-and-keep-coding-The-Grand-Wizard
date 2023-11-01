package MainMenuGui;

import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;

public class LeftPanel extends VBox{

	private PlayButton play = new PlayButton();
	private HowToPlayButton howtoplay = new HowToPlayButton();
	private AboutButton about = new AboutButton();
	private ExitButton exit = new ExitButton();
	
	public LeftPanel() {
		this.setPrefWidth(400);
		this.setPrefHeight(200);
		this.setSpacing(15);
		this.setPadding(new Insets(20));
		this.setAlignment(Pos.CENTER);
		
		
		//Button PlayButton = new Button("Play");
		//PlayButton.setAlignment(Pos.CENTER);
		//PlayButton.setPrefHeight(50);
		
		//Button HowToPlayButton = new Button("How to play");
		//HowToPlayButton.setAlignment(Pos.CENTER);
		
		//Button AboutButton = new Button("About");
		//AboutButton.setAlignment(Pos.CENTER);
		
		//Button ExitButton = new Button("Exit");
		//ExitButton.setAlignment(Pos.CENTER);
		
		//this.getChildren().addAll(PlayButton,HowToPlayButton,AboutButton,ExitButton);
		
		this.getChildren().addAll(play,howtoplay,about,exit);
		
	}

	public PlayButton getPlay() {
		return play;
	}

	public void setPlay(PlayButton play) {
		this.play = play;
	}

	public HowToPlayButton getHowtoplay() {
		return howtoplay;
	}

	public void setHowtoplay(HowToPlayButton howtoplay) {
		this.howtoplay = howtoplay;
	}

	public AboutButton getAbout() {
		return about;
	}

	public void setAbout(AboutButton about) {
		this.about = about;
	}

	public ExitButton getExit() {
		return exit;
	}

	public void setExit(ExitButton exit) {
		this.exit = exit;
	}

	
	
	
	
}
