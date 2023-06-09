package me.moteloff.tables.modules;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "tables")
public class DataTable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String name;

    @OneToMany(mappedBy = "table", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @Fetch(FetchMode.JOIN)
    private List<DataCell> cells = new ArrayList<>();

    @PrePersist
    private void init() {
        if (!cells.isEmpty()) return;
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                DataCell cell = new DataCell(this, i, j, "");
                cells.add(cell);
            }
        }
    }

    public DataCell getCell(int col, int row) {
        for (DataCell cell : cells) {
            if (cell.getRowIndex() == row && cell.getColIndex() == col) {
                return cell;
            }
        }
        return null;
    }

    public String refactorExpression(String expression) {
        Pattern pattern = Pattern.compile("[A-D][0-4]");
        Matcher matcher = pattern.matcher(expression);

        StringBuffer replacedExpression = new StringBuffer();
        while (matcher.find()) {
            String cell = matcher.group();

            char colChar = cell.charAt(0);
            int rowIndex = Integer.parseInt(cell.substring(1)) - 1;
            int colIndex = colChar - 'A';

            String cellValue = getCell(colIndex, rowIndex).getValue();
            matcher.appendReplacement(replacedExpression, cellValue);
        }

        matcher.appendTail(replacedExpression);
        String test = replacedExpression.toString();
        System.out.println(test);
        return test;
    }
}
