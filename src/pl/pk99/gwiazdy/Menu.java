package pl.pk99.gwiazdy;

import java.util.Scanner;

//Klasa menu, służy do nawigacji po programie za pomocą instrukcji przekazywanych
//przez użytkownika, zawiera trzy menu:
//Menu Główne, Menu Wyszukiwania Gwiazd oraz Menu Dodawnia Gwiazd

class Menu {
    private Scanner sc = new Scanner(System.in);

    Menu () {
        start();
    }

    private void start () {
        if(GwiazdyManager.maZapisaneDane())
            GwiazdyManager.wczytajGwiazdy();
        else
            GwiazdyManager.wstawPrzykladowe();

        System.out.println("Witaj w programie \"Gwiazdy\"" +
                "\nAutor: Przemysław Kocur");

        menuGlowne();
    }

    private void menuGlowne () {
        przerywnikWizualny();
        System.out.println("====================================");
        System.out.println("[1] - Dodaj gwiazdę\n[2] - Usuń gwiazdę" +
                "\n[3] - Wyświetl wszystkie gwiazdy" + "\n[4] - Wyszukaj gwiazdy" +
                "\n[0] - Wyjdź");
        System.out.println("====================================");

        switch (sc.nextLine()) {
            case "1": {
                menuDodajGwiazde();
                break;
            }
            case "2": {
                usunGwiazde();
                break;
            }
            case "3": {
                GwiazdyManager.wyswietlGwiazdy();
                menuGlowne();
                break;
            }
            case "4": {
                menuSzukajGwiazd();
                break;
            }
            case "0": {
                wyjdz();
                break;
            }
            default: {
                System.out.println("Błędna wartość");
                menuGlowne();
            }
        }
    }

    private void wyjdz() {
        System.out.println("Czy zapisać zmiany? " +
                "\n[1] - Zapisz zmiany \n[2] - Nie zapisuj zmian");

        switch (sc.nextLine()) {
            case "1": {
                GwiazdyManager.zapiszGwiazdy();
                System.exit(0);
                break;
            }
            case "2": {
                System.exit(0);
                break;
            }
        }
    }

    private void menuSzukajGwiazd() {
        przerywnikWizualny();
        System.out.println("====================================");
        System.out.println("Wybierz jedno z kryteriów wyszukiwania:");
        System.out.println("[1] - Wyszukaj wszystkie gwiazdy w danym gwiazdozbiorze" +
                "\n[2] - Wyszukaj gwiazdy znajdujące się w odległości x parseków od Ziemi" +
                "\n[3] - Wyszukaj gwiazdy o temperaturze w zadanym przedziale" +
                "\n[4] - Wyszukaj gwiazdy o wielkości gwiazdowej w zadanym przedziale" +
                "\n[5] - Wyszukaj gwiazdy z półkuli północnej / południowej" +
                "\n[6] - Wyszukaj potencjalne supernowe");
        System.out.println("====================================");

        try {
            switch (sc.nextLine()) {
                case "1": {
                    System.out.println("Podaj gwiazdozbiór:");
                    String gwiazdozbior = sc.nextLine();
                    GwiazdyManager.znajdzGwiazdyWGwiazdozbiorze(gwiazdozbior);
                    menuGlowne();
                    break;
                }
                case "2": {
                    System.out.println("Podaj odległość (w parsekach):");
                    double pc = Double.parseDouble(sc.nextLine());
                    GwiazdyManager.znajdzGwiazdyWOdleglosci(pc);
                    menuGlowne();
                    break;
                }
                case "3": {
                    System.out.println("Podaj dolną wartość przedziału temepratury:");
                    double tMin = Double.parseDouble(sc.nextLine());
                    System.out.println("Podaj górną wartość przedziału temepratury:");
                    double tMax = Double.parseDouble(sc.nextLine());
                    GwiazdyManager.znajdzGwiazdyOTemperaturaturze(tMin, tMax);
                    menuGlowne();
                    break;
                }
                case "4": {
                    System.out.println("Podaj dolną wartość absolutnej wielkości gwiazdowej:");
                    double awgMin = Double.parseDouble(sc.nextLine());
                    System.out.println("Podaj górną wartość absolutnej wielkości gwiazdowej:");
                    double awgMax = Double.parseDouble(sc.nextLine());
                    GwiazdyManager.znajdzGwiazdyOAbsolutnejWielkosciGwiazdowej(awgMin, awgMax);
                    menuGlowne();
                    break;
                }
                case "5": {
                    System.out.println("Podaj półkulę ([N] - Północna, [S] - Południowa):");
                    String polkula = sc.nextLine();
                    boolean polnocna = true;

                    if(polkula.equalsIgnoreCase("N")) {
                        polnocna = true;
                    }
                    else if (polkula.equalsIgnoreCase("S")) {
                        polnocna = false;
                    }
                    else {
                        System.out.println("Błąd: Błędna wartość");
                        menuSzukajGwiazd();
                    }

                    GwiazdyManager.znajdzGwiazdyNaPolkuli(polnocna);
                    menuGlowne();
                    break;
                }
                case "6": {
                    GwiazdyManager.znajdzGwiazdyPotencjalneSupernowe();
                    menuGlowne();
                    break;
                }
                default: {
                    System.out.println("Błąd: Błędna wartość");
                    menuSzukajGwiazd();
                }
            }
        }
        catch (IllegalArgumentException e) {
            System.out.println("Błąd: "+ e.getMessage());
            menuSzukajGwiazd();
        }

    }

