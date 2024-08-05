package bg.softuni.plantMe.web;

import bg.softuni.plantMe.models.DTOs.AddPlantDTO;
import bg.softuni.plantMe.models.DTOs.PlantFullInfoDTO;
import bg.softuni.plantMe.models.DTOs.PlantShortInfoDTO;
import bg.softuni.plantMe.service.PlantService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.io.File;
import java.util.List;

@Controller
@RequestMapping("/plants")
public class PlantController {
    private final PlantService plantService;
    public PlantController(PlantService plantService) {
        this.plantService = plantService;
    }

    @GetMapping()
    public String allPlants(Model model) {
        model.addAttribute("allPlants", plantService.getAllPlantsShortInfo());
        return "plants";
    }

    @GetMapping("/{id}")
    public String viewPlantDetails(@PathVariable("id") Long id, Model model) {
        PlantFullInfoDTO plantFullInfoDTO = plantService.showPlantDetails(id);
        List<PlantShortInfoDTO> plantsShortInfoByPlantFamily = plantService
                .getPlantsShortInfoByPlantFamilyName(plantFullInfoDTO.getPlantFamily().getName());

        model.addAttribute("plantDetails", plantFullInfoDTO);
        model.addAttribute("PlantFamilyMembers", plantsShortInfoByPlantFamily);
        return "plant-details";
    }

    @DeleteMapping("/{id}")
    public String deletePlant (@PathVariable("id") Long id) {

        plantService.deletePlant(id);
        return "redirect:/plants";
    }
}

