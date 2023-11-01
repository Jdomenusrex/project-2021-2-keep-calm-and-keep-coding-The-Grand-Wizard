package MainMenuGui;

import javafx.animation.Animation;
import javafx.animation.AnimationTimer;
import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.canvas.Canvas;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;

public class StandingWizard extends Pane{
	
	public StandingWizard() {
		/*AnimationTimer timer = new AnimationTimer() {
			@Override
			public void handle(long arg0) {
				// TODO Auto-generated method stub
				Platform.runLater(new Runnable() {

					@Override
					public void run() {
						// TODO Auto-generated method stub
						PlayAnimation();
					}
				});
			}
		};
		timer.start();*/
	}
	
	public void PlayAnimation() {
		Thread thread = new Thread(() -> {
		int i = 0;
		while(i<25) {
			this.setBackground(new Background(new BackgroundImage(new Image(ClassLoader.getSystemResource("alonso-gomez-wizard-idle-anim-"+i+".png").toString()), BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, null, new BackgroundSize(400,563,false,false,false,false))));
			try {
				Thread.sleep(50);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			i++;
			if(i==25) {
				i = 0;
			}
		}
		});
		thread.start();
	}
	
	
}

