<!doctype html>
<html lang="en">
<head>

<#import "lib/head.ftl" as h>
<@h.children />

</head>
<body>

<#import "lib/navigator.ftl" as n>
<@n.navelem homeClass="active" sprintClass="" dmeetingClass="" />

<div class="container">

    <!-- Main component for a primary marketing message or call to action -->
    <div class="jumbotron">
        <h1>Daily Meeting</h1>
    <#if name??>
        Hi ${name}, How are you?
    </#if>
        <p>Ejemplo de pantalla de administrador de daily meetings.</p>

        <a href="dmeetingform">
            <button type="button" class="btn btn-primary">Nuevo daily meeting</button>
        </a>
    </div>

</div>

<#import "lib/scripts.ftl" as s>
<@s.elems />

</body>
</html>