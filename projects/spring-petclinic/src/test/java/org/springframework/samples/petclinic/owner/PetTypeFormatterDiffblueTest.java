package org.springframework.samples.petclinic.owner;

import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Locale;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ContextConfiguration(classes = {PetTypeFormatter.class, PetRepository.class})
@ExtendWith(SpringExtension.class)
public class PetTypeFormatterDiffblueTest {
  @MockBean
  private PetRepository petRepository;
  @Autowired
  private PetTypeFormatter petTypeFormatter;
  @Test
  public void testPrint() {
    // Arrange
    Locale locale = new Locale("foo");

    // Act and Assert
    assertNull(this.petTypeFormatter.print(new PetType(), locale));
  }
  @Test
  public void testParse() throws ParseException {
    // Arrange
    when(this.petRepository.findPetTypes()).thenReturn(new ArrayList<PetType>());

    // Act and Assert
    assertThrows(ParseException.class, () -> this.petTypeFormatter.parse("Dog", new Locale("foo")));
  }
  @Test
  public void testParse2() throws ParseException {
    // Arrange
    PetType petType = new PetType();
    petType.setId(1);
    petType.setName("Dog");
    ArrayList<PetType> petTypeList = new ArrayList<PetType>();
    petTypeList.add(petType);
    when(this.petRepository.findPetTypes()).thenReturn(petTypeList);

    // Act and Assert
    assertSame(petType, this.petTypeFormatter.parse("Dog", new Locale("foo")));
  }
  @Test
  public void testParse3() throws ParseException {
    // Arrange
    PetType petType = new PetType();
    petType.setId(1);
    petType.setName("Bella");
    ArrayList<PetType> petTypeList = new ArrayList<PetType>();
    petTypeList.add(petType);
    when(this.petRepository.findPetTypes()).thenReturn(petTypeList);

    // Act and Assert
    assertThrows(ParseException.class, () -> this.petTypeFormatter.parse("Dog", new Locale("foo")));
  }
}

