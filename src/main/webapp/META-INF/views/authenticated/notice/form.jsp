<%@page language="java"%>

<%@taglib prefix="acme" tagdir="/WEB-INF/tags"%>

<acme:form readonly="true">

	<acme:form-textbox code="authenticated.notice.form.label.header" path="header"/>
	<acme:form-textbox code="authenticated.notice.form.label.title" path="title"/>
	<acme:form-moment code="authenticated.notice.form.label.creation-date" path="creationDate"/>	
	<acme:form-moment code="authenticated.notice.form.label.deadline" path="deadline"/>
	<acme:form-textarea code="authenticated.notice.form.label.body" path="body"/>
	<acme:form-url code="authenticated.notice.form.label.links" path="links"/>
	
	<acme:form-return code="authenticated.notice.form.button.return" />
		
</acme:form>
