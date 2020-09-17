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

import com.example.RaktarkezeloProgram.Service.CsomagoltService;
import com.example.RaktarkezeloProgram.domain.Csomagolt;

import Export.CsomagoltPdf;
import Wrappers.CsomagoltWrapper;
import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor

public class CsomagoltController {

	private final CsomagoltService csomagoltService;
	
	@GetMapping("/csomagoltak")
	public String getAllCsomagolt(Model model) {

		model.addAttribute("csomagoltak" , csomagoltService.getAllCsomagolt());
		return "csomagoltak";
	}	
	@GetMapping({"/csomagoltak/edit", "/csomagoltak/edit/{id}"})
	public String editCsomagoltById(Model model,@PathVariable("id") Optional<Long> id)
	{
		if(id.isPresent()) {
			Csomagolt csomagolt = csomagoltService.getCsomagoltById(id.get());
					model.addAttribute("csomagolt", csomagolt);
		}else {
			model.addAttribute("csomagolt" ,new Csomagolt());
		}
		return "csomagolt-form";
	}
	
	@PostMapping(path="/createCsomagolt")
	public String createOrUpdateCsomagolt(Csomagolt csomagolt) {
		csomagoltService.createOrUpdateCsomagolt(csomagolt);
		return "redirect:/csomagoltak";
	}
	@GetMapping("/csomagoltak/delete/{id}")
	public String deleteCsomagoltById(Csomagolt csomagolt,@PathVariable("id") Optional<Long> id) {
		csomagoltService.deleteCsomagoltById(id.get());
		return "redirect:/csomagoltak";
	}

	@RequestMapping(value="/getSelectedCsomagolt", method = RequestMethod.POST)
	public String getAllSelectedCsomagolt(Model model, @RequestParam Csomagolt[] csomagoltak) {
		model.addAttribute("csomagoltak", Arrays.asList(csomagoltak));
		
		CsomagoltWrapper csomagoltWrapper = new CsomagoltWrapper();
		
		csomagoltWrapper.setList(Arrays.asList(csomagoltak));
		model.addAttribute("wrapper", csomagoltWrapper);
		
		return "csomagolt-kijelolt-form";
	}

	@PostMapping(path="/modifySelectedCsomagolt")
	public String modifySelectedCsomagolt(@ModelAttribute CsomagoltWrapper wrapper,Model model) {
		
		for (Csomagolt csomagolt : wrapper.getList()) {
			csomagoltService.editDarabszamCsomagolt(csomagolt);
		}
		
		return "redirect:/csomagoltak";
	}
    @RequestMapping(path = "/csomagoltPdf", method = RequestMethod.GET)
    public ModelAndView report() {
        
        Map<String, Object> model = new HashMap<>();

        List<Csomagolt> csomagolt = csomagoltService.getAllCsomagolt();
        model.put("csomagoltak", csomagolt);

        return new ModelAndView(new CsomagoltPdf(), model);
    }
}
