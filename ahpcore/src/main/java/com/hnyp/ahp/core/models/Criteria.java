package com.hnyp.ahp.core.models;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("criteria")  
public class Criteria extends ComparableItem {
   
}