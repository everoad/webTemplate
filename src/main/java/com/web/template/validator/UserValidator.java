package com.web.template.validator;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.web.template.vo.UserVO;

public class UserValidator implements Validator {

	@Override
	public boolean supports(Class<?> clazz) {
		return UserVO.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object obj, Errors errors) {
		UserVO userVo = (UserVO) obj;
		
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "user_email", "required", "빈칸입니다.");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "user_pwd", "required", "빈칸입니다.");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "user_nick", "required", "빈칸입니다.");
	}

}
