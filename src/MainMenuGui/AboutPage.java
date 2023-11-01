package MainMenuGui;

import javafx.scene.image.Image;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.BorderPane;

public class AboutPage extends BorderPane{

	BackButton back = new BackButton();
	
	public AboutPage() {
		this.setBackground(new Background(new BackgroundImage(new Image(ClassLoader.getSystemResource("Aboutpage.jpg").toString()), BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, null, new BackgroundSize(500,600,false,false,false,false))));
		this.setBottom(back);
	}

	public BackButton getBack() {
		return back;
	}

	public void setBack(BackButton back) {
		this.back = back;
	}
	
	
	
}
