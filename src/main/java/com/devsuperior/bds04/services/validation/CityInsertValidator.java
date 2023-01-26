package com.devsuperior.bds04.services.validation;

import java.util.ArrayList;
import java.util.List;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import com.devsuperior.bds04.controllers.exceptions.FieldMessage;
import com.devsuperior.bds04.dto.CityDTO;

public class CityInsertValidator implements ConstraintValidator<CityInsertValid, CityDTO> {
	
	@Override
	public void initialize(CityInsertValid ann) {
	}

	@Override
	public boolean isValid(CityDTO dto, ConstraintValidatorContext context) {
		
		List<FieldMessage> list = new ArrayList<>();
		

		for (FieldMessage e : list) {
			context.disableDefaultConstraintViolation();
			context.buildConstraintViolationWithTemplate(e.getMessage()).addPropertyNode(e.getFieldName())
					.addConstraintViolation();
		}
		return list.isEmpty();
	}
}
