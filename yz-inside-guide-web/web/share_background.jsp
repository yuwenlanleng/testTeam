<%-- 
    Document   : index
    Created on : Sep 21, 2016, 5:06:47 PM
    Author     : 彭锐锐 <zhaohongkun@yzhtech.com>
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
<meta content="jQuery" name="keywords" />
<meta content="jQuery弹出层效果" name="description" />
<script src="/uploads/common/js/jquery-1.4.2.min.js" type="text/javascript"></script>
<link rel="stylesheet" href="<%=basePath%>css/myLayout.css" type="text/css" media="screen" charset="utf-8" />
<link rel="stylesheet" href="<%=basePath%>css/colour.css" type="text/css" media="screen" charset="utf-8" />
<link rel="shortcut icon" type="image/x-icon" href="<%=basePath%>img/yz_web_logo.ico" media="screen" />
<script type="text/javascript"> 
//弹出隐藏层
function ShowDiv(show_div,bg_div){
document.getElementById(show_div).style.display='block';
document.getElementById(bg_div).style.display='block';
document.getElementById("body").style.overflow='hidden';
scroll(show_div);
var bgdiv = document.getElementById(bg_div);
var showdiv=document.getElementById(show_div);
bgdiv.style.width = document.body.scrollWidth;
 bgdiv.style.height = $(document).height();
$("#"+bg_div).height($(document).height());
};
//关闭弹出层
function CloseDiv(show_div,bg_div)
{
document.getElementById(show_div).style.display='none';
document.getElementById(bg_div).style.display='none';
document.getElementById("body").style.overflow='scroll';
};

</script>   
</head>
<body id="body">
    
<div id="home">                     
<table>                          
<tr>
<td class="head" colspan="2">                             
<img  src="<%=basePath%>/img/insideguidelogo.png" >英智兴达内部共享页                         
</td>
</tr>  
                     
    <!--服务资源显示区-->
<tr>
<td class="left">                                                                
    <div id="model">
    <img  src="<%=basePath%>/img/通用服务网站.png" ><br/>
    <span id="stitle"><s:property value="guideItemsWebs.{guideType}[0]"/></span>
    </div>  
</td>                                                       
<td class="right">   
        <s:iterator value="guideItemsWebs" var="gw">
        <div id="box">
          <p>
             <div id="before">
             <div id="picture">
                  <img id="images" style="width:100%;height:100%" src="<%=basePath %>showPicture.action?uploadPicture=<s:property value="#gw.uploadPicture"/>">                           
             </div>
              <div id="td1">   <s:property value="#gw.name"/><br/></div>
              <div id="desid">   <s:property value="#gw.des"/><br/></div>
              </div>
              <div id="after">
              <div id="one">
                <span id="innerid"> <s:if test="#gw.innerUrl != null and #gw.innerUrl != ''">
                    <a href="<s:property value="#gw.innerUrl"/>"  target="_blank">
                    <img  id="inimage"src="<%=basePath%>/img/点击状态.png" onmouseover="this.src = '<%=basePath%>/img/入口.png'" onmouseout="this.src = '<%=basePath%>/img/点击状态.png'"><br/>
                    <span style="font-size:11px;color:white ">内网入口</span>
                    </a>
                    </s:if>
               </span>
              </div>
              <div id="two"></div>    
              <div id="three">
                <span id="outerid">  
                <s:if test="#gw.outerUrl != null and #gw.outerUrl != ''">
                <a href="<s:property value="#gw.outerUrl"/>" target="_blank">
                    <img  src="<%=basePath%>/img/点击状态.png" onmouseover="this.src = '<%=basePath%>/img/入口.png'" onmouseout="this.src = '<%=basePath%>/img/点击状态.png'"><br/>
                        <span style="font-size:11px;color:white ">外网入口</span>
                         </a><br/>
                </s:if>
                <s:else>
                 <a target="_blank">
                    <img  src="<%=basePath%>/img/暂无.png"><br/>
                        <span style="font-size:11px;color:white ">外网入口</span>
                         </a><br/>                        
                </s:else>         
                </span>
              </div>
              </div>  
         </div>             
    </s:iterator>  
    
</td>
</tr>                                                                  
  
    <!--管理系统显示区-->           
