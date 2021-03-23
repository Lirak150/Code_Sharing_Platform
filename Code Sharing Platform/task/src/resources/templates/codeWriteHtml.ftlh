<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Create</title>
    <link rel="stylesheet" href="/css/codeWriteCSS.css"/>
    <script src="/js/submitButton.js" type="text/javascript">
    </script>
    <link rel="stylesheet"
          target="_blank" href="//cdn.jsdelivr.net/gh/highlightjs/cdn-release@10.2.1/build/styles/default.min.css">
    <script src="//cdn.jsdelivr.net/gh/highlightjs/cdn-release@10.2.1/build/highlight.min.js"></script>
    <script>hljs.initHighlightingOnLoad();</script>
</head>
<body>
<textarea id="code_snippet">//write your code here</textarea>
<form>
    <p><label for="time_restriction"><b>Time restriction:</b></label><input id="time_restriction" type="text"/></p>
    <p><label for="views_restriction"><b>Views restriction:</b></label><input id="views_restriction" type="text"/></p>
    <button id="send_snippet" type="submit" onclick="send()">
        Submit
    </button>
</form>
</body>
</html>
