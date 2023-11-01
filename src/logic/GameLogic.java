package logic;


import java.lang.annotation.Target;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Stack;

import buff.BaseBuff;
import buff.BuffHHa;
import buff.BuffHHd;
import buff.BuffaaH;
import buff.Buffaad;
import buff.BuffddH;
import buff.Buffdda;

import creature.Creature;
import creature.Monster;
import creature.Player;
import javafx.animation.FadeTransition;
import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import javafx.util.Duration;
import orb.AttackOrb;
import orb.BaseOrb;
import orb.DefenseOrb;
import orb.HealOrb;
import pane.EndTurnPane;
import pane.MonsterPane;
import pane.OrbPane;
import pane.PlayerPane;
import pane.ShowOrbPane;
import scene.BattleScene;


public class GameLogic {
	public static Map<BaseBuff,Boolean> allPlayerBuffThatActivate=new HashMap<BaseBuff, Boolean>()
	{{
	    put(new Buffaad(),false);
	    put(new Buffdda(),false);
	    put(new BuffHHd(), false);
	    put(new BuffHHa(), false);
	    put(new BuffaaH(), false);
	    put(new BuffddH(),false);
	}};
	public static ArrayList<BaseBuff> allplayerBuff=new ArrayList<>(Arrays.asList(new Buffaad(),new Buffdda(),new BuffHHd(),new BuffHHa(),new BuffaaH(),new BuffddH()));
	public static ArrayList<BaseOrb> holdingOrb;
	public static ArrayList<BaseOrb> selectOrb;
	public static Player player;
	public static Monster monster;
	public static int turnNumber;
	public static BorderPane root;
	public static Map<BaseOrb, Integer> orbMap;
	public static Map<String, Integer> orbNameMap; 
	public static Boolean isGameEnd;
	public static Boolean isPlayerTurn;
	public static Scene mainMenuSecen;
	public static Stage stage;
	private static int a;
	public static int attack(int AttackDamge) {
	
		if(GameLogic.allPlayerBuffThatActivate.get(new Buffaad())==true) {
		AttackDamge=(int) Math.round(AttackDamge*1.2);
	}	
	
	return AttackDamge;
	
	}
	public static void startNewPlayerTurn() {
		if(!isGameEnd) {
		GameLogic.isPlayerTurn=true;
		if(GameLogic.turnNumber>1) {
		showTurn("Player Turn");}
	
		GameLogic.player.setShield(0);
		for (BaseBuff buff:GameLogic.allPlayerBuffThatActivate.keySet()) {
			GameLogic.allPlayerBuffThatActivate.replace(buff, false);
		}
		for (BaseOrb orb:holdingOrb) {
			orb.gainOrb();
		}
		GameLogic.monster.showIntend(GameLogic.turnNumber);
		
		}
	}
	public static int addShield(int shield) {
		
		if(GameLogic.allPlayerBuffThatActivate.get(new Buffdda())==true) {
			shield=(int) Math.round(shield*1.2);
		}	
		return shield;
	
		
	}
	public static int heal(int hp) {
		
		return hp;
		
	}
	public static void healFromComboEffect() {
		int Hp=0;
		if(!isGameEnd) {
		if(GameLogic.allPlayerBuffThatActivate.get(new BuffHHd())==true) {
			Hp+=GameLogic.orbMap.get(new DefenseOrb())*4;
		}	
		if(GameLogic.allPlayerBuffThatActivate.get(new BuffHHa())==true) {
		
			Hp+=GameLogic.orbMap.get(new AttackOrb())*4;
		}
		
		GameLogic.player.heal(Hp);}
	}
	public static void effectFromHeal(int Hp) {
	if(!isGameEnd) {	
		if(GameLogic.allPlayerBuffThatActivate.get(new BuffaaH())==true) {
	
			GameLogic.monster.takeDamge(Hp);
		}
		if(GameLogic.allPlayerBuffThatActivate.get(new BuffddH())==true) {
		
			GameLogic.player.addShield(Hp);
		}}
	}
	
	
	
