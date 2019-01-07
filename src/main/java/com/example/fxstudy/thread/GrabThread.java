package com.example.fxstudy.thread;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONException;
import com.alibaba.fastjson.JSONObject;
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

    public GrabThread(List<String> nps, List<String> seats, List<String> trains, String start_code, String end_code, String date, String purpose_codes) {
        this.nps = nps;
        this.seats = seats;
        this.trains = trains;
        this.start_code = start_code;
        this.end_code = end_code;
        this.date = date;
        this.purpose_codes = purpose_codes;
    }

    @Override
    public void run() {
        super.run();
    }

    @Override
    public synchronized void start() {
        boolean isGrab = false;
        while(!isGrab) {
            String resp = TicketServer.grabTicket(nps, seats, trains, start_code, end_code, date, purpose_codes);
            if (!resp.equals("-1")) {
                JSONObject respo = null;
                try {
                    respo = JSON.parseObject(resp);
                    boolean flag = respo.getJSONObject("data").getString("submitStatus").equals("true");
                    JSONArray msg = respo.getJSONArray("messages");
                    if (flag) {
                        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "抢票成功");
                        alert.showAndWait();
                        isGrab=true;
                    } else {
//                        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, respo.getJSONArray("messages").get(0).toString());
//                        alert.showAndWait();
                    }
                } catch (JSONException e) {
//                    Alert alert = new Alert(Alert.AlertType.CONFIRMATION, resp);
//                    alert.showAndWait();
                    e.printStackTrace();
                }

            }
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
