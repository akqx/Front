var express = require("express");
var app = express();
var bodyParser = require('body-parser');
app.use(express.static(__dirname))
app.use(bodyParser.json());
app.use(bodyParser.urlencoded({ extended: true }));
var fs = require('fs');


app.post("/post", function(request, response,next) {
    var person = request.body;
    response.send(person);
    console.log('POST person');
    save(person, function(err) {
        if (err) {
            response.status(404).send('User not saved');
            return;
             next();
        }
    });

    app.get('/get', function(req, res, next) {

        res.send(person);
        console.log('GET person');
        next();
    });
});

function save(person, callback) {
    fs.writeFile(__dirname + '/date/.reserva.json', JSON.stringify(person), callback, '\t');
}

app.listen(3000);
console.log(" locakhost:3000");
