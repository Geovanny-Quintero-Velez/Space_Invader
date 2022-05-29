package application;
	
import java.util.ArrayList;

import controller.MenuController;
import javafx.application.Application;
import javafx.stage.Stage;
import model.Alien;
import model.Game;
import model.Nave;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.fxml.FXMLLoader;


public class Main extends Application {
	Stage currentStage;
	Game game;
	
	
	@Override
	public void start(Stage primaryStage) {
		game=new Game();
		try {
			BorderPane root;
			FXMLLoader loader = new FXMLLoader(getClass().getResource("../ui/Menu.fxml"));
			root = (BorderPane)loader.load();
			MenuController controller = loader.getController();
			controller.setMain(this);
			
			Scene scene = new Scene(root);
			scene.getStylesheets().add(getClass().getResource("../ui/application.css").toExternalForm());
			Stage stage = new Stage();
			stage.setScene(scene);
			stage.setWidth(game.WIDTH-33);
			stage.setHeight(game.HEIGHT-10);
			stage.show();
			currentStage = stage;
			controller.actualize();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public Game getGame() {
		return game;
	}
	
	
	public void disparar() {
		game.disparar();
	}
	public void movePlayer(int i,int dir) {
		game.movePlayer(i, dir);
	}
	
	public void actualizeGame() {
		game.actualize();
	}
	
	
	public Nave getPlayer() {
		return game.getPlayer();
	}
	
	public ArrayList<Alien> getAliens() {
		return game.getAliens();
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
