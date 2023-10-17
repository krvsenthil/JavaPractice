package entities;

import javax.persistence.*;

@Entity
@Table(name = "user_profile")
public class UserProfile {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int Id;

//    @OneToOne
//    private User user;

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

/*    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }*/

    /*@OneToMany(cascade = CascadeType.ALL, mappedBy = "userProfile1")
    private List<User> userList = new ArrayList<>();

    @ManyToMany(cascade = CascadeType.ALL, mappedBy = "userProfileSet")
    private Set<User> userSet = new HashSet<>();*/
}
