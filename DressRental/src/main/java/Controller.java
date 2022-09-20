import Dao.ClientDao;
import Data.ClientData;
import Data.DressData;
import Data.OrderData;
import Model.Client;
import Model.Dress;
import Model.Order;
import Service.ClientService;
import Service.DressService;
import Service.OrderService;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

import javax.persistence.criteria.CriteriaBuilder;
import java.util.List;

public class Controller {

    //elements for client entity
    @FXML
    private TextField newPersonalId;

    @FXML
    private TextField newFirstname;

    @FXML
    private TextField newSecondname;

    @FXML
    private TextField newSize;

    @FXML
    private TextField newEmail;

    @FXML
    private Button deleteClient;

    @FXML
    private Button updateClient;

    @FXML
    private Button addNewClient;

    @FXML
    private TableView<ClientData> clientTable;

    @FXML
    private TableColumn<ClientData, Integer> idColumn;

    @FXML
    private TableColumn<ClientData, String> fnameColumn;

    @FXML
    private TableColumn<ClientData, String> snameColumn;

    @FXML
    private TableColumn<ClientData, Integer> sizeColumn;

    @FXML
    private TableColumn<ClientData, String> emailColumn;

    @FXML
    private Button updateClientTable;

    @FXML
    private Label errorClient;

    @FXML
    private Button clientChoice;

    ClientService clientService = new ClientService();
    DressService dressService = new DressService();
    OrderService orderService = new OrderService();
    private ClientData clientSelection = null;
    private DressData dressSelection = null;
    private OrderData orderSelection = null;



    //functions for client entity
    //adding new client in database
    @FXML
    public void addClient(ActionEvent event) {
        Client client = getClientFromTextFields();

        if(clientService.findClient(Integer.parseInt(newPersonalId.getText())) == null &&
                newFirstname.getText().length() <= 100 && newSecondname.getText().length() <= 100 &&
                newSize.getText().length() <= 3  && newEmail.getText().length() <= 100){
            clientService.saveClient(client);
            newPersonalId.setText("");
            newFirstname.setText("");
            newSecondname.setText("");
            newSize.setText("");
            newEmail.setText("");
            updateClientList();
            errorClient.setText("");
        } else {
            errorClient.setText("Invalid data, check and try again!");

        }
    }

    //choosing a client from table
    @FXML
    public void clientChoice(ActionEvent event){
        if(clientSelection != null){
            newPersonalId.setText(Integer.toString(clientSelection.getNewPersonalId()));
            newFirstname.setText(clientSelection.getNewFirstname());
            newSecondname.setText(clientSelection.getNewSecondname());
            newSize.setText(Integer.toString(clientSelection.getNewSize()));
            newEmail.setText(clientSelection.getNewEmail());
        }else{
            errorClient.setText("Click on the row you want to update!");
        }
    }

    //updating client information
    @FXML
    public void updateClient(ActionEvent event) {
        Client client = getClientFromTextFields();

        if (clientSelection.getNewPersonalId() != Integer.parseInt(newPersonalId.getText())) {
            errorClient.setText("It is impossible to update ID!");
        } else {
            if (clientService.findClient(Integer.parseInt(newPersonalId.getText())).getPersonal_id() == Integer.parseInt(newPersonalId.getText()) &&
                    newFirstname.getText().length() <= 100 && newFirstname.getText().length() != 0 && newSecondname.getText().length() <= 100 &&
                    newSecondname.getText().length() != 0 && newSize.getText().length() <= 3 && newSize.getText().length() != 0  &&
                    newEmail.getText().length() <= 100 && newEmail.getText().length() != 0) {
                clientService.updateClient(client);
                newPersonalId.setText("");
                newFirstname.setText("");
                newSecondname.setText("");
                newSize.setText("");
                newEmail.setText("");
                updateClientList();
                errorClient.setText("");
                clientSelection = null;
            } else {
                errorClient.setText("Check the form and try again!");
            }
        }
    }

