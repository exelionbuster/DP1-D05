<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" tagdir="/WEB-INF/tags"%>

<style>
div.containerL{
display: inline-block;
width: 40%;
text-align: center
}
div.containerR{
display: inline-block;
width: 40%;
text-align: center}
div.containerC{
display: inline-block;
width: 80%;
text-align: center
}
</style>

<h1>
	<acme:message code="administrator.dashboard.form.title" />
</h1>

<br />
<br />
<acme:form>
	<acme:form-integer code="administrator.dashboard.form.table.cell.total-notices" path="numberOfNotices"/>
	<acme:form-integer code="administrator.dashboard.form.table.cell.total-tech-records" path="numberOfTechnologyRecords"/>
	<acme:form-integer code="administrator.dashboard.form.table.cell.total-tool-records" path="numberOfToolRecords"/>
	<br />
	<br />
	<h4>
		<acme:message code="administrator.dashboard.form.table.title.min-money-active-inquiries"/>
	</h4>
	<acme:form-textbox code="administrator.dashboard.form.table.cell.stats.min" path="minMinInquiriesCurrency"/>
	<acme:form-textbox code="administrator.dashboard.form.table.cell.stats.max" path="maxMinInquiriesCurrency"/>
	<acme:form-textbox code="administrator.dashboard.form.table.cell.stats.avg" path="avgMinInquiriesCurrency"/>
	<acme:form-textbox code="administrator.dashboard.form.table.cell.stats.stddev" path="stddevMinInquiriesCurrency"/>

	<br />
	<br />
	
	<h4>
		<acme:message code="administrator.dashboard.form.table.title.max-money-active-inquiries"/>
	</h4>
	<acme:form-money code="administrator.dashboard.form.table.cell.stats.min" path="minMaxInquiriesCurrency"/>
	<acme:form-money code="administrator.dashboard.form.table.cell.stats.max" path="maxMaxInquiriesCurrency"/>
	<acme:form-money code="administrator.dashboard.form.table.cell.stats.avg" path="avgMaxInquiriesCurrency"/>
	<acme:form-money code="administrator.dashboard.form.table.cell.stats.stddev" path="stddevMaxInquiriesCurrency"/>

	<br />
	<br />
	
	<h4>	
		<acme:message code="administrator.dashboard.form.table.title.min-money-active-overtures"/>
	</h4>
	<acme:form-money code="administrator.dashboard.form.table.cell.stats.min" path="minMinOverturesCurrency"/>
	<acme:form-money code="administrator.dashboard.form.table.cell.stats.max" path="maxMinOverturesCurrency"/>
	<acme:form-money code="administrator.dashboard.form.table.cell.stats.avg" path="avgMinOverturesCurrency"/>
	<acme:form-money code="administrator.dashboard.form.table.cell.stats.stddev" path="stddevMinOverturesCurrency"/>

	<br />
	<br />
	
	<h4>
		<acme:message code="administrator.dashboard.form.table.title.max-money-active-overtures"/>
	</h4>
	<acme:form-money code="administrator.dashboard.form.table.cell.stats.min" path="minMaxOverturesCurrency"/>
	<acme:form-money code="administrator.dashboard.form.table.cell.stats.max" path="maxMaxOverturesCurrency"/>
	<acme:form-money code="administrator.dashboard.form.table.cell.stats.avg" path="avgMaxOverturesCurrency"/>
	<acme:form-money code="administrator.dashboard.form.table.cell.stats.stddev" path="stddevMaxOverturesCurrency"/>
	
	<br />
	<br />
	<br />

	<acme:form-money code="administrator.dashboard.form.table.cell.inv-round-per-entrep" path="invRoundPerEntrep"/>
	<acme:form-money code="administrator.dashboard.form.table.cell.app-per-entrep" path="appPerEntrep"/>
	<acme:form-money code="administrator.dashboard.form.table.cell.app-per-investor" path="appPerInvestor"/>

</acme:form>
<br />
<br />

<h2></h2>
<div class="containerL">
	<b><acme:message code="administrator.dashboard.form.chart.title.techs-by-sector"/></b>
	<br /><br />
	<canvas id="chart-area"></canvas>
</div>

<div class="containerR">
	<b><acme:message code="administrator.dashboard.form.chart.title.techs-by-licence"/></b>
	<br /><br />
	<canvas id="chart-area2" ></canvas>
</div>

