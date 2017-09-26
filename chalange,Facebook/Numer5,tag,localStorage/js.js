(function() {
    'use strict';

    var rama = document.getElementById("rama");
    var text = document.getElementById('tag');
    var button = document.getElementsByTagName('button');


    function createDiv(textInner) {
        var newEl = document.createElement('div');
        var btn = document.createElement("button");
        var btnText = document.createTextNode("\u00D7");
        newEl.className += "hasz";
        newEl.innerHTML = textInner;
        newEl.appendChild(btn);
        rama.appendChild(newEl);
        btn.appendChild(btnText);
        btn.className += "buttonStyle";
        btn.addEventListener('click', function() {
            newEl.remove();

        });

    }






    // function deleteBut(button) {
    //     button.remove(this.button);
    // }

    function createTag(element) {
        var tab = element.value.trim().split(",");

        for (var i in tab) {
            if (tab[i] != "") {
                createDiv(tab[i]);

            }
        }

    }
    text.addEventListener('keyup', function(e) {
        if (e.keyCode == 13) {
            createTag(text);
            text.value = '';
        }
    });





}());