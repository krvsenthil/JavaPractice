package core;

public class OpenClosed {

    public interface Operation{
        int perform();
    }

    static class Addition implements Operation {
        int a;
        int b;
        public void assignValues(int a, int b){
            this.a = a; this.b = b;
        }

        public int perform(){
            int c = a + b;
            return c;
        }
    }

   static class Subtraction implements Operation {
        int a;
        int b;
        public void assignValues(int a, int b){
            this.a = a; this.b = b;
        }
        public int perform(){
            int c = a - b;
            return c;
        }
    }

    static class Multiply implements Operation {
        int a;
        int b;
        public void assignValues(int a, int b){
            this.a = a; this.b = b;
        }
        public int perform(){
            int c = a * b;
            return c;
        }
    }

    int calculate (Operation operation){
        return operation.perform();
    }

    public static void main(String[] args) {
        OpenClosed openClosed = new OpenClosed();
        Addition addition = new Addition();
        addition.assignValues(10, 20);
        Subtraction subtraction = new Subtraction();
        subtraction.assignValues(20, 10);
        Multiply multiply = new Multiply();
        multiply.assignValues(20, 10);
        System.out.println(openClosed.calculate(addition));
        System.out.println(openClosed.calculate(subtraction));
        System.out.println(openClosed.calculate(multiply));
    }

}
