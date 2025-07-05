import java.util.*;

// Course class
class Course {
    private String courseId;
    private String courseName;
    private String status;
    private int duration;

    public Course(String courseId, String courseName, String status, int duration) {
        this.courseId = courseId;
        this.courseName = courseName;
        this.status = status;
        this.duration = duration;
    }

    public String getCourseId() {
        return courseId;
    }

    public void setCourseId(String courseId) {
        this.courseId = courseId;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }
}

// Learner class
class Learner {
    private String learnerId;
    private String learnerName;
    private List<Course> courses;

    public Learner(String learnerId, String learnerName, List<Course> courses) {
        this.learnerId = learnerId;
        this.learnerName = learnerName;
        this.courses = courses;
    }

    public String getLearnerId() {
        return learnerId;
    }

    public void setLearnerId(String learnerId) {
        this.learnerId = learnerId;
    }

    public String getLearnerName() {
        return learnerName;
    }

    public void setLearnerName(String learnerName) {
        this.learnerName = learnerName;
    }

    public List<Course> getCourses() {
        return courses;
    }

    public void setCourses(List<Course> courses) {
        this.courses = courses;
    }
}

// LearnerSolution class
class LearnerSolution {

    // 1. Find Ongoing Course Count
    public static void FindOngoingCourseCount(List<Learner> learners, String learnerId) {
        boolean found = false;
        int count = 0;

        for (Learner l : learners) {
            if (l.getLearnerId().equalsIgnoreCase(learnerId)) {
                found = true;
                for (Course c : l.getCourses()) {
                    if (c.getStatus().equalsIgnoreCase("Inprogress")) {
                        count++;
                    }
                }
                break;
            }
        }

        if (!found || count == 0) {
            System.out.println("No ongoing courses");
        } else {
            System.out.println(count);
        }
    }

    // 2. Find Total Learning Hours
    public static void FindTotalLearningHours(List<Learner> learners, String learnerId) {
        boolean found = false;
        int total = 0;

        for (Learner l : learners) {
            if (l.getLearnerId().equalsIgnoreCase(learnerId)) {
                found = true;
                for (Course c : l.getCourses()) {
                    if (c.getStatus().equalsIgnoreCase("Completed")) {
                        total += c.getDuration();
                    }
                }
                break;
            }
        }

        if (!found || total == 0) {
            System.out.println("No completed courses available");
        } else {
            System.out.println(total);
        }
    }
}

// Main class
public class Solution {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        List<Learner> learners = new ArrayList<>();

        int numLearners = Integer.parseInt(sc.nextLine());

        for (int i = 0; i < numLearners; i++) {
            String learnerId = sc.nextLine();
            String learnerName = sc.nextLine();
            int numCourses = Integer.parseInt(sc.nextLine());

            List<Course> courses = new ArrayList<>();
            for (int j = 0; j < numCourses; j++) {
                String courseId = sc.nextLine();
                String courseName = sc.nextLine();
                String status = sc.nextLine();
                int duration = Integer.parseInt(sc.nextLine());

                courses.add(new Course(courseId, courseName, status, duration));
            }

            learners.add(new Learner(learnerId, learnerName, courses));
        }

        String searchId = sc.nextLine();  // Used for both method calls

        LearnerSolution.FindOngoingCourseCount(learners, searchId);
        LearnerSolution.FindTotalLearningHours(learners, searchId);
    }
}
