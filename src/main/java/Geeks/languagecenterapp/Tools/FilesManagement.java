package Geeks.languagecenterapp.Tools;

import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class FilesManagement {

    public static final String UPLOAD_DIR = "language-center-app/uploads";  

    public static String uploadSingleFile(MultipartFile file) {
        if (file == null || file.isEmpty()) {
            return null;
        }
        try {
            
            Path projectDir = Paths.get("").toAbsolutePath();

            
            Path uploadPath = projectDir.resolve(UPLOAD_DIR);

            
            if (!Files.exists(uploadPath)) {
                Files.createDirectories(uploadPath);
            }

            
            Path filePath = uploadPath.resolve(Objects.requireNonNull(file.getOriginalFilename()));
            file.transferTo(filePath.toFile());
            return filePath.toAbsolutePath().toString();

        } catch (IOException e) {
            System.err.println("Error uploading file: " + e.getMessage());
            return null;
        }
    }

    public static List<String> uploadMultipleFile(List<MultipartFile> files) {
        List<String> filePaths = new ArrayList<>();

        if (files == null || files.isEmpty()) {
            return null;
        }
        try {
            
            Path projectDir = Paths.get("").toAbsolutePath();

            
            Path uploadPath = projectDir.resolve(UPLOAD_DIR);

            
            if (!Files.exists(uploadPath)) {
                Files.createDirectories(uploadPath);
            }

            
            for (MultipartFile element : files) {
                if (element != null && !element.isEmpty()) {
                    Path filePath = uploadPath.resolve(Objects.requireNonNull(element.getOriginalFilename()));
                    element.transferTo(filePath.toFile());
                    filePaths.add(filePath.toAbsolutePath().toString());
                }
            }

        } catch (IOException e) {
            System.err.println("Error uploading files: " + e.getMessage());
            return null;
        }
        return filePaths;
    }
}
