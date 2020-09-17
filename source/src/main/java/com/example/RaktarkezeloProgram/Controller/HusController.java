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

import com.example.RaktarkezeloProgram.Service.HusService;
import com.example.RaktarkezeloProgram.domain.Csomagolt;
import com.example.RaktarkezeloProgram.domain.Hus;

import Export.CsomagoltPdf;
import Export.HusPdf;
import Wrappers.CsomagoltWrapper;
import Wrappers.HusWrapper;
import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class HusController {

	private final HusService husService;
	
	@GetMapping("/husok")
	public String getAllHus(Model model) {
		model.addAttribute("husok",husService.getAllHus());
		return "husok";
	}
	
	@GetMapping({"/husok/edit","/husok/edit/{id}"})
		public String editHusById(Model model,@PathVariable("id") Optional<Long> id) {
		if(id.isPresent()) {
			Hus hus = husService.getHusById(id.get());
			model.addAttribute("hus" ,hus);
		
	}else {
		model.addAttribute("hus" , new Hus());
	}
		return "hus-form";
	
}
	@PostMapping(path="/createHus")
	public String createOrUpdateHus(Hus hus) {
		husService.createOrUpdateHus(hus);
		return "redirect:/husok";
	}
	@GetMapping("/husok/delete/{id}")
	public String deleteHus(Hus hus,@PathVariable("id") Optional<Hus> id) {
		husService.deleteHusById(hus.getId());
		return "redirect:/husok";
	}
	@RequestMapping(value="/getSelectedHus",method=RequestMethod.POST)
	public String getSelectedHus(Model model,@RequestParam Hus[] husok) {
		model.addAttribute("husok",Arrays.asList(husok));
		HusWrapper husWrapper = new HusWrapper();
		
		husWrapper.setList(Arrays.asList(husok));
		model.addAttribute("wrapper",husWrapper);
		return "hus-kijelolt-form";
	}
	@PostMapping(path="/modifySelectedHus")
	public String modifySelectedHus(@ModelAttribute HusWrapper wrapper,Model model) {
		
		for (Hus hus : wrapper.getList()) {
			husService.editDarabszamHus(hus);
		}
		
		//model.addAttribute("wrapper", wrapper);
		return "redirect:/husok";
	}
    @RequestMapping(path = "/husPdf", method = RequestMethod.GET)
    public ModelAndView report() {
        
        Map<String, Object> model = new HashMap<>();

        List<Hus> hus = husService.getAllHus();
        model.put("husok", hus);

        return new ModelAndView(new HusPdf(), model);
    }
}
