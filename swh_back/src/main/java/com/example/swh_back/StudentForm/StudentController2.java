package com.example.swh_back.StudentForm;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.math.BigDecimal;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/api/studentssave")
public class StudentController2 {

    @Autowired
    private StudentDao2 studentDao2;

    @PostMapping
public void insertData2(@RequestBody Map<String, Object> student) {
    String salutation = (String) student.get("salutation");
    String fname = (String) student.get("fname");
    String mname = (String) student.get("mname");
    String lname= (String) student.get("lname");
    String email = (String) student.get("email");
    String phone_number = (String) student.get("phone_number");
    java.sql.Date dob = null;
    try {
        dob = new java.sql.Date(
            new SimpleDateFormat("MM-dd-yyyy").parse((String) student.get("dob")).getTime());
    } catch (ParseException e) {
        e.printStackTrace(); // handle the exception as needed
    }
    String street = (String) student.get("street");
    String city = (String) student.get("city");
    String state = (String) student.get("state");
    String zipCode = (String) student.get("zipCode");
    String currentInstitution = (String) student.get("currentInstitution");
    String degreeProgram = (String) student.get("degreeProgram");
    String major = (String) student.get("major");
    String yearOfGraduation = (String) student.get("yearOfGraduation");
    //String gpa = (String) student.get("gpa");
    Double gpa = (Double) student.get("gpa");

    String companyName = (String) student.get("companyName");
    String role = (String) student.get("role");
    String duration = (String) student.get("duration");
    String title = (String) student.get("title");
    String description = (String) student.get("description");
    String duration2 = (String) student.get("duration2");
    String contributions = (String) student.get("contributions");
    String technologies = (String) student.get("technologies");

    List<String> programmingLanguages = (List<String>) student.get("programmingLanguages");
    List<String> toolsFrameworks = (List<String>) student.get("toolsFrameworks");
    List<String> softSkills = (List<String>) student.get("softSkills");

    String clubs = (String) student.get("clubs");
    String volunteerWork = (String) student.get("volunteerWork");
    String hobbies = (String) student.get("hobbies");
    String motivation = (String) student.get("motivation");

    // Pass the lists directly without converting to strings
    studentDao2.insertPersonalInfo(email, salutation, fname, mname, lname, phone_number, dob);
    studentDao2.insertAddress(email, street, city, state, zipCode);
    studentDao2.insertAcademicInfo(email, currentInstitution, degreeProgram, major, yearOfGraduation, gpa);
    studentDao2.insertWorkExperience(email, companyName, role, duration, title, description, duration2, contributions);
    studentDao2.insertSkills(email, technologies, toolsFrameworks, programmingLanguages, softSkills);
    studentDao2.insertExtra(email, clubs, volunteerWork, hobbies, motivation);
}

    @PostMapping("/resume")
    public void uploadResume(@RequestParam("email") String email,
            @RequestParam("resume") MultipartFile resume,
            @RequestParam("check1") boolean check1,
            @RequestParam("check2") boolean check2,
            @RequestParam("check3") boolean check3) {
        try {
            studentDao2.insertResume(email, resume, check1, check2, check3);
        } catch (Exception e) {
            e.printStackTrace(); // handle the exception as needed
        }
    }

    @GetMapping
    public List<Map<String, Object>> getStudents() {
        return studentDao2.getStudents();
    }
    @Autowired
    private JdbcTemplate jdbcTemplate;
    @GetMapping("/studenttt/{email}")
public Map<String, Object> getStudentRegistrationData(@PathVariable String email) {
    String query = "SELECT salutation,  fname, mname,  lname, email, dob, phone_number FROM student_reg WHERE email = ?";
    return jdbcTemplate.queryForMap(query, email);
}

}

