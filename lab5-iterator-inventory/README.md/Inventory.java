import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Inventory {

  // creates inventory   
  private List<Item> items; 

    // Start with an empty inventory
    public Inventory() {
        items = new ArrayList<>();
    }

    // Adds an item to the inventory
    public void addItem(Item item) {
        items.add(item);
    }

    // Prints all items currently in the inventory
    public void display() {
        System.out.println("Inventory:");

        if (items.isEmpty()) {
            System.out.println(" - Inventory is empty)");
            return;
        }

        for (Item item : items) {
            System.out.println("- " + item);
        }
    }

    // Combining two items if both are found
    public void combineItems(String name1, String name2) {

        // stores items
        Item item1 = null;
        Item item2 = null;

        // Iterator to remove items safely while looping
        Iterator<Item> iter = items.iterator();

        while (iter.hasNext()) {
            Item current = iter.next();

            // Find and remove the first item
            if (item1 == null && current.getName().equalsIgnoreCase(name1)) {
                item1 = current;   
                iter.remove();     
            }
            // Find and remove the second item
            else if (item2 == null && current.getName().equalsIgnoreCase(name2)) {
                item2 = current;
                iter.remove();
            }

            // stops if both are found
            if (item1 != null && item2 != null) {
                break;
            }
        }

        // Only combine if both are found
        if (item1 != null && item2 != null) {
            String combinedName = name1 + " + " + name2;
            items.add(new Item(combinedName));
            System.out.println("Combined items into: " + combinedName);
        } else {
            System.out.println("Combine failed: missing items.");
        }
    }
}
