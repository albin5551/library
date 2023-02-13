package librarymanagement.library.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import librarymanagement.library.entity.Csv;
import librarymanagement.library.helper.CsvHelper;
import librarymanagement.library.message.ResponseMeassage;
import librarymanagement.library.service.CsvService;





// @CrossOrigin("http://localhost:8080")
@Controller
@RequestMapping("/csv")
public class CsvController {

    @Autowired
    CsvService csvService;


    @PostMapping("/upload")
  public ResponseEntity<ResponseMeassage> uploadFile(@RequestParam("file") MultipartFile file) {
    String message = "";

    if (CsvHelper .hasCSVFormat(file)) {
      try {
        csvService.save(file);

        message = "Uploaded the file successfully: " + file.getOriginalFilename();
        
        String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("/api/csv/download/")
                .path(file.getOriginalFilename())
                .toUriString();

        return ResponseEntity.status(HttpStatus.OK).body(new ResponseMeassage(message,fileDownloadUri));
      } catch (Exception e) {
        message = "Could not upload the file: " + file.getOriginalFilename() + "!";
        return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new ResponseMeassage(message,""));
      }
    }

    message = "Please upload a csv file!";
    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ResponseMeassage(message,""));
  }
    
  @GetMapping("/tutorials")
  public ResponseEntity<List<Csv>> getAllTutorials() {
    try {
      List<Csv> tutorials = csvService.getAll();

      if (tutorials.isEmpty()) {
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
      }

      return new ResponseEntity<>(tutorials, HttpStatus.OK);
    } catch (Exception e) {
      return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }

  @GetMapping("/download")
  public ResponseEntity<Resource> downloadFile() {
    InputStreamResource file = new InputStreamResource(csvService.load());

    return ResponseEntity.ok()
        .header(HttpHeaders.CONTENT_DISPOSITION, "attachment;")
        .contentType(MediaType.parseMediaType("application/csv"))
        .body(file);
  }
}
