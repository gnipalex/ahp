package com.hnyp.ahp.web.v2.models;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("criteria")  
public class Criteria extends ComparableItem {
   
}