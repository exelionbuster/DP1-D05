<%@page language="java"%>

<%@taglib prefix="acme" tagdir="/WEB-INF/tags"%>
<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<spring:message var="noInvR" code="authenticated.forum.form.label.no-inv-round"/>

<acme:form>
	<jstl:if test="${notOwned}">
		<acme:form-textbox code="authenticated.forum.form.label.title" path="title" readonly="true"/>
		<acme:form-textbox code="authenticated.forum.form.label.users" path="users" readonly="true"/>
	</jstl:if>
	<jstl:if test="${!notOwned}">
		<acme:form-textbox code="authenticated.forum.form.label.title" path="title"/>
		<acme:form-textbox code="authenticated.forum.form.label.users.owned" path="users"/>
	</jstl:if>
	<jstl:if test="${command != 'create'}">
		<acme:form-moment code="authenticated.forum.form.label.creation-date" path="creationDate" readonly="true"/>
		<acme:form-textbox code="authenticated.forum.form.label.owner" path="forumOwner" readonly="true"/>
		<jstl:choose>
			<jstl:when test="${invR != null}">
				<acme:form-textbox code="authenticated.forum.form.label.investment-round" path="invR" readonly="true"/>
			</jstl:when>
			<jstl:otherwise>
				<acme:form-textbox code="authenticated.forum.form.label.investment-round" path="invR" placeholder="${noInvR}" readonly="true"/>
			</jstl:otherwise>
		</jstl:choose>
	</jstl:if>
	<jstl:if test="${hasMessages}">
		<acme:form-return code="authenticated.forum.form.button.messages" action="/authenticated/message/list?forumId=${id}"/>
	</jstl:if>
	<jstl:if test="${command != 'create'}">
		<acme:form-return code="authenticated.forum.form.button.new-message" 
			action="/authenticated/message/create?forumId=${id}"/>
		<br>
		<br>
	</jstl:if>
	<acme:form-submit test="${command == 'create'}" 
		code="authenticated.forum.form.button.create" 
		action="/authenticated/forum/create"/>
	<acme:form-submit test="${command == 'show' and !notOwned}" 
		code="authenticated.forum.form.button.update" 
		action="/authenticated/forum/update"/>
		<acme:form-submit test="${command == 'show' and !notOwned}" 
		code="authenticated.forum.form.button.delete" 
		action="/authenticated/forum/delete"/>
	<acme:form-submit test="${command == 'update' and !notOwned}" 
		code="authenticated.forum.form.button.update" 
		action="/authenticated/forum/update"/>
	<acme:form-submit test="${command == 'update' and !notOwned}" 
		code="authenticated.forum.form.button.delete" 
		action="/authenticated/forum/delete"/>
	<acme:form-submit test="${command == 'delete' and !notOwned}" 
		code="authenticated.forum.form.button.update" 
		action="/authenticated/forum/update"/>
	<acme:form-submit test="${command == 'delete' and !notOwned}" 
		code="authenticated.forum.form.button.delete" 
		action="/authenticated/forum/delete"/>
	<acme:form-return code="authenticated.forum.form.button.return"/>
	
</acme:form>