<tr>
<td  class="left">
    <div id="model">
    <img  src="<%=basePath%>/img/工具.png" ><br/>
    <span id="stitle"><s:property value="guideItemsSyss.{guideType}[0]"/></span>
    </div>
    </td>
    <td class="right">
        <s:iterator value="guideItemsSyss" var="gs">
        <div id="box">
        <p>
        <div id="before">
        <div id="picture">
          <img id="images" style="width:100%;height:100%" src="<%=basePath %>showPicture.action?uploadPicture=<s:property value="#gs.uploadPicture"/>"> 
        </div>
        <div id="td1">   <s:property value="#gs.name"/><br/></div>
        <div id="desid">   <s:property value="#gs.des"/><br/></div>
         </div>
         <div id="after">
         <div id="one1">
         <span id="innerid"> 
         <s:if test="#gs.innerUrl != null and #gs.innerUrl != ''">
         <a href="<s:property value="#gs.innerUrl"/>"  target="_blank">
         <img  src="<%=basePath%>/img/点击状态.png" onmouseover="this.src = '<%=basePath%>/img/入口.png'" onmouseout="this.src = '<%=basePath%>/img/点击状态.png'"><br/>
         <span style="font-size:13px;color:white ">内网</span>
         </a>
          </s:if>
         </span>
         </div>
         <div id="two1"></div>
         <div id="three1">
          <span id="outerid">
          <s:if test="#gs.outerUrl != null and #gs.outerUrl != ''">
            <a href="<s:property value="#gs.outerUrl"/>" target="_blank">   
            <img  src="<%=basePath%>/img/点击状态.png" onmouseover="this.src = '<%=basePath%>/img/入口.png'" onmouseout="this.src = '<%=basePath%>/img/点击状态.png'"><br/>
            <span style="font-size:13px;color:white ">外网</span>
            </a>
          </s:if>
          <s:else>
                 <a target="_blank">
                 <img  src="<%=basePath%>/img/暂无.png"><br/>
                 <span style="font-size:11px;color:white ">外网入口</span>
                 </a><br/>                        
          </s:else>     
          </span>
          </div>
          <div id="two1"></div>
          <div id="four1">
          <span id="iosid">   
               <!--无下载路径-->
               <s:if test="(#gs.downloadUrlForIos == null or #gs.downloadUrlForIos == '') and (#gs.downloadUrlForAndroid == null or #gs.downloadUrlForAndroid == '') and (#gs.downloadUrlForPc == null or #gs.downloadUrlForPc == '')">
                <a href="<s:property value="#gs.downloadUrlForPc"/>" target="_blank">
                    <img  src="<%=basePath%>/img/暂无.png" >
                        <br/><span style="font-size:13px;color:white ">下载</span>
                </a>
            </s:if> 
               <!--1个下载路径-->
               <s:elseif test="(#gs.downloadUrlForIos == null or #gs.downloadUrlForIos == '') and (#gs.downloadUrlForAndroid == null or #gs.downloadUrlForAndroid == '') and (#gs.downloadUrlForPc != null or #gs.downloadUrlForPc != '')">
                <a href="<s:property value="#gs.downloadUrlForPc"/>" target="_blank">
                    <img  src="<%=basePath%>/img/下载点击状态.png" onmouseover="this.src = '<%=basePath%>/img/下载.png'" onmouseout="this.src = '<%=basePath%>/img/下载点击状态.png'">
                        <br/><span style="font-size:13px;color:white ">下载</span>
                </a>
            </s:elseif> 
               <s:elseif test="(#gs.downloadUrlForIos == null or #gs.downloadUrlForIos == '') and (#gs.downloadUrlForAndroid != null or #gs.downloadUrlForAndroid != '') and (#gs.downloadUrlForPc == null or #gs.downloadUrlForPc == '')">
                <a href="<s:property value="#gs.downloadUrlForAndroid"/>" target="_blank">
                    <img  src="<%=basePath%>/img/下载点击状态.png" onmouseover="this.src = '<%=basePath%>/img/下载.png'" onmouseout="this.src = '<%=basePath%>/img/下载点击状态.png'">
                     <br/><span style="font-size:13px;color:white ">下载</span>
                </a>               
            </s:elseif>
               <s:elseif test="(#gs.downloadUrlForIos != null or #gs.downloadUrlForIos != '') and (#gs.downloadUrlForAndroid == null or #gs.downloadUrlForAndroid == '') and (#gs.downloadUrlForPc == null or #gs.downloadUrlForPc == '')">
                <a href="<s:property value="#gs.downloadUrlForIos"/>" target="_blank">
                    <img  src="<%=basePath%>/img/下载点击状态.png" onmouseover="this.src = '<%=basePath%>/img/下载.png'" onmouseout="this.src = '<%=basePath%>/img/下载点击状态.png'">
                     <br/><span style="font-size:13px;color:white ">下载</span>
                </a>               
            </s:elseif> 
               <s:elseif test="(#gs.downloadUrlForIos == null and #gs.downloadUrlForIos == '') and (#gs.downloadUrlForAndroid == null and #gs.downloadUrlForAndroid == '') and (#gs.downloadUrlForPc == null and #gs.downloadUrlForPc == '')">
                 <img  src="<%=basePath%>/img/暂无.png">
                 <br/><span style="font-size:13px;color:white ">下载</span>
            </s:elseif> 
              <!--多个下载路径-->
             <s:else>
              <a onclick="ShowDiv('${gs.name}','${gs.id}')" target="_blank">
              <img  src="<%=basePath%>/img/下载点击状态.png" onmouseover="this.src = '<%=basePath%>/img/下载.png'" onmouseout="this.src = '<%=basePath%>/img/下载点击状态.png'"><br/>
              <span style="font-size:13px;color:white ">下载</span>
              </a>   
             </s:else>
        </span>    
         </div>
         </div>         
         </p>
          </div>
              <!--弹出层时背景层DIV-->
              <div id="${gs.id}" class="black_overlay"></div>  
              <table id="${gs.name}" class="white_content">
                  <tr>
                      <td id="backfont" colspan="2">&nbsp; &nbsp; &nbsp;下载渠道</td>
                      <td id="closeimg">  <img  src="<%=basePath%>/img/办公网切图关闭.png" onclick="CloseDiv('${gs.name}','${gs.id}')"/>&nbsp; &nbsp; &nbsp; </td>            
                  </tr>
                  <tr>
                      <td style="border-top:1px solid lightgrey;" colspan="3"></td>
                  </tr>   
                      <!--2个下载路径-->
