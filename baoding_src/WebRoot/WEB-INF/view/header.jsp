<%@ page language="java" import="java.util.*"  pageEncoding="UTF-8"%>
 <div class="top">
     <div class="top-rt clearfix">
         <div class="weather fl">
             &nbsp;<img width="25px" height="18px" src="../comm/image/xiaoyu.png" style="vertical-align:middle;">&nbsp;<font class="red">22 </font>~<font class="green"> 19℃</font>&nbsp; 北风微风
         </div>
         <div class="date fl">
                                   今天是2016年09月05日    星期一
         </div>
         <div class="menu">
             <a href="<%=request.getContextPath()%>/view/index" class="sy">首页</a>
             <a href="<%=request.getContextPath()%>/contact_us/way" class="lx">联系我们</a>
         </div>

     </div>
     <div class="top-tc clearfix">
         <a href="javascript:;"  class="logo fl">
             <i></i>
             <b>陕西宝鼎融资担保</b>
             <em>SHANXIBAODINGRONGZIDANBAO</em>
             <!--<img src="image/logo.png" style="width: 376px"/>-->
         </a>
         <div class="nav clearfix">
             <div class="nav-list" id="navlist">
                 <ul class="clearfix">
                     <li class="item01"><a href="../about_us/information">关于我们</a></li>
                     <li class="item02"><a href="../news_center/company">新闻中心</a></li>
                     <li class="item03"><a href="../busi_introduction/financial_guarantee">业务介绍</a></li>
                     <li class="item04"><a href="../busi_process/process">业务流程</a></li>
                     <li class="item05"><a href="../busi_apply/apply">业务申请</a></li>
                     <li class="item06"><a href="../policy/policy" class="">政策法规</a></li>
                 </ul>
             </div>
             <div class="nav-box" id="navbox" style="height: 0px; opacity: 0.5; overflow: hidden;">
                 <div class="cont" style="display: none; height: 36px; padding-left: 285px;">
                     <a href="../about_us/information">公司概况</a>
                     <a href="../about_us/culture">企业文化</a>
                     <a href="../about_us/run">经营范围</a>
                     <a href="../about_us/team">管理团队</a>
                     <a href="../about_us/brand">品牌优势</a>
                 </div>
                 <div class="cont" style="display: none; height: 36px; padding-left: 460px;">
                     <a href="../news_center/company">公司新闻</a>
                     <a href="../news_center/trend">行业动态</a>
                     <a href="../news_center/staff">员工风采</a>
                 </div>
                 <div class="cont" style="display: none; height: 36px; padding-left: 560px;">
                     <a href="../busi_introduction/financial_guarantee">融资担保</a>
                     <a href="../busi_introduction/preservation_lawsuit">诉讼保全</a>
                     <a href="../busi_introduction/performance_guarantee">履约担保</a>
                     <a href="../busi_introduction/financial_consult">融资咨询</a>
                 </div>
                 <div class="cont" style="display: none; height: 36px; padding-left: 650px;">
                     <a href="../busi_process/process">业务流程</a>
                 </div>
                 <div class="cont" style="display: none; height: 36px; padding-left: 760px;">
                     <a href="../busi_apply/apply">业务申请</a>
                 </div>
                 <div class="cont" style="height: 36px; padding-left: 845px; display: none;">
                     <a href="../policy/policy">政策法规</a>
                 </div>
             </div>
         </div>
     </div>
 </div>
