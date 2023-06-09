package me.moteloff.tables.repositrories;

import me.moteloff.tables.modules.DataCell;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CellRepository extends JpaRepository <DataCell, Long> {
    DataCell findByTable_IdAndColIndexAndRowIndex(long tableId, int colIndex, int rowIndex);
}
