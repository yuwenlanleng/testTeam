<%-- 
    Document   : index
    Created on : May 18, 2016, 5:06:47 PM
    Author     : 赵洪坤 <zhaohongkun@yzhtech.com>
--%>

<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName()
            + ":" + request.getServerPort() + path + "/";
%>
<%@ taglib uri="/struts-tags" prefix="s" %>
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en" lang="en">
    <head>
        <meta http-equiv="Content-type" content="text/html; charset=utf-8" />
        <meta http-equiv="X-UA-Compatible" content="IE=Edge，chrome=1">
            <meta name="viewport" content="width=device-width, initial-scale=1.0">
                <title>英智兴达内部共享页</title>
                <link rel="stylesheet" href="<%=basePath%>css/layout.css" type="text/css" media="screen" charset="utf-8" />
                <link rel="stylesheet" href="<%=basePath%>css/colour.css" type="text/css" media="screen" charset="utf-8" />
                <link rel="shortcut icon" type="image/x-icon" href="<%=basePath%>img/yz_web_logo.ico" media="screen" />
                </head>
                <body id="body">
                    <div id="conten_div">
                        <div id="head" class="head">英智兴达内部共享页</div>

                        <!--服务资源显示区-->
                        <div id="head2" class="head2"><s:property value="guideItemsWebs.{guideType}[0]"/></div>
                        <div id="content" class="container_16 clearfix content">
                            <div class="grid_16">
                                <table id="tab">
                                    <thead>
                                        <tr>
                                            <th id="th1id" class="th1">名称</th>
                                            <th id="th2id" class="th2">描述</th>
                                            <th id="th3id" class="th3">内网入口</th>
                                            <th class="th4">外网入口</th>
                                            <th id="th5id" class="th5">下载</th>
                                        </tr>
                                    </thead>
                                    <tbody>
                                        <s:iterator value="guideItemsWebs" var="gw">
                                            <tr>
                                                <td id="td1" class="td1">
                                                    <s:property value="#gw.name"/>
                                                </td>
                                                <td id="desid">
                                                    <s:property value="#gw.des"/>
                                                </td>
                                                <td id="innerid" align="center">
                                                    <s:if test="#gw.innerUrl != null and #gw.innerUrl != ''">
                                                        <a href="<s:property value="#gw.innerUrl"/>"  target="_blank">
                                                            <img  src="<%=basePath%>/img/mouseouticon.png" onmouseover="this.src = '<%=basePath%>/img/mouseonicon.png'" onmouseout="this.src = '<%=basePath%>/img/mouseouticon.png'"> 
                                                        </a>
                                                    </s:if>
                                                </td>
                                                <td id="outerid" align="center"> 
                                                    <s:if test="#gw.outerUrl != null and #gw.outerUrl != ''">
                                                        <a href="<s:property value="#gw.outerUrl"/>" target="_blank">
                                                            <img  src="<%=basePath%>/img/mouseouticon.png" onmouseover="this.src = '<%=basePath%>/img/mouseonicon.png'" onmouseout="this.src = '<%=basePath%>/img/mouseouticon.png'">
                                                        </a>
                                                    </s:if>
                                                </td>
                                                <td id="iosid" align="center">

                                                    <!--当三种链接中任意两种为空的情况一下，默认用同一种图标-->
                                                    <s:if test="(#gw.downloadUrlForIos == null or #gw.downloadUrlForIos == '') and (#gw.downloadUrlForAndroid == null or #gw.downloadUrlForAndroid == '') and (#gw.downloadUrlForPc != null and #gw.downloadUrlForPc != '')">
                                                        <a href="<s:property value="#gw.downloadUrlForPc"/>" target="_blank">
                                                            <img  src="<%=basePath%>/img/mobileouticon.png" onmouseover="this.src = '<%=basePath%>/img/mobileonicon.png'" onmouseout="this.src = '<%=basePath%>/img/mobileouticon.png'">
                                                        </a>
                                                    </s:if> 
                                                    <s:if test="(#gw.downloadUrlForIos == null or #gw.downloadUrlForIos == '') and (#gw.downloadUrlForAndroid != null and #gw.downloadUrlForAndroid != '') and (#gw.downloadUrlForPc == null or #gw.downloadUrlForPc == '')">
                                                        <a href="<s:property value="#gw.downloadUrlForAndroid"/>" target="_blank">
                                                            <img  src="<%=basePath%>/img/mobileouticon.png" onmouseover="this.src = '<%=basePath%>/img/mobileonicon.png'" onmouseout="this.src = '<%=basePath%>/img/mobileouticon.png'">
                                                        </a>
                                                    </s:if> 
                                                    <s:if test="(#gw.downloadUrlForIos != null and #gw.downloadUrlForIos != '') and (#gw.downloadUrlForAndroid == null or #gw.downloadUrlForAndroid == '') and (#gw.downloadUrlForPc == null or #gw.downloadUrlForPc == '')">
                                                        <a href="<s:property value="#gw.downloadUrlForIos"/>" target="_blank">
                                                            <img  src="<%=basePath%>/img/mobileouticon.png" onmouseover="this.src = '<%=basePath%>/img/mobileonicon.png'" onmouseout="this.src = '<%=basePath%>/img/mobileouticon.png'">
                                                        </a>
                                                    </s:if>

                                                    <!--三种链接中任意两种不为空，使用各自匹配的图标-->
                                                    <s:if test="#gw.downloadUrlForIos != null and #gw.downloadUrlForIos != '' and ((#gw.downloadUrlForAndroid != null and #gw.downloadUrlForAndroid != '') or (#gw.downloadUrlForPc != null and #gw.downloadUrlForPc != ''))">
                                                        <a href="<s:property value="#gw.downloadUrlForIos"/>" target="_blank">
                                                            <img id="hrefimg" src="<%=basePath%>/img/iosmouseouticon.png" onmouseover="this.src = '<%=basePath%>/img/iosmouseonicon.png'" onmouseout="this.src = '<%=basePath%>/img/iosmouseouticon.png'">
                                                        </a>
                                                    </s:if> 


                                                    <s:if test="#gw.downloadUrlForAndroid != null and #gw.downloadUrlForAndroid != '' and((#gw.downloadUrlForIos != null and #gw.downloadUrlForIos != '') or(#gw.downloadUrlForPc != null and #gw.downloadUrlForPc != ''))">
                                                        <a href="<s:property value="#gw.downloadUrlForAndroid"/>" target="_blank">
                                                            <img id="hrefimg" src="<%=basePath%>/img/androidmouseouticon.png" onmouseover="this.src = '<%=basePath%>/img/androidmouseonicon.png'" onmouseout="this.src = '<%=basePath%>/img/androidmouseouticon.png'">
                                                        </a>
                                                    </s:if> 

                                                    <s:if test="#gw.downloadUrlForPc != null and #gw.downloadUrlForPc != '' and ((#gw.downloadUrlForIos != null and #gw.downloadUrlForIos != '') or (#gw.downloadUrlForAndroid != null and #gw.downloadUrlForAndroid != ''))">
                                                        <a href="<s:property value="#gw.downloadUrlForPc"/>" target="_blank">
                                                            <img id="hrefimg" src="<%=basePath%>/img/pcmouseouticon.png" onmouseover="this.src = '<%=basePath%>/img/pcmouseonicon.png'" onmouseout="this.src = '<%=basePath%>/img/pcmouseouticon.png'">
                                                        </a>
                                                    </s:if> 
                                                </td>
                                            </tr>
                                        </s:iterator>

                                    </tbody>
                                </table>
                            </div>
                        </div>

                        <!--工具显示区-->
                        <div id="head3" class="head3"><s:property value="guideItemsSyss.{guideType}[0]"/></div>
                        <div id="content" class="container_16 clearfix content">
                            <div class="grid_16">
                                <table id="tab">
                                    <thead>
                                        <tr>
                                            <th id="th1id" class="th1">名称</th>
                                            <th id="th2id" class="th2">描述</th>
                                            <th id="th3id" class="th3">内网入口</th>
                                            <th class="th4">外网入口</th>
                                            <th id="th5id" class="th5">下载</th>
                                        </tr>
                                    </thead>
                                    <tbody>
                                        <s:iterator value="guideItemsSyss" var="gs">
                                            <tr>
                                                <td id="td1" class="td1">
                                                    <s:property value="#gs.name"/>
                                                </td>
                                                <td id="desid">
                                                    <s:property value="#gs.des"/>
                                                </td>
                                                <td id="innerid" align="center">
                                                    <s:if test="#gs.innerUrl != null and #gs.innerUrl != ''">
                                                        <a href="<s:property value="#gs.innerUrl"/>"  target="_blank">
                                                            <img  src="<%=basePath%>/img/mouseouticon.png" onmouseover="this.src = '<%=basePath%>/img/mouseonicon.png'" onmouseout="this.src = '<%=basePath%>/img/mouseouticon.png'">
                                                        </a>
                                                    </s:if>
                                                </td>
                                                <td id="outerid" align="center"> 
                                                    <s:if test="#gs.outerUrl != null and #gs.outerUrl != ''">
                                                        <a href="<s:property value="#gs.outerUrl"/>" target="_blank">
                                                            <img  src="<%=basePath%>/img/mouseouticon.png" onmouseover="this.src = '<%=basePath%>/img/mouseonicon.png'" onmouseout="this.src = '<%=basePath%>/img/mouseouticon.png'">
                                                        </a>
                                                    </s:if>
                                                </td>
                                                <td id="iosid" align="center">

                                                    <!--当三种链接中任意两种为空的情况一下，默认用同一种图标-->
                                                    <s:if test="(#gs.downloadUrlForIos == null or #gs.downloadUrlForIos == '') and (#gs.downloadUrlForAndroid == null or #gs.downloadUrlForAndroid == '') and (#gs.downloadUrlForPc != null and #gs.downloadUrlForPc != '')">
                                                        <a href="<s:property value="#gs.downloadUrlForPc"/>" target="_blank">
                                                            <img  src="<%=basePath%>/img/pcmouseouticon.png" onmouseover="this.src = '<%=basePath%>/img/pcmouseonicon.png'" onmouseout="this.src = '<%=basePath%>/img/pcmouseouticon.png'">
                                                        </a>
                                                    </s:if> 
                                                    <s:if test="(#gs.downloadUrlForIos != null or #gs.downloadUrlForIos != '') and (#gs.downloadUrlForAndroid != null and #gs.downloadUrlForAndroid != '') and (#gs.downloadUrlForPc == null or #gs.downloadUrlForPc == '')">
                                                        <a href="<s:property value="#gs.downloadUrlForAndroid"/>" target="_blank">
                                                            <img  src="<%=basePath%>/img/iosmouseouticon.png" onmouseover="this.src = '<%=basePath%>/img/iosmouseonicon.png'" onmouseout="this.src = '<%=basePath%>/img/iosmouseouticon.png'">
                                                        </a>
                                                        <a href="<s:property value="#gs.downloadUrlForIos"/>" target="_blank">
                                                            <img  src="<%=basePath%>/img/androidmouseouticon.png" onmouseover="this.src = '<%=basePath%>/img/androidmouseonicon.png'" onmouseout="this.src = '<%=basePath%>/img/androidmouseouticon.png'">
                                                        </a>
                                                    </s:if> 

                                                </td>
                                            </tr>
                                        </s:iterator>

                                    </tbody>
                                </table>
                            </div>
                        </div>

                        <!--常用文档显示区-->
                        <div id="head4" class="head4"><s:property value="guideItemsDocs.{guideType}[0]"/></div>
                        <div id="content" class="container_16 clearfix content">
                            <div class="grid_16">
                                <table id="tab">
                                    <thead>
                                        <tr>
                                            <th id="th1id" class="th1">名称</th>
                                            <th id="th2id" class="th2">描述</th>
                                            <th id="th3id" class="th3">内网入口</th>
                                            <th class="th4">外网入口</th>
                                            <th id="th5id" class="th5">下载</th>
                                        </tr>
                                    </thead>
                                    <tbody>
                                        <s:iterator value="guideItemsDocs" var="gd">
                                            <tr>
                                                <td id="td1" class="td1">
                                                    <s:property value="#gd.name"/>
                                                </td>
                                                <td id="desid">
                                                    <s:property value="#gd.des"/>
                                                </td>
                                                <td id="innerid" align="center">
                                                    <s:if test="#gd.innerUrl != null and #gd.innerUrl != ''">
                                                        <a href="<s:property value="#gd.innerUrl"/>"  target="_blank">
                                                            <img  src="<%=basePath%>/img/mouseouticon.png" onmouseover="this.src = '<%=basePath%>/img/mouseonicon.png'" onmouseout="this.src = '<%=basePath%>/img/mouseouticon.png'"> 
                                                        </a>
                                                    </s:if>
                                                </td>
                                                <td id="outerid" align="center"> 
                                                    <s:if test="#gd.outerUrl != null and #gd.outerUrl != ''">
                                                        <a href="<s:property value="#gd.outerUrl"/>" target="_blank">
                                                            <img  src="<%=basePath%>/img/mouseouticon.png" onmouseover="this.src = '<%=basePath%>/img/mouseonicon.png'" onmouseout="this.src = '<%=basePath%>/img/mouseouticon.png'">
                                                        </a>
                                                    </s:if>
                                                </td>
                                                <td id="iosid" align="center">

                                                    <!--当三种链接中任意两种为空的情况一下，默认用同一种图标-->
                                                    <s:if test="(#gd.downloadUrlForIos == null or #gd.downloadUrlForIos == '') and (#gd.downloadUrlForAndroid == null or #gd.downloadUrlForAndroid == '') and (#gd.downloadUrlForPc != null and #gd.downloadUrlForPc != '')">
                                                        <a href="<s:property value="#gd.downloadUrlForPc"/>" target="_blank">
                                                            <img  src="<%=basePath%>/img/mobileouticon.png" onmouseover="this.src = '<%=basePath%>/img/mobileonicon.png'" onmouseout="this.src = '<%=basePath%>/img/mobileouticon.png'">
                                                        </a>
                                                    </s:if> 
                                                    <s:if test="(#gd.downloadUrlForIos == null or #gd.downloadUrlForIos == '') and (#gd.downloadUrlForAndroid != null and #gd.downloadUrlForAndroid != '') and (#gd.downloadUrlForPc == null or #gd.downloadUrlForPc == '')">
                                                        <a href="<s:property value="#gd.downloadUrlForAndroid"/>" target="_blank">
                                                            <img  src="<%=basePath%>/img/mobileouticon.png" onmouseover="this.src = '<%=basePath%>/img/mobileonicon.png'" onmouseout="this.src = '<%=basePath%>/img/mobileouticon.png'">
                                                        </a>
                                                    </s:if> 
                                                    <s:if test="(#gd.downloadUrlForIos != null and #gd.downloadUrlForIos != '') and (#gd.downloadUrlForAndroid == null or #gd.downloadUrlForAndroid == '') and (#gd.downloadUrlForPc == null or #gd.downloadUrlForPc == '')">
                                                        <a href="<s:property value="#gd.downloadUrlForIos"/>" target="_blank">
                                                            <img  src="<%=basePath%>/img/mobileouticon.png" onmouseover="this.src = '<%=basePath%>/img/mobileonicon.png'" onmouseout="this.src = '<%=basePath%>/img/mobileouticon.png'">
                                                        </a>
                                                    </s:if>

                                                    <!--三种链接中任意两种不为空，使用各自匹配的图标-->
                                                    <s:if test="#gd.downloadUrlForIos != null and #gd.downloadUrlForIos != '' and ((#gd.downloadUrlForAndroid != null and #gd.downloadUrlForAndroid != '') or (#gd.downloadUrlForPc != null and #gd.downloadUrlForPc != ''))">
                                                        <a href="<s:property value="#gd.downloadUrlForIos"/>" target="_blank">
                                                            <img id="hrefimg" src="<%=basePath%>/img/iosmouseouticon.png" onmouseover="this.src = '<%=basePath%>/img/iosmouseonicon.png'" onmouseout="this.src = '<%=basePath%>/img/iosmouseouticon.png'">
                                                        </a>
                                                    </s:if> 


                                                    <s:if test="#gd.downloadUrlForAndroid != null and #gd.downloadUrlForAndroid != '' and((#gd.downloadUrlForIos != null and #gd.downloadUrlForIos != '') or(#gd.downloadUrlForPc != null and #gd.downloadUrlForPc != ''))">
                                                        <a href="<s:property value="#gd.downloadUrlForAndroid"/>" target="_blank">
                                                            <img id="hrefimg" src="<%=basePath%>/img/androidmouseouticon.png" onmouseover="this.src = '<%=basePath%>/img/androidmouseonicon.png'" onmouseout="this.src = '<%=basePath%>/img/androidmouseouticon.png'">
                                                        </a>
                                                    </s:if> 

                                                    <s:if test="#gd.downloadUrlForPc != null and #gd.downloadUrlForPc != '' and ((#gd.downloadUrlForIos != null and #gd.downloadUrlForIos != '') or (#gd.downloadUrlForAndroid != null and #gd.downloadUrlForAndroid != ''))">
                                                        <a href="<s:property value="#gd.downloadUrlForPc"/>" target="_blank">
                                                            <img  id="hrefimg" src="<%=basePath%>/img/pcmouseouticon.png" onmouseover="this.src = '<%=basePath%>/img/pcmouseonicon.png'" onmouseout="this.src = '<%=basePath%>/img/pcmouseouticon.png'">
                                                        </a>
                                                    </s:if> 
                                                </td>
                                            </tr>
                                        </s:iterator>

                                    </tbody>
                                </table>
                            </div>
                        </div>

                        <!--显示底部背景图片-->
                        <br id="brid"><br id="brid">                  
                                </div>
                                <br id="brid"> <br id="brid">
                                        </body>
                                        </html>
