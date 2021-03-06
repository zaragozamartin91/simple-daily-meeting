<!doctype html>
<html lang="en">
<head>

<#import "lib/head.ftl" as h>
<@h.children />

</head>
<body>

<#import "lib/navigator.ftl" as n>
<@n.navelem homeClass="" sprintClass="" dmeetingClass="active" />

<div class="container">

<#if dmeetingId??>
    <div class="alert alert-success" role="alert">Daily meeting ${dmeetingId} creado exitosamente</div>
</#if>


<#if error??>
    <div class="alert alert-danger" role="alert">${error}</div>
</#if>


    <!-- Main component for a primary marketing message or call to action -->
    <div class="container">
        <h1>Nuevo daily meeting</h1>
        <p id="subtitle"></p>

        <form id="dmeeting-form" action="dmeeting" method="post">
            <input type="hidden"
                   name="${_csrf.parameterName}"
                   value="${_csrf.token}"/>


            <div class="form-group">
                <label for="sprint-select">Selecciona sprint</label>
                <select name="sprint" class="form-control" id="sprint-select">
                <#list sprints as sprint>
                    <option value="${sprint.id}">${sprint.title}</option>
                </#list>
                </select>
            </div>

        <#list members as member>
        <#--<label> Tema de ${member.name}: <input name="topic${member.id}" type="text"/> </label> <br/>-->
            <div class="form-group">
                <label for="topic-${member.id}">Tema de ${member.name}</label>
                <input name="topic${member.id}" type="text" class="form-control" id="topic-${member.id}"/>
                <input name="check${member.id}" checked="checked" type="checkbox" class="form-check-input" id="check-${member.id}"/>
                <label class="form-check-label" for="check-${member.id}">Participa</label>
            </div>
        <#--<div class="form-check">-->
        <#--</div>-->
        </#list>

            <button id="dmeeting-form-btn" type="button" class="btn btn-primary col-xs-6 col-xs-12">Crear</button>
        </form>
    </div>

</div>

<#import "lib/scripts.ftl" as s>
<@s.elems />

<script>
    $(document).ready(function () {
        var today = new Date();
        var strDate = today.getFullYear() + "-" + (today.getMonth() + 1) + "-" + today.getDate();
        document.querySelector("#subtitle").innerHTML = "Reunion del dia " + strDate;

        document.querySelector("#dmeeting-form-btn").onclick = function (e) {
            e.preventDefault();
            document.querySelector("#dmeeting-form").submit();
        };
    });
</script>

</body>
</html>