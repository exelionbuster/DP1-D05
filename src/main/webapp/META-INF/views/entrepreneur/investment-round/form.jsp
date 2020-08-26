<%@page language="java"%>

<%@taglib prefix="acme" tagdir="/WEB-INF/tags"%>
<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>

<acme:form>
	<acme:form-textbox code="entrepreneur.investment-round.form.label.ticker" path="ticker"/>
	
	<jstl:if test="${command != 'create'}">
		<acme:form-moment code="entrepreneur.investment-round.form.label.creation-date" path="creationDate"/>
	</jstl:if>
	
	<acme:form-textbox code="entrepreneur.investment-round.form.label.kind" path="kind"/>	
	<acme:form-textbox code="entrepreneur.investment-round.form.label.title" path="title"/>
	<acme:form-textarea code="entrepreneur.investment-round.form.label.description" path="description"/>
	<acme:form-money code="entrepreneur.investment-round.form.label.amount" path="amount"/>
	<acme:form-url code="entrepreneur.investment-round.form.label.link" path="link"/>
	
	<jstl:if test="${activities != null}">
		<acme:form-submit test="${command != 'create'}" method="get" code="entrepreneur.investment-round.form.button.activities" action="/entrepreneur/activity/list?id=${id}"/>
	</jstl:if>
	
	<jstl:if test="${command != 'create'}">
		<acme:form-checkbox code="entrepreneur.investment-round.form.label.finalMode" path="finalMode" readonly="true" />
	</jstl:if>
	
	<acme:form-submit test="${command == 'create'}" code="entrepreneur.investment-round.form.button.create" action="/entrepreneur/investment-round/create" />
	<jstl:if test="${finalMode == false}">
		
		<acme:form-submit test="${command == 'show'}" code="entrepreneur.investment-round.form.button.publish" action="/entrepreneur/investment-round/publish" />
	</jstl:if>
	
	<acme:form-submit test="${command == 'update'}" code="entrepreneur.investment-round.form.button.update" action="/entrepreneur/investment-round/update" />
	<jstl:if test="${finalMode == false}">
		<acme:form-submit test="${command == 'show'}" code="entrepreneur.investment-round.form.button.update" action="/entrepreneur/investment-round/update" />
	</jstl:if>
	
	<jstl:if test="${applications == 0}">
		<acme:form-submit test="${command == 'show'}" code="entrepreneur.investment-round.form.button.delete" action="/entrepreneur/investment-round/delete" />
	</jstl:if>
	
	<acme:form-return code="entrepreneur.investment-round.form.button.return" />
		
</acme:form>
