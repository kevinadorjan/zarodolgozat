package com.example.RaktarkezeloProgram.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.RaktarkezeloProgram.Repository.HusRepository;
import com.example.RaktarkezeloProgram.domain.Hus;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class HusService {

	private final HusRepository husRepository;
	
	public List<Hus> getAllHus(){
		return husRepository.findAll();
	}
	
	public Hus getHusById(Long id) {
		Optional<Hus> hus = husRepository.findById(id);
		if(hus.isPresent()) {
			return hus.get();
		}else {
			throw new IllegalArgumentException("not found [hus] by id");
		}
	}
	
	public Hus createOrUpdateHus(Hus entity) {
		if(entity.getId() == null) {
			return husRepository.save(entity);
		}
		else {
			Optional<Hus> hus = husRepository.findById(entity.getId());
			if(hus.isPresent()) {
				Hus newHus = hus.get();
				newHus.setCikkSzam(entity.getCikkSzam());
				//newHus.setKgARaktaron(entity.getKgARaktaron());
				newHus.setKgARaktaronMinimum(entity.getKgARaktaronMinimum());
				newHus.setNev(entity.getNev());
				newHus.setTermekAra(entity.getTermekAra());
				husRepository.save(newHus);
				return newHus;
			}else {
				entity.setId(null);
				entity = husRepository.save(entity);
				return entity;
			}
		}
	}
	public void deleteHusById(Long id) {
		Optional<Hus> hus = husRepository.findById(id);
		if(hus.isPresent()) {
			husRepository.deleteById(id);
		}else {
			throw new IllegalArgumentException("delete failed cant find hus by id");
		}
	}
	public void editDarabszamHus(Hus entity) {
		Optional<Hus> hus = husRepository.findById(entity.getId());
		if(hus.isPresent()) {
			Hus newHus = hus.get();
			newHus.setKgARaktaron(entity.getKgARaktaron()+newHus.getKgARaktaron());
			husRepository.save(newHus);
		}
		
	}
}
