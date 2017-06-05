package com.sibat.domain.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigInteger;

/**
 * Created by tgw61 on 2017/5/16.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class LikeMap {
    private String key;
    private BigInteger value;
}
