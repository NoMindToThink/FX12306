package com.example.fxstudy.springfx;

import com.example.fxstudy.FxstudyApplication;
import com.example.fxstudy.gui.TrainTableView;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.stage.Stage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.context.ConfigurableApplicationContext;

import javax.swing.*;
import java.lang.reflect.Constructor;
import java.util.concurrent.CompletableFuture;

/**
 * @Author: D7-Dj
 * @Date: 2018/12/3 21:19
 */
public class AbstactSpringBootApplication extends Application {
    public static Logger logger = LoggerFactory.getLogger(AbstactSpringBootApplication.class);
    private static Stage primaryStage;
    private static AbstractFXView primaryView;
    private static Class<? extends AbstractFXView> trainTableViewClass;
    private static String[] args=new String[0];
    private static ConfigurableApplicationContext applicationContext;
    @Override
    public void start(Stage primaryStage) throws Exception {
        setPrimaryStage(primaryStage);
        Constructor<? extends AbstractFXView> abstractFXViewConstructor = trainTableViewClass.getConstructor(Stage.class);
        AbstractFXView abstractFXView = abstractFXViewConstructor.newInstance(primaryStage);
    }


    public static void launch(Class<FxstudyApplication> fxstudyApplicationClass, Class<TrainTableView> trainTableViewClass, String[] args) {
        setTrainTableViewClass(trainTableViewClass);
        launch(fxstudyApplicationClass,args);
    }


    @Override
    public void init() throws Exception {
        // Load in JavaFx Thread and reused by Completable Future, but should no be a big deal.
        super.init();
        SpringApplication.run(this.getClass(),getArgs());
    }

    private void launchApplicationView(ConfigurableApplicationContext ctx) {
        setApplicationContext(ctx);
    }

    public static Stage getPrimaryStage() {
        return primaryStage;
    }

    public static void setPrimaryStage(Stage primaryStage) {
        AbstactSpringBootApplication.primaryStage = primaryStage;
    }

    public static Class<? extends AbstractFXView> getTrainTableViewClass() {
        return trainTableViewClass;
    }

    public static void setTrainTableViewClass(Class<? extends AbstractFXView> trainTableViewClass) {
        AbstactSpringBootApplication.trainTableViewClass = trainTableViewClass;
    }

    public static String[] getArgs() {
        return args;
    }

    public static void setArgs(String[] args) {
        AbstactSpringBootApplication.args = args;
    }

    public static ConfigurableApplicationContext getApplicationContext() {
        return applicationContext;
    }

    public static void setApplicationContext(ConfigurableApplicationContext applicationContext) {
        AbstactSpringBootApplication.applicationContext = applicationContext;
    }

    public static AbstractFXView getPrimaryView() {
        return primaryView;
    }

    public static void setPrimaryView(AbstractFXView primaryView) {
        AbstactSpringBootApplication.primaryView = primaryView;
    }
}