<s:if test="(#gs.downloadUrlForIos == null or #gs.downloadUrlForIos == '') and (#gs.downloadUrlForAndroid != null or #gs.downloadUrlForAndroid != '') and (#gs.downloadUrlForPc != null or #gs.downloadUrlForPc != '')">
    <tr>
        <td style="height:300px;width:50%">
             <a href="<s:property value="#gs.downloadUrlForPc"/>" target="_blank">                  
                    <img  src="<%=basePath%>/img/办公网切图桌面.png" ><br/>
                    <br/>
                    <span class="myButton">直接下载</span>
             </a> 
         </td>
                    <td>&nbsp;</td>
         <td style="height:300px;width:50%">      
             <a href="<s:property value="#gs.downloadUrlForAndroid"/>" target="_blank">
                <img  src="<%=basePath%>/img/办公网切图安卓.png"/><br/>
                    <br/> 
                    <span class="myButton">Android下载</span>
             </a>                  
       </td>     
    </tr>
</s:if> 
<s:if test="(#gs.downloadUrlForIos != null or #gs.downloadUrlForIos != '') and (#gs.downloadUrlForAndroid != null or #gs.downloadUrlForAndroid != '') and (#gs.downloadUrlForPc == null or #gs.downloadUrlForPc == '')">
    <tr>
        <td style="height:300px;width:50%">
            <a href="<s:property value="#gs.downloadUrlForIos"/>" target="_blank">
                <img  src="<%=basePath%>/img/办公网切图苹果.png"/> <br/>
                    <br/>
                    <span class="myButton">App store下载</span>
             </a> 
        </td>
                    <td>&nbsp;</td>
        <td style="height:300px;width:50%">            
             <a href="<s:property value="#gs.downloadUrlForAndroid"/>" target="_blank">
                <img  src="<%=basePath%>/img/办公网切图安卓.png"/><br/>
                    <br/> 
                    <span class="myButton">Android下载</span>
             </a>       
        </td>
    </tr>
