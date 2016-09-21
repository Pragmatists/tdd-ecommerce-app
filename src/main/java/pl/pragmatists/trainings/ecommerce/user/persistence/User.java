package pl.pragmatists.trainings.ecommerce.user.persistence;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class User {

    @Id
    private long id;
    private String name;

    private User() {

    }

    public User(long id, String name) {

        this.id = id;
        this.name = name;
    }
}
