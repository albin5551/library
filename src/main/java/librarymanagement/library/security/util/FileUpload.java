package librarymanagement.library.security.util;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

import org.springframework.web.multipart.MultipartFile;
import librarymanagement.library.exception.BadRequestException;

public class FileUpload {
    //   public static final String PATH = "/home/albinantony/Desktop/Libarymanagement/library/src/main/resources/images/";
    public static final String PATH = "/home/albinantony/Desktop/Libarymanagement/libraryfr/library/libraryfr/src/assets/";

    public static final String PROFILE_PIC_DIR = "item_pics/";

    private FileUpload() {
    }

    public static void saveBookImage(String fileName, MultipartFile file) {

        saveFile(PROFILE_PIC_DIR, fileName, file);

    }

    public static void saveFile(String uploadDir, String fileName, MultipartFile file) {

        Path uploadPath = Paths.get(PATH + uploadDir);

        if (!Files.exists(uploadPath)) {
            try {
                Files.createDirectories(uploadPath);
            } catch (IOException e) {
                e.printStackTrace();
                throw new BadRequestException("FIle Upload Failed");
            }
        }

        try (InputStream inputStream = file.getInputStream()) {
            Path filePath = uploadPath.resolve(fileName);
            Files.copy(inputStream, filePath, StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException ioe) {
            throw new BadRequestException("Could not save file" + ioe);
        }

    }

    public static byte[] getImage(String fileName) {

        return getFile(PROFILE_PIC_DIR, fileName);

    }

    public static byte[] getFile(String dir, String fileName) {

        byte[] file;
        try {
            file = Files.readAllBytes(Path.of(PATH + dir + fileName));
        } catch (IOException e) {
            throw new BadRequestException("File Not Found");
        }
        return file;
    }

}
