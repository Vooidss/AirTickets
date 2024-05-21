

function submitForm(event) {
    function submitForm() {
        const login = document.getElementById('login').value;
        const password = document.getElementById('password').value;

        const xhr = new XMLHttpRequest();
        xhr.open("POST", "/auth", true);
        xhr.setRequestHeader("Content-Type", "application/json;charset=UTF-8");

        xhr.onreadystatechange = function () {
            if (xhr.readyState === 4 && xhr.status === 200) {
                const response = JSON.parse(xhr.responseText);
                console.log(response);
            }
        };

        xhr.send(JSON.stringify({ login: login, password: password }));
    }
}