package model;

import lombok.Data;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import javax.persistence.*;
import java.util.List;

@RequiredArgsConstructor
@Data
@Entity(name = "employee")
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NonNull
    private String name;

    @NonNull
    @Column(name = "hourly")
    private double moneyPerHour;

    @NonNull
    private String position;

    @NonNull
    @Column(name = "on_vacation")
    private boolean onHolidays;

    @NonNull
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "currency_name")
    private Currency paymentCurrency;

    @NonNull
    @OneToOne(cascade = CascadeType.ALL)
    @PrimaryKeyJoinColumn(name = "time_sheet")
    private TimeSheet timeSheetList;

    public Employee() {
    }
}
