package pt.pauloortolan.pojo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import pt.pauloortolan.loader.Country;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Market {

    private String name;
    private int year;
    private List<Country> countries;

}
