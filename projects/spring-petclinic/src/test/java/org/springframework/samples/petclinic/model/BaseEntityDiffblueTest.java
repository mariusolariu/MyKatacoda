package org.springframework.samples.petclinic.model;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;

public class BaseEntityDiffblueTest {
  @Test
  public void testSetId() {
    // Arrange
    BaseEntity baseEntity = new BaseEntity();

    // Act
    baseEntity.setId(1);

    // Assert
    assertFalse(baseEntity.isNew());
  }

  @Test
  public void testIsNew() {
    // Arrange, Act and Assert
    assertTrue((new BaseEntity()).isNew());
  }

  @Test
  public void testIsNew2() {
    // Arrange
    BaseEntity baseEntity = new BaseEntity();
    baseEntity.setId(1);

    // Act and Assert
    assertFalse(baseEntity.isNew());
  }
}

