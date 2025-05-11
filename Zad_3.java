import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

interface Figura {
    double obliczPole();
    double obliczObwod();
}

class Trojkat implements Figura {
    private double a, b, c;

    public Trojkat(double a, double b, double c) {
        this.a = a;
        this.b = b;
        this.c = c;
    }

    @Override
    public double obliczPole() {
        double s = (a + b + c) / 2;
        return Math.sqrt(s * (s - a) * (s - b) * (s - c));
    }

    @Override
    public double obliczObwod() {
        return a + b + c;
    }

    @Override
    public String toString() {
        return "Trojkat [a=" + a + ", b=" + b + ", c=" + c + "]";
    }
}

class Prostokat implements Figura {
    private double szerokosc, wysokosc;

    public Prostokat(double szerokosc, double wysokosc) {
        this.szerokosc = szerokosc;
        this.wysokosc = wysokosc;
    }

    @Override
    public double obliczPole() {
        return szerokosc * wysokosc;
    }

    @Override
    public double obliczObwod() {
        return 2 * (szerokosc + wysokosc);
    }

    @Override
    public String toString() {
        return "Prostokat [szerokosc=" + szerokosc + ", wysokosc=" + wysokosc + "]";
    }
}

class Kwadrat implements Figura {
    private double bok;

    public Kwadrat(double bok) {
        this.bok = bok;
    }

    @Override
    public double obliczPole() {
        return bok * bok;
    }

    @Override
    public double obliczObwod() {
        return 4 * bok;
    }

    @Override
    public String toString() {
        return "Kwadrat [bok=" + bok + "]";
    }
}

class Kolo implements Figura {
    private double promien;

    public Kolo(double promien) {
        this.promien = promien;
    }

    @Override
    public double obliczPole() {
        return Math.PI * promien * promien;
    }

    @Override
    public double obliczObwod() {
        return 2 * Math.PI * promien;
    }

    @Override
    public String toString() {
        return "Kolo [promien=" + promien + "]";
    }
}

class FiguraFactory {
    public static Figura utworzFigure(String figura, double... parametry) {
        switch (figura) {
            case "Trojkat":
                if (parametry.length == 3) {
                    return new Trojkat(parametry[0], parametry[1], parametry[2]);
                }
                break;
            case "Prostokat":
                if (parametry.length == 2) {
                    return new Prostokat(parametry[0], parametry[1]);
                }
                break;
            case "Kwadrat":
                if (parametry.length == 1) {
                    return new Kwadrat(parametry[0]);
                }
                break;
            case "Kolo":
                if (parametry.length == 1) {
                    return new Kolo(parametry[0]);
                }
                break;
        }
        return null;
    }
}

public class Zad_3 {
    public static void main(String[] args) {
        List<Figura> figury = wczytajFiguryZPliku("dane.txt");
        if (figury != null) {
            for (Figura figura : figury) {
                System.out.println(figura);
                System.out.println("Pole: " + figura.obliczPole());
                System.out.println("Obwod: " + figura.obliczObwod());
                System.out.println();
            }
        }
    }

    public static List<Figura> wczytajFiguryZPliku(String nazwaPliku) {
        List<Figura> figury = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(nazwaPliku))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(" ");
                if (parts.length > 0) {
                    String figura = parts[0];
                    double[] parametry = new double[parts.length - 1];
                    for (int i = 1; i < parts.length; i++) {
                        parametry[i - 1] = Double.parseDouble(parts[i]);
                    }

                    Figura utworzonaFigura = FiguraFactory.utworzFigure(figura, parametry);
                    if (utworzonaFigura != null) {
                        figury.add(utworzonaFigura);
                    } else {
                        System.out.println("Nieprawidlowe dane: " + line);
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return figury;
    }
}
