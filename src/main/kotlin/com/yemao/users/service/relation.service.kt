package com.yemao.users.service

import com.yemao.common.Tuple
import com.yemao.common.base.BaseObject
import com.yemao.event.Event
import com.yemao.event.EventManager
import com.yemao.repository.AbstractQueryRepository
import com.yemao.repository.RelationRepository
import com.yemao.users.models.Relation
import com.yemao.utility.Assert
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import com.yemao.utility.Util.*

@Service
class RelationService : BaseObject() {

    /**
     */
    val RELATION_ALREADY_EXIST = 406


    @Autowired lateinit var eventManager: EventManager

    @Autowired lateinit var relationRepository: RelationRepository

    fun unfollow(from: String, to: String) {
        val relation = relationRepository.findByFromAndTo(from, to)
        Assert.notNull(relation,
                RELATION_ALREADY_EXIST, "你还没有关注他/她")

        relationRepository.delete(relation)
        eventManager.publish(Event.UserUnFollowOther_Tuple_FromUid_ToUid, Tuple(from, to))
    }

    fun follow(from: String, to: String): Relation {
        var relation: Relation? = null
        Assert.isNull(relationRepository.findByFromAndTo(from, to),
                RELATION_ALREADY_EXIST, "你已经关注他/她了")
        relation = Relation()
        relation.from = from
        relation.to = to
        relation.create_time = now()
        relation.id = uuid()

        relationRepository.save(relation)
        eventManager.publish(Event.UserFollowOther_Relation, relation)
        return relation
    }

    fun findFollowers(uid: String): List<Relation> {
        return relationRepository.findFollowers(uid)
    }

    fun findFollowing(uid: String): List<Relation> {
        return relationRepository.findFollowing(uid)
    }

    fun save(relation: Relation) {
        relationRepository.save(relation)
    }

    fun saveOrUpdate(relation: Relation) {
        relationRepository.saveOrUpdate(relation)
    }

    fun update(relation: Relation) {
        relationRepository.update(relation)
    }

    fun queryDefault(): AbstractQueryRepository.DefaultQuery<Relation> {
        return relationRepository.queryDefault()
    }

    fun delete(relation: Relation) {
        relationRepository.delete(relation)
    }


}