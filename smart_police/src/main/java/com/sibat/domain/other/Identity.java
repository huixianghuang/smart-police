package com.sibat.domain.other;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Created by tgw61 on 2017/5/31.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "identity")
public class Identity {
    @Id
    private String id;
    private String place;
}
