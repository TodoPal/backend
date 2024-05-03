package com.todopal.backend.entities;

import lombok.Getter;
import lombok.Setter;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;

public abstract class BaseEntity {
    @Id
    @Getter
    @Setter
    private ObjectId id;
}
