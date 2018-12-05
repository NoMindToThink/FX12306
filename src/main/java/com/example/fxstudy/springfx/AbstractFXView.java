package com.example.fxstudy.springfx;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * Created with IDEA
 * author:Dnetted
 * Date:2018/12/3
 * Time:17:23
 */
public class AbstractFXView{
    private FXMLView annoation;
    public AbstractFXView(){
         annoation = getAnnoation();
        Pane pane = null;
        try {
            pane = FXMLLoader.load(getClass().getResource(annoation.value()));
        } catch (IOException e) {
            e.printStackTrace();
        }
        Scene scene = new Scene(pane);
        Stage newStage = new Stage();
        newStage.setScene(scene);
        newStage.show();
    }
    public AbstractFXView(Stage primaryStage) throws IOException {
        annoation = getAnnoation();
        Pane pane = null;
        Class ss = getClass();
            pane = FXMLLoader.load(ss.getResource(annoation.value()));
        Scene scene = new Scene(pane);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private FXMLView getAnnoation() {
        Class<? extends AbstractFXView> theclass = this.getClass();
        annoation = theclass.getAnnotation(FXMLView.class);
        return annoation;
    }
}
