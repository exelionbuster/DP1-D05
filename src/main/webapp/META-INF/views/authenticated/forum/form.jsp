<%@page language="java"%>

<%@taglib prefix="acme" tagdir="/WEB-INF/tags"%>
<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<spring:message var="noInvR" code="authenticated.forum.form.label.no-inv-round"/>

<acme:form readonly="true">

	<acme:form-textbox code="authenticated.forum.form.label.title" path="title"/>
	<acme:form-moment code="authenticated.forum.form.label.creation-date" path="creationDate"/>
	<acme:form-textbox code="authenticated.forum.form.label.owner" path="forumOwner"/>
	<acme:form-textbox code="authenticated.forum.form.label.users" path="users"/>
	<jstl:choose>
		<jstl:when test="${invR != null}">
			<acme:form-textbox code="authenticated.forum.form.label.investment-round" path="invR"/>
		</jstl:when>
		<jstl:otherwise>
			<acme:form-textbox code="authenticated.forum.form.label.investment-round" path="invR" placeholder="${noInvR}"/>
		</jstl:otherwise>
	</jstl:choose>
	
	<acme:form-return code="authenticated.forum.form.button.messages" action="/authenticated/message/list?forumId=${id}"/>
	<acme:form-return code="authenticated.forum.form.button.return" />
	
</acme:form>
