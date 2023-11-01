package gui;




import MainMenuGui.Mainmenu;
import creature.Monster;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.BorderPane;
import javafx.scene.media.AudioClip;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import logic.GameLogic;
import pane.EndTurnPane;
import pane.MonsterPane;
import pane.OrbPane;
import pane.PlayerPane;
import scene.BattleScene;

public class App extends Application {
	
	AudioClip BG = new AudioClip(ClassLoader.getSystemResource("BGMusic.wav").toString());
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		launch(args);
	}

	@Override
	public void start(Stage stage) throws Exception {
		// TODO Auto-generated method stub
		
		
			
		Mainmenu root = new Mainmenu();
		Scene scene = new Scene(root,1400,800);
		GameLogic.mainMenuSecen=scene;
		GameLogic.stage=stage;
		stage.setResizable(false);
		stage.setScene(scene);
		stage.setTitle("The Grand Wizard");
		stage.show();
		stage.setOnCloseRequest(new EventHandler<WindowEvent>() {
		    @Override
		    public void handle(WindowEvent t) {
		        Platform.exit();
		        System.exit(0);
		    }
		});
		BG.setVolume(0.1);
		BG.setCycleCount(10000); 
		BG.play();
		
		
		root.getLeftpanel().getPlay().setOnMouseClicked(new EventHandler<MouseEvent>()  {
			@Override
			public void handle(MouseEvent arg0) {
				// TODO Auto-generated method stub
				AudioClip SwordDraw = new AudioClip(ClassLoader.getSystemResource("SwordDraw.wav").toString());
				SwordDraw.setVolume(0.1);
				SwordDraw.play();
				try {
					stage.setScene(new BattleScene());
					GameLogic.showBattleStart();
					GameLogic.startNewPlayerTurn();
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}
		});
		
	}

	public AudioClip getBG() {
		return BG;
	}

	public void setBG(AudioClip bG) {
		BG = bG;
	}

	
	
}
