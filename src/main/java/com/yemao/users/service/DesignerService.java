package com.yemao.users.service;

import com.yemao.users.repository.ProfileRepository;
import com.yemao.users.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class DesignerService {

    @Autowired
    private ProfileRepository profileRep;
    @Autowired
    private UserRepository userRep;

    public ProfileRepository.Query query() {
//        return new DesignerQuery(profileRep.getCriteria(), userRep, profileRep);
        return profileRep.query();
    }

    public enum Official {
        ALL, OFFICIAL, NOT_OFFICIAL
    }
}

//    public static class DesignerQuery extends DesignerProfileRepository.ProfileQuery {
//
//        private UserRepository userRep;
//        private DesignerProfileRepository profileRep;
//
//        // 设计师昵称
//        private String nickname;
//
//        private DesignerQuery(Criteria criteria, UserRepository userRepository, DesignerProfileRepository profileRepository) {
//            super(criteria);
//            this.userRep = userRepository;
//            this.profileRep = profileRepository;
//        }
//
//        @SuppressWarnings("unchecked")
//        public List<DesignerProfile> list() {
//
////            List<DesignerProfile> profiles = super.list();
////
////            if (isNotEmpty(nickname)) {
////                List<User> users = userRep.findByLnickname(nickname);
////                users.forEach(user -> {
////                    profiles.add(profileRep.findById(user.getUid()));
////                });
////            }
////
////            return profiles;
////        }
//
//
//        public DesignerQuery nickname(String nickname) {
//            this.nickname = nickname;
//            return this;
//        }
//
//
//    }
//}
