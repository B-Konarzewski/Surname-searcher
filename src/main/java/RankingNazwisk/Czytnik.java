package RankingNazwisk;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Czytnik {

    public List<String> CzytajMeskie(String parametr) {
        List<String> meskie = new ArrayList<>();
        String csvFile = "nazwiska_meskie_aktualne.csv";
        String imie;
        int pozycja = 0;
        try ( BufferedReader br = new BufferedReader(new FileReader(csvFile))) {
            while ((imie = br.readLine()) != null) {
                if (parametr.isBlank() && !(imie.contains("Nazwisko"))) {
                    imie = imie.replace(",", "      Liczba: ");
                    // Dodanie nazwiska do listy
                    meskie.add(pozycja + ". " + imie);
                } else {
                    String imie_szukane = imie.substring(0, imie.indexOf(","));
                    if (imie_szukane.equals(parametr) && !(imie.contains("Nazwisko"))) {
                        imie = imie.replace(",", "      Liczba: ");
                        // Dodanie nazwiska do listy
                        meskie.add(pozycja + ". " + imie);
                    }
                }
                pozycja++;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return meskie;
    }

    public List<String> CzytajDamskie(String parametr) {
        List<String> damskie = new ArrayList<>();
        String csvFile = "nazwiska_zenskie-osoby_zyjace_aktualne.csv";
        int pozycja = 0;
        String imie;
        try ( BufferedReader br = new BufferedReader(new FileReader(csvFile))) {
            while ((imie = br.readLine()) != null) {
                if (parametr.isBlank() && !(imie.contains("Nazwisko"))) {
                    imie = imie.replace(",", "      Liczba: ");
                    // Dodanie nazwiska do listy
                    damskie.add(pozycja + ". " + imie);
                } else {
                    String imie_szukane = imie.substring(0, imie.indexOf(","));
                    if (imie_szukane.equals(parametr) && !(imie.contains("Nazwisko"))) {
                        imie = imie.replace(",", "      Liczba: ");
                        // Dodanie nazwiska do listy
                        damskie.add(pozycja + ". " + imie);
                    }
                }
                pozycja++;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return damskie;
    }

    public List<String> CzytajWszytskie(String parametr) {
        List<String> meskie = this.CzytajMeskie(parametr);
        System.out.println("wczytano meskie");
        List<String> damskie = this.CzytajDamskie(parametr);
        System.out.println("wczytano damskie");
        List<String> wszystkie = new ArrayList<>(meskie);
        wszystkie.addAll(damskie);
        System.out.println("wczytano listy");

        int n = wszystkie.size();
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - 1 - i; j++) {
                String nazwisko1 = wszystkie.get(j).substring(wszystkie.get(j).indexOf(":") + 1).trim();

                String nazwisko2 = wszystkie.get(j + 1).substring(wszystkie.get(j + 1).indexOf(":") + 1).trim();
                int nazwisko1_liczba = Integer.parseInt(nazwisko1);
                int nazwisko2_liczba = Integer.parseInt(nazwisko2);

                if (nazwisko2_liczba > nazwisko1_liczba) {
                    Collections.swap(wszystkie, j, j + 1);
                }
            }
        }
        if (wszystkie.size() > 1) {
            for (int i = 0; i < wszystkie.size(); i++) {
                String imie_pierwsze = wszystkie.get(i).substring(wszystkie.get(i).indexOf("."), wszystkie.get(i).indexOf(":") - 13);
                if (i + 1 < wszystkie.size()) {
                    String imie_drugie = wszystkie.get(i + 1).substring(wszystkie.get(i + 1).indexOf("."), wszystkie.get(i + 1).indexOf(":") - 13);
                    if (imie_pierwsze.equals(imie_drugie)) {
                        System.out.println("Chuj");
                        int liczba_pierwsza = Integer.parseInt(wszystkie.get(i).substring(wszystkie.get(i).indexOf(":") + 2));
                        System.out.println(liczba_pierwsza);
                        int liczba_druga = Integer.parseInt(wszystkie.get(i + 1).substring(wszystkie.get(i + 1).indexOf(":") + 2));
                        System.out.println(liczba_druga);
                        int liczba_calkowita = liczba_pierwsza + liczba_druga;
                        System.out.println(liczba_calkowita);
                        wszystkie.set(i, wszystkie.get(i).substring(0, wszystkie.get(i).indexOf(":") + 2) + liczba_calkowita);
                        wszystkie.remove(i + 1);
                        i--;
                    }
                }

            }
        }

        return wszystkie;
    }
}
