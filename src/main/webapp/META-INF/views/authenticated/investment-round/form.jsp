<%@page language="java"%>

<%@taglib prefix="acme" tagdir="/WEB-INF/tags"%>
<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>

<acme:form readonly="true">

	<acme:form-textbox code="authenticated.investment-round.form.label.ticker" path="ticker"/>
	<acme:form-moment code="authenticated.investment-round.form.label.creation-date" path="creationDate"/>
	<acme:form-textbox code="authenticated.investment-round.form.label.kind" path="kind"/>	
	<acme:form-textbox code="authenticated.investment-round.form.label.title" path="title"/>
	<acme:form-textarea code="authenticated.investment-round.form.label.description" path="description"/>
	<acme:form-money code="authenticated.investment-round.form.label.amount" path="amount"/>
	<acme:form-url code="authenticated.investment-round.form.label.link" path="link"/>
	
	<jstl:if test="${isInvolved}">
		<acme:form-return code="authenticated.investment-round.form.button.forum" action="/authenticated/forum/show?id=${forumId}"/>
	</jstl:if>
	
	<jstl:if test="${activities != null}">
		<acme:form-submit test="${command != 'create'}" method="get" code="authenticated.investment-round.form.button.activities" action="/authenticated/activity/list?id=${id}"/>
	</jstl:if>
	
	<jstl:if test="${isInvestor}">
		<acme:form-return code="authenticated.investment-round.form.button.apply" action="/investor/application/create?investmentRoundId=${id}"/>
	</jstl:if>
	
	<jstl:if test="${accountingRecords != null}">
	<acme:form-return code="authenticated.investment-round.form.button.accounting-records" action="/authenticated/accounting-record/list?investmentRoundId=${id}"/>
	</jstl:if>
	
	<acme:form-return code="authenticated.investment-round.form.button.return" />
		
</acme:form>
