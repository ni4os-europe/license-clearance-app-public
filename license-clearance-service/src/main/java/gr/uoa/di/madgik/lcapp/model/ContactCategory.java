package gr.uoa.di.madgik.lcapp.model;

import javax.persistence.*;

@Entity
public class ContactCategory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "email")
    private String email;

    @Column(name = "name")
    private String name;

    @Column(name = "friendly_name")
    private String friendlyName;

    public ContactCategory(Long id, String email, String name, String friendlyName) {
        this.id = id;
        this.email = email;
        this.name = name;
        this.friendlyName = friendlyName;
    }

    public ContactCategory() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFriendlyName() {
        return friendlyName;
    }

    public void setFriendlyName(String friendlyName) {
        this.friendlyName = friendlyName;
    }
}
