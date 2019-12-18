package com.devop.aashish.utility;

import com.devop.aashish.constant.PropertyFileConstant;
import com.devop.aashish.model.UIComponent;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;


public class ExcelDataHelper {

    public static List<UIComponent> readExcelData(String fileName) throws IOException {
        String filePath = PropertyFileConstant.DIR_NAME_PROPERTY.
                concat(File.separator).concat(PropertyFileConstant.ENTITY_DIRECTORY)
                .concat(File.separator).concat(fileName).concat(".xlsx");
        File file = new File(filePath);
        if (!file.exists() || !file.canRead()) {
            return Collections.emptyList();
        }
        List<UIComponent> list = new ArrayList<>();
        FileInputStream inputStream = new FileInputStream(file);
        Workbook workbook = new XSSFWorkbook(inputStream);
        int size = workbook.getNumberOfSheets();
        for (int i = 0; i < size; i++) {
            Sheet sheet = (Sheet) workbook.getSheetAt(i);
            readComponent(sheet, list);
            inputStream.close();
        }
        return list;
    }

    private static void readComponent(Sheet sheet, List<UIComponent> list) {
        for (Row nextRow : sheet) {
            if (nextRow.getRowNum() == 0) {
                continue;
            }
            Iterator<Cell> cellIterator = nextRow.cellIterator();
            UIComponent ui = new UIComponent();

            while (cellIterator.hasNext()) {
                Cell cell = cellIterator.next();
                switch (cell.getColumnIndex()) {
                    case 0:
                        ui.setAttributeName(cell.getStringCellValue());
                        break;
                    case 1:
                        ui.setAttributeType(cell.getStringCellValue());
                        break;
                    case 2:
                        ui.setHintText(cell.getStringCellValue());
                        break;
                    case 3:
                        ui.setRowItem(cell.getBooleanCellValue());
                        break;
                }

            }
            list.add(ui);
        }
    }


}
