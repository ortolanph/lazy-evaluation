package pt.pauloortolan;

import pt.pauloortolan.loader.RevenueLoader;
import pt.pauloortolan.service.ClassicMarketService;
import pt.pauloortolan.service.MarketService;

public class Main {

    public static final String RIGHT_FORMATTED = "%30s%n";
    public static final String NUMBER_FORMAT = "%,.2f";

    public static void main(String[] args) {
        RevenueLoader loader = new RevenueLoader("supermarket_revenue.json");

        double result = 0.0;
        String formattedResult = "";

        MarketService service = new ClassicMarketService(loader.getMarketData());

        System.out.println("ALL COUNTRIES");
        service
                .getAllCountries()
                .forEach(System.out::println);
        System.out.println("----------------------------------------");

        System.out.println();
        System.out.println("ALL COUNTRIES");
        service
                .getStoresByCountry(country -> country.getName().equals("Portugal"))
                .forEach(System.out::println);
        System.out.println("----------------------------------------");

        System.out.println();
        System.out.println("ALL LOCATIONS BY COUNTRY");
        service
                .getLocationsByCountry(country -> country.getName().equals("Portugal"))
                .forEach(System.out::println);
        System.out.println("----------------------------------------");

        System.out.println();
        System.out.println("ALL SECTIONS BY STORE");
        service
                .getSectionsByStore(store -> store.getId().equals("PT001"))
                .forEach(System.out::println);
        System.out.println("----------------------------------------");

        System.out.println();
        System.out.println("TOTAL REVENUE");
        result = service.getTotalRevenue();
        formattedResult = String.format(NUMBER_FORMAT, result);
        System.out.printf(RIGHT_FORMATTED, formattedResult);
        System.out.println("----------------------------------------");

        System.out.println();
        System.out.println("TOTAL REVENUE BY COUNTRY");
        result = service.getTotalRevenueByCountry(country -> country.getName().equals("Portugal"));
        formattedResult = String.format(NUMBER_FORMAT, result);
        System.out.printf(RIGHT_FORMATTED, formattedResult);
        System.out.println("----------------------------------------");

        System.out.println();
        System.out.println("TOTAL REVENUE BY COUNTRY AND STORE");
        result = service
                .getTotalRevenueByCountryAndStore(
                        country -> country.getName().equals("Portugal"),
                        store -> store.getId().equals("PT001")
                );
        formattedResult = String.format(NUMBER_FORMAT, result);
        System.out.printf(RIGHT_FORMATTED, formattedResult);
        System.out.println("----------------------------------------");
    }
}