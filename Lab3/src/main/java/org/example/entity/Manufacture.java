package org.example;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "manufactures")
public class Manufacture {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", nullable = false)
    private String id;


    @Column(name = "Name", nullable = false, length = 128)
    private String name;

    @Column(name = "location")
    private String location;

    @Column(name = "employee")
    private int employee;

    @OneToMany(mappedBy = "manufacture")
    private List<Phone> phones;

    public String getId(){
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public int getEmployee() {
        return employee;
    }

    public void setEmployee(int employee) {
        this.employee = employee;
    }
}
