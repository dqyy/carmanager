package dqyy;

import dqyy.AccountInfo;

import java.util.List;

public interface AccountInfoMapper {
    int deleteByPrimaryKey(Byte id);

    int insert(AccountInfo record);

    int insertSelective(AccountInfo record);

    AccountInfo selectByPrimaryKey(Integer id);

    List<AccountInfo> selectAll();

    int updateByPrimaryKeySelective(AccountInfo record);

    int updateByPrimaryKey(AccountInfo record);
}