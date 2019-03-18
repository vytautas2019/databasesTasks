package model;

import lombok.Data;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@RequiredArgsConstructor
@Data
@Entity(name="salary")
public class Salary {

    @Id
    @Column(name="person_id")
    private long id;

    @NonNull
    private int pay;

    private Salary() {
    }
}
