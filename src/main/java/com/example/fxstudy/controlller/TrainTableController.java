package com.example.fxstudy.controlller;

import com.example.fxstudy.FxstudyApplication;
import com.example.fxstudy.entity.BookTicketInfo;
import com.example.fxstudy.entity.Passengers;
import com.example.fxstudy.entity.QueryInfo;
import com.example.fxstudy.entity.QueryLeftNewDTO;
import com.example.fxstudy.exception.TicketException;
import com.example.fxstudy.gui.LoginView;
import com.example.fxstudy.gui.PassengerView;
import com.example.fxstudy.http.TicketInfoContain;
import com.example.fxstudy.http.TicketServer;
import com.example.fxstudy.springfx.AbstractFXView;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.net.URL;
import java.util.*;

/**
 * Created with IDEA
 * author:Dnetted
 * Date:2018/12/3
 * Time:18:01
 */
public class TrainTableController implements Initializable {
    private static Logger logger =LoggerFactory.getLogger(TrainTableController.class);
    @FXML
    public Button checkU;
    @FXML
    public Button closeBtn;
    @FXML
    public Button loginBtn;
    @FXML
    public VBox passengerBox;
    @FXML
    public VBox trainIdBox;
    @FXML
    public VBox seatTypeBox;
    @FXML
    public Button grabTicket;
    @FXML
    private TableView train_table;
    @FXML
    private TableColumn col_station_train_code;
    @FXML
    private TableColumn col_to_station_name;
    @FXML
    private TableColumn col_from_station_name;
    @FXML
    private TableColumn col_start_time;
    @FXML
    private TableColumn col_arrive_time;
    @FXML
    private TableColumn col_lishi;
    @FXML
    private TableColumn col_swz_num;
    @FXML
    private TableColumn col_tz_num;
    @FXML
    private TableColumn col_zy_num;
    @FXML
    private TableColumn col_ze_num;
    @FXML
    private TableColumn col_gr_num;
    @FXML
    private TableColumn col_rw_num;
    @FXML
    private TableColumn col_srrb_num;
    @FXML
    private TableColumn col_yw_num;
    @FXML
    private TableColumn col_rz_num;
    @FXML
    private TableColumn col_yz_num;
    @FXML
    private TableColumn col_wz_num;
    @FXML
    private TableColumn col_qt_num;
    @FXML
    private TableColumn col_btn;
    @FXML
    private TableColumn col_btn2;
    @FXML
    private Button query;
    @FXML
    private TextField start;
    @FXML
    private TextField end;
    @FXML
    private DatePicker datePicker;

    public static Map<String, Stage> stagerContain = new HashMap<>();

    public static Map<String, Object> controllerContain = new HashMap<>();

    public static List<QueryInfo> lq;

    public void queryInfo(ActionEvent actionEvent) {
        System.out.println("搜索");
    }
    public void close(ActionEvent actionEvent){
        FxstudyApplication.getPrimaryStage().close();
    }

    public void showLogin() throws IOException {
        if(stagerContain.get("login")!=null){
            stagerContain.get("login").show();
            return;
        }
        Stage stage = new Stage();
        LoginView loginView = new LoginView(stage);
        stagerContain.put("login",stage);
        stagerContain.put("passengers",new Stage());
    }
    public void showPassenger() throws IOException {
        Stage stage = new Stage();
        PassengerView passengerView = new PassengerView(stage);
        stagerContain.put("passengers",stage);
    }

    public void quertTicket(ActionEvent actionEvent) throws IOException {
        ObservableList<QueryLeftNewDTO> list = FXCollections.observableArrayList();
        String start_code = TicketInfoContain.STATIONS.get(start.getText()).getCode();
        String end_code = TicketInfoContain.STATIONS.get(end.getText()).getCode();
        String date = datePicker.getValue().toString();
        logger.info(start_code+"-->"+end_code+"::"+date);
        lq = TicketServer.quertTicket(date, start_code, end_code, "ADULT");
        for (QueryInfo qi:lq){
//            logger.info(qi.queryLeftNewDTO.toString());
            list.add(qi.queryLeftNewDTO);
        }
        train_table.setItems(list);
    }

