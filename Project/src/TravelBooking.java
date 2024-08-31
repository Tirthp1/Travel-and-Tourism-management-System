
import java.util.*;

class Person {
    private String custId;
    private String custName;
    private int age;

    public Person(String custId, String custName, int age) {
        this.custId = custId;
        this.custName = custName;
        this.age = age;
    }

    public void display() {
        System.out.println("Customer ID: " + custId);
        System.out.println("Customer Name: " + custName);
        System.out.println("Age: " + age);
    }
}

class Customer extends Person {
    private Map<String, List<Object>> dict = new HashMap<>();
    private List<Integer> ageList = new ArrayList<>();
    int noc;

    public Customer(int noc) {
        super("", "", 0);
        this.noc = noc;
        Scanner scanner = new Scanner(System.in);
        
        for (int i = 0; i < noc; i++) {
            List<Object> info = new ArrayList<>();
            System.out.println("Enter customer ID:");
            String custId = scanner.nextLine();
            System.out.println("Enter customer name:");
            String custName = scanner.nextLine();
            System.out.println("Enter age:");
            int custAge = Integer.parseInt(scanner.nextLine());
            
            info.add(custName);
            info.add(custAge);
            ageList.add(custAge);
            dict.put(custId, info);
            new Person(custId, custName, custAge);
        }
        System.out.println("Customer dictionary: " + dict);
    }

    public void display() {
        System.out.println("Customer dictionary: " + dict);
    }

    public Map<String, List<Object>> getDict() {
        return dict;
    }

    public List<Integer> getAgeList() {
        return ageList;
    }
}

class Booking extends Customer {
    private int numPersons;
    private List<String> vPlace = new ArrayList<>();
    private int inOut;
    private String cFaci = "";
    
    public Booking() {
        super(0);
        Scanner scanner = new Scanner(System.in);
        System.out.println("How many persons:");
        numPersons = Integer.parseInt(scanner.nextLine());
        setNoc(numPersons);

        // Handle input and other initialization
        // States and places
        List<String> listState = Arrays.asList("Gujarat", "Maharashtra", "Jammu & Kashmir", "Uttrakhand", "Assam", "Kerala");
        Map<String, List<String>> statePlaces = new HashMap<>();
        statePlaces.put("Gujarat", Arrays.asList("Somnath", "Kutch", "Diu", "Saputara", "Gir National Park", "Ambaji", "Narmada"));
        statePlaces.put("Maharashtra", Arrays.asList("Gateway of India", "Amusement Park", "Juhu Beach", "Ajanta and Ellora Caves", "Mahabaleshwar", "Lonavala", "Rajmachi", "Khandala"));
        statePlaces.put("Jammu & Kashmir", Arrays.asList("Pahalgam", "Sonmarg", "Gulmarg", "Srinagar", "Vaishno Devi", "Gurez Valley"));
        statePlaces.put("Uttrakhand", Arrays.asList("Rishikesh", "Nainital", "Dehradun", "Mussoorie", "Jim Corbett National Park", "Kedarnath", "Almora"));
        statePlaces.put("Assam", Arrays.asList("Kaziranga National Park", "Majuli Island", "Kakochang Waterfalls", "Pobitora Wildlife Sanctuary", "Guwahati"));
        statePlaces.put("Kerala", Arrays.asList("Munnar", "Alleppey", "Kochi", "Wayanad", "Poovar", "Thrissur", "Nilambur"));

        // Countries
        List<String> countries = Arrays.asList("Maldives", "Bali", "Malaysia", "Dubai", "Thailand", "Singapore", "U.S.", "Paris", "Hong Kong");
        
        System.out.println("Where do you want to go for the trip?");
        System.out.println("1. IN INDIA");
        System.out.println("2. OUT OF INDIA");
        inOut = Integer.parseInt(scanner.nextLine());
        
        if (inOut == 1) {
            System.out.println("Select a state:");
            for (int i = 0; i < listState.size(); i++) {
                System.out.println((i + 1) + " : " + listState.get(i));
            }
            int stateIndex = Integer.parseInt(scanner.nextLine()) - 1;
            List<String> places = statePlaces.get(listState.get(stateIndex));

            for (int i = 0; i < statePlaces.get(listState.get(stateIndex)).size(); i++) {
                System.out.println((i + 1) + " : " + statePlaces.get(listState.get(stateIndex)).get(i));
            }

            System.out.println("How many places do you want to go to?");
            int numPlaces = Integer.parseInt(scanner.nextLine());
            
            for (int i = 0; i < numPlaces; i++) {
                System.out.println("Enter place number:");
                int placeIndex = Integer.parseInt(scanner.nextLine()) - 1;
                vPlace.add(places.get(placeIndex));
            }

            System.out.println("Places you want to visit: " + vPlace);
        } else if (inOut == 2) {
            System.out.println("Select a country:");
            for (int i = 0; i < countries.size(); i++) {
                System.out.println((i + 1) + " : " + countries.get(i));
            }
            int countryIndex = Integer.parseInt(scanner.nextLine()) - 1;
            vPlace.add(countries.get(countryIndex));
        }
    }

