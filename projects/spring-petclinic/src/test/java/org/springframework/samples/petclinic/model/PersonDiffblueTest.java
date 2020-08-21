package org.springframework.samples.petclinic.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

public class PersonDiffblueTest {
  @Test
  public void testSetFirstName() {
    // Arrange
    Person person = new Person();

    // Act
    person.setFirstName("Jane");

    // Assert
    assertEquals("Jane", person.getFirstName());
  }

  @Test
  public void testSetLastName() {
    // Arrange
    Person person = new Person();

    // Act
    person.setLastName("Doe");

    // Assert
    assertEquals("Doe", person.getLastName());
  }
}

