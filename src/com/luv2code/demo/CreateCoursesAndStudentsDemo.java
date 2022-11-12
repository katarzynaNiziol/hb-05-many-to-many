package com.luv2code.demo;

import com.luv2code.hibernate.demo.entity.*;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class CreateCoursesAndStudentsDemo {
    public static void main(String[] args) {
        SessionFactory factory = new Configuration().configure("hibernate.cfg.xml")
                .addAnnotatedClass(Instructor.class)
                .addAnnotatedClass(InstructorDetail.class)
                .addAnnotatedClass(Course.class)
                .addAnnotatedClass(Review.class)
                .addAnnotatedClass(Student.class)
                .buildSessionFactory();

        Session session = factory.getCurrentSession();

        try {
            //start a transaction
            session.beginTransaction();

            //create a course
            Course tempCourse = new Course("Java course");

            //save the course
            System.out.println("\nSaving the course");
            session.save(tempCourse);
            System.out.println("Saved the course: " + tempCourse);

            //create the students
            Student tempStudent10 = new Student("Mary", "Marunia", "mary@gmail.com");

            //add to the course
            tempCourse.addStudent(tempStudent10);


            //save students
            System.out.println("\nSaving students....");
            session.save(tempStudent10);
            System.out.println("Saved students:  " + tempCourse.getStudents());


            //commit transaction
            session.getTransaction().commit();
            System.out.println("Done!");

        } finally {
            session.close();
            factory.close();

        }
    }
}
