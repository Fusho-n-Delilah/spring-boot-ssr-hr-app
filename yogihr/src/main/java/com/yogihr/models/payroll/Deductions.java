package com.yogihr.models.payroll;

import jakarta.persistence.*;

@Entity
@Table(name = "deductions")
public class Deductions {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "emp_no")
    private int id;

    @Column(name = "ppo")
    private double health;

    @Column(name = "dppo")
    private double dental;

    @Column(name = "vision")
    private double vision;

    public Deductions() {
    }

    public Deductions(double health, double dental, double vision) {
        this.health = health;
        this.dental = dental;
        this.vision = vision;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getHealth() {
        return health;
    }

    public void setHealth(double health) {
        this.health = health;
    }

    public double getDental() {
        return dental;
    }

    public void setDental(double dental) {
        this.dental = dental;
    }

    public double getVision() {
        return vision;
    }

    public void setVision(double vision) {
        this.vision = vision;
    }
}
