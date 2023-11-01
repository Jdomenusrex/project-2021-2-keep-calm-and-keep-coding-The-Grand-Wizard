package pane;


import java.util.ArrayList;

import javafx.animation.FadeTransition;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.util.Duration;
import logic.GameLogic;

public class ShowOrbPane extends HBox {
		public ShowOrbPane() {
			this.setPadding(new Insets(250,0,0,520));
			this.setSpacing(3);
		
		
		}
		public void addOrb(Circle NewCircle) {
			
			this.getChildren().add(NewCircle);
			NewCircle.setOnMouseClicked(new EventHandler<MouseEvent>() {

				@Override
				public void handle(MouseEvent arg0) {
					// TODO Auto-generated method stub
					Platform.runLater(new Runnable() {
						public void run() {
					ShowOrbPane.this.getChildren().remove(NewCircle);
					System.out.println("ok");
						}
					});	
				}
			});
		}
		
}