    private void usunGwiazde () {
        przerywnikWizualny();
        System.out.println("Podaj nazwę katalogową gwiazdy, którą chcesz usunąć:");
        String nazwa = sc.nextLine();
        boolean usunieta = GwiazdyManager.usunGwiazde(nazwa);

        if(usunieta)
            System.out.println("Gwiazda została pomyślnie usunięta");
        else
            System.out.println("Nie znaleziono gwiazdy");

        menuGlowne();
    }

    private void menuDodajGwiazde() {
        przerywnikWizualny();

        boolean error;

        do {
            error = false;
            try {
                System.out.println("Wprowadź nazwę gwiazdy:");
                String nazwa = sc.nextLine();

                System.out.println("Wprowadź nazwę gwiazdozbioru:");
                String nazwaG = sc.nextLine();

                ObserwowanaWielkoscGwiazdowa owg;

                System.out.println("Wprowadź obserwowaną wielkość gwiazdową (od -26.74 do 15):");
                owg = new ObserwowanaWielkoscGwiazdowa(Double.parseDouble(sc.nextLine()));

                System.out.println("Wprowadź odległość gwiazdy (w latach świetlnych):");
                double odleglosc = Double.parseDouble(sc.nextLine());

                System.out.println("Wprowadź półkulę, z której można zobaczyć gwiazdę " +
                        "([N] - północna, [S] - południowa):");
                String polkula = sc.nextLine();

                boolean polnocna;

                if(polkula.equalsIgnoreCase("N"))
                    polnocna = true;
                else if (polkula.equalsIgnoreCase("S"))
                    polnocna = false;
                else
                    throw new IllegalArgumentException("Błędna wartość");

                System.out.println("Wprowadź wartości deklinacji:");
                System.out.println("Wprowadź stopnie " +
                        ((polnocna) ? "(wartości dodatnie):" : "(wartości ujemne):"));
                int st = Integer.parseInt(sc.nextLine());
                System.out.println("Wprowadź minuty:");
                int mn = Integer.parseInt(sc.nextLine());
                System.out.println("Wprowadź sekundy:");
                double sek = Double.parseDouble(sc.nextLine());

                Deklinacja deklinacja = new Deklinacja(polnocna, st, mn, sek);

                System.out.println("Wprowadź wartości rektascensji:");
                System.out.println("Wprowadź godziny:");
                int gdzR = Integer.parseInt(sc.nextLine());
                System.out.println("Wprowadź minuty:");
                int mnR = Integer.parseInt(sc.nextLine());
                System.out.println("Wprowadź sekundy:");
                int sekR = Integer.parseInt(sc.nextLine());

                Rektascensja rektascensja = new Rektascensja(gdzR, mnR, sekR);

                System.out.println("Wprowadź temperaturę gwiazdy (min. 2000):");
                Temperatura temperatura = new Temperatura(Double.parseDouble(sc.nextLine()));

                System.out.println("Wprowadź masę gwiadzy w odniesieniu do masy Słońca (od 0.1 do 50):");
                Masa masa = new Masa(Double.parseDouble(sc.nextLine()));

                Gwiazda g = new Gwiazda(nazwa, nazwaG, owg, odleglosc, polnocna, deklinacja, rektascensja, temperatura, masa);

                GwiazdyManager.dodajGwiazde(g);

                System.out.println("Gwiazda została pomyślnie dodana");
            }
            catch (IllegalArgumentException e) {
                System.out.println("Błąd: "+ e.getMessage());
                error = true;
            }
        } while (error);

        menuGlowne();
    }

    /**
     * Metoda oddziela wizualnie komunikaty w konsoli
     */

    private void przerywnikWizualny () {
        System.out.println("\n");
    }
}
