package application;
	
import java.io.IOException;
import java.util.ArrayList;

import controller.LoginController;
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
		try {
			BorderPane root;
			FXMLLoader loader = new FXMLLoader(getClass().getResource("../ui/Login.fxml"));
			root = (BorderPane)loader.load();
			LoginController controller = loader.getController();
			controller.setMain(this);
			
			Scene scene = new Scene(root);
			scene.getStylesheets().add(getClass().getResource("../ui/application.css").toExternalForm());
			Stage stage = new Stage();
			stage.setScene(scene);
			stage.show();
			currentStage = stage;
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public void showClassification() {
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("../ui/Classification.fxml"));
			BorderPane loginR = (BorderPane)loader.load();
			LoginController controller = loader.getController();
			controller.setMain(this);
			
			Stage stage = currentStage;
			Scene scene = new Scene(loginR);
			stage.setScene(scene);
			stage.show();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void showGame(String name) {
		game=new Game(name);
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("../ui/Menu.fxml"));
			BorderPane gameRoot = (BorderPane)loader.load();
			MenuController controller = loader.getController();
			controller.setMain(this);
			
			Scene scene = new Scene(gameRoot);
			scene.getStylesheets().add(getClass().getResource("../ui/application.css").toExternalForm());
			Stage stage = currentStage;
			stage.setScene(scene);
			stage.setWidth(game.WIDTH-33);
			stage.setHeight(game.HEIGHT-10);
			stage.show();
			controller.actualize();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public Game getGame() {
		return game;
	}
	
	public void setGame(Game game) {
		this.game = game;
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
