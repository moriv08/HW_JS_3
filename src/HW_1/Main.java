package HW_1;

public class Main {
    public static void main(String[] args) {

        // exercise a
        Box<Apple> appleBox = new Box<>(new Apple(), new Apple(), new Apple(), new Apple(), new Apple(), new Apple());
        Box<Orange> orangeBox = new Box<>(new Orange(), new Orange(), new Orange(), new Orange());
        Box<Orange> emptyOrangeBox = new Box<>();

        // exercise d
        System.out.println("В коробке " + appleBox.countFruits() + " ябл. Вес коробки " + appleBox.getWeight());
        System.out.println("В коробке " + orangeBox.countFruits() + " апелс. Вес коробки " + orangeBox.getWeight());

        System.out.println("____________");

        // exercise e
        if (appleBox.compare(orangeBox))
            System.out.println("Коробки равны");
        else
            System.out.println("Коробки не равны");


        System.out.println("____________");

        emptyOrangeBox.addFruit(new Orange());


        // exercise f
        System.out.println("Вес emptyOrangeBox до " + emptyOrangeBox.getWeight());
        System.out.println("Вес orangeBox до " + orangeBox.getWeight());
        System.out.println("");
        System.out.println("Пересыпаем фрукты...");
        System.out.println("");
        emptyOrangeBox.poureFruit(orangeBox);
        System.out.println("Вес emptyOrangeBox после " + emptyOrangeBox.getWeight());
        System.out.println("Вес orangeBox после " + orangeBox.getWeight());
    }
}
