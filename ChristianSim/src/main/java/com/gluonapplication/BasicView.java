package com.gluonapplication;

import com.gluonhq.charm.glisten.control.AppBar;
import com.gluonhq.charm.glisten.control.Icon;
import com.gluonhq.charm.glisten.mvc.View;
import com.gluonhq.charm.glisten.visual.MaterialDesignIcon;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class BasicView extends View {
    //Variables
    boolean gameStart = false;
    int energy = 100;
    double money = 0.00;
    double wage = 7.00;
    int favorGod = 0;
    int daysPassed = 0;
    String status = "";
    int bibleKnowledge = 0;
    int soulsSaved = 0;
	
    //Constants
    final String defaultStatus = "Blessed & Highly Favored!";
	
    //Layouts
    VBox playerUI = new VBox(10);
    VBox menuUI = new VBox(10);
    VBox evangalismUI = new VBox(10);
    
    public BasicView(String name) {
        super(name);
        
        //Intro VBox
        Label introText = new Label("Welcome To Christian Simulator!");
        Button playButton = new Button("Play");
              
        
        VBox startVBox = new VBox(15.0, introText, playButton);
        startVBox.setAlignment(Pos.CENTER);
        
        setCenter(startVBox);
        
        //Game VBox
        Label getReady = new Label("Get Ready!");
        VBox gameVBox = new VBox(15.0, getReady);
        gameVBox.setAlignment(Pos.CENTER);
        
        //Main Scene
        //Labels & Buttons
            Label days = new Label("Day " + daysPassed);
            Button workButton = new Button("Work(40 Energy)");
            Button prayButton = new Button("Pray(10 Energy)");
            Button sleepButton = new Button("Sleep");
            Label moneyIndicator = new Label("Money: $0");
            Label soulsIndicator = new Label("Souls Saved: " + soulsSaved);
            Label favorIndicator = new Label("God's Favor: " + favorGod);
            Label energyIndicator = new Label("Energy: " + energy + "%");
            Label statusIndicator = new Label("Status: " + defaultStatus);


            
            HBox actions = new HBox(10);
            HBox stats = new HBox(10);

            //Horizontal actions
            actions.getChildren().add(workButton);
            actions.getChildren().add(prayButton);
            actions.getChildren().add(sleepButton);
            actions.setAlignment(Pos.CENTER);

            //Horizontal statistics from actions
            stats.getChildren().add(moneyIndicator);
            stats.getChildren().add(soulsIndicator);
            stats.getChildren().add(favorIndicator);

            stats.setAlignment(Pos.CENTER);


            playerUI.setAlignment(Pos.CENTER);
            playerUI.getChildren().add(days);
            playerUI.getChildren().add(actions);
            playerUI.getChildren().add(stats);
            playerUI.getChildren().add(energyIndicator);
            playerUI.getChildren().add(statusIndicator);
        
            //menuUI
            Button evangalismButton = new Button("Evangelism");
            
            menuUI.getChildren().add(evangalismButton);
            
            
            //EvangalismUI
            Button evangalismWitnessButton = new Button("Witness To One Person");
            
            evangalismUI.getChildren().add(evangalismWitnessButton);
            evangalismUI.setAlignment(Pos.CENTER);
        //Actions
        playButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                setCenter(playerUI);
            }
        });
        workButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {


                if (energy >= 40) {
                    status = defaultStatus;
                    statusIndicator.setText("Status: " + status);
                    energy -= 40;
                    money += wage;
                    soulsIndicator.setText("Souls Saved: " + soulsSaved);
                    moneyIndicator.setText("Money: $" + money);
                    energyIndicator.setText("Energy: " + energy + "%");
                } else {
                    status = "Not enough energy";
                    statusIndicator.setText("Status: " + status);
                }


            }
        });

        prayButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                if (energy >= 10) {
                    status = defaultStatus;
                    statusIndicator.setText("Status: " + status);
                    favorGod += 1;
                    energy -= 10;
                    favorIndicator.setText("God's Favor: " + favorGod);
                    energyIndicator.setText("Energy:" + energy + "%");
                } else {
                    status = "Not enough energy";
                    statusIndicator.setText("Status: " + status);
                }
            }
        });

        sleepButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                daysPassed += 1;
                energy = 100;
                days.setText("Day " + daysPassed);
                energyIndicator.setText("Energy: " + energy + "%");
                status = defaultStatus;
                statusIndicator.setText("Status: " + status);
            }
        });
        evangalismButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                setCenter(evangalismUI);
            }
        });
        evangalismWitnessButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
            	if((Math.random()*100) > 50) {
            		soulsSaved++;
            		status = "You saved 1 soul!";
            		statusIndicator.setText("Status: " + status);
            		soulsIndicator.setText("Souls Saved: " + soulsSaved);
            	}
            }
        });
    }

    @Override
    protected void updateAppBar(AppBar appBar) {
        Button playerButton = new Button("Player");
        Button menuButton = new Button("Menu");
        
        playerButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                setCenter(playerUI);
            }
        });
        
        
        menuButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
            	//menuUI.getChildren().add(statusIndicator);
            	setCenter(menuUI);
                appBar.setTitleText("Menu");
            }
        });
        
    	appBar.setNavIcon(menuButton);
        appBar.setTitleText("Christian Simulator");
        //appBar.getActionItems().add(MaterialDesignIcon.SEARCH.button(e -> System.out.println("Search")));
        appBar.getActionItems().add(playerButton);
    }
    
}
