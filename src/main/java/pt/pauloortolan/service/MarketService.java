package pt.pauloortolan.service;

import lombok.Getter;
import pt.pauloortolan.pojo.Country;
import pt.pauloortolan.pojo.Market;
import pt.pauloortolan.pojo.Store;

import java.util.List;
import java.util.function.Predicate;

@Getter
public abstract class MarketService {

    private final Market market;

    public MarketService(Market market) {
        this.market = market;
    }

    public abstract List<String> getAllCountries();

    public abstract List<String> getStoresByCountry(Predicate<Country> countryPredicate);

    public abstract List<String> getLocationsByCountry(Predicate<Country> countryPredicate);

    public abstract List<String> getSectionsByStore(Predicate<Store> storePredicate);

    public abstract double getTotalRevenue();

    public abstract double getTotalRevenueByCountry(Predicate<Country> countryPredicate);

    public abstract double getTotalRevenueByCountryAndStore(Predicate<Country> countryPredicate, Predicate<Store> storePredicate);

}
