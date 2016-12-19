/*
 * UserPermission.java
 * 
 * Copyright(c) 2007-2013 by Yingzhi Tech
 * All Rights Reserved
 * 
 * Created at 2013-08-20 19:31:09
 */
package com.nazca.inside.guide.common.enums;

/**
 *
 * @author Zhang Chun Nan
 */
public enum UserPermission {

    browseUndergraduate() {
                @Override
                public String toString() {
                    return "浏览本科生";
                }
            },
    browsePostgraduate() {
                @Override
                public String toString() {
                    return "浏览研究生";
                }
            },
    academyAudit() {
                @Override
                public String toString() {
                    return "院系审核";
                }
            },
    officeAudit() {
                @Override
                public String toString() {
                    return "就业中心审核";
                }
            },
    modifyStudent() {
                @Override
                public String toString() {
                    return "修改学生信息";
                }
            },
    modifyAcademyMemo() {
                @Override
                public String toString() {
                    return "修改院系备注";
                }
            },
    breachMgmt() {
                @Override
                public String toString() {
                    return "违约信息管理";
                }
            },
    importStudent() {
                @Override
                public String toString() {
                    return "导入考生信息";
                }
            },
    exportStudent() {
                @Override
                public String toString() {
                    return "导出考生信息";
                }
            },
    batchModifyStudent() {
                @Override
                public String toString() {
                    return "批量修改学生";
                }
            },
    browseSurveyStat() {
                @Override
                public String toString() {
                    return "查看问卷统计";
                }
            },
    systemMgmt() {
                @Override
                public String toString() {
                    return "系统管理";
                }
            },
    dispatchMgmt() {
                @Override
                public String toString() {
                    return "派遣核对管理";
                }
            },
    dispatchTaskBrowse() {
                @Override
                public String toString() {
                    return "派遣核对任务浏览";
                }
            },
    dispatchTaskManage() {
                @Override
                public String toString() {
                    return "派遣核对任务管理";
                }
            },
    dispatchTaskAudit() {
                @Override
                public String toString() {
                    return "派遣核对审核";
                }
            },
    articleManage() {

                @Override
                public String toString() {
                    return "可访问信息管理";
                }

            },
    articleInfoView() {

                @Override
                public String toString() {
                    return "查看信息内容";
                }

            },
    articleInfoMgmt() {

                @Override
                public String toString() {
                    return "管理信息内容";
                }

            },
    linksView() {

                @Override
                public String toString() {
                    return "查看常用链接";
                }

            },
    linksMgmt() {

                @Override
                public String toString() {
                    return "管理常用链接";
                }

            },
    studentStatInfoManage() {

                @Override
                public String toString() {
                    return "可访问生源信息管理";
                }
            },
    studentStatInfoMgmt() {

                @Override
                public String toString() {
                    return "维护生源信息";
                }
            },
    academyInfoManage() {

                @Override
                public String toString() {
                    return "可访问院系信息管理";
                }

            },
    academyInfoView() {

                @Override
                public String toString() {
                    return "查看院系信息";
                }

            },
    academyInfoMgmt() {

                @Override
                public String toString() {
                    return "管理院系信息";
                }

            },
    recruitmentInfoManage() {

                @Override
                public String toString() {
                    return "可访问招聘信息管理";
                }

            },
    recruitmentInfoView() {

                @Override
                public String toString() {
                    return "查看招聘信息";
                }

            },
    recruitmentInfoMgmt() {

                @Override
                public String toString() {
                    return "管理招聘信息";
                }

            },
    careerTalkManage() {

                @Override
                public String toString() {
                    return "可访问宣讲会管理";
                }

            },
    careerTalkView() {

                @Override
                public String toString() {
                    return "查看宣讲会";
                }

            },
    careerTalkMgmt() {

                @Override
                public String toString() {
                    return "管理宣讲会";
                }

            },
    jobfairInfoManage() {

                @Override
                public String toString() {
                    return "可访问招聘会管理";
                }

            },
    jobfairInfoView() {

                @Override
                public String toString() {
                    return "查看招聘会";
                }

            },
    jobfairInfoMgmt() {

                @Override
                public String toString() {
                    return "管理招聘会";
                }

            },
    consultManage() {

                @Override
                public String toString() {
                    return "可访问预约咨询管理";
                }

            },
    consultView() {

                @Override
                public String toString() {
                    return "查看预约咨询";
                }

            },
    consultMgmt() {

                @Override
                public String toString() {
                    return "管理预约咨询";
                }

            },
    enterpriseManage() {

                @Override
                public String toString() {
                    return "可访问企业管理";
                }

            },
    enterpriseInfoView() {

                @Override
                public String toString() {
                    return "查看企业信息";
                }

            },
    enterpriseInfoMgmt() {

                @Override
                public String toString() {
                    return "管理企业信息";
                }

            },
    enterpriseUserMgmt() {

                @Override
                public String toString() {
                    return "管理企业用户";
                }

            },
    orgInfoVerifyView() {

                @Override
                public String toString() {
                    return "查看企业审核管理";
                }

            },
    authOrgInfoVerify() {

                @Override
                public String toString() {
                    return "审核企业信息";
                }
            },
    downloadManage() {

                @Override
                public String toString() {
                    return "可访问下载中心";
                }

            },
    downloadView() {

                @Override
                public String toString() {
                    return "查看下载中心";
                }

            },
    downloadMgmt() {

                @Override
                public String toString() {
                    return "管理下载中心";
                }

            },
    studentChangeDispatchManage() {

                @Override
                public String toString() {
                    return "可访问改派信息";
                }

            },
    studentChangeDispatchView() {

                @Override
                public String toString() {
                    return "查看改派信息";
                }

            },
    studentChangeDispatchMgmt() {

                @Override
                public String toString() {
                    return "管理改派信息";
                }

            },
    notifyManage() {

                @Override
                public String toString() {
                    return "可访问短消息";
                }

            },
}
