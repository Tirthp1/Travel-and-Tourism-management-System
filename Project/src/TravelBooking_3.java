import java.util.*;
import java.io.*;
import java.util.stream.Collectors;

class IDException extends Exception {
    IDException(String message) {
        super(message);
    }
}

class Person_3 {
    public String custId;
    public String custName;
    public int age;

    public Person_3(String custId, String custName, int age) {
        this.custId = custId;
        this.custName = custName;
        this.age = age;
    }

    public void display() {
        System.out.println("Customer ID: " + custId);
        System.out.println("Customer Name: " + custName);
        System.out.println("Age: " + age);
    }

    public String getCustId() {
        return custId;
    }
}

class Customer_3 extends Person_3 {
    public Map<String, List<Object>> dict = new HashMap<>();
    public List<Integer> ageList = new ArrayList<>();
    public List<String> idList = new ArrayList<String>();
    public int noc;

    public Customer_3(int noc) {
        super("", "", 0);
        this.noc = noc;
    }

    public void addCustomer(String custId, String custName, int age) {
        List<Object> info = new ArrayList<>();
        info.add(custName);
        info.add(age);
        ageList.add(age);
        dict.put(custId, info);
        new Person_3(custId, custName, age);
    }

    public void removeCustomer(String custId) {
        List<Object> info = dict.remove(custId);
        if (info != null) {
            int age = (int) info.get(1);
            ageList.remove((Integer) age);
            System.out.println("Customer with ID " + custId + " removed.");
        } else {
            System.out.println("No customer found with ID " + custId);
        }
    }

    public void displayCustomerInfo(String custId) {
        List<Object> info = dict.get(custId);
        if (info != null) {

            System.out.println("Customer ID: " + custId);
            System.out.println("Customer Name: " + info.get(0));
            System.out.println("Age: " + info.get(1));

            Booking_3 booking = new Booking_3();
            booking.getTripInfoForCustomer(custId);
        } else {
            System.out.println("No customer found with ID " + custId);
        }
    }

    public void displayAllCustomers() {
        if (dict.isEmpty()) {
            System.out.println("No customers to display.");
        } else {
            Booking_3 booking = new Booking_3();
            System.out.println("All Customers:");
            for (Map.Entry<String, List<Object>> entry : dict.entrySet()) {
                String custId = entry.getKey();
                List<Object> info = entry.getValue();
                System.out.println("Customer ID: " + custId);
                System.out.println("Customer Name: " + info.get(0));
                System.out.println("Age: " + info.get(1));

                booking.getTripInfoForCustomer(custId);
            }
            System.out.println(
                    "-------------------------------------------------------------------------------------------------");
        }
    }

    public Map<String, List<Object>> getDict() {
        return dict;
    }

    public List<Integer> getAgeList() {
        return ageList;
    }

    public void setNoc(int noc) {
        this.noc = noc;
    }
}

class Booking_3 extends Customer_3 {
    public int numPersons;
    int junum = 0;
    public List<Object> vPlace = new ArrayList<>();
    public List<Object> idlist2 = new ArrayList<>();
    Map<String, List<String>> customerTrips = new HashMap<>();
    public int inOut;
    public String cFaci = "";
    int total_1;
    public static final String FILE_PATH = "trips.txt";

    public Booking_3() {
        super(0);

    }

