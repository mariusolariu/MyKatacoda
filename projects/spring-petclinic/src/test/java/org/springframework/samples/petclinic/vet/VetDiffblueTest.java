package org.springframework.samples.petclinic.vet;

import static org.junit.jupiter.api.Assertions.assertEquals;
import java.util.HashSet;
import org.junit.jupiter.api.Test;

public class VetDiffblueTest {
  @Test
  public void testGetSpecialtiesInternal() {
    // Arrange
    Vet vet = new Vet();

    // Act and Assert
    assertEquals(0, vet.getSpecialtiesInternal().size());
    assertEquals(0, vet.getNrOfSpecialties());
  }

  @Test
  public void testSetSpecialtiesInternal() {
    // Arrange
    Vet vet = new Vet();

    // Act
    vet.setSpecialtiesInternal(new HashSet<Specialty>());

    // Assert
    assertEquals(0, vet.getNrOfSpecialties());
  }

  @Test
  public void testGetSpecialties() {
    // Arrange
    Vet vet = new Vet();

    // Act and Assert
    assertEquals(0, vet.getSpecialties().size());
    assertEquals(0, vet.getNrOfSpecialties());
  }

  @Test
  public void testGetNrOfSpecialties() {
    // Arrange
    Vet vet = new Vet();

    // Act and Assert
    assertEquals(0, vet.getNrOfSpecialties());
    assertEquals(0, vet.getNrOfSpecialties());
  }

  @Test
  public void testAddSpecialty() {
    // Arrange
    Vet vet = new Vet();

    // Act
    vet.addSpecialty(new Specialty());

    // Assert
    assertEquals(1, vet.getNrOfSpecialties());
  }

  @Test
  public void testAddSpecialty2() {
    // Arrange
    Vet vet = new Vet();
    vet.setSpecialtiesInternal(new HashSet<Specialty>());

    // Act
    vet.addSpecialty(new Specialty());

    // Assert
    assertEquals(1, vet.getNrOfSpecialties());
  }
}

