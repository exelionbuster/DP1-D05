<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" tagdir="/WEB-INF/tags"%>

<acme:form readonly="true">

	<acme:form-textbox code="patron.banner.form.label.picture" path="picture"/>
	<acme:form-textbox code="patron.banner.form.label.slogan" path="slogan"/>
	<acme:form-textbox code="patron.banner.form.label.target" path="target"/>
	
	<acme:form-select code="patron.banner.form.label.credit-card" path="creditCardId">
		<jstl:if test="${creditCardId == null}">
			<acme:form-option code="patron.banner.form.credit-card.default" value=""/>
		</jstl:if>
		<jstl:if test="${creditCardId != null}">
			<acme:form-option code="${creditCardNumber}" value="${creditCardId}"/>
			<acme:form-option code="patron.banner.form.credit-card.default" value=""/>
		</jstl:if>
	</acme:form-select>
	<jstl:if test="${creditCardId != null and creditCardId != '' and command != 'create'}">
		<acme:form-submit method="get" code="patron.banner.form.button.credit-card" action="/patron/credit-card/show?id=${creditCardId}"/>
		<br>
		<br>
	</jstl:if>
	<acme:form-return code="patron.banner.form.button.return" />
		
</acme:form>
