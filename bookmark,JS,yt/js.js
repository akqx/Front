// listener
document.getElementById('myForm').addEventListener('submit', saveBookmark);

function saveBookmark(e) {
    //pobieranie danych z forma
    var siteName = document.getElementById('siteName').value;
    var siteURL = document.getElementById('siteURL').value;

    var bookmark = {
        name: siteName,
        url: siteURL
    }
    // localStorage.setItem('test','HelloWorls');
    // console.log(localStorage.getItem('test'));
    // localStorage.removeItem('test');
    //utworzenie localStroarge item o nazwie bookmarks

    if (localStorage.getItem('bookmarks') === null) {
        var bookmarks = [];
        //tablicca do przechowywania naszych danych 
        bookmarks.push(bookmark);
        //TERAZ zachowuwjem nasze dade, w tablicy, i bierzemy wartości Java Script na obiekty JSON  
        localStorage.setItem('bookmarks', JSON.stringify(bookmarks));
    } else {
        //JSON pars-> parse służy do pobierania tego JSON na javaScript
        var bookmarks = JSON.parse(localStorage.getItem('bookmarks'));
        bookmarks.push(bookmark);
        localStorage.setItem('bookmarks', JSON.stringify(bookmarks));

    }


  // Clear form
  document.getElementById('myForm').reset();

  // Re-fetch bookmarks
  fetchBookmarks();

  // Prevent form from submitting
  e.preventDefault();




}

function deleteBookmark(url) {
    // Get bookmarks from localStorage
    var bookmarks = JSON.parse(localStorage.getItem('bookmarks'));
    // Loop throught bookmarks
    for (var i = 0; i < bookmarks.length; i++) {
        if (bookmarks[i].url == url) {
            // Remove from array
            bookmarks.splice(i, 1);
        }
    }
    // Re-set back to localStorage
    localStorage.setItem('bookmarks', JSON.stringify(bookmarks));

    // Re-fetch bookmarks
    fetchBookmarks();
}




function fetchBookmarks() {
    // Get bookmarks from localStorage
    var bookmarks = JSON.parse(localStorage.getItem('bookmarks'));
    // Get output id
    var bookmarksResults = document.getElementById('bookmarksResults');
    for (var i = 0; i < bookmarks.length; i++) {
        var name = bookmarks[i].name;
        var url = bookmarks[i].url;

        console.log(url);
        bookmarkResoults.innerHTML +=
            '<div class="well">' +
            '<h3>' + name +
            ' <a class="btn btn-default" target="_blank" href="' + url + '">Visit</a> ' +
            ' <a onclick="deleteBookmark(\'' + url + '\')" class="btn btn-danger" href="#">Delete</a> ' +
            '</h3>' +
            '</div>';

    }

}