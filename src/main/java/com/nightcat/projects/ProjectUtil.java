package com.nightcat.projects;

import com.nightcat.entity.Project;

public class ProjectUtil {


    public static boolean employerCancel(Project project) {
        Project.Status status = project.getStatus();
        switch (status) {
            case Publish:
                return true;

            case ConfirmDesigner_WaitDesignerConfirm:
                return true;

            case DesignerConfirm_WaitModify:
                return true;

            case PayComplete_WaitDesign:
                return false;
            case DesignComplete_WaitComment:
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
