package bg.softuni.plantMe.web;

import bg.softuni.plantMe.models.DTOs.AddPlantDTO;
import bg.softuni.plantMe.models.enums.SunRequirements;
import bg.softuni.plantMe.service.PlantService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Controller
public class AddPlantController {

    private static final String UPLOAD_DIRECTORY = System.getProperty("user.dir") + "\\plantMe\\src\\main\\resources\\static\\images";
    private final PlantService plantService;

    public AddPlantController(PlantService plantService) {
        this.plantService = plantService;
    }

    @ModelAttribute("addPlantDTO")
    public AddPlantDTO addPlantDTO() {
        return new AddPlantDTO();
    }
    @GetMapping("/add-plant")
    public String viewAddPlant (Model model) {
        model.addAttribute("plantNames",plantService.getPlantFamilyNames());
        model.addAttribute("sunRequirements", SunRequirements.values());
        System.out.println(System.getProperty("user.dir"));
        return "add-plant";
    }
    @PostMapping("/add-plant")
    public String addPlant (@Valid AddPlantDTO addPlantDTO,
                            @RequestParam("image") MultipartFile file,
                            BindingResult bindingResult,
                            RedirectAttributes redirectAttributes) throws IOException {

        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("addPaintingDTO", addPlantDTO);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.addPlantDTO", bindingResult);
            return "redirect:/add-plant";
        }

        Path fileNameAndPath = Paths.get(UPLOAD_DIRECTORY, file.getOriginalFilename());
        Files.write(fileNameAndPath,file.getBytes());

        plantService.addPlant(addPlantDTO, file.getOriginalFilename());

        return "redirect:/home";
    }
}
