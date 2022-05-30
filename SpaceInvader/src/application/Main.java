package application;
	
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

import comparator.CompareToName;
import comparator.CompareToScore;
import controller.ClassificationController;
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
	ArrayList<Nave> top = new ArrayList<>();	
	
	
	@Override
	public void start(Stage primaryStage) {
		
		deserialize();
		Collections.sort(top, new CompareToName());
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
		Collections.sort(top, new CompareToScore());
		serialize();
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("../ui/Classification.fxml"));
			BorderPane loginR = (BorderPane)loader.load();
			ClassificationController controller = loader.getController();
			controller.setMain(this);
			controller.showTop();
			Stage stage = currentStage;
			Scene scene = new Scene(loginR);
			stage.setScene(scene);
			stage.setWidth(600);
			stage.show();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	

	public ArrayList<Nave> getTop() {
		return top;
	}

	public void setTop(ArrayList<Nave> top) {
		this.top = top;
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
			
			e.printStackTrace();
		}
	}
	
	public Nave binarySearch(int begin, int end, String toFind) {
		int mid =(begin+end)/2;
		if(!(begin <= end)) {
			return null; 
		}else if(top.size() == 0){
			return null;
		}else if(toFind.compareTo(top.get(mid).getName()) == 0) {
			return top.get(mid);
		}else if(mid == 0 || mid ==top.size()-1) {
			return null;
		}else if(toFind.compareTo(top.get(mid).getName())<0) {
			return binarySearch(begin, mid-1, toFind);
		}else if(toFind.compareTo(top.get(mid).getName())>0) {
			return binarySearch(mid+1, end, toFind);
		}
		return null;
	}
	
	public void deserialize() {
		try {
			File file = new File(".\\src\\files\\top.txt");
			
			FileReader fr;
			fr = new FileReader(file);
			
			BufferedReader input = new BufferedReader(fr);
			while (input.ready()) {
				
				String line = input.readLine();
				
				String [] data = line.split(";");
				
				String name = data[0];
				int score = Integer.parseInt(data[1]);
				
				top.add(new Nave(name, score));
				
			}
			input.close();
			fr.close();
		} catch (IOException e) {
			
			e.printStackTrace();
		}
		
	}
	
	public void serialize() {
		try {
			File file = new File(".\\src\\files\\top.txt");
			FileWriter fw;
			fw = new FileWriter(file);
			BufferedWriter output = new BufferedWriter(fw);
			for(int j = 0; j< top.size(); j++) {
				output.write(top.get(j).getName() +";"+ top.get(j).getPoints()+"\n");
			}
			output.close();
			fw.close();
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
