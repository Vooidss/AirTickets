document.addEventListener("DOMContentLoaded", () => {
    fetch('../../static/json/infoTicket.json')
        .then(response => {
            if (!response.ok) {
                throw new Error('Network response was not ok ' + response.statusText);
            }
            return response.json();
        })
        .then(data => {
            console.log(JSON.stringify(data,null,2));
            document.getElementById("buyer").textContent = data.owner.nsp;
            document.getElementById("arrivalCity").textContent = data.arrivalCity.country.nameCountry + "," + data.arrivalCity.name;
            document.getElementById("sendingCity").textContent = data.sendingCity.country.nameCountry + "," + data.sendingCity.name;
            document.getElementById("arrivalDate").textContent = data.sendingDate;
            document.getElementById("sendingDate").textContent = data.arrivalDate;
            document.getElementById("countpeople").textContent = data.countpeople;
            document.getElementById("price").textContent = data.price;
        })
        .catch(error => {
            console.error('There was a problem with the fetch operation:', error);
        });
});