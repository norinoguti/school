package br.com.alura.school.courseEnroll;

import static javax.persistence.GenerationType.IDENTITY;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Entity
@Table(name = "enroll")
public class Enroll {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;

    @Size(max=10)
    @NotBlank
    @Column(nullable = false, unique = true)
    private String code;

    @Size(max=20)
    @NotBlank
    @Column(nullable = false, unique = true)
    private String username;

    private LocalDate enrolldate;

    Enroll(String code, String username, LocalDate enrolldate) {
        this.code = code;
        this.username = username;
        this.enrolldate = enrolldate;
    }

    String getCode() {
        return code;
    }

    String getUserName() {
        return username;
    }

    LocalDate getEnrolldate() {
		return enrolldate;
	}

}
