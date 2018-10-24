<!doctype html>
<html lang="en">
<head>

<#import "lib/head.ftl" as h>
<@h.children />

</head>
<body>

<#import "lib/navigator.ftl" as n>
<@n.navelem homeClass="" sprintClass="active" dmeetingClass="" />




<!-- Main component for a primary marketing message or call to action -->
<div class="container">

<#if sprintId??>
    <div class="alert alert-success" role="alert">Sprint ${sprintId} creado exitosamente</div>
</#if>

<#if error??>
    <div class="alert alert-danger" role="alert">${error}</div>
</#if>

    <h1>Alta de sprint</h1>
    <p>Crear un sprint que contenga una o mas daily meetings</p>

    <form id="sprint-form" action="sprint" method="post">
        <input type="hidden"
               name="${_csrf.parameterName}"
               value="${_csrf.token}"/>

        <div class="form-group">
            <label for="sprint-title">Titulo</label>
            <input name="title" type="text" class="form-control" id="sprint-title"/>
        </div>

        <label>
            Fecha de inicio:
            <input name="startDate" type="date"/>
        </label><br/>
        <label>
            Fecha de cierre:
            <input name="endDate" type="date"/>
        </label><br/>
        <button id="sprint-form-btn" type="button" class="btn btn-primary col-xs-12">Crear</button>
    </form>
</div>



<#import "lib/scripts.ftl" as s>
<@s.elems />

<script>
    $(document).ready(function () {
        document.querySelector("#sprint-form-btn").onclick = function (e) {
            e.preventDefault();
            document.querySelector("#sprint-form").submit();
        }
    });
</script>

</body>
</html>