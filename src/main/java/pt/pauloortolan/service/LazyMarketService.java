package pt.pauloortolan.service;

import pt.pauloortolan.pojo.Country;
import pt.pauloortolan.pojo.Market;
import pt.pauloortolan.pojo.Month;
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
        return getMarket()
                .getCountries()
                .stream()
                .map(Country::getName)
                .toList();
    }

    @Override
    public List<String> getStoresByCountry(Predicate<Country> countryPredicate) {
        return getMarket()
                .getCountries()
                .stream()
                .filter(countryPredicate)
                .map(Country::getStores)
                .flatMap(List::stream)
                .map(Store::getId)
                .distinct()
                .toList();
    }

    @Override
    public List<String> getLocationsByCountry(Predicate<Country> countryPredicate) {
        return getMarket()
                .getCountries()
                .stream()
                .filter(countryPredicate)
                .map(Country::getStores)
                .flatMap(List::stream)
                .map(Store::getLocation)
                .distinct()
                .toList();
    }

    @Override
    public List<String> getSectionsByStore(Predicate<Store> storePredicate) {
        return getMarket()
                .getCountries()
                .stream()
                .map(Country::getStores)
                .flatMap(List::stream)
                .map(Store::getSections)
                .flatMap(List::stream)
                .map(Section::getSection)
                .distinct()
                .toList();
    }

    @Override
    public double getTotalRevenue() {
        return getMarket()
                .getCountries()
                .stream()
                .map(Country::getStores)
                .flatMap(List::stream)
                .map(Store::getSections)
                .flatMap(List::stream)
                .map(Section::getRevenues)
                .flatMap(List::stream)
                .mapToDouble(Month::getRevenue)
                .sum();
    }

    @Override
    public double getTotalRevenueByCountry(Predicate<Country> countryPredicate) {
        return getMarket()
                .getCountries()
                .stream()
                .filter(countryPredicate)
                .map(Country::getStores)
                .flatMap(List::stream)
                .map(Store::getSections)
                .flatMap(List::stream)
                .map(Section::getRevenues)
                .flatMap(List::stream)
                .mapToDouble(Month::getRevenue)
                .sum();
    }

    @Override
    public double getTotalRevenueByCountryAndStore(Predicate<Country> countryPredicate, Predicate<Store> storePredicate) {
        return getMarket()
                .getCountries()
                .stream()
                .filter(countryPredicate)
                .map(Country::getStores)
                .flatMap(List::stream)
                .filter(storePredicate)
                .map(Store::getSections)
                .flatMap(List::stream)
                .map(Section::getRevenues)
                .flatMap(List::stream)
                .mapToDouble(Month::getRevenue)
                .sum();
    }

    @Override
    public double getTotalRevenueByCountryAndStoreAndSection(Predicate<Country> countryPredicate, Predicate<Store> storePredicate, Predicate<Section> sectionPredicate) {
        return getMarket()
                .getCountries()
                .stream()
                .filter(countryPredicate)
                .map(Country::getStores)
                .flatMap(List::stream)
                .filter(storePredicate)
                .map(Store::getSections)
                .flatMap(List::stream)
                .filter(sectionPredicate)
                .map(Section::getRevenues)
                .flatMap(List::stream)
                .mapToDouble(Month::getRevenue)
                .sum();
    }
}
