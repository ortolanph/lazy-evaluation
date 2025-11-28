package pt.pauloortolan.service;

import pt.pauloortolan.pojo.Country;
import pt.pauloortolan.pojo.Market;
import pt.pauloortolan.pojo.Section;
import pt.pauloortolan.pojo.Store;

import java.util.List;
import java.util.function.Predicate;

public class LazyMarketService extends MarketService {

    public LazyMarketService(Market market) {
        super(market);
    }

    @Override
    public List<String> getAllCountries() {
        return List.of();
    }

    @Override
    public List<String> getStoresByCountry(Predicate<Country> countryPredicate) {
        return List.of();
    }

    @Override
    public List<String> getLocationsByCountry(Predicate<Country> countryPredicate) {
        return List.of();
    }

    @Override
    public List<String> getSectionsByStore(Predicate<Store> storePredicate) {
        return List.of();
    }

    @Override
    public double getTotalRevenue() {
        return 0;
    }

    @Override
    public double getTotalRevenueByCountry(Predicate<Country> countryPredicate) {
        return 0;
    }

    @Override
    public double getTotalRevenueByCountryAndStore(Predicate<Country> countryPredicate, Predicate<Store> storePredicate) {
        return 0;
    }

    @Override
    public double getTotalRevenueByCountryAndStoreAndSection(Predicate<Country> countryPredicate, Predicate<Store> storePredicate, Predicate<Section> sectionPredicate) {
        return 0;
    }
}
