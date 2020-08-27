<%@page language="java"%>

<%@taglib prefix="acme" tagdir="/WEB-INF/tags"%>

<acme:form>
	<acme:form-textbox code="patron.credit-card.form.label.holder-name" path="holderName"/>
	<acme:form-textbox code="patron.credit-card.form.label.number" path="number"/>
	<acme:form-textbox code="patron.credit-card.form.label.brand" path="brand"/>
	<acme:form-textbox code="patron.credit-card.form.label.expiration-date" path="ccDate" placeholder="MM/yyyy"/>
	<acme:form-textbox code="patron.credit-card.form.label.cvv" path="cvv"/>
	
	
	<acme:form-submit test="${command == 'create'}" code="patron.credit-card.form.button.create" action="/patron/credit-card/create"/>
	<acme:form-submit test="${command == 'show'}" code="patron.credit-card.form.button.update" action="/patron/credit-card/update"/>
	<acme:form-submit test="${command == 'show'}" code="patron.credit-card.form.button.delete" action="/patron/credit-card/delete"/>
	<acme:form-submit test="${command == 'update'}" code="patron.credit-card.form.button.update" action="/patron/credit-card/update"/>
	<acme:form-submit test="${command == 'update'}" code="patron.credit-card.form.button.delete" action="/patron/credit-card/delete"/>
	<acme:form-return code="patron.credit-card.form.button.return" />	
</acme:form>
