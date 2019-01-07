package com.example.fxstudy.thread;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONException;
import com.alibaba.fastjson.JSONObject;
import com.example.fxstudy.controlller.TrainTableController;
import com.example.fxstudy.exception.TicketException;
import com.example.fxstudy.http.TicketServer;
import javafx.scene.control.Alert;

import java.util.List;

/**
 * Created with IDEA
 * author:Dnetted
 * Date:2019/1/7
 * Time:10:51
 */
public class GrabThread extends Thread {
    private List<String> nps;
    private List<String> seats;
    private List<String> trains;
    private String start_code;
    private String end_code;
    private String date;
    private String purpose_codes;
    private TrainTableController trainTableController;

    public GrabThread(List<String> nps, List<String> seats, List<String> trains, String start_code, String end_code, String date, String purpose_codes, TrainTableController trainTableController) {
        this.nps = nps;
        this.seats = seats;
        this.trains = trains;
        this.start_code = start_code;
        this.end_code = end_code;
        this.date = date;
        this.purpose_codes = purpose_codes;
        this.trainTableController=trainTableController;
    }


    @Override
    public synchronized void run() {
        boolean isGrab = false;
        while(!isGrab && !Thread.currentThread().isInterrupted()) {
            String resp = TicketServer.grabTicket(nps, seats, trains, start_code, end_code, date, purpose_codes);
            if (!resp.equals("-1")) {
                JSONObject respo = null;
                try {
                    respo = JSON.parseObject(resp);
                    boolean flag = respo.getJSONObject("data").getString("submitStatus").equals("true");
                    JSONArray msg = respo.getJSONArray("messages");
                    if (flag) {
                        throw new TicketException("抢票成功");
                    } else {
//                        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, respo.getJSONArray("messages").get(0).toString());
//                        alert.showAndWait();
                    }
                } catch (JSONException e) {
//                    Alert alert = new Alert(Alert.AlertType.CONFIRMATION, resp);
//                    alert.showAndWait();
                    trainTableController.showMsg(Alert.AlertType.ERROR,resp);
                    e.printStackTrace();
                } catch (TicketException e) {
                    trainTableController.showMsg(Alert.AlertType.INFORMATION,e.getMessage());
                    Thread.currentThread().interrupt();
                }

            }
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
                Thread.currentThread().interrupt();
            }
        }
    }
}
