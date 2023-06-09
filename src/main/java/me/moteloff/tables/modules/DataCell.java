package me.moteloff.tables.modules;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@Table(name = "cells")
public class DataCell {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "table_id")
    private DataTable table;
    private int colIndex;
    private int rowIndex;
    private String value;

    public DataCell(DataTable table, int column_index, int row_index, String value) {
        this.table = table;
        this.colIndex = column_index;
        this.rowIndex = row_index;
        this.value = value;
    }
}
