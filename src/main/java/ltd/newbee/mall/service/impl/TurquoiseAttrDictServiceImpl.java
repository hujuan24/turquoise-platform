package ltd.newbee.mall.service.impl;

import ltd.newbee.mall.entity.TurquoiseAttrDict;
import ltd.newbee.mall.mapper.TurquoiseAttrDictMapper;
import ltd.newbee.mall.service.TurquoiseAttrDictService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class TurquoiseAttrDictServiceImpl implements TurquoiseAttrDictService {

    @Resource
    private TurquoiseAttrDictMapper turquoiseAttrDictMapper;

    @Override
    public List<TurquoiseAttrDict> getAttrListByType(String attrType) {
        return turquoiseAttrDictMapper.selectListByType(attrType);
    }

    @Override
    public boolean saveAttr(TurquoiseAttrDict attrDict) {
        return turquoiseAttrDictMapper.insert(attrDict) > 0;
    }

    @Override
    public boolean deleteAttr(Long attrId) {
        return turquoiseAttrDictMapper.deleteByPrimaryKey(attrId) > 0;
    }
}