    //deleting client
    @FXML
    public void deleteClient(ActionEvent event) {
        if (clientSelection != null) {
            Client client = getClientFromTextFields();
            List<Order> orders = orderService.findAllOrders();
            for (Order o : orders) {
                if (o.getClientID() == clientSelection.getNewPersonalId()) {
                    errorClient.setText("It is not possible to delete this object, because it is related to order in another table!");
                    return;
                }
            }
            clientService.deleteClient(client);
            newPersonalId.setText("");
            newFirstname.setText("");
            newSecondname.setText("");
            newSize.setText("");
            newEmail.setText("");
            updateClientList();
            errorClient.setText("");
            clientSelection = null;
        } else {
            errorClient.setText("Click on the row you want to delete!");
        }
    }

    //showing updated client table
    @FXML
    public void updateClientTable(ActionEvent event){
        updateClientList();
    }

    //updating client table
    public void updateClientList(){
        TableView.TableViewSelectionModel<ClientData> selectionModel = clientTable.getSelectionModel();
        selectionModel.selectedItemProperty().addListener(new ChangeListener<ClientData>(){
            public void changed(ObservableValue<? extends ClientData> observable, ClientData oldValue, ClientData newValue) {
                if (newValue != null) clientSelection = newValue;
            }
        });
        ObservableList<ClientData> data = FXCollections.observableArrayList();
        List<Client> clients = clientService.findAllClients();
        for (Client c : clients){
            data.add(new ClientData(c.getPersonal_id(), c.getFirstname(), c.getSecondname(), c.getSize(), c.getEmail()));
        }
        this.idColumn.setCellValueFactory(new PropertyValueFactory<ClientData, Integer>("newPersonalId"));
        this.fnameColumn.setCellValueFactory(new PropertyValueFactory<ClientData, String>("newFirstname"));
        this.snameColumn.setCellValueFactory(new PropertyValueFactory<ClientData, String>("newSecondname"));
        this.sizeColumn.setCellValueFactory(new PropertyValueFactory<ClientData, Integer>("newSize"));
        this.emailColumn.setCellValueFactory(new PropertyValueFactory<ClientData, String>("newEmail"));
        this.clientTable.setItems(data);
    }

    //getting information from text fields
    private Client getClientFromTextFields() {
        Client client = new Client(Integer.parseInt(newPersonalId.getText()), newFirstname.getText(), newSecondname.getText(), Integer.parseInt(newSize.getText()), newEmail.getText());
        return client;
    }

    @FXML
    private TextField newSerialNumber;

    @FXML
    private TextField new_Size;

    @FXML
    private TextField newStyle;

    @FXML
    private TextField newBrand;

    @FXML
    private TextField newColor;

    @FXML
    private TableView<DressData> dressTable;

    @FXML
    private TableColumn<DressData, Integer> snumColumn;

    @FXML
    private TableColumn<DressData, Integer> size_Column;

    @FXML
    private TableColumn<DressData, String> styleColumn;

    @FXML
    private TableColumn<DressData, String> brandColumn;

    @FXML
    private TableColumn<DressData, String> colorColumn;

    @FXML
    private Button updateDressTable;

    @FXML
    private Button deleteDress;

    @FXML
    private Button updateDress;

    @FXML
    private Button addNewDress;

    @FXML
    private Label errorDress;

    @FXML
    private Button dressChoice;

    //functions for dress entity
    //adding new dress in database
    @FXML
    public void addDress(ActionEvent event) {
        Dress dress = getDressFromTextFields();

        if(dressService.findDress(Integer.parseInt(newSerialNumber.getText())) == null &&
                new_Size.getText().length() <= 3 && newStyle.getText().length() <= 100 &&
                newBrand.getText().length() <= 100  && newColor.getText().length() <= 100){
            dressService.saveDress(dress);
            newSerialNumber.setText("");
            new_Size.setText("");
            newStyle.setText("");
            newBrand.setText("");
            newColor.setText("");
            updateDressList();
            errorDress.setText("");
        } else {
            errorDress.setText("Invalid data, check and try again!");
        }
    }

