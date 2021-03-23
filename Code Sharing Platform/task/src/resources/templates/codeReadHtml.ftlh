<#-- @ftlvariable name="code" type="platform.models.Code" -->
<!DOCTYPE html>
<html lang=\ru\>
<head>
    <meta charset="UTF-8">
    <title>Code</title>
    <link rel="stylesheet" href="/css/codeReadCSS.css">
    <link rel="stylesheet"
          target="_blank" href="//cdn.jsdelivr.net/gh/highlightjs/cdn-release@10.2.1/build/styles/default.min.css">
    <script src="//cdn.jsdelivr.net/gh/highlightjs/cdn-release@10.2.1/build/highlight.min.js"></script>
    <script>hljs.initHighlightingOnLoad();</script>
</head>
<body>
<p>
    <span id="load_date">${code.getDateAsString()}</span>
</p>
<#if (code.isViewsRestricted())><span id="views_restriction"><span class="emphasizedPhrase">${code.getViews()}</span> more views allowed
    </span></#if>
<#if (code.isTimeRestricted())><br><span id="time_restriction">The code will be available for <span
            class="emphasizedPhrase">${code.getTime()}</span> seconds</span></#if>
<pre id="code_snippet"><code>${code.code}</code></pre>
</body>
</html>
