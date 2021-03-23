function send() {
    let code = document.getElementById("code_snippet").value
    let time = parseInt(document.getElementById("time_restriction").value)
    let views = parseInt(document.getElementById("views_restriction").value)
    let object = {
        "code": code,
        "time": time,
        "views": views,
    };

    let json = JSON.stringify(object);

    let xhr = new XMLHttpRequest();
    xhr.open("POST", '/api/code/new', false)
    xhr.setRequestHeader('Content-type', 'application/json; charset=utf-8');
    xhr.send(json);

    if (xhr.status === 200) {
        alert("Success!");
    }
}