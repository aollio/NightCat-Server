package com.yemao.projects;


import com.yemao.projects.models.Project;
import com.yemao.projects.models.Project.*;

public class ProjectUtil {


    public static boolean employerCancel(Project project) {
        Status status = project.getStatus();
        switch (status) {
            case Publish:
                return true;

            case ConfirmDesigner_WaitDesignerConfirm:
                return true;

            case DesignerConfirm_WaitModify:
                return true;

            case DesignerModify_WaitPay:
                return true;

            case EmployerHarvest_WaitComment:
                return false;

            case PayComplete_WaitDesign:
                return false;
            case DesignComplete_WaitHarvest:
                return false;
            case Complete:
                return false;

            case Platform_InterPose:
                return false;
            case Cancel:
                return false;
            default:
                return false;
        }
    }

    public static boolean designerCancel(Project project) {
        return false;
    }
}
