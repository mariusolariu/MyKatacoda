package org.springframework.samples.petclinic.owner;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;
import java.time.LocalDate;
import java.util.Set;
import org.junit.jupiter.api.Test;
import org.springframework.samples.petclinic.visit.Visit;

public class PetDiffblueTest {
  @Test
  public void testSetBirthDate() {
    // Arrange
    LocalDate ofEpochDayResult = LocalDate.ofEpochDay(1L);
    Pet pet = new Pet();

    // Act
    pet.setBirthDate(ofEpochDayResult);

    // Assert
    assertSame(ofEpochDayResult, pet.getBirthDate());
  }

  @Test
  public void testSetType() {
    // Arrange
    Pet pet = new Pet();
    PetType petType = new PetType();

    // Act
    pet.setType(petType);

    // Assert
    assertSame(petType, pet.getType());
  }

  @Test
  public void testSetOwner() {
    // Arrange
    Pet pet = new Pet();
    Owner owner = new Owner();

    // Act
    pet.setOwner(owner);

    // Assert
    assertSame(owner, pet.getOwner());
  }

  @Test
  public void testGetVisitsInternal() {
    // Arrange, Act and Assert
    assertEquals(0, (new Pet()).getVisitsInternal().size());
  }

  @Test
  public void testGetVisits() {
    // Arrange, Act and Assert
    assertEquals(0, (new Pet()).getVisits().size());
  }

  @Test
  public void testAddVisit() {
    // Arrange
    Pet pet = new Pet();
    Visit visit = new Visit();

    // Act
    pet.addVisit(visit);

    // Assert
    assertNull(visit.getPetId());
  }

  @Test
  public void testConstructor() {
    // Arrange and Act
    Pet actualPet = new Pet();

    // Assert
    assertNull(actualPet.getName());
    Set<Visit> visitsInternal = actualPet.getVisitsInternal();
    assertTrue(visitsInternal instanceof java.util.LinkedHashSet);
    assertNull(actualPet.getBirthDate());
    assertEquals(0, visitsInternal.size());
    assertNull(actualPet.getType());
    assertNull(actualPet.getOwner());
    assertNull(actualPet.getId());
    assertTrue(actualPet.isNew());
    assertNull(actualPet.toString());
  }
}

