package me.moteloff.tables.repositrories;

import me.moteloff.tables.modules.DataTable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TableRepository extends JpaRepository<DataTable, Long> {
    DataTable findByName(String name);
}
