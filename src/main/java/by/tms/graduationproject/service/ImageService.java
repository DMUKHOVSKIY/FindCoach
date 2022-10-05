package by.tms.graduationproject.service;

import by.tms.graduationproject.entity.Coach;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

@Service
@Slf4j
@RequiredArgsConstructor
public class ImageService {
    public String upload(MultipartFile image, String path) throws IOException {
        String resultImageName = null;
        if (!image.isEmpty()) {

            File uploadDir = new File(path);

            if (!uploadDir.exists()) {
                uploadDir.mkdir();
            }

            resultImageName = createUniqueImageName(image);
            image.transferTo(new File(path + "/" + resultImageName));
        }
        return resultImageName;
    }

    private String createUniqueImageName(MultipartFile image) {
        String uuidFile = UUID.randomUUID().toString();
        return uuidFile + "." + image.getOriginalFilename();
    }
}