</s:if> 
<s:if test="(#gs.downloadUrlForIos != null or #gs.downloadUrlForIos != '') and (#gs.downloadUrlForAndroid == null or #gs.downloadUrlForAndroid == '') and (#gs.downloadUrlForPc != null or #gs.downloadUrlForPc != '')">
    <tr>
        <td style="height:300px;width:50%">
            <a href="<s:property value="#gs.downloadUrlForIos"/>" target="_blank">
                <img  src="<%=basePath%>/img/办公网切图苹果.png"/> <br/>
                    <br/>
                    <span class="myButton">App store下载</span>
             </a>  
        </td>
                    <td>&nbsp;</td>
        <td style="height:300px;width:50%">
             <a href="<s:property value="#gs.downloadUrlForPc"/>" target="_blank">                  
                    <img  src="<%=basePath%>/img/办公网切图桌面.png" ><br/>
                    <br/>
                    <span class="myButton">直接下载</span>
             </a>      
        </td>
    </tr>
</s:if>  
                      <!--3个下载路径-->
<s:if test="(#gs.downloadUrlForIos != null and #gs.downloadUrlForIos != '') and (#gs.downloadUrlForAndroid != null and #gs.downloadUrlForAndroid != '') and (#gs.downloadUrlForPc != null and #gs.downloadUrlForPc != '')">
 <tr>
        <td style="height:300px;width:33%">
                <a href="<s:property value="#gs.downloadUrlForPc"/>" target="_blank">                  
                    <img  src="<%=basePath%>/img/办公网切图桌面.png" ><br/>
                    <br/>
                    <span class="myButton">直接下载</span>
                </a>                                           
        </td>
         <td style="height:300px;width:34%">
             <a href="<s:property value="#gs.downloadUrlForIos"/>" target="_blank">
                <img  src="<%=basePath%>/img/办公网切图苹果.png"/> <br/>
                    <br/>
                    <span class="myButton">App store下载</span>
             </a>       
        </td>            
        <td style="height:300px;width:33%">
                <a href="<s:property value="#gs.downloadUrlForAndroid"/>" target="_blank">
                <img  src="<%=basePath%>/img/办公网切图安卓.png"/><br/>
                    <br/> 
                    <span class="myButton">Android下载</span>
                </a>
         </td>    
    </tr>
    
</s:if>          
              </table>      
             <div id="${gs.id}" class="black_overlay"></div>  
        
          </s:iterator>
           </td>
                                                                                                                                                                                                                              
</tr>
  
    <!--常用文档显示区-->                                                                                            
<tr  style="border-radius:0 0 8px 8px">
<td  class="left">
    <div id="model">
    <img  src="<%=basePath%>/img/常用文档.png" ><br/>
    <span id="stitle"><s:property value="guideItemsDocs.{guideType}[0]"/></span>
    </div>  
</td>
<td class="right">
    <s:iterator value="guideItemsDocs" var="gd">
    <div id="box">
        <p>
    <div id="before">
    <div id="picture">
    <img id="images" style="width:100%;height:100%" src="<%=basePath %>showPicture.action?uploadPicture=<s:property value="#gd.uploadPicture"/>"> 
    </div>
  <div id="td1">   <s:property value="#gd.name"/><br/></div>
  <div id="desid">   <s:property value="#gd.des"/><br/></div>
  </div>

<div id="after" >
    <div id="one">
    <span id="innerid">
    <s:if test="#gd.innerUrl != null and #gd.innerUrl != ''">
    <a href="<s:property value="#gd.innerUrl"/>"  target="_blank">
    <img  src="<%=basePath%>/img/点击状态.png" onmouseover="this.src = '<%=basePath%>/img/入口.png'" onmouseout="this.src = '<%=basePath%>/img/点击状态.png'"><br/>
    <span style="font-size:11px;color:white ">内网入口</span>
    </a>
    </s:if>
    </span>
    </div>
    <div id="two"></div>    
    <div id="three">
    <span id="outerid">  
    <s:if test="#gd.outerUrl != null and #gd.outerUrl != ''">
      <a href="<s:property value="#gd.outerUrl"/>" target="_blank">
        <img  src="<%=basePath%>/img/点击状态.png" onmouseover="this.src = '<%=basePath%>/img/入口.png'" onmouseout="this.src = '<%=basePath%>/img/点击状态.png'"><br/>
            <span style="font-size:11px;color:white ">外网入口</span>
      </a><br/>
    </s:if>
    <s:else>
    <a target="_blank">
    <img  src="<%=basePath%>/img/暂无.png"><br/>
    <span style="font-size:11px;color:white ">外网入口</span>
    </a><br/>                        
    </s:else>   
    </span>
    </div>

</div>   

  </p>
</div>

</s:iterator>       

</td>
</tr>    
    
</table>                                                                                                                              
</div>    
    
</body>
</html>


