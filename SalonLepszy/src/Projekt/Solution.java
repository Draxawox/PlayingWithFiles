package Projekt;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;

public class Solution {
    static Scanner sc = new Scanner(System.in);
    static ArrayList<Dealer> dealerList = new ArrayList<>();

    public static void startMenu() {
        int x;
        String mainFolder = ("C:\\ProjectCarSalon\\");
        if (!Files.exists(Paths.get(mainFolder))) {
            try {
                Files.createDirectory(Paths.get(mainFolder));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        String saveFolder = ("C:\\ProjectCarSalon\\SaveKrt\\");
        if (!Files.exists(Paths.get(saveFolder))) {
            try {
                Files.createDirectory(Paths.get(saveFolder));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        System.out.println("Wybierz isniejący salon - 1");
        System.out.println("Stwórz nowy salon - 2");
        System.out.println("Wczytaj pliki - 3");
        System.out.println("Wyjdź - 4");
        do {
            x = isNumber(sc.nextLine());
            if (x == 1) {
                x = 4;
                choose();
            } else if (x == 2) {
                x = 4;
                createDealer();
            } else if (x == 3) {
                x = 4;
                loadDealer();
                startMenu();
            } else if (x == 4) {
                System.out.println("Do zobaczenia");
            }
        } while (x != 4);
    }

    public static void choose() {
        int x;
        System.out.println("Liczba Obiektów: " + dealerList.size());
        for (int i = 0; i < dealerList.size(); i++) {
            System.out.println("Aby wybrać: " + dealerList.get(i).name + " wpisz: " + i);
        }
        do {
            System.out.println("Podaj numer obiektu: ");
            x = isNumber(sc.nextLine());
            if (x < dealerList.size()) {
                dealerList.get(x).consoleMenuDealer(dealerList.get(x));
            } else {
                System.out.println("Nie ma takiego indeksu");
            }
        } while (x >= dealerList.size());
    }

    public static String checkZipCode(String zipCode) {
        if (!zipCode.matches("[0-9]{2}[-][0-9]{3}")) {
            System.out.println("Błędny kod pocztowy");
            checkZipCode(sc.nextLine());
        }
        return zipCode;
    }

    public static String checkPhoneNumber(String phoneNumber) {
        if (!phoneNumber.matches("[+]?[0-9]{0,2}[-. ]?[0-9]{3}[-. ]?[0-9]{3}[-. ]?[0-9]{3}")) {
            System.out.println("Błędny numer telefonu");
            checkPhoneNumber(sc.nextLine());
        }
        return phoneNumber;
    }

    public static int isNumber(String str) {
        int a = 0;
        try {
            a = Integer.parseInt(str);
            if (a < 0) {
                System.out.println("Liczba nie może być ujemna");
                a = isNumber(sc.nextLine());
            }
        } catch (NumberFormatException r) {
            System.out.println("Podaj liczbę jeszcze raz: ");
            isNumber(sc.nextLine());
        }
        return a;
    }

    public static void loadDealer() {
        boolean isDifferent = true;
        Dealer dealer;
        Address address;
        String name, phoneNumber, street, number, zipCode, city, brand, model, color;
        String[] inventory;
        int price, carListLength;
        File folder = new File("C:\\ProjectCarSalon\\SaveKrt\\");
        File[] filenames = folder.listFiles();
        Car car;
        assert filenames != null;
        for(File file : filenames) {
            try {
                Scanner fileSc = new Scanner(file);
                name = fileSc.nextLine();
                street = fileSc.nextLine();
                number = fileSc.nextLine();
                zipCode = fileSc.nextLine();
                city = fileSc.nextLine();
                phoneNumber = fileSc.nextLine();
                carListLength = isNumber(fileSc.nextLine());
                address = new Address(street, number, zipCode, city);
                dealer = new Dealer(name, address, phoneNumber);
                dealer.carList = new Car[carListLength];
                for (int j = 0; j < carListLength; j++) {
                    brand = fileSc.next();
                    if (brand.equals("")) {
                        brand = fileSc.nextLine();
                    }
                    model = fileSc.nextLine();
                    if (model.equals("")) {
                        model = fileSc.nextLine();
                    }
                    color = fileSc.nextLine();
                    String fullInvTab = fileSc.nextLine();
                    inventory = new String[]{fullInvTab};
                    price = fileSc.nextInt();
                    car = new Car(brand, model, color, price);
                    car.inventory = inventory;
                }
                for (int i = 0; i < dealerList.size(); i++) {
                    if (isDifferent) {
                        if (!dealer.name.equals(dealerList.get(i).name)) {
                            if (i == dealerList.size() - 1) {
                                dealerList.add(dealer);
                            }
                        } else {
                            isDifferent = false;
                        }
                    }
                }
            } catch (IOException a) {
                a.printStackTrace();
            }
        }
    }

    public static void createDealer() {
        Scanner sc = new Scanner(System.in);
        Dealer dealer;
        Address address;
        String name, phoneNumber, street, number, zipCode, city, brand, model, color;
        String[] inventory;
        int price;
        System.out.println("Podaj nazwę salonu: ");
        name = sc.nextLine();
        System.out.println("Podaj numer telefonu: ");
        phoneNumber = checkPhoneNumber(sc.nextLine());
        System.out.println("Podaj ulicę: ");
        street = sc.nextLine();
        System.out.println("Podaj numer budynku: ");
        number = sc.nextLine();
        System.out.println("Podaj kod pocztowy w formacie xx-xxx: ");
        zipCode = checkZipCode(sc.nextLine());
        System.out.println("Podaj miasto: ");
        city = sc.nextLine();
        address = new Address(street, number, zipCode, city);
        dealer = new Dealer(name, address, phoneNumber);
        System.out.println("Podaj liczbę samochodów: ");
        if (sc.hasNextInt()) {
            dealer.carList = new Car[isNumber(sc.nextLine())];
            for (int i = 0; i < dealer.carList.length; i++) {
                System.out.println("Samochód nr " + (i + 1));
                System.out.println("Marka: ");
                brand = sc.nextLine();
                System.out.println("Model: ");
                model = sc.nextLine();
                System.out.println("Kolor: ");
                color = sc.nextLine();
                System.out.println("Podaj ilość elementów wyposarzenia: ");
                inventory = new String[isNumber(sc.nextLine())];
                for (int j = 0; j < inventory.length; j++) {
                    System.out.println("Podaj " + (j + 1) + " element wyposarzenia: ");
                    inventory[j] = sc.nextLine();
                }
                System.out.println("Podaj cenę: ");
                price = isNumber(sc.nextLine());
                Car car = new Car(brand, model, color, price);
                car.inventory = inventory;
                dealer.carList[i] = car;
            }
        }
        dealerList.add(dealer);
        dealer.save(dealer);
        startMenu();
    }

    public static void main(String[] args) {
        Car s1 = new Car("Honda", "Civic", "Black", 50000);
        s1.inventory = new String[]{"Elektryczne lusterka", "Podgrzewane fotele", "Alufelgi"};
        Car s2 = new Car("Daewo", "Lanos", "White", 1000);
        s2.inventory = new String[]{"Welurowa tapicerka", "Koło zapsaowe", "Instalacja LPG"};
        Car s3 = new Car("Ford", "Focus", "Black", 5000);
        s3.inventory = new String[]{"Klimatyzacja", "Koło zapasowe", "Podgrzewane lustrka"};
        Car s4 = new Car("Bugatti", "Veron", "Green", 1000000);
        s4.inventory = new String[]{"Serio? To jest Bugatti!"};
        Car s5 = new Car("Audi", "A3", "Blue", 40000);
        s5.inventory = new String[]{"Bluetooth", "Alufelgi", "Skórzane fotele"};
        Car s6 = new Car("BMW", "M6", "Purple", 230000);
        s6.inventory = new String[]{"Skórzane fotele", "kllimatyzacja", "Elektryczne lusterka"};
        Car s7 = new Car("Fiat", "126p", "Red", 1500);
        s7.inventory = new String[]{"koło zapsowe"};
        Car s8 = new Car("Opel", "Astra", "Yellow", 3000);
        s8.inventory = new String[]{"Koło zpapasowe", "Welurowa tapicerka", "Klimatyzacja"};
        Address dealerAddress = new Address("Mokra", "37", "37-700", "Przemyśl");
        Dealer dealer1 = new Dealer("U Janusza", dealerAddress, "+48-794-990-115");
        dealer1.carList = new Car[]{s1,s2,s3,s4,s5,s6,s7,s8};
        dealerList.add(dealer1);
        startMenu();
        sc.close();
    }
}