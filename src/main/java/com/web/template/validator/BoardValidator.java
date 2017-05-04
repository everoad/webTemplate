package com.web.template.validator;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.web.template.vo.BoardVO;

public class BoardValidator implements Validator {

	@Override
	public boolean supports(Class<?> clazz) {
		return clazz.isAssignableFrom(BoardVO.class);
	}

	@Override
	public void validate(Object obj, Errors errors) {
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "title", "require", "제목 빈칸");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "content", "required", "내용 빈칸");
	}

}
