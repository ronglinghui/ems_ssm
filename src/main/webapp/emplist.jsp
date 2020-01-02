<%@page language="java" import="java.util.*" pageEncoding="UTF-8"
	contentType="text/html;charset=UTF-8" isELIgnored="false" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html xmlns:th="http://www.thymeleaf.org">
<head>
<title>emplist</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/style.css" />
</head>
<body id="bod">
	<div id="wrap">
		<div id="top_content">
			<div id="header">
				<div id="rightheader">
					<p>
						2009/11/20 <br />
					</p>
				</div>
				<div id="topheader">
					<h1 id="title">
						<a href="#">main</a>
					</h1>
				</div>
				<div id="navigation"></div>
			</div>
			<div id="content">
				<p id="whereami"></p>
				<form action="${pageContext.request.contextPath}/emp/selectByName?pageNumber=${requestScope.pageNumber}&Name=${requestScope.moName}" method="post">
				<h1>Welcome !&nbsp;&nbsp;[${sessionScope.manager.username}]&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;名字：<input type="text" name="name" value="${requestScope.moName}"/>&nbsp;<input type="submit" value="查询"/></h1>
				</form>
				<form method="post" action="${pageContext.request.contextPath}/emp/plDelete?pageNumber=${requestScope.pageNumber}">
					<table class="table">
						<tbody id="tod">
						<tr class="table_header">
							<td>plDelete</td>
							<td>ID</td>
							<td>Name</td>
							<td>Salary</td>
							<td>Age</td>
							<td>Bir</td>
							<td>Sex</td>
							<td colspan="2">Operation</td>
						</tr>
						<c:forEach var="emp" items="${requestScope.emps}">
							<tr>
								<td><input type="checkbox" name="plId" value="${emp.id}"></td>
								<td>${emp.id}</td>
								<td>${emp.name}</td>
								<td>${emp.salary}</td>
								<td>${emp.age}</td>
								<td>${emp.birthday}</td>
								<td>${emp.sex}</td>
								<td><a href="${pageContext.request.contextPath}/emp/deleteEmp?id=${emp.id}">delete
									emp</a>&nbsp; <a href="${pageContext.request.contextPath}/emp/selectById?id=${emp.id}">update
									emp</a></td>
							</tr>
						</c:forEach>
						<tr><td align="center" colspan="8"><input type="submit" value="plDelete"></td></tr>
						</tbody>
					</table>
				</form>
				<p>
					<!-- 上一页的按钮 -->
					<c:if test="${requestScope.pageNumber>1}"><a href="${pageContext.request.contextPath}/login/syBase?pageNumber=${requestScope.pageNumber-1}&moName=${requestScope.moName}"><input type="button" value="上一页"></a></c:if>
					<c:if test="${requestScope.pageNumber<=1}"><input type="button" value="上一页"></c:if>
					<!-- 上一页的按钮 -->
					<c:if test="${requestScope.pageNumber<requestScope.pageCount}"><a href="${pageContext.request.contextPath}/login/syBase?pageNumber=${requestScope.pageNumber+1}&moName=${requestScope.moName}"><input type="button" value="下一页"></a></c:if>
					<c:if test="${requestScope.pageNumber>=requestScope.pageCount}"><input type="button" value="下一页"></c:if>
				
				</p>
				<p>
					<a href="${pageContext.request.contextPath}/addEmp.jsp">添加员工信息</a>
					<form action="${pageContext.request.contextPath}/emp/upLoadFile" method="post" enctype="multipart/form-data">
						<input type="file" name="file" value="上传文件"/>
						<input type="submit" value="提交"/>
					</form>
					<a href="${pageContext.request.contextPath}/xiafile.jsp">下载文件</a>
				</p>
			</div>
		</div>
		<div id="footer">
			<div id="footer_bg">ABC@126.com</div>
		</div>
	</div>
</body>
</html>
