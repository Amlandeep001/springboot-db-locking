package com.db.locking.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Version;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

/**
 * https://www.baeldung.com/jpa-optimistic-locking
 */

@Entity
@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Seat
{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	Long id;

	String movieName;

	boolean booked;

	@Version
	int version;
}
