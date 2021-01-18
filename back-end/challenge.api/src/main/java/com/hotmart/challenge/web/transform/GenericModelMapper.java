package com.hotmart.challenge.web.transform;

import org.modelmapper.ModelMapper;

public class GenericModelMapper {

	private GenericModelMapper() {
		super();
	}

	public static <T, O> T transform(O obj, Class<T> objClass) {
		return new ModelMapper().map(obj, objClass);
	}

}
