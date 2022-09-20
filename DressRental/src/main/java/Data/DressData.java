package Data;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class DressData {
    private final SimpleIntegerProperty newSerialNumber;
    private final SimpleIntegerProperty new_Size;
    private final SimpleStringProperty newStyle;
    private final SimpleStringProperty newBrand;
    private final SimpleStringProperty newColor;

    public DressData(int newSerialNumber, int newSize, String newStyle, String newBrand, String newColor) {
        this.newSerialNumber = new SimpleIntegerProperty(newSerialNumber);
        this.new_Size = new SimpleIntegerProperty(newSize);
        this.newStyle = new SimpleStringProperty(newStyle);
        this.newBrand = new SimpleStringProperty(newBrand);
        this.newColor = new SimpleStringProperty(newColor);
    }

    public int getNewSerialNumber() { return newSerialNumber.get(); }
    public SimpleIntegerProperty newSerialNumberProperty() { return newSerialNumber; }
    public void setNewSerialNumber(int newSerialNumber) { this.newSerialNumber.set(newSerialNumber); }

    public int getNewSize1() { return new_Size.get(); }
    public SimpleIntegerProperty newSize1Property() { return new_Size; }
    public void setNewSize1(int newSize) { this.new_Size.set(newSize); }

    public String getNewStyle() { return newStyle.get(); }
    public SimpleStringProperty newStyleProperty() { return newStyle; }
    public void setNewStyle(String newStyle) { this.newStyle.set(newStyle); }

    public String getNewBrand() { return newBrand.get(); }
    public SimpleStringProperty newBrandProperty() { return newBrand; }
    public void setNewBrand(String newBrand) { this.newBrand.set(newBrand); }

    public String getNewColor() { return newColor.get(); }
    public SimpleStringProperty newColorProperty() { return newBrand; }
    public void setNewColor(String newColor) { this.newColor.set(newColor); }
}
