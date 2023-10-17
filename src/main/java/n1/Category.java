package n1;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity(name = "category_problem")
@Table(name = "category_problem")
@NamedEntityGraph(name = "cat-entity-graph", attributeNodes = {
        @NamedAttributeNode("products")
})
public class Category {

    public Category() {
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "category_name")
    private String categoryName;

    @Column(name="is_available")
    private boolean isAvailable;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "category", fetch = FetchType.EAGER)
    private List<Product> products = new ArrayList<>();

    public Category(int id, String categoryName, boolean isAvailable, List<Product> products) {
        this.id = id;
        this.categoryName = categoryName;
        this.isAvailable = isAvailable;
        this.products = products;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }

    public boolean isAvailable() {
        return isAvailable;
    }

    public void setAvailable(boolean available) {
        isAvailable = available;
    }
}
