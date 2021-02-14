package Projekt;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Collections;
import java.util.Scanner;

import static Projekt.Solution.*;

public class Dealer {
    String name;
    Address address;
    String phoneNumber;
    Car[] carList;

    public Dealer(String name, Address address, String phoneNumber) {
        this.name = name;
        this.address = address;
        this.phoneNumber = phoneNumber;
    }
    public void save (Dealer dealer) {
        String path = "C:\\ProjectCarSalon\\SaveKrt\\" + dealer.name + ".txt";
        File f = new File(path);
        f.getParentFile().mkdirs();
        try {
            f.createNewFile();
        } catch (IOException a) {
            a.printStackTrace();
        }
        try {
            PrintWriter pw = new PrintWriter(path);
            pw.print(dealer.name + "\n" + dealer.address.street + "\n" + dealer.address.number + "\n" + dealer.address.zipCode + "\n" + dealer.address.city + "\n" + dealer.phoneNumber + "\n" + carList.length + "\n");
            pw.flush();
            for (Car car : carList) {
                pw.print(car.brand + "\n" + car.model + "\n" + car.color + "\n" + Arrays.toString(car.inventory) + "\n" + car.price + "\n");
                pw.flush();
            }
            pw.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
    public void applyChanges (Dealer dealer, boolean isChanged, String oldName) {
        if (isChanged) {
            File f = new File("C:\\ProjectCarSalon\\SaveKrt\\" + oldName + ".txt");
            if (f.exists()) {
                renameFile(oldName, dealer.name);
            }
            save(dealer);
        }
    }

    public void renameFile(String name, String  newName) {
        try {
            File file = new File("C:\\ProjectCarSalon\\SaveKrt\\" + name + ".txt");
            File newFile = new File("C:\\ProjectCarSalon\\SaveKrt\\" + newName + ".txt");
            if (file.renameTo(newFile)) {
                System.out.println("Wszystko w porządku");
            } else {
                System.out.println("\033[1;31m" + "Coś poszło nie tak" + "\u001B[0m");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void editDealer(Dealer dealer) {
        int zmienna;
        String oldName = dealer.name;
        boolean isChanged = false;
        Car car;
        String[] inv;
        int cilen;
        do {
            System.out.println("edytuj dane salonu - 1");
            System.out.println("edytuj dane samochodów - 2");
            System.out.println("wyjdź - 3");
            zmienna = isNumber(sc.nextLine());
            if (zmienna == 1) {
                do {
                    System.out.println("zmień nazwę - 1");
                    System.out.println("zmień nazwę ulicy - 2");
                    System.out.println("zmień numer budynku - 3");
                    System.out.println("zmień kod pocztowy - 4");
                    System.out.println("zmień miasto - 5");
                    System.out.println("zmień numer telefonu - 6");
                    System.out.println("powrót - 7");
                    zmienna = isNumber(sc.nextLine());
                    if (zmienna == 1) {
                        System.out.println("aktualna wartość to: " + dealer.name);
                        System.out.println("wprowadź nową wartość: ");
                        dealer.name = sc.nextLine();
                        isChanged = true;
                    } else if (zmienna == 2) {
                        System.out.println("aktualna wartość to: " + dealer.address.street);
                        System.out.println("wprowadź nową wartość");
                        dealer.address.street = sc.nextLine();
                        isChanged = true;
                    } else if (zmienna == 3) {
                        System.out.println("aktualna wartość to: " + dealer.address.number);
                        System.out.println("wprowadź nową wartość");
                        dealer.address.number = sc.nextLine();
                        isChanged = true;
                    } else if (zmienna == 4) {
                        System.out.println("aktualna wartość to: " + dealer.address.zipCode);
                        System.out.println("wprowadź nową wartość");
                        dealer.address.zipCode = checkZipCode(sc.nextLine());
                        isChanged = true;
                    } else if (zmienna == 5) {
                        System.out.println("aktualna wartość to: " + dealer.address.city);
                        System.out.println("wprowadź nową wartość");
                        dealer.address.city = sc.nextLine();
                        isChanged = true;
                    } else if (zmienna == 6) {
                        System.out.println("aktualna wartość to: " + dealer.phoneNumber);
                        System.out.println("wprowadź nową wartość");
                        dealer.phoneNumber = checkPhoneNumber(sc.nextLine());
                        isChanged = true;
                    } else if (zmienna == 7) {

                    } else {
                        System.out.println("wrowadź jeszcze raz: ");
                    }
                    if (isChanged) {
                        applyChanges(dealer, true, oldName);
                    }
                } while (zmienna != 7);
            } else if (zmienna == 2) {
                do {
                    for (int i = 0; i < dealer.carList.length; i++) {
                        System.out.println("Aby wybrać " + dealer.carList[i].brand + " " + dealer.carList[i].model + " - " + i);
                    }
                    System.out.println("wyjdź - 8");
                    do {
                        zmienna = isNumber(sc.nextLine());
                        if (zmienna < carList.length && zmienna >= 0) {
                            car = dealer.carList[zmienna];
                            System.out.println("zmień markę - 1");
                            System.out.println("zmień model - 2");
                            System.out.println("zmieć kolor - 3");
                            System.out.println("zmień elementy wyposarzenia - 4");
                            System.out.println("dodaj elementy wyposarzenia - 5");
                            System.out.println("zmień cenę - 6");
                            System.out.println("powrót - 7");
                            zmienna = isNumber(sc.nextLine());
                            if (zmienna == 1) {
                                System.out.println("aktualna wartość to: " + car.brand);
                                System.out.println("wprowadź nową wartość: ");
                                car.brand = sc.nextLine();
                                isChanged = true;
                            } else if (zmienna == 2) {
                                System.out.println("aktualna wartość to: " + car.model);
                                System.out.println("wprowadź nową wartość: ");
                                car.model = sc.nextLine();
                                isChanged = true;
                            } else if (zmienna == 3) {
                                System.out.println("aktualna wartość to: " + car.color);
                                System.out.println("wprowadź nową wartość: ");
                                car.color = sc.nextLine();
                                isChanged = true;
                            } else if (zmienna == 6) {
                                System.out.println("aktualna wartość to: " + car.price);
                                System.out.println("wprowadź nową wartość: ");
                                car.price = isNumber(sc.nextLine());
                                isChanged = true;
                            } else if (zmienna == 4) {
                                System.out.println("wybierz element: ");
                                for (int i = 0; i < car.inventory.length; i++) {
                                    System.out.println(car.inventory[i] + " - " + i);
                                }
                                zmienna = isNumber(sc.nextLine());
                                if (zmienna < car.inventory.length && zmienna >= 0) {
                                    System.out.println("aktualna wartość to: " + car.inventory[zmienna]);
                                    System.out.println("wprowadź nową wartość: ");
                                    car.inventory[zmienna] = sc.nextLine();
                                    isChanged = true;
                                } else {
                                    System.out.println("\033[1;31m" + "podany indeks nie istnieje" + "\u001B[0m");
                                }

                            } else if (zmienna == 5) {
                                System.out.println("ile elementów chcesz dodać?");
                                zmienna = isNumber(sc.nextLine());
                                cilen = 0;
                                if (zmienna > 0) {
                                    inv = new String[car.inventory.length + zmienna];
                                    for (int i = 0; i < inv.length; i++) {
                                        if (cilen < car.inventory.length) {
                                            inv[cilen] = car.inventory[cilen];
                                            cilen++;
                                        } else {
                                            System.out.println("dodaj nowy element: ");
                                            inv[i] = sc.nextLine();
                                        }
                                    }
                                    car.inventory = inv;
                                    isChanged = true;
                                } else {
                                    System.out.println("zmienna musi być większa od 0");
                                }
                            } else if (zmienna == 7) {

                            }
                            if (isChanged) {
                                applyChanges(dealer, true, oldName);
                            }
                        } else {
                            System.out.println("\033[1;31m" + "podany indeks nie istnieje" + "\u001B[0m");
                        }
                    } while (zmienna == 7);
                } while (zmienna == 8);
            } else if (zmienna == 3) {
                if (isChanged) {
                    File f = new File("C:\\ProjectCarSalon\\SaveKrt\\" + dealer.name + ".txt");
                    if (f.exists()) {
                        renameFile(oldName, dealer.name);
                    }
                    save(dealer);
                }
                System.out.println("wychodzę");
            } else {
                System.out.println("podano niepoprawną wartość");
            }
        } while (zmienna == 3);
    }

    public void saveDealerDetails(Dealer dealer) {

        String path = "C:\\ProjectCarSalon\\" + dealer.name + ".txt";
        File f = new File(path);
        f.getParentFile().mkdirs();
        try {
            f.createNewFile();
        } catch (IOException a) {
            a.printStackTrace();
        }
        try {
            PrintWriter pw = new PrintWriter(path);
            pw.print(dealer.name + "\n" + dealer.address.street + "\n" + dealer.address.number + "\n" + dealer.address.city + "\n\n");
            pw.flush();
            pw.print("Ilość samochdów w salonie: " + carList.length + "\n\n");
            for (Car car : carList) {
                pw.print(car.brand + "\n" + car.model + "\n" + car.color + "\n" + "Ilość elementów wyposarzenia: " + car.inventory.length + "\n" + Arrays.toString(car.inventory) + "\n" + car.price + "\n---------------\n");
                pw.flush();
            }
            pw.close();
        } catch (FileNotFoundException e) {            e.printStackTrace();
        }
    }

    public void printDealerInfo() {
        System.out.printf("%s\n%s %s\n%s\n%s\nNumer telefonu:\n%s\n\n", name, address.street, address.number, address.zipCode, address.city, phoneNumber);
    }

    public String getColor(String color) {
        String reset = "\u001B[0m";
        if (color.equalsIgnoreCase("white") || color.equalsIgnoreCase("biały")) {
            color = "\033[1;30m" + color + reset;
        } else if (color.equalsIgnoreCase("red") || color.equalsIgnoreCase("czerwony")) {
            color = "\033[1;31m" + color + reset;
        } else if (color.equalsIgnoreCase("black") || color.equalsIgnoreCase("czarny")) {
            color = "\033[1;90m" + color + reset;
        } else if (color.equalsIgnoreCase("green") || color.equalsIgnoreCase("zielony")) {
            color = "\033[1;32m" + color + reset;
        } else if (color.equalsIgnoreCase("yellow") || color.equalsIgnoreCase("żółty")) {
            color = "\033[1;33m" + color + reset;
        } else if (color.equalsIgnoreCase("blue") || color.equalsIgnoreCase("niebieski")) {
            color = "\033[1;34m" + color + reset;
        } else if (color.equalsIgnoreCase("purple") | color.equalsIgnoreCase("fioletowy")) {
            color = "\033[1;35m" + color + reset;
        }
        return color;
    }

    public void sortByPriceAsc(Car[] carList) {
        Arrays.sort(carList);
    }

    public void sortByPriceDesc(Car[] carList) {
        Arrays.sort(carList, Collections.reverseOrder());
    }


    public void printAll(Dealer dealer) {
        for (int i = 0; i < dealer.carList.length; i++) {
            System.out.println(dealer.carList[i].brand + "\n" + dealer.carList[i].model + "\n" + getColor(dealer.carList[i].color));
            if (dealer.carList[i].inventory != null) {
                System.out.print(Arrays.toString(dealer.carList[i].inventory));
            }
            System.out.println("\n" + dealer.carList[i].price + "\n-------------\n");
        }
    }

    public void consoleMenuDealer(Dealer dealer) {
        Scanner sc = new Scanner(System.in);
        int x = 0;
        do {
            System.out.println("Co chcesz zrobić?");
            System.out.println("sortuj rosnąco - 1");
            System.out.println("sourtuj malejąco - 2");
            System.out.println("wypisz listę samochodów - 3");
            System.out.println("stwórz plik txt - 4");
            System.out.println("wypisz informacje o salonie - 5");
            System.out.println("edytuj - 6");
            System.out.println("powrót - 7");
            System.out.println("menu główne - 8");
            if (sc.hasNextInt()) {
                x = sc.nextInt();
                if (x == 1) {
                    dealer.sortByPriceAsc(carList);
                } else if (x == 2) {
                    dealer.sortByPriceDesc(carList);
                } else if (x == 3) {
                    dealer.printAll(dealer);
                } else if (x == 4) {
                    dealer.saveDealerDetails(dealer);
                } else if (x == 5) {
                    dealer.printDealerInfo();
                } else if (x == 6) {
                    dealer.editDealer(dealer);
                } else if (x == 7) {
                    x = 8;
                    choose();
                } else if (x == 8) {
                    startMenu();
                } else {
                    System.out.println("Jeszcze raz");
                }
            } else {
                System.out.println("Jeszcze raz");
            }
        } while (x != 8);
    }
}