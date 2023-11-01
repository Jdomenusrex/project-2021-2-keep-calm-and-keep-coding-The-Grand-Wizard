package MainMenuGui;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.BorderPane;
import javafx.scene.media.AudioClip;

public class Mainmenu extends BorderPane{

	private LeftPanel leftpanel = new LeftPanel();
	private StandingWizard wizard = new StandingWizard();
	private AboutPage aboutpage = new AboutPage();
	private HowToPlayPage howtoplaypage = new HowToPlayPage();
	
	public Mainmenu() {
		this.setPadding(new Insets(0));
		this.setLeft(leftpanel);
		this.setCenter(wizard);
		wizard.PlayAnimation();
		ImageView title=new ImageView(new Image(ClassLoader.getSystemResource("IMG-5037.jpg").toString()));
		title.setFitWidth(1400);
		this.setTop(title);
		this.setBackground(new Background(new BackgroundImage(new Image(ClassLoader.getSystemResource("Background.jpg").toString()), BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, null, new BackgroundSize(1400,800,false,false,false,false))));
		
		this.leftpanel.getHowtoplay().setOnMouseClicked(new EventHandler<MouseEvent>()  {
			@Override
			public void handle(MouseEvent arg0) {
				// TODO Auto-generated method stub
				AudioClip SwordDraw = new AudioClip(ClassLoader.getSystemResource("SwordDraw.wav").toString());
				SwordDraw.setVolume(0.1);
				SwordDraw.play();
				OpenHowToPlayPage();
			}
		});
		this.leftpanel.getAbout().setOnMouseClicked(new EventHandler<MouseEvent>()  {
			@Override
			public void handle(MouseEvent arg0) {
				// TODO Auto-generated method stub
				AudioClip SwordDraw = new AudioClip(ClassLoader.getSystemResource("SwordDraw.wav").toString());
				SwordDraw.setVolume(0.1);
				SwordDraw.play();
				OpenAboutPage();
			}
		});
		
		this.aboutpage.getBack().setOnMouseClicked(new EventHandler<MouseEvent>()  {
			@Override
			public void handle(MouseEvent arg0) {
				// TODO Auto-generated method stub
				AudioClip SwordDraw = new AudioClip(ClassLoader.getSystemResource("SwordDraw.wav").toString());
				SwordDraw.setVolume(0.1);
				SwordDraw.play();
				ReturnPage();
			}
		});
		
		this.howtoplaypage.getBack().setOnMouseClicked(new EventHandler<MouseEvent>()  {
			@Override
			public void handle(MouseEvent arg0) {
				// TODO Auto-generated method stub
				AudioClip SwordDraw = new AudioClip(ClassLoader.getSystemResource("SwordDraw.wav").toString());
				SwordDraw.setVolume(0.1);
				SwordDraw.play();
				ReturnPage();
			}
		});
		
	}

	public void ReturnPage() {
		this.setCenter(wizard);
	}
	
	public void OpenAboutPage() {
		this.setCenter(aboutpage);
	}
	public void OpenHowToPlayPage() {
		this.setCenter(howtoplaypage);
	}
	
	public LeftPanel getLeftpanel() {
		return leftpanel;
	}

	public void setLeftpanel(LeftPanel leftpanel) {
		this.leftpanel = leftpanel;
	}

	public StandingWizard getWizard() {
		return wizard;
	}

	public void setWizard(StandingWizard wizard) {
		this.wizard = wizard;
	}
	
	
	
}
