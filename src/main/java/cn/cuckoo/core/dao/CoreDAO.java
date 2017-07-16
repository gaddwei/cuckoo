package cn.cuckoo.core.dao;

import java.util.List;

import cn.cuckoo.core.entity.District;

public interface CoreDAO {
	List<District> getDistrictByLevel(District district);
}
