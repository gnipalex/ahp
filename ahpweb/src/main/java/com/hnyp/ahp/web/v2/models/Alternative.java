package com.hnyp.ahp.web.v2.models;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue(value = "alternative")
public class Alternative extends ComparableItem {

}
