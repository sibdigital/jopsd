package ru.sibdigital.jopsd.model.opsd;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;

@Table(name = "target_risks")
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
public class TargetRisk {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @ManyToOne(optional = false)
    @JoinColumn(name = "target_id", nullable = false)
    private Target target;

    @ManyToOne(optional = false)
    @JoinColumn(name = "risk_id", nullable = false)
    private Risk risk;

    @Basic
    @Column(name = "is_solved")
    private Boolean isSolved;

    @Basic
    @Column(name = "solution_date")
    private LocalDate solutionDate;


    public Boolean getSolved() {
        return isSolved;
    }

    public void setSolved(Boolean solved) {
        isSolved = solved;
    }


    public LocalDate getSolutionDate() {
        return solutionDate;
    }

    public void setSolutionDate(LocalDate solutionDate) {
        this.solutionDate = solutionDate;
    }


    public Risk getRisk() {
        return risk;
    }

    public void setRisk(Risk risk) {
        this.risk = risk;
    }

    public Target getTarget() {
        return target;
    }

    public void setTarget(Target target) {
        this.target = target;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}