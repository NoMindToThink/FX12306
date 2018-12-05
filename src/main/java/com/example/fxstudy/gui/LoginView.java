package com.example.fxstudy.gui;


import com.example.fxstudy.springfx.AbstractFXView;
import com.example.fxstudy.springfx.FXMLView;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * @Author: D7-Dj
 * @Date: 2018/12/3 22:55
 */
@FXMLView("/fxml/login.fxml")
public class LoginView extends AbstractFXView{
    public LoginView(Stage primaryStage) throws IOException {
        super(primaryStage);
    }
}
