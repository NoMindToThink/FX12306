package com.example.fxstudy;

import com.example.fxstudy.gui.TrainTableView;
import com.example.fxstudy.springfx.AbstactSpringBootApplication;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class FxstudyApplication extends AbstactSpringBootApplication {

    public static void main(String[] args) {
    launch(FxstudyApplication.class,TrainTableView.class,args);
    }


}
