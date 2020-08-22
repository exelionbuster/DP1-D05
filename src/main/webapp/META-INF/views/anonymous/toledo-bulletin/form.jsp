<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" tagdir="/WEB-INF/tags"%>

<acme:form>
	<acme:form-textbox code="anonymous.bulletin.toledo.form.label.author" path="author"/>
	<acme:form-textbox code="anonymous.bulletin.toledo.form.label.email" path="email"/>
	<acme:form-textarea code="anonymous.bulletin.toledo.form.label.text" path="text"/>
	<acme:form-submit code="anonymous.bulletin.toledo.form.button.create" action="/anonymous/toledo-bulletin/create"/>
	<acme:form-return code="anonymous.bulletin.toledo.form.button.return"/>
</acme:form>
