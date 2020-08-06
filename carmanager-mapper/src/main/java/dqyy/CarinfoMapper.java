package dqyy;

import dqyy.Carinfo;

import java.util.List;

public interface CarinfoMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Carinfo record);

    int insertSelective(Carinfo record);

    Carinfo selectByPrimaryKey(Integer id);

    Carinfo selectByLicense(String license);

    List<Carinfo> selectLikeLicense(String license);

    int updateByPrimaryKeySelective(Carinfo record);

    int updateByPrimaryKey(Carinfo record);

    List<Carinfo> selectAll();
}