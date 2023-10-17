package n1;

import javax.persistence.*;

@Entity(name = "product_problem")
public class Product {
    public Product(){

    }

    public Product(String productName, Category category) {
        this.productName = productName;
        this.category = category;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name="product_name")
    private String productName;

    @ManyToOne
    private Category category;
}
