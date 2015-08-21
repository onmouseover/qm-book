<%@page import="java.util.*"%>
<%@ page session="false" %>
<%
	java.util.Date currTime = new java.util.Date();
	java.text.SimpleDateFormat yymmdd = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.US);
	out.print(new String(yymmdd.format(currTime).getBytes("iso-8859-1")));
%>