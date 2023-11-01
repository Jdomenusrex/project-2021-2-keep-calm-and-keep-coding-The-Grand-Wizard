package scene;
import javafx.application.Platform;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import logic.GameLogic;

public class BattleScene extends Scene{

	public BattleScene() throws Exception {
		super(GameLogic.setBattleSecene());
		// TODO Auto-generated constructor stub
		
	}
	
}
