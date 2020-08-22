<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" tagdir="/WEB-INF/tags"%>

<acme:form>
	<acme:form-textbox code="anonymous.bonacini.bulletin.form.label.author" path="author"/>
	<acme:form-textbox code="anonymous.bonacini.bulletin.form.label.city" path="city"/>
	<acme:form-textarea code="anonymous.bonacini.bulletin.form.label.text" path="text" />

	<acme:form-submit code="anonymous.bonacini.bulletin.form.button.create" action="/anonymous/bonacini-bulletin/create"/>
	<acme:form-return code="anonymous.bonacini.bulletin.form.button.return"/>
</acme:form>