package pl.put.poznan.hospitalmanagement.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalTime;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Shift {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "employee_id")
    private Employee employee;
    private LocalDate startDateShift;
    private LocalDate endDateShift;
    private LocalTime startTimeShift;
    private LocalTime endTimeShift;

    @CreationTimestamp
    private Timestamp timestamp;

    @Override
    public String toString() {
        return "Shift{" +
                "id=" + id +
                ", employee=" + employee +
                ", startDateShift=" + startDateShift +
                ", endDateShift=" + endDateShift +
                ", startTimeShift=" + startTimeShift +
                ", endTimeShift=" + endTimeShift +
                ", timestamp=" + timestamp +
                '}';
    }
}
