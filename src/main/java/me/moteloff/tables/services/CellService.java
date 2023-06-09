package me.moteloff.tables.services;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import me.moteloff.tables.exeptions.ExpressionException;
import me.moteloff.tables.modules.DataCell;
import me.moteloff.tables.modules.DataTable;
import me.moteloff.tables.repositrories.CellRepository;
import me.moteloff.tables.repositrories.TableRepository;
import org.springframework.stereotype.Service;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
@Slf4j
@RequiredArgsConstructor
public class CellService {
    private final CellRepository cellRepository;
    private final TableRepository tableRepository;

    public void saveCell(long tableId, int colIndex, int rowIndex, String value) {
        DataCell cell = cellRepository.findByTable_IdAndColIndexAndRowIndex(tableId, colIndex, rowIndex);
        cell.setValue(value);
        cellRepository.save(cell);
    }
}
