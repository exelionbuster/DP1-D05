<%@page language="java"%>

<%@taglib prefix="acme" tagdir="/WEB-INF/tags"%>

<acme:form readonly="true">

	<acme:form-textbox code="investor.application.form.label.ticker" path="ticker"/>
	<acme:form-moment code="investor.application.form.label.creationDate" path="creationDate"/>
	<acme:form-textbox code="investor.application.form.label.statement" path="statement"/>
	<acme:form-textbox code="investor.application.form.label.status" path="status" />	
	<acme:form-textbox code="investor.application.form.label.offer" path="offer"/>
	
	<acme:form-return code="investor.application.form.button.return" />
		
</acme:form>
