$(function() {
    // GET/READ$('.item').first().hide();
    $(document).ready(function() {
        $.ajax({
            url: '/hotels',
            method: 'GET',
            contentType: 'application/json',
            success: function(response) {
                var tbodyEl = $('tbody');

                tbodyEl.html('');

  

                response.hotels.forEach(function(hotel) {
                    tbodyEl.append('\
                        <tr class="item" >\
                            \
                             <td class="id">' + hotel.id + '</td>\
                            <td class="name">' + 'Name: ' + hotel.name + '</td>\
                            <td class="price" >' + 'Price: ' + hotel.price + '</td>\
                             <td class="desc" ' + 'Description: ' + hotel.desc + '</td>\
                            <td class="containerBestItem">\
                                <button class="update-button deleteButton">EDIT</button>\
                                <button class="delete-button deleteButton">DELETE</button>\
                            </td>\
                        </tr>\
                    ');
                });  $('.item').first().hide();
            }
          
        });
                        
    });

    // CREATE/POST
    $('#create-form').on('submit', function(event) {
        event.preventDefault();

        var createInputName = $('#create-inputName');
        var createInputPrice = $('#create-inputPrice');
        var createInputDesc = $('#create-inputDesc');

        $.ajax({
            url: '/hotels',
            method: 'POST',
            contentType: 'application/json',
            data: JSON.stringify({ name: createInputName.val(), price: createInputPrice.val(), desc: createInputDesc.val() }),
            success: function(response) {
                console.log(response);
                createInputName.val('');
                createInputPrice.val('');
                createInputDesc.val('');
                $('#get-button').click();
            }
        });
    });

    
    $('table').on('click', '.delete-button', function() {
        window.location.reload();
    });
    


    // UPDATE/PUT
    $('table').on('click', '.update-button', function() {
        window.location.href = 'edit.html';
        var rowEl = $(this).closest('tr');
        var id = rowEl.find('.id').text();

        var newName = $('#nameEdit').val();
        var newPrice = $('#priceEdit').val();
        var newDesc = $('#descEdit').val();
        $.ajax({
            url: '/hotels/' + id,
            method: 'PUT',
            contentType: 'application/json',
            data: JSON.stringify({ name: newName, price: newPrice, newDesc: newDesc }),
            success: function(response) {
                console.log(response);
                $('#update-buttonEdit').click();

            }
        });
    });

    // DELETE
    $('table').on('click', '.delete-button', function() {
        var rowEl = $(this).closest('tr');
        var id = rowEl.find('.id').text();

        $.ajax({
            url: '/hotels/' + id,
            method: 'DELETE',
            contentType: 'application/json',
            success: function(response) {
                console.log(response);
                $('#get-button').click();
            }
        });
    });
});
