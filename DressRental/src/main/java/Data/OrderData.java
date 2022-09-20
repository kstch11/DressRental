package Data;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class OrderData {
    private final SimpleIntegerProperty newClientId;
    private final SimpleIntegerProperty newDressId;

    public OrderData(int newClientId, int newDressId) {
        this.newClientId = new SimpleIntegerProperty(newClientId);
        this.newDressId = new SimpleIntegerProperty(newDressId);
    }

    public int getNewClientId() { return newClientId.get(); }
    public SimpleIntegerProperty newClientIdProperty() { return newClientId; }
    public void setNewClientId(int newClientId) { this.newClientId.set(newClientId); }

    public int getNewDressId() { return newDressId.get(); }
    public SimpleIntegerProperty newDressIdProperty() { return newDressId; }
    public void setNewDressId(int newDressId) { this.newDressId.set(newDressId); }
}
