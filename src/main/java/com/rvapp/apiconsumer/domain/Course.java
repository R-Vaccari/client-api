package com.rvapp.apiconsumer.domain;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;


public class Course implements Serializable {
    private static final long serialVersionUID = 1L;

    private String id;
    private String courseName;
    private String courseType;

    private Set<ClassGroup> classes = new HashSet<>();

    public Course() {
    }

    public Course(String id, String courseName, String courseType) {
        this.id = id;
        this.courseName = courseName;
        this.courseType = courseType;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCourseName() {
        return courseName;
    }

    public String getType() {
        return courseType;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public void setType(String courseType) {
        this.courseType = courseType;
    }

    public Set<ClassGroup> getClasses() {
        return classes;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Course other = (Course) obj;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "Course{" +
                "id='" + id + '\'' +
                ", courseName='" + courseName + '\'' +
                ", courseType='" + courseType + '\'' +
                ", classes=" + classes +
                '}';
    }
}
