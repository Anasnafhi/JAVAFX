package com.example.demo2.service;

import com.example.demo2.dao.ComputerDao;
import com.example.demo2.dao.impl.ComputerDaoImp;
import com.example.demo2.entities.Computer;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;


import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class ComputerService {
    private ComputerDao computerDao = new ComputerDaoImp();

    public List<Computer> findAll() {
        return computerDao.findAll();
    }

    public void save(Computer computer) {
        computerDao.insert(computer);
    }

    public void update(Computer computer) {
        computerDao.update(computer);
    }

    public void remove(Computer computer) {
        computerDao.deleteById(computer.getId());
    }
    public Computer findById(Integer id){ return computerDao.findById(id);}

    public void importerDepuisExcel(String path) throws ParseException {
        try (Workbook workbook = WorkbookFactory.create(new FileInputStream(path))) {
            Sheet sheet = workbook.getSheetAt(0);

            // Start from row 1 as row 0 contains the header
            for (int i = 1; i <= ((Sheet) sheet).getLastRowNum(); i++) {
                Row row = sheet.getRow(i);

                int id = (int) row.getCell(1).getNumericCellValue();
                String name = row.getCell(2).getStringCellValue();
                String marque= row.getCell(3).getStringCellValue();
                int prix = (int) row.getCell(4).getNumericCellValue();
                Computer.generations generation= Computer.generations.valueOf(row.getCell(5).getStringCellValue());
                Computer.typePros typePros= Computer.typePros.valueOf( row.getCell(6).getStringCellValue());

                double dateValue = row.getCell(7).getNumericCellValue();
                boolean ssd  = row.getCell(8).getBooleanCellValue();

                // Parse the date using SimpleDateFormat
                //SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
                //Date dateCreation = dateFormat.parse(dateStr);
                Date dateCreation = DateUtil.getJavaDate(dateValue);

                // Create the  object
                Computer computer = new Computer(id, name,marque,prix,ssd,dateCreation,generation, typePros);

                // Save or process the equipe object as needed
                save(computer);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void exporterVersExcel(String path) {
        List<Computer> computers = findAll();
        // Create a date format for formatting the date
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        // Create a new workbook
        try (Workbook workbook = new XSSFWorkbook()) {
            // Create a new sheet
            Sheet sheet = workbook.createSheet("Computers");
            // Create header row
            Row headerRow = sheet.createRow(0);
            headerRow.createCell(0).setCellValue("ID");
            headerRow.createCell(1).setCellValue("Marque");
            headerRow.createCell(2).setCellValue("Name");
            headerRow.createCell(3).setCellValue("Prix");
            headerRow.createCell(4).setCellValue("Generation");
            headerRow.createCell(5).setCellValue("TypeProcessor");
            headerRow.createCell(6).setCellValue("ssd");

            headerRow.createCell(7).setCellValue("Date de création");
            // Create data rows
            int rowNumber = 1;
            for (Computer computer : computers) {
                Row row = sheet.createRow(rowNumber++);
                row.createCell(0).setCellValue(computer.getId());
                row.createCell(1).setCellValue(computer.getMarque());
                row.createCell(2).setCellValue(computer.getName());
                row.createCell(3).setCellValue(computer.getPrix());
                row.createCell(4).setCellValue(String.valueOf(computer.getGeneration()));
                row.createCell(5).setCellValue(String.valueOf(computer.getTypeProcesor()));
                row.createCell(6).setCellValue(computer.isSsd());
                row.createCell(7).setCellValue(dateFormat.format(computer.getAnneFabri()));
            }
            // Auto-size columns
            for (int i = 0; i < 4; i++) {
                sheet.autoSizeColumn(i);
            }
            // Write the workbook to a file
            try (FileOutputStream fos = new FileOutputStream(path)) {
                workbook.write(fos);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}