package pages;

import lombok.Data;

@Data
public class TableModel {

    String name;
    String complexity;

    public TableModel(String nameValue, String complexityValue) {
        this.name = nameValue;
        this.complexity = complexityValue;
    }
}
