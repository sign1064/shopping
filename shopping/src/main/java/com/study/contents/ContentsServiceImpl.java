package com.study.contents;
 
import java.util.List;
import java.util.Map;
 
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("com.study.contents.ContentsServiceImpl")
public class ContentsServiceImpl implements ContentsService {
	@Autowired
	private ContentsMapper mapper;
 
  @Override
  public int create(ContentsDTO dto) {
    return mapper.create(dto);
  }
 
  @Override
  public int update(ContentsDTO dto) {
    return mapper.update(dto);
  }
 
  @Override
  public int total(Map map) {
    return mapper.total(map);
  }
 
  @Override
  public List<ContentsDTO> list(Map map) {
    return mapper.list(map);
  }
 
  @Override
  public int updateFile(Map map) {
    return mapper.updateFile(map);
  }

@Override
public ContentsDTO detail(int contentsno) {
	return mapper.detail(contentsno);
}

@Override
public List<Map> getCategory() {
	return mapper.getCategory();
}

@Override
public List<ContentsDTO> mainlist(Map map) {
	return mapper.mainlist(map);
}

@Override
public int delete(int contentsno) {
	return mapper.delete(contentsno);
}
 
}