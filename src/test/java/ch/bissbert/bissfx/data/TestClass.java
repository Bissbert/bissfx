package ch.bissbert.bissfx.data;

import ch.bissbert.bissfx.data.csv.CsvValue;
import lombok.*;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TestClass {
    @CsvValue(name = "id", index = 0)
    private int id;
    @CsvValue(name = "name", index = 1)
    private String name;
}
