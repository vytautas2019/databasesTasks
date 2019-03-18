package model;

import lombok.Data;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import javax.persistence.*;

@RequiredArgsConstructor
@Data
@Entity(name = "currency")
public class Currency {

    @NonNull
    @Id
    @Column(name = "cur_name")
    private String currencyName;

    @NonNull
    @Column(name = "cur_rate")
    private double rate;

    public Currency() {
    }
}
