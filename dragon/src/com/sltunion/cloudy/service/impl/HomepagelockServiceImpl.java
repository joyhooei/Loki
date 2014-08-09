package com.sltunion.cloudy.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.sltunion.cloudy.common.Pager;
import com.sltunion.cloudy.persistent.mapper.THomepagechannelMapper;
import com.sltunion.cloudy.persistent.mapper.THomepagelockMapper;
import com.sltunion.cloudy.persistent.model.THomepagechannel;
import com.sltunion.cloudy.persistent.model.THomepagelock;
import com.sltunion.cloudy.service.HomepagelockService;

@Service("homepagelockService")
public class HomepagelockServiceImpl implements HomepagelockService {
	@Resource
	private THomepagelockMapper tHomepagelockMapper;
	@Resource
	private THomepagechannelMapper tHomepagechannelMapper;

	public void findPagerList(Pager pager) {
		long totalSize = tHomepagelockMapper.countPager(pager.getParams());
		pager.setTotalSize(totalSize);
		List<Map<String,Object>> pageList = tHomepagelockMapper.selectPager(pager.getParams());
		pager.setPageList(pageList);
		Map<String,Object> footer = tHomepagelockMapper.selectFooter(pager.getParams());
		pager.setFooter(footer);
	}

	public void save(THomepagelock entity) {
		tHomepagelockMapper.insertSelective(entity);
	}

	public void update(THomepagelock entity) {
		tHomepagelockMapper.updateByPrimaryKeySelective(entity);
	}

	public void changeStatus(Long id, Byte status) {
		THomepagelock entity = new THomepagelock();
		entity.setId(id);
		entity.setStatus(status);
		tHomepagelockMapper.updateByPrimaryKeySelective(entity);
	}

	public void delete(Long id) {
		THomepagelock entity = new THomepagelock();
		entity.setId(id);
		tHomepagelockMapper.deleteByPrimaryKey(entity);
	}

	public void batchDel(String ids) {
		
	}

	public void assign(Long id, Long channelid, boolean checked) {
		THomepagechannel entity = new THomepagechannel();
		entity.setHomepageid(id);
		entity.setChannelid(channelid);
		if(checked){
			tHomepagechannelMapper.insertSelective(entity);
		}else{
			tHomepagechannelMapper.deleteByPrimaryKey(entity);
		}
	}
}
