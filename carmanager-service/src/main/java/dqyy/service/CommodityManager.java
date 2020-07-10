package dqyy.service;

import dqyy.Commodity;
import dqyy.CommodityMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommodityManager {

    @Autowired
    CommodityMapper commodityMapper;

    //增添商品
    public int addComm(String image, String name, String number, Integer price, Integer stock) {
        Commodity commodity = new Commodity();
        commodity.setImage(image);
        commodity.setName(name);
        commodity.setNumber(number);
        commodity.setPrice(price);
        commodity.setStock(stock);
        int insert = commodityMapper.insert(commodity);
        if (insert != 0) {
            return 1;
        }
        return 0;
    }

    //修改价格
    public int updataCommprice(String name, Integer price) {
        Commodity commodity = findCommodity(name);
        commodity.setPrice(price);
        int i = commodityMapper.updateByPrimaryKeySelective(commodity);
        if (i != 0) {
            return 1;
        }
        return 0;
    }

    //修改库存
    public int updataCommstock(String name, Integer stock) {
        Commodity commodity = findCommodity(name);
        commodity.setStock(stock);
        int i = commodityMapper.updateByPrimaryKeySelective(commodity);
        if (i != 0) {
            return 1;
        }
        return 0;
    }

    //删除商品
    public int deleteComm(String name) {
        Commodity commodity = findCommodity(name);
        Integer id = commodity.getId();
        int i = commodityMapper.deleteByPrimaryKey(id);
        if (i != 0) {
            return 1;
        }
        return 0;
    }

    //查询商品详情
    public Commodity findCommodity(String name) {
        return commodityMapper.selectByName(name);
    }

    @Cacheable(value = "commAll")
    //查询所有商品及价格
    public List<Commodity> serchAll() {
        return commodityMapper.selectAll();
    }

    //模糊查询
    public List<Commodity> likeSerch(String name) {
        return commodityMapper.selectLikename(name);
    }
}
