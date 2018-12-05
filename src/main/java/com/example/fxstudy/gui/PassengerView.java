package com.example.fxstudy.gui;

import com.example.fxstudy.springfx.AbstractFXView;
import com.example.fxstudy.springfx.FXMLView;
import javafx.fxml.FXML;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * Created with IDEA
 * author:Dnetted
 * Date:2018/12/4
 * Time:16:20
 */
@FXMLView("/fxml/passenger.fxml")
public class PassengerView extends AbstractFXView {
    public PassengerView(Stage primaryStage) throws IOException {
        super(primaryStage);
    }
}
