package bg.softuni.plantMe.web;

import bg.softuni.plantMe.models.DTOs.PlantFamilyDTO;
import bg.softuni.plantMe.service.PlantFamilyService;
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

    public PlantFamilyController(PlantFamilyService plantFamilyService) {
        this.plantFamilyService = plantFamilyService;

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

    @GetMapping("/plant-families")
    public String allPlantFamiliesView () {
        return "plant-families";
    }
    @GetMapping("/plant-family/{id}")
    public String plantFamilyDetailsView (@PathVariable("id") Long id, Model model) {
        model.addAttribute("plantFamilyDetails", plantFamilyService.getPlantFamilyById(id));
        return "plant-family-details";
    }


}
