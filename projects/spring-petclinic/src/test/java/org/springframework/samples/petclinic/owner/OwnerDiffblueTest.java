package org.springframework.samples.petclinic.owner;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import java.util.ArrayList;
import java.util.HashSet;
import org.junit.jupiter.api.Test;
import org.springframework.samples.petclinic.visit.Visit;

public class OwnerDiffblueTest {
  @Test
  public void testSetAddress() {
    // Arrange
    Owner owner = new Owner();

    // Act
    owner.setAddress("42 Main St");

    // Assert
    assertEquals("42 Main St", owner.getAddress());
  }

  @Test
  public void testSetCity() {
    // Arrange
    Owner owner = new Owner();

    // Act
    owner.setCity("Oxford");

    // Assert
    assertEquals("Oxford", owner.getCity());
  }

  @Test
  public void testSetTelephone() {
    // Arrange
    Owner owner = new Owner();

    // Act
    owner.setTelephone("4105551212");

    // Assert
    assertEquals("4105551212", owner.getTelephone());
  }

  @Test
  public void testGetPetsInternal() {
    // Arrange, Act and Assert
    assertEquals(0, (new Owner()).getPetsInternal().size());
  }

  @Test
  public void testSetPetsInternal() {
    // Arrange
    Owner owner = new Owner();
    HashSet<Pet> petSet = new HashSet<Pet>();

    // Act
    owner.setPetsInternal(petSet);

    // Assert
    assertSame(petSet, owner.getPetsInternal());
  }

  @Test
  public void testGetPets() {
    // Arrange, Act and Assert
    assertEquals(0, (new Owner()).getPets().size());
  }

  @Test
  public void testAddPet() {
    // Arrange
    Owner owner = new Owner();
    Pet pet = new Pet();

    // Act
    owner.addPet(pet);

    // Assert
    assertSame(owner, pet.getOwner());
  }

  @Test
  public void testGetPet() {
    // Arrange, Act and Assert
    assertNull((new Owner()).getPet("Bella"));
    assertNull((new Owner()).getPet("Bella", true));
  }

  @Test
  public void testGetPet2() {
    // Arrange
    Owner owner = new Owner();
    owner.setPetsInternal(new HashSet<Pet>());

    // Act and Assert
    assertNull(owner.getPet("Bella", true));
  }

  @Test
  public void testGetPet3() {
    // Arrange
    Pet pet = new Pet();
    pet.setBirthDate(null);
    Owner owner = new Owner();
    owner.setLastName("Doe");
    owner.setId(1);
    owner.setCity("Oxford");
    owner.setPetsInternal(new HashSet<Pet>());
    owner.setAddress("42 Main St");
    owner.setFirstName("Jane");
    owner.setTelephone("4105551212");
    pet.setOwner(owner);
    pet.setVisitsInternal(new ArrayList<Visit>());
    pet.setId(1);
    pet.setName("Bella");
    PetType petType = new PetType();
    petType.setId(1);
    petType.setName("Dog");
    pet.setType(petType);
    HashSet<Pet> petSet = new HashSet<Pet>();
    petSet.add(pet);
    Owner owner1 = new Owner();
    owner1.setPetsInternal(petSet);

    // Act and Assert
    assertSame(pet, owner1.getPet("Bella", true));
  }

  @Test
  public void testGetPet4() {
    // Arrange
    Pet pet = new Pet();
    pet.setBirthDate(null);
    Owner owner = new Owner();
    owner.setLastName("Doe");
    owner.setId(1);
    owner.setCity("Oxford");
    owner.setPetsInternal(new HashSet<Pet>());
    owner.setAddress("42 Main St");
    owner.setFirstName("Jane");
    owner.setTelephone("4105551212");
    pet.setOwner(owner);
    pet.setVisitsInternal(new ArrayList<Visit>());
    pet.setId(null);
    pet.setName("Bella");
    PetType petType = new PetType();
    petType.setId(1);
    petType.setName("Dog");
    pet.setType(petType);
    HashSet<Pet> petSet = new HashSet<Pet>();
    petSet.add(pet);
    Owner owner1 = new Owner();
    owner1.setPetsInternal(petSet);

    // Act and Assert
    assertNull(owner1.getPet("Bella", true));
  }

  @Test
  public void testGetPet5() {
    // Arrange
    Pet pet = new Pet();
    pet.setBirthDate(null);
    Owner owner = new Owner();
    owner.setLastName("Doe");
    owner.setId(1);
    owner.setCity("Oxford");
    owner.setPetsInternal(new HashSet<Pet>());
    owner.setAddress("42 Main St");
    owner.setFirstName("Jane");
    owner.setTelephone("4105551212");
    pet.setOwner(owner);
    pet.setVisitsInternal(new ArrayList<Visit>());
    pet.setId(1);
    pet.setName("name");
    PetType petType = new PetType();
    petType.setId(1);
    petType.setName("Dog");
    pet.setType(petType);
    HashSet<Pet> petSet = new HashSet<Pet>();
    petSet.add(pet);
    Owner owner1 = new Owner();
    owner1.setPetsInternal(petSet);

    // Act and Assert
    assertNull(owner1.getPet("Bella", true));
  }

  @Test
  public void testGetPet6() {
    // Arrange
    Pet pet = new Pet();
    pet.setBirthDate(null);
    Owner owner = new Owner();
    owner.setLastName("Doe");
    owner.setId(1);
    owner.setCity("Oxford");
    owner.setPetsInternal(new HashSet<Pet>());
    owner.setAddress("42 Main St");
    owner.setFirstName("Jane");
    owner.setTelephone("4105551212");
    pet.setOwner(owner);
    pet.setVisitsInternal(new ArrayList<Visit>());
    pet.setId(1);
    pet.setName("Bella");
    PetType petType = new PetType();
    petType.setId(1);
    petType.setName("Dog");
    pet.setType(petType);
    HashSet<Pet> petSet = new HashSet<Pet>();
    petSet.add(pet);
    Owner owner1 = new Owner();
    owner1.setPetsInternal(petSet);

    // Act and Assert
    assertSame(pet, owner1.getPet("Bella", false));
  }
}

