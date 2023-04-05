package ru.zakirov.operator.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import ru.zakirov.operator.service.OperatorService;

import java.util.Objects;

@Controller
@RequestMapping("/operator")
public class OperatorController {
    private final OperatorService operatorService;

    public OperatorController(OperatorService operatorService) {
        this.operatorService = operatorService;
    }

    @GetMapping()
    public String numberPhone(Model model){
        Object numberPhone = model.getAttribute("numberPhone");
        if(numberPhone != null)
            model.addAttribute("operator",
                operatorService.getOperator(Objects.requireNonNull(model.getAttribute("numberPhone")).toString()));
        return "number-phone";
    }

    @GetMapping("/get-operator")
    public String getOperator(String numberPhone, RedirectAttributes redirectAttributes){
        redirectAttributes.addFlashAttribute("numberPhone", numberPhone);
        return "redirect:/operator";
    }
}
