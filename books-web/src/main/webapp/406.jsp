<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="/common/path.jsp"%>
<%@ page session="false" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
<title>请求失败</title>
<link rel="stylesheet" href="${online_path}/saleNew/themes/default/0066CC/page.css" />
</head>
<body>
	<div class="error-box error-other">
		<div class="error-main">
			<h3 class="error-title">
				站点已停用,原因如下：
			</h3>
			<p class="error-content">
				${errormsg}
			</p>
		</div>
		<ul class="error-action">
			<li>
				<a href="javaScript:history.go(-1);"><i class="icon icon-arrow-left"></i> 返回上一页</a>
			</li>
		</ul>
	</div>
</body>
</html>