    //choosing a dress from table
    @FXML
    public void dressChoice(ActionEvent event){
        if(dressSelection != null){
            newSerialNumber.setText(Integer.toString(dressSelection.getNewSerialNumber()));
            new_Size.setText(Integer.toString(dressSelection.getNewSize1()));
            newStyle.setText(dressSelection.getNewStyle());
            newBrand.setText(dressSelection.getNewBrand());
            newColor.setText(dressSelection.getNewColor());
        }else{
            errorDress.setText("Click on the row you want to update!");
        }
    }

    //updating dress information
    @FXML
    public void updateDress(ActionEvent event) {
        Dress dress = getDressFromTextFields();

        if (dressSelection.getNewSerialNumber() != Integer.parseInt(newSerialNumber.getText())) {
            errorClient.setText("It is impossible to update serial number!");
        } else {
            if (dressService.findDress(Integer.parseInt(newSerialNumber.getText())).getSerialNumber() == Integer.parseInt(newSerialNumber.getText()) &&
                    new_Size.getText().length() <= 3 && new_Size.getText().length() != 0 && newStyle.getText().length() <= 100 &&
                    newStyle.getText().length() != 0 && newBrand.getText().length() <= 100 && newBrand.getText().length() != 0  &&
                    newColor.getText().length() <= 100 && newColor.getText().length() != 0) {
                dressService.updateDress(dress);
                newSerialNumber.setText("");
                new_Size.setText("");
                newStyle.setText("");
                newBrand.setText("");
                newColor.setText("");
                updateDressList();
                errorDress.setText("");
                dressSelection = null;
            } else {
                errorDress.setText("Check the form and try again!");
            }
        }
    }

    //deleting dress
    @FXML
    public void deleteDress(ActionEvent event) {
        if (dressSelection != null) {
            Dress dress = getDressFromTextFields();
            List<Order> orders = orderService.findAllOrders();
            for (Order o : orders) {
                if (o.getDressID() == dressSelection.getNewSerialNumber()) {
                    errorClient.setText("It is not possible to delete this object, because it is related to order in another table!");
                    return;
                }
            }
            dressService.deleteDress(dress);
            newSerialNumber.setText("");
            new_Size.setText("");
            newStyle.setText("");
            newBrand.setText("");
            newColor.setText("");
            updateDressList();
            errorDress.setText("");
            dressSelection = null;
        } else {
            errorDress.setText("Click on the row you want to delete!");
        }
    }

    //showing updated dress table
    @FXML
    public void updateDressTable(ActionEvent event){
        updateDressList();
    }

    //updating client table
    public void updateDressList(){
        TableView.TableViewSelectionModel<DressData> selectionModel = dressTable.getSelectionModel();
        selectionModel.selectedItemProperty().addListener(new ChangeListener<DressData>(){
            public void changed(ObservableValue<? extends DressData> observable, DressData oldValue, DressData newValue) {
                if (newValue != null) dressSelection = newValue;
            }
        });
        ObservableList<DressData> data = FXCollections.observableArrayList();
        List<Dress> dresses = dressService.findAllDresses();
        for (Dress d : dresses){
            data.add(new DressData(d.getSerialNumber(), d.getSize(), d.getStyle(), d.getBrand(), d.getColor()));
        }
        this.snumColumn.setCellValueFactory(new PropertyValueFactory<DressData, Integer>("newSerialNumber"));
        this.size_Column.setCellValueFactory(new PropertyValueFactory<DressData, Integer>("new_Size"));
        this.styleColumn.setCellValueFactory(new PropertyValueFactory<DressData, String>("newStyle"));
        this.brandColumn.setCellValueFactory(new PropertyValueFactory<DressData, String>("newBrand"));
        this.colorColumn.setCellValueFactory(new PropertyValueFactory<DressData, String>("newColor"));
        this.dressTable.setItems(data);
    }

