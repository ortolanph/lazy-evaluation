package pt.pauloortolan.service;

import pt.pauloortolan.pojo.Country;
import pt.pauloortolan.pojo.Market;
import pt.pauloortolan.pojo.Month;
import pt.pauloortolan.pojo.Section;
import pt.pauloortolan.pojo.Store;

import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Stream;

public class LazyMarketService extends MarketService {

    public LazyMarketService(Market market) {
        super(market);
    }

    protected Stream<Country> countryStream() {
        return getMarket()
                .getCountries()
                .stream();
    }

    protected Stream<Country> filteredCountryStream(Predicate<Country> countryPredicate) {
        return countryStream()
                .filter(countryPredicate);
    }

    protected Stream<Store> storeStream(Stream<Country> countryStream) {
        return countryStream
                .map(Country::getStores)
                .flatMap(List::stream);
    }

    protected Stream<Store> filteredStoreStream(Stream<Country> countryStream, Predicate<Store> storePredicate) {
        return countryStream
                .map(Country::getStores)
                .flatMap(List::stream)
                .filter(storePredicate);
    }

    protected Stream<Section> sectionStream(Stream<Store> storeStream) {
        return storeStream
                .map(Store::getSections)
                .flatMap(List::stream);
    }

    protected Stream<Section> filteredSectionStream(Stream<Store> storeStream, Predicate<Section> sectionPredicate) {
        return storeStream
                .map(Store::getSections)
                .flatMap(List::stream)
                .filter(sectionPredicate);
    }

    protected Stream<Month> revenueStream(Stream<Section> sectionStream) {
        return sectionStream
                .map(Section::getRevenues)
                .flatMap(List::stream);
    }


    @Override
    public List<String> getAllCountries() {
        return countryStream()
                .map(Country::getName)
                .toList();
    }

    @Override
    public List<String> getStoresByCountry(Predicate<Country> countryPredicate) {
        return storeStream(filteredCountryStream(countryPredicate))
                .map(Store::getId)
                .distinct()
                .toList();
    }

    @Override
    public List<String> getLocationsByCountry(Predicate<Country> countryPredicate) {
        return storeStream(filteredCountryStream(countryPredicate))
                .map(Store::getLocation)
                .distinct()
                .toList();
    }

    @Override
    public List<String> getSectionsByStore(Predicate<Store> storePredicate) {
        return filteredStoreStream(countryStream(), storePredicate)
                .map(Store::getSections)
                .flatMap(List::stream)
                .map(Section::getSection)
                .distinct()
                .toList();
    }

    @Override
    public double getTotalRevenue() {
        return revenueStream(
                sectionStream(
                        storeStream(
                                countryStream()
                        )
                )
        )
                .mapToDouble(Month::getRevenue)
                .sum();
    }

    @Override
    public double getTotalRevenueByCountry(Predicate<Country> countryPredicate) {
        return revenueStream(
                sectionStream(
                        storeStream(
                                filteredCountryStream(countryPredicate)
                        )
                )
        )
                .mapToDouble(Month::getRevenue)
                .sum();
    }

    @Override
    public double getTotalRevenueByCountryAndStore(Predicate<Country> countryPredicate, Predicate<Store> storePredicate) {
        return revenueStream(
                sectionStream(
                        filteredStoreStream(
                                filteredCountryStream(countryPredicate),
                                storePredicate
                        )
                )
        )
                .mapToDouble(Month::getRevenue)
                .sum();
    }

    @Override
    public double getTotalRevenueByCountryAndStoreAndSection(Predicate<Country> countryPredicate, Predicate<Store> storePredicate, Predicate<Section> sectionPredicate) {
        return revenueStream(
                filteredSectionStream(
                        filteredStoreStream(
                                filteredCountryStream(countryPredicate),
                                storePredicate
                        ),
                        sectionPredicate
                )
        )
                .mapToDouble(Month::getRevenue)
                .sum();
    }
}
