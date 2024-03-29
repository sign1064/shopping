package com.study.contents;

import java.util.List;
import java.util.Map;

public interface ContentsMapper {

	int create(ContentsDTO dto);

	int update(ContentsDTO dto);

	int total(Map map);

	List<ContentsDTO> list(Map map);

	int updateFile(Map map);

	ContentsDTO detail(int contentsno);

	List<Map> getCategory();

	List<ContentsDTO> mainlist(Map map);

	int delete(int contentsno);

}
