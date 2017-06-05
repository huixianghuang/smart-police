package com.sibat.domain.other;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Created by tgw61 on 2017/5/24.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "police")
public class Police {
    @Id
    @GeneratedValue
    private int id;
    private String policeId;
    private String policeName;
}
