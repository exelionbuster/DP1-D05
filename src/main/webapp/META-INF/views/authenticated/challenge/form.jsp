<%@page language="java"%>

<%@taglib prefix="acme" tagdir="/WEB-INF/tags"%>

<acme:form readonly="true">

	<acme:form-textbox code="authenticated.challenge.form.label.title" path="title"/>
	<acme:form-moment code="authenticated.challenge.form.label.deadline" path="deadline"/>	
	<acme:form-textarea code="authenticated.challenge.form.label.description" path="description"/>
	<acme:form-money code="authenticated.challenge.form.label.rookie-goal" path="rookieGoal"/>
	<acme:form-money code="authenticated.challenge.form.label.rookie-reward" path="rookieReward"/>
	<acme:form-url code="authenticated.challenge.form.label.average-goal" path="averageGoal"/>
	<acme:form-url code="authenticated.challenge.form.label.average-reward" path="averageReward"/>
	<acme:form-url code="authenticated.challenge.form.label.expert-goal" path="expertGoal"/>
	<acme:form-url code="authenticated.challenge.form.label.expert-reward" path="expertReward"/>
	
	<acme:form-return code="authenticated.challenge.form.button.return" />
		
</acme:form>
