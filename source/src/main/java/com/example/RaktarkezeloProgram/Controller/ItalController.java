package com.example.RaktarkezeloProgram.Controller;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.example.RaktarkezeloProgram.Service.ItalService;
import com.example.RaktarkezeloProgram.domain.Ital;

import Export.ItalPdf;
import Wrappers.ItalWrapper;
import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class ItalController {
	

	private final ItalService italService;

	@GetMapping("/italok")
	public String getAllItal(Model model) {
		model.addAttribute("italok",italService.getAllItal());
		return "italok";
	}
	
	@GetMapping({"/italok/edit","/italok/edit/{id}"})
	public String editItal(Model model,@PathVariable("id") Optional<Long> id) {
		if(id.isPresent()) {
			Ital ital = italService.getItalById(id.get());
			model.addAttribute("ital" , ital);
		}else {
			model.addAttribute("ital", new Ital());
		}
		return "ital-form";
	}
	@PostMapping(path="/createItal")
	public String createOrUpdateItal(Ital ital) {
		italService.createOrUpdateItal(ital);
		return "redirect:/italok";
	}
	@GetMapping({"/italok/delete","/italok/delete/{id}"})
	public String deleteItal(Ital ital,@PathVariable("id") Optional<Ital> id) {
		if(id.isPresent()) {
			italService.deleteItalById(ital.getId());
		}
		return "redirect:/italok";
	}
	
	@RequestMapping(value="/getSelectedItal",method=RequestMethod.POST)
	public String getSelectedItal(Model model,@RequestParam Ital[] italok) {
		model.addAttribute("italok",Arrays.asList(italok));
		ItalWrapper italWrapper = new ItalWrapper();
		
		italWrapper.setList(Arrays.asList(italok));
		
		model.addAttribute("wrapper",italWrapper);
		return "ital-kijelolt-form";
	}
	@PostMapping(path="/modifySelectedItal")
	public String modifySelectedItal(@ModelAttribute ItalWrapper wrapper,Model model) {
		
		for (Ital ital : wrapper.getList()) {
			italService.editDarabszamItal(ital);
		}
		
		//model.addAttribute("wrapper", wrapper);
		return "redirect:/italok";
	}
    @RequestMapping(path = "/italPdf", method = RequestMethod.GET)
    public ModelAndView report() {
        
        Map<String, Object> model = new HashMap<>();

        List<Ital> ital = italService.getAllItal();
        model.put("italok", ital);

        return new ModelAndView(new ItalPdf(), model);
    }
}

