<%@page language="java"%>

<%@taglib prefix="acme" tagdir="/WEB-INF/tags"%>
<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>

<acme:form>
	
	<jstl:if test="${command != 'create'}">
	<acme:form-moment code="investor.application.form.label.creationDate" path="creationDate" readonly="true"/>
	<acme:form-textbox code="investor.application.form.label.status" path="status" readonly="true"/>	
	</jstl:if>
	
	<acme:form-textbox code="investor.application.form.label.ticker" path="ticker"/>
	<acme:form-textbox code="investor.application.form.label.statement" path="statement"/>
	<acme:form-textbox code="investor.application.form.label.offer" path="offer"/>
	
	<acme:form-submit test="${command == 'create' }"
		code="investor.application.form.button.create"
		action="/investor/application/create?investmentRoundId=${investmentRoundId}"/>
	
	<acme:form-return code="investor.application.form.button.return" />
		
</acme:form>
