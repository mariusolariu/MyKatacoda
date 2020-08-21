package org.springframework.samples.petclinic.vet;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

public class VetsDiffblueTest {
  @Test
  public void testGetVetList() {
    // Arrange, Act and Assert
    assertEquals(0, (new Vets()).getVetList().size());
  }
}

