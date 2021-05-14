$(document).ready(function (){
    $("#srchbtn").click(function() {
        alert("hey");
        window.location.replace("localhost:8080/info/" + $("#tbMain").val());
    });
});

