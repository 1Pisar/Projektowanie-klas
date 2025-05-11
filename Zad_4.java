import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.InputMismatchException;

class Autor implements Serializable {
    private String imie;
    private String nazwisko;

    public Autor(String imie, String nazwisko) {
        this.imie = imie;
        this.nazwisko = nazwisko;
    }

    public String getImie() {
        return imie;
    }

    public String getNazwisko() {
        return nazwisko;
    }

    public String pelneImie() {
        return imie + " " + nazwisko;
    }
}

class Recenzja implements Serializable {
    private String komentarz;
    private int ocena; // Ocena w skali od 1 do 10
    private LocalDate dataDodania;

    public Recenzja(String komentarz, int ocena) {
        this.komentarz = komentarz;
        this.ocena = ocena;
        this.dataDodania = LocalDate.now();
    }

    public String getKomentarz() {
        return komentarz;
    }

    public int getOcena() {
        return ocena;
    }

    public LocalDate getDataDodania() {
        return dataDodania;
    }
}

class Ksiazka implements Serializable {
    private String tytul;
    private Autor autor;
    private boolean dostepna;
    private List<Recenzja> recenzje;

    public Ksiazka(String tytul, Autor autor) {
        this.tytul = tytul;
        this.autor = autor;
        this.dostepna = true;
        this.recenzje = new ArrayList<>();
    }

    public String getTytul() {
        return tytul;
    }

    public Autor getAutor() {
        return autor;
    }

    public boolean isDostepna() {
        return dostepna;
    }

    public List<Recenzja> getRecenzje() {
        return recenzje;
    }

    public void dodajRecenzje(Recenzja recenzja) {
        recenzje.add(recenzja);
    }

    public void wypozycz() {
        dostepna = false;
    }

    public void oddaj() {
        dostepna = true;
    }
}

class Biblioteka implements Serializable {
    private List<Ksiazka> ksiazki;
    public List<Autor> autorzy;
    private List<String> historia;

    public Biblioteka() {
        ksiazki = new ArrayList<>();
        autorzy = new ArrayList<>();
        historia = new ArrayList<>();
    }

    public void dodajKsiazke(String tytul, Autor autor) {
        Ksiazka ksiazka = new Ksiazka(tytul, autor);
        ksiazki.add(ksiazka);
    }

    public void dodajAutora(Autor autor) {
        autorzy.add(autor);
    }

    public void listaAutorow() {
        System.out.println("Lista Autorów:");
        for (int i = 0; i < autorzy.size(); i++) {
            Autor autor = autorzy.get(i);
            System.out.println((i + 1) + ". " + autor.pelneImie());
        }
    }

    public Autor wybierzAutora(int numer) {
        if (numer > 0 && numer <= autorzy.size()) {
            return autorzy.get(numer - 1);
        }
        return null;
    }

    public void listaKsiazek() {
        System.out.println("Dostępne Książki:");
        for (Ksiazka ksiazka : ksiazki) {
            if (ksiazka.isDostepna()) {
                System.out.println("Tytuł: " + ksiazka.getTytul() + ", Autor: " + ksiazka.getAutor().pelneImie());
            }
        }
    }

    public void wypozyczKsiazke(String tytul) {
        for (Ksiazka ksiazka : ksiazki) {
            if (ksiazka.getTytul().equals(tytul) && ksiazka.isDostepna()) {
                ksiazka.wypozycz();
                historia.add("Wypożyczono: " + tytul);
                System.out.println("Wypożyczono: " + tytul);
                return;
            }
        }
        System.out.println("Książka niedostępna lub nie istnieje.");
    }

    public void oddajKsiazke(String tytul, Scanner skaner) {
        for (Ksiazka ksiazka : ksiazki) {
            if (ksiazka.getTytul().equals(tytul) && !ksiazka.isDostepna()) {
                ksiazka.oddaj();
                historia.add("Oddano: " + tytul);
                System.out.println("Oddano: " + tytul);
                System.out.print("Podaj komentarz (opcjonalnie): ");
                String komentarz = skaner.nextLine();
                int ocena = -1;
                while (ocena < 1 || ocena > 10) {
                    System.out.print("Podaj ocenę od 1 do 10: ");
                    ocena = skaner.nextInt();
                }
                Recenzja recenzja = new Recenzja(komentarz, ocena);
                ksiazka.dodajRecenzje(recenzja);
                System.out.println("Dodano recenzję.");
                return;
            }
        }
        System.out.println("Książka nie znaleziona w Twoich wypożyczonych książkach.");
    }

    public void listaWypozyczonychKsiazek() {
        System.out.println("Wypożyczone Książki:");
        for (Ksiazka ksiazka : ksiazki) {
            if (!ksiazka.isDostepna()) {
                System.out.println("Tytuł: " + ksiazka.getTytul() + ", Autor: " + ksiazka.getAutor().pelneImie());
                List<Recenzja> recenzje = ksiazka.getRecenzje();
                for (Recenzja recenzja : recenzje) {
                    System.out.println("Recenzja: " + recenzja.getKomentarz() + " (Ocena: " + recenzja.getOcena() +
                            ", Data dodania: " + recenzja.getDataDodania() + ")");
                }
            }
        }
    }

