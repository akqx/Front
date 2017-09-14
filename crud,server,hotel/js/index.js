$(document).ready(function() {

    $(".conteiner").click(function() {
        $(".main").hide(1000);
        $("[a] ").css('color', 'purple');
    })

    $('.photo_flickr').click(function(){
    var idimg = $(this).attr('id');
    var srcimg = $(this).attr('src');
    alert('ID is: '+ idimg+ '\n SRC: '+ srcimg);
       
}); 
    });


$('#mainphoto').hide();
$('.main').hide();
//API FLICKR
$(".conteiner a").on('click',  function() {

    $(".conteiner a").removeClass('pressed');
    $(this).addClass('pressed');

$('#mainphoto').show();
$('.main').show();
    document.getElementById("subpage_city").innerHTML = $(this).attr('id');
    document.getElementById("subpage_city1").innerHTML = $(this).attr('id');
    
    city = $(this).attr('id');
    
  $.simpleWeather({
    location:$(this).attr('id'),
    unit: 'c',
    success: function(weather) {
     html = '<h2><i class="icon-'+weather.code+'"></i> '+weather.temp+'&deg;'+weather.units.temp+'</h2>';
      html += '<ul><li>'+weather.city+', '+weather.region+'</li>';
      
      $("#weather").html(html);
    },
    error: function(error) {
      $("#weather").html('<p>'+error+'</p>');
    }
  });


    
    $.getJSON("http://api.flickr.com/services/feeds/photos_public.gne?jsoncallback=?", {
            tags: $(this).attr('id'),
            tagmode: "any",
            format: "json"
        },
        function(data) {
            $.each(data.items, function(i, item) {
                //changing the size of photo to large
                var imgLarge = item.media.m.split('m.jpg')[0] + 'b.jpg';
                $('<img class = "photo_flickr" />').attr("src", imgLarge).appendTo(".pic" );
                
                    //take the first photo and display it on main box
                    var srcimg = $('.photo_flickr').attr('src');
                    $('#mainphoto').empty().append('<img src='+ srcimg +  '>');

                //display 8 photos
                if (i == 7) return false;
            });

                //after click the photo displays on main box            
                 $('.photo_flickr').on('click', function(){
                    var srcimg = $(this).attr('src');
                    $('#mainphoto').empty().append('<img src='+ srcimg +  '>');
                });
        });
    
    //remove the photos after click in container a               
    $('.photo_flickr').remove(); 
});



$("button").hide();
$(".conteiner a").click(function() {
console.log(city);

    $("button").show();
});

    $("#topPromotionsB").click(function() {
console.log(city);
         window.location.href = 'toppromotions.html?city='+city;
    });


$(".conteinerInf").hide();
$(".conteiner a").click(function() {
    $(".conteinerInf").show();
});


$("#bestHotelsB").click(function() {
    console.log(city)
    window.location.href = 'besthotels.html';
});

