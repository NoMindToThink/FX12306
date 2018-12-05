package com.example.fxstudy.controlller;

import com.example.fxstudy.entity.BookingPassenger;
import com.example.fxstudy.entity.Passengers;
import com.example.fxstudy.http.TicketInfoContain;
import com.example.fxstudy.http.TicketServer;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.FlowPane;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

/**
 * Created with IDEA
 * author:Dnetted
 * Date:2018/12/4
 * Time:16:22
 */
public class PassengerController implements Initializable {
@FXML
    public Button checkUs;
    @FXML
    public TableView<BookingPassenger> bookingPassengerTableView;
    @FXML
    public FlowPane checkPane;
    @FXML
    public TableColumn col_passenger_type_name;
    @FXML
    public TableColumn col_seatType_name;
    @FXML
    public TableColumn col_passenger_name;
    @FXML
    public TableColumn col_passenger_id_type_name;
    @FXML
    public TableColumn col_passenger_id_no;
    @FXML
    public TableColumn col_mobile_no;

    public static Logger logger = LoggerFactory.getLogger(PassengerController.class);
    ObservableList<BookingPassenger> list = FXCollections.observableArrayList();


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        relativePro();
        List<Passengers.DataBean.NormalPassengersBean> lp = TicketInfoContain.getPassengers().getData().getNormal_passengers();
        for (Passengers.DataBean.NormalPassengersBean p:lp){
            CheckBox checkBox = new CheckBox(p.getPassenger_name());
            checkBox.setOnAction((actionEvent -> {
                CheckBox target = (CheckBox) actionEvent.getTarget();
                BookingPassenger bookingPassenger = new BookingPassenger(p);
                if(target.isSelected()){
                    TicketInfoContain.normalPassengersBeans.add(p);
                    list.add(bookingPassenger);
                }else {
                    TicketInfoContain.normalPassengersBeans.remove(p);
                    list.remove(bookingPassenger);
                }
                bookingPassengerTableView.setItems(list);
                logger.info(TicketInfoContain.normalPassengersBeans.size()+":"+TicketInfoContain.normalPassengersBeans);
            }));
            checkPane.getChildren().addAll(checkBox);
        }

    }
    public void relativePro(){
        col_passenger_type_name.setCellValueFactory(new PropertyValueFactory("passenger_type_name"));
        col_seatType_name.setCellValueFactory(new PropertyValueFactory("seatType_name"));
        col_passenger_name.setCellValueFactory(new PropertyValueFactory("passenger_name"));
        col_passenger_id_type_name.setCellValueFactory(new PropertyValueFactory("passenger_id_type_name"));
        col_passenger_id_no.setCellValueFactory(new PropertyValueFactory("passenger_id_no"));
        col_mobile_no.setCellValueFactory(new PropertyValueFactory("mobile_no"));
    }

    public void submit(ActionEvent actionEvent) {
        try {
            TicketServer.submitOrder(list,TicketInfoContain.getQueryLeftNewDTO());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
