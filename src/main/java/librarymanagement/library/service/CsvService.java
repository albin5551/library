package librarymanagement.library.service;

import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import librarymanagement.library.entity.Csv;
import librarymanagement.library.helper.CsvHelper;
import librarymanagement.library.repository.CsvRepository;

@Service
public class CsvService {
    
    @Autowired
    CsvRepository repository;

    public void save(MultipartFile file){
        try{
            List<Csv>csvTest=CsvHelper.csvToDb(file.getInputStream());
            repository.saveAll(csvTest);
        }catch(IOException e){
            throw new RuntimeException("fail to store csv data"+e.getMessage());
        }
    }

    public ByteArrayInputStream load(){
        List<Csv> csvTest=repository.findAll();
        ByteArrayInputStream in=CsvHelper.loadFromdb(csvTest);
        return in;
    }

    public List<Csv>getAll(){
        return repository.findAll();
    }
}
