package bg.softuni.plantMe.web;

import bg.softuni.plantMe.models.DTOs.PlantFamilyDTO;
import bg.softuni.plantMe.models.DTOs.PlantShortInfoDTO;
import bg.softuni.plantMe.service.PlantFamilyService;
import bg.softuni.plantMe.service.PlantService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

@Controller
public class PlantFamilyController {

    private static final String UPLOAD_DIRECTORY = System.getProperty("user.dir") +
            "\\plantMe\\src\\main\\resources\\static\\images";

    private final PlantFamilyService plantFamilyService;
    private final PlantService plantService;

    public PlantFamilyController(PlantFamilyService plantFamilyService, PlantService plantService) {
        this.plantFamilyService = plantFamilyService;

        this.plantService = plantService;
    }
    @ModelAttribute("plantFamilyDTO")
    public PlantFamilyDTO plantFamilyDTO() {
        return new PlantFamilyDTO();
    }

    @ModelAttribute("allPlantFamilies")
    public List<PlantFamilyDTO> allPlantFamilyDTOs () {
        return plantFamilyService.findAllPlantFamilies();
    }

    @GetMapping("/add-plant-family")
    public String viewAddPlantFamily () {
        return "add-plant-family";
    }

    @PostMapping("add-plant-family")
    public String addPlantFamily (@Valid PlantFamilyDTO addPlantFamilyDTO,
                                  @RequestParam("image") MultipartFile file,
                                  BindingResult bindingResult,
                                  RedirectAttributes redirectAttributes) throws IOException {

        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("addPlantFamilyDTO", addPlantFamilyDTO);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.addPlantFamilyDTO", bindingResult);
            return "redirect:/add-plant-family";
        }

        Path fileNameAndPath = Paths.get(UPLOAD_DIRECTORY, file.getOriginalFilename());
        Files.write(fileNameAndPath,file.getBytes());

        plantFamilyService.addPlantFamily(addPlantFamilyDTO, file.getOriginalFilename());

        return "redirect:/home";
    }

    @GetMapping("/plantFamilies")
    public String allPlantFamiliesView () {
        return "plant-families";
    }
    @GetMapping("/plant-family/{id}")
    public String plantFamilyDetailsView (@PathVariable("id") Long id, Model model) {
        PlantFamilyDTO plantFamilyById = plantFamilyService.getPlantFamilyById(id);
        List<PlantShortInfoDTO> plantsShortInfoByPlantFamilyName = plantService.getPlantsShortInfoByPlantFamilyName(plantFamilyById.getName());

        model.addAttribute("plantFamilyDetails", plantFamilyById);
        model.addAttribute("PlantFamilyMembers", plantsShortInfoByPlantFamilyName);
        return "plant-family-details";
    }

    @DeleteMapping("/plant-family/{id}")
    public String deletePlantFamily (@PathVariable("id") Long id) {
        plantFamilyService.deleteFamily(id);
        return "redirect:/plantFamilies";
    }



}
