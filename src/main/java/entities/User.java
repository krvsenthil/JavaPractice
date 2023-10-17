package entities;

import org.hibernate.annotations.CacheConcurrencyStrategy;
//import org.hibernate.resource.beans.container.spi.ExtendedBeanManager;

import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import org.hibernate.annotations.Cache;

@Entity(name = "corporate_user")
@Table(name = "corporate_user")
//@EntityListeners(ExtendedBeanManager.LifecycleListener.class)
@Cache(usage = CacheConcurrencyStrategy.READ_ONLY, region = "user")
public class User {

    //can have public/protected at class level
    //can have no args constructor and args constructor
    //cannot declare class or method as FINAL
    //Non-Entity classes may extend Entity classes
    //May need to implement serializable interface

    @Id
    //Persistence provider - DB's select automatically
    //@GeneratedValue(strategy = GenerationType.AUTO)

    //Relies on other db table incremented column to use
    //Performance is low as new value is created every insert but can do improve optimize the column
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    //used quite often - used for global uniqueness for distributed system
    //size of large compared to numeric
    //@GeneratedValue(strategy = GenerationType.UUID)

    //@GeneratedValue(strategy = GenerationType.SEQUENCE)
    //@SequenceGenerator(name = "user_generator", sequenceName = "user_seq_generator",allocationSize = 100)

    //@GeneratedValue(strategy = GenerationType.TABLE, generator = "user_generator")
    // Rarely used as it slows down app performance
    //@TableGenerator(name = "user_generator",table = "id_generator", schema = "entity")
    private int id;

/*    @OneToOne(cascade = CascadeType.PERSIST, mappedBy = "user")
    private UserProfile userProfile;*/

    /*@ManyToOne
    @JoinColumn(name = "user_profile_id")
    private UserProfile userProfile1;

    @ManyToMany
    @JoinTable(name="USER_USER_PROFILE", joinColumns = @JoinColumn(name = "user_id"),
    inverseJoinColumns = @JoinColumn(name="user_profile_id"))
    private Set<UserProfile> userProfileSet = new HashSet<>();
    */

    //Composite Primary Key - if primary Key consists of more than one column
    //@EmbeddedId
    //private UserCompositeKey userCompositeKey;
    //Or composite primary key created using @IdClass annotation and can have multiple @Id attributes in a class with id class.
    //dept id + project id

    @Column(name="user_name")
    private String name;

    @Column(name="date_of_birth")
    //converting to date/time to db
    //Temporal not used in Java10 onwards as its handled with local date
    //@Temporal(TemporalType.DATE)
    private Date dob;

    @Transient //not persistent in db
    private Integer age;

    @Enumerated(EnumType.STRING)
    private Gender gender;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    /*public UserCompositeKey getUserCompositeKey() {
        return userCompositeKey;
    }

    public void setUserCompositeKey(UserCompositeKey userCompositeKey) {
        this.userCompositeKey = userCompositeKey;
    }*/

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getDob() {
        return dob;
    }

    public void setDob(Date dob) {
        this.dob = dob;
    }

    public Integer getAge() {
        return age;
    }

    public User(String name, Date dob, Integer age, Gender gender) {
        this.name = name;
        this.dob = dob;
        this.age = age;
        this.gender = gender;
    }

    public User() {
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }


    /* Life cycle Event Handlers */
    @PrePersist
    public void handlePrePesistUser(){
        System.out.println("Before User is persisting into database.");
    }
    //or
    /*public void handlePrePesistUser(User user){
        System.out.println("Before User is persisting into database.-->"+user.getName());
    }*/

/*
    public UserProfile getUserProfile() {
        return userProfile;
    }

    public void setUserProfile(UserProfile userProfile) {
        this.userProfile = userProfile;
    }
*/


    /*@PostPersist
    @PreRemove
    @PostRemove
    @PreUpdate
    @PostUpdate
    @PostLoad*/

}

enum Gender {
    MALE, FEMALE
}
