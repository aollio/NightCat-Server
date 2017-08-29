package com.yemao.vo;

import com.yemao.common.base.BaseObject;
import com.yemao.entity.EntityModel;
import com.yemao.notice.models.Notice;
import com.yemao.projects.models.Project;
import com.yemao.projects.models.ProjectImage;
import com.yemao.projects.repository.ProjectImagesRepository;
import com.yemao.users.models.*;
import com.yemao.users.service.ExperienceService;
import com.yemao.users.service.ProfileService;
import com.yemao.users.service.UserService;
import com.yemao.utility.Util;
import com.yemao.vo.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Service
public class VoService extends BaseObject {


    @Autowired
    private ProfileService profileServ;

    @Autowired
    private ProjectImagesRepository projectImagesRep;

    @Autowired
    private UserService userServ;

    @Autowired
    private ExperienceService expService;

    /**
     * 用户相关
     * =============================================================
     */
    public UserVo from(User user) {
        if (user == null) return null;
        Profile profile = profileServ.findById(user.getUid());
        return from(user, profile);
    }

    public UserVo from(Profile profile) {
        if (profile == null) return null;
        User user = userServ.findById(profile.getUid());
        return from(user, profile);
    }

    public ExpVo from(Experience src) {
        ExpVo vo = new ExpVo();
        Util.less2more(src, vo);
        vo.setCreator(from(userServ.findById(src.getUid())));
        return vo;
    }

    public RelationVo from(Relation relation) {
        RelationVo vo = new RelationVo();
        vo.setId(relation.getId());
        vo.setCreate_time(relation.getCreate_time());
        vo.setType(relation.getType());
        vo.setFrom(from(userServ.findById(relation.getFrom())));
        vo.setTo(from(userServ.findById(relation.getTo())));
        return vo;
    }

    public ExpCommentVo from(ExpComment comment) {
        ExpCommentVo vo = new ExpCommentVo();
        Util.less2more(comment, vo);
        vo.setCreator(from(userServ.findById(comment.getUid())));
        vo.setExp(from(expService.findById(comment.getExp_id())));
        return vo;
    }

    public NoticeVo from(Notice notice) {
        return NoticeVo.from(notice);
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
        else if (e instanceof Profile) return from((Profile) e);
        else if (e instanceof Experience) return from((Experience) e);
        else if (e instanceof ExpComment) return from((ExpComment) e);
        else if (e instanceof Notice) return from((Notice) e);
        else if (e instanceof Relation) return from((Relation) e);
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


    private static UserVo from(User user, Profile profile) {
        UserVo userVo = new UserVo();
        Util.less2more(user, userVo);
        Util.less2more(profile, userVo);
        return userVo;
    }
}
