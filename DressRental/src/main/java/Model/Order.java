package Model;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "Order_")
public class Order implements Serializable {
    @Id
    @Column(name = "Client_ID")
    private int client_id;

    @Id
    @Column(name = "Dress_ID")
    private int dress_id;

    public Order() {
    }

    public Order(int clientID, int dressID) {
        this.client_id = clientID;
        this.dress_id = dressID;
    }

    public int getClientID() {
        return client_id;
    }

    public void setClientID(int clientID) {
        this.client_id = clientID;
    }

    public int getDressID() {
        return dress_id;
    }

    public void setDressID(int dressID) {
        this.dress_id = dressID;
    }
}
