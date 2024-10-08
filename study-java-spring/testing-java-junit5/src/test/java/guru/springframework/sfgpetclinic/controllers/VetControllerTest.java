package guru.springframework.sfgpetclinic.controllers;

import static org.assertj.core.api.Assertions.assertThat;
import guru.springframework.sfgpetclinic.ControllerTests;
import guru.springframework.sfgpetclinic.fauxspring.Model;
import guru.springframework.sfgpetclinic.fauxspring.ModelMapImpl;
import guru.springframework.sfgpetclinic.model.Vet;
import guru.springframework.sfgpetclinic.services.SpecialtyService;
import guru.springframework.sfgpetclinic.services.VetService;
import guru.springframework.sfgpetclinic.services.map.SpecialityMapService;
import guru.springframework.sfgpetclinic.services.map.VetMapService;
import java.util.Set;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


class VetControllerTest implements ControllerTests {

  VetService vetService;
  SpecialtyService specialtyService;

  VetController vetController;

  @BeforeEach
  void setUp() {
    this.specialtyService = new SpecialityMapService();
    this.vetService = new VetMapService(this.specialtyService);

    this.vetController = new VetController(this.vetService);

    Vet vet1 = new Vet(1L, "Joe", "Buck", null);
    Vet vet2 = new Vet(2L, "Jimmy", "Smith", null);

    this.vetService.save(vet1);
    this.vetService.save(vet2);
  }

  @Test
  void testListVets() {
    Model model = new ModelMapImpl();

    String view = this.vetController.listVets(model);

    Assertions.assertThat(view).describedAs("Invalid View Path").isEqualTo("vets/index");

    Assertions.assertThat(((ModelMapImpl) model).getMap()).describedAs("Invalid Model Size").hasSize(1);
    Assertions.assertThat((Set) (((ModelMapImpl) model).getMap().get("vets")))
        .describedAs("Invalid Model Size").hasSize(2);
  }
}
