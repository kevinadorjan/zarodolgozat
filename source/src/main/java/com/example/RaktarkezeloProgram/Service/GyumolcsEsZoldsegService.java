package com.example.RaktarkezeloProgram.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import com.example.RaktarkezeloProgram.Repository.GyumolcsEsZoldsegRepository;
import com.example.RaktarkezeloProgram.domain.GyumolcsEsZoldseg;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class GyumolcsEsZoldsegService {
	private final GyumolcsEsZoldsegRepository gyumolcsEsZoldsegRepository;
	
	public List<GyumolcsEsZoldseg> getGyumolcsEsZoldseg(){
		
		return gyumolcsEsZoldsegRepository.findAll();
	}
	
	public GyumolcsEsZoldseg getGyumolcsEsZoldsegById(Long id) {
		Optional<GyumolcsEsZoldseg> gyumolcseszoldseg = gyumolcsEsZoldsegRepository.findById(id);
		if(gyumolcseszoldseg.isPresent()) {
			return gyumolcseszoldseg.get();
		}else {
			throw new IllegalArgumentException("not found by this id");
		}
	}
	
	public GyumolcsEsZoldseg createOrUpdateGyumolcsEsZoldseg(GyumolcsEsZoldseg entity) {
		if(entity.getId() == null) {
			return gyumolcsEsZoldsegRepository.save(entity);
			
		}
		else{
			Optional<GyumolcsEsZoldseg> gyumolcseszoldseg = gyumolcsEsZoldsegRepository.findById(entity.getId());
			if(gyumolcseszoldseg.isPresent())
			{
				GyumolcsEsZoldseg newGyumolcsEsZoldseg = gyumolcseszoldseg.get();
				newGyumolcsEsZoldseg.setCikkSzam(entity.getCikkSzam());
				newGyumolcsEsZoldseg.setNev(entity.getNev());
				newGyumolcsEsZoldseg.setTermekAra(entity.getTermekAra());
				//newGyumolcsEsZoldseg.setKgARaktaron(entity.getKgARaktaron());
				newGyumolcsEsZoldseg.setKgARaktaronMinimum(entity.getKgARaktaronMinimum());
				gyumolcsEsZoldsegRepository.save(newGyumolcsEsZoldseg);
				return newGyumolcsEsZoldseg;
			}
			else {
				entity.setId(null);
				  entity = gyumolcsEsZoldsegRepository.save(entity);
		            return entity;
			}
		}
	}
	
	public void deleteGyumolcsEsZoldsegById(Long id) {
		Optional <GyumolcsEsZoldseg> gyumolcseszoldseg = gyumolcsEsZoldsegRepository.findById(id);
		if(gyumolcseszoldseg.isPresent()) {
			 gyumolcsEsZoldsegRepository.deleteById(id);

		}else {
			throw new IllegalArgumentException("not found by id");
		}
	}
	public void editDarabszamGyumolcsEsZoldseg(GyumolcsEsZoldseg entity) {
		Optional<GyumolcsEsZoldseg> gyumolcseszoldseg = gyumolcsEsZoldsegRepository.findById(entity.getId());
		GyumolcsEsZoldseg newGyumolcsEsZoldseg = gyumolcseszoldseg.get();
		newGyumolcsEsZoldseg.setKgARaktaron(entity.getKgARaktaron()+newGyumolcsEsZoldseg.getKgARaktaron());
		gyumolcsEsZoldsegRepository.save(newGyumolcsEsZoldseg);
		
	}
}
