package com.example.hw9_files;

import com.codeborne.pdftest.PDF;
import com.codeborne.xlstest.XLS;
import com.example.hw9_files.domain.People;
import com.opencsv.CSVReader;
import io.qameta.allure.internal.shadowed.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;
import java.util.zip.ZipFile;

import static java.nio.charset.StandardCharsets.UTF_8;
import static org.assertj.core.api.Assertions.assertThat;


public class FilesTest {
    ClassLoader classLoader = FilesTest.class.getClassLoader();
    String zipFullPath = "src/test/resources/Documents.zip";
    String pdfFilename = "pdf.pdf";
    String csvFilename = "csv.csv";
    String xlsFilename = "xlsx.xlsx";
    String jsonFilename = "test.json";

    private InputStream getFileFromZipByName(String filename, String zipFullPath) throws Exception {
        File zipFile = new File(zipFullPath);
        ZipFile zip = new ZipFile(zipFile);
        return zip.getInputStream(zip.getEntry(filename));
    }

    @Test
    void pdfTest() throws Exception {

        try (InputStream is = getFileFromZipByName(pdfFilename, zipFullPath)) {
            PDF pdfFile = new PDF(is);
            assertThat(pdfFile.text).contains("File to Test");
        }
    }

    @Test
    void csvTest() throws Exception {
        try (InputStream is = getFileFromZipByName(csvFilename, zipFullPath)) {
            CSVReader csvReader = new CSVReader(new InputStreamReader(is, UTF_8));
            List<String[]> csv = csvReader.readAll();
            assertThat(csv).contains(
                    new String[]{"id", "firstname", "age"},
                    new String[]{"1", "Maxim", "18"},
                    new String[]{"2", "Andrey", "22"},
                    new String[]{"3", "Leonid", "55"}

            );
        }

    }

    @Test
    void xlsxTest() throws Exception {
        try (InputStream is = getFileFromZipByName(xlsFilename, zipFullPath)) {
            XLS xlsx = new XLS(is);
            assertThat(xlsx.excel.getSheetAt(0).getRow(0).getCell(0).getStringCellValue()).contains("id");
            assertThat(xlsx.excel.getSheetAt(0).getRow(1).getCell(1).getStringCellValue()).contains("Maxim");
            assertThat(xlsx.excel.getSheetAt(0).getRow(2).getCell(2).getNumericCellValue()).isEqualTo(22);
        }

    }

    @Test
    void jsonJacksonTest() throws Exception {
        try (InputStream is = classLoader.getResourceAsStream(jsonFilename)) {
            ObjectMapper mapper = new ObjectMapper();
            People people = mapper.readValue(is, People.class);
            assertThat(people.name).isEqualTo("Alexey");
            assertThat(people.mobile).isEqualTo("+79001234567");
            assertThat(people.married).isTrue();
            assertThat(people.pets).isEqualTo(new String[]{"Dog", "cat"});
            assertThat(people.country).isEqualTo("RU");


        }
    }
}