    //getting information from text fields
    private Dress getDressFromTextFields() {
        Dress dress = new Dress(Integer.parseInt(newSerialNumber.getText()), Integer.parseInt(new_Size.getText()), newStyle.getText(), newBrand.getText(), newColor.getText());
        return dress;
    }

    @FXML
    private TextField newClientId;

    @FXML
    private TextField newDressId;

    @FXML
    private Button deleteOrder;

    @FXML
    private Button updateOrder;

    @FXML
    private Button addNewOrder;

    @FXML
    private Button updateOrderTable;

    @FXML
    private TableView<OrderData> orderTable;

    @FXML
    private TableColumn<OrderData, Integer> clientColumn;

    @FXML
    private TableColumn<OrderData, Integer> dressColumn;

    @FXML
    private Label errorOrder;

    @FXML
    private Button orderChoice;

    //functions for order relation
    //adding new order in database
    @FXML
    public void addOrder(ActionEvent event) {
        Order order = getOrderFromTextFields();

        if(clientService.findClient(Integer.parseInt(newClientId.getText())) != null && dressService.findDress(Integer.parseInt(newDressId.getText())) != null){
            orderService.saveOrder(order);
            newClientId.setText("");
            newDressId.setText("");
            updateOrderList();
            errorOrder.setText("");
        } else {
            errorOrder.setText("Invalid data, check and try again!");
        }
    }

    //choosing a order from table
    @FXML
    public void orderChoice(ActionEvent event){
        if(orderSelection != null){
            newClientId.setText(Integer.toString(orderSelection.getNewClientId()));
            newDressId.setText(Integer.toString(orderSelection.getNewDressId()));
        }else{
            errorOrder.setText("Click on the row you want to update!");
        }
    }

    //updating order information
    @FXML
    public void updateOrder(ActionEvent event) {
        Order newOrder = new Order(Integer.parseInt(newClientId.getText()), Integer.parseInt(newDressId.getText()));
        Order oldOrder = new Order(orderSelection.getNewClientId(), orderSelection.getNewClientId());

        if (clientService.findClient(Integer.parseInt(newClientId.getText())) != null && dressService.findDress(Integer.parseInt(newDressId.getText())) != null) {
            orderService.deleteOrder(oldOrder);
            orderService.saveOrder(newOrder);
            newClientId.setText("");
            newDressId.setText("");
            updateOrderList();
            errorOrder.setText("");
            orderSelection = null;
        } else {
            errorOrder.setText("Invalid data, check and try again!");
        }
    }

    //deleting an order
    @FXML
    public void deleteOrder(ActionEvent event) {
        if (orderSelection != null) {
            Order order = getOrderFromTextFields();
            orderService.deleteOrder(order);
            updateOrderList();
            errorOrder.setText("");
            orderSelection = null;
        } else {
            errorDress.setText("Click on the row you want to delete!");
        }
    }

    //showing updated order table
    @FXML
    public void updateOrderTable(ActionEvent event){
        updateOrderList();
    }

    //updating order table
    public void updateOrderList(){
        TableView.TableViewSelectionModel<OrderData> selectionModel = orderTable.getSelectionModel();
        selectionModel.selectedItemProperty().addListener(new ChangeListener<OrderData>(){
            public void changed(ObservableValue<? extends OrderData> observable, OrderData oldValue, OrderData newValue) {
                if (newValue != null) orderSelection = newValue;
            }
        });
        ObservableList<OrderData> data = FXCollections.observableArrayList();
        List<Order> orders = orderService.findAllOrders();
        for (Order o : orders){
            data.add(new OrderData(o.getClientID(), o.getDressID()));
        }
        this.clientColumn.setCellValueFactory(new PropertyValueFactory<OrderData, Integer>("newClientId"));
        this.dressColumn.setCellValueFactory(new PropertyValueFactory<OrderData, Integer>("newDressId"));
        this.orderTable.setItems(data);
    }

    //getting information from text fields
    private Order getOrderFromTextFields() {
        Order order = new Order(Integer.parseInt(newClientId.getText()), Integer.parseInt(newDressId.getText()));
        return order;
    }

}
