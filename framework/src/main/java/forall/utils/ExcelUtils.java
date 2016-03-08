package forall.utils;

import javaslang.control.Try;
import one.util.streamex.EntryStream;
import org.apache.commons.collections.IteratorUtils;
import org.apache.poi.ss.usermodel.*;

import java.io.File;
import java.lang.reflect.Method;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

import static java.util.stream.Collectors.toList;

/**
 * Author: Serhii Korol.
 */
public final class ExcelUtils {

    // global thread-safe workbooks storage, where key = filename, value = workbook
    private static final Map<String, Workbook> DATA_SOURCES = new ConcurrentHashMap<>();

    public static void clearDataStorage() {
        // try to close all workbooks within global data storage
        DATA_SOURCES.values().forEach(workbook -> Try.run(workbook::close));
        // cleanup storage
        DATA_SOURCES.clear();
    }

    public static List<Object[]> getData(final String filePath, final String sheetName, final Method method) {
        openWorkbook(filePath);

        // iterate through each worksheet row
        return getRows(filePath, sheetName)
                // ignore first row (header) and associate current test with corresponding row
                .filterKeyValue((idx, row) -> idx > 0 && isValidRow(row, method))
                // iterate through each cell in a current row
                .mapValues(row -> StreamUtils.asStream(row.cellIterator())
                        // ignore first cell (test name)
                        .filter(cell -> cell.getColumnIndex() > 0)
                        // read cell value
                        .map(ExcelUtils::getCellValue)
                        // put all values into array
                        .toArray())
                // check if values' array size > 0
                .filterValues(values -> values.length > 0)
                // EntryStream is an index-based collection (pairs of indexes and rows), so we need to get only rows, as output
                .values()
                // collect all rows into list
                .collect(toList());
    }

    private static String getCellValue(final Cell cell) {
        // parse current cell's value and return its String representation
        switch (cell.getCellType()) {
            // if cell type is number, transform it to String
            case Cell.CELL_TYPE_NUMERIC:
                return String.valueOf((int) cell.getNumericCellValue());
            case Cell.CELL_TYPE_STRING:
            default:
                return cell.getStringCellValue();
        }
    }

    private static boolean isValidRow(final Row row, final Method method) {
        // check if first cell value = current test name
        return row.getCell(0).getStringCellValue().equals(method.getName());
    }

    @SuppressWarnings("unchecked")
    private static EntryStream<Integer, Row> getRows(final String filePath, final String sheetName) {
        // try to read workbook from global storage
        return Try.of(() -> DATA_SOURCES.get(filePath))
                // try to read worksheet and get Rows iterator
                .map(book -> book.getSheet(sheetName).iterator())
                // check if worksheet exists
                .filter(Objects::nonNull)
                // transform Rows iterator to EntryStream to get better control over collection for further processing
                .map(sheet -> EntryStream.of((List<Row>) IteratorUtils.toList(sheet)))
                // in case of error, throw an exception
                .orElseThrow(() -> new IllegalArgumentException("Unable to open the following worksheet " + sheetName));
    }

    private static void openWorkbook(final String filePath) {
        // try to open excel file
        Try.of(() -> WorkbookFactory.create(new File(filePath)))
                // if file has been successfully opened, add workbook into global workbooks storage
                .onSuccess(workbook -> DATA_SOURCES.putIfAbsent(filePath, workbook))
                // in case of opening error, throw an exception
                .orElseThrow(ex -> new IllegalArgumentException("Unable to open the following file " + filePath, ex));
    }

    private ExcelUtils() {
    }
}