    public void transport() {
        Scanner scanner = new Scanner(System.in);
        List<String> transport = Arrays.asList("Bus", "Train", "Flight");
        Map<String, List<String>> flights = new HashMap<>();
        flights.put("IndiGo", Arrays.asList("Apackage", "Bpackage", "Cpackage"));
        flights.put("Air India", Arrays.asList("Apackage", "Bpackage", "Cpackage"));
        flights.put("SpiceJet", Arrays.asList("Apackage", "Bpackage", "Cpackage"));
        flights.put("Go First", Arrays.asList("Apackage", "Bpackage", "Cpackage"));
        flights.put("AirAsia India", Arrays.asList("Apackage", "Bpackage", "Cpackage"));
        flights.put("Vistara", Arrays.asList("Apackage", "Bpackage", "Cpackage"));
        flights.put("Alliance Air", Arrays.asList("Apackage", "Bpackage", "Cpackage"));
        flights.put("TruJet", Arrays.asList("Apackage", "Bpackage", "Cpackage"));
        
        Map<String, String> packages = new HashMap<>();
        packages.put("Apackage", "with AC and with best food facilities in transport and 5-star hotel for stay");
        packages.put("Bpackage", "with AC and with better food facilities in transport and 4-star hotel for stay");
        packages.put("Cpackage", "without AC and with food facilities in transport and 3-star hotel for stay");
        packages.put("Dpackage", "without AC and without food facilities in transport and 3-star hotel for stay");
        
        if (inOut == 1) {
            System.out.println("TRANSPORT FACILITIES:");
            for (int i = 0; i < transport.size(); i++) {
                System.out.println((i + 1) + " : " + transport.get(i));
            }
            
            int transportChoice = Integer.parseInt(scanner.nextLine());
            if (transportChoice == 1 || transportChoice == 2) {
                System.out.println("You chose " + transport.get(transportChoice - 1) + " for transportation");
                for (Map.Entry<String, String> entry : packages.entrySet()) {
                    System.out.println(entry.getKey() + " : " + entry.getValue());
                }
                cFaci = scanner.nextLine();
                System.out.println("You chose package " + cFaci + " with facilities: " + packages.get(cFaci));
            } else if (transportChoice == 3) {
                System.out.println("You chose " + transport.get(transportChoice - 1) + " for transportation");
                for (Map.Entry<String, List<String>> entry : flights.entrySet()) {
                    System.out.println(entry.getKey() + ": " + entry.getValue());
                }
                String flightChoice = scanner.nextLine();
                System.out.println("You chose flight " + flightChoice);
                for (Map.Entry<String, String> entry : packages.entrySet()) {
                    if (entry.getKey().equals("Cpackage")) {
                        break;
                    }
                    System.out.println(entry.getKey() + " : " + entry.getValue());
                }
                cFaci = scanner.nextLine();
                System.out.println("You chose package " + cFaci + " with flight " + flightChoice);
            }
        } else if (inOut == 2) {
            for (Map.Entry<String, List<String>> entry : flights.entrySet()) {
                System.out.println(entry.getKey() + ": " + entry.getValue());
            }
            String flightChoice = scanner.nextLine();
            System.out.println("You chose flight " + flightChoice);
        }
    }

