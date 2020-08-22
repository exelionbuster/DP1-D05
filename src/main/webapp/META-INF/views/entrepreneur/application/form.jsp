<%@page language="java"%>

<%@taglib prefix="acme" tagdir="/WEB-INF/tags"%>
<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>

<acme:form readonly="true">

	<acme:form-textbox code="entrepreneur.application.form.label.ticker" path="ticker"/>
	<acme:form-moment code="entrepreneur.application.form.label.creationDate" path="creationDate"/>
	<acme:form-textbox code="entrepreneur.application.form.label.statement" path="statement"/>	
	<acme:form-textbox code="entrepreneur.application.form.label.offer" path="offer"/>
	
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
	
	<acme:form-textbox code="entrepreneur.application.form.label.investor" path="investor.userAccount.username"/>
	
	<acme:form-return code="entrepreneur.application.form.button.return" />
		
</acme:form>
