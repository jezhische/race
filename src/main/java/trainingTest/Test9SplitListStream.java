package trainingTest;

import java.io.*;
import java.util.*;

/**
 * Created by Ежище on 30.06.2016.
 */
public class Test9SplitListStream implements List {
    public static void main(String[] args) {
        List<String> carObjects = new ArrayList<String>();
        String carObject;
        try (BufferedReader br = new BufferedReader(new FileReader("src//main//resources//pilotProbesData//Probe2.txt"))) {
            int i = 0;
            while ((carObject = br.readLine()) != null) {
//                System.out.println(i + 1 + ". " + carObject);
                carObjects.add(i, carObject + "\n");
                i++;
//                System.out.println(carObjects);
            }
        } catch (IOException | NullPointerException e) {
            System.out.println(e.getMessage());
        }
        try {
            System.out.println(carObjects);
            String newCarObject = "\"mashito\", \"cars.MashkaCar\", \"28\" ,\"598.5\", \"0.7\"\n";
            System.out.println(carObjects.get(2));
            System.out.println(carObjects.set(2, newCarObject));
            System.out.println(carObjects.get(2));
            carObjects.add(3, newCarObject);
            System.out.println(carObjects.set(2, newCarObject = "\"ferrfurr\", \"cars.FerraryCar\", \"22\", \"300\", \"0.3\"\n"));
            System.out.println(carObjects.get(2));
            System.out.println(carObjects.get(3));
            carObjects.add(4, newCarObject = "\"bemeka\", \"cars.BmwCar\", \"150\", \"100\", \"0.5\"");
//            carObjects.add(4, newCarObject = "\"ferrylo\", \"cars.FerrariCar\", \"12\", \"130\", \"0.6\""); // вот так
            // я просто заменю 4-й элемент на новый
//            carObjects.set(4, newCarObject = "\"ferrylo\", \"cars.FerrariCar\", \"12\", \"130\", \"0.6\""); // вот так
            // то же самое. Что за фигня?
        } catch (IndexOutOfBoundsException e) {
            System.out.println("IndexOutOfBoundsException:  " + e.getLocalizedMessage());
        }
        System.out.println(carObjects);
        System.out.println("");

        try (BufferedWriter bw = new BufferedWriter(new FileWriter("src//main//resources//pilotProbesData//Probe3.txt"));
             BufferedReader br = new BufferedReader(new FileReader("src//main//resources//pilotProbesData//Probe3.txt"))) {
            bw.write(String.valueOf(carObjects));
            bw.flush(); // интересно, что без этой строчки файл не прочитывался, хотя и записывался. Why? What the puzzle?
            String str;
            while ((str = br.readLine()) != null) {
                System.out.println(str);
            }

        } catch (IOException e) {
            System.out.println("IOException:  " + e.getLocalizedMessage());
        }
        System.out.printf("В списке %d элементов \n", carObjects.size());


    }

    @Override
    public int size() {
        return 0;
    }

    @Override
    public boolean isEmpty() {
        return false;
    }

    @Override
    public boolean contains(Object o) {
        return false;
    }

    @Override
    public Iterator iterator() {
        return null;
    }

    @Override
    public Object[] toArray() {
        return new Object[0];
    }

    @Override
    public boolean add(Object o) {
        return false;
    }

    @Override
    public boolean remove(Object o) {
        return false;
    }

    @Override
    public boolean addAll(Collection c) {
        return false;
    }

    @Override
    public boolean addAll(int index, Collection c) {
        return false;
    }

    @Override
    public void clear() {

    }

    @Override
    public Object get(int index) {
        return null;
    }

    @Override
    public Object set(int index, Object element) {
        return null;
    }

    @Override
    public void add(int index, Object element) {

    }

    @Override
    public Object remove(int index) {
        return null;
    }

    @Override
    public int indexOf(Object o) {
        return 0;
    }

    @Override
    public int lastIndexOf(Object o) {
        return 0;
    }

    @Override
    public ListIterator listIterator() {
        return null;
    }

    @Override
    public ListIterator listIterator(int index) {
        return null;
    }

    @Override
    public List subList(int fromIndex, int toIndex) {
        return null;
    }

    @Override
    public boolean retainAll(Collection c) {
        return false;
    }

    @Override
    public boolean removeAll(Collection c) {
        return false;
    }

    @Override
    public boolean containsAll(Collection c) {
        return false;
    }

    @Override
    public Object[] toArray(Object[] a) {
        return new Object[0];
    }
}
