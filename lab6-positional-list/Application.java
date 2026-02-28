public class Application {
    public static void main(String[] args) {

        //creates new positional list to store itinerary
        LinkedPositionalList<String> itinerary = new LinkedPositionalList<>();

       //adds stops to the end of list
        Position<String> paris = itinerary.addLast("Paris");
        Position<String> eiffel = itinerary.addLast("Eiffel Tower");
        Position<String> museum = itinerary.addLast("Musee d'Art Moderne de Paris");
        Position<String> garden = itinerary.addLast("Champ de Mars Garden");

        //print list using Iterable
        System.out.println("--- Original Itinerary ---");
        for (String stop : itinerary) {
            System.out.println("- " + stop);
        }

        //add stops in middle of itinerary
        itinerary.addAfter(eiffel, "Cafe Break");
        itinerary.addBefore(museum, "Seine River Walk");

        //prints final itinerary
        System.out.println("\n--- Final Itinerary (for-each loop) ---");
        for (String stop : itinerary) {
            System.out.println("- " + stop);
        }
    }
}
