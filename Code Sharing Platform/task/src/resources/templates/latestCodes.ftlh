<#-- @ftlvariable name="codes" type="java.util.List<platform.models.Code>" -->
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Latest</title>
    <link rel="stylesheet" href="/css/codeReadCSS.css">
    <link rel="stylesheet"
          target="_blank" href="//cdn.jsdelivr.net/gh/highlightjs/cdn-release@10.2.1/build/styles/default.min.css">
    <script src="//cdn.jsdelivr.net/gh/highlightjs/cdn-release@10.2.1/build/highlight.min.js"></script>
    <script>hljs.initHighlightingOnLoad();</script>
</head>
<body>
    <#list codes as code>
        <p>
            <span id="load_date">${code.getDateAsString()}</span>
        </p>
        <pre id="code_snippet"><code>${code.code}</code></pre>
    </#list>
</body>
</html>