<%@page language="java"%>

<%@taglib prefix="acme" tagdir="/WEB-INF/tags"%>
<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<spring:message var="noTags" code="authenticated.message.form.no-tags"/>

<acme:form readonly="false">
	<jstl:if test="${command == 'create'}">
		<acme:form-textbox code="authenticated.message.form.label.title" path="title" readonly="false"/>
	</jstl:if>
	<jstl:if test="${command != 'create'}">
		<acme:form-textbox code="authenticated.message.form.label.title" path="title" readonly="true"/>
		<acme:form-moment code="authenticated.message.form.label.creation-date" path="creationDate" readonly="true"/>
		<acme:form-textbox code="authenticated.message.form.label.sender" path="msgSender" readonly="true"/>
	</jstl:if>
	<jstl:if test="${command == 'create'}">
		<acme:form-textbox code="authenticated.message.form.label.tags" path="msgTags" readonly="false"/>
	</jstl:if>
	<jstl:if test="${command != 'create'}">
		<jstl:choose>
			<jstl:when test="${msgTags != null}">
				<acme:form-textbox code="authenticated.message.form.label.tags" path="msgTags" readonly="true"/>
			</jstl:when>
			<jstl:otherwise>
				<acme:form-textbox code="authenticated.message.form.label.tags" path="msgTags" placeholder="${noTags}" readonly="true"/>
			</jstl:otherwise>
		</jstl:choose>
	</jstl:if>
	<acme:form-textarea code="authenticated.message.form.label.body" path="body"/>
	
	<jstl:if test="${command == 'create'}">
		<acme:form-checkbox
	   		code="authenticated.message.form.button.checkbox"
	    	path="accept"/>
		<acme:form-submit code="authenticated.message.form.button.create"
				action="/authenticated/message/create?forumId=${forumId}"/>
	</jstl:if>
	
	
	<acme:form-return code="authenticated.message.form.button.return" />
	
</acme:form>
