package pt.pauloortolan.loader;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Data;
import pt.pauloortolan.pojo.Market;

import java.io.IOException;

@Data
public class RevenueLoader {

    private Market marketData;

    public RevenueLoader(String fileName) {
        ObjectMapper mapper = new ObjectMapper();

        try {
            marketData = mapper.readValue(
                this.getClass().getClassLoader().getResourceAsStream(fileName),
                Market.class
            );
        } catch (IOException exception) {
            System.out.printf(exception.getMessage());
        }
    }

}
