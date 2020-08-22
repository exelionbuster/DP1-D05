<%@page language="java"%>

<%@taglib prefix="acme" tagdir="/WEB-INF/tags"%>

<acme:form>
	<acme:form-textbox code="patron.credit-card.form.label.holder-name" path="holderName"/>
	<acme:form-textbox code="patron.credit-card.form.label.number" path="number"/>
	<acme:form-textbox code="patron.credit-card.form.label.brand" path="brand"/>
	<acme:form-moment code="patron.credit-card.form.label.expiration-date" path="expirationDate"/>
	<acme:form-textbox code="patron.credit-card.form.label.cvv" path="cvv"/>
	
	<acme:form-return code="patron.credit-card.form.button.return" />	
</acme:form>
