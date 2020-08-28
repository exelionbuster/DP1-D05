<%@page language="java"%>

<%@taglib prefix="acme" tagdir="/WEB-INF/tags"%>
<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>

<acme:form>

	<jstl:if test="${!isFinalMode}">

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
	
	</jstl:if>
	
	<jstl:if test="${isFinalMode}">
	
		<acme:form-textbox code="entrepreneur.activity.form.label.title" path="title" readonly="true"/>
		<acme:form-moment code="entrepreneur.activity.form.label.start-date" path="startDate" readonly="true"/>
		<acme:form-moment code="entrepreneur.activity.form.label.end-date" path="endDate" readonly="true"/>
		<acme:form-money code="entrepreneur.activity.form.label.budget" path="budget" readonly="true"/>
	
	</jstl:if>
	
	<acme:form-return code="entrepreneur.activity.form.button.return" />
	
		
</acme:form>
