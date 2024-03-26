package org.example.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "Manufacture")
public class Manufacture implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;
    @Column(nullable = false, length = 128)
    public String name;
    @Column(nullable = false)
    public String location;
    @Column(nullable = false)
    public int employee;

    @lombok.Setter
    @OneToMany(mappedBy = "manufacture")
    private List<Phone> phones;

    public Manufacture(String name, String location, int employee) {
        this.name = name;
        this.location = location;
        this.employee = employee;
    }

    public Manufacture() {

    }

    public List<Phone> getPhones() {
        return phones;
    }

    public void setPhones(List<Phone> phones) {
        this.phones = phones;
    }

    public String getLocation() {
        return location;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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
    public String toString(){
        return id + "/" + employee + "/" + location;
    }
}