/*
 * package com.example.app2_back;
 * 
 * import org.springframework.web.bind.annotation.CrossOrigin;
 * import org.springframework.beans.factory.annotation.Autowired;
 * import org.springframework.web.bind.annotation.*;
 * import org.springframework.web.multipart.MultipartFile;
 * 
 * import java.text.ParseException;
 * import java.text.SimpleDateFormat;
 * import java.util.List;
 * import java.util.Map;
 * 
 * @RestController
 * 
 * @CrossOrigin(origins = "http://localhost:3000")
 * 
 * @RequestMapping("/api/students")
 * public class StudentController {
 * 
 * @Autowired
 * private StudentDao studentDao;
 * 
 * @PostMapping
 * public void insertData(
 * 
 * @RequestParam("salutation") String salutation,
 * 
 * @RequestParam("firstName") String firstName,
 * 
 * @RequestParam("middleName") String middleName,
 * 
 * @RequestParam("lastName") String lastName,
 * 
 * @RequestParam("email") String email,
 * 
 * @RequestParam("phoneNumber") String phoneNumber,
 * 
 * @RequestParam("dateOfBirth") String dateOfBirthStr,
 * 
 * @RequestParam("street") String street,
 * 
 * @RequestParam("city") String city,
 * 
 * @RequestParam("state") String state,
 * 
 * @RequestParam("zipCode") String zipCode,
 * 
 * @RequestParam("currentInstitution") String currentInstitution,
 * 
 * @RequestParam("degreeProgram") String degreeProgram,
 * 
 * @RequestParam("major") String major,
 * 
 * @RequestParam("yearOfGraduation") String yearOfGraduation,
 * 
 * @RequestParam("gpa") String gpa,
 * 
 * @RequestParam("companyName") String companyName,
 * 
 * @RequestParam("role") String role,
 * 
 * @RequestParam("duration") String duration,
 * 
 * @RequestParam("title") String title,
 * 
 * @RequestParam("description") String description,
 * 
 * @RequestParam("duration2") String duration2,
 * 
 * @RequestParam("contributions") String contributions,
 * 
 * @RequestParam("technologies") String technologies,
 * 
 * @RequestParam("programmingLanguages") String programmingLanguages,
 * 
 * @RequestParam("toolsFrameworks") String toolsFrameworks,
 * 
 * @RequestParam("softSkills") String softSkills,
 * 
 * @RequestParam("clubs") String clubs,
 * 
 * @RequestParam("volunteerWork") String volunteerWork,
 * 
 * @RequestParam("hobbies") String hobbies,
 * 
 * @RequestParam("motivation") String motivation,
 * 
 * @RequestParam(value = "resumePdf", required = false) MultipartFile resumePdf
 * ) {
 * java.sql.Date dateOfBirth = null;
 * try {
 * dateOfBirth = new java.sql.Date(
 * new SimpleDateFormat("dd-MM-yyyy").parse(dateOfBirthStr).getTime()
 * );
 * } catch (ParseException e) {
 * e.printStackTrace(); // handle the exception as needed
 * }
 * 
 * studentDao.insertPersonalInfo(email, salutation, firstName, middleName,
 * lastName, phoneNumber, dateOfBirth);
 * studentDao.insertAddress(email, street, city, state, zipCode);
 * studentDao.insertAcademicInfo(email, currentInstitution, degreeProgram,
 * major, yearOfGraduation, gpa);
 * studentDao.insertWorkExperience(email, companyName, role, duration, title,
 * description, duration2, contributions);
 * studentDao.insertSkills(email, technologies, toolsFrameworks,
 * programmingLanguages, softSkills);
 * studentDao.insertExtra(email, clubs, volunteerWork, hobbies, motivation);
 * 
 * if (resumePdf != null) {
 * try {
 * studentDao.insertResume(email, resumePdf, false, false, false); // Modify as
 * needed for checkboxes
 * } catch (Exception e) {
 * e.printStackTrace(); // handle the exception as needed
 * }
 * }
 * }
 * 
 * @PostMapping("/resume")
 * public void uploadResume(
 * 
 * @RequestParam("email") String email,
 * 
 * @RequestParam("resume") MultipartFile resume,
 * 
 * @RequestParam("check1") boolean check1,
 * 
 * @RequestParam("check2") boolean check2,
 * 
 * @RequestParam("check3") boolean check3
 * ) {
 * try {
 * studentDao.insertResume(email, resume, check1, check2, check3);
 * } catch (Exception e) {
 * e.printStackTrace(); // handle the exception as needed
 * }
 * }
 * 
 * @GetMapping
 * public List<Map<String, Object>> getStudents() {
 * return studentDao.getStudents();
 * }
 * }
 */