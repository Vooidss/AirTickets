function showOneWayForm() {
    document.getElementById('one-way-form').style.display = 'block';
    document.getElementById('round-trip-form').style.display = 'none';
    document.getElementById('firstbut').style.background = '#3d3b3b';
    document.getElementById('secondbut').style.background =  '#000000';
}

function showRoundTripForm() {
    document.getElementById('one-way-form').style.display = 'none';
    document.getElementById('round-trip-form').style.display = 'block';
    document.getElementById('secondbut').style.background = '#3d3b3b';
    document.getElementById('firstbut').style.background = '#000000';
}

// Профиль

const modal = document.querySelector('#modal');
const btn = document.querySelector('#openModal');
const popup = document.querySelector('.popup');
const account = document.querySelector('.account');

btn.onclick = function () {
  modal.style.display = 'block';
  console.log("откр");
};

popup.onclick = function () {
  modal.style.display = 'none';
};

