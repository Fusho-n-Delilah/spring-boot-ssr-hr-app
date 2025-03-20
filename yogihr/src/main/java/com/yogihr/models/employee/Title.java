package com.yogihr.models.employee;

import jakarta.persistence.*;
import org.springframework.context.annotation.Scope;

import java.time.LocalDate;
import java.util.Objects;

@Entity
@Scope("prototype")
@Table(name = "title")
public class Title {

    @Id
    @Column(name = "emp_no")
    private int id;

    @Id
    @Column(name = "title")
    private String title;

    @Id
    @Column(name = "from_date")
    private LocalDate fromDate;

    @Column(name = "to_date")
    private LocalDate toDate;



    public Title() {
    }

    public Title(int id, String title, LocalDate fromDate, LocalDate toDate) {
        this.id = id;
        this.title = title;
        this.fromDate = fromDate;
        this.toDate = toDate;
    }

    public Title(int id, String title) {
        this.id = id;
        this.title = title;
        this.fromDate = LocalDate.now();
        this.toDate = LocalDate.of(9999, 1, 1);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public LocalDate getFromDate() {
        return fromDate;
    }

    public void setFromDate(LocalDate fromDate) {
        this.fromDate = fromDate;
    }

    public LocalDate getToDate() {
        return toDate;
    }

    public void setToDate(LocalDate toDate) {
        this.toDate = toDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Title title1 = (Title) o;
        return id == title1.id && Objects.equals(title, title1.title) && Objects.equals(fromDate, title1.fromDate) && Objects.equals(toDate, title1.toDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title, fromDate, toDate);
    }
}