    public void checkUser(ActionEvent actionEvent) throws IOException {
        Boolean b = TicketServer.checkUser();
        logger.info("登陆状态:"+b);
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        //存储控制器
        controllerContain.put("train",this);
        //表和属性关联
        relativePro();
        //地名初始化
        TicketInfoContain.initStation();
        //载入座位类型
        TicketInfoContain.initSeatType();
        showSeatType();
        logger.info("地名已成功初始化"+TicketInfoContain.STATIONS.size()+"条记录！");
        try {
            TicketServer.getQueryUrl();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void relativePro(){
        col_station_train_code.setCellValueFactory(new PropertyValueFactory("station_train_code"));
        col_from_station_name.setCellValueFactory(new PropertyValueFactory("from_station_name"));
        col_to_station_name.setCellValueFactory(new PropertyValueFactory("to_station_name"));
        col_start_time.setCellValueFactory(new PropertyValueFactory("start_time"));
        col_arrive_time.setCellValueFactory(new PropertyValueFactory("arrive_time"));
        col_lishi.setCellValueFactory(new PropertyValueFactory("lishi"));
        col_swz_num.setCellValueFactory(new PropertyValueFactory("swz_num"));
        col_tz_num.setCellValueFactory(new PropertyValueFactory("tz_num"));
        col_zy_num.setCellValueFactory(new PropertyValueFactory("zy_num"));
        col_ze_num.setCellValueFactory(new PropertyValueFactory("ze_num"));
        col_gr_num.setCellValueFactory(new PropertyValueFactory("gr_num"));
        col_rw_num.setCellValueFactory(new PropertyValueFactory("rw_num"));
        col_srrb_num.setCellValueFactory(new PropertyValueFactory("srrb_num"));
        col_yw_num.setCellValueFactory(new PropertyValueFactory("yw_num"));
        col_rz_num.setCellValueFactory(new PropertyValueFactory("rz_num"));
        col_yz_num.setCellValueFactory(new PropertyValueFactory("yz_num"));
        col_wz_num.setCellValueFactory(new PropertyValueFactory("wz_num"));
        col_qt_num.setCellValueFactory(new PropertyValueFactory("qt_num"));
        col_btn.setCellFactory((col)->{
            TableCell<QueryLeftNewDTO, Boolean> cell = new TableCell<QueryLeftNewDTO, Boolean>() {
                @Override
                public void updateItem(Boolean item, boolean empty) {
                    super.updateItem(item, empty);
                    this.setText(null);
                    this.setGraphic(null);

                    if (!empty) {
                        Button button = new Button("预约");
                        this.setGraphic(button);
                        button.setOnMouseClicked((me)->{
                            ObservableList<QueryLeftNewDTO> oq= this.getTableView().getItems();
                            QueryLeftNewDTO q = oq.get(this.getIndex());
                            logger.info("你预约了"+q.getStation_train_code()+"车次");
//                            预约车辆保存
                            TicketInfoContain.setQueryLeftNewDTO(q);
                            for (QueryInfo qi:lq){
                                if (qi.queryLeftNewDTO.equals(q)){
                                    BookTicketInfo bookTicketInfo = new BookTicketInfo();
                                    bookTicketInfo.setSecretStr(qi.getSecretStr());
                                    bookTicketInfo.setBack_train_date(datePicker.getValue().toString());
                                    bookTicketInfo.setTrain_date(datePicker.getValue().toString());
                                    bookTicketInfo.setTour_flag("dc");
                                    bookTicketInfo.setPurpose_codes("ADULT");
                                    bookTicketInfo.setQuery_from_station_name(q.from_station_name);
                                    bookTicketInfo.setQuery_to_station_name(q.to_station_name);
                                    logger.info(q.getStation_train_code());
                                    try {
                                        TicketServer.bookingTicket(bookTicketInfo);
                                    } catch (IOException e) {
                                        e.printStackTrace();
                                    } catch (TicketException e) {
                                        Alert alert = new Alert(Alert.AlertType.ERROR,e.getMessage());
                                        alert.showAndWait();
                                        e.printStackTrace();
                                    }
                                    break;
                                }
                            }
                            try {
                                showPassenger();
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        });
                    }
                }

            };
            return cell;
        });

        col_btn2.setCellFactory((col)->{
            TableCell<QueryLeftNewDTO, Boolean> cell = new TableCell<QueryLeftNewDTO, Boolean>() {
                @Override
                public void updateItem(Boolean item, boolean empty) {
                    super.updateItem(item, empty);
                    this.setText(null);
                    this.setGraphic(null);

                    if (!empty) {
                        Button button = new Button("预约");
                        this.setGraphic(button);
                        button.setOnMouseClicked((me)->{
                            ObservableList<QueryLeftNewDTO> oq= this.getTableView().getItems();
                            QueryLeftNewDTO q = oq.get(this.getIndex());
                            Button e = new Button(q.getStation_train_code());
                            e.setOnMouseClicked((ex)->{
                                trainIdBox.getChildren().removeAll(e);
                            });
                            if(!trainIdBox.getChildren().contains(e)) {
                                trainIdBox.getChildren().add(e);
                            }
                            logger.info("你预约了"+q.getStation_train_code()+"车次");
                        });
                    }
                }

            };
            return cell;
        });
    }
    public void showPassengerNew(){
        try {
            List<Passengers.DataBean.NormalPassengersBean> passengers = TicketServer.getPassenger();
            for (Passengers.DataBean.NormalPassengersBean np:passengers){
                passengerBox.setSpacing(2);
                TicketInfoContain.normalPassengersBeans.add(np);
                passengerBox.getChildren().add(new CheckBox(np.getPassenger_name()));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    /*
    9 -- 商务座
    M -- 一等座
    O -- 二等座
    1 -- 硬座
    2 -- 软座
    3 -- 硬卧
    4 -- 软卧
 */
    public void showSeatType(){
        Set<String> set = TicketInfoContain.SEATTYPES.keySet();
        for (String s:set) {
            seatTypeBox.setSpacing(2);
            seatTypeBox.getChildren().add(new CheckBox(s));
        }
    }

    public void grabTicket(ActionEvent actionEvent) {
        List<String> nps = getPassengers();
        logger.info(nps.toString());
        List<String> seats = getSeatType();
        logger.info(seats.toString());
        List<String> trains = getTrainId();
        logger.info(trains.toString());

        String start_code = TicketInfoContain.STATIONS.get(start.getText()).getCode();
        String end_code = TicketInfoContain.STATIONS.get(end.getText()).getCode();
        String date = datePicker.getValue().toString();
        String purpose_codes = "ADULT";
        TicketServer.grabTicket(nps,seats,trains,start_code,end_code,date,purpose_codes);

    }
    public List<String> getPassengers(){
        Iterator<Node> iterator = passengerBox.getChildren().iterator();
        List<String> pnames = new ArrayList<>();
        while(iterator.hasNext()){
            CheckBox npc = (CheckBox) iterator.next();
            if(npc.isSelected()){
                pnames.add(npc.getText());
            }
        }
        return pnames;
    }
    public List<String> getSeatType(){
        Iterator<Node> iterator = seatTypeBox.getChildren().iterator();
        List<String> pnames = new ArrayList<>();
        while(iterator.hasNext()){
            CheckBox npc = (CheckBox) iterator.next();
            if(npc.isSelected()){
                pnames.add(npc.getText());
            }
        }
        return pnames;
    }
    public List<String> getTrainId(){
        Iterator<Node> iterator = trainIdBox.getChildren().iterator();
        List<String> pnames = new ArrayList<>();
        while(iterator.hasNext()){
            Button npc = (Button) iterator.next();
                pnames.add(npc.getText());
        }
        return pnames;
    }
}
