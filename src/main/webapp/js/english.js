var date = new Date();
var hours = date.getHours();
var day = date.getDay();

function check(){
    if(day == 0){
           if(hours >= 17 || hours < 8){
               document.getElementById("isOpened").innerHTML = "Closed now";
           }
           else{
               document.getElementById("isOpened").innerHTML = "Now openly";
           }
    }
    else{
           if(hours >= 20 || hours < 8){
               document.getElementById("isOpened").innerHTML = "Closed now";
           }
           else{
               document.getElementById("isOpened").innerHTML = "Now openly";
           }
    }
}

check();