    public void total() {
        int total = 0;
        Map<String, Integer> placePrices = new HashMap<>();
        placePrices.put("Somnath", 1000);
        placePrices.put("Kutch", 2000);
        placePrices.put("Diu", 1400);
        placePrices.put("Saputara", 1000);
        placePrices.put("Gir National Park", 1300);
        placePrices.put("Ambaji", 1000);
        placePrices.put("Narmada", 1400);
        placePrices.put("Gateway of India", 1500);
        placePrices.put("Amusement Park", 2400);
        placePrices.put("Juhu Beach", 1500);
        placePrices.put("Ajanta and Ellora Caves", 1200);
        placePrices.put("Mahabaleshwar", 1000);
        placePrices.put("Lonavala", 2000);
        placePrices.put("Rajmachi", 1000);
        placePrices.put("Khandala", 1400);
        placePrices.put("Pahalgam", 1300);
        placePrices.put("Sonmarg", 1400);
        placePrices.put("Gulmarg", 1500);
        placePrices.put("Srinagar", 2000);
        placePrices.put("Vaishno Devi", 1200);
        placePrices.put("Gurez Valley", 1300);
        placePrices.put("Rishikesh", 1500);
        placePrices.put("Nainital", 1300);
        placePrices.put("Dehradun", 1500);
        placePrices.put("Mussoorie", 1400);
        placePrices.put("Jim Corbett National Park", 1400);
        placePrices.put("Kedarnath", 1400);
        placePrices.put("Almora", 1300);
        placePrices.put("Kaziranga National Park", 1350);
        placePrices.put("Majuli Island", 1550);
        placePrices.put("Kakochang Waterfalls", 1200);
        placePrices.put("Pobitora Wildlife Sanctuary", 1400);
        placePrices.put("Guwahati", 2000);
        placePrices.put("Munnar", 2140);
        placePrices.put("Alleppey", 1560);
        placePrices.put("Kochi", 1400);
        placePrices.put("Wayanad", 1560);
        placePrices.put("Poovar", 1580);
        placePrices.put("Thrissur", 1300);
        placePrices.put("Nilambur", 1340);
        placePrices.put("Maldives", 154000);
        placePrices.put("Bali", 152000);
        placePrices.put("Malaysia", 142000);
        placePrices.put("Dubai", 132000);
        placePrices.put("Thailand", 120000);
        placePrices.put("Singapore", 152000);
        placePrices.put("U.S.", 200000);
        placePrices.put("Paris", 201000);
        placePrices.put("Hong Kong", 301000);

        Map<String, Integer> flightPrices = new HashMap<>();
        flightPrices.put("Apackage", 6000);
        flightPrices.put("Bpackage", 5250);
        flightPrices.put("Cpackage", 3800);
        flightPrices.put("Dpackage", 2500);

        for (String place : vPlace) {
            if (placePrices.containsKey(place)) {
                int placePrice = placePrices.get(place);
                int ageDiscount = 0;
                
                if (flightPrices.containsKey(cFaci)) {
                    for (int age : getAgeList()) {
                        if (age < 18 && age > 9) {
                            total += (placePrice - 500);
                            total += (flightPrices.get(cFaci) - 1600);
                        } else if (age < 9) {
                            total += (placePrice - 1000);
                            total += (flightPrices.get(cFaci) - 2500);
                        } else {
                            total += placePrice;
                            total += flightPrices.get(cFaci);
                        }
                    }
                } else {
                    for (int age : getAgeList()) {
                        if (age < 18 && age > 9) {
                            total += (placePrice - 39500);
                        } else if (age < 9) {
                            total += (placePrice - 65000);
                        } else {
                            total += placePrice;
                        }
                    }
                }
            }
        }
        
        System.out.println("TOTAL: " + total);
        System.out.println("YOUR TRIP BOOKED!!!");
    }

    public void setNoc(int noc) {
        super.noc = noc;
        
    }
}

public class TravelBooking {
    public static void main(String[] args) {
        Booking booking = new Booking();
        booking.transport();
        booking.total();
    }
}



