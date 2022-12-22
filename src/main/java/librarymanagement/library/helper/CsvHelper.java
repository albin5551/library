package librarymanagement.library.helper;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

import javax.mail.Multipart;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVPrinter;
import org.apache.commons.csv.CSVRecord;
import org.apache.commons.csv.QuoteMode;
import org.springframework.web.multipart.MultipartFile;



import librarymanagement.library.entity.Csv;

public class CsvHelper {

    public static String TYPE="text/csv";
    static String[] HEADERs={"book_name","book_author","stock"};

    public static boolean hasCSVFormat(MultipartFile file){
        System.out.println(file.getContentType());
        if(TYPE.equals(file.getContentType())||file.getContentType().equals("application/vnd.ms-excel")){
            return true;
        }
        return false;
    }

    public static List<Csv>csvToDb(InputStream is){
        try(BufferedReader fileReader=new BufferedReader(new InputStreamReader(is,"UTF-8"));
        CSVParser csvParser = new CSVParser(fileReader,
        CSVFormat.DEFAULT.withFirstRecordAsHeader().withIgnoreHeaderCase().withTrim());) {
            List<Csv>csvList=new ArrayList<>();
            Iterable<CSVRecord> csvRecords=csvParser.getRecords();
            for(CSVRecord csvRecord:csvRecords){
                Csv csvob=new Csv(
                    csvRecord.get("bookName"),
                    csvRecord.get("bookAuthor"),
                    Integer.parseInt(csvRecord.get("stock")) 
                    

                );
                csvList.add(csvob);

            }
            return csvList;
            
    }catch(IOException e){
        throw new  RuntimeException("fail to parse csv file"+e.getMessage());
    }
    
}

public static ByteArrayInputStream loadFromdb(List<Csv>csvList){
    final CSVFormat format=CSVFormat.DEFAULT.withQuoteMode(QuoteMode.MINIMAL);
    try(ByteArrayOutputStream out=new ByteArrayOutputStream();
    CSVPrinter csvPrinter=new CSVPrinter(new PrintWriter(out),format);){
        String[] HEADERs={"book_id","book_name","book_author","stock"};
        csvPrinter.printRecord(HEADERs);
        for( Csv csv:csvList){
            List<String>data=Arrays.asList(
                String.valueOf(csv.getBookId()),
                csv.getBookName(),
                csv.getBookAuthor(),
                String.valueOf(csv.getStock())
            );
            csvPrinter.printRecord(data);

        }
        csvPrinter.flush();
        return new ByteArrayInputStream(out.toByteArray());
    }catch(IOException e){
        throw new RuntimeException("fail to import data to CSV file: " + e.getMessage());
    }
}
}
