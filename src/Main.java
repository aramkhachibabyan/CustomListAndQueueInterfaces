public class Main {
    public static void main(String[] args) {
        Human human1 = new Human("Aram", 25);
        Human human2 = new Human("Karen", 36);
        Human human3 = new Human("Anton", 12);
        Human human4 = new Human("Narek", 23);
        Human human5 = new Human("Abgar", 32);
        Human human6 = new Human("Sona", 21);

        MyCustomArrayList myCustomArrayList = new MyCustomArrayList();
        myCustomArrayList.add(human1);
        myCustomArrayList.add(human2);
        myCustomArrayList.add(human3);

        MyCustomCollection myCustomLinkedList = new MyCustomLinkedList();
        myCustomLinkedList.add(human4);
        myCustomLinkedList.add(human5);
        myCustomLinkedList.add(human6);
        System.out.println(myCustomLinkedList);
        System.out.println("__________________________________________________");
        System.out.println(myCustomLinkedList);

    }
}