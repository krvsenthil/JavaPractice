package core;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public class StreamAPITest {
    public static void main(String[] args) {
        List<ProductItem> productItemList = new ArrayList<>(
                Arrays.asList(new ProductItem("Apple", 1200.00,10),
                        new ProductItem("Apple Pro", 1100.00,5),
                        new ProductItem("Samsung Note", 900.00,10),
                        new ProductItem("Oppo", 500.00,10),
                        new ProductItem("Micromax", 400.00,10),
                        new ProductItem("OnePlus", 1100.00,10))
        );

        /* Filtering - filter() */
        List<ProductItem> modifiedProductItemList = productItemList.stream().filter(p->p.getPrice() > 1000)
        /* Mapping - map(), flatmap() - to convert object into another type */
        .map(p->{
            p.setPrice(p.getPrice() + 100);
            return p;
        })
        /* Collect - toList() */
        .collect(Collectors.toList());
        System.out.println("Modified List Start --->");
        modifiedProductItemList.forEach(System.out::println);
        System.out.println("Modified List End --->");

        /* Flatmap - converts the list into stream  */
        List<Warehouse> warehouseList = new ArrayList<>(
                Arrays.asList(
                        new Warehouse(Arrays.asList(new ProductItem("W-Apple", 1200.00,10),
                                new ProductItem("W-Samsung Note", 900.00,10))),
                        new Warehouse(Arrays.asList(new ProductItem("W-OnePlus", 1100.00,10),
                                new ProductItem("W-Samsung Note", 900.00,10))),
                        new Warehouse(Arrays.asList(new ProductItem("W-Apple", 1200.00,10),
                                new ProductItem("W-Samsung Note", 900.00,10))
                        )
                ));
        ProductItem[] productItemsArray = warehouseList.stream()
                .flatMap(w->w.getProductItems().stream())
                .filter(p->p.getPrice()>1000)
                .map(p->{
                    p.setPrice(p.getPrice() - p.getPrice() * 0.01);
                    return p;
                }).toArray(ProductItem[]::new);
        System.out.println("Flatmap start -->");
        Arrays.stream(productItemsArray).forEach(System.out::println);
        System.out.println("Flatmap End -->");

        /* Slicing - limit(), reduce(), max(), count()*/
        System.out.println("<--Limit-->Start");
        productItemList.stream().limit(3).forEach(System.out::println);
        System.out.println("<--Limit-->End");

        System.out.println("Total Items-->"+
                modifiedProductItemList.stream().
                mapToInt(value ->value.getItemInWarehouse())
                .sum()+"<--");

        /* Reduction Operation - reduce(), collect() */
        System.out.println("Reduce-->"+productItemList.stream()
                .reduce((s1,s2)-> s1.getPrice() < s2.getPrice() ? s1 : s2)
                .get().getName());

        Map<String, ProductItem> productItemMap =
                productItemList.stream()
                .filter(p -> p.getPrice() > 1000)
                .collect(
                        Collectors.toMap(ProductItem::getName, Function.identity())
                );
        System.out.println("ProductMap:-->"+productItemMap);

        /* searching - findAny(), findFirst(), match() */
        ProductItem productItem = productItemList.stream().findFirst().get();
        System.out.println("FindFirst-->"+productItem);
    }
}

class Warehouse{
    private List<ProductItem> productItems;

    @Override
    public String toString() {
        return "core.Warehouse{" +
                "productItems=" + productItems +
                '}';
    }

    public List<ProductItem> getProductItems() {
        return productItems;
    }

    public void setProductItems(List<ProductItem> productItems) {
        this.productItems = productItems;
    }

    public Warehouse(List<ProductItem> productItems) {
        this.productItems = productItems;
    }
}

class ProductItem{
    private String name;
    private double price;
    private int itemInWarehouse;

    public ProductItem(String name, double price, int itemInWarehouse) {
        this.name = name;
        this.price = price;
        this.itemInWarehouse = itemInWarehouse;
    }

    @Override
    public String toString() {
        return "core.ProductItem{" +
                "name='" + name + '\'' +
                ", price=" + price +
                ", itemInWarehouse=" + itemInWarehouse +
                '}';
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getItemInWarehouse() {
        return itemInWarehouse;
    }

    public void setItemInWarehouse(int itemInWarehouse) {
        this.itemInWarehouse = itemInWarehouse;
    }
}
