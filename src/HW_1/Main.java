package HW_1;

import java.util.ArrayList;

public class Main {

    public static void main(String[] args){

        // 1 Exercise
        Integer[] arrInt = {1, 2, 3, 4, 5, 6, 7};
        String[] arrStr = {"one", "two", "three", "fore", "five"};
        print(arrStr);
        print(arrInt);
        changeElem(arrStr, 0, 4);
        changeElem(arrInt, 0, 5);
        print(arrStr);
        print(arrInt);

        // 2 Exercise
        ArrayList list = makeList(arrInt);
        ArrayList stringList = makeList(arrStr);

        printList(list);
        printList(stringList);





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

    private static<T> void changeElem(T[] arr, int a, int b){
        T tmp;

        tmp = arr[a];
        arr[a] = arr[b];
        arr[b] = tmp;
    }

    private static <T> void print(T[] arr){
        for (T t: arr)
            System.out.print(t + " ");
        System.out.println();
    }

    private static <T> ArrayList makeList(T[] arr){
        ArrayList<T> list = new ArrayList<T>();
        for (T t: arr)
            list.add(t);
        return list;
    }

    private static <T> void printList(ArrayList<T> list){
        for (T t: list)
            System.out.print(t + " ");
        System.out.println();
    }
}
