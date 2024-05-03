package kr.co.littleriders.backend.global.utils;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

@Component

public class ImageUtil {
	private final String BASE_PATH;

	public ImageUtil(@Value("${spring.resource.directory}") String basePath) {
		BASE_PATH = basePath;
	}

	public String saveImage(MultipartFile multipartFile){
		// valid 확장자 검사

		//multipartFile -> 받아서 rename 해주고
		String generatedName = null;

		return BASE_PATH + "/" + generatedName;
	}

	//image valid check 의 담당을 해야될거같다고 생각해
	//.png, .jpg 이런거 정도만?


}
