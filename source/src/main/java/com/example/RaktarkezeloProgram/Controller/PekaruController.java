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

import com.example.RaktarkezeloProgram.Service.PekaruService;
import com.example.RaktarkezeloProgram.domain.Pekaru;

import Export.PekaruPdf;
import Wrappers.ItalWrapper;
import Wrappers.PekaruWrapper;
import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class PekaruController {

	private final PekaruService pekaruService;
	//
	
	@GetMapping("/pekaruk")
	public String getAllPekaru(Model model) {
		model.addAttribute("pekaruk",pekaruService.getAllPekaru());
		return "pekaruk";
	}
	@GetMapping({"/pekaruk/edit","/pekaruk/edit/{id}"})
	public String editPekaru(Model model,@PathVariable("id") Optional<Long>id) {
		if(id.isPresent()) {
			Pekaru pekaru = pekaruService.getPekaruById(id.get());
			model.addAttribute("pekaruk",pekaru);
		}else {
			model.addAttribute("pekaruk",new Pekaru());
		}
		return "pekaru-form";
	}
	@PostMapping(path="/createPekaru")
	public String createPekaru(Pekaru pekaru) {
		pekaruService.createOrUpdatePekaru(pekaru);
		return "redirect:/pekaruk";
	}
	@GetMapping({"/pekaruk/delete","/pekaruk/delete/{id}"})
	public String deletePekaru(Model model,@PathVariable("id") Optional<Long> id) {
		if(id.isPresent()) {
			pekaruService.deletePekaruById(id.get());
		}
		return "redirect:/pekaruk";
	}
	@PostMapping(value="/getSelectedPekaru")
	public String getSelectedPekaru(Model model,@RequestParam Pekaru[] pekaruk) {
	model.addAttribute("pekaruk",Arrays.asList(pekaruk));
	PekaruWrapper pekaruWrapper = new PekaruWrapper();
	pekaruWrapper.setList(Arrays.asList(pekaruk));
	model.addAttribute("wrapper", pekaruWrapper);
	return "pekaru-kijelolt-form";
	}
	@PostMapping(value="/modifySelectedPekaru")
	public String modifySelectedPekaru(@ModelAttribute PekaruWrapper wrapper,Model model) {
		for(Pekaru pekaru : wrapper.getList()) {
			pekaruService.editDarabszamPekaru(pekaru);
		}
		return "redirect:/pekaruk";
	}
    @RequestMapping(path = "/pekaruPdf", method = RequestMethod.GET)
    public ModelAndView report() {
        
        Map<String, Object> model = new HashMap<>();

        List<Pekaru> pekaru = pekaruService.getAllPekaru();
        model.put("pekaruk", pekaru);

        return new ModelAndView(new PekaruPdf(), model);
    }
}
