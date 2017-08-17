<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@page import="listener.Data"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html lang="zh-CN">
  <head>
    <base href="<%=basePath%>">
    
    <title>车牌生成系统</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	  <link rel="stylesheet" type="text/css" href="./css/bootstrap.min.css">
	 <style type="text/css">
	 .styled-select select {
   background: transparent;
   width:100px;
   padding: 5px;
   font-size: 16px;
   font-family:'微软雅黑';
   border: 1px solid #ccc;
   height: 34px;
   -webkit-appearance: none; /*for chrome*/
   
}
</style>

<script type="text/javascript"> 
                        //XMLHttpRequest组件
var xhs;
//区域菜单的值发生改变时调用该方法,并把区域菜单当前的value传递进来
function cascade(province){
        //请求字符串,把区域的id作为页面参数传到后台
        var url="/plateNumberGenerator/ChangeAreaServlet?province="+province;
        //创建XMLHttpRequest组件
        xhs=new XMLHttpRequest();
        //设置回调函数,processReuqest方法的定义在下面
        xhs.onreadystatechange=processReuqest;
        //打开与服务器的地址连接
        xhs.open("post", url, true);
        //发送请求
        xhs.send(null);
}

//processReuqest方法作为回调方法
function processReuqest(){
    if(xhs.readyState==4){
        if(xhs.status==200){
            //创建新的select节点
            var newSelect=document.createElement("select");
            newSelect.id="area";
              newSelect.name="area";
            //为新创建的select节点添加option节点
            var op=document.createElement("option");
            op.value='*';
            op.innerHTML="请选择地区";
            newSelect.appendChild(op);
            //得到完成请求后返回的字串符
            var str = xhs.responseText;
            //根据返回的字符串为新创建的select节点添加option节点
            var len=str.length;
            var arr1=str.substring(1,len-1).split(",");
            for(var i=0;i<arr1.length;i++){
                var arr2=arr1[i].split("=");
                var child=document.createElement("option");
                child.innerHTML=arr2[0]+'  '+arr2[1];
                child.value=arr2[0];
                /*
                if(arr2[0]==oldValue){
                	child.setAttribute('selected','selected');
                }*/
                newSelect.appendChild(child);
            }
            //用新select节点替换旧的select节点
           var select = document.getElementById("area");
            select.parentNode.replaceChild(newSelect, select);
        }
    }
}

function createXmlHttpRequest(){
    if(window.ActiveXObject){
        return new ActiveXObject("Microsoft.XMLHTTP");
    }else if(window.XMLHttpRequest){
        return new XMLHttpRequest();
    }
}

</script> 
  </head>
  
  <body>
  <h1 align="center">车牌生成系统</h1>
  <hr color="black">
    <h3 align="left">制作人：谢宝发 6103114005 计科卓越141</h3>
    
    <p>声明：如果全部不选或者选为机选，表示是随机取号，将生成5个车牌号；如果只选择其中某几个（机选除外）表示是限制取号,将生成5个车牌号；如果全部限制则为指定选号,将生成1个车牌号。</p>
  <form name="myForm"  action="${pageContext.request.contextPath}/MainServlet?action=randGenerate"  method="post">
  
  <div style="position:relative; top:-10px; left:100px; "> 

　
  <div class="styled-select">
  <select  name="province" id="province" onchange="cascade(this.value)" >
  <option value="*">请选择省份</option>
  <option value="*">----机选----</option>
  <c:forEach var="province" items="${applicationScope.data.dataMap['province']}">
  <option value="${province.value }" 
  <c:if test="${requestScope.province1==province.value}">selected</c:if>
  >---${province.key }[${province.value }]---</option>
  </c:forEach> 
   </select>
   </div>
     <div class="styled-select">
   <select  id="area" name="area">
  <option value="*">请选择地区</option>
   <option value="*">----机选----</option>
   <c:forEach var="area" items="${applicationScope.data.dataMap[province1]}">
  <option value="${area.key }" 
  <c:if test="${requestScope.area1==area.key}">selected</c:if>
  >${area.key }&nbsp;${area.value }</option>
  </c:forEach>
   </select>
   </div>
   
   
     <div class="styled-select">
   <select  id="code1" name="code1">
  <option value="*">请选号</option>
  <option value="*">--机选--</option>
  <c:forEach var="code" items="${applicationScope.data.codeList}">
  <option value="${code }" 
  <c:if test="${requestScope.code1==code}">selected</c:if>
  >----${code}----</option>
  </c:forEach>
   </select>
   </div>
   
     <div class="styled-select">
   <select  id="code2"  name="code2">
  <option value="*">请选号</option>
  <option value="*">--机选--</option>
  <c:forEach var="code" items="${applicationScope.data.codeList}">
  <option value="${code }" 
  <c:if test="${requestScope.code2==code}">selected</c:if>
  >----${code}----</option>
  </c:forEach>
   </select>
   </div>
   
   
     <div class="styled-select">
   <select  id="code3" name="code3">
  <option value="*">请选号</option>
  <option value="*">--机选--</option>
  <c:forEach var="code" items="${applicationScope.data.codeList}">
<option value="${code }" 
  <c:if test="${requestScope.code3==code}">selected</c:if>
  >----${code}----</option>
  </c:forEach>
   </select>
   </div>
   
   
     <div class="styled-select">
   <select  id="code4"  name="code4">
  <option value="*">请选号</option>
  <option value="*">--机选--</option>
  <c:forEach var="code" items="${applicationScope.data.codeList}">
<option value="${code }" 
  <c:if test="${requestScope.code4==code}">selected</c:if>
  >----${code}----</option>
  </c:forEach>
   </select>
   </div>
   
   
     <div class="styled-select">
   <select  id="code5"  name="code5">
  <option value="*">请选号</option>
  <option value="*">--机选--</option>
  <c:forEach var="code" items="${applicationScope.data.codeList}">
<option value="${code }" 
  <c:if test="${requestScope.code5==code}">selected</c:if>
  >----${code}----</option>
  </c:forEach>
   </select>
   </div>
   
   </div>
   <center>
   <button class="btn btn-primary" onclick="submit()">点击生成牌照</button></center>
   </form>
   <center>
  <font  face="微软雅黑" size="4"> 您现在正在进行 ${type}</font>
  <br>
    <c:forEach items="${plateNumber}" var="number">
      <font  face="微软雅黑" size="6" color="blue">${number}</font>&nbsp;
   </c:forEach>
   </center>
  </body>
</html>
