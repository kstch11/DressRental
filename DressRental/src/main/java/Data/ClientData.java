package Data;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;


public class ClientData {
    private final SimpleIntegerProperty newPersonalId;
    private final SimpleStringProperty newFirstname;
    private final SimpleStringProperty newSecondname;
    private final SimpleIntegerProperty newSize;
    private final SimpleStringProperty newEmail;

    public ClientData(int newPersonalId, String newFirstname, String newSecondname, int newSize, String newEmail) {
        this.newPersonalId = new SimpleIntegerProperty(newPersonalId);
        this.newFirstname = new SimpleStringProperty(newFirstname);
        this.newSecondname = new SimpleStringProperty(newSecondname);
        this.newSize = new SimpleIntegerProperty(newSize);
        this.newEmail = new SimpleStringProperty(newEmail);
    }

    public int getNewPersonalId() { return newPersonalId.get(); }
    public SimpleIntegerProperty newPersonalIdProperty() { return newPersonalId; }
    public void setNewPersonalId(int newPersonalId) { this.newPersonalId.set(newPersonalId); }

    public String getNewFirstname() { return newFirstname.get(); }
    public SimpleStringProperty newFirstnameProperty() { return newFirstname; }
    public void setNewFirstname(String newFirstname) { this.newFirstname.set(newFirstname); }

    public String getNewSecondname() { return newSecondname.get(); }
    public SimpleStringProperty newSecondnameProperty() { return newSecondname; }
    public void setNewSecondname(String newSecondname) { this.newSecondname.set(newSecondname); }

    public int getNewSize() { return newSize.get(); }
    public SimpleIntegerProperty newSizeProperty() { return newSize; }
    public void setNewSize(int newSize) { this.newSize.set(newSize); }

    public String getNewEmail() { return newEmail.get(); }
    public SimpleStringProperty newEmailProperty() { return newEmail; }
    public void setNewEmail(String newEmail) { this.newEmail.set(newEmail); }
}
