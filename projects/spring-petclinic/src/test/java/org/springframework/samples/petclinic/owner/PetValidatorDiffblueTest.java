package org.springframework.samples.petclinic.owner;

import static org.junit.jupiter.api.Assertions.assertFalse;
import org.junit.jupiter.api.Test;

public class PetValidatorDiffblueTest {
  @Test
  public void testSupports() {
    // Arrange
    Class<?> clazz = Object.class;

    // Act and Assert
    assertFalse((new PetValidator()).supports(clazz));
  }
}

