<H1>Endpoint documentation available at: <br> http://localhost:8080/swagger-ui/index.html#</H1>

<H2>/stars/add add new star</H2>
Body requiered:</br>
<code>{<br>
"id": 0,<br>
"name": "string",<br>
"distance": 0<br>
}<br>
</code>

Response:<br>
<code>{<br>
"id": 0,<br>
"name": "string",<br>
"distance": 0<br>
}<br>
</code>

<H2>/stars/{id} GET get star by ID</H2>
Id required.<br>
Response:<br>
<code>{<br>
"id": 0,<br>
"name": "string",<br>
"distance": 0<br>
}<br>
</code>

<H2>/stars/delete/{id} delete star by ID</H2>
Id required. Response: Http status.

<H2>/stars/update/{id} update star</H2>
Id, body required.<br>
Body:</br>
<code>{<br>
"id": 0,<br>
"name": "string",<br>
"distance": 0<br>
}<br>
</code>

Response:<br>
<code>{<br>
"id": 0,<br>
"name": "string",<br>
"distance": 0<br>
}<br>
</code>

<H2>/stars/distances get stars by distance</H2>
Body required:<br>
Body:</br>
<code>{<br>
"id": 0,<br>
"name": "string",<br>
"distance": 0<br>
}<br>
</code>

<H2>/stars/closest get closest stars</H2>
Body required:<br>
Body:</br>
<code>{<br>
"id": 0,<br>
"name": "string",<br>
"distance": 0<br>
}<br>
</code>

<H2>/stars/unique get stars w/o duplicates</H2>
Body required:<br>
Body:</br>
<code>{<br>
"id": 0,<br>
"name": "string",<br>
"distance": 0<br>
}<br>
</code>
