var date = new Date();
var hours = date.getHours();
var day = date.getDay();

function check(){
    if(day == 0){
           if(hours >= 17 || hours < 8){
               document.getElementById("isOpened").innerHTML = "現已關閉 ";
           }
           else{
               document.getElementById("isOpened").innerHTML = "現在開門了 ";
           }
    }
    else{
           if(hours >= 20 || hours < 8){
               document.getElementById("isOpened").innerHTML = "現已關閉 ";
           }
           else{
               document.getElementById("isOpened").innerHTML = "現在開門了 ";
           }
    }
}

check();