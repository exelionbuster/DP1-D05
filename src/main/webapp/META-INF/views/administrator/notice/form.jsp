<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" tagdir="/WEB-INF/tags"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<spring:message var="fecha" code="administrator.notice.form.placeholder.deadline"/>
<acme:form>

	<acme:form-url code="administrator.notice.form.label.header" path="header"/>
	<acme:form-textbox code="administrator.notice.form.label.title" path="title"/>
	<jstl:if test="${command != 'create'}">
		<acme:form-moment code="administrator.notice.form.label.creation-date" path="creationDate" readonly="true"/>	
	</jstl:if>
	<acme:form-moment code="administrator.notice.form.label.deadline" placeholder="${fecha}" path="deadline"/>
	<acme:form-textarea code="administrator.notice.form.label.body" path="body"/>
	<acme:form-textbox code="administrator.notice.form.label.links" path="links"/>
	
	<jstl:if test="${command == 'create'}">
		<acme:form-checkbox
	   		code="administrator.notice.form.button.checkbox"
	    	path="accept"/>
		<acme:form-submit code="administrator.notice.form.button.create" action="/administrator/notice/create"/>
	</jstl:if>
	<acme:form-return code="administrator.notice.form.button.return" />
		
</acme:form>
