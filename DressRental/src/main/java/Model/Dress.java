package Model;

import javax.persistence.*;
import java.util.Collection;
import java.util.List;

@Entity
@Table(name = "Dress")
public class Dress {

    @Id
    @Column(name = "Serial_number")
    private int serialNumber;

    @Column(name = "Size")
    private int size;
    @Column(name = "Style")
    private String style;
    @Column(name = "Brand")
    private String brand;
    @Column(name = "Color")
    private String color;

    @ManyToMany(mappedBy = "dresses")
    private List<Client> clients;

    public Dress() {}

    public Dress(int serialNumber, int size, String style, String brand, String color) {
        this.serialNumber = serialNumber;
        this.size = size;
        this.style = style;
        this.brand = brand;
        this.color = color;
    }

    public int getSerialNumber() { return serialNumber; }
    public void setSerialNumber(int serialNumber) { this.serialNumber = serialNumber; }

    public int getSize() { return size; }
    public void setSize(int size) { this.size = size; }

    public String getStyle() { return style; }
    public void setStyle(String style) {this.style = style; }

    public String getBrand() { return brand; }
    public void setBrand(String brand) { this.brand = brand; }

    public String getColor() { return color; }
    public void setColor(String color) { this.color = color; }
}
