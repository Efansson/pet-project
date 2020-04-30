
public class Cat {
    /*

    private double quantityMeal;
    public double summ(double food) {
        quantityMeal = quantityMeal + food;
        System.out.println("Сумма съеденной еды = " + quantityMeal);
        return quantityMeal;
    */
    private double originWeight;
    private double weight;

    private double minWeight;
    public double maxWeight;

    public Cat () {
        weight = 1500.0 + 3000.0 * Math.random();
        originWeight = weight;
        minWeight = 1000.0;
        maxWeight = 9000.0;

    }

    public void eat () {
        weight = weight + 1;

    }

    public void pee () {
        weight = weight - 1;
        System.out.println("Crap");
    }

    public void meow () {
        weight = weight - 1;
        System.out.println("Meow");
    }

    public void feed ( Double amount ) {
        weight = weight + amount;
    }

    public void drink ( Double amount ) {
        weight = weight + amount;
    }

    public Double getWeight () {
        return weight;
    }

    public String getStatus () {
        if (weight < minWeight) {
            return "Dead";
        } else if (weight > maxWeight) {
            return "Exploded";
        } else if (weight > originWeight) {
            return "Sleeping";
        } else {
            return "Playing";
        }
    }

    /* public Double meatSumm () {
        if (weight > originWeight) {
            var m = weight - originWeight;
        }
        {
            System.out.println(meatSumm());
        }
        return null;
    }*/
}
