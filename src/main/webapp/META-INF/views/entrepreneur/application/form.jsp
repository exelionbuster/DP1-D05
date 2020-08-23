<%@page language="java"%>

<%@taglib prefix="acme" tagdir="/WEB-INF/tags"%>
<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>

<acme:form>

	<acme:form-textbox code="entrepreneur.application.form.label.ticker" path="ticker" readonly="true"/>
	<acme:form-moment code="entrepreneur.application.form.label.creationDate" path="creationDate" readonly="true"/>
	<acme:form-textbox code="entrepreneur.application.form.label.statement" path="statement" readonly="true"/>	
	<acme:form-textbox code="entrepreneur.application.form.label.offer" path="offer" readonly="true"/>
	
	<jstl:if test="${status != 'pending'}">
		<acme:form-textbox code="entrepreneur.application.form.label.status" path="status" readonly="true"/>
	</jstl:if>
	<jstl:if test="${status == 'pending'}">
		<acme:form-select code="entrepreneur.application.form.label.status" path="status">
  			<acme:form-option code="entrepreneur.application.form.label.pending" value="pending"/>
	 		<acme:form-option code="entrepreneur.application.form.label.accepted" value="accepted"/>
	 		<acme:form-option code="entrepreneur.application.form.label.rejected" value="rejected"/>
		</acme:form-select>
	</jstl:if>
	
	<acme:form-textbox code="entrepreneur.application.form.label.justification" path="justification"/>	
	<acme:form-textbox code="entrepreneur.application.form.label.investor" path="investor.userAccount.username" readonly="true"/>
	
	<acme:form-submit test="${command == 'show'}"
		code="entrepreneur.application.form.button.update"
		action="/entrepreneur/application/update"/>
		
	<acme:form-submit test="${command == 'update'}"
		code="entrepreneur.application.form.button.update"
		action="/entrepreneur/application/update"/>
	
	<acme:form-return code="entrepreneur.application.form.button.return" />
		
</acme:form>