<script type="text/javascript">
	$(document).ready(function() {
		var data = {
			labels : [
				<jstl:forEach var="sector" items="${techRecordsSectors}" varStatus="loop">
					"<jstl:out value="${sector}"/>"
					<jstl:if test="${!loop.last}"> ,</jstl:if>
				</jstl:forEach>		
			],
			datasets: [
				{
					data: [
						<jstl:forEach var="recordsNum" items="${techRecordsBySector}" varStatus="loop">
							<jstl:out value="${recordsNum}"/>
							<jstl:if test="${!loop.last}"> ,</jstl:if>
							
						</jstl:forEach>
					],
					backgroundColor : [
						<jstl:forEach var="recordsNum" items="${techRecordsBySector}" varStatus="loop">
							<jstl:choose>
								<jstl:when test="${loop.count % 3 == 1}">
									'rgba(50,0,200,1)'
								</jstl:when>
								
								<jstl:when test="${loop.count % 3 == 2}">
									'rgba(200, 50, 0, 1)'
								</jstl:when>
								
								<jstl:otherwise>
									'rgba(0, 200, 50, 1)'
								</jstl:otherwise>
							</jstl:choose>
							<jstl:if test="${!loop.last}"> ,</jstl:if>
						</jstl:forEach>
					]
				}
			]
		};
		var options = {
				responsive : true
			};
		var canvas, context;
		
		canvas = document.getElementById("chart-area3");
		context = canvas.getContext("2d");
		new Chart(context, {
			type : "pie",
			data : data,
			options : options
		});
	});
</script>

<script type="text/javascript">
	$(document).ready(function() {
		var data = {
			labels : ["Open-source","Closed-source"],
			datasets: [
				{
					data: [
						<jstl:forEach var="licenceCount" items="${techRecordsLicence}" varStatus="loop">
							<jstl:out value="${licenceCount}"/>
							<jstl:if test="${!loop.last}"> ,</jstl:if>							
						</jstl:forEach>
					],
					backgroundColor : [	"green", "red" ]
				}
			]
		};
		var options = {
				responsive : true
			};
		var canvas, context;
		
		canvas = document.getElementById("chart-area4");
		context = canvas.getContext("2d");
		new Chart(context, {
			type : "pie",
			data : data,
			options : options
		});
	});
</script>

<br />
<br />
<br />
<br />
<div class="containerL">
	<b><acme:message code="administrator.dashboard.form.chart.title.tools-by-sector"/></b>
	<br /><br />
	<canvas id="chart-area3"></canvas>
</div>

<div class="containerR">
	<b><acme:message code="administrator.dashboard.form.chart.title.tools-by-licence"/></b>
	<br /><br />
	<canvas id="chart-area4" ></canvas>
</div>

<script type="text/javascript">
	$(document).ready(function() {
		var data = {
			labels : [
				<jstl:forEach var="sector" items="${toolRecordsSectors}" varStatus="loop">
					"<jstl:out value="${sector}"/>"
					<jstl:if test="${!loop.last}"> ,</jstl:if>
				</jstl:forEach>		
			],
			datasets: [
				{
					data: [
						<jstl:forEach var="recordsNum" items="${toolRecordsBySector}" varStatus="loop">
							<jstl:out value="${recordsNum}"/>
							<jstl:if test="${!loop.last}"> ,</jstl:if>
							
						</jstl:forEach>
					],
					backgroundColor : [
						<jstl:forEach var="recordsNum" items="${toolRecordsBySector}" varStatus="loop">
							<jstl:choose>
								<jstl:when test="${loop.count % 3 == 1}">
									'rgba(50,0,200,1)'
								</jstl:when>
								
								<jstl:when test="${loop.count % 3 == 2}">
									'rgba(200, 50, 0, 1)'
								</jstl:when>
								
								<jstl:otherwise>
									'rgba(0, 200, 50, 1)'
								</jstl:otherwise>
							</jstl:choose>
							<jstl:if test="${!loop.last}"> ,</jstl:if>
						</jstl:forEach>
					]
				}
			]
		};
		var options = {
				responsive : true
			};
		var canvas, context;
		
		canvas = document.getElementById("chart-area");
		context = canvas.getContext("2d");
		new Chart(context, {
			type : "pie",
			data : data,
			options : options
		});
	});
</script>

<script type="text/javascript">
	$(document).ready(function() {
		var data = {
			labels : ["Open-source","Closed-source"],
			datasets: [
				{
					data: [
						<jstl:forEach var="licenceCount" items="${toolRecordsLicence}" varStatus="loop">
							<jstl:out value="${licenceCount}"/>
							<jstl:if test="${!loop.last}"> ,</jstl:if>							
						</jstl:forEach>
					],
					backgroundColor : [	"green", "red" ]
				}
			]
		};
		var options = {
				responsive : true
			};
		var canvas, context;
		
		canvas = document.getElementById("chart-area2");
		context = canvas.getContext("2d");
		new Chart(context, {
			type : "pie",
			data : data,
			options : options
		});
	});
