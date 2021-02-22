package com.example.club.service.impl;

import com.example.club.mapper.ClubMapper;
import com.example.club.model.ClubWithBLOBs;
import com.example.club.service.ClubService;
import com.example.club.util.PageRequest;
import com.example.club.util.PageResult;
import com.example.club.util.PageUtils;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.sql.SQLException;
import java.util.List;

@Service
public class ClubServiceImpl implements ClubService {

    @Resource
    private ClubMapper clubMapper;

    @Override
    public List<ClubWithBLOBs> findClubs() {
        return clubMapper.selectAll();
    }

    @Override
    public PageResult findClubsByPage(PageRequest pageRequest) {
        return PageUtils.getPageResult(pageRequest, getPageInfo(pageRequest));
    }

    /**
     * 调用分页插件完成分页
     *
     * @param pageRequest
     * @return
     */
    private PageInfo<ClubWithBLOBs> getPageInfo(PageRequest pageRequest) {
        int pageNum = pageRequest.getPageNum();
        int pageSize = pageRequest.getPageSize();
        PageHelper.startPage(pageNum, pageSize);
        List<ClubWithBLOBs> sysMenus = clubMapper.selectPage();
        return new PageInfo<ClubWithBLOBs>(sysMenus);
    }

    @Override
    public ClubWithBLOBs findClubById(Integer id) {
        return clubMapper.selectByPrimaryKey(id);
    }

    @Override
    public int createClub(ClubWithBLOBs club) throws SQLException {
        return clubMapper.insertSelective(club);
    }

    @Override
    public int changeClubInfo(ClubWithBLOBs club) {
        return clubMapper.updateByPrimaryKeySelective(club);
    }

    @Override
    public List<ClubWithBLOBs> getCreatedClubs(Integer initiatorId) {
        return clubMapper.selectByInitiatorId(initiatorId);
    }

    @Override
    public List<ClubWithBLOBs> getApplyClubs() {
        return clubMapper.selectByProgress(1);
    }

    @Override
    public int deleteClubById(Integer clubId) {
        return clubMapper.deleteByPrimaryKey(clubId);
    }

}
