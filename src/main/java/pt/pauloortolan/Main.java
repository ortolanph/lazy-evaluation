package pt.pauloortolan;

import pt.pauloortolan.loader.RevenueLoader;
import pt.pauloortolan.service.ClassicMarketService;
import pt.pauloortolan.service.MarketService;

public class Main {

    public static final String RIGHT_FORMATTED = "%30s%n";
    public static final String NUMBER_FORMAT = "%,.2f";
    public static final String LINE = "----------------------------------------";

    public static void main(String[] args) {
        RevenueLoader loader = new RevenueLoader("supermarket_revenue.json");

        String formattedResult = "";

        MarketService service = new ClassicMarketService(loader.getMarketData());

        System.out.println("ALL COUNTRIES");
        System.out.println(LINE);
        service
                .getAllCountries()
                .forEach(System.out::println);
        System.out.println(LINE);

        System.out.println();
        System.out.println("ALL LOCATIONS");
        System.out.println(LINE);
        service
                .getStoresByCountry(country -> country.getName().equals("Portugal"))
                .forEach(System.out::println);
        System.out.println(LINE);

        System.out.println();
        System.out.println("ALL LOCATIONS BY COUNTRY");
        System.out.println(LINE);
        service
                .getLocationsByCountry(country -> country.getName().equals("Portugal"))
                .forEach(System.out::println);
        System.out.println(LINE);

        System.out.println();
        System.out.println("ALL SECTIONS BY STORE");
        System.out.println(LINE);
        service
                .getSectionsByStore(store -> store.getId().equals("PTCOI001"))
                .forEach(System.out::println);
        System.out.println(LINE);

        System.out.println();
        System.out.println("TOTAL REVENUE");
        System.out.println(LINE);
        double result = service.getTotalRevenue();
        formattedResult = String.format(NUMBER_FORMAT, result);
        System.out.printf(RIGHT_FORMATTED, formattedResult);
        System.out.println(LINE);

        System.out.println();
        System.out.println("TOTAL REVENUE BY COUNTRY");
        System.out.println(LINE);
        result = service.getTotalRevenueByCountry(country -> country.getName().equals("Portugal"));
        formattedResult = String.format(NUMBER_FORMAT, result);
        System.out.printf(RIGHT_FORMATTED, formattedResult);
        System.out.println(LINE);

        System.out.println();
        System.out.println("TOTAL REVENUE BY COUNTRY AND STORE");
        System.out.println(LINE);
        result = service
                .getTotalRevenueByCountryAndStore(
                        country -> country.getName().equals("Portugal"),
                        store -> store.getId().equals("PTCOI001")
                );
        formattedResult = String.format(NUMBER_FORMAT, result);
        System.out.printf(RIGHT_FORMATTED, formattedResult);
        System.out.println(LINE);

        System.out.println();
        System.out.println("TOTAL REVENUE BY COUNTRY AND STORE ANS SECTION");
        System.out.println(LINE);
        result = service
                .getTotalRevenueByCountryAndStoreAndSection(
                        country -> country.getName().equals("Portugal"),
                        store -> store.getId().equals("PTCOI001"),
                        section -> section.getSection().equals("Seafood")
                );
        formattedResult = String.format(NUMBER_FORMAT, result);
        System.out.printf(RIGHT_FORMATTED, formattedResult);
        System.out.println(LINE);
    }
}