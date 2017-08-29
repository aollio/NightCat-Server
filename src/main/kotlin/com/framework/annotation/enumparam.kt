package com.framework.annotation

import com.yemao.common.CatException
import com.yemao.common.base.BaseObject
import org.springframework.core.MethodParameter
import org.springframework.stereotype.Component
import org.springframework.web.bind.support.WebDataBinderFactory
import org.springframework.web.context.request.NativeWebRequest
import org.springframework.web.method.support.HandlerMethodArgumentResolver
import org.springframework.web.method.support.ModelAndViewContainer
import kotlin.annotation.AnnotationTarget.VALUE_PARAMETER
import com.yemao.common.constant.HttpStatus.*

/**
 * @author Aollio
 * @date 15/05/2017
 */
@Target(VALUE_PARAMETER)
@Retention(AnnotationRetention.RUNTIME)
annotation class EnumParam(val defaultValue: Int = 0, val required: Boolean = true)

@Component
class EnumParamMethodArgumentResolver :
        BaseObject(), HandlerMethodArgumentResolver {

    override fun supportsParameter(parameter: MethodParameter?): Boolean {
        return parameter
                ?.hasParameterAnnotation(EnumParam::class.java) == true
    }

    override fun resolveArgument(parameter: MethodParameter?,
                                 mavContainer: ModelAndViewContainer?,
                                 webRequest: NativeWebRequest?,
                                 binderFactory: WebDataBinderFactory?): Any {
        val name: String? = parameter?.parameterName
        logger.info("开始执行方法参数解析: $name")
        var origin = -1
        try {
            origin = webRequest?.getParameter(name)?.toInt() ?: -1
        } catch (e: Exception) {
            throw CatException(BAD_REQUEST, "the param $name not exist or is a wrong number")
        }

        val enum: Class<*> = parameter!!.parameterType
        enum.enumConstants
                .filter { (it as Enum<*>).ordinal == origin }
                .forEach { return it }
        //mismatch enum and origin
        val enum_param = parameter.getParameterAnnotation(EnumParam::class.java)

        if (enum_param.required) {
            throw CatException(BAD_REQUEST,
                    """the param $name has a wrong enum type origin value.
                        | Enum :${parameter.parameterType.canonicalName}""".trimMargin())
        }

        try {
            return enum.enumConstants
                    .filter { (it as Enum<*>).ordinal == enum_param.defaultValue }
                    .forEach { return it }
        } catch (e: Exception) {
            throw CatException(BAD_REQUEST,
                    """the param $name has a wrong defaultValue param origin value.
                        | Enum :${parameter.parameterType.canonicalName}""".trimMargin())
        }
    }
}