	public static  Pane setBattleSecene() throws Exception {
		setUp();
		BorderPane root =new BorderPane();
		root.setPadding(new Insets(0, 0,0,0));
		GameLogic.player=new Player(75,"alonso-gomez-wizard-idle-anim-0.png",new ShowOrbPane());
		GameLogic.monster=new Monster(150,"dc9a3wp-3ea69672-7df1-4477-b47c-f284266eb2c3-0.png");
		PlayerPane player1pane=new PlayerPane(GameLogic.player);
		MonsterPane monsterpane=new MonsterPane(GameLogic.monster);
		OrbPane testorb=new OrbPane(player1pane.getPlayer());
		EndTurnPane endTurn=new EndTurnPane();
		root.setBackground(new Background(new BackgroundImage(new Image(ClassLoader.getSystemResource("Backgroud1.png").toString()),BackgroundRepeat.NO_REPEAT,BackgroundRepeat.NO_REPEAT, null,new BackgroundSize(1400, 800, false, false, false, false))));
		root.setPrefSize(1400, 800);
		root.setLeft(player1pane);
		root.setRight(monsterpane);
		root.setBottom(testorb);
		testorb.getChildren().add(endTurn);
		root.getChildren().add(player1pane.getPlayer().getShowOrbPane());
		BorderPane.setAlignment(player1pane.getPlayer().getShowOrbPane(),Pos.TOP_CENTER);
		setUpForGameEnd("You win!",GameLogic.monster);
		setUpForGameEnd("You lose!",GameLogic.player);
		GameLogic.root=root;
		return root;
	}
	public static void startMonsterTurn() {
		// TODO Auto-generated method stub
		if(!isGameEnd) {
		showTurn("Monster Trun");
		GameLogic.monster.setShield(0);
		 Thread thread = new Thread(() -> {
			 	try {
			 		Thread.sleep(1000);
					Thread actionThread=GameLogic.monster.getActionThread(player, turnNumber);
					actionThread.start();
					actionThread.join();
					turnNumber++;
					Thread.sleep(1000);
					GameLogic.startNewPlayerTurn();
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			 	 });
		thread.start();}
		
		
	}
	public static void showTurn(String string){
		
		Thread thread = new Thread(() -> {
			
			Text text=new Text(string);
			Font font = new Font("Verdana", 30);
		    text.setFont(font);
		    text.setFill(Color.LIGHTGREEN);
		   
				Platform.runLater(new Runnable() {

					@Override
					public void run() {
						// TODO Auto-generated method stub
						
						(GameLogic.root).setCenter(text);
						FadeTransition fadeOut=new FadeTransition(Duration.seconds(2),text);
						fadeOut.setDelay(Duration.seconds(1));
						fadeOut.setFromValue(1.0);
						fadeOut.setToValue(0.0);
						fadeOut.play();
						fadeOut.setOnFinished(new EventHandler<ActionEvent>() {

							@Override
							public void handle(ActionEvent arg0) {
								// TODO Auto-generated method stub
								BorderPane.clearConstraints(text);
							}
						});
					}
					});	
					
				});
			
			
			
		thread.start();
	}

public static void setUpForGameEnd(String text,Creature target) {
	target.getHpPrecentProperty().addListener((ChangeListener<? super Number>) new ChangeListener<Object>(){
        
		@Override
		public void changed(ObservableValue<? extends Object> arg0, Object arg1, Object arg2) {
			// TODO Auto-generated method stub
			
			if((Double)arg2==0.0) {
				try {
					GameLogic.isGameEnd=true;
					GameLogic.player.muteAll();
					GameLogic.monster.muteAll();
					Thread.sleep(500);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			Platform.runLater(new Runnable() {
				
				@Override
				public void run() {
					// TODO Auto-generated method stub
					GameLogic.root.getLeft().setVisible(false);
					GameLogic.root.getRight().setVisible(false);
					GameLogic.root.getBottom().setVisible(false);
					Pane endGamePane=GameLogic.createGameEndpane(text);
					BorderPane.setAlignment(endGamePane,Pos.CENTER_LEFT);
					GameLogic.root.setCenter(endGamePane);
					}
				}
			);
			
		}
      };
	
	
});
}

public static Pane createGameEndpane(String text) {
	
	VBox root =new VBox();
	Text winningText=new Text(text);
	Font font = new Font("Verdana", 150);
	winningText.setFont(font);
	winningText.setFill(Color.RED);
	Text returnToMainMenu=new Text("Return to  mainmenu");
	Font font2 = new Font("Verdana", 30);
	returnToMainMenu.setFont(font2);
	Text Quit=new Text("Quit");
	Quit.setFont(font2);
	returnToMainMenu.setFill(Color.RED);
	Quit.setFill(Color.RED);
	winningText.setTextAlignment(TextAlignment.CENTER);
	root.setAlignment(Pos.CENTER);
	root.setSpacing(20);
	root.setPadding(new Insets(0,0,0,0));
	Quit.setOnMouseClicked(new EventHandler<Event>() {
	    @Override
		public void handle(Event arg0) {
			// TODO Auto-generated method stub
			 Platform.exit();
		        System.exit(0);
		}
	});
	returnToMainMenu.setOnMouseClicked(new EventHandler<Event>() {

		@Override
		public void handle(Event arg0) {
			// TODO Auto-generated method stub
			GameLogic.stage.setScene(GameLogic.mainMenuSecen);
		}
	});
	root.getChildren().addAll(winningText,returnToMainMenu,Quit);
	return root;
	
}

public static void endTurn() {
	GameLogic.isPlayerTurn=false;
	Collections.sort(GameLogic.selectOrb);
	

	    for(BaseOrb orb : new HashSet<>(GameLogic.selectOrb)) {
	        GameLogic.orbMap.put(orb, Collections.frequency(GameLogic.selectOrb,orb));
	        GameLogic.orbNameMap.put(orb.getClass().getSimpleName(), Collections.frequency(GameLogic.selectOrb,orb));
	    }
	
	    for(BaseBuff x:GameLogic.allplayerBuff) {
	    	
	    	if(GameLogic.orbNameMap.equals(x.getOrbtobuff())&&!GameLogic.player.getBuffList().contains(x)) {
	  
	    		GameLogic.player.getBuffList().add(x);
	    		
	    		((PlayerPane)GameLogic.root.getLeft()).upDatePlayerBuffListBox();
	    }
	    
	    }
	   
	    
	    GameLogic.player.getShowOrbPane().getChildren().clear();
	   
	    	
	    for(BaseBuff buff:GameLogic.player.getBuffList()) {
	    buff.reslove(GameLogic.orbNameMap);
	    
	    }
	    Thread playerthread = new Thread(() -> {
	    for(BaseOrb orb:GameLogic.orbMap.keySet()) {
	    
	    	if (orb instanceof AttackOrb) {
	    		Thread attackThred=getAttackThred(GameLogic.attack(orb.getbasicComboValue(GameLogic.orbMap.get(orb))), monster);
	    		attackThred.start();
	    		try {
					attackThred.join();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
	    	}
	    	else if (orb instanceof DefenseOrb) {
	    		Thread shieldThred=getShieldThred(GameLogic.addShield(orb.getbasicComboValue(GameLogic.orbMap.get(orb))),player);
	    		shieldThred.start();
	    		try {
					shieldThred.join();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
	    	}
	    	else if (orb instanceof HealOrb) {
	    		Thread healThred=getHealThred(GameLogic.heal(orb.getbasicComboValue(GameLogic.orbMap.get(orb))),player);
	    		healThred.start();
	    		try {
					healThred.join();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
	    	}
}
	    try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    GameLogic.healFromComboEffect();
	    GameLogic.selectOrb.clear();
	    GameLogic.orbMap.clear();
	    GameLogic.orbNameMap.clear();
		GameLogic.player.setBoxVisible(0);
		GameLogic.startMonsterTurn();});
	    playerthread.start();

}

 public static Thread getAttackThred(int AttackDamge,Creature target) {
	 Thread thread = new Thread(() -> {
		 	try {
				
				GameLogic.player.setAttackValue(AttackDamge);
				Thread.sleep(1500);
				target.takeDamge(AttackDamge);
				Thread.sleep(600);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	 });
		return thread;
}
 
 public static Thread getShieldThred(int shield,Creature target) {
	 Thread thread = new Thread(() -> {
		 	try {
				
				GameLogic.player.setShieldValue(shield);
				Thread.sleep(1500);
				GameLogic.player.addShield(shield);
				Thread.sleep(600);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		 	 });
		return thread;
}
 
 
public static Thread getHealThred(int hp,Creature target) {
	 Thread thread = new Thread(() -> {
		 	try {
				
				GameLogic.player.setHealValue(hp);
				Thread.sleep(1500);
				target.heal(hp);
				GameLogic.effectFromHeal(hp);
				Thread.sleep(600);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	 });
		return thread;
	 }
public static void showBattleStart() {
	

	Platform.runLater(new Runnable() {

		@Override
		public void run() {
			// TODO Auto-generated method stub
			Text text=new Text("Battle Start!");
			Font font = new Font("Verdana", 80);
			text.setFont(font);
			text.setFill(Color.AZURE);
			GameLogic.root.setCenter(text);
			BorderPane.setAlignment(text, Pos.CENTER);
			FadeTransition fadeOut=new FadeTransition(Duration.seconds(2),text);
			fadeOut.setDelay(Duration.seconds(1));
			fadeOut.setFromValue(1.0);
			fadeOut.setToValue(0.0);
			fadeOut.play();
			fadeOut.setOnFinished(new EventHandler<ActionEvent>() {

				@Override
				public void handle(ActionEvent arg0) {
					// TODO Auto-generated method stub
					BorderPane.clearConstraints(text);
				}
			});
		}
		}
	);	
}
 public static void setUp() {
	 holdingOrb=new ArrayList<BaseOrb>();
	 selectOrb=new ArrayList<BaseOrb>();
	 orbMap = new HashMap<>();
	 orbNameMap = new HashMap<>();
	 isGameEnd=false;
	 isPlayerTurn=true;
	 turnNumber=1;
 }

}



