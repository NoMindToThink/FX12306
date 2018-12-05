package com.example.fxstudy.gui;

import com.example.fxstudy.springfx.AbstractFXView;
import com.example.fxstudy.springfx.FXMLView;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * Created with IDEA
 * author:Dnetted
 * Date:2018/12/3
 * Time:17:33
 */
@FXMLView(value = "/fxml/traintable.fxml")
public class TrainTableView extends AbstractFXView {
    public TrainTableView(Stage primaryStage) throws IOException {
        super(primaryStage);
    }
}
