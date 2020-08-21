package org.springframework.samples.petclinic.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import org.junit.jupiter.api.Test;

public class NamedEntityDiffblueTest {
  @Test
  public void testSetName() {
    // Arrange
    NamedEntity namedEntity = new NamedEntity();

    // Act
    namedEntity.setName("Bella");

    // Assert
    assertEquals("Bella", namedEntity.getName());
  }

  @Test
  public void testToString() {
    // Arrange, Act and Assert
    assertNull((new NamedEntity()).toString());
  }
}

