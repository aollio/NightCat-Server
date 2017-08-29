package com.yemao.projects.service

import com.yemao.common.CatException
import com.yemao.common.base.BaseObject
import com.yemao.projects.models.Dynamic
import com.yemao.repository.DynamicRepository
import com.yemao.utility.Util
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class DynamicService : BaseObject() {

    @Autowired lateinit var dynamicRepository: DynamicRepository

    fun logger(): DynamicLogger {
        return DynamicLogger(dynamicRepository)
    }


    class DynamicLogger constructor(private val dynamicRepository: DynamicRepository) {


        private var proj_id: String? = null
        private var uid: String? = null
        private var publisher: Boolean = false
        private var type: Dynamic.Type? = null
        private var content: String? = null
        private var img_url: String? = null


        fun project(proj_id: String): DynamicLogger {
            this.proj_id = proj_id
            return this
        }

        fun user(uid: String): DynamicLogger {
            this.uid = uid
            return this
        }

        fun publisher(publisher: Boolean): DynamicLogger {
            this.publisher = publisher
            return this
        }

        fun type(type: Dynamic.Type): DynamicLogger {
            this.type = type
            return this
        }

        fun content(content: String): DynamicLogger {
            this.content = content
            return this
        }

        fun img(img_url: String): DynamicLogger {
            this.img_url = img_url
            return this
        }


        fun log(): Dynamic {

            val dynamic = Dynamic()
            dynamic.id = Util.uuid()
            dynamic.create_time = Util.now()

            if (proj_id == null) {
                throw CatException("Build Project Dynamic proj_id must be exist")
            }

            if (uid == null) {
                throw CatException("Build Project Dynamic uid must be exist")
            }

            if (type == null) {
                throw CatException("Build Project Dynamic type must be exist")
            }

            if (proj_id != null && uid != null && type != null) {
                dynamic.proj_id = proj_id ?: ""
                dynamic.uid = uid ?: ""
                dynamic.publisher = publisher
                dynamic.type = type
                dynamic.content = content ?: ""
                dynamic.img_url = img_url ?: "http://about:blank"
                dynamicRepository.save(dynamic)
                return dynamic
            } else
                throw CatException("Build Project Dynamic type must be exist")

        }
    }

}