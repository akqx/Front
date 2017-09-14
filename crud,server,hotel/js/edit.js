$(".stars").click(function() {
    $(this).prevAll().css('background-color', 'orange');
    $(this).css('background-color', 'orange');
    console.log($(this).attr('id'));
});


