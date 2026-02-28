// Tests if inventory works before and after combining
public class Application {

    public static void main(String[] args) {

        //creates new inventory object
        Inventory inv = new Inventory();

        // Adds items to inventory
        inv.addItem(new Item("Messi"));
        inv.addItem(new Item("The GOAT"));
        inv.addItem(new Item("You already know who it is"));

        // Shows inventory before combining
        System.out.println("--- BEFORE ---");
        inv.display();

        // Combines two items
        System.out.println("\n--- COMBINE Messi + The GOAT ---");
        inv.combineItems("Messi", "The GOAT");

        // Shows inventory after combining
        System.out.println("\n--- AFTER ---");
        inv.display();
    }
}
