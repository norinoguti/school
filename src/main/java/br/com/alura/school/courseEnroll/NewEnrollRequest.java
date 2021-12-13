package br.com.alura.school.courseEnroll;

import br.com.alura.school.course.Course;
import br.com.alura.school.support.validation.Unique;
import br.com.alura.school.user.User;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.LocalDate;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class NewEnrollRequest {

    @Unique(entity = Course.class, field = "code")
    @Size(max=10)
    @NotBlank
    @JsonProperty
    private final String code;

    @Unique(entity = User.class, field = "username")
    @Size(max=20)
    @NotBlank
    @JsonProperty
    private final String username;

    @JsonProperty
    private final LocalDate enrolldate;

    public NewEnrollRequest(String code, String username, LocalDate enrolldate) {
        this.code = code;
        this.username = username;
        this.enrolldate = enrolldate;
    }

    public String getCode() {
        return code;
    }

    public String getUsername() {
        return username;
    }

    public LocalDate getEnrollDate() {
        return enrolldate;
    }

    Enroll toEntity() {
    	return new Enroll(code, username, enrolldate);
    }
}
