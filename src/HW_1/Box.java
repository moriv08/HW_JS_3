package HW_1;

import java.util.ArrayList;
import java.util.Iterator;

public class Box<T extends Number> {
    private ArrayList<T> fruts = new ArrayList();

    public Box(T... fruts) {
        for (T t: fruts)
            this.fruts.add(t);
    }

    public double getWeight(){
        double answer = 0.0;
        for (T elem: this.fruts)
            answer += elem.doubleValue();
        return answer;
    }

    public boolean compare(Box<?> box){
        return ((this.getWeight() - box.getWeight()) > 0.0001) ? false : true;
    }

    public void addFruit(T... t){
        for (T elem: t) {
            this.fruts.add(elem);
        }
    }

    public void poureFruit(Box<T> box){

        for (Iterator<T> iter = box.fruts.iterator(); iter.hasNext();){
            this.addFruit(iter.next());
            iter.remove();
        }

//        for (int i = 0; i < box.fruts.size(); i++)
//            this.fruts.add(box.fruts.get(i));
//        for (int i = box.fruts.size() - 1; i >= 0; i--)
//            box.fruts.remove(box.fruts.get(i));
    }

    public int countFruits(){

        int counter = 0;
        for (T t: this.fruts)
            counter++;

        return counter;
    }

}
