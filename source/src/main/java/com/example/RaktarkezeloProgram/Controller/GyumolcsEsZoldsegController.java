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

import com.example.RaktarkezeloProgram.Service.GyumolcsEsZoldsegService;
import com.example.RaktarkezeloProgram.domain.Csomagolt;
import com.example.RaktarkezeloProgram.domain.GyumolcsEsZoldseg;

import Export.CsomagoltPdf;
import Export.GyumolcsEsZoldsegPdf;
import Wrappers.CsomagoltWrapper;
import Wrappers.GyumolcsEsZoldsegWrapper;
import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class GyumolcsEsZoldsegController {

	private final GyumolcsEsZoldsegService gyumolcsEsZoldsegService;
	
	@GetMapping("/gyumolcseszoldsegek")
	public String getAllGyumolcsEsZoldsegek(Model model) {
		model.addAttribute("gyumolcseszoldsegek",gyumolcsEsZoldsegService.getGyumolcsEsZoldseg());
		return "gyumolcseszoldsegek";
	}
	
	@GetMapping({"/gyumolcseszoldsegek/edit","/gyumolcseszoldsegek/edit/{id}"})
	public String editGyumolcsEsZoldsegById(Model model,@PathVariable("id") Optional<Long>id) {
		if(id.isPresent()) {
			GyumolcsEsZoldseg gyumolcseszoldseg = gyumolcsEsZoldsegService.getGyumolcsEsZoldsegById(id.get());
			model.addAttribute("gyumolcseszoldseg",gyumolcseszoldseg);
			
		}else {
			model.addAttribute("gyumolcseszoldseg", new GyumolcsEsZoldseg());
			
		}
		return "gyumolcseszoldseg-form";
	}
	@PostMapping(path="/createGyumolcsEsZoldseg")
	public String createOrUpdateGyumolcsEsZoldseg(GyumolcsEsZoldseg gyumolcsEsZoldseg) {
		gyumolcsEsZoldsegService.createOrUpdateGyumolcsEsZoldseg(gyumolcsEsZoldseg);
		return "redirect:/gyumolcseszoldsegek";
	}
	@GetMapping("/gyumolcseszoldsegek/delete/{id}")
	public String deleteGyumolcsEsZoldseg(Model model,@PathVariable("id") Optional<Long>id) {
		if(id.isPresent()) {
			gyumolcsEsZoldsegService.deleteGyumolcsEsZoldsegById(id.get());
		}else {
			throw new IllegalArgumentException("not found this id delete failed");
		}
		return "redirect:/gyumolcseszoldsegek";
		
	}
	@RequestMapping(value="/getSelectedGyumolcs", method = RequestMethod.POST)
	public String getAllSelectedGyumolcs(Model model, @RequestParam GyumolcsEsZoldseg[] gyumolcseszoldsegek) {
		model.addAttribute("gyumolcseszoldsegek", Arrays.asList(gyumolcseszoldsegek));
		
		GyumolcsEsZoldsegWrapper gyumolcsEsZoldsegWrapper = new GyumolcsEsZoldsegWrapper();
		
		gyumolcsEsZoldsegWrapper.setList(Arrays.asList(gyumolcseszoldsegek));
		model.addAttribute("wrapper", gyumolcsEsZoldsegWrapper);
		
		return "gyumolcseszoldseg-kijelolt-form";
	}
	@PostMapping(path="/modifySelectedGyumolcs")
	public String modifySelectedGyumolcs(@ModelAttribute GyumolcsEsZoldsegWrapper wrapper,Model model) {
		
		for (GyumolcsEsZoldseg gyumolcseszoldseg : wrapper.getList()) {
			gyumolcsEsZoldsegService.editDarabszamGyumolcsEsZoldseg(gyumolcseszoldseg);
		}
		
		return "redirect:/gyumolcseszoldsegek";
	}
    @RequestMapping(path = "/gyumolcsEsZoldsegPdf", method = RequestMethod.GET)
    public ModelAndView report() {
        
        Map<String, Object> model = new HashMap<>();

        List<GyumolcsEsZoldseg> gyumolcseszoldseg = gyumolcsEsZoldsegService.getGyumolcsEsZoldseg();
        model.put("gyumolcseszoldsegek", gyumolcseszoldseg);

        return new ModelAndView(new GyumolcsEsZoldsegPdf(), model);
    }
}