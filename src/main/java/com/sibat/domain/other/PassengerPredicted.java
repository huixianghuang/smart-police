package com.sibat.domain.other;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

/**
 * Created by tgw61 on 2017/5/17.
 */
@Entity
@Table
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PassengerPredicted {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;
    private String stationId;
    private String time;
    private Integer stationIn;
    private Integer stationOut;
}
