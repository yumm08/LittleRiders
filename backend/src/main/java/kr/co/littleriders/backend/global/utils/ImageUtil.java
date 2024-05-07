package kr.co.littleriders.backend.global.utils;

import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.http.MediaType;

import kr.co.littleriders.backend.global.error.code.ImageErrorCode;
import kr.co.littleriders.backend.global.error.exception.ImageException;

@Component

public class ImageUtil {
	private final String BASE_PATH;

	public ImageUtil(@Value("${spring.resource.directory}") String basePath) {
		BASE_PATH = basePath;
	}

	public String saveImage(MultipartFile file)  {

		// 확장자 valid 검사
		String originName = file.getOriginalFilename(); //원본 이미지 이름
		String ext = getExtension(StringUtils.getFilenameExtension(originName)); //확장자
		String generatedName = UUID.randomUUID() + ext;

		//multipartFile -> 받아서 rename 해주고
		byte[] bytes = new byte[0];
		try {
			bytes = file.getBytes();
			Path path = Paths.get(BASE_PATH, generatedName);
			Files.write(path, bytes);

			return path.toString();
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	private String getExtension(String extension) {
		switch (extension.toLowerCase()) {
			case "jpeg":
			case "jpg":
			case "png":
				return "." + extension;
			default:
				throw ImageException.from(ImageErrorCode.ILLEGAL_EXTENSION);
		}
	}

	private MediaType getMediaType(String imagePath) {

		String extension = null;
		int dotIndex = imagePath.lastIndexOf('.');
		if (dotIndex > 0 && dotIndex < imagePath.length() - 1) {
			extension = imagePath.substring(dotIndex + 1);
		}

		switch (extension.toLowerCase()) {
			case "jpeg":
			case "jpg":
				return MediaType.IMAGE_JPEG;
			case "png":
				return MediaType.IMAGE_PNG;
			default:
				throw ImageException.from(ImageErrorCode.ILLEGAL_EXTENSION);
		}
	}

	public Map<String, Object> getImage(String imagePath) {
		Map<String, Object> result = new HashMap<>();
		Path imageFilePath = Paths.get(imagePath);

		try {
			Resource imageResource = new UrlResource(imageFilePath.toUri());
			MediaType mediaType = getMediaType(imagePath);

			result.put("resource", imageResource);
			result.put("mediaType", mediaType);
		} catch (MalformedURLException urlException) {
			throw ImageException.from(ImageErrorCode.NOT_FOUND);
		}

		return result;
    }

	public Map<String, Object> getImageByUUID(String uuid) {

		String imagePath = BASE_PATH + "/" + uuid;
		return getImage(imagePath);
	}
}
