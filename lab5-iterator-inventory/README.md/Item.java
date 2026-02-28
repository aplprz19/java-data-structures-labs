public class Item {

    //name of item
    private String name;

    // creates item and name
    public Item(String name) {
        this.name = name;
    }

    // Lets Inventory read the item name when searching for it
    public String getName() {
        return name;
    }

    //prints readable
    @Override
    public String toString() {
        return name;
    }
}
