package com.nightcat.users.service;

import com.nightcat.entity.DesignerProfile;
import com.nightcat.entity.User;
import com.nightcat.repository.DesignerProfileRepository;
import com.nightcat.repository.UserRepository;
import org.hibernate.Criteria;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

import static org.apache.commons.lang3.StringUtils.isNotEmpty;
import static org.apache.commons.lang3.StringUtils.remove;

@Service
public class DesignerService {

    @Autowired
    private DesignerProfileRepository profileRep;
    @Autowired
    private UserRepository userRep;

    public DesignerQuery query() {
        return new DesignerQuery(profileRep.getCriteria(), userRep, profileRep);
    }

    public enum Official {
        ALL, OFFICIAL, NOT_OFFICIAL
    }

    public static class DesignerQuery extends DesignerProfileRepository.ProfileQuery {

        private UserRepository userRep;
        private DesignerProfileRepository profileRep;

        // 设计师昵称
        private String nickname;

        private DesignerQuery(Criteria criteria, UserRepository userRepository, DesignerProfileRepository profileRepository) {
            super(criteria);
            this.userRep = userRepository;
            this.profileRep = profileRepository;
        }

        @SuppressWarnings("unchecked")
        public List<DesignerProfile> list() {

            List<DesignerProfile> profiles = super.list();

            if (isNotEmpty(nickname)) {
                List<User> users = userRep.findByLnickname(nickname);
                users.forEach(user -> {
                    profiles.add(profileRep.findById(user.getUid()));
                });
            }

            return profiles;
        }


        public DesignerQuery nickname(String nickname) {
            this.nickname = nickname;
            return this;
        }


    }
}
