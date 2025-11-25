package pt.pauloortolan.loader;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Store {

    private String id;
    private int employees;
    private String location;
    private boolean delivery;

}
