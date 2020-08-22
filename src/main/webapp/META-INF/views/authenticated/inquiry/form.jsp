<%@page language="java"%>

<%@taglib prefix="acme" tagdir="/WEB-INF/tags"%>

<acme:form readonly="true">

	<acme:form-textbox code="authenticated.inquiry.form.label.title" path="title"/>
	<acme:form-moment code="authenticated.inquiry.form.label.creation-date" path="creationDate"/>
	<acme:form-moment code="authenticated.inquiry.form.label.deadline" path="deadline"/>	
	<acme:form-textarea code="authenticated.inquiry.form.label.description" path="description"/>
	<acme:form-money code="authenticated.inquiry.form.label.min-money" path="minMoney"/>
	<acme:form-money code="authenticated.inquiry.form.label.max-money" path="maxMoney"/>
	<acme:form-textbox code="authenticated.inquiry.form.label.contact-email" path="contactEmail"/>
	
	<acme:form-return code="authenticated.inquiry.form.button.return" />
		
</acme:form>
