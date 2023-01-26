package com.devsuperior.bds04.services;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.devsuperior.bds04.dto.CityDTO;
import com.devsuperior.bds04.entities.City;
import com.devsuperior.bds04.repositories.CityRepository;
import com.devsuperior.bds04.services.exceptions.DatabaseException;
import com.devsuperior.bds04.services.exceptions.ResourceNotFoundException;

@Service
public class CityService {

	@Autowired
	private CityRepository repository;
	
	@Transactional(readOnly = true)
	public List<CityDTO> findAll() {
		List<City> list = repository.findAll(Sort.by("name"));
		List<CityDTO> listaDTO = new ArrayList<CityDTO>(); 
		
		for(City d: list) {
			CityDTO dDto = new CityDTO();
			dDto.setId(d.getId());
			dDto.setName(d.getName());
			listaDTO.add(dDto);
		}
		
		return listaDTO;
	}
	
	@Transactional
	public CityDTO insert(CityDTO dto) {
		City entity = new City();
		entity.setName(dto.getName());
		entity = repository.save(entity);
		return new CityDTO(entity);
	}
	
	
	//não coloca o transactional para pegar a exception
	public void delete(Long id) {
		try {
			repository.deleteById(id);	
		}catch(EmptyResultDataAccessException e) {
			throw new ResourceNotFoundException("Id not found: "+id);
		}catch(DataIntegrityViolationException e) {
			throw new DatabaseException("Integrity violation ");
		}
	}
	
}
