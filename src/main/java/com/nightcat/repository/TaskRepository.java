package com.nightcat.repository;

import com.nightcat.common.utility.Util;
import com.nightcat.entity.Task;
import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import java.util.List;

import static com.nightcat.common.utility.Util.emptyStr;
import static com.nightcat.common.utility.Util.uuid;

@Repository
public class TaskRepository extends AbstractRepository<Task> {


    public List<Task> findNeedExecute() {
        Criteria criteria = getCriteria();
        criteria.add(Restrictions.le("create_time", Util.now()));
        criteria.add(Restrictions.eq("status", Task.Status.Wait_execute));
        return criteria.list();
    }

    @Override
    public void save(Task task) {
        if (emptyStr(task.getId())) {
            task.setId(uuid());
        }
        super.save(task);
    }
}
