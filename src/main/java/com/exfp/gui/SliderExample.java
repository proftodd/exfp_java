package com.exfp.gui;

import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.effect.SepiaTone;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class SliderExample extends Application {
	
	final Slider opacityLevel = new Slider(0, 1, 1);
	final Slider sepiaTone = new Slider(0, 1, 1);
	final Slider scaling = new Slider(0.5, 1, 1);
	final Image image = new Image(SliderExample.class.getResourceAsStream("cappuccino.jpg"));
	
	final Label opacityCaption = new Label("Opacity Level:");
	final Label sepiaCaption = new Label("Sepia Tone:");
	final Label scalingCaption = new Label("Scaling Factor:");
	
	final Label opacityValue = new Label(String.format("%.2f", opacityLevel.getValue()));
	final Label sepiaValue = new Label(String.format("%.2f", sepiaTone.getValue()));
	final Label scalingValue = new Label(String.format("%.2f", scaling.getValue()));
	
	final static Color textColor = Color.BLACK;
	final static SepiaTone sepiaEffect = new SepiaTone();
	
	@Override
	public void start(Stage stage) {
		Group root = new Group();
		Scene scene = new Scene(root, 600, 400);
		stage.setScene(scene);
		stage.setTitle("Slider Sample");
		scene.setFill(Color.BLACK);
		
		GridPane grid = new GridPane();
		grid.setPadding(new Insets(10, 10, 10, 10));
		grid.setVgap(10);
		grid.setHgap(70);
		
		final ImageView cappuccino = new ImageView(image);
		cappuccino.setEffect(sepiaEffect);
		GridPane.setConstraints(cappuccino, 0, 0);
		GridPane.setColumnSpan(cappuccino, 3);
		grid.getChildren().add(cappuccino);
		scene.setRoot(grid);
		
		opacityCaption.setTextFill(textColor);
		GridPane.setConstraints(opacityCaption, 0, 1);
		grid.getChildren().add(opacityCaption);
		
		opacityLevel.valueProperty().addListener((ov, oldValue, newValue) -> {
			cappuccino.setOpacity(newValue.doubleValue());
			opacityValue.setText(String.format("%.2f",  newValue));
		});
		GridPane.setConstraints(opacityLevel, 1, 1);
		grid.getChildren().add(opacityLevel);
		
		opacityValue.setTextFill(textColor);
		GridPane.setConstraints(opacityValue, 2, 1);
		grid.getChildren().add(opacityValue);
		
		sepiaCaption.setTextFill(textColor);
		GridPane.setConstraints(sepiaCaption, 0, 2);
		grid.getChildren().add(sepiaCaption);
		
		sepiaTone.valueProperty().addListener((ov, oldValue, newValue) -> {
			sepiaEffect.setLevel(newValue.doubleValue());
			sepiaValue.setText(String.format("%.2f", newValue));
		});
		GridPane.setConstraints(sepiaTone, 1, 2);
		grid.getChildren().add(sepiaTone);
		
		sepiaValue.setTextFill(textColor);
		GridPane.setConstraints(sepiaValue, 2, 2);
		grid.getChildren().add(sepiaValue);
		
		scalingCaption.setTextFill(textColor);
		GridPane.setConstraints(scalingCaption, 0, 3);
		grid.getChildren().add(scalingCaption);
		
		scaling.valueProperty().addListener((ov, oldValue, newValue) -> {
			cappuccino.setScaleX(newValue.doubleValue());
			cappuccino.setScaleY(newValue.doubleValue());
			scalingValue.setText(String.format("%.2f", newValue));
		});
		GridPane.setConstraints(scaling, 1, 3);
		grid.getChildren().add(scaling);
		
		scalingValue.setTextFill(textColor);
		GridPane.setConstraints(scalingValue, 2, 3);
		grid.getChildren().add(scalingValue);
		
		stage.show();
	}
	
	public static void main(String [] args) {
		launch(args);
	}
}
