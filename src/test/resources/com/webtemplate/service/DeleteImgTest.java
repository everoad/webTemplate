package com.webtemplate.service;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(JUnit4.class)
public class DeleteImgTest {

	@Before
	public void before() {
		System.out.println("before");
	}

	@Test
	public void deleteImgs() {
		// DB에서 이미지 이름 가져오기
		List<String> list;

		// upload 폴더에서 이미지 파일 목록 가져오기
		String path = "C:/java/uploads";
		Map<String, Boolean> map = new HashMap<>();
		File dir = new File(path);

		if (dir.exists()) {
			String[] names = dir.list();

			for (String name : names) {
				System.out.println(name);
				map.put(name, false);
			}

		}

	}

}