    public void readFromFile() {
        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_PATH))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] data = line.split(",");

                String custId = data[0];
                String custName = data[1];
                int age = Integer.parseInt(data[2]);
                int noc = Integer.parseInt(data[3]);
                int inOut = Integer.parseInt(data[4]);

                List<String> places = Arrays.asList(data[5].split(";"));

                String transportAndFacility = data[6];

                addCustomer(custId, custName, age);
                setNoc(noc);
                this.inOut = inOut;
                vPlace.clear();
                vPlace.addAll(places);
                cFaci = transportAndFacility;
            }
        } catch (IOException e) {
            System.out.println("Error reading from file: " + e.getMessage());
        }
    }

    public void writeToFile() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_PATH,true))) {

            for (Map.Entry<String, List<Object>> entry : dict.entrySet()) {
                String custId = entry.getKey();
                List<Object> info = entry.getValue();
                String custName = (String) info.get(0);
                int age = (int) info.get(1);
                List<Object> places = vPlace;
                List<String> vPlaceStrings = vPlace.stream().map(Object::toString).collect(Collectors.toList());

                writer.write(String.format("%s,%s,%d,%d,%s,%s\n",
                        custId, custName, age, noc,
                        ""+customerTrips.values(), cFaci));
            }
        } catch (IOException e) {
            System.out.println("Error writing to file: " + e.getMessage());
        }
    }

    public void getTripInfoForCustomer(String custId) {
        Map<String, List<Object>> dict = getDict();
        if (dict.containsKey(custId)) {

            System.out.println("Customer ID: " + custId);
            System.out.println("Places to visit: " + vPlace);

            int totalCost = total_1;
            System.out.println("Total cost: " + totalCost);
        } else {
            System.out.println("No trip information found for customer ID: " + customerTrips.values());
        }
    }

    public void addTrip(Scanner scanner) throws IDException {

        System.out.println("How many persons will go on the trip?");
        numPersons = Integer.parseInt(scanner.nextLine());
        setNoc(numPersons);
        junum = numPersons;

        for (int i = 0; i < numPersons; i++) {
            int p=1;

            while(p==1){
                try {
                System.out.println("Enter customer ID:");
                 String custId = scanner.nextLine();
                 if (idlist2.contains(custId)) {
                    throw new IDException("ID already available ");
                          }
                          else {
                            idlist2.add(custId);
                            idList.add(custId);
                            p=0;
                        }
                } catch (IDException e) {
                    System.out.println(e);
                }
                
            }
            
            System.out.println("Enter customer name:");
            String custName = scanner.nextLine();
            System.out.println("Enter age:");
            int custAge = Integer.parseInt(scanner.nextLine());

            addCustomer(custId, custName, custAge);
        }

        List<String> listState = Arrays.asList("Gujarat", "Maharashtra", "Jammu & Kashmir", "Uttrakhand", "Assam",
                "Kerala");
        Map<String, List<String>> statePlaces = new HashMap<>();
        statePlaces.put("Gujarat",
                Arrays.asList("Somnath", "Kutch", "Diu", "Saputara", "Gir National Park", "Ambaji", "Narmada"));
        statePlaces.put("Maharashtra", Arrays.asList("Gateway of India", "Amusement Park", "Juhu Beach",
                "Ajanta and Ellora Caves", "Mahabaleshwar", "Lonavala", "Rajmachi", "Khandala"));
        statePlaces.put("Jammu & Kashmir",
                Arrays.asList("Pahalgam", "Sonmarg", "Gulmarg", "Srinagar", "Vaishno Devi", "Gurez Valley"));
        statePlaces.put("Uttrakhand", Arrays.asList("Rishikesh", "Nainital", "Dehradun", "Mussoorie",
                "Jim Corbett National Park", "Kedarnath", "Almora"));
        statePlaces.put("Assam", Arrays.asList("Kaziranga National Park", "Majuli Island", "Kakochang Waterfalls",
                "Pobitora Wildlife Sanctuary", "Guwahati"));
        statePlaces.put("Kerala",
                Arrays.asList("Munnar", "Alleppey", "Kochi", "Wayanad", "Poovar", "Thrissur", "Nilambur"));

        // Countries
        List<String> countries = Arrays.asList("Maldives", "Bali", "Malaysia", "Dubai", "Thailand", "Singapore", "U.S.",
                "Paris", "Hong Kong");

        System.out.println("Where do you want to go for the trip?");
        System.out.println("1. IN INDIA");
        System.out.println("2. OUT OF INDIA");
        int count = 0;
        List<String> tripPlaces = new ArrayList<>();
        try {
            inOut = Integer.parseInt(scanner.nextLine());
            switch (inOut) {
                case 1:
                    count = 1;
                    System.out.println("Select a state:");
                    for (int i = 0; i < listState.size(); i++) {
                        System.out.println((i + 1) + " : " + listState.get(i));
                    }
                    int stateIndex = Integer.parseInt(scanner.nextLine()) - 1;
                    if (stateIndex < 0 || stateIndex >= listState.size()) {
                        throw new IndexOutOfBoundsException("Invalid state index");
                    }
                    List<String> places = statePlaces.get(listState.get(stateIndex));

                    for (int i = 0; i < places.size(); i++) {
                        System.out.println((i + 1) + " : " + places.get(i));
                    }

                    System.out.println("How many places do you want to go to?");
                    int numPlaces = Integer.parseInt(scanner.nextLine());

                    for (int i = 0; i < numPlaces; i++) {
                        System.out.println("Enter place number:");
                        int placeIndex = Integer.parseInt(scanner.nextLine()) - 1;
                        if (placeIndex < 0 || placeIndex >= places.size()) {
                            throw new IndexOutOfBoundsException("Invalid place index");
                        }
                        tripPlaces.add(places.get(placeIndex));
                    }

                    break;
                case 2:
                    count = 1;
                    System.out.println("Select a country:");
                    for (int i = 0; i < countries.size(); i++) {
                        System.out.println((i + 1) + " : " + countries.get(i));
                    }
                    int countryIndex = Integer.parseInt(scanner.nextLine()) - 1;
                    if (countryIndex < 0 || countryIndex >= countries.size()) {
                        throw new IndexOutOfBoundsException("Invalid country index");
                    }
                    tripPlaces.add(countries.get(countryIndex));
                    break;
                default:
                    count = 0;
                    throw new IllegalArgumentException("Invalid input! Please enter either 1 or 2.");
            }
        } catch (NumberFormatException e) {
            System.out.println("Invalid input! Please enter a valid number.");
        } catch (IndexOutOfBoundsException e) {
            System.out.println("Invalid selection! Please enter a valid option.");
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }

        customerTrips.put(custId, tripPlaces);
        vPlace.clear();
        transport(scanner);
        total(scanner);
        writeToFile();

    }

    public void removeTrip(Scanner scanner) {
        System.out.println("Enter customer ID to remove:");
        String custId = scanner.nextLine();
        removeCustomer(custId);
        writeToFile();
    }

    public void tripInfoById(Scanner scanner) {
        System.out.println("Enter customer ID to search:");
        String custId = scanner.nextLine();
        displayCustomerInfo(custId);
    }

    // All trip info method
    public void allTripInfo() {
        displayAllCustomers();
    }

    public void transport(Scanner scanner) {
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
            for (Map.Entry<String, String> entry : packages.entrySet()) {
                if (entry.getKey().equals("Cpackage")) {
                    break;
                }
                System.out.println(entry.getKey() + " : " + entry.getValue());
            }
            cFaci = scanner.nextLine();
            System.out.println("You chose package " + cFaci + " with flight " + flightChoice);
        }
    }

    public void total(Scanner scanner) {
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

        for (Collection s : customerTrips.values()) {

            Iterator itr = s.iterator();
            while (itr.hasNext()) {

                // System.out.println(s);
                String place = (String) itr.next();
                // System.out.println(place);

                if (placePrices.containsKey(place)) {
                    int placePrice = placePrices.get(place);
                    // System.out.println(placePrice);

                    ageList = getAgeList();
                    // System.out.println(ageList.size());
                    // for (int i = ageList.size() - 1; i >= ageList.size() - junum; i--) {
                    //     System.out.println(ageList.get(i));
                    // }
                    for (int age : getAgeList()) {

                        int ageDiscount = 0;

                        if (age < 9) {
                            ageDiscount = 1000;
                        } else if (age >= 9 && age <= 18) {
                            ageDiscount = 500;
                        }

                        int flightCost = flightPrices.getOrDefault(cFaci, 0);

                        total += placePrice - ageDiscount + flightCost;
                        total_1 = total;
                    }
                } else {
                    System.out.println("Enter");
                    System.out.println("Place not found in price list: " + place);
                }
            }
        }

        System.out.println("TOTAL: " + total_1);
        System.out.println("YOUR TRIP BOOKED!!!");
    }
}

public class TravelBooking_3 {
    public static void main(String[] args) throws Exception {
        Scanner scanner = new Scanner(System.in);
        Booking_3 booking = new Booking_3();

        while (true) {
            System.out.println("\nTravel Booking System");
            System.out.println("1. Add a trip");
            System.out.println("2. Remove trip by id");
            System.out.println("3. Search trip info by ID");
            System.out.println("4. Display all trip info");
            System.out.println("5. Exit");
            System.out.print("Enter your choice: ");
            int choice = Integer.parseInt(scanner.nextLine());

            switch (choice) {
                case 1:
                    booking.addTrip(scanner);
                    System.out.println("Trip added successfully.");
                    break;
                case 2:
                    booking.removeTrip(scanner);
                    break;
                case 3:
                    booking.tripInfoById(scanner);
                    break;
                case 4:
                    booking.allTripInfo();
                    break;
                case 5:
                    System.out.println("Exiting...");
                    scanner.close();
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }
}
