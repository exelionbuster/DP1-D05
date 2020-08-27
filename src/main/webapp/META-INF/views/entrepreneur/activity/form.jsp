<%@page language="java"%>

<%@taglib prefix="acme" tagdir="/WEB-INF/tags"%>

<acme:form>

	<acme:form-textbox code="entrepreneur.activity.form.label.title" path="title"/>
	<acme:form-moment code="entrepreneur.activity.form.label.start-date" path="startDate"/>
	<acme:form-moment code="entrepreneur.activity.form.label.end-date" path="endDate"/>
	<acme:form-money code="entrepreneur.activity.form.label.budget" path="budget"/>
	
	<acme:form-submit test="${command == 'create' }"
		code="entrepreneur.activity.form.button.create"
		action="/entrepreneur/activity/create?investmentRoundId=${investmentRoundId}"/>
		
    <acme:form-submit test="${command == 'show'}" code="entrepreneur.activity.form.button.update" action="/entrepreneur/activity/update"/>
    <acme:form-submit test="${command == 'show'}" code="entrepreneur.activity.form.button.delete" action="/entrepreneur/activity/delete"/>
    <acme:form-submit test="${command == 'update'}" code="entrepreneur.activity.form.button.update" action="/entrepreneur/activity/update"/>
    <acme:form-submit test="${command == 'update'}" code="entrepreneur.activity.form.button.delete" action="/entrepreneur/activity/delete"/>
	<acme:form-return code="entrepreneur.activity.form.button.return" />
		
</acme:form>