</script>
<br />
<br />
<br />
<br />

<h2></h2>
<div class="containerL">
	<b><acme:message code="administrator.dashboard.form.chart.title.inv-round-by-kind"/></b>
	<br /><br />
	<canvas id="chart-area5"></canvas>
</div>

<div class="containerR">
	<b><acme:message code="administrator.dashboard.form.chart.title.apps-by-status"/></b>
	<br /><br />
	<canvas id="chart-area6" ></canvas>
</div>

<script type="text/javascript">
	$(document).ready(function() {
		var data = {
			labels : [
				<jstl:forEach var="kind" items="${invRoundKinds}" varStatus="loop">
					"<jstl:out value="${kind}"/>"
					<jstl:if test="${!loop.last}"> ,</jstl:if>
				</jstl:forEach>		
			],
			datasets: [
				{
					data: [
						<jstl:forEach var="invRCount" items="${invRoundsByKind}" varStatus="loop">
							<jstl:out value="${invRCount}"/>
							<jstl:if test="${!loop.last}"> ,</jstl:if>
							
						</jstl:forEach>
					],
					backgroundColor : "blue"
				}
			]
		};
		var options = {
				legend:{display:false},
				responsive : true
			};
		var canvas, context;
		
		canvas = document.getElementById("chart-area5");
		context = canvas.getContext("2d");
		new Chart(context, {
			type : "bar",
			data : data,
			options : options
		});
	});
</script>

<script type="text/javascript">
	$(document).ready(function() {
		var data = {
				labels : [
					<jstl:forEach var="status" items="${appStatus}" varStatus="loop">
						"<jstl:out value="${status}"/>"
						<jstl:if test="${!loop.last}"> ,</jstl:if>
					</jstl:forEach>		
				],
			datasets: [
				{
					data: [
						<jstl:forEach var="appCount" items="${appByStatus}" varStatus="loop">
							<jstl:out value="${appCount}"/>
							<jstl:if test="${!loop.last}"> ,</jstl:if>							
						</jstl:forEach>
					],
					backgroundColor : [	"green", "red", "gray" ]
				}
			]
		};
		var options = {
				legend:{display:false},
				responsive : true,
				scales: {yAxes:[{
					ticks:{
						suggestedMin : 0.0
					}
				}]}
			};
		var canvas, context;
		
		canvas = document.getElementById("chart-area6");
		context = canvas.getContext("2d");
		new Chart(context, {
			type : "bar",
			data : data,
			options : options
		});
	});
</script>
<br />
<br />
<br />
<br />

<h2></h2>
<div class="containerC">
	<b><acme:message code="administrator.dashboard.form.chart.title.past-15-days-apps"/></b>
	<br /><br />
	<canvas id="chart-area7"></canvas>
</div>

<script type="text/javascript">
	$(document).ready(function() {
		var data = {
			labels : [
				<jstl:forEach var="day" items="${days}" varStatus="loop">
					"<jstl:out value="${day}"/>"
					<jstl:if test="${!loop.last}"> ,</jstl:if>
				</jstl:forEach>		
			],
			datasets: [
				{
					data: [
						<jstl:forEach var="acceptedApp" items="${acceptedAppsPast15Days}" varStatus="loop">
							<jstl:out value="${acceptedApp}"/>
							<jstl:if test="${!loop.last}"> ,</jstl:if>
							
						</jstl:forEach>
					],
					label : "ACCEPTED",
					borderColor : "green",
					fill : false
				}, {
					data: [
						<jstl:forEach var="rejectedApp" items="${rejectedAppsPast15Days}" varStatus="loop">
							<jstl:out value="${rejectedApp}"/>
							<jstl:if test="${!loop.last}"> ,</jstl:if>
							
						</jstl:forEach>
					],
					label : "REJECTED",
					borderColor : "red",
					fill : false
				}, {
					data: [
						<jstl:forEach var="pendingApp" items="${pendingAppsPast15Days}" varStatus="loop">
							<jstl:out value="${pendingApp}"/>
							<jstl:if test="${!loop.last}"> ,</jstl:if>
							
						</jstl:forEach>
					],
					label : "PENDING",
					borderColor : "gray",
					fill : false
				}
			]
		};
		var options = {
				responsive : true
			};
		var canvas, context;
		
		canvas = document.getElementById("chart-area7");
		context = canvas.getContext("2d");
		new Chart(context, {
			type : "line",
			data : data,
			options : options
		});
	});
</script>