    public void wyswietlHistorie() {
        System.out.println("Historia:");
        for (String wpis : historia) {
            System.out.println(wpis);
        }
    }

    public void listaKsiazekAutora(Autor autor) {
        System.out.println("Dostępne książki autora: " + autor.pelneImie());
        boolean znaleziono = false;
        for (Ksiazka ksiazka : ksiazki) {
            if (ksiazka.isDostepna() && ksiazka.getAutor().equals(autor)) {
                System.out.println("Tytuł: " + ksiazka.getTytul());
                znaleziono = true;
            }
        }
        if (!znaleziono) {
            System.out.println("Brak dostępnych książek tego autora.");
        }
    }
}

public class Zad_4 {
    private static int getValidIntInput(Scanner skaner) {
        while (true) {
            try {
                return skaner.nextInt();
            } catch (InputMismatchException e) {
                System.out.println("Wprowadź poprawną liczbę!");
                skaner.nextLine(); // Czyści błędne dane wejściowe
            }
        }
    }
    public static void main(String[] args) {
        Biblioteka biblioteka = wczytajBiblioteke();

        Scanner skaner = new Scanner(System.in);
        boolean dziala = true;

        while (dziala) {
            System.out.println("\nMenu Biblioteki:");
            System.out.println("1. Dodaj Książkę");
            System.out.println("2. Dodaj Autora");
            System.out.println("3. Lista Dostępnych Książek");
            System.out.println("4. Wypożycz Książkę");
            System.out.println("5. Oddaj Książkę");
            System.out.println("6. Lista Wypożyczonych Książek");
            System.out.println("7. Lista Autorów");
            System.out.println("8. Historia");
            System.out.println("9. Zapisz i Wyjdź");
            System.out.println("10. Filtruj książki po autorze");

            int wybor = getValidIntInput(skaner);

            switch (wybor) {
                case 1:
                    System.out.print("Podaj tytuł książki: ");
                    String tytul = skaner.nextLine();
                    biblioteka.listaAutorow();
                    System.out.print("Wybierz numer autora lub dodaj nowego autora wpisując nowy numer: ");
                    int numerAutora = skaner.nextInt();
                    skaner.nextLine(); // Skonsumuj znak nowej linii
                    Autor autor;
                    if (numerAutora > 0 && numerAutora <= biblioteka.autorzy.size()) {
                        autor = biblioteka.wybierzAutora(numerAutora);
                    } else {
                        System.out.print("Podaj imię autora: ");
                        String imieAutora = skaner.nextLine();
                        System.out.print("Podaj nazwisko autora: ");
                        String nazwiskoAutora = skaner.nextLine();
                        autor = new Autor(imieAutora, nazwiskoAutora);
                        biblioteka.dodajAutora(autor);
                    }
                    biblioteka.dodajKsiazke(tytul, autor);
                    break;
                case 2:
                    System.out.print("Podaj imię autora: ");
                    String imieAutora = skaner.nextLine();
                    System.out.print("Podaj nazwisko autora: ");
                    String nazwiskoAutora = skaner.nextLine();
                    Autor nowyAutor = new Autor(imieAutora, nazwiskoAutora);
                    biblioteka.dodajAutora(nowyAutor);
                    break;
                case 3:
                    biblioteka.listaKsiazek();
                    break;
                case 4:
                    System.out.print("Podaj tytuł książki do wypożyczenia: ");
                    String wypozyczTytul = skaner.nextLine();
                    biblioteka.wypozyczKsiazke(wypozyczTytul);
                    break;
                case 5:
                    System.out.print("Podaj tytuł książki do zwrotu: ");
                    String oddajTytul = skaner.nextLine();
                    biblioteka.oddajKsiazke(oddajTytul, skaner);
                    break;
                case 6:
                    biblioteka.listaWypozyczonychKsiazek();
                    break;
                case 7:
                    biblioteka.listaAutorow();
                    break;
                case 8:
                    biblioteka.wyswietlHistorie();
                    break;
                case 9:
                    dziala = false;
                    break;
                case 10:
                    biblioteka.listaAutorow();
                    System.out.print("Wybierz numer autora: ");
                    int numer = getValidIntInput(skaner);
                    Autor wybranyAutor = biblioteka.wybierzAutora(numer);
                    if (wybranyAutor != null) {
                        biblioteka.listaKsiazekAutora(wybranyAutor);
                    } else {
                        System.out.println("Niepoprawny numer autora.");
                    }
                    break;
            }
        }
        zapiszBiblioteke(biblioteka);
        System.out.println("Dane biblioteki zapisane. Do widzenia!");
    }

    private static Biblioteka wczytajBiblioteke() {
        Biblioteka biblioteka = new Biblioteka();
        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream("biblioteka.dat"))) {
            biblioteka = (Biblioteka) in.readObject();
            System.out.println("Dane biblioteki wczytane pomyślnie.");
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Nie znaleziono zapisanych danych biblioteki. Rozpoczynamy z pustą biblioteką.");
        }
        return biblioteka;
    }

    private static void zapiszBiblioteke(Biblioteka biblioteka) {
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("biblioteka.dat"))) {
            out.writeObject(biblioteka);
            System.out.println("Dane biblioteki zostały zapisane.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
