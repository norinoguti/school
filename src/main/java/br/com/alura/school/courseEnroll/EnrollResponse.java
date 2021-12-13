package br.com.alura.school.courseEnroll;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonProperty;

class EnrollResponse {

    @JsonProperty
    private final String code;

    @JsonProperty
    private final String username;

    @JsonProperty
    private final LocalDate enrolldate;

    EnrollResponse(Enroll enroll) {
        this.code = enroll.getCode();
        this.username = enroll.getUserName();
        this.enrolldate = enroll.getEnrolldate();
    }

}
