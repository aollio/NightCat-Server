package com.nightcat.notice.service;

import com.nightcat.common.CatException;
import com.nightcat.entity.Notice;
import com.nightcat.event.Event;
import com.nightcat.event.EventExecutor;
import com.nightcat.event.EventManager;
import com.nightcat.repository.NoticeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;

import static com.nightcat.common.utility.Util.now;
import static com.nightcat.common.utility.Util.uuid;

@Service
public class NoticeService {

    @Autowired
    private NoticeRepository noticeRepository;

    public NoticeSender sender() {
        return new NoticeSender(noticeRepository);
    }

    @Autowired
    private EventManager eventManager;

    public void init() {
        eventManager.register(Event.ProjectPublishedEvent, projectPublishEventExector());
    }

    public static class NoticeSender {

        private String id = uuid();
        private String content;
        private String uid;
        private Timestamp create_time = now();
        private boolean read = false;
        private Notice.Type type;
        private boolean del = false;

        private NoticeRepository noticeRep;

        NoticeSender(NoticeRepository noticeRep) {
            this.noticeRep = noticeRep;
        }

        public NoticeSender content(String content) {
            this.content = content;
            return this;
        }

        public NoticeSender uid(String uid) {
            this.uid = uid;
            return this;
        }


        public NoticeSender type(Notice.Type type) {
            this.type = type;
            return this;
        }

        public void send() {

            if (uid == null) {
                throw new CatException("NoticeSender uid must be exist");
            }

            if (type == null) {
                throw new CatException("NoticeSender type must be exist");
            }

            if (content == null) {
                throw new CatException("NoticeSender content must be exist");
            }

            Notice notice = new Notice(id, content, uid,
                    create_time, read, type, del);

            this.noticeRep.save(notice);
        }
    }

    public EventExecutor projectPublishEventExector() {
        return new EventExecutor() {
            @Override
            public void execute(Event event, Object context) {
                if (event != Event.ProjectPublishedEvent) return;

            }
        };
    }
}