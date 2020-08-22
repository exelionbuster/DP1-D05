<%@page language="java"%>

<%@taglib prefix="acme" tagdir="/WEB-INF/tags"%>
<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<spring:message var="noTags" code="authenticated.message.form.no-tags"/>
<acme:form readonly="true">

	<acme:form-textbox code="authenticated.message.form.label.title" path="title"/>
	<acme:form-moment code="authenticated.message.form.label.creation-date" path="creationDate"/>
	<acme:form-textbox code="authenticated.message.form.label.sender" path="msgSender"/>
	<jstl:choose>
		<jstl:when test="${msgTags != null}">
			<acme:form-textbox code="authenticated.message.form.label.tags" path="msgTags"/>
		</jstl:when>
		<jstl:otherwise>
			<acme:form-textbox code="authenticated.message.form.label.tags" path="msgTags" placeholder="${noTags}"/>
		</jstl:otherwise>
	</jstl:choose>
	<acme:form-textarea code="authenticated.message.form.label.body" path="body"/>
	
	<acme:form-return code="authenticated.message.form.button.return" />
	
</acme:form>
