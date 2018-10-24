<!doctype html>
<html lang="en">
<head>

<#import "lib/head.ftl" as h>
<@h.children />

</head>
<body>

<#import "lib/navigator.ftl" as n>
<@n.navelem homeClass="" sprintClass="active" dmeetingClass="" />

<div class="container">

<#if sprintId??>
    <p class="text-success">Sprint ${sprintId} creado exitosamente</p>
</#if>

<#if error??>
    <p class="text-danger">${error}</p>
</#if>

    <!-- Main component for a primary marketing message or call to action -->
    <div class="container">
        <h1>Alta de sprint</h1>
        <p>Crear un sprint que contenga una o mas daily meetings</p>

        <form id="sprint-form" action="sprint" method="post">
            <input type="hidden"
                   name="${_csrf.parameterName}"
                   value="${_csrf.token}"/>
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