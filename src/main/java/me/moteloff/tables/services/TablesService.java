package me.moteloff.tables.services;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import me.moteloff.tables.modules.DataTable;
import me.moteloff.tables.repositrories.TableRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
@Slf4j
@RequiredArgsConstructor
public class TablesService {

    private final TableRepository tableRepository;
    public List<DataTable> getAllTables() {
        log.info("Getting Table");
        return tableRepository.findAll();
    }

    public void saveTable(DataTable table) {
        log.info("Saving Table");
        tableRepository.save(table);
    }

    public void deleteTable(Long id) {
        log.info("Deleting Table");
        tableRepository.deleteById(id);
    }

    public DataTable getTable(Long id) {
        return tableRepository.findById(id).orElse(null);
    }
}
