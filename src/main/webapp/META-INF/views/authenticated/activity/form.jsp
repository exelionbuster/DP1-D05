<%@page language="java"%>

<%@taglib prefix="acme" tagdir="/WEB-INF/tags"%>

<acme:form readonly="true">

	<acme:form-textbox code="authenticated.activity.form.label.title" path="title"/>
	<acme:form-moment code="authenticated.activity.form.label.start-date" path="startDate"/>
	<acme:form-moment code="authenticated.activity.form.label.end-date" path="endDate"/>	
	<acme:form-money code="authenticated.activity.form.label.budget" path="budget"/>
	
	<acme:form-return code="authenticated.activity.form.button.return" />
		
</acme:form>
