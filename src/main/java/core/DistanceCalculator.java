package core;

@FunctionalInterface
public interface DistanceCalculator {
    double apply(double city1, double city2);
    //can have any number of static and default methods
}
