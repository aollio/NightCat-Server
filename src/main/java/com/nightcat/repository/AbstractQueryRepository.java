package com.nightcat.repository;

import com.nightcat.common.CatException;
import com.nightcat.common.base.BaseObject;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.*;

import static com.nightcat.common.ErrorCode.SYSTEM_PERSISTENT_INCORRECT_KEY;

/**
 * Created by finderlo on 16-12-17.
 */
@Transactional
public abstract class AbstractQueryRepository<T> extends BaseObject {

    @Autowired
    protected SessionFactory sessionFactory;

    protected List<String> ids = new ArrayList<>();

    protected String id;

    public List<T> sort(List<T> T) {
        return T;
    }

    @SuppressWarnings("unchecked")
    public List<T> findAll() {
        Session session = sessionFactory.getCurrentSession();
        List<T> tList = session.createQuery("from " + bindClassName()).list();

        if (tList == null) {
            return new ArrayList<T>();
        }
        return sort(tList);
    }

    public List<T> findBy(String[] keys, String[] values) {
        return findBy(keys, values, true);
    }

    public List<T> findBy(String key, String value) {
        return findBy(new String[]{key}, new String[]{value}, true);
    }

    public List<T> findBy(String key, String value, boolean isLikeQuery) {
        return findBy(new String[]{key}, new String[]{value}, isLikeQuery);
    }

    @SuppressWarnings("unchecked")
    public List<T> findBy(String[] keys, Object[] values, boolean isLikeQuery) {

        if (keys == null || values == null || keys.length == 0 || values.length == 0) {
            return new ArrayList<T>();
        }
        int length = keys.length <= values.length ? keys.length : values.length;
        for (int i = 0; i < length; i++) {
            if (!bindKeys().contains(keys[i])) {
                throw new CatException(SYSTEM_PERSISTENT_INCORRECT_KEY);
            }
        }

        Query<T, ? extends Query> query = query();

        for (int i = 0; i < length; i++) {
            //参数判断关键词匹配
            if (keys[i] == null || keys[i].trim().equals("") || values[i] == null) {
                continue;
            }
            if (isLikeQuery) {
                query.like(keys[i], values[i]);
            } else {
                query.eq(keys[i], values[i]);
            }
        }

        List<T> result = query.list();
        return sort(result);
    }

    public T findById(String id) {

        if (sessionFactory == null) {
            System.out.println("session::null");
        }
        if (isMoreId()) {
            throw new UnsupportedOperationException();
        }

        if (id == null || id.trim().equals("")) {
            return null;
        }

        HashMap<String, String> idAndValue = new HashMap<>();
        idAndValue.put(getId(), id);
        return findByIds(idAndValue);
    }

    @SuppressWarnings("unchecked")
    public T findByIds(Map<String, String> idAndValues) {
        Session session = sessionFactory.getCurrentSession();
        String hql = jointHqlByIdsQuery(idAndValues);
        org.hibernate.Query query = session.createQuery(hql);
        int i = 0;
        for (Map.Entry<String, String> entry : idAndValues.entrySet()) {
            String value = entry.getValue().trim();
            query.setString(i, value);
            i++;
        }
        List<T> tList = query.list();
        if (tList.isEmpty()) {
            return null;
        }
        return tList.get(0);
    }


    private String jointHqlByIdsQuery(Map<String, String> idAndValues) {

        String hql = " from " + bindClassName() + " e where ";
        StringBuilder hqlbuilder = new StringBuilder();
        boolean isFirst = true;
        for (Map.Entry<String, String> entry : idAndValues.entrySet()) {
            String value = entry.getValue().trim();
            String key = entry.getKey().trim();
            if (!isFirst) {
                hqlbuilder.append(" and");
            } else {
                isFirst = false;
            }
            hqlbuilder.append(" e.").append(key).append("= ?");
        }
        return hql + hqlbuilder.toString();
    }

    private String bindClassName() {
        return bindClass().getSimpleName();
    }

    private List<String> getIds() {
        if (ids.isEmpty()) {
            findIds();
        }
        return ids;
    }

    protected String getId() {
        if (id == null) {
            findIds();
        }
        return id;
    }

    private void findIds() {
        ids.clear();
        Method[] methods = bindClass().getDeclaredMethods();
        for (Method method : methods) {
            if (method.isAnnotationPresent(javax.persistence.Id.class)) {
                String methodName = method.getName();
                String name = methodName.substring(3);
                char[] idchar = name.toCharArray();
                String first = String.valueOf(idchar[0]).toLowerCase();
                String id = first + String.copyValueOf(idchar, 1, idchar.length - 1);
                ids.add(id);
            }
        }
        if (ids.size() == 1) {
            id = ids.get(0);
        }
    }

    private List<String> bindKeys() {
        List<String> results = new ArrayList<>();
        for (Field field : bindClass().getDeclaredFields()) {
            results.add(field.getName());
        }
        return results;
    }

    private boolean isMoreId() {
        if (getIds().size() > 1) {
            return true;
        } else {
            return false;
        }
    }

    @SuppressWarnings("unchecked")
    private Class bindClass() {
        Type sType = getClass().getGenericSuperclass();
        Type[] generics = ((ParameterizedType) sType).getActualTypeArguments();
        Class<T> mTClass = (Class<T>) (generics[0]);
        return mTClass;
    }

    public List<T> findBy(Map<String, String> attr, boolean likeQuery) {
        String[] key = new String[attr.size()];
        String[] val = new String[attr.size()];
        int i = 0;
        for (Map.Entry<String, String> entry : attr.entrySet()) {
            key[i] = entry.getKey();
            val[i] = entry.getValue();
            i++;
        }
        return findBy(key, val, likeQuery);
    }

    public Criteria getCriteria() {
        return sessionFactory.getCurrentSession().createCriteria(bindClass());
    }

    public Criteria getCriteria(int limit) {
        Criteria criteria = sessionFactory.getCurrentSession().createCriteria(bindClass());
        criteria.setFirstResult(0);
        criteria.setMaxResults(limit);
        return criteria;
    }


    //todo
    protected Query<T, ? extends Query> query() {
        return new Query<T, Query>(getCriteria(), new Query(getCriteria()));
    }

    public static class Query<ENTITY, RETURN extends Query> {

        protected Criteria criteria = null;

        protected RETURN query;

        public Query(Criteria criteria) {
            this(criteria, null);
        }

        public Query(Criteria criteria, RETURN query) {
            this.query = query;
            if (query != null) {
                query.criteria = criteria;
            }
        }

        public RETURN page(int page) {
            if (page >= 0)
                query.criteria.setFirstResult(page);
            return query;
        }

        public RETURN limit(int limit) {
            if (limit > 0)
                query.criteria.setMaxResults(limit);
            return query;
        }

        public RETURN add(Criterion condition) {
            query.criteria.add(condition);
            return query;
        }

        public RETURN eq(String key, Object value) {
            query.criteria.add(Restrictions.eq(key, value));
            return query;
        }

        public RETURN like(String key, Object value) {
            query.criteria.add(Restrictions.like(key, value));
            return query;
        }

        public RETURN desc(String key) {
            query.criteria.addOrder(Order.desc(key));
            return query;
        }

        public RETURN between(String key, Object low, Object high) {
            query.criteria.add(Restrictions.between(key, low, high));
            return query;
        }


        @SuppressWarnings("unchecked")
        public List<ENTITY> list() {
            return query.criteria.list();
        }


    }

}
