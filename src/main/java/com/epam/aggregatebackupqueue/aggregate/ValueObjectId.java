package com.epam.aggregatebackupqueue.aggregate;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.Embeddable;
import java.io.Serializable;

@Getter
@ToString
@Embeddable
@NoArgsConstructor
@AllArgsConstructor
public class ValueObjectId implements Serializable {
    private String value;
}
