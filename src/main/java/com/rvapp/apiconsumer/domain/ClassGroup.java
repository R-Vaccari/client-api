package com.rvapp.apiconsumer.domain;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

public class ClassGroup implements Serializable {
    private static final long serialVersionUID = 1L;

    private String id;
    private String className;
    private String classLevel;
    private Teacher teacher;

    private Set<Student> students = new HashSet<>();

    public ClassGroup() {
    }

    public ClassGroup(String id, String className, String classLevel, Teacher teacher) {
        super();
        this.id = id;
        this.className = className;
        this.classLevel = classLevel;
        this.teacher = teacher;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Teacher getTeacher() {
        return teacher;
    }

    public void setTeacher(Teacher teacher) {
        this.teacher = teacher;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getClassLevel() {
        return classLevel;
    }

    public void setClassLevel(String classLevel) {
        this.classLevel = classLevel;
    }

    public Set<Student> getStudents() {
        return students;
    }

    public String getClassName() {
        return className;
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
        ClassGroup other = (ClassGroup) obj;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        return true;
    }
}