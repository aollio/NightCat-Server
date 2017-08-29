package com.yemao.projects.util

import com.yemao.projects.models.Project
import com.yemao.projects.models.Status


object ProjectUtil {


    fun employerCancel(project: Project): Boolean {
        val status = project.status
        when (status) {
            Status.Publish -> return true

            Status.ConfirmDesigner_WaitDesignerConfirm -> return true

            Status.DesignerConfirm_WaitModify -> return true

            Status.DesignerModify_WaitPay -> return true

            Status.EmployerHarvest_WaitComment -> return false

            Status.PayComplete_WaitDesign -> return false
            Status.DesignComplete_WaitHarvest -> return false
            Status.Complete -> return false

            Status.Platform_InterPose -> return false
            Status.Cancel -> return false
            else -> return false
        }
    }

    fun designerCancel(project: Project): Boolean {
        return false
    }
}