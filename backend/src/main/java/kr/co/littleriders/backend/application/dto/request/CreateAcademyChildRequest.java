package kr.co.littleriders.backend.application.dto.request;

import jakarta.validation.constraints.NotBlank;
import kr.co.littleriders.backend.domain.academy.entity.Academy;
import kr.co.littleriders.backend.domain.academy.entity.AcademyChild;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDate;

public class CreateAcademyChildRequest {

    @NotBlank
    private String name;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate birthDate;

    @NotBlank
    private String gender;

    private MultipartFile image;


    public AcademyChild toAcademyChild(Academy academy){
        return null;
    }

}
