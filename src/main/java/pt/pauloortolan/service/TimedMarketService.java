package pt.pauloortolan.service;

import pt.pauloortolan.pojo.Country;
import pt.pauloortolan.pojo.Market;
import pt.pauloortolan.pojo.Section;
import pt.pauloortolan.pojo.Store;

import java.util.List;
import java.util.function.Predicate;
import java.util.function.Supplier;

public class TimedMarketService extends MarketService {

    private final MarketService delegate;

    public TimedMarketService(Market market, MarketService delegate) {
        super(market);
        this.delegate = delegate;
    }

    @Override
    public List<String> getAllCountries() {
        return measureTime("ALL COUNTRIES", delegate::getAllCountries);
    }

    @Override
    public List<String> getStoresByCountry(Predicate<Country> countryPredicate) {
        return measureTime("STORES BY COUNTRY", () -> delegate.getStoresByCountry(countryPredicate));
    }

    @Override
    public List<String> getLocationsByCountry(Predicate<Country> countryPredicate) {
        return measureTime("LOCATIONS BY COUNTRY", () -> delegate.getLocationsByCountry(countryPredicate));
    }

    @Override
    public List<String> getSectionsByStore(Predicate<Store> storePredicate) {
        return measureTime("SECTIONS BY STORE", () -> delegate.getSectionsByStore(storePredicate));
    }

    @Override
    public double getTotalRevenue() {
        return measureTime("TOTAL REVENUE", delegate::getTotalRevenue);
    }

    @Override
    public double getTotalRevenueByCountry(Predicate<Country> countryPredicate) {
        return measureTime("TOTAL REVENUE BY COUNTRY",
                () -> delegate.getTotalRevenueByCountry(countryPredicate));
    }

    @Override
    public double getTotalRevenueByCountryAndStore(Predicate<Country> countryPredicate, Predicate<Store> storePredicate) {
        return measureTime("TOTAL REVENUE BY COUNTRY AND STORE",
                () -> delegate.getTotalRevenueByCountryAndStore(countryPredicate, storePredicate));
    }

    @Override
    public double getTotalRevenueByCountryAndStoreAndSection(Predicate<Country> countryPredicate, Predicate<Store> storePredicate, Predicate<Section> sectionPredicate) {
        return measureTime("TOTAL REVENUE BY COUNTRY AND STORE AND SECTION",
                () -> delegate.getTotalRevenueByCountryAndStoreAndSection(countryPredicate, storePredicate, sectionPredicate));
    }

    private <T> T measureTime(String useCase, Supplier<T> supplier) {
        long startTime = System.nanoTime();
        T result = supplier.get();
        long endTime = System.nanoTime();

        double durationMs = (endTime - startTime) / 1_000_000.0;
        System.out.printf("⏱️  %s executed in %.3f ms%n", useCase, durationMs);
        System.out.println("----------------------------------------");

        return result;
    }
}
