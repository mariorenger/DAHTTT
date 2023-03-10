package com.httt.backend.controller;

import com.google.common.io.Files;
import com.httt.backend.entity.User;
import com.httt.backend.service.S3Services;
import com.httt.backend.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayOutputStream;
import java.util.Date;
import java.util.List;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping(value = "/api/aws")
public class FileAWSController {
    S3Services s3Services;
    UserService userService;

    Logger logger = LoggerFactory.getLogger(FileAWSController.class);

    @Autowired
    public FileAWSController(S3Services s3Services, UserService userService) {
        this.s3Services = s3Services;
        this.userService = userService;
    }

    @Value("${isc.s3.bucket}")
    private String bucketName;

    @Value("${isc.aws.endpointUrl}")
    private String endpointUrl;
    private static String UPLOADED_FOLDER = "assets/images/users/";

    /**
     * Upload file
     *
     * @param file
     * @return keyName
     */

    @PostMapping("/file/upload")
    public String uploadMultipartFile(@RequestParam("file") MultipartFile file, @RequestParam String fileAs) {
        String keyName = "";
        switch (fileAs) {
            case "avatar": {
                String extension = Files.getFileExtension(file.getOriginalFilename());
                String username = userService.getUserName();
                keyName = username.concat("-avatar.").concat(extension);
                s3Services.uploadS3File(keyName, file);
                User user = userService.getUserByUsername(username).get();
                String avatarUrl = endpointUrl + "/" + bucketName + "/" + keyName;
                user.getProfile().setImage(avatarUrl);
                return avatarUrl;
            }
            case "course": {
                keyName = new Date().toString().concat(file.getOriginalFilename());

            }
            default: {
                keyName = file.getOriginalFilename();
                break;
            }
        }

        s3Services.uploadS3File(keyName, file);

        return endpointUrl + "/" + bucketName + "/" + keyName;
    }

    @PostMapping("/file/upload/course")
    public String uploadCourseImg(@RequestParam("file") MultipartFile file) {
        String keyName = new Date().toString().concat(file.getOriginalFilename());

        s3Services.uploadS3File(keyName, file);

        return "assets/images/course.png";
    }

    @PostMapping("/file/upload/avatar")
    public String uploadMultipartFile(@RequestParam("file") MultipartFile file) throws Exception {
        String username = userService.getUserName();
        /*
        Get url image of user if exists
         */
        final String imgUrlUser = userService.getUserByUsername(username).get().getProfile().getImage();
        String extension = Files.getFileExtension(file.getOriginalFilename());
//        String keyName = new Date().getTime() + username + "Avatar." + extension;
        String keyName = username + "Avatar." + extension;
        try {
            s3Services.uploadS3File(keyName, file);
//            if (!imgUrlUser.isEmpty()) {
//                String fileName = imgUrlUser.replace(endpointUrl + "/" + bucketName + "/", "");
//                s3Services.deleteFile(fileName);
//            }
            User user = userService.getUserByUsername(username).get();
            String avatarUrl = UPLOADED_FOLDER + keyName;
            user.getProfile().setImage(avatarUrl);
            userService.updateUser(user);
            return avatarUrl;

        } catch (Exception exception) {
            throw new Exception(exception.getMessage());
        }
    }

    /*
     * Download Files
     */
    @GetMapping("/file/{keyName}")
    public ResponseEntity<byte[]> downloadFile(@PathVariable String keyName) {
        ByteArrayOutputStream downloadInputStream = s3Services.downloadS3File(keyName);

        return ResponseEntity.ok()
                .contentType(contentType(keyName))
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + keyName + "\"")
                .body(downloadInputStream.toByteArray());
    }

    /*
     * List ALL Files
     */
    @GetMapping("/file/all")
    public List<String> listAllFiles() {
        return s3Services.listS3Files();
    }

    private MediaType contentType(String keyName) {
        String[] arr = keyName.split("\\.");
        String type = arr[arr.length - 1];
        switch (type) {
            case "txt":
                return MediaType.TEXT_PLAIN;
            case "png":
                return MediaType.IMAGE_PNG;
            case "jpg":
                return MediaType.IMAGE_JPEG;
            default:
                return MediaType.APPLICATION_OCTET_STREAM;
        }
    }


    /**
     * @param keyName
     * @return
     */
    @DeleteMapping(value = "/delete")
    public ResponseEntity<String> deleteFile(@RequestParam(value = "fileName") final String keyName) {
        s3Services.deleteFile(keyName);
        final String response = "[" + keyName + "] deleted successfully.";
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
