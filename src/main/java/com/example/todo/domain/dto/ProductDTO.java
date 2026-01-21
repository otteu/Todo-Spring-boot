package com.example.todo.domain.dto;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ProductDTO {

	private Long pno;
	
	private String pname;
	
	private int price;
	
	private String pdesc;
	
	private boolean delFlag;
	
	// 업로드에 사용
	@Builder.Default
	private List<MultipartFile> files = new ArrayList<>();
	
	// upload 된거 조회에 사용
	@Builder.Default
	private List<String> uploadFileNames = new ArrayList<>();

}
