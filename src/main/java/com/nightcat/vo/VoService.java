package com.nightcat.vo;

import com.nightcat.common.base.BaseObject;
import com.nightcat.entity.*;
import com.nightcat.repository.ProjectImagesRepository;
import com.nightcat.users.service.DesignerProfileService;
import com.nightcat.users.service.UserExpService;
import com.nightcat.users.service.UserService;
import com.nightcat.utility.Util;
import com.nightcat.vo.model.ExpCommentVo;
import com.nightcat.vo.model.ExpVo;
import com.nightcat.vo.model.ProjectVo;
import com.nightcat.vo.model.UserVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Service
public class VoService extends BaseObject {


    @Autowired
    private DesignerProfileService profileServ;

    @Autowired
    private ProjectImagesRepository projectImagesRep;

    @Autowired
    private UserService userServ;

    @Autowired
    private UserExpService expService;

    /**
     * 用户相关
     * =============================================================
     */
    public UserVo from(User user) {
        if (user == null) return null;
        DesignerProfile profile = profileServ.findById(user.getUid());
        return from(user, profile);
    }

    public UserVo from(DesignerProfile profile) {
        if (profile == null) return null;
        User user = userServ.findById(profile.getUid());
        return from(user, profile);
    }

    public ExpVo from(UserExperience src) {
        ExpVo vo = new ExpVo();
        Util.less2more(src, vo);
        vo.setCreator(from(userServ.findById(src.getUid())));
        return vo;
    }

    public ExpCommentVo from(UserExpComment comment) {
        ExpCommentVo vo = new ExpCommentVo();
        Util.less2more(comment, vo);
        vo.setCreator(from(userServ.findById(comment.getUid())));
        vo.setExp(from(expService.findById(comment.getExp_id())));
        return vo;
    }

    /**
     * 项目相关
     * =============================================================
     */
    public ProjectVo from(Project project) {
        if (project == null) return null;
        List<ProjectImage> imgs = projectImagesRep.findByProjId(project.getId());
        return from(project, imgs);

    }

    /**
     * =============================================================
     */
    private ProjectVo from(Project p, List<ProjectImage> img_urls) {
        ProjectVo vo = new ProjectVo();


        vo.setId(p.getId());
        vo.setTitle(p.getTitle());
        vo.setType(p.getType());
        vo.setContent(p.getContent());
        vo.setBudget(p.getBudget());
        vo.setArea(p.getArea());
        vo.setArea_count(p.getArea_count());
        vo.setDepth(p.getDepth());
        vo.setPeriod(p.getPeriod());
        vo.setStart_time(p.getStart_time());
        vo.setEnd_time(p.getEnd_time());
        vo.setStatus(p.getStatus());
        vo.setCreate_by(p.getCreate_by());
        vo.setCreate_time(p.getCreate_time());
        vo.setGood(p.isGood());
        vo.setModify_by(p.getModify_by());
        vo.setModify_mark(p.getModify_by());
        vo.setDue_time(p.getDue_time());
        vo.setFav_count(p.getFav_count());
        vo.setGrab_count(p.getGrab_count());
        vo.setCancel_reason(p.getCancel_reason());

        if (img_urls != null) {
            img_urls.forEach(e -> {
                if (e.getProj_id().equals(p.getId()))
                    vo.getImg_urls().add(e.getImg_url());
            });
        }

        vo.setCreator(from(userServ.findById(p.getCreate_by())));

        if (Util.strExist(p.getBidder())) {
            vo.setBidder(from(userServ.findById(p.getBidder())));
        }

        return vo;
    }


    public Object from(EntityModel e) {
        if (e instanceof Project) return from((Project) e);
        else if (e instanceof User) return from((User) e);
        else if (e instanceof DesignerProfile) return from((DesignerProfile) e);
        else if (e instanceof UserExperience) return from((UserExperience) e);
        else if (e instanceof UserExpComment) return from((UserExpComment) e);

        logger.warn("收到不明实体类 请求转换为VO. " + e.getClass());
        return e;
    }

    @SuppressWarnings("unchecked")
    public Collection<Object> from(Collection<? extends EntityModel> models) {
        if (models == null || models.size() == 0) return new ArrayList<>();
        List<Object> result = new ArrayList<>();
        models.forEach(e -> {
            if (e != null) {
                result.add(from(e));
            }
        });
        return result;
    }


    private static UserVo from(User user, DesignerProfile profile) {
        UserVo userVo = new UserVo();
        Util.less2more(user, userVo);
        Util.less2more(profile, userVo);
        return userVo;
    }
}
