<%@page language="java"%>

<%@taglib prefix="acme" tagdir="/WEB-INF/tags"%>

<acme:form readonly="true">

	<acme:form-textbox code="entrepreneur.activity.form.label.title" path="title"/>
	<acme:form-moment code="entrepreneur.activity.form.label.creation-date" path="creationDate"/>
	<acme:form-textbox code="entrepreneur.activity.form.label.end-date" path="endDate"/>	
	<acme:form-textbox code="entrepreneur.activity.form.label.budget" path="budget"/>
	
	<acme:form-return code="entrepreneur.activity.form.button.return" />
		
</acme:form>
