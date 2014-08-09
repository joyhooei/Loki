package com.sltunion.cloudy.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.sltunion.cloudy.common.Pager;
import com.sltunion.cloudy.persistent.mapper.TPopadsMapper;
import com.sltunion.cloudy.persistent.mapper.TPopadschannelMapper;
import com.sltunion.cloudy.persistent.model.TPopads;
import com.sltunion.cloudy.persistent.model.TPopadschannel;
import com.sltunion.cloudy.service.PopadsService;

@Service("popadsService")
public class PopadsServiceImpl implements PopadsService {
	@Resource
	private TPopadsMapper tPopadsMapper;
	@Resource
	private TPopadschannelMapper tPopadschannelMapper;

	public void findPagerList(Pager pager) {
		long totalSize = tPopadsMapper.countPager(pager.getParams());
		pager.setTotalSize(totalSize);
		List<Map<String,Object>> pageList = tPopadsMapper.selectPager(pager.getParams());
		pager.setPageList(pageList);
		Map<String,Object> footer = tPopadsMapper.selectFooter(pager.getParams());
		pager.setFooter(footer);
	}

	public void save(TPopads entity) {
		tPopadsMapper.insertSelective(entity);
	}

	public void update(TPopads entity) {
		tPopadsMapper.updateByPrimaryKeySelective(entity);
	}

	public void changeStatus(Long id, Byte status) {
		TPopads entity = new TPopads();
		entity.setId(id);
		entity.setStatus(status);
		tPopadsMapper.updateByPrimaryKeySelective(entity);
	}

	public void delete(Long id) {
		TPopads entity = new TPopads();
		entity.setId(id);
		tPopadsMapper.deleteByPrimaryKey(entity);
	}

	public void batchDel(String ids) {
		
	}

	public void assign(Long id, Long channelid, boolean checked) {
		TPopadschannel entity = new TPopadschannel();
		entity.setPopadsid(id);
		entity.setChannelid(channelid);
		if(checked){
			tPopadschannelMapper.insertSelective(entity);
		}else{
			tPopadschannelMapper.deleteByPrimaryKey(entity);
		}
	}
}
