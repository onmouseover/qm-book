<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="/common/path.jsp"%>
<%@ page session="false" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
<title>页面找不到</title>
<link rel="stylesheet" href="${online_path}/saleNew/themes/default/0066CC/page.css" />
</head>
<body>
	<div class="error-box error-404">
		<div class="error-main">
			<h3 class="error-title">
				您访问的页面暂时无法找到
			</h3>
		</div>
		<ul class="error-action">
			<li>
				<a href="javaScript:history.go(-1);"><i class="icon icon-arrow-left"></i> 返回上一页</a>
			</li>
		</ul>
	</div>
</body>
</html>