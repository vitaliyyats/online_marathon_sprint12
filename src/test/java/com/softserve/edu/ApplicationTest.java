package com.softserve.edu;

import java.util.Arrays;
import java.util.List;

import com.softserve.edu.dto.AverageScore;
import com.softserve.edu.dto.MentorStudent;
import com.softserve.edu.dto.SprintScore;
import com.softserve.edu.dto.StudentScore;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.softserve.edu.service.DataService;
import com.softserve.edu.service.MarathonService;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class ApplicationTest {

    @Autowired
    private final MarathonService marathonService;

    @Autowired
    private DataService dataService;

    @Autowired
    public ApplicationTest(MarathonService marathonService) {
        this.marathonService = marathonService;
    }

    @BeforeAll
    public void setup() {
        fillDataService();
    }

    private void fillDataService() {
        dataService.addStudent("Vitaliy");
        dataService.addStudent("Taras");
        dataService.addSprint("Sprint1");
        dataService.addSprint("Sprint2");
        dataService.addMentor("Yaroslav");
        dataService.addSolution("Vitaliy", "Sprint1", 80);
        dataService.addSolution("Taras", "Sprint1", 100);
        dataService.addSolution("Vitaliy", "Sprint2", 100);
        dataService.addSolution("Taras", "Sprint2", 90);
        dataService.addCommunication("Taras", "Yaroslav");
        dataService.addCommunication("Vitaliy", "Yaroslav");
    }

    @Test
    public void checkGetStudents() {
        List<String> expected = Arrays.asList("Vitaliy", "Taras");
        List<String> actual = marathonService.getStudents();
        Assertions.assertEquals(expected, actual, "checkGetStudents()");
    }

    @Test
    public void checkGetMentors() {
        List<String> expected = Arrays.asList("Yaroslav");
        List<String> actual = marathonService.getMentors();
        Assertions.assertEquals(expected, actual, "checkGetMentors()");
    }

    @Test
    public void checkGetStudentResult() {
        StudentScore expected = new StudentScore("Vitaliy",
                Arrays.asList(new SprintScore("Sprint1", 80),
                        new SprintScore("Sprint2", 100)));
        StudentScore actual = marathonService.studentResult("Vitaliy");
        Assertions.assertEquals(expected, actual, "checkGetStudentResult()");
    }

    @Test
    public void checkAllStudentsResult() {
        StudentScore expected1 = new StudentScore("Vitaliy",
                Arrays.asList(new SprintScore("Sprint1", 80),
                        new SprintScore("Sprint2", 100)));
        StudentScore expected2 = new StudentScore("Taras",
                Arrays.asList(new SprintScore("Sprint1", 100),
                        new SprintScore("Sprint2", 90)));
        List<StudentScore> expected = Arrays.asList(expected1, expected2);
        List<StudentScore> actual = marathonService.allStudentsResult();
        Assertions.assertEquals(expected, actual, "allStudentsResult()");
    }

    @Test
    public void checkStudentAverage() {
        AverageScore expected = new AverageScore("Vitaliy", 90.0);
        AverageScore actual = marathonService.studentAverage("Vitaliy");
        Assertions.assertEquals(expected, actual, "checkStudentAverage()");
    }

    @Test
    public void checkAllStudentsAverage() {
        AverageScore expected1 = new AverageScore("Vitaliy", 90.0);
        AverageScore expected2 = new AverageScore("Taras", 95.0);
        List<AverageScore> expected = Arrays.asList(expected1, expected2);
        List<AverageScore> actual = marathonService.allStudentsAverage();
        Assertions.assertEquals(expected, actual, "checkAllStudentsAverage()");
    }

    @Test
    public void checkMentorStudents() {
        MentorStudent expected = new MentorStudent("Yaroslav",
                Arrays.asList("Taras", "Vitaliy"));
        MentorStudent actual = marathonService.mentorStudents("Yaroslav");
        Assertions.assertEquals(expected, actual, "checkMentorStudents()");
    }

}
