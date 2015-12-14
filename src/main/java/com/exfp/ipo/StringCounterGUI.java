package com.exfp.ipo;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class StringCounterGUI extends Application {

	public static void main(String [] args) {
		launch(args);	
	}
	
	@Override
	public void start(Stage primaryStage) {
		primaryStage.setTitle("String Length Counter");
		
		GridPane grid = new GridPane();
		grid.setAlignment(Pos.CENTER);
		grid.setHgap(10);
		grid.setVgap(10);
		grid.setPadding(new Insets(25, 25, 25, 25));
			
		Text sceneTitle = new Text("String length counter");
		sceneTitle.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
		grid.add(sceneTitle, 0, 0, 2, 1);
		
		Label inputLabel = new Label("Enter some text:");
		grid.add(inputLabel, 0, 1);
		
		TextField inputTextField = new TextField();
		grid.add(inputTextField, 1, 1);
		
		final Text actionTarget = new Text();
		grid.add(actionTarget, 1, 6);
		
		inputTextField.textProperty().addListener(
			(observable, oldValue, newValue) -> {
				if (newValue.length() == 0) {
					actionTarget.setText("");
				} else {
					actionTarget.setFill(Color.BLUE);
					actionTarget.setText(String.format(
						"'%s' is %d characters long",
						newValue,
						newValue.length()
					));
				}
			}
		);
		
		Scene scene = new Scene(grid, 300, 275);
		primaryStage.setScene(scene);
		primaryStage.show();
	}
}
