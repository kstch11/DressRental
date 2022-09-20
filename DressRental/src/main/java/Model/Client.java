package Model;

import javax.persistence.*;
import java.util.Collection;
import java.util.List;

@Entity
@Table(name = "Client")
public class Client {

    @Id
    @Column(name = "Personal_ID")
    private int personal_id;

    @Column(name = "Firstname")
    private String firstname;
    @Column(name = "Secondname")
    private String secondname;
    @Column(name = "Size")
    private int size;
    @Column(name = "Email")
    private String email;

    @ManyToMany
    @JoinTable(name = "Order_", joinColumns = @JoinColumn(name = "Client_ID"), inverseJoinColumns = @JoinColumn(name = "Dress_ID"))
    private Collection<Dress> dresses;


    public Client() {}

    public Client(int personal_id, String firstname, String secondname, int size, String email) {
        this.personal_id = personal_id;
        this.firstname = firstname;
        this.secondname = secondname;
        this.size = size;
        this.email = email;
    }


    public int getPersonal_id() { return personal_id; }
    public void setPersonal_id(int personal_id) { this.personal_id = personal_id; }

    public String getFirstname() { return firstname; }
    public void setFirstname(String firstname) { this.firstname = firstname; }

    public String getSecondname() { return  secondname; }
    public void setSecondname(String secondname) { this.secondname = secondname; }

    public int getSize() { return size; }
    public void setSize(int size) { this.size = size; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public Collection<Dress> getDresses() { return dresses; }
    public void setDresses(Collection<Dress> dresses) { this.dresses = dresses; }
}
