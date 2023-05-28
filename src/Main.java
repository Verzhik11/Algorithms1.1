public class Main {
    public static void main(String[] args) {
        System.out.println("Hello world!");
        StringListImpl stringList = new StringListImpl(5);
        stringList.add("Карандаш");
        stringList.add("Ручка");
        stringList.add("Ручка");
        stringList.add(2, "Ластик");
        System.out.println(stringList.indexOf("Ручка"));
        stringList.clear();
        System.out.println(stringList);
        System.out.println(stringList.equals(stringList));



    }
}