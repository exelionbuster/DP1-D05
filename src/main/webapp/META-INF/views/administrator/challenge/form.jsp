<%--
- form.jsp
-
- Copyright (c) 2019 Rafael Corchuelo.
-
- In keeping with the traditional purpose of furthering education and research, it is
- the policy of the copyright owner to permit non-commercial use and redistribution of
- this software. It has been tested carefully, but it is not guaranteed for any particular
- purposes.  The copyright owner does not offer any warranties or representations, nor do
- they accept any liabilities with respect to them.
--%>

<%@page language="java"%>

<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="acme" tagdir="/WEB-INF/tags"%>

<spring:message var="fecha" code="administrator.challenge.form.placeholder.deadline"/>

<acme:form>
	<acme:form-textbox code="administrator.challenge.form.label.title" path="title" />
	<acme:form-textbox code="administrator.challenge.form.label.deadline" placeholder="${fecha }" path="deadline" />
	<acme:form-textarea code="administrator.challenge.form.label.description" path="description" />
	<acme:form-textbox code="administrator.challenge.form.label.rookie-goal" path="rookieGoal" />
	<acme:form-double code="administrator.challenge.form.label.rookie-reward" path="rookieReward" />
	<acme:form-textbox code="administrator.challenge.form.label.average-goal" path="averageGoal" />
	<acme:form-double code="administrator.challenge.form.label.average-reward" path="averageReward" />
	<acme:form-textbox code="administrator.challenge.form.label.expert-goal" path="expertGoal" />
	<acme:form-double code="administrator.challenge.form.label.expert-reward" path="expertReward" />
	
  	<acme:form-submit test="${command == 'create'}" 
		code="administrator.challenge.form.button.create" 
		action="/administrator/challenge/create"/>
	<acme:form-submit test="${command == 'show'}" 
		code="administrator.challenge.form.button.update" 
		action="/administrator/challenge/update"/>
		<acme:form-submit test="${command == 'show'}" 
		code="administrator.challenge.form.button.delete" 
		action="/administrator/challenge/delete"/>
	<acme:form-submit test="${command == 'update'}" 
		code="administrator.challenge.form.button.update" 
		action="/administrator/challenge/update"/>
	<acme:form-submit test="${command == 'update'}" 
		code="administrator.challenge.form.button.delete" 
		action="/administrator/challenge/delete"/>
	<acme:form-return code="authenticated.challenge.form.button.return"/>
	
</acme:form>