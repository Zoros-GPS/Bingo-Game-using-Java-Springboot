document.getElementById('newgame').addEventListener('click', () => {
    document.getElementById("result").style.display="none";
    
    const xhr = new XMLHttpRequest();
    xhr.open("GET", "http://localhost:8080/newgame");
    xhr.send();
    xhr.responseType = "json";
    xhr.onload = () => {
    if (xhr.readyState == 4 && xhr.status == 200) {
        const data = xhr.response;
        for (let row = 0; row < data.length; row++) {
            let cellId = `C${row}`;
            document.getElementById(cellId).innerText = data[row];
        }
    } else {
        console.log(`Error: ${xhr.status}`);
    }
};

document.getElementById("callnum").disabled = false;
const tds = document.querySelectorAll('td');
tds.forEach(td => {
  td.classList.remove('highlight'); 
});

});

const tds = document.querySelectorAll('td');
tds.forEach(td => {
    td.addEventListener('click', function() {
    this.classList.toggle('highlight');
    });
});

document.getElementById('callnum').addEventListener('click', () => {

    const xhr = new XMLHttpRequest();
    xhr.open("GET", "http://localhost:8080/call");
    xhr.send();
    xhr.responseType = "json";
    xhr.onload = () => {
    if (xhr.readyState == 4 && xhr.status == 200) {
        const data = xhr.response;
        document.getElementById("disp").innerText = data[0];
    if(data[1]==0){
        document.getElementById("result").innerText = "B I N G O";
        document.getElementById("callnum").disabled = true;
        document.getElementById("result").style.display="block";
    }

    if(data[1]==12){
        document.getElementById("result").innerText = "Better luck Next Time";
        document.getElementById("result").style.display="block";
        document.getElementById("callnum").disabled = true;}
    } else {
        console.log(`Error: ${xhr.status}`);
    }
    };
});