package me.moteloff.tables.controllers;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import me.moteloff.tables.exeptions.ExpressionException;
import me.moteloff.tables.modules.DataTable;
import me.moteloff.tables.services.CellService;
import me.moteloff.tables.services.TablesService;
import me.moteloff.tables.utils.Calculator;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
@Slf4j
@Async
public class TableController {
    private final TablesService tablesService;
    private final CellService cellService;

    @GetMapping("/")
    public String tables(Model model) {
        model.addAttribute("tables", tablesService.getAllTables());
        return "tables";
    }

    @GetMapping("/tables/{id}")
    public String productInfo(@PathVariable Long id, Model model) {
        model.addAttribute("table", tablesService.getTable(id));
        return "table-template";
    }

    @PostMapping("/tables/create")
    public String createProduct(DataTable table) {
        tablesService.saveTable(table);
        return "redirect:/";
    }

    @PostMapping("/tables/delete/{id}")
    public String deleteTable(@PathVariable Long id) {
        tablesService.deleteTable(id);
        return "redirect:/";
    }

    @PostMapping("/tables/update/{id}")
    public @ResponseBody String updateTable(@PathVariable("id") Long tableId, @RequestParam("rowIndex") int rowIndex, @RequestParam("colIndex") int colIndex, @RequestParam("expression") String expression) {
        try {
            DataTable table = tablesService.getTable(tableId);

            String refactoredExpression = table.refactorExpression(expression);
            String calculatedExpression = Calculator.evaluate(refactoredExpression);

            cellService.saveCell(tableId, colIndex, rowIndex, calculatedExpression);

            return calculatedExpression;
        } catch (ExpressionException e) {log.error(e.getMessage());}
        return "Ошибка записи";
    }
}
