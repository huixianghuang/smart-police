package com.sibat.domain.origin;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

/**
 * Created by tgw61 on 2017/5/26.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "view_police_data")
public class ViewPoliceData {
    @Id
    private String userCode;
    private String deptName;
    private String deptCode;
    private String mobilePhone;
    private String name;
    @Transient
    private String dept_id;
}
