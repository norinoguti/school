package br.com.alura.school.courseEnroll;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.alura.school.course.Course;
import br.com.alura.school.user.User;

public interface EnrollRepository extends JpaRepository<Enroll, Long> {

	/*
	 * @Query("SELECT " +
	 * "new br.com.alura.scholl.courseEnroll.EnrollReportResponse(" + "u.email," +
	 * " COUNT (e.code) AS quantidade_matriculas) " +
	 * "FROM enroll e INNER JOIN user u ON" +
	 * " e.username = u.username GROUP BY u.email")
	 */
	// List<EnrollReportResponse> reportEnroll();

	Optional<User> findByUsername(String username);
	Optional<Course> findByCode(String code);
}
