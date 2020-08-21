package org.springframework.samples.petclinic.visit;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;
import java.time.LocalDate;
import org.junit.jupiter.api.Test;

public class VisitDiffblueTest {
  @Test
  public void testConstructor() {
    // Arrange and Act
    Visit actualVisit = new Visit();

    // Assert
    assertNull(actualVisit.getPetId());
    assertNull(actualVisit.getDescription());
    assertNull(actualVisit.getId());
    assertTrue(actualVisit.isNew());
  }

  @Test
  public void testSetDate() {
    // Arrange
    LocalDate ofEpochDayResult = LocalDate.ofEpochDay(1L);
    Visit visit = new Visit();

    // Act
    visit.setDate(ofEpochDayResult);

    // Assert
    assertSame(ofEpochDayResult, visit.getDate());
  }

  @Test
  public void testSetDescription() {
    // Arrange
    Visit visit = new Visit();

    // Act
    visit.setDescription("The characteristics of someone or something");

    // Assert
    assertEquals("The characteristics of someone or something", visit.getDescription());
  }

  @Test
  public void testSetPetId() {
    // Arrange
    Visit visit = new Visit();

    // Act
    visit.setPetId(123);

    // Assert
    assertEquals(123, visit.getPetId().intValue());
  }
}

