package com.example.fxstudy.controlller;

import com.alibaba.fastjson.JSONObject;
import com.example.fxstudy.exception.TicketException;
import com.example.fxstudy.http.TicketInfoContain;
import com.example.fxstudy.http.TicketServer;
import com.example.fxstudy.util.TicketRespUtil;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.json.Json;
import javax.json.JsonObjectBuilder;
import java.io.IOException;
import java.net.URL;
import java.util.*;

/**
 * @Author: D7-Dj
 * @Date: 2018/12/3 22:56
 */
public class LoginController implements Initializable {
    private Logger logger = LoggerFactory.getLogger(LoginController.class);
    @FXML
    private Button refreshCap;

    @FXML
    private ImageView capPic;

    @FXML
    private CheckBox ch1;
    @FXML
    private CheckBox ch2;
    @FXML
    private CheckBox ch3;
    @FXML
    private CheckBox ch4;
    @FXML
    private CheckBox ch5;
    @FXML
    private CheckBox ch6;
    @FXML
    private CheckBox ch7;
    @FXML
    private CheckBox ch8;
    @FXML
    private TextField t_username;
    @FXML
    private PasswordField t_password;

    public static Random random = new Random();
    public static Map<String,CheckBox> chMap = new HashMap<>();

    public void refreshCaptchBtn(ActionEvent actionEvent) {
        refreshCaptch();
    }
    public void refreshCaptch() {
        try {
            JSONObject body = TicketServer.getCaptcja();
            capPic.setImage(new Image(TicketRespUtil.getImageBase64(body.getString("image"))));
            setSelectedAll(false);
        } catch (IOException e1) {
            e1.printStackTrace();
        }
    }

    public void close(ActionEvent actionEvent) {
        TrainTableController.stagerContain.get("login").close();
        TrainTableController.stagerContain.put("login",null);

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        //存储控制器
        TrainTableController.controllerContain.put("login",this);
        chMap.put("ch1",ch1);
        chMap.put("ch2",ch2);
        chMap.put("ch3",ch3);
        chMap.put("ch4",ch4);
        chMap.put("ch5",ch5);
        chMap.put("ch6",ch6);
        chMap.put("ch7",ch7);
        chMap.put("ch8",ch8);
        JSONObject body = null;
        try {
            body = TicketServer.getCaptcja();
            capPic.setImage(new Image(TicketRespUtil.getImageBase64(body.getString("image"))));
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void submit(ActionEvent actionEvent) {
        TrainTableController train = (TrainTableController) TrainTableController.controllerContain.get("train");
        try {
            String ans = checkCapAll();
            JSONObject jsonObject = TicketServer.login(t_username.getText(), t_password.getText(), ans);
            Alert loginSuc = new Alert(Alert.AlertType.INFORMATION,jsonObject.getString("result_message"));
            loginSuc.showAndWait();
            train.showPassengerNew();
            close(actionEvent);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (TicketException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR,e.getMessage());
            alert.showAndWait();
            refreshCaptch();
            e.printStackTrace();
        }
    }
    private String checkCapAll(){
        String ans="";
        if (ch1.isSelected()){
            ans+=checkCap(1);
        }
        if (ch2.isSelected()){
            ans+=checkCap(2);
        }
        if (ch3.isSelected()){
            ans+=checkCap(3);
        }
        if (ch4.isSelected()){
            ans+=checkCap(4);
        }
        if (ch5.isSelected()){
            ans+=checkCap(5);
        }
        if (ch6.isSelected()){
            ans+=checkCap(6);
        }
        if (ch7.isSelected()){
            ans+=checkCap(7);
        }
        if (ch8.isSelected()){
            ans+=checkCap(8);
        }
        if(ans=="") {
            return null;
        }
        return ans.substring(0,ans.length()-1);
    }
    private String checkCap(int i) {
        String ans= "";
        int x = (i-1)%4;
        int y = (i-1)/4;
        int ansx = 33+75*x+random.nextInt(26)-13;
        int ansy = 33+75*y+random.nextInt(26)-13;
        return ansx+","+ansy+",";
    }
    public static void setSelectedAll(boolean selected){
        Set<String> chs = chMap.keySet();
        for (String key:chs){
            chMap.get(key).setSelected(selected);
        }
    }
}
