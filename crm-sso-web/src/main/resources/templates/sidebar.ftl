<div id="sidebar">
    <ul class="side-menu">
        <#list bars as bar>
            <li id="__actData" class=""><a onclick="${bar.func}"><span >${bar.name}</span></a></li>
        </#list>
    </ul>
</div>
