package core;

import java.util.*;
import java.util.function.Function;
import java.util.function.Predicate;

public class LambdaExpressionExample {
    public static void main(String[] args) {
        DistanceCalculator ds = (d1, d2) -> d1 - d2;
        System.out.println("Result:-->"+ds.apply(20, 10));
        GoogleDistanceCalculator gdd = new GoogleDistanceCalculator(3, 3);
        DistanceCalculator ds1 = gdd::gDistanceCalculator;
        System.out.println("Result1-->"+ds1.apply(30,20));
    }
}

class GoogleDistanceCalculator{
    double d1 = 0;
    GoogleDistanceCalculator(double d1, double d2){
        this.d1 = d1;
    }
    double gDistanceCalculator(double d1, double d2){
        return (d1 + this.d1) - d2;
    }
}

class FunctionalClass {
    public static void main(String[] args) {
        Map<Integer, String> map = new HashMap<>();
        map.put(1, "One");
        map.put(2,"Two");
        map.put(3,"Three");
        //Function - Functional Interface
        map.computeIfAbsent(4, (k)->Integer.toString(k));
        //BiFunction - Functional Interface
        map.computeIfPresent(4, (k,v)-> k + ":" + v);
        System.out.println("Map Values-->"+map);
        List<Product> products = new ArrayList<>(Arrays.asList(
                new Product(1,10.10), new Product(2,20.23),
                new Product(3,32.22), new Product(4,5.2)
        ));
        Map<Product, Double> productMap = new HashMap();
        for(Product p : products){
            Function<Product, Double> getPriceFunction = p2 -> p2.getPrice();
            Function<Double, Double> getDiscountFunction = price -> price * 0.05;
            System.out.println("Price:-->"+getPriceFunction.apply(p));
            Function<Product, Double> getAndThen = getPriceFunction.andThen(getDiscountFunction);
            //above line and below methods same output only the execution is different
            Function<Product, Double> getCompose = getDiscountFunction.compose(getPriceFunction);
            productMap.putIfAbsent(p, getAndThen.apply(p));
        }
        System.out.println("Products:-->"+productMap);
        products.sort(Comparator.comparing(Product::getPrice));
        // or above and below are same
        products.sort(Comparator.comparing(p->p.getPrice()));
        System.out.println("Sorted Products-->"+products);
        //Consumer example
        products.iterator().forEachRemaining(
                p->p.setPrice(p.getPrice()+100)
        );
        System.out.println("State Change->"+products);
        //remove product if price >200; using predicate
        Predicate<Product> pre = p -> p.getPrice() > 120;
        products.removeIf(pre);
        System.out.println("After Remove:-->"+products);
    }
}

class SortingWords{
    public static void main(String[] args) {
        List<String> stringList = new ArrayList<>(Arrays.asList(
                "apple", "windows", "oracle", "microsoft", "Apple", "zpple"
        ));
        stringList.sort(Comparator.comparing(String::length));
        System.out.println("Output:-->"+stringList);
    }
}

class Product {
    private int id;
    private double price;

    public Product(int id, double price) {
        this.id = id;
        this.price = price;
    }

    @Override
    public String toString() {
        return "core.Product{" +
                "id=" + id +
                ", price='" + price + '\'' +
                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }
}
