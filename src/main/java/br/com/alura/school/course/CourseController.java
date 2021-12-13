package br.com.alura.school.course;

import static java.lang.String.format;
import static org.springframework.http.HttpStatus.NOT_FOUND;

import java.net.URI;
import java.util.List;

import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import br.com.alura.school.courseEnroll.EnrollRepository;

@RestController
class CourseController {

	private final CourseRepository courseRepository;
	private final EnrollRepository enrollRepository;

	CourseController(CourseRepository courseRepository, EnrollRepository enrollRepository) {
		this.courseRepository = courseRepository;
		this.enrollRepository = enrollRepository;
	}

	@GetMapping("/courses")
	ResponseEntity<List<CourseResponse>> allCourses() {
		List<Course> courses = courseRepository.findAll();
		return ResponseEntity.ok(CourseResponse.toConvert(courses));
	}

	@GetMapping("/courses/{code}")
	ResponseEntity<CourseResponse> courseByCode(@PathVariable("code") String code) {
		Course course = courseRepository.findByCode(code).orElseThrow(
				() -> new ResponseStatusException(NOT_FOUND, format("Course with code %s not found", code)));
		return ResponseEntity.ok(new CourseResponse(course));
	}

	@PostMapping("/courses")
	ResponseEntity<Void> newCourse(@RequestBody @Valid NewCourseRequest newCourseRequest) {
		courseRepository.save(newCourseRequest.toEntity());
		URI location = URI.create(format("/courses/%s", newCourseRequest.getCode()));
		return ResponseEntity.created(location).build();
	}
	
	//Não consegui implementar os endpoints de matrícula e relatório

//	@PostMapping("/courses/{courseCode}/enroll")
//    ResponseEntity<Void> newCourse(@PathVariable("code") String courseCode, @RequestBody @Valid NewEnrollRequest newEnrollRequest) {
//        Course course = courseRepository.findByCode(courseCode).orElseThrow(() -> new ResponseStatusException(NOT_FOUND, format("Course with code %s not found", courseCode)));
//        User user = userRepository.findByUsername(newEnrollRequest.username).orElseThrow(() -> new ResponseStatusException(NOT_FOUND, format("User %s not found", newEnrollRequest.username)));
//        Enroll enrollFounded = verificar se o course já tem o mesmo user matriculado
//        if (enrollFounded) {
//            Throw ResponseStatusException(BAD_REQUEST, format("User %s has a enroll to course ", course.name)));
//        }
//        Enroll enroll = enrollRepository.save(newEnrollRequest.toEntity());
//        return ResponseEntity.ok(new EnrollResponse(enroll));
//    }

//	@GetMapping("/report")
//	public List<EnrollResponse> enrollReport() {
//		return enrollRepository.reportEnroll();
//	}

}
