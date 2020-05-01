package pl.phyriak.springboothostingimages;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import org.springframework.stereotype.Service;
import pl.phyriak.springboothostingimages.model.Image;
import pl.phyriak.springboothostingimages.repo.ImageRepo;

import java.io.File;
import java.io.IOException;
import java.util.Map;

@Service
public class ImageUploader {
    private Cloudinary cloudinary;
    private ImageRepo imageRepo;

    public ImageUploader(ImageRepo imageRepo) {
        this.imageRepo=imageRepo;
        cloudinary = new Cloudinary(ObjectUtils.asMap(
                "cloud_name", "dzth8bme5",
                "api_key", "186827599689354",
                "api_secret", "aqexh_UB2mzIvhVqknfUothzlwo"));
    }

    public String uploadFileAndSaveToDb(String path) {
        File file = new File(path);
        Map uploadResult = null;
        try {
            uploadResult = cloudinary.uploader().upload(file, ObjectUtils.emptyMap());
            imageRepo.save(new Image(uploadResult.get("url").toString()));
        } catch (IOException e) {
            //todo
        }
        return uploadResult.get("url").toString();
    }
}