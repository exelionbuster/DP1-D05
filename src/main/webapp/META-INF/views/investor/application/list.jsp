<%--
- list.jsp
-
- Copyright (c) 2019 Rafael Corchuelo.
-
- In keeping with the traditional purpose of furthering education and research, it is
- the policy of the copyright owner to permit non-commercial use and redistribution of
- this software. It has been tested carefully, but it is not guaranteed for any particular
- purposes.  The copyright owner does not offer any warranties or representations, nor do
- they accept any liabilities with respect to them.
--%>

<%@page language="java"%>

<%@taglib prefix="acme" tagdir="/WEB-INF/tags"%>

<acme:list>

	<acme:list-column code="investor.application.list.label.ticker" path="ticker" width="35%"/>
	<acme:list-column code="investor.application.list.label.creationDate" path="creationDate" width="35%"/>
	<acme:list-column code="investor.application.list.label.offer" path="offer" width="35%"/>
	<acme:list-column code="entrepreneur.application.list.label.status" path="status" width="35%"/>
			
</acme:list>



