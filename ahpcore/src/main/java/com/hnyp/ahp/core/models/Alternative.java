package com.hnyp.ahp.core.models;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue(value = "alternative")
public class Alternative extends ComparableItem